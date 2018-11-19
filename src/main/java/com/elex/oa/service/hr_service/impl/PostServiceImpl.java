package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.*;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.IPostService;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.hr_util.IDcodeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    IHRsetDao ihRsetDao;

    /**
     *@Author:ShiYun;
     *@Description:根据岗位ID查询岗位信息
     *@Date: 11:18 2018\4\11 0011
     */
    @Override
    public Post queryOneByPostid(Integer id) {
        Post post = iPostDao.selectPostByPostid(id);
        return getPostdetailByPost(post);
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

    private Post getPostdetailByPost(Post post) {
        if (post!=null) {
            if (null!=post.getFunctionaltypeid()) {
                post.setFunctionaltype(ihRsetDao.selectById(post.getFunctionaltypeid()).getDatavalue());
            }
            if (null!=post.getPostfamilyid()) {
                post.setPostfamily(ihRsetDao.selectById(post.getPostfamilyid()).getDatavalue());
            }
            if (null!=post.getPostgradeid()) {
                post.setPostgrade(ihRsetDao.selectById(post.getPostgradeid()).getDatavalue());
            }
            if (null!=post.getRankid()) {
                post.setRank(ihRsetDao.selectById(post.getRankid()).getDatavalue());
            }
            if (null!=post.getPostlevelid()) {
                post.setPostlevel(ihRsetDao.selectById(post.getPostlevelid()).getDatavalue());
            }
        }
        return post;
    }
}
