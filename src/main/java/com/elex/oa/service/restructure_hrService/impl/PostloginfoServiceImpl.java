package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.restructure_hr.IPostinfoDao;
import com.elex.oa.dao.restructure_hr.IPostloginfoDao;
import com.elex.oa.entity.hr_entity.ReadBaseExcel;
import com.elex.oa.entity.hr_entity.ReadPostlogExcel;
import com.elex.oa.entity.restructure_hrentity.Postinfo;
import com.elex.oa.entity.restructure_hrentity.Postloginfo;
import com.elex.oa.service.restructure_hrService.IPostloginfoService;
import com.elex.oa.util.hr_util.HrUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\12\24 0024 13:18
 * @Version 1.0
 **/
@Service
public class PostloginfoServiceImpl implements IPostloginfoService {
    @Resource
    IPostloginfoDao iPostloginfoDao;
    @Resource
    HrUtils hrUtils;
    @Resource
    IPostinfoDao iPostinfoDao;

    @Override
    public PageInfo<Postloginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Postloginfo postloginfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<Postloginfo> postloginfoList = iPostloginfoDao.selectPostloginfoListByConditions(postloginfo);
        for (Postloginfo p:postloginfoList
             ) {
            p = getDetailPostloginfoByPostloginfo(p);
        }
        return new PageInfo<>(postloginfoList);
    }

    @Override
    public List<Postloginfo> queryAllPostLogInformations() {
        List<Postloginfo> postloginfoList = iPostloginfoDao.selectAll();
        for (Postloginfo p:postloginfoList
             ) {
            p = getDetailPostloginfoByPostloginfo(p);
        }
        return postloginfoList;
    }

    @Override
    public Map<String, String> removePostlogByIds(List<String> postlogids) {
        if(null==postlogids)return null;
        Map<String,String> respMap = new HashMap<>();
        for (String id:postlogids
             ) {
            try {
                iPostloginfoDao.deleteByPrimaryKey(id);
            } catch (Exception e) {
                e.printStackTrace();
                respMap.put(id,":此条信息删除失败");
            }
        }
        return respMap.size()==0?null:respMap;
    }

    @Override
    public Map<String, String> importPostloginformations(MultipartFile multipartFile) {
        //获取对象集合
        Map<String,String> respMap = new HashMap<>();
        ReadBaseExcel readExcel = new ReadPostlogExcel();
        List<Postloginfo> postloginfoList = readExcel.getExcelInfo(multipartFile);
        //遍历并添加或修改相应的岗位日志信息
        for (Postloginfo p:postloginfoList
             ) {
            respMap = getRespMapByInsertPostloginfo(respMap,p);
        }
        return respMap;
    }

    private Map<String, String> getRespMapByInsertPostloginfo(Map<String, String> respMap, Postloginfo postloginfo) {
        //先获取岗位编号
        String postcode = postloginfo.getPostcode();
        if(StringUtils.isEmpty(postcode)){
            //岗位编号不存在则用岗位名称（不严谨）
            postcode = hrUtils.getPostcodeByPostname(postloginfo.getPostname());
        }
        if(StringUtils.isEmpty(postcode))return respMap;
        Postinfo postinfoTemp = iPostinfoDao.selectByPrimaryKey(postcode);
        if(null==postinfoTemp){
            respMap.put(postcode,":该岗位编号所在的岗位不存在！");
            return respMap;
        }
        //添加相应的岗位日志信息
        try {
            iPostloginfoDao.insert(postloginfo);
        } catch (Exception e) {
            e.printStackTrace();
            respMap.put(postcode,":该岗位的数据导入失败！");
            return respMap;
        }
        return respMap;
    }


    private Postloginfo getDetailPostloginfoByPostloginfo(Postloginfo postloginfo) {
        postloginfo.setPostname(hrUtils.getPostnameByPostcode(postloginfo.getPostcode()));
        postloginfo.setTransactortruename(hrUtils.getTruenameByEmployeenumberInnewtable(postloginfo.getTransactoruserid()));
        return postloginfo;
    }
}