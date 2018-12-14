package com.elex.oa.service.restructure_hrService.impl;

import com.elex.oa.dao.hr.IDeptDao;
import com.elex.oa.dao.hr.IPersonalInformationDao;
import com.elex.oa.dao.restructure_hr.IDeploginfoDao;
import com.elex.oa.dao.restructure_hr.IDepinfoDao;
import com.elex.oa.dao.restructure_hr.IPersonalinfoDao;
import com.elex.oa.dao.restructure_hr.IPersonalloginfoDao;
import com.elex.oa.entity.hr_entity.Dept;
import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.restructure_hrentity.Depinfo;
import com.elex.oa.entity.restructure_hrentity.Deploginfo;
import com.elex.oa.entity.restructure_hrentity.Personalinfo;
import com.elex.oa.entity.restructure_hrentity.Personalloginfo;
import com.elex.oa.service.restructure_hrService.IDepinfoService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2018\11\27 0027 17:04
 * @Version 1.0
 **/
@Service
public class DepinfoServiceImpl implements IDepinfoService {
    @Resource
    IDeptDao iDeptDao;
    @Resource
    IDepinfoDao iDepinfoDao;
    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;
    @Resource
    IPersonalinfoDao iPersonalinfoDao;
    @Resource
    IDeploginfoDao iDeploginfoDao;
    @Resource
    IPersonalloginfoDao iPersonalloginfoDao;

    @Override
    public Boolean changeTable() {
        Boolean valBoolean = true;
        List<Dept> deptList = iDeptDao.selectAllDept();
        for (Dept d:deptList
             ) {
            Depinfo temDepinfo = getDepinfoByDepcode(d.getDepcode());
            if(null!=temDepinfo){
                valBoolean = false;
                continue;
            }
            getDepcodeByAddDepInfo(getNewDepinfoByDept(d));
        }
        return valBoolean;
    }

    @Override
    public Map<String, Object> getDepTree() {
        Map<String,Object> respMap = new HashMap<>();
        List<Depinfo> depinfoList = iDepinfoDao.selectByEntity(new Depinfo(null, "top"));
        respMap.put("title",depinfoList.get(0).getDepname());
        respMap.put("code",depinfoList.get(0).getDepcode());
        respMap.put("ordercode",depinfoList.get(0).getOrdercode());
        respMap.put("expand",true);
        //获取children值
        respMap = getRespMapByParentcode(depinfoList.get(0).getDepcode(),respMap);
        return respMap;
    }

    @Override
    public Depinfo queryOneByDepcode(String depcode) {
        Depinfo depinfo = getDepinfoByDepcode(depcode);
        depinfo = getDepinfoDetailByDepinfo(depinfo);
        return depinfo;
    }

    @Override
    public List<Depinfo> queryDepartmentinfoList() {
        return iDepinfoDao.selectByEntity(new Depinfo(null,null ,"1"));
    }

    @Override
    public Boolean addOneDepartment(Depinfo depinfo, String transactorusername) {
        //先校验部门编号是否存在
        Depinfo depinfoTemp = iDepinfoDao.selectByDepcode(depinfo.getDepcode());
        if(depinfoTemp!=null)return false;
        //添加部门信息
        iDepinfoDao.insert(depinfo);
        //正职、副职、秘书的原部门信息修改、并添加相应的部门信息修改日志
        if(null!=depinfo.getPrincipaluserid() && StringUtils.isNotEmpty(depinfo.getPrincipaluserid())){
            removeOldPosition(depinfo.getPrincipaluserid(),transactorusername,depinfo.getDepname());
        }
        if(null!=depinfo.getDeputyuserid() && StringUtils.isNotEmpty(depinfo.getDeputyuserid())){
            removeOldPosition(depinfo.getDeputyuserid(),transactorusername,depinfo.getDepname());
        }
        if(null!=depinfo.getSecretaryuserid() && StringUtils.isNotEmpty(depinfo.getSecretaryuserid())){
            removeOldPosition(depinfo.getSecretaryuserid(),transactorusername,depinfo.getDepname());
        }
        return true;
    }

