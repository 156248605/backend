package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.*;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.entity.hr_entity.personalinformation.PersonalInformation;
import com.elex.oa.service.hr_service.IDeptService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.hr_util.PageHelper;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
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

    /**
     *@Author:ShiYun;
     *@Description:根据部门名称获得部门
     *@Date: 15:29 2018\6\1 0001
     */
    @Override
    public List<Dept> queryOneDepByDepname(String depname) {
        return iDeptDao.selectDeptByDeptname(depname);
    }

    //根据部门名称模糊查询获得部门
    @Override
    public List<Dept> queryOneDepByLikeDepname(String depname) {
        return iDeptDao.selectDeptByLikeDeptname(depname);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门code查询部门信息
     *@Date: 10:20 2018\7\16 0016
     */
    @Override
    public Dept queryOneByDepcode(String depcode) {
        Dept dept = iDeptDao.selectDeptByDeptcode(depcode);
        return getDetailDeptByDept(dept);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门code模糊查询部门信息
     *@Date: 17:08 2018\8\14 0014
     */
    @Override
    public List<Dept> queryDeptsByDepcode(String depcode) {
        List<Dept> depts = iDeptDao.selectDeptByDeptcode2(depcode);
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID获得部门
     *@Date: 15:30 2018\6\1 0001
     */
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

    /**
     *@Author:ShiYun;
     *@Description:获得所有部门
     *@Date: 15:30 2018\6\1 0001
     */
    @Override
    public List<Dept> queryAllDepts() {
        List<Dept> depts = iDeptDao.selectAllDept();
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据上级部门ID查询部门信息
     *@Date: 10:52 2018\4\16 0016
     */
    @Override
    public List<Dept> queryByParentId(Integer parentid) {
        List<Dept> depts = iDeptDao.selectByParentId(parentid);
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加部门信息并返回主键
     *@Date: 11:00 2018\4\23 0023
     */
    @Override
    public Integer addOne(Dept dept) {
        Integer integer = iDeptDao.insertOne(dept);
        return dept.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:修改部门信息
     *@Date: 9:58 2018\5\2 0002
     */
    @Override
    public void modifyOne(Dept dept) {
        iDeptDao.updateOne(dept);
    }

    /**
     *@Author:ShiYun;
     *@Description:先要将该部门下的所有员工的部门信息改为无部门，再根据部门ID删除部门信息
     *@Date: 14:07 2018\5\2 0002
     */
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

    /**
     *@Author:ShiYun;
     *@Description:删除用户时修改部门信息
     *@Date: 15:25 2018\5\10 0010
     */
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
        if(depts1.size()>0){
            Dept dept = new Dept();
            dept.setPrincipaluserid(userid);
            dept.setId(depts1.get(0).getId());
            iDeptDao.updateByDeleteUser(dept);
        }else if(depts2.size()>0){
            Dept dept = new Dept();
            dept.setDeputyuserid(userid);
            dept.setId(depts1.get(0).getId());
            iDeptDao.updateByDeleteUser(dept);
        }else if(depts3.size()>0){
            Dept dept = new Dept();
            dept.setSecretaryuserid(userid);
            dept.setId(depts1.get(0).getId());
            iDeptDao.updateByDeleteUser(dept);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:修改部门时将在其他部门的信息休息掉
     *@Date: 10:33 2018\6\20 0020
     */
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
            dept = iDeptDao.selectDeptByDeptname("江苏博智软件科技股份有限公司").get(0);
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            HashMap<String, Object> paramMap = new HashMap<>();
            List<HRManageCard> hrManageCardList = new ArrayList<>();
            List<Dept> depts1 = iDeptDao.selectAllDept();
            List<Dept> depts = new ArrayList<>();
            for (Dept d:depts1
                 ) {
                if(companyname.equals("江苏博智软件科技股份有限公司") || d.getCompanyname().equals(companyname)){
                    depts.add(d);
                }
            }

            //获得总人数(edate时间点的在职总人数)
            Integer num;
            Resp resp2 = (Resp) this.getHRManageCard2(companyname,5, 1, sdate, edate);
            if(resp2.getBody()!=null){
                PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp2.getBody();
                num = pageHelper2.getTotal();
                paramMap.put("allNum",num);
            }else {
                return resp2;
            }

            //获得入职总人数(edate时间点的入职总人数)
            Resp resp3 = (Resp) this.getHRManageCard3(companyname,5, 1, sdate, edate);
            if(resp3.getBody()!=null){
                PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp3.getBody();
                paramMap.put("intoNum",pageHelper2.getTotal());
            }else {
                return resp3;
            }

            //获得离职总人数(edate时间点的离职总人数)
            Resp resp4 = (Resp) this.getHRManageCard4(companyname,5, 1, sdate, edate);
            if(resp4.getBody()!=null){
                PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp4.getBody();
                paramMap.put("outNum",pageHelper2.getTotal());
            }else {
                return resp4;
            }

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
                    PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp5.getBody();
                    ratio = pageHelper2.getTotal();
                    hrManageCard.setNum(ratio);
                }else {
                    return resp5;
                }

                //人数占比
                Double db = ratio.doubleValue()/num.doubleValue()*100;
                BigDecimal bg = new BigDecimal(db).setScale(2, RoundingMode.UP);
                hrManageCard.setRatio(bg.doubleValue() + "%");

                //获得所在部门入职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
                Resp resp6 = (Resp) this.getHRManageCard6(5, 1, dept.getId(), sdate, edate);
                if(resp6.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp6.getBody();
                    hrManageCard.setIntoNum(pageHelper2.getTotal());
                }else {
                    return resp6;
                }

                //获得所在部门离职人数(edate时间点的入职总人数)(注意转部门员工的影响，注：此种情况前期版本暂不考虑)
                Resp resp7 = (Resp) this.getHRManageCard7(5, 1, dept.getId(), sdate, edate);
                if(resp7.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp7.getBody();
                    hrManageCard.setOutNum(pageHelper2.getTotal());
                }else {
                    return resp7;
                }

                //获得部门相应的人员(edate时间点的在职人员)
                /*List<Map> users = new ArrayList<>();
                for (PersonalInformation per:((PageHelper<PersonalInformation>) resp2.getBody()).getAllList()
                     ) {
                    HashMap<String, Object> map = new HashMap<>();
                    User user = iUserDao.selectById(per.getUserid());
                    map.put("id",user.getId());
                    map.put("truename",user.getTruename());
                    map.put("deptname",iDeptDao.selectDeptByDepid(per.getDepid()).getDepname());
                    users.add(map);
                }
                hrManageCard.setUsers(users);*/
                hrManageCardList.add(hrManageCard);
            }
            paramMap.put("HRManageCards",hrManageCardList);
            return RespUtil.successResp("205","返回成功！",paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
        }
    }

    //计算时间初期数和时间末期数的工具
    private Map<String,String> getTwoDate(String sdate,String edate){
        HashMap<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String curDate = sdf.format(date);
        Boolean d1 = sdate==null || sdate.equals("");
        Boolean d2 = edate==null || edate.equals("");
        //1.sdate=null,  edate=null=>作为当前时间
        if(d1 && d2){
            sdate = edate =curDate;
        }
        //2.sdate!=null, edate=null=>限：sdate<=当前时间
        //                           结：edate=sdate
        if(!d1 && d2){
            if(sdate.compareTo(curDate)<=0){
                edate=sdate;
            }else {
                return null;
            }
        }
        //3.sdate=null, edate!=null=>限：edate<=当前时间
        //                           结：sdate=edate
        if(d1 && !d2){
            if(edate.compareTo(curDate)<=0){
                sdate = edate;
            }else {
                return null;
            }
        }
        //4.sdate!=null,edate!=null=>限：sdate<=edate<=当前时间
        //                           结：sdate,edate
        if(!d1 && !d2){
            if(sdate.compareTo(edate)>0 || edate.compareTo(curDate)>0){
                return null;
            }
        }
        map.put("sdate",sdate);
        map.put("edate",edate);
        return map;
    };
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll2(null,edate);//时间节点edate前的入职人员
            List<PersonalInformation> personalInformationList2 = iPersonalInformationDao.selectAll3(null,edate);//时间节点edate前的离职人员
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:personalInformationList1) {
                if(
                    !personalInformationList2.contains(per)
                    && (
                        companyname.trim().equals("江苏博智软件科技股份有限公司")
                                ||
                        (per.getDepid()!=null?//没有部门（depid=null）
                                (iDeptDao.selectDeptByDepid(per.getDepid())!=null?//所在部门不存在(depid!=null但相应的部门已经被删除，实际上这种情况是绝对不存在的，私自直接操作数据库时会发生)
                                        iDeptDao.selectDeptByDepid(per.getDepid()).getCompanyname().equals(companyname)//筛选公司
                                        :
                                        false
                                )
                                :
                                false
                        )//没有部门的员工(或者所在部门不存在，实际上这种情况是绝对不存在的)暂时不纳入人事看板的统计
                    )
                ){
                    personalInformationList.add(per);
                }
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page,rows,personalInformationList);
            return RespUtil.successResp("205","提交成功！",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll2(sdate, edate);
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:personalInformationList1
                 ) {
                if(
                    companyname.equals("江苏博智软件科技股份有限公司")
                            ||
                    (per.getDepid()!=null?//没有部门（depid=null）
                            (iDeptDao.selectDeptByDepid(per.getDepid())!=null?//所在部门不存在(depid!=null但相应的部门已经被删除，实际上这种情况是绝对不存在的，私自直接操作数据库时会发生)
                                    iDeptDao.selectDeptByDepid(per.getDepid()).getCompanyname().equals(companyname)//筛选公司
                                    :
                                    false
                            )
                            :
                            false
                    )//没有部门的员工(或者所在部门不存在，实际上这种情况是绝对不存在的)暂时不纳入人事看板的统计
                ){
                    personalInformationList.add(per);
                }
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","提交成功！",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll3(sdate, edate);
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            for (PersonalInformation per:personalInformationList1
                    ) {
                if(
                    companyname.trim().equals("江苏博智软件科技股份有限公司")
                    ||
                    (per.getDepid()!=null?//没有部门（depid=null）
                            (iDeptDao.selectDeptByDepid(per.getDepid())!=null?//所在部门不存在(depid!=null但相应的部门已经被删除，实际上这种情况是绝对不存在的，私自直接操作数据库时会发生)
                                    iDeptDao.selectDeptByDepid(per.getDepid()).getCompanyname().equals(companyname)//筛选公司
                                    :
                                    false
                            )
                            :
                            false
                    )//没有部门的员工(或者所在部门不存在，实际上这种情况是绝对不存在的)暂时不纳入人事看板的统计
                ){
                    personalInformationList.add(per);
                }
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","提交成功！",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectByDepid2(depid,null,edate);
            List<PersonalInformation> personalInformationList2 = iPersonalInformationDao.selectByDepid3(depid,null,edate);
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            if (personalInformationList2.size()>0) {
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
            return RespUtil.successResp("205","提交成功！",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid2(depid, sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","提交成功！",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
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
                return RespUtil.successResp("505","时间选择错误！",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid3(depid, sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","提交成功！",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","系统正在忙，请稍后再试！",null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:forGXF（根据姓名查询相应的公司名称）
     *@Date: 17:25 2018\8\14 0014
     */
    @Override
    public String queryByTruename(String truename) {
        User user;
        if (truename!=null || !"".equals(truename)) {
            user = iUserDao.selectByTruename(truename);
        }else {
            /*this.logger.info("=================================");
            this.logger.info("员工姓名不能为空或NUll！");
            this.logger.info("=================================");*/
            return "99";
        }
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(user.getId());
        Integer depid;
        if(null==personalInformation || null == personalInformation.getDepid()){
            /*this.logger.info("=================================");
            this.logger.info("员工所在的人事信息不存在或员工没有设置部门信息！");
            this.logger.info("=================================");*/
            return "99";
        }else {
            depid = personalInformation.getDepid();
        }
        Dept dept = iDeptDao.selectDeptByDepid(depid);
        if(null==dept){
            /*this.logger.info("=================================");
            this.logger.info("员工所在的部门不存在，请重新设置部门或添加新部门！");
            this.logger.info("=================================");*/
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

    /**
     *@Author:ShiYun;
     *@Description:查询所有的一级公司和二级公司
     *@Date: 10:59 2018\8\21 0021
     */
    @Override
    public List<Dept> queryAllCompany1and2() {
        //先获得一级公司和二级公司的类型ID
        Integer firstDeptypeid = null;
        List<HRset> hRsetFirstList = ihRsetDao.selectByConditions(new HRset("deptype", "一级公司"));
        if(hRsetFirstList!=null && hRsetFirstList.size()==1){
            firstDeptypeid = hRsetFirstList.get(0).getId();
        }
        Integer secondDeptypeid = null;
        List<HRset> hRsetSecondList = ihRsetDao.selectByConditions(new HRset("deptype", "二级公司"));
        if(hRsetSecondList!=null && hRsetSecondList.size()==1){
            secondDeptypeid = hRsetSecondList.get(0).getId();
        }

        //获得相应的公司
        List<Dept> deptList = null;
        if (firstDeptypeid!=null) {
            deptList = iDeptDao.selectDeptByDeptypeid(firstDeptypeid);
        }
        List<Dept> deptList2 = null;
        if (secondDeptypeid!=null) {
            deptList2 = iDeptDao.selectDeptByDeptypeid(secondDeptypeid);
        }
        if (deptList!=null && deptList.size()>0) {
            for (Dept d:deptList
                 ) {
                deptList2.add(d);
            }
        }
        return deptList2;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据公司名称查询公司的部门（不包括公司）
     *@Date: 11:24 2018\8\21 0021
     */
    @Override
    public List<Dept> queryByCompanyname(String companyname) {
        return iDeptDao.selectDeptByCompanyname(companyname);
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有部门（去除下级部门和自身）
     *@Date: 17:59 2018\8\30 0030
     */
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

    /**
     *@Author:ShiYun;
     *@Description:判断是否是子节点
     *@param parentdepid:高级节点
     *@param childdepid:低级节点
     *@return 如果childdepid是parentdepid的子节点则返回true,否则返回false
     *@Date: 17:59 2018\8\30 0030
     */
    @Override
    public Boolean isChildPoint(Integer parentdepid, Integer childdepid) {
        Integer cid = childdepid;
        Integer pid = childdepid;
        if(childdepid==parentdepid){//自己不能作为自己的上级
            return false;
        }
        while (pid!=null){
            if(pid==parentdepid){//是自己的上级返回true
                return true;
            }else {
                cid = pid;
                pid = iDeptDao.selectDeptByDepid(pid).getParentdepid();
            }
        }
        return false;//上级为null时跳出循环（到顶点），说明不是自己的上级
    }

    /**
     *@Author:ShiYun;
     *@param userid 人员ID
     *@param truename 人员名称
     *@return companyname 公司名称
     *@Description:根据人名或人员ID查询所在的公司名称
     *@Date: 14:12 2018\9\8 0008
     */
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
        if(StringUtils.isBlank(dept.getDepcode()))return RespUtil.successResp("500","部门编号不能为空！",null);
        Dept deptTemp = iDeptDao.selectDeptByDeptcode(dept.getDepcode());
        if(null!=deptTemp)return RespUtil.successResp("500","部门编号已存在，请重新输入部门编号！",null);
        //添加新部门
        dept.setPost_list(hrUtils.getArrayToString(dept.getPostList(),","));
        iDeptDao.insertOne(dept);
        return RespUtil.successResp("200","提交成功！",dept);
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
    public List<Map<String,Object>> getAllDepidAndDepnameByDEP_ON() {
        return iDeptDao.getAllDepidAndDepnameByDEP_ON();
    }

    @Override
    public String getPrincipalTruenameByDepid(Integer depid) {
        return iDeptDao.getPrincipalTruenameByDepid(depid);
    }

    @Override
    public List<String> getAllCompanyNames() {
        return iDeptDao.getAllCompanyNamesByDEP_ON();
    }

    @Override
    public Object getAllDepidAndDepnameByRemoveCompany() {
        return iDeptDao.getAllDepidAndDepnameByRemoveCompany();
    }


    //比较新旧部门信息是否有修改并添加部门日志信息（返回布尔值）
    private Map<String, String> getRespMapByOlddeptAndNewdept(Dept oldDept, Dept newDept, String transactorusername, Map<String, String> respMap){
        Boolean isUpdate = false;
        Boolean respBoolean = false;
        Integer depid = oldDept.getId();
        //判断部门编号（首先判断是否相等，如果不相等还要判断是否可用）
        if(!newDept.getDepcode().equals(oldDept.getDepcode())){
            Boolean isExist = getaBooleanByDepcode(newDept.getDepcode());
            if(isExist==null){
                respMap.put(newDept.getDepcode(),"部门编号不能为空！");
                return respMap;
            }
            if(isExist){
                if (!newDept.getDepcode().equals(oldDept.getDepcode())) {
                    respMap.put(newDept.getDepcode(),"部门编号已存在！");
                    return respMap;
                }
            }
            isUpdate = getaBooleanByTwoString(depid,oldDept.getDepcode(),newDept.getDepcode(),transactorusername,"部门编号");
            if(isUpdate)respBoolean = true;
        }
        //判断部门名称并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getDepname(),newDept.getDepname(),transactorusername,"部门名称");
        if(isUpdate)respBoolean = true;
        //判断部门类型并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getDeptype(),newDept.getDeptype(),transactorusername,"部门类型");
        if(isUpdate)respBoolean = true;
        //判断职能类型并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getFunctionaltype(),newDept.getFunctionaltype(),transactorusername,"职能类型");
        if(isUpdate)respBoolean = true;
        //判断上级部门并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,getStringOfDepnameAndDepcode(oldDept.getParentdep()),getStringOfDepnameAndDepcode(newDept.getParentdep()),transactorusername,"上级部门");
        if(isUpdate)respBoolean = true;
        //判断公司名称并部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getCompanyname(),newDept.getCompanyname(),transactorusername,"公司名称");
        if(isUpdate)respBoolean = true;
        //判断部门正职并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,getStringOfTruenameAndEmployeenumber(oldDept.getPrincipaluser()),getStringOfTruenameAndEmployeenumber(newDept.getPrincipaluser()),transactorusername,"部门正职");
        if(isUpdate)respBoolean = true;
        //判断部门副职并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,getStringOfTruenameAndEmployeenumber(oldDept.getDeputyuser()),getStringOfTruenameAndEmployeenumber(newDept.getDeputyuser()),transactorusername,"部门副职");
        if(isUpdate)respBoolean = true;
        //判断部门秘书并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,getStringOfTruenameAndEmployeenumber(oldDept.getSecretaryuser()),getStringOfTruenameAndEmployeenumber(newDept.getSecretaryuser()),transactorusername,"部门秘书");
        if(isUpdate)respBoolean = true;
        //判断部门职责并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getDutydescription(),newDept.getDutydescription(),transactorusername,"部门职责");
        if(isUpdate)respBoolean = true;
        //判断部门概述并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getDepdescription(),newDept.getDepdescription(),transactorusername,"部门概述");
        if(isUpdate)respBoolean = true;
        //判断部门所含岗位并添加部门日志
        isUpdate = getaBooleanByTwoString(depid,oldDept.getPost_list(),newDept.getPost_list(),transactorusername,"部门所含岗位");
        if(isUpdate)respBoolean = true;
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
        if(StringUtils.isBlank(depcode))return null;
        Dept dept = iDeptDao.selectDeptByDeptcodeIgnoreState(depcode);
        if(null==dept)return false;
        return true;
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
