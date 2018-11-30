package com.elex.oa.service.restructure_hrService.impl;

import com.alibaba.druid.util.StringUtils;
import com.elex.oa.dao.hr.IHRsetDao;
import com.elex.oa.dao.hr.IPostDao;
import com.elex.oa.dao.restructure_hr.IHrdatadictionaryDao;
import com.elex.oa.dao.restructure_hr.IPostinfoDao;
import com.elex.oa.entity.hr_entity.HRset;
import com.elex.oa.entity.hr_entity.Post;
import com.elex.oa.entity.restructure_hrentity.Hrdatadictionary;
import com.elex.oa.entity.restructure_hrentity.Postinfo;
import com.elex.oa.service.restructure_hrService.IPostinfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    IHRsetDao ihRsetDao;
    @Resource
    IHrdatadictionaryDao iHrdatadictionaryDao;

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