    //移除旧部门的相应的岗位：
    // 担任其它部门（旧部门）相应职位（正职、副职、秘书）的则先添加日志再移除相应的职位；
    //然后将相应的人事表中部门信息修改并添加相应的人事日志
    private void removeOldPosition(String employeenumber,String transactorusername,String newDepname){
        //获取修改前的信息=>oldDepcode "部门正职" "张三" "null"
        //获取oldDepcode
        Map<String, String> depcodeMap = iPersonalinfoDao.selectDepcodeByEmployeenumber(employeenumber);
        String depcode = depcodeMap.get("depcode");
        if(StringUtils.isEmpty(depcode))return;
        //获取oldDepinfo
        Depinfo oldDepinfo = getDepinfoByDepcode(depcode);
        if(null==oldDepinfo)return;
        //获取部门日志的四个参数+修改相应的信息
        Map<String, String> depLogMap = getDeploginfoByDepinfo(oldDepinfo, employeenumber);
        //添加部门日志
        addDepinfolog(depLogMap,transactorusername);
        //获取人事日志的四个参数+修改相应的信息
        Map<String, String> perLogMap = getPerloginfoByPerinfo(employeenumber, oldDepinfo.getDepname(), newDepname, "所属部门");
        //添加人事日志
        addPersonalinfolog(perLogMap,transactorusername);
        //修改相应的信息
        iPersonalinfoDao.updateByPrimaryKeySelective(new Personalinfo(employeenumber,depcode));//Null属性不跟新
    }

    //添加部门日志
    private void addDepinfolog(Map<String, String> logMap,String transactorusername){
        if(null==logMap)return;
        //自动生成ID
        Deploginfo deploginfo = new Deploginfo();
        deploginfo.setId("dep_log_"+System.currentTimeMillis());
        //添加部门日志的四个参数
        deploginfo.setDepcode(logMap.get("depcode"));
        deploginfo.setChangeinformation(logMap.get("changeinformation"));
        deploginfo.setBeforeinformation(logMap.get("beforeinformation"));
        deploginfo.setAfterinformation(logMap.get("afterinformation"));
        //添加其它信息
        deploginfo.setChangereason("业务需要");
        deploginfo.setChangedate(hrUtilsTemp.getDateStringByTimeMillis(System.currentTimeMillis()));
        deploginfo.setTransactoruserid(transactorusername);
        //添加部门日志
        iDeploginfoDao.insertSelective(deploginfo);//有选择的保存，Null属性不保存
    }

    //添加人事日志
    private void addPersonalinfolog(Map<String, String> logMap,String transactorusername){
        //自动生成ID
        Personalloginfo personalloginfo = new Personalloginfo();
        personalloginfo.setId("per_log_"+System.currentTimeMillis());
        //添加人事日志的四个参数
        personalloginfo.setEmployeenumber(logMap.get("employeenumber"));
        personalloginfo.setChangeinformation(logMap.get("changeinformation"));
        personalloginfo.setBeforeinformation(logMap.get("beforeinformation"));
        personalloginfo.setAfterinformation(logMap.get("afterinformation"));
        //添加其它信息
        personalloginfo.setChangereason("业务需要");
        personalloginfo.setChangedate(hrUtilsTemp.getDateStringByTimeMillis(System.currentTimeMillis()));
        personalloginfo.setTransactoruserid(transactorusername);
        //添加人事日志
        iPersonalloginfoDao.insertSelective(personalloginfo);//有选择的保存，Null属性不保存
    }

    //根据oldDepinfo获得部门信息修改日志的四个参数（仅仅适用于"部门正职"、"部门副职"、"部门秘书"）+ 修改相应的部门信息
    private Map<String,String> getDeploginfoByDepinfo(Depinfo depinfo,String employeenumber){
        String depcode = depinfo.getDepcode();
        String principaluserid = depinfo.getPrincipaluserid();
        String deputyuserid = depinfo.getDeputyuserid();
        String secretaryuserid = depinfo.getSecretaryuserid();
        Map<String,String> respMap = getDepLogInfoByPositionuserid(depcode, principaluserid, employeenumber,"部门正职");
        if(null==respMap){
            return null;
        }else {
            depinfo.setPrincipaluserid(null);
        }
        respMap = getDepLogInfoByPositionuserid(depcode, deputyuserid, employeenumber,"部门副职");
        if(null==respMap){
            return null;
        }else {
            depinfo.setDeputyuserid(null);
        }
        respMap = getDepLogInfoByPositionuserid(depcode, secretaryuserid, employeenumber,"部门秘书");
        if(null==respMap){
            return null;
        }else {
            depinfo.setSecretaryuserid(null);
        }
        //修改相应的部门信息
        iDepinfoDao.updateByPrimaryKey(depinfo);
        return respMap;
    }

    //获得人事信息修改日志的四个参数：employeenumber、changeinformation、beforeinformation、afterinformation
    private Map<String,String> getPerloginfoByPerinfo(String employeenumber,String beforeinformation,String afterinformation,String changeinformationName){
        Map<String,String> respMap = new HashMap<>();
        respMap.put("employeenumber",employeenumber);
        respMap.put("changeinformation",changeinformationName);
        respMap.put("beforeinformation",beforeinformation);
        respMap.put("afterinformation",afterinformation);
        return respMap;
    }

