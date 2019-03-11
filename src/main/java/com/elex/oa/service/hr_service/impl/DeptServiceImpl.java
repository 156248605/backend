package com.elex.oa.service.hr_service.impl;

import com.alibaba.fastjson.JSONObject;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.hr.*;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.department.Dept;
import com.elex.oa.entity.hr_entity.department.DeptLog;
import com.elex.oa.entity.hr_entity.department.DeptTree;
import com.elex.oa.entity.hr_entity.hr_set.HRset;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.entity.hr_entity.personalinformation.User;
import com.elex.oa.entity.hr_entity.post.TitleAndCode;
import com.elex.oa.service.hr_service.IDeptService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.hr_util.PageHelper;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:部门信息（业务层）
 * @Date:Created in  13:33 2018\3\16 0016
 * @Modify By:
 */
@Service
public class DeptServiceImpl implements IDeptService {

    @Resource
    private IDeptDao iDeptDao;
    @Resource
    private IPersonalInformationDao iPersonalInformationDao;
    @Resource
    private IUserDao iUserDao;
    @Resource
    private IHRsetDao ihRsetDao;
    @Resource
    HrUtils hrUtils;
    @Resource
    IDeptLogDao iDeptLogDao;
    @Resource
    IPostDao iPostDao;

    private static Logger logger = LoggerFactory.getLogger(DeptServiceImpl.class);

    @Override
    public List<Dept> queryOneDepByDepname(String depname) {
        return iDeptDao.selectDeptByDeptname(depname);
    }

    //根据部门名称模糊查询获得部门
    @Override
    public List<Dept> queryOneDepByLikeDepname(String depname) {
        return iDeptDao.selectDeptByLikeDeptname(depname);
    }

    @Override
    public Dept queryOneByDepcode(String depcode) {
        Dept dept = iDeptDao.selectDeptByDeptcode(depcode);
        return getDetailDeptByDept(dept);
    }

    @Override
    public List<Dept> queryDeptsByDepcode(String depcode) {
        return iDeptDao.selectDeptByDeptcode2(depcode);
    }

    @Override
    public Dept queryOneDepByDepid(Integer id) {
        return iDeptDao.selectDeptByDepid(id);
    }

    @Override
    public Dept queryOneByDepid(Integer depid) {
        Dept dept = iDeptDao.selectDeptByDepid(depid);
        dept.setFunctionaltype(hrUtils.getDatavalueByHrsetid(dept.getFunctionaltypeid()));
        dept.setDeptype(hrUtils.getDatacodeByHrsetid(dept.getDeptypeid()));
        dept.setPostList(hrUtils.getStringToListString(dept.getPost_list(),","));
        return dept;
    }

    @Override
    public List<Dept> queryAllDepts() {
        return iDeptDao.selectAllDept();
    }

    @Override
    public List<Dept> queryByParentId(Integer parentid) {
        return iDeptDao.selectByParentId(parentid);
    }

    @Override
    public Integer addOne(Dept dept) {
        return iDeptDao.insertOne(dept);
    }

    @Override
    public void modifyOne(Dept dept) {
        iDeptDao.updateOne(dept);
    }

    @Override
    public void removeOne(Integer id) {
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid(id);
        PersonalInformation personalInformation = new PersonalInformation();
        //先清空人员的相应部门信息
        for (PersonalInformation personalinformation:personalInformationList
                ) {
            personalInformation.setId(personalinformation.getId());
            personalInformation.setDepid(personalinformation.getDepid());
            iPersonalInformationDao.updateDeptinformation(personalInformation);
        }
        //再删除相应的部门
        iDeptDao.deleteOne(id);
    }

