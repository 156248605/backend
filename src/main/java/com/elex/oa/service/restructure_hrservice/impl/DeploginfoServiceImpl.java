package com.elex.oa.service.restructure_hrservice.impl;

import com.elex.oa.dao.restructure_hr.IDepinfoDao;
import com.elex.oa.dao.restructure_hr.IDeploginfoDao;
import com.elex.oa.entity.hr_entity.readexcel.ReadBaseExcel;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import com.elex.oa.entity.restructure_hrentity.ReadDeploginfoExcel;
import com.elex.oa.service.restructure_hrservice.IDeploginfoService;
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
 * @Date 2018\12\17 0017 16:06
 * @Version 1.0
 **/
@Service
public class DeploginfoServiceImpl implements IDeploginfoService {
    @Resource
    IDeploginfoDao iDeploginfoDao;
    @Resource
    HrUtils hrUtils;
    @Resource
    IDepinfoDao iDepinfoDao;

    @Override
    public PageInfo<Deploginfo> queryDeptLogInformations(Integer pageNum, Integer pageSize, Deploginfo deploginfo) {
        PageHelper.startPage(pageNum,pageSize);
        List<Deploginfo> deploginfoList = iDeploginfoDao.selectDeploginfoListByConditions(deploginfo);
        for (Deploginfo d:deploginfoList
             ) {
            d = getDetailDeploginfoByDeploginfo(d);
        }
        return new PageInfo<>(deploginfoList);
    }

    @Override
    public List<Deploginfo> queryAllDeptLogInformations() {
        List<Deploginfo> deploginfoList = iDeploginfoDao.selectAll();
        for (Deploginfo d:deploginfoList
             ) {
            d = getDetailDeploginfoByDeploginfo(d);
        }
        return deploginfoList;
    }

    @Override
    public Map<String, String> removeDeplogByIds(List<String> deplogids) {
        if(null==deplogids)return null;
        Map<String,String> respMap = new HashMap<>();
        for (String id:deplogids
             ) {
            try {
                iDeploginfoDao.deleteByPrimaryKey(id);
            } catch (Exception e) {
                e.printStackTrace();
                respMap.put(id,"此条信息删除失败");
            }
        }
        return respMap.size()==0?null:respMap;
    }


    @Override
    public Map<String, String> importDeploginformations(MultipartFile multipartFile) {
        //获取对象集合
        Map<String,String> respMap = new HashMap<>();
        ReadBaseExcel readExcel = new ReadDeploginfoExcel();
        List<Deploginfo> deploginfoList = readExcel.getExcelInfo(multipartFile);
        //遍历并添加或修改相应的部门日志信息
        for (Deploginfo d:deploginfoList
             ) {
            respMap = getRespMapByInsertDeploginfo(respMap, d);
        }
        return respMap;
    }

    private Map<String, String> getRespMapByInsertDeploginfo(Map<String, String> respMap, Deploginfo deploginfo) {
        //先获取部门编号
        String depcode = deploginfo.getDepcode();
        if(StringUtils.isEmpty(depcode)){
            //部门号不存在则用部门名称
            depcode = hrUtils.getDepcodeByDepname(deploginfo.getDepname());
        }
        if(StringUtils.isEmpty(depcode)) return respMap;
        Depinfo depinfoTemp = iDepinfoDao.selectByPrimaryKey(depcode);
        if(null==depinfoTemp){
            respMap.put(depcode,":该部门编号所在的部门不存在！");
            return respMap;
        }
        //添加相应的部门日志信息
        try {
            iDeploginfoDao.insertSelective(deploginfo);
        } catch (Exception e) {
            e.printStackTrace();
            respMap.put(depcode,":该部门的数据导入失败！");
            return respMap;
        }
        return respMap;
    }

    private Deploginfo  getDetailDeploginfoByDeploginfo(Deploginfo deploginfo){
        deploginfo.setDepname(hrUtils.getDepnameByDepcode(deploginfo.getDepcode()));
        deploginfo.setTransactortruename(hrUtils.getTruenameByEmployeenumberInnewtable(deploginfo.getTransactoruserid()));
        return deploginfo;
    }


}
