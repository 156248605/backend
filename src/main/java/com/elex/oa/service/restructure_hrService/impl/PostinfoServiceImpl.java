package com.elex.oa.service.restructure_hrService.impl;

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

    //根据岗位（粗略的信息）获得详细的岗位信息
    private Postinfo getPostinfoDetailByPostinfo(Postinfo postinfo) {
        if(null==postinfo)return postinfo;
        //获得职能类型
        postinfo.setFunctionaltype(hrUtilsTemp.getDatavalueByDatacode(postinfo.getFunctionaltypeid()));
        //获取上级岗位
        postinfo.setParentpost(iPostinfoDao.selectByPrimaryKey(postinfo.getParent_postcode()));
        //获取职级
        postinfo.setPostrank(hrUtilsTemp.getDatavalueByDatacode(postinfo.getPostrankid()));
        return postinfo;
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

    private String getPostcodeByAddPostinfo(Postinfo postinfo) {
        Postinfo temPostinfo = getPostinfoByPostcode(postinfo.getPostcode());
        if(null!=temPostinfo)return null;
        iPostinfoDao.insert(postinfo);
        return postinfo.getPostcode();
    }

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