    @Override
    public void modifyOne(Integer userid) {
        Dept dept1 = new Dept();
        dept1.setPrincipaluserid(userid);
        Dept dept2 = new Dept();
        dept2.setDeputyuserid(userid);
        Dept dept3 = new Dept();
        dept3.setSecretaryuserid(userid);
        List<Dept> depts1 = iDeptDao.selectDeptsByDept(dept1);
        List<Dept> depts2 = iDeptDao.selectDeptsByDept(dept2);
        List<Dept> depts3 = iDeptDao.selectDeptsByDept(dept3);
        if(!depts1.isEmpty()){
            Dept dept = new Dept();
            dept.setPrincipaluserid(userid);
            dept.setId(depts1.get(0).getId());
            iDeptDao.updateByDeleteUser(dept);
        }else if(!depts2.isEmpty()){
            Dept dept = new Dept();
            dept.setDeputyuserid(userid);
            dept.setId(depts1.get(0).getId());
            iDeptDao.updateByDeleteUser(dept);
        }else if(!depts3.isEmpty()){
            Dept dept = new Dept();
            dept.setSecretaryuserid(userid);
            dept.setId(depts1.get(0).getId());
            iDeptDao.updateByDeleteUser(dept);
        }
    }

    @Override
    public void modifyByDeptidAndOtherinformation(Dept dept){
        iDeptDao.updateByDeleteUser(dept);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID获得部门名称、人数、总人数
     *@Date: 15:31 2018\6\1 0001
     */
    @Override
    public HRManageCard getParamMap1(Integer depid) {
        HRManageCard hrManageCard = new HRManageCard();
        //获得部门名称
        Dept dept;
        if (depid!=null) {
            dept = iDeptDao.selectDeptByDepid(depid);
        } else {
            dept = iDeptDao.selectDeptByDeptname(Commons.COMPANYNAME_GROUP).get(0);
        }
        hrManageCard.setDepname(dept.getDepname());
        //获得部门ID
        hrManageCard.setDeptid(dept.getId());
        //获得上级部门ID
        hrManageCard.setParentid(dept.getParentdepid());
        //获得部门的人员
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid(dept.getId());
        List<Map> users = new ArrayList<>();
        for (PersonalInformation per:personalInformationList
                ) {
            HashMap<String, Object> map = new HashMap<>();
            User user = iUserDao.selectById(per.getUserid());
            map.put("id",user.getId());
            map.put("truename",user.getTruename());
            map.put("deptname",iDeptDao.selectDeptByDepid(per.getDepid()).getDepname());
            map.put("mobilephone",per.getMobilephone());
            users.add(map);
        }
        hrManageCard.setUsers(users);
        //获得子部门
        List<Dept> depts = iDeptDao.selectByParentId(dept.getId());
        hrManageCard.setChildDepts(depts);
        return hrManageCard;
    }

    /**
     *@Author:ShiYun;
     *@Description:在职的人事管理看板
     *@Date: 11:53 2018\6\28 0028
     */
    @Override
    public Object getHRManageCard(String companyname,String sdate,String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505",Commons.RESP_FAIL_CHOOSEDATE,null);
            }
            sdate = twoDate.get(Commons.PARAM_SDATE);
            edate = twoDate.get(Commons.PARAM_EDATE);
            HashMap<String, Object> paramMap = new HashMap<>();
            List<HRManageCard> hrManageCardList = new ArrayList<>();
            List<Dept> depts = iDeptDao.selectDeptByDeptypeValueLike("公司");

            //获得总人数(edate时间点的在职总人数)
            Integer num;
            Resp resp2 = (Resp) this.getHRManageCard2(companyname,5, 1, sdate, edate);
            if(resp2.getBody()==null)return resp2;
            PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp2.getBody();
            num = pageHelper2.getTotal();
            paramMap.put("allNum",num);

            //获得入职总人数(edate时间点的入职总人数)
            Resp resp3 = (Resp) this.getHRManageCard3(companyname,5, 1, sdate, edate);
            if(resp3.getBody()==null)return resp3;
            PageHelper<PersonalInformation> pageHelper3 = (PageHelper<PersonalInformation>)resp3.getBody();
            paramMap.put("intoNum",pageHelper3.getTotal());

            //获得离职总人数(edate时间点的离职总人数)
            Resp resp4 = (Resp) this.getHRManageCard4(companyname,5, 1, sdate, edate);
            if(resp4.getBody()==null)return resp4;
            PageHelper<PersonalInformation> pageHelper4 = (PageHelper<PersonalInformation>)resp4.getBody();
            paramMap.put("outNum",pageHelper4.getTotal());

            for (Dept dept:depts
                    ) {
                HRManageCard hrManageCard = new HRManageCard();

                //获得部门名称
                hrManageCard.setDeptid(dept.getId());
                hrManageCard.setDepname(dept.getDepname());

                //获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
                Integer ratio;
                Resp resp5 = (Resp) this.getHRManageCard5(5, 1, dept.getId(), sdate, edate);
                if(resp5.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp5.getBody();
                    ratio = pageHelper.getTotal();
                    hrManageCard.setNum(ratio);
                }else {
                    return resp5;
                }

                //人数占比
                Double db = ratio.doubleValue()/num.doubleValue()*100;
                BigDecimal bg = BigDecimal.valueOf(db).setScale(2, RoundingMode.UP);
                hrManageCard.setRatio(bg.doubleValue() + "%");

                //获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
                Resp resp6 = (Resp) this.getHRManageCard6(5, 1, dept.getId(), sdate, edate);
                if(resp6.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp6.getBody();
                    hrManageCard.setIntoNum(pageHelper.getTotal());
                }else {
                    return resp6;
                }

                //获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
                Resp resp7 = (Resp) this.getHRManageCard7(5, 1, dept.getId(), sdate, edate);
                if(resp7.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper = (PageHelper<PersonalInformation>)resp7.getBody();
                    hrManageCard.setOutNum(pageHelper.getTotal());
                }else {
                    return resp7;
                }

                //获得部门相应的人员(edate时间点的在职人员)
                hrManageCardList.add(hrManageCard);
            }
            paramMap.put("HRManageCards",hrManageCardList);
            return RespUtil.response("205","返回成功！",paramMap);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405",Commons.RESP_FAIL,null);
        }
    }

    //计算时间初期数和时间末期数的工具
    private Map<String,String> getTwoDate(String sdate,String edate){
        HashMap<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String curDate = sdf.format(date);
        Boolean d1 = StringUtils.isBlank(sdate);
        Boolean d2 = StringUtils.isBlank(edate);
        //1.sdate=null,  edate=null=>作为当前时间
        if(d1 && d2){
            sdate = edate =curDate;
        }
        //2.sdate!=null, edate=null=>限：sdate<=当前时间
        //                           结：edate=sdate
        if(!d1 && d2){
            if(sdate.compareTo(curDate)>0)return null;
            edate=sdate;
        }
        //3.sdate=null, edate!=null=>限：edate<=当前时间
        //                           结：sdate=edate
        if(d1 && !d2){
            if(edate.compareTo(curDate)>0)return null;
            sdate = edate;
        }
        //4.sdate!=null,edate!=null=>限：sdate<=edate<=当前时间
        //                           结：sdate,edate
        if(!d1 && !d2 && (sdate.compareTo(edate)>0 || edate.compareTo(curDate)>0))return null;
        map.put(Commons.PARAM_SDATE,sdate);
        map.put(Commons.PARAM_EDATE,edate);
        return map;
    }

    /**
     *@Author:ShiYun;
     *@Description:获得总人数(edate时间点的在职总人数)
     *@Date: 10:13 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard2(String  companyname,Integer rows, Integer page, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505",Commons.RESP_FAIL_CHOOSEDATE,null);
            }
            edate = twoDate.get(Commons.PARAM_EDATE);
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll2(null,edate);//时间节点edate前的入职人员
            List<PersonalInformation> personalInformationList2 = iPersonalInformationDao.selectAll3(null,edate);//时间节点edate前的离职人员
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:personalInformationList1) {
                Integer depidTemp = per.getDepid();
                Dept deptTemp = null;
                if (depidTemp!=null) {
                    deptTemp = iDeptDao.selectDeptByDepid(depidTemp);
                }
                Boolean isExistDepId = depidTemp!=null && deptTemp!=null && deptTemp.getCompanyname().equals(companyname);
                if(
                    !personalInformationList2.contains(per) && (
                        companyname.trim().equals(Commons.COMPANYNAME_GROUP)
                                ||
                        isExistDepId
                    )
                ){
                    personalInformationList.add(per);
                }
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page,rows,personalInformationList);
            return RespUtil.response("205",Commons.RESP_SUCCESS,pageHelper);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405",Commons.RESP_FAIL,null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:获得入职总人数(edate时间点的入职总人数)
     *@Date: 10:13 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard3(String companyname,Integer rows, Integer page, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505",Commons.RESP_FAIL,null);
            }
            sdate = twoDate.get(Commons.PARAM_SDATE);
            edate = twoDate.get(Commons.PARAM_EDATE);
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll2(sdate, edate);
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:personalInformationList1
                 ) {
                Integer depidTemp = per.getDepid();
                Dept deptTemp = null;
                if (depidTemp!=null) {
                    deptTemp = iDeptDao.selectDeptByDepid(depidTemp);
                }
                Boolean isExistDepId = depidTemp!=null && deptTemp!=null && deptTemp.getCompanyname().equals(companyname);
                if(
                    companyname.equals(Commons.COMPANYNAME_GROUP)
                            ||isExistDepId//没有部门的员工(或者所在部门不存在，实际上这种情况是绝对不存在的)暂时不纳入人事看板的统计
                ){
                    personalInformationList.add(per);
                }
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.response("205",Commons.RESP_SUCCESS,pageHelper);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405",Commons.RESP_FAIL,null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:获得离职总人数(edate时间点的离职总人数)
     *@Date: 10:14 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard4(String companyname,Integer rows, Integer page, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505",Commons.RESP_FAIL_CHOOSEDATE,null);
            }
            sdate = twoDate.get(Commons.PARAM_SDATE);
            edate = twoDate.get(Commons.PARAM_EDATE);
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll3(sdate, edate);
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:personalInformationList1
                    ) {
                Integer depidTemp = per.getDepid();
                Dept deptTemp = null;
                if (depidTemp!=null) {
                    deptTemp = iDeptDao.selectDeptByDepid(depidTemp);
                }
                Boolean isExistDepId = depidTemp!=null && deptTemp!=null && deptTemp.getCompanyname().equals(companyname);
                if(
                    companyname.trim().equals(Commons.COMPANYNAME_GROUP)
                    ||
                            isExistDepId//没有部门的员工(或者所在部门不存在，实际上这种情况是绝对不存在的)暂时不纳入人事看板的统计
                ){
                    personalInformationList.add(per);
                }
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.response("205",Commons.RESP_SUCCESS,pageHelper);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405",Commons.RESP_FAIL,null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门在职人数(edate时间点的在职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 10:14 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard5(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505",Commons.RESP_FAIL_CHOOSEDATE,null);
            }
            edate = twoDate.get(Commons.PARAM_EDATE);
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectByDepid2(depid,null,edate);
            List<PersonalInformation> personalInformationList2 = iPersonalInformationDao.selectByDepid3(depid,null,edate);
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            if (!personalInformationList2.isEmpty()) {
                for (PersonalInformation per:personalInformationList1
                        ) {
                    if(!personalInformationList2.contains(per)){
                        personalInformationList.add(per);
                    }
                }
            } else {
                personalInformationList = personalInformationList1;
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page,rows,personalInformationList);
            return RespUtil.response("205",Commons.RESP_SUCCESS,pageHelper);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405",Commons.RESP_FAIL,null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 10:15 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard6(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid2(depid, sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.response("205",Commons.RESP_SUCCESS,pageHelper);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405",Commons.RESP_FAIL,null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
     *@Date: 10:15 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard7(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.response("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid3(depid, sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.response("205","提交成功！",pageHelper);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("405","系统正在忙，请稍后再试！",null);
        }
    }

    @Override
    public String queryByTruename(String truename) {
        User user;
        if (StringUtils.isNotBlank(truename)) {
            user = iUserDao.selectByTruename(truename);
        }else {
            return "99";
        }
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(user.getId());
        Integer depid;
        if(null==personalInformation || null == personalInformation.getDepid()){
            return "99";
        }else {
            depid = personalInformation.getDepid();
        }
        Dept dept = iDeptDao.selectDeptByDepid(depid);
        if(null==dept){
            return "99";
        }
        String depcode = dept.getDepcode();
        boolean marker = NumberUtils.isNumber(String.valueOf(depcode.charAt(0)));
        if(marker) {
            return depcode.substring(0,2);
        } else {
            int length = depcode.length();
            return depcode.substring(length-2, length);
        }
    }

    @Override
    public List<Dept> queryAllCompany1and2() {
        return iDeptDao.selectDeptByLikeDeptname("公司");
    }

    @Override
    public List<Dept> queryByCompanyname(String companyname) {
        return iDeptDao.selectDeptByCompanyname(companyname);
    }

    @Override
    public List<Dept> queryDepartmentsRemoveChilren(Integer depid) {
        List<Dept> depts = iDeptDao.selectAllDept();
        List<Dept> depts1 = new ArrayList<>();
        for (Dept p:depts
                ) {
            if(!this.isChildPoint(depid,p.getId()) && depid!=p.getId()){
                depts1.add(p);
            }
        }
        return depts1;
    }

    @Override
    public Boolean isChildPoint(Integer parentdepid, Integer childdepid) {
        Integer pid = childdepid;
        if(childdepid==parentdepid){//自己不能作为自己的上级
            return false;
        }
        while (pid!=null){
            if(pid==parentdepid){//是自己的上级返回true
                return true;
            }else {
                pid = iDeptDao.selectDeptByDepid(pid).getParentdepid();
            }
        }
        return false;//上级为null时跳出循环（到顶点），说明不是自己的上级
    }

    @Override
    public String queryCompanynameByUseridOrTruename(Integer userid, String truename){
        Dept dept = null;
        if(null==userid){
            if(StringUtils.isBlank(truename))return null;
            dept = iDeptDao.selectCompanynameAndDepnameByTruename(truename);
        }else {
            dept = iDeptDao.selectCompanynameAndDepnameByUserid(userid);
        }
        if(null==dept || StringUtils.isBlank(dept.getDepname()))return null;
        return dept.getCompanyname() + "--" +dept.getDepname();
    }

    @Override
    public Map<String,String> updateOneDepartment(Dept dept, String transactorusername) {
        Map<String,String> respMap = new HashMap<>();
        //获取旧部门信息
        Dept oldDept = iDeptDao.selectDeptByDepid(dept.getId());
        oldDept = getDetailDeptByDept(oldDept);
        //获取新部门信息
        Dept newDept = getDetailDeptByDept(dept);
        //比较新旧部门信息是否有修改并添加部门日志信息（返回布尔值）
        respMap = getRespMapByOlddeptAndNewdept(oldDept, newDept, transactorusername, respMap);
        if(respMap.size()!=0)return respMap;
        //修改部门信息
        iDeptDao.updateOne(newDept);
        return respMap;
    }

    @Override
    public Object addOneDepartment(Dept dept, String transactorusername) {
        //先校验部门名称是否存在
        if(StringUtils.isBlank(dept.getDepcode()))return RespUtil.response("500","部门编号不能为空！",null);
        Dept deptTemp = iDeptDao.selectDeptByDeptcode(dept.getDepcode());
        if(null!=deptTemp)return RespUtil.response("500","部门编号已存在，请重新输入部门编号！",null);
        //添加新部门
        dept.setPost_list(hrUtils.getArrayToString(dept.getPostList(),","));
        //判断是否为顶点部门
        if(null==dept.getParentdepid()){
            List<Map<String, Object>> allDepidAndDepnameByDepOn = iDeptDao.getAllDepidAndDepnameByDepON();
            if(!allDepidAndDepnameByDepOn.isEmpty())return RespUtil.response("500","非顶级部门必须选择上级部门！",null);
        }
        iDeptDao.insertOne(dept);
        return RespUtil.response("200","提交成功！",dept);
    }

    @Override
    public Map<String,Object> queryPostListByDepidButIsNotNull(Integer depid) {
        Map<String,Object> respMap = new HashMap<>();
        Dept dept = null;
        //再获得部门主管姓名、工号
        if (null!=depid) {
            dept = iDeptDao.selectDeptByDepid(depid);
            if(null!=dept){
                Integer principaluserid = dept.getPrincipaluserid();
                if(null!=principaluserid){
                    User user = iUserDao.selectById(principaluserid);
                    if(null!=user){
                        respMap.put("principaltruename",user.getTruename());
                        respMap.put("principalemployeenumber",user.getEmployeenumber());
                    }
                }
            }
        }
        //先获得岗位集合
        if(null==depid || null==dept || StringUtils.isBlank(dept.getPost_list())){
            List<Map<String, Object>> mapList = iPostDao.selectAllPostOfIdPostcodePostnameStateON();
            respMap.put("postList",mapList);
        }else {
            List<Map<String, Object>> mapList = iPostDao.selectByPostlist(hrUtils.getStringToListString(dept.getPost_list(), ","));
            respMap.put("postList",mapList);
        }
        return respMap;
    }

    @Override
    public List<Map<String,Object>> getAllDepidAndDepnameByDepON() {
        return iDeptDao.getAllDepidAndDepnameByDepON();
    }

    @Override
    public String getPrincipalTruenameByDepid(Integer depid) {
        return iDeptDao.getPrincipalTruenameByDepid(depid);
    }

    @Override
    public List<String> getAllCompanyNames() {
        return iDeptDao.getAllCompanyNamesByDepON();
    }

    @Override
    public Object getAllDepidAndDepnameByRemoveCompany() {
        return iDeptDao.getAllDepidAndDepnameByRemoveCompany();
    }

    @Override
    public DeptTree submitSortdata(String sortdata, String title, String deptTreeStr) {
        List<TitleAndCode> titleAndCodeList = JSONObject.parseArray(sortdata, TitleAndCode.class);//获得同级数据展示
        //更新排序码
        for (TitleAndCode titleAndCode:titleAndCodeList
             ) {
            Integer depId = iDeptDao.selectDeptByDeptname(titleAndCode.getTitle()).get(0).getId();
            iDeptDao.updateOne(new Dept(depId,titleAndCode.getCode()));
        }
        //返回部门树结构数据
        Dept topDept = iDeptDao.selectByParentId(null).get(0);
        DeptTree deptTree = new DeptTree(topDept.getDepname(),topDept.getDepname(),topDept.getDepcode(),topDept.getId(),true);
        //获得子部门信息
        getSubDeptListByDeptTree(deptTree);
        return deptTree;
    }

    //根据父节点获得子节点
    private DeptTree getSubDeptListByDeptTree(DeptTree deptTree) {
        List<Dept> deptList = iDeptDao.selectByParentId(deptTree.getId());
        if(deptList.isEmpty())return deptTree;
        List<DeptTree> children = new ArrayList<>();
        for (Dept deptTemp:deptList
             ) {
            DeptTree deptTreeTemp = new DeptTree(deptTemp.getDepname(), deptTemp.getDepname(), deptTemp.getDepcode(), deptTemp.getId(), true);
            deptTreeTemp = getSubDeptListByDeptTree(deptTreeTemp);
            children.add(deptTreeTemp);
        }
        deptTree.setChildren(children);
        return deptTree;
    }

    //比较新旧部门信息是否有修改并添加部门日志信息（返回布尔值）
    private Map<String, String> getRespMapByOlddeptAndNewdept(Dept oldDept, Dept newDept, String transactorusername, Map<String, String> respMap){
        Boolean isUpdate1 = false;
        Boolean respBoolean = false;
        Integer depid = oldDept.getId();
        //判断部门编号（首先判断是否相等，如果不相等还要判断是否可用）
        if(!newDept.getDepcode().equals(oldDept.getDepcode())){
            Boolean isExist = getaBooleanByDepcode(newDept.getDepcode());
            if(isExist==null){
                respMap.put(newDept.getDepcode(),"部门编号不能为空！");
                return respMap;
            }
            if(isExist && !newDept.getDepcode().equals(oldDept.getDepcode())){
                respMap.put(newDept.getDepcode(),"部门编号已存在！");
                return respMap;
            }
            isUpdate1 = getaBooleanByTwoString(depid,oldDept.getDepcode(),newDept.getDepcode(),transactorusername,"部门编号");
        }
        //判断部门名称并添加部门日志
        Boolean isUpdate2 = getaBooleanByTwoString(depid,oldDept.getDepname(),newDept.getDepname(),transactorusername,"部门名称");
        //判断部门类型并添加部门日志
        Boolean isUpdate3 = getaBooleanByTwoString(depid,oldDept.getDeptype(),newDept.getDeptype(),transactorusername,"部门类型");
        //判断职能类型并添加部门日志
        Boolean isUpdate4 = getaBooleanByTwoString(depid,oldDept.getFunctionaltype(),newDept.getFunctionaltype(),transactorusername,"职能类型");
        //判断上级部门并添加部门日志
        Boolean isUpdate5 = getaBooleanByTwoString(depid,getStringOfDepnameAndDepcode(oldDept.getParentdep()),getStringOfDepnameAndDepcode(newDept.getParentdep()),transactorusername,"上级部门");
        //判断公司名称并部门日志
        Boolean isUpdate6 = getaBooleanByTwoString(depid,oldDept.getCompanyname(),newDept.getCompanyname(),transactorusername,"公司名称");
        //判断部门正职并添加部门日志
        Boolean isUpdate7 = getaBooleanByTwoString(depid,getStringOfTruenameAndEmployeenumber(oldDept.getPrincipaluser()),getStringOfTruenameAndEmployeenumber(newDept.getPrincipaluser()),transactorusername,"部门正职");
        //判断部门副职并添加部门日志
        Boolean isUpdate8 = getaBooleanByTwoString(depid,getStringOfTruenameAndEmployeenumber(oldDept.getDeputyuser()),getStringOfTruenameAndEmployeenumber(newDept.getDeputyuser()),transactorusername,"部门副职");
        //判断部门秘书并添加部门日志
        Boolean isUpdate9 = getaBooleanByTwoString(depid,getStringOfTruenameAndEmployeenumber(oldDept.getSecretaryuser()),getStringOfTruenameAndEmployeenumber(newDept.getSecretaryuser()),transactorusername,"部门秘书");
        //判断部门职责并添加部门日志
        Boolean isUpdate10 = getaBooleanByTwoString(depid,oldDept.getDutydescription(),newDept.getDutydescription(),transactorusername,"部门职责");
        //判断部门概述并添加部门日志
        Boolean isUpdate11 = getaBooleanByTwoString(depid,oldDept.getDepdescription(),newDept.getDepdescription(),transactorusername,"部门概述");
        //判断部门所含岗位并添加部门日志
        Boolean isUpdate12 = getaBooleanByTwoString(depid,oldDept.getPost_list(),newDept.getPost_list(),transactorusername,"部门所含岗位");
        if(isUpdate1 || isUpdate2 || isUpdate3 || isUpdate4 || isUpdate5 || isUpdate6 || isUpdate7 || isUpdate8 || isUpdate9 || isUpdate10 || isUpdate11 || isUpdate12 )respBoolean = true;
        if(respBoolean){
            return respMap;
        }else {
            respMap.put(newDept.getDepcode(),"没有需要修改的部门信息！");
            return respMap;
        }
    }

    //根据Dept获得部门名称+部门编号的字符串拼接
    private String getStringOfDepnameAndDepcode(Dept dept){
        if(null==dept)return null;
        return dept.getDepname()+"--"+dept.getId();
    }

    //根据User获得姓名+员工号的字符串拼接
    private String getStringOfTruenameAndEmployeenumber(User user){
        if(null==user)return null;
        return user.getTruename()+"--"+user.getEmployeenumber();
    }

    //判断部门编号是否存在
    private Boolean getaBooleanByDepcode(String depcode){
        if(StringUtils.isBlank(depcode))return false;
        Dept dept = iDeptDao.selectDeptByDeptcodeIgnoreState(depcode);
        return null!=dept;
    }

    //判断相应的两个字段是否相同并添加部门日志
    private Boolean getaBooleanByTwoString(Integer depid,String beforeinformation,String afterinformation,String transactorusername,String changeinformationName){
        if(StringUtils.isBlank(afterinformation))return false;
        if(afterinformation.equals(beforeinformation))return false;
        DeptLog deptLog = new DeptLog();
        deptLog.setDeptid(depid);
        deptLog.setChangeinformation(changeinformationName);
        deptLog.setBeforeinformation(beforeinformation);
        deptLog.setAfterinformation(afterinformation);
        deptLog.setChangereason("业务需要");
        deptLog.setChangedate(hrUtils.getDateStringByTimeMillis(System.currentTimeMillis()));
        deptLog.setTransactoruserid(getUseridByUsername(transactorusername));
        iDeptLogDao.insertOne(deptLog);
        return true;
    }

    //根据登录ID获得账号ID
    private Integer getUseridByUsername(String username){
        if(StringUtils.isBlank(username))return null;
        User user = iUserDao.selectByUsername(username);
        if(null==user)return null;
        return user.getId();
    }

    //根据部门粗略信息获取部门详细信息
    private Dept getDetailDeptByDept(Dept dept){
        if(null==dept)return null;
        //获取部门类型信息
        dept.setDeptype(getHRsetValueByHRsetID(dept.getDeptypeid()));
        //获取职能类型
        dept.setFunctionaltype(getHRsetValueByHRsetID(dept.getFunctionaltypeid()));
        //获取上级部门粗略信息
        dept.setParentdep(iDeptDao.selectDeptByDepid(dept.getParentdepid()));
        //获取部门正职粗略信息
        dept.setPrincipaluser(iUserDao.selectById(dept.getPrincipaluserid()));
        //获取部门副职粗略信息
        dept.setDeputyuser(iUserDao.selectById(dept.getDeputyuserid()));
        //获取部门秘书粗略信息
        dept.setSecretaryuser(iUserDao.selectById(dept.getSecretaryuserid()));
        //获取所含岗位信息
        if(StringUtils.isBlank(dept.getPost_list())){
            dept.setPost_list(hrUtils.getArrayToString(dept.getPostList(),","));
        }
        if(null==dept.getPostList()){
            dept.setPostList(hrUtils.getStringToListString(dept.getPost_list(),","));
        }
        return dept;
    }

    //根据HRset的id获取HRset字段
    private String getHRsetValueByHRsetID(Integer id){
        if(null==id)return null;
        HRset hRset = ihRsetDao.selectById(id);
        if(null==hRset)return null;
        return hRset.getDatavalue();
    }
}
