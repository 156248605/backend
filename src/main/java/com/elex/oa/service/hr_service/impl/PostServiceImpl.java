package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.*;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.IPostService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.hr_util.IDcodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:部门信息服务层的实现类
 * @Date:Created in  11:04 2018\3\20 0020
 * @Modify By:
 */
@Service
public class PostServiceImpl implements IPostService {

    @Resource
    IPostDao iPostDao;
    @Resource
    IPerandpostrsDao iPerandpostrsDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    HrUtils hrUtils;
    @Resource
    IPostLogDao iPostLogDao;

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID查询岗位信息
     *@Date: 11:18 2018\4\11 0011
     */
    @Override
    public Post queryOneByPostid(Integer id) {
        Post post = iPostDao.selectPostByPostid(id);
        if(null==post)return null;
        return getPostdetailByPost(post);
    }

    @Override
    public Post queryOnePostByPostid(Integer id) {
        if(null==id)return null;
        Post post = iPostDao.selectPostByPostid(id);
        if(null==post)return null;
        post = getPostdetailByPost(post);
        return post;
    }


    /**
     *@Author:ShiYun;
     *@Description:根据岗位名称查询岗位信息
     *@Date: 11:19 2018\4\11 0011
     */
    @Override
    public Post queryOneByPostname(String postname) {
        Post post = iPostDao.selectPostByPostname(postname);
        return post;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位编号查询岗位信息
     *@Date: 15:32 2018\8\11 0011
     */
    @Override
    public Post queryOneByPostcode(String postcode) {
        Post post = iPostDao.selectPostByPostcode(postcode);
        return getPostdetailByPost(post);
    }

    @Override
    public Post queryOnePostByPostcode(String postcode) {
        if(StringUtils.isBlank(postcode))return null;
        Post post = iPostDao.selectPostByPostcode(postcode);
        if(null==post)return null;
        post = getPostdetailByPost(post);
        return post;
    }


    /**
     *@Author:ShiYun;
     *@Description:查询所有岗位信息
     *@Date: 11:19 2018\4\11 0011
     */
    @Override
    public List<Post> queryAllPosts() {
        List<Post> posts = iPostDao.selectAllPosts();
        return posts;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据parentpostid查询岗位信息
     *@Date: 9:46 2018\4\23 0023
     */
    @Override
    public List<Post> queryByParentpostid(Integer parentpostid) {
        List<Post> posts = iPostDao.selectPostsByParentpostid(parentpostid);
        return posts;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加岗位信息并返回主键
     *@Date: 13:08 2018\4\23 0023
     */
    @Override
    public Integer addOne(Post post) {
        Integer integer = iPostDao.insertOne(post);
        return post.getId();
    }

    @Override
    public Object addOnePost(Post post) {
        if(null==post){
            return RespUtil.successResp("500","提交信息不能为空！",null);
        }
        if(StringUtils.isBlank(post.getPostcode())){
            return RespUtil.successResp("500","岗位编号不能为空！",null);
        }
        if(StringUtils.isBlank(post.getPostname())){
            return RespUtil.successResp("500","岗位名称不能为空！",null);
        }
        //先校验岗位编号
        Post postTemp = iPostDao.selectPostByPostcode(post.getPostcode());
        if(null!=postTemp){
            return RespUtil.successResp("500","岗位编号已经存在！",null);
        }
        //再添加岗位信息
        iPostDao.insertOne(post);
        return RespUtil.successResp("200","添加成功！",post);
    }


    /**
     *@Author:ShiYun;
     *@Description:修改岗位信息
     *@Date: 11:11 2018\5\2 0002
     */
    @Override
    public void modifyOne(Post post) {
        iPostDao.updateOne(post);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID删除岗位信息
     *@Date: 14:53 2018\5\2 0002
     */
    @Override
    public void remove(Integer id) {
        /*先删除关系表中的相关数据*/
        iPerandpostrsDao.deleteByPostid(id);
        /*再删除相应的岗位信息*/
        iPostDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有的岗位（去除下级岗位和本生）
     *@Date: 13:46 2018\8\30 0030
     */
    @Override
    public List<Post> queryPostsRemoveChilren(Integer postid) {
        List<Post> posts = iPostDao.selectAllPosts();
        List<Post> posts1 = new ArrayList<>();
        for (Post p:posts
             ) {
            if(!this.isChildPoint(postid,p.getId()) && postid!=p.getId()){
                posts1.add(p);
            }
        }
        return posts1;
    }

    /**
     *@Author:ShiYun;
     *@Description:
     *@param parentpostid:高级节点
     *@param childpostid:低级节点
     *@return 如果childpostid是parentpostid的子节点则返回true,否则返回false
     *@Date: 14:06 2018\8\30 0030
     */
    @Override
    public Boolean isChildPoint(Integer parentpostid, Integer childpostid) {
        Integer cid = childpostid;
        Integer pid = childpostid;
        if(childpostid==parentpostid){//自己不能作为自己的上级
            return false;
        }
        while (pid!=null){
            if(pid==parentpostid){//是自己的上级返回true
                return true;
            }else {
                cid = pid;
                pid = iPostDao.selectPostByPostid(cid).getParentpostid();
            }
        }
        return false;//上级为null时跳出循环（到顶点），说明不是自己的上级
    }

    /**
     *@Author:ShiYun;
     *@Description:根据登录ID查询岗位名称
     *@Date: 13:49 2018\9\25 0025
     */
    @Override
    public Object queryPostnameByUsername(String username) {
        if(StringUtils.isBlank(username))return RespUtil.successResp("500","登录名为空",null);
        User user = iUserDao.selectByUsername(username);
        if(user==null)return RespUtil.successResp("500","登录名不存在",null);
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(user.getId());
        if(personalInformation==null)return RespUtil.successResp("500","登录名的岗位信息不存在",null);
        List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectPostidsByPerid(personalInformation.getId());
        if(perAndPostRs==null ||perAndPostRs.size()==0)return RespUtil.successResp("500","登录名的岗位信息不存在",null);
        List<String> strs = new ArrayList<>();
        for (PerAndPostRs p :perAndPostRs
                ) {
            strs.add(iPostDao.selectPostByPostid(p.getPostid()).getPostname());
        }
        String postnames = IDcodeUtil.getArrayToString(strs, ";");
        return RespUtil.successResp("200","提交成功！",postnames);
    }

    @Override
    public Object updateOnePost(Post post, String transactorusername) {
        //获得就岗位信息
        Post oldPost = iPostDao.selectPostByPostid(post.getId());
        oldPost = getPostdetailByPost(oldPost);
        //获得新岗位信息
        Post newPost = getPostdetailByPost(post);
        //校验岗位编号
        Boolean isExist = getaBooleanByPostcode(newPost.getPostcode());
        if(isExist){
            //岗位编号存在
            if(!newPost.getPostcode().equals(oldPost.getPostcode())){
                //新旧岗位编号不一样则修改失败
                return RespUtil.successResp("500","岗位编号已经存在！",null);
            }
        }else if(null == isExist){
            //编号为空不合规定
            return RespUtil.successResp("500","岗位编号不能为空！",null);
        }
        //判断新旧两个对象并添加岗位日志信息
        Boolean isUpdate = getaBooleanByOldpostAndNewpost(oldPost, newPost, transactorusername);
        //修改岗位信息
        if(isUpdate){
            iPostDao.updateOne(newPost);
            return RespUtil.successResp("200","修改成功！",newPost);
        }
        return RespUtil.successResp("500","没有需要修改的信息（空值会被忽略）！",null);
    }

    @Override
    public Boolean validateByPostcode(String postcode) {
        if(StringUtils.isBlank(postcode))return false;
        Post post = iPostDao.selectPostByPostcode(postcode);
        if(null==post)return false;
        return true;
    }

    //判断新旧两个对象并添加岗位日志信息

    private Boolean getaBooleanByOldpostAndNewpost(Post oldPost,Post newPost,String transactorusername){
        if(null==oldPost.getId())return false;
        Boolean respBoolean = false;
        Boolean isUpdate = false;
        Integer postid = oldPost.getId();
        //判断岗位编号是否相同并添加岗位日志
        /*Boolean isExist = getaBooleanByPostcode(newPost.getPostcode());
        if(isExist){
            //岗位编号存在
            if(!newPost.getPostcode().equals(oldPost.getPostcode())){
                //新旧岗位编号不一样则修改失败
                return false;
            }
        }else if(null == isExist){
            //编号为空不合规定
            return false;
        }else {
        }*/
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid, oldPost.getPostcode(), newPost.getPostcode(), "岗位编号", transactorusername);
        if(isUpdate)respBoolean = true;
        //判断岗位名称并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,oldPost.getPostname(),newPost.getPostname(),"岗位名称",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断职能类型并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid, hrUtils.getDatavalueByHrsetid(oldPost.getFunctionaltypeid()), hrUtils.getDatacodeByHrsetid(newPost.getFunctionaltypeid()),"职能类型",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断上级岗位并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,getStringOfPostnameAndPostid(oldPost),getStringOfPostnameAndPostid(newPost),"岗位名称",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断职级并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid, hrUtils.getDatavalueByHrsetid(oldPost.getPostrankid()), hrUtils.getDatacodeByHrsetid(newPost.getPostrankid()),"职级",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断编制并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,oldPost.getOrganization(),newPost.getOrganization(),"编制",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断职责并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,oldPost.getDuty(),newPost.getDuty(),"职责",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断入职条件并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,oldPost.getEntryrequirements(),newPost.getEntryrequirements(),"入职需求",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断岗位概述并添加日志
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,oldPost.getJobdescription(),newPost.getJobdescription(),"岗位描述",transactorusername);
        if(isUpdate)respBoolean = true;
        //判断岗位说明书
        isUpdate = getaBooleanByBeforeAndAfterinfo(postid,oldPost.getDutyfile(),newPost.getDutyfile(),"岗位说明书",transactorusername);
        if(isUpdate)respBoolean = true;
        return respBoolean;
    }
    //获得岗位名称和岗位ID的字符串

    private String getStringOfPostnameAndPostid(Post post){
        if(null==post)return null;
        return post.getPostname()+"--"+post.getId();
    }
    //判断岗位编号是否存在

    private Boolean getaBooleanByPostcode(String postcode){
        if(StringUtils.isBlank(postcode))return null;
        Post post = iPostDao.selectPostByPostcode(postcode);
        if(null==post)return false;
        return true;
    }
    //判断新旧两个字段是否相同并添加相应的岗位日志信息

    private Boolean getaBooleanByBeforeAndAfterinfo(Integer postid,String beforeinformation,String afterinformation,String changeinformationName,String transactorusername){
        if(StringUtils.isBlank(beforeinformation))return false;
        if(beforeinformation.equals(afterinformation))return false;
        PostLog postLog = new PostLog();
        postLog.setPostid(postid);
        postLog.setChangeinformation(changeinformationName);
        postLog.setBeforeinformation(beforeinformation);
        postLog.setAfterinformation(afterinformation);
        postLog.setChangedate(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        postLog.setTransactoruserid(hrUtils.getUseridByUsername(transactorusername));
        postLog.setChangereason("业务需要");
        iPostLogDao.insertOne(postLog);
        return true;
    }
    //根据岗位对象获得详细岗位对象信息

    private Post getPostdetailByPost(Post post) {
        if(null==post)return null;
        //获得职能类型
        post.setFunctionaltype(hrUtils.getDatavalueByHrsetid(post.getFunctionaltypeid()));
        //获得职系（已过时）
        //获得职等（已过时）
        //获得岗级（级别）
        post.setPostlevel(hrUtils.getDatavalueByHrsetid(post.getPostlevelid()));
        //获得职级
        post.setPostrank(hrUtils.getDatavalueByHrsetid(post.getPostrankid()));
        //获得上级岗位（粗略信息）
        post.setParentpost(getCursoryPostByPostid(post.getParentpostid()));
        return post;
    }
    //根据postid获得粗略的岗位信息

    private Post getCursoryPostByPostid(Integer postid){
        if(null==postid)return null;
        Post post = iPostDao.selectPostByPostid(postid);
        if(null==post)return null;
        return post;
    }
}
