package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IPerandpostrsDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.hr.IPostDao;
import com.elex.oa.dao.restructure_hr.IPostandpersonalrelationshipDao;
import com.elex.oa.entity.hr_entity.PerAndPostRs;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.Post;
import com.elex.oa.entity.restructure_hrentity.Postandpersonalrelationshipinfo;
import com.elex.oa.service.restructure_hrService.IPostandpersonalrelationshipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\12 0012 13:16
 * @Version 1.0
 **/
@Service
public class PostandpersonalrelationshipinfoServiceImpl implements IPostandpersonalrelationshipService {
    @Resource
    IPostandpersonalrelationshipDao iPostandpersonalrelationshipDao;
    @Resource
    IPerandpostrsDao iPerandpostrsDao;
    @Resource
    IPostDao iPostDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;

    @Override
    public Boolean changeTable() {
        List<PerAndPostRs> perAndPostRsList = iPerandpostrsDao.selectAll();
        for (PerAndPostRs p:perAndPostRsList
             ) {
            //获取人员岗位关系实例
            try {
                Postandpersonalrelationshipinfo postandpersonalrelationshipinfo = getPostandpersonalrelationshipinfoByPerandpostrs(p);
                if(null==postandpersonalrelationshipinfo){
                    System.out.println(p.toString());
                    continue;
                }
                iPostandpersonalrelationshipDao.insert(postandpersonalrelationshipinfo);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private Postandpersonalrelationshipinfo getPostandpersonalrelationshipinfoByPerandpostrs(PerAndPostRs p) {
        Postandpersonalrelationshipinfo postandpersonalrelationshipinfo = new Postandpersonalrelationshipinfo();
        //获取岗位编号
        Post post = iPostDao.selectPostByPostid(p.getPostid());
        if(null==post)return null;
        String postcode = post.getPostcode();
        postandpersonalrelationshipinfo.setPostcode(postcode);
        //获取员工号
        PersonalInformation personalInformation = iPersonalInformationDao.selectById(p.getPerid());
        if(null==personalInformation)return null;
        String employeenumber = personalInformation.getEmployeenumber();
        postandpersonalrelationshipinfo.setEmployeenumber(employeenumber);
        return postandpersonalrelationshipinfo;
    }
}