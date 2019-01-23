package com.elex.oa.service.ouService.Impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.ou.OuDepDao;
import com.elex.oa.entity.ou.OuDep;
import com.elex.oa.service.ouService.IOuDepService;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:16
 * @Version 1.0
 **/
@Service
public class OuDepServiceImpl implements IOuDepService {
    @Resource
    private OuDepDao ouDepDao;

    @Override
    public Object addOuDep(OuDep ouDep) {
        //先判断部门编号
        if(StringUtils.isBlank(ouDep.getCode()))return RespUtil.successResp("500","部门编号不能为空",null);
        List<OuDep> allOuDepList = ouDepDao.selectAll();
        if(allOuDepList.size()>0){
            List<OuDep> ouDepListTemp = ouDepDao.select(new OuDep(ouDep.getCode()));
            if(ouDepListTemp.size()!=0)return RespUtil.successResp("500","部门编号已存在",null);
        }else {
            ouDep.setParentDepcode("top");//如果是首次添加部门则提前补充上级部门编号
        }
        //判断其它值
        if(StringUtils.isBlank(ouDep.getName()))return RespUtil.successResp("500","部门名称不能为空",null);
        if(null==ouDep.getFunctionalTypeid())return RespUtil.successResp("500","职能类型不能为空",null);
        if(StringUtils.isBlank(ouDep.getParentDepcode()))return RespUtil.successResp("500","上级部门编号不能为空",null);
        OuDep parentOuDep = null;
        if (allOuDepList.size()>0) {
            List<OuDep> parentDepListTemp = ouDepDao.select(new OuDep(ouDep.getParentDepcode()));
            if(parentDepListTemp.size()==0)return RespUtil.successResp("500","上级部门编号所在的部门不存在",null);
            parentOuDep = parentDepListTemp.get(0);
        }
        if(null==ouDep.getDepTypeid())return RespUtil.successResp("500","部门类型不能为空",null);
        if(StringUtils.isBlank(ouDep.getOrder())){
            //如果排序号不存在则1.用部门编号代替;2.随机数;
            //1.总公司
            if("ELEXTEC".equals(ouDep.getCode()))ouDep.setOrder("0");
            if(!"ELEXTEC".equals(ouDep.getCode()) && ouDep.getCode().indexOf("ELEX")!=-1)ouDep.setOrder(ouDep.getCode().substring(ouDep.getCode().length()-2,ouDep.getCode().length()));//截取后两位
            if(ouDep.getCode().indexOf("ELEX")==-1)ouDep.setOrder(ouDep.getCode());
        }
        //判断部门层级
        if(StringUtils.isBlank(ouDep.getLevel()))return RespUtil.successResp("500","部门层级不能为空",null);
        try {
            Integer curLevelNumber = Integer.parseInt(ouDep.getLevel());
            if(allOuDepList.size()>0){
                //有上级部门则当前部门层级必须大于上级部门层级数字
                Integer parentLevelNumber = Integer.parseInt(parentOuDep.getLevel());
                if(curLevelNumber.intValue()<=parentLevelNumber.intValue())return RespUtil.successResp("500","当前部门层级必须大于上级部门层级",null);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return RespUtil.successResp("500","部门层级必须为数字类型",null);
        }
        //添加部门信息
        ouDep.setId("dep_"+System.currentTimeMillis());
        ouDep.setState(Commons.DEP_ON);
        try {
            ouDepDao.insertSelective(ouDep);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("500","添加部门信息失败",null);
        }
        //刷新上级部门的子部门信息
        if(allOuDepList.size()>0){
            String oldParentSubdepartments = parentOuDep.getSubdepartments();
            String newParentSubdepartments = "";
            if(StringUtils.isBlank(oldParentSubdepartments)){
                newParentSubdepartments = ouDep.getCode();
            }else {
                newParentSubdepartments = oldParentSubdepartments+";"+ouDep.getCode();
            }
            parentOuDep.setSubdepartments(newParentSubdepartments);
            try {
                ouDepDao.updateByPrimaryKeySelective(parentOuDep);
            } catch (Exception e) {
                e.printStackTrace();//需要回滚
                ouDepDao.deleteByPrimaryKey(ouDep.getId());
                return RespUtil.successResp("500","更新上级部门的子部门信息时失败导致添加部门失败",null);
            }
        }
        return RespUtil.successResp("200","添加部门成功",null);
    }
}