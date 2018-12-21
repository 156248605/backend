package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPostDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.dao.restructure_hr.IPostinfoDao;
import com.elex.oa.entity.hr_entity.Post;
import com.elex.oa.entity.restructure_hrentity.Postinfo;
import com.elex.oa.service.restructure_hrService.IPostinfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\28 0028 10:12
 * @Version 1.0
 **/
@Service
public class PostinfoServiceImpl implements IPostinfoService {
    @Resource
    IPostDao iPostDao;
    @Resource
    IPostinfoDao iPostinfoDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;

    @Override
    public Boolean changeTable() {
        Boolean valBoolean = true;
        List<Post> postList = iPostDao.selectAllPosts();
        for (Post p:postList
        ) {
            Postinfo temPostinfo = getPostinfoByPostcode(p.getPostcode());
            if(null!=temPostinfo){
                valBoolean = false;
                continue;
            }
            getPostcodeByAddPostinfo(getNewPostinfoByPost(p));
        }
        return valBoolean;
    }

    @Override
    public Boolean updateNodelevel() {
        //查询顶层董事长（parent_postcode='top'）
        try {
            updateNodelevelByParent_postcode("top","0");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Map<String, Object> getPostTree() {
        Map<String,Object> respMap = new HashMap<>();
        List<Postinfo> postinfoList = iPostinfoDao.selectByEntity(new Postinfo(null, "top"));
        respMap.put("title",postinfoList.get(0).getPostname());
        respMap.put("code",postinfoList.get(0).getPostcode());
        respMap.put("ordercode",postinfoList.get(0).getOrdercode());
        respMap.put("expand",true);
        //获取children值
        respMap = getRespMapByParentcode(postinfoList.get(0).getPostcode(),respMap);
        return respMap;
    }

    @Override
    public Postinfo queryOnePostByPostcode(String postcode) {
        Postinfo postinfo = getPostinfoByPostcode(postcode);
        postinfo = getPostinfoDetailByPostinfo(postinfo);
        return postinfo;
    }

    @Override
    public List<Postinfo> queryPostinfoList() {
        return iPostinfoDao.selectByEntity(new Postinfo(null,null,"1"));
    }

    @Override
    public Boolean validateByPostcode(String postcode) {
        Postinfo postTemp = getPostinfoByPostcode(postcode);
        if(null==postTemp)return false;
        return true;
    }

    @Override
    public Boolean addOnePost(Postinfo postinfo) {
        //获取不足的数据
        postinfo.setState(Commons.POST_ON);
        postinfo.setOrdercode("0");
        postinfo.setNode_level(hrUtilsTemp.getNodelevelByParentpostcode(postinfo.getParent_postcode()));
        //添加一条数据
        String postcode = getPostcodeByAddPostinfo(postinfo);
        if(null==postcode)return false;
        return true;
    }

    @Override
    public List<Map<String, String>> queryPostsRemoveChilren(String postcode) {
        if(null==postcode || StringUtils.isEmpty(postcode))return null;
        //步骤：1.获得需要被移除的岗位removeList；2.获得所有的岗位numList；3.总的减去需要被移除的就是最终结果rspList=numList-rmoveList
        //获取当前岗位信息
        Postinfo curPostinfo = iPostinfoDao.selectByPrimaryKey(postcode);
        if(null==curPostinfo)return null;
        //1.获得需要被移除的岗位removeList；
        List<Map<String, String>> removeList = getRemoveList(curPostinfo);
        //2.获得所有的岗位numList；
        List<Map<String, String>> numList = getNumList();
        //3.获得最终结果rspList=numList-rmoveList
        List<Map<String, String>> respList = getRespListByNumListAndRemoveList(numList, removeList);
        return respList;
    }

    //总的减去需要被移除的就是最终结果rspList=numList-removeList
    private List<Map<String, String>> getRespListByNumListAndRemoveList(List<Map<String, String>> numList, List<Map<String, String>> removeList) {
        List<Map<String, String>> respList = new ArrayList<>();
        Boolean aBoolean = true;
        for (Map<String,String> num:numList
        ) {
            aBoolean = true;
            loop:for (Map<String,String> remove:removeList
            ) {
                boolean tempBoolean = num.get("postcode").equals(remove.get("postcode"));
                if(tempBoolean){
                    aBoolean = false;
                    break loop;//跳出loop循环，默认值只跳出一层
                }
            }
            if(aBoolean)respList.add(num);
        }
        return respList;
    }

    //获得所有的岗位numList（里面只有postcode,postname）
    private List<Map<String, String>> getNumList() {
        List<Postinfo> postinfoList = iPostinfoDao.selectAll();
        List<Map<String, String>> numList = new ArrayList<>();
        for (Postinfo p:postinfoList
        ) {
            numList.add(getMapForPostcodeAndPostname(p));
        }
        return numList;
    }

    //获得需要被移除的岗位removeList（里面只有postcode,postname）
    private List<Map<String, String>> getRemoveList(Postinfo curPostinfo) {
        List<Map<String, String>> removeList = new ArrayList<>();
        removeList.add(getMapForPostcodeAndPostname(curPostinfo));
        //添加需要被移除的子岗位
        removeList = getChildrenAndShelfPostinfo(removeList,curPostinfo.getPostcode());
        return removeList;
    }

    ////获得子岗位对象集合（里面只有postcode,postname）
    private List<Map<String, String>> getChildrenAndShelfPostinfo(List<Map<String, String>> removeList, String parent_postcode) {
        List<Postinfo> postinfoList = iPostinfoDao.select(new Postinfo(null, parent_postcode));
        if(null==postinfoList)return removeList;
        for (Postinfo p:postinfoList
        ) {
            //将被移除对象放入集合
            removeList.add(getMapForPostcodeAndPostname(p));
            //递归查询下级岗位
            removeList = getChildrenAndShelfPostinfo(removeList,p.getPostcode());
        }
        return removeList;
    }

    //获得岗位对象（里面只有postcode,postname）
    private Map<String, String> getMapForPostcodeAndPostname(Postinfo p) {
        Map<String, String> respMap = new HashMap<>();
        respMap.put("postcode", p.getPostcode());
        respMap.put("postname", p.getPostname());
        return respMap;
    }

    //根据岗位（粗略的信息）获得详细的岗位信息
    private Postinfo getPostinfoDetailByPostinfo(Postinfo postinfo) {
        if(null==postinfo)return postinfo;
        //获得职能类型
        postinfo.setFunctionaltype(hrUtilsTemp.getDatavalueByDatacode(postinfo.getFunctionaltypeid()));
        //获取上级岗位
        postinfo.setParentpost(iPostinfoDao.selectByPrimaryKey(postinfo.getParent_postcode()));
        //获取职级
        postinfo.setPostrank(hrUtilsTemp.getDatavalueByDatacode(postinfo.getPostrankid()));
        //获取下级岗位
        postinfo.setChildrenPostnames(getChildrenPostnamesByParentpostcode(postinfo.getPostcode()));
        return postinfo;
    }

    //获取下级岗位名称
    private String getChildrenPostnamesByParentpostcode(String parent_psotcode) {
        if(null == parent_psotcode || StringUtils.isEmpty(parent_psotcode))return null;
        String childrenPostnames = null;
        List<Postinfo> tempPostinfoList = iPostinfoDao.selectByEntity(new Postinfo(null, parent_psotcode));
        if(null!=tempPostinfoList && tempPostinfoList.size()>0){
            childrenPostnames = "";
            for (Postinfo p:tempPostinfoList
                 ) {
                childrenPostnames += p.getPostname()+";";
            }
            childrenPostnames = childrenPostnames.substring(0,childrenPostnames.length()-2);
        }
        return childrenPostnames;
    }

    //获得岗位树的数据
    private Map<String, Object> getRespMapByParentcode(String parentcode,Map<String,Object> respMap){
        List<Postinfo> postinfoList = iPostinfoDao.selectByEntity(new Postinfo(null, parentcode));
        if(postinfoList==null)return respMap;
        List<Map<String, Object>> children = new ArrayList<>();
        for (Postinfo p:postinfoList
        ) {
            Map<String,Object> respMapTemp = new HashMap<>();
            respMapTemp.put("title",p.getPostname());
            respMapTemp.put("code",p.getPostcode());
            respMapTemp.put("ordercode",p.getOrdercode());
            respMap.put("expand",true);
            respMapTemp = getRespMapByParentcode(p.getPostcode(), respMapTemp);
            children.add(respMapTemp);
        }
        respMap.put("children",getOrderedChildren(children));
        return respMap;
    }

    //获得排序后的子节点
    private List<Map<String, Object>>  getOrderedChildren(List<Map<String, Object>> children){
        //将子节点排序
        children.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer ordercode1 = Integer.parseInt((String)(o1.get("ordercode")));
                Integer ordercode2 = Integer.parseInt((String)(o2.get("ordercode")));
                return ordercode1.compareTo(ordercode2);
            }
        });
        return children;
    }

    //根据父节点的postcode和父节点的node_level来级联更新它的所有子节点的层级
    private void updateNodelevelByParent_postcode(String parent_postcode,String parent_nodelevel){
        String current_postlevel = (Integer.parseInt(parent_nodelevel)+1)+"";
        List<Postinfo> tempPostinfoList = iPostinfoDao.selectByEntity(new Postinfo(null, parent_postcode));
        if(null==tempPostinfoList || tempPostinfoList.size()==0)return;
        for (Postinfo p:tempPostinfoList
             ) {
            //更新层级数据
            p.setNode_level(current_postlevel);
            iPostinfoDao.updateByEntity(p);
            updateNodelevelByParent_postcode(p.getPostcode(),current_postlevel);
        }
    }

    //添加一条岗位信息并返回postcode(主键)
    private String getPostcodeByAddPostinfo(Postinfo postinfo) {
        Postinfo temPostinfo = getPostinfoByPostcode(postinfo.getPostcode());
        if(null!=temPostinfo)return null;
        iPostinfoDao.insert(postinfo);
        return postinfo.getPostcode();
    }

    //根据旧对象（Post）获得新对象（Postinfo）
    private Postinfo getNewPostinfoByPost(Post p) {
        Postinfo postinfo = new Postinfo();
        postinfo.setPostcode(p.getPostcode());
        postinfo.setParent_postcode(getPostcodeByPostid(p.getParentpostid()));
        postinfo.setPostname(p.getPostname());
        postinfo.setFunctionaltypeid(new HrUtilsTemp().getDatacodeByHrsetid(p.getFunctionaltypeid()));
        postinfo.setPostfamilyid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostfamilyid()));
        postinfo.setPostgradeid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostgradeid()));
        postinfo.setPostrankid(new HrUtilsTemp().getDatacodeByHrsetid(p.getRankid()));
        postinfo.setPostlevelid(new HrUtilsTemp().getDatacodeByHrsetid(p.getPostlevelid()));
        postinfo.setOrganization(p.getOrganization());
        postinfo.setJobdescription(p.getJobdescription());
        postinfo.setDuty(p.getDuty());
        postinfo.setEntryrequirements(p.getEntryrequirements());
        postinfo.setDutyfile(p.getDutyfile());
        postinfo.setState(Integer.toString(p.getState()));
        postinfo.setOrdercode(Integer.toString(p.getOrdercode()));
        /*层级手动添加(默认4)*/
        postinfo.setNode_level("4");
        return postinfo;
    }

    //根据tb_id_post表的id查询岗位编号（已过时）
    private String getPostcodeByPostid(Integer parentpostid) {
        Post post = iPostDao.selectPostByPostid(parentpostid);
        if(null==post)return "top";
        return post.getPostcode();
    }

    //根据岗位编号获得岗位信息（粗略信息）
    private Postinfo getPostinfoByPostcode(String postcode) {
        if(StringUtils.isEmpty(postcode))return null;
        List<Postinfo> postinfoList = iPostinfoDao.selectByEntity(new Postinfo(postcode));
        if(null == postinfoList || postinfoList.size()==0){
            return null;
        }else if(postinfoList.size()==1){
            return postinfoList.get(0);
        }
        return null;
    }
}