    //获得部门信息修改日志的四个参数：depcode、changeinformation、beforeinformation、afterinformation
    private Map<String,String> getDepLogInfoByPositionuserid(String depcode,String userid,String employeenumber,String changeinformationName){
        Map<String,String> respMap = null;
        if(null!=userid && userid==employeenumber){
            respMap.put("depcode",depcode);
            respMap.put("changeinformation",changeinformationName);
            respMap.put("beforeinformation",iPersonalinfoDao.selectUserByEmployeenumber(employeenumber).get("truename"));
            respMap.put("afterinformation",null);
        }
        return respMap;
    }

    //获得部门树的数据
    private Map<String, Object> getRespMapByParentcode(String parentcode,Map<String,Object> respMap){
        List<Depinfo> depinfoList = iDepinfoDao.selectByEntity(new Depinfo(null, parentcode));
        if(depinfoList==null)return respMap;
        List<Map<String, Object>> children = new ArrayList<>();
        for (Depinfo d:depinfoList
             ) {
            Map<String,Object> respMapTemp = new HashMap<>();
            respMapTemp.put("title",d.getDepname());
            respMapTemp.put("code",d.getDepcode());
            respMapTemp.put("ordercode",d.getOrdercode());
            respMap.put("expand",true);
            respMapTemp = getRespMapByParentcode(d.getDepcode(), respMapTemp);
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

    //添加部门信息
    private String getDepcodeByAddDepInfo(Depinfo depinfo){
        Depinfo temDepinfo = getDepinfoByDepcode(depinfo.getDepcode());
        if(null!=temDepinfo)return null;
        iDepinfoDao.insert(depinfo);
        return depinfo.getDepcode();
    }

    //根据部门编号获得部门信息（粗略的信息）
    private Depinfo getDepinfoByDepcode(String depcode){
        if(StringUtils.isEmpty(depcode))return null;
        List<Depinfo> depinfoList = iDepinfoDao.selectByEntity(new Depinfo(depcode));
        if(null == depinfoList || depinfoList.size()==0){
            return null;
        }else if(depinfoList.size()==1){
            Depinfo depinfo = depinfoList.get(0);
            return depinfo;
        }
        return null;
    }

    //根据原部门实例获得新部门实例（数据迁移用）
    private Depinfo getNewDepinfoByDept(Dept d) {
        Depinfo depinfo = new Depinfo();
        depinfo.setDepcode(d.getDepcode());
        depinfo.setCompanyname(d.getCompanyname());
        depinfo.setDepname(d.getDepname());
        depinfo.setFunctionaltypeid(new HrUtilsTemp().getDatacodeByHrsetid(d.getFunctionaltypeid()));
        depinfo.setDeptypeid(new HrUtilsTemp().getDatacodeByHrsetid(d.getDeptypeid()));
        depinfo.setParent_depcode(new HrUtilsTemp().getDepcodeByDepid(d.getParentdepid()));
        depinfo.setPrincipaluserid(getEmployeenumberByUserid(d.getPrincipaluserid()));
        depinfo.setDeputyuserid(getEmployeenumberByUserid(d.getDeputyuserid()));
        depinfo.setSecretaryuserid(getEmployeenumberByUserid(d.getSecretaryuserid()));
        depinfo.setDutydescription(d.getDutydescription());
        depinfo.setDepdescription(d.getDepdescription());
        depinfo.setState(Integer.toString(d.getState()));
        depinfo.setOrdercode(Integer.toString(d.getOrdercode()));
        /*层级手动添加(默认4)*/
        depinfo.setNode_level("4");
        return depinfo;
    }

    //根据Userid获得Employeenumber(已过时)
    private String getEmployeenumberByUserid(Integer userid){
        if(null==userid)return null;
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(userid);
        if(null==personalInformation)return null;
        return personalInformation.getEmployeenumber();
    }

    //根据部门（粗略的信息）获得详细的部门信息
    private Depinfo getDepinfoDetailByDepinfo(Depinfo depinfo){
        //获取职能类型
          depinfo.setFunctionaltype(hrUtilsTemp.getDatavalueByDatacode(depinfo.getFunctionaltypeid()));
        //获取部门类型
          depinfo.setDeptype(hrUtilsTemp.getDatavalueByDatacode(depinfo.getDeptypeid()));
        //获取上级部门
          depinfo.setParentdep(iDepinfoDao.selectByDepcode(depinfo.getParent_depcode()));
        //获取部门正职
          depinfo.setPrincipaluser(iPersonalinfoDao.selectPersonalinfoByEmployeenumber(depinfo.getPrincipaluserid()));
        //获取部门副职
          depinfo.setDeputyuser(iPersonalinfoDao.selectPersonalinfoByEmployeenumber(depinfo.getDeputyuserid()));
        //获取部门秘书
          depinfo.setSecretaryuser(iPersonalinfoDao.selectPersonalinfoByEmployeenumber(depinfo.getSecretaryuserid()));
        return depinfo;
    }
}