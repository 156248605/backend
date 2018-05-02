package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.permission.EmployeeService;
import com.elex.oa.service.service_shiyun.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:人事档案里的人事信息的查询
 * @Date:Created in  9:47 2018\4\8 0008
 * @Modify By:
 */
@Controller
@CrossOrigin
public class PersonalInformationController {

    @Autowired
    IPersonalInformationService iPersonalInformationService;
    @Autowired
    IUserService iUserService;
    @Autowired
    IDeptService iDeptService;
    @Autowired
    IPostService iPostService;
    @Autowired
    IBaseInformationService iBaseInformationService;
    @Autowired
    IManageInformationService iManageInformationService;
    @Autowired
    ICostInformationService iCostInformationService;
    @Autowired
    IOtherInformationService iOtherInformationService;
    @Autowired
    IChangeInformationService iChangeInformationService;

    @Autowired
    private EmployeeService employeeService; //员工权限相关信息添加修改，高晓飞

    /**
     *@Author:ShiYun;
     *@Description:人事信息的查询
     *@Date: 9:49 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformations")
    @ResponseBody
    public PageInfo<PersonalInformation> queryPersonalInformation(@RequestParam("page") int page,
                                                                  @RequestParam("rows") int rows,
                                                                  @RequestParam("truename") String truename,
                                                                  @RequestParam("employeenumber") String employeenumber,
                                                                  @RequestParam("depname") String depname,
                                                                  @RequestParam("postname") String postname){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("pageNum",page);
        paramMap.put("pageSize",rows);
        PersonalInformation personalInformation = new PersonalInformation();

        //获得userID
        if (truename!=null && truename!=""){
            User user = new User();
            user.setTruename(truename);
            user = iUserService.selectOne(user);
            if (user!=null) {
                personalInformation.setUserid(user.getId());
            }else {
                return null;
            }
        }

        //获得员工号
        if(employeenumber!=null && employeenumber!="") {
            personalInformation.setEmployeenumber(employeenumber);
        }

        //获得部门ID
        if(depname!=null && depname!=""){
            Dept dept = iDeptService.queryOneDepByDepname(depname);
            if (dept!=null) {
                personalInformation.setDepid(dept.getId());
            } else {
                return null;
            }
        }

        //获得岗位ID
        if(postname!=null && postname!=""){
            Post post = iPostService.queryOneByPostname(postname);
            if (post!=null) {
                personalInformation.setPostid(post.getId());
            } else {
                return null;
            }
        }

        paramMap.put("entity",personalInformation);
        PageInfo<PersonalInformation> list = iPersonalInformationService.queryPIs(paramMap);
        List<PersonalInformation> list1 = list.getList();
        for (PersonalInformation pi:list1
             ) {
            User user = iUserService.getById(pi.getUserid());
            pi.setTruename(user.getTruename());
            Dept dept = new Dept();
            dept = iDeptService.queryOneDepByDepid(pi.getDepid());
            if (dept!=null) {
                pi.setDepname(dept.getDepname());
            } else {
                pi.setDepname("部门信息还未添加");
            }
            Post post = iPostService.queryOneByPostid(pi.getPostid());
            if (post!=null) {
                pi.setPostname(post.getPostname());
            } else {
                pi.setPostname("岗位信息还未添加");
            }
        }
        list.setList(list1);
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:人事信息的基本信息查询
     *@Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryBaseInformation")
    @ResponseBody
    public BaseInformation queryBaseInformation(@RequestParam("personalInformationId") int personalInformationId){
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation.getBaseinformationid());

        // 获得员工号
        baseInformation.setEmployeenumber(personalInformation.getEmployeenumber());

        // 获得员工姓名
        User user = iUserService.getById(personalInformation.getUserid());
        baseInformation.setTruename(user.getTruename());

        // 获得获得年龄
        Calendar c1 = Calendar.getInstance();
        int curyear = c1.get(c1.YEAR);
        int curmonth = c1.get(c1.MONTH)+1;
        int curday = c1.get(c1.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date birdate = null;
        try {
            birdate = simpleDateFormat.parse(baseInformation.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c2 = Calendar.getInstance();
        c2.setTime(birdate);
        int biryear = c2.get(c2.YEAR);
        int birmonth = c2.get(c2.MONTH)+1;
        int birday = c2.get(c2.DAY_OF_MONTH);

        int age = curyear - biryear;
        if(curmonth<birmonth){
            age = age - 1;
        }else if(curmonth==birmonth){
            if(curday<birday){
                age = age - 1;
            }
        }
        baseInformation.setAge(age);

        // 获得性别
        baseInformation.setSex(personalInformation.getSex());

        // 获得司龄
        Date firstworkingdate = null;
        try {
            firstworkingdate = simpleDateFormat.parse(baseInformation.getFirstworkingtime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c3 = Calendar.getInstance();
        c3.setTime(firstworkingdate);
        int firstworkingyear = c3.get(c3.YEAR);
        int firstworkingmonth = c3.get(c3.MONTH)+1;
        int firstworkingday = c3.get(c3.DAY_OF_MONTH);

        int firstworkingage = curyear - firstworkingyear;
        if(curmonth<firstworkingmonth){
            firstworkingage = firstworkingage - 1;
        }else if(curmonth==firstworkingmonth){
            if(curday<firstworkingday){
                firstworkingage = firstworkingage - 1;
            }
        }
        baseInformation.setWorkingage(firstworkingage);

        return baseInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:人事信息的管理信息查询
     *@Date: 10:16 2018\4\9 0009
     */
    @RequestMapping("/queryManageInformation")
    @ResponseBody
    public ManageInformation queryManageInformation(@RequestParam("personalInformationId") int personalInformationId){
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        ManageInformation manageInformation = iManageInformationService.queryOneById(personalInformation.getManageinformationid());
        // 获得公司名称
        Dept dept = iDeptService.queryOneDepByDepid(personalInformation.getDepid());
        if (dept!=null) {
            manageInformation.setCompany(dept.getCompanyname());
        } else {
            manageInformation.setCompany("还没选部门");
        }
        // 获得部门名称
        manageInformation.setDepname(dept.getDepname());
        // 获得岗位名称
        Post post = iPostService.queryOneByPostid(personalInformation.getPostid());
        manageInformation.setPostname(post.getPostname());
        // 获得主管名字
        User principaluser = iUserService.getById(dept.getPrincipaluserid());
        if (principaluser!=null) {
            manageInformation.setPrincipalusertruename(principaluser.getTruename());
            // 获得主管工号
            PersonalInformation principalpersonalInformation = iPersonalInformationService.queryOneByUserid(dept.getPrincipaluserid());
            manageInformation.setPrincipalusercode(principalpersonalInformation.getEmployeenumber());
        }
        // 获得岗位级别
        manageInformation.setPostlevel(post.getPostlevel());
        // 获得司龄
        Calendar c1 = Calendar.getInstance();
        int curyear = c1.get(c1.YEAR);
        int curmonth = c1.get(c1.MONTH)+1;
        int curday = c1.get(c1.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date entrydate = null;
        try {
            entrydate = simpleDateFormat.parse(manageInformation.getEntrydate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c2 = Calendar.getInstance();
        c2.setTime(entrydate);
        int entryyear = c2.get(c2.YEAR);
        int entrymonth = c2.get(c2.MONTH)+1;
        int entryday = c2.get(c2.DAY_OF_MONTH);

        int age = curyear - entryyear;
        int mon = curmonth - entrymonth;
        if(curday<entryday){
            mon = mon - 1;
        }
        if(curmonth<entrymonth){
            age = age - 1;
            mon = 12 + mon;
        }else if(curmonth==entrymonth){
            if(curday<entryday){
                age = age - 1;
                mon = 11;
            }
        }

        String sn = age + "年" + mon + "个月";
        manageInformation.setSn(sn);
        return manageInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:人事信息的成本信息查询
     *@Date: 13:12 2018\4\9 0009
     */
    @RequestMapping("/queryCostInformation")
    @ResponseBody
    public CostInformation queryCostInformation(@RequestParam("personalInformationId") int personalInformationId){
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        CostInformation costInformation = iCostInformationService.queryOneById(personalInformation.getCostinformationid());
        return costInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:人事信息的其他信息查询
     *@Date: 13:51 2018\4\9 0009
     */
    @RequestMapping("/queryOtherInformation")
    @ResponseBody
    public OtherInformation queryOtherInformation(@RequestParam("personalInformationId") int personalInformationId){
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        OtherInformation otherInformation = iOtherInformationService.queryOneById(personalInformation.getOtherinformationid());
        // 获得办公电话
        otherInformation.setTelphone(personalInformation.getTelphone());
        // 获得移动电话
        otherInformation.setMobilephone(personalInformation.getMobilephone());
        return otherInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加用户人事信息（基本信息）
     *@Date: 18:07 2018\4\10 0010
     */
    @RequestMapping("/addBaseInformation")
    @ResponseBody
    public Integer addBaseInformation(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("truename") String truename,
            @RequestParam("employeenumber") String employeenumber,
            @RequestParam("bloodtype") String bloodtype,
            @RequestParam("marriage") String marriage,
            @RequestParam("englishname") String englishname,
            @RequestParam("race") String race,
            @RequestParam("idcode") String idcode,
            @RequestParam("hktype") String hktype,
            @RequestParam("birthday") String birthday,
            @RequestParam("zzmm") String zzmm,
            @RequestParam("bysj") String bysj,
            @RequestParam("sex") String sex,
            @RequestParam("hkszd") String hkszd,
            @RequestParam("constellation") String constellation,
            @RequestParam("zgxl") String zgxl,
            @RequestParam("nationality") String nationality,
            @RequestParam("byyx") String byyx,
            @RequestParam("sxzy") String sxzy,
            @RequestParam("pyfs") String pyfs,
            @RequestParam("firstla") String firstla,
            @RequestParam("secondla") String secondla,
            @RequestParam("thirdla") String thirdla,
            @RequestParam("elsela") String elsela,
            @RequestParam("zyzstype") String zyzstype,
            @RequestParam("firstworkingtime") String firstworkingtime,
            @RequestParam("sbdd") String sbdd,
            @RequestParam("parentcompany") String parentcompany,
            @RequestParam("isactive") Integer isactive
    ){
        // 在tb_id_user表中保存用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setTruename(truename);
        user.setIsactive(isactive);
        int id1 = iUserService.saveOne(user);
        Integer userid = user.getId();

        // 在tb_id_baseinformation表中保存用户基本信息
        BaseInformation baseInformation = new BaseInformation();
        baseInformation.setBloodtype(bloodtype);
        baseInformation.setMarriage(marriage);
        baseInformation.setEnglishname(englishname);
        baseInformation.setRace(race);
        baseInformation.setIdcode(idcode);
        baseInformation.setHktype(hktype);
        baseInformation.setBirthday(birthday);
        baseInformation.setZzmm(zzmm);
        baseInformation.setBysj(bysj);
        baseInformation.setHkszd(hkszd);
        baseInformation.setConstellation(constellation);
        baseInformation.setZgxl(zgxl);
        baseInformation.setNationality(nationality);
        baseInformation.setByyx(byyx);
        baseInformation.setSxzy(sxzy);
        baseInformation.setPyfs(pyfs);
        baseInformation.setFirstla(firstla);
        baseInformation.setSecondla(secondla);
        baseInformation.setThirdla(thirdla);
        baseInformation.setElsela(elsela);
        baseInformation.setZyzstype(zyzstype);
        /*baseInformation.setZyzs(zyzs);*/
        /*baseInformation.setZyzscp(zyzscp);*/
        baseInformation.setFirstworkingtime(firstworkingtime);
        baseInformation.setSbdd(sbdd);
        baseInformation.setParentcompany(parentcompany);
        Integer id2 = iBaseInformationService.saveOne(baseInformation);
        Integer baseInformationId = baseInformation.getId();

        // 在tb_id_personalinformation表中添加数据
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setUserid(userid);
        personalInformation.setEmployeenumber(employeenumber);
        personalInformation.setSex(sex);
        personalInformation.setBaseinformationid(baseInformationId);
        Integer id3 = iPersonalInformationService.saveOne(personalInformation);
        Integer personalInformationId = personalInformation.getId();

        return userid;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的管理信息
     *@Date: 11:36 2018\4\11 0011
     */
    @RequestMapping("/addManageInformation")
    @ResponseBody
    public String addManageInformation(
            @RequestParam("userid") Integer userid,
            @RequestParam("depid") Integer depid,
            @RequestParam("postid") Integer postid,
            @RequestParam("checkworkattendance") String checkworkattendance,
            @RequestParam("workingaddress") String workingaddress,
            @RequestParam("zj") String zj,
            @RequestParam("employeetype") String employeetype,
            @RequestParam("entrydate") String entrydate,
            @RequestParam("zhuanzhengdate") String zhuanzhengdate
    ){
        // 保存人事信息的管理信息
        ManageInformation manageInformation = new ManageInformation();
        manageInformation.setCheckworkattendance(checkworkattendance);
        manageInformation.setWorkingaddress(workingaddress);
        manageInformation.setZj(zj);
        manageInformation.setEmployeetype(employeetype);
        manageInformation.setEntrydate(entrydate);
        manageInformation.setZhuanzhengdate(zhuanzhengdate);
        Integer id1 = iManageInformationService.saveOne(manageInformation);
        Integer manageInformationId = manageInformation.getId();

        // 修改人事信息的主体信息
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        personalInformation.setDepid(depid);
        personalInformation.setPostid(postid);
        personalInformation.setManageinformationid(manageInformationId);
        iPersonalInformationService.modifyOne(personalInformation);
        //姓名、工号、部门、岗位
        User user = iUserService.getById(userid);
        /*String truename = user.getTruename();员工姓名*/
        /*String employeenumber = baseInformation.getEmployeenumber();员工工号*/
        /*depid部门ID
        postid岗位ID*/
        Integer isactive = user.getIsactive();
        int judgment = employeeService.addEmployee(user.getTruename(),personalInformation.getEmployeenumber(),depid,postid,isactive); //权限相关部分，员工信息添加，高晓飞

        return "管理信息添加成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的成本信息
     *@Date: 17:41 2018\4\11 0011
     */
    @RequestMapping("/addCostInformation")
    @ResponseBody
    public String addCostInformation(
            @RequestParam("userid") int userid,
            @RequestParam("costtype") String costtype,
            @RequestParam("costcode") String costcode,
            @RequestParam("salary") String salary,
            @RequestParam("accumulationfund") String accumulationfund,
            @RequestParam("gscdylbx") String gscdylbx,
            @RequestParam("grcdylbx") String grcdylbx,
            @RequestParam("gscdgjj") String gscdgjj,
            @RequestParam("grcdgjj") String grcdgjj,
            @RequestParam("gscdsybx") String gscdsybx,
            @RequestParam("grcdsybx") String grcdsybx,
            @RequestParam("gscdylbx2") String gscdylbx2,
            @RequestParam("grcdylbx2") String grcdylbx2,
            @RequestParam("shengyubx") String shengyubx,
            @RequestParam("gsbx") String gsbx,
            @RequestParam("salarykhh") String salarykhh,
            @RequestParam("salaryaccount") String salaryaccount,
            @RequestParam("sbjnd") String sbjnd,
            @RequestParam("sbcode") String sbcode
    ){
        // 保存人事信息的成本信息
        CostInformation costInformation = new CostInformation();
        costInformation.setCosttype(costtype);
        costInformation.setCostcode(costcode);
        costInformation.setSalary(salary);
        costInformation.setAccumulationfund(accumulationfund);
        costInformation.setGscdylbx(gscdylbx);
        costInformation.setGrcdylbx(grcdylbx);
        costInformation.setGscdgjj(gscdgjj);
        costInformation.setGrcdgjj(grcdgjj);
        costInformation.setGscdsybx(gscdsybx);
        costInformation.setGrcdsybx(grcdsybx);
        costInformation.setGscdylbx2(gscdylbx2);
        costInformation.setGrcdylbx2(grcdylbx2);
        costInformation.setShengyubx(shengyubx);
        costInformation.setGsbx(gsbx);
        costInformation.setSalarykhh(salarykhh);
        costInformation.setSalaryaccount(salaryaccount);
        costInformation.setSbjnd(sbjnd);
        costInformation.setSbcode(sbcode);
        Integer id1 = iCostInformationService.saveOne(costInformation);
        Integer costInformationId = costInformation.getId();

        // 修改人事信息
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        personalInformation.setCostinformationid(costInformationId);
        iPersonalInformationService.modifyOne(personalInformation);

        return "添加成本信息成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的其它信息
     *@Date: 18:42 2018\4\11 0011
     */
    @RequestMapping("/addOtherInformation")
    @ResponseBody
    public String addOtherInformation(
            @RequestParam("userid") int userid,
            @RequestParam("telphone") String telphone,
            @RequestParam("mobilephone") String mobilephone,
            @RequestParam("companyemail") String companyemail,
            @RequestParam("privateemail") String privateemail,
            @RequestParam("emergencycontract") String emergencycontract,
            @RequestParam("emergencyphone") String emergencyphone,
            @RequestParam("address") String address,
            @RequestParam("remark") String remark
    ){
        // 添加人事信息的其它信息
        OtherInformation otherInformation = new OtherInformation();
        otherInformation.setCompanyemail(companyemail);
        otherInformation.setPrivateemail(privateemail);
        otherInformation.setEmergencycontract(emergencycontract);
        otherInformation.setEmergencyphone(emergencyphone);
        otherInformation.setAddress(address);
        otherInformation.setRemark(remark);
        Integer id1 = iOtherInformationService.saveOne(otherInformation);
        Integer otherInformationId = otherInformation.getId();

        // 修改人事信息
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        personalInformation.setTelphone(telphone);
        personalInformation.setMobilephone(mobilephone);
        personalInformation.setOtherinformationid(otherInformationId);
        iPersonalInformationService.modifyOne(personalInformation);

        return "添加其他信息成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的基本信息
     *@Date: 13:59 2018\4\12 0012
     */
    @RequestMapping("/updateBaseInformation")
    @ResponseBody
    public String updateBaseInformation(
            @RequestParam("userid") Integer userid,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("truename") String truename,
            @RequestParam("employeenumber") String employeenumber,
            @RequestParam("bloodtype") String bloodtype,
            @RequestParam("marriage") String marriage,
            @RequestParam("englishname") String englishname,
            @RequestParam("race") String race,
            @RequestParam("idcode") String idcode,
            @RequestParam("hktype") String hktype,
            @RequestParam("birthday") String birthday,
            @RequestParam("zzmm") String zzmm,
            @RequestParam("bysj") String bysj,
            @RequestParam("sex") String sex,
            @RequestParam("hkszd") String hkszd,
            @RequestParam("constellation") String constellation,
            @RequestParam("zgxl") String zgxl,
            @RequestParam("nationality") String nationality,
            @RequestParam("byyx") String byyx,
            @RequestParam("sxzy") String sxzy,
            @RequestParam("pyfs") String pyfs,
            @RequestParam("firstla") String firstla,
            @RequestParam("secondla") String secondla,
            @RequestParam("thirdla") String thirdla,
            @RequestParam("elsela") String elsela,
            @RequestParam("zyzstype") String zyzstype,
            @RequestParam("firstworkingtime") String firstworkingtime,
            @RequestParam("sbdd") String sbdd,
            @RequestParam("parentcompany") String parentcompany
    ){
        // 返回信息
        String gobackmessage = "没有需要修改的信息！";

        //添加修改信息
        ChangeInformation changeInformation = new ChangeInformation();
        changeInformation.setChangeduserid(userid);
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        changeInformation.setChangedate(changedate);
        changeInformation.setTransactoruserid(1);//权限管理还没有做，先默认办理人为管理员

        // 在tb_id_user表中保存修改后的用户数据
        User user = iUserService.getById(userid);
        if (!username.equals(user.getUsername())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("用户名");
            changeInformation.setBeforeinformation(user.getUsername());
            changeInformation.setAfterinformation(username);
            iChangeInformationService.addOne(changeInformation);
        }
        user.setUsername(username);
        if (!password.equals(user.getPassword())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("密码");
            changeInformation.setBeforeinformation(user.getPassword());
            changeInformation.setAfterinformation(password);
            iChangeInformationService.addOne(changeInformation);
        }
        user.setPassword(password);
        if (!truename.equals(user.getTruename())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("姓名");
            changeInformation.setBeforeinformation(user.getTruename());
            changeInformation.setAfterinformation(truename);
            iChangeInformationService.addOne(changeInformation);
        }
        user.setTruename(truename);
        iUserService.update(user);

        // 在tb_id_personalinformation表中修改数据
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        if (!employeenumber.equals(personalInformation.getEmployeenumber())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("员工号");
            changeInformation.setBeforeinformation(personalInformation.getEmployeenumber());
            changeInformation.setAfterinformation(employeenumber);
            iChangeInformationService.addOne(changeInformation);
        }
        personalInformation.setEmployeenumber(employeenumber);
        if (!sex.equals(personalInformation.getSex())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("性别");
            changeInformation.setBeforeinformation(personalInformation.getSex());
            changeInformation.setAfterinformation(sex);
            iChangeInformationService.addOne(changeInformation);
        }
        personalInformation.setSex(sex);
        iPersonalInformationService.modifyOne(personalInformation);

        // 在tb_id_baseinformation表中保存修改后的用户基本信息数据
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation.getBaseinformationid());
        if (!bloodtype.equals(baseInformation.getBloodtype())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("血型 ");
            changeInformation.setBeforeinformation(baseInformation.getBloodtype());
            changeInformation.setAfterinformation(bloodtype);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setBloodtype(bloodtype);
        if (!marriage.equals(baseInformation.getMarriage())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("婚姻 ");
            changeInformation.setBeforeinformation(baseInformation.getMarriage());
            changeInformation.setAfterinformation(marriage);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setMarriage(marriage);
        if (!englishname.equals(baseInformation.getEnglishname())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("英文名 ");
            changeInformation.setBeforeinformation(baseInformation.getEnglishname());
            changeInformation.setAfterinformation(englishname);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setEnglishname(englishname);
        if (!race.equals(baseInformation.getRace())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("民族 ");
            changeInformation.setBeforeinformation(baseInformation.getRace());
            changeInformation.setAfterinformation(race);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setRace(race);
        if (!idcode.equals(baseInformation.getIdcode())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("身份证 ");
            changeInformation.setBeforeinformation(baseInformation.getIdcode());
            changeInformation.setAfterinformation(idcode);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setIdcode(idcode);
        if (!hktype.equals(baseInformation.getHktype())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("户口类型 ");
            changeInformation.setBeforeinformation(baseInformation.getHktype());
            changeInformation.setAfterinformation(hktype);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setHktype(hktype);
        if (!birthday.equals(baseInformation.getBirthday())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("生日 ");
            changeInformation.setBeforeinformation(baseInformation.getBirthday());
            changeInformation.setAfterinformation(birthday);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setBirthday(birthday);
        if (!zzmm.equals(baseInformation.getZzmm())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("政治面貌 ");
            changeInformation.setBeforeinformation(baseInformation.getZzmm());
            changeInformation.setAfterinformation(zzmm);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setZzmm(zzmm);
        if (!bysj.equals(baseInformation.getBysj())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("毕业时间 ");
            changeInformation.setBeforeinformation(baseInformation.getBysj());
            changeInformation.setAfterinformation(bysj);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setBysj(bysj);
        if (!hkszd.equals(baseInformation.getHkszd())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("户口所在地 ");
            changeInformation.setBeforeinformation(baseInformation.getHkszd());
            changeInformation.setAfterinformation(hkszd);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setHkszd(hkszd);
        if (!constellation.equals(baseInformation.getConstellation())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("星座 ");
            changeInformation.setBeforeinformation(baseInformation.getConstellation());
            changeInformation.setAfterinformation(constellation);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setConstellation(constellation);
        if (!zgxl.equals(baseInformation.getZgxl())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("最高学历 ");
            changeInformation.setBeforeinformation(baseInformation.getZgxl());
            changeInformation.setAfterinformation(zgxl);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setZgxl(zgxl);
        if (!nationality.equals(baseInformation.getNationality())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("国家 ");
            changeInformation.setBeforeinformation(baseInformation.getNationality());
            changeInformation.setAfterinformation(nationality);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setNationality(nationality);
        if (!byyx.equals(baseInformation.getByyx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("毕业学校 ");
            changeInformation.setBeforeinformation(baseInformation.getByyx());
            changeInformation.setAfterinformation(byyx);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setByyx(byyx);
        if (!sxzy.equals(baseInformation.getSxzy())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("所学专业 ");
            changeInformation.setBeforeinformation(baseInformation.getSxzy());
            changeInformation.setAfterinformation(sxzy);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setSxzy(sxzy);
        if (!pyfs.equals(baseInformation.getPyfs())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("培养方式 ");
            changeInformation.setBeforeinformation(baseInformation.getPyfs());
            changeInformation.setAfterinformation(pyfs);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setPyfs(pyfs);
        if (!firstla.equals(baseInformation.getFirstla())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("第一外语 ");
            changeInformation.setBeforeinformation(baseInformation.getFirstla());
            changeInformation.setAfterinformation(firstla);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setFirstla(firstla);
        if (!secondla.equals(baseInformation.getSecondla())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("第二外语 ");
            changeInformation.setBeforeinformation(baseInformation.getSecondla());
            changeInformation.setAfterinformation(secondla);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setSecondla(secondla);
        if (!thirdla.equals(baseInformation.getThirdla())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("第三外语 ");
            changeInformation.setBeforeinformation(baseInformation.getThirdla());
            changeInformation.setAfterinformation(thirdla);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setThirdla(thirdla);
        if (!elsela.equals(baseInformation.getElsela())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("其他外语 ");
            changeInformation.setBeforeinformation(baseInformation.getElsela());
            changeInformation.setAfterinformation(elsela);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setElsela(elsela);
        if (!zyzstype.equals(baseInformation.getZyzstype())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("职业证书类型 ");
            changeInformation.setBeforeinformation(baseInformation.getZyzstype());
            changeInformation.setAfterinformation(zyzstype);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setZyzstype(zyzstype);
        if (!firstworkingtime.equals(baseInformation.getFirstworkingtime())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("第一次工作时间 ");
            changeInformation.setBeforeinformation(baseInformation.getFirstworkingtime());
            changeInformation.setAfterinformation(firstworkingtime);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setFirstworkingtime(firstworkingtime);
        if (!sbdd.equals(baseInformation.getSbdd())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("社保断档 ");
            changeInformation.setBeforeinformation(baseInformation.getSbdd());
            changeInformation.setAfterinformation(sbdd);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setSbdd(sbdd);
        if (!parentcompany.equals(baseInformation.getParentcompany())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("上一家公司 ");
            changeInformation.setBeforeinformation(baseInformation.getParentcompany());
            changeInformation.setAfterinformation(parentcompany);
            iChangeInformationService.addOne(changeInformation);
        }
        baseInformation.setParentcompany(parentcompany);
        iBaseInformationService.modifyOne(baseInformation);

        return gobackmessage;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的管理信息
     *@Date: 15:59 2018\4\12 0012
     */
    @RequestMapping("/updateManageInformation")
    @ResponseBody
    public String updateManageInformation(
            @RequestParam("userid") Integer userid,
            @RequestParam("depid") Integer depid,
            @RequestParam("postid") Integer postid,
            @RequestParam("checkworkattendance") String checkworkattendance,
            @RequestParam("workingaddress") String workingaddress,
            @RequestParam("zj") String zj,
            @RequestParam("employeetype") String employeetype,
            @RequestParam("entrydate") String entrydate,
            @RequestParam("zhuanzhengdate") String zhuanzhengdate
    ){
        // 返回信息
        String gobackmessage = "没有需要修改的信息！";
        // 添加变更信息
        ChangeInformation changeInformation = new ChangeInformation();
        changeInformation.setChangeduserid(userid);
        changeInformation.setChangereason("公司业务需要");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        changeInformation.setChangedate(changedate);
        changeInformation.setTransactoruserid(1);//权限管理还没有做，先默认办理人为管理员

        // 修改人事信息的主体信息
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        if (depid!=null) {
            if (depid!=personalInformation.getDepid()){
                gobackmessage = "修改信息成功！";
                changeInformation.setChangedinformation("部门");
                if (personalInformation.getDepid()!=null) {
                    changeInformation.setBeforeinformation(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getDepname());
                } else {
                    changeInformation.setBeforeinformation("无");
                }
                changeInformation.setAfterinformation(iDeptService.queryOneDepByDepid(depid).getDepname());
                iChangeInformationService.addOne(changeInformation);
            }
            personalInformation.setDepid(depid);
        }
        /*
        * 人员变动，修改部门 id,departmentid,
        * userid 即为id
        * depid 即为departmentid
        * */
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation.getBaseinformationid());
        /*String employeenumber = baseInformation.getEmployeenumber();*/

        int judgment = employeeService.departmentModify(baseInformation.getEmployeenumber(),depid); //修改权限部分员工的部门信息，返回值为1修改成功，返回0修改失败


        if (postid!=null) {
            if (postid!=personalInformation.getPostid()){
                gobackmessage = "修改信息成功！";
                changeInformation.setChangedinformation("岗位");
                if (personalInformation.getPostid()!=null) {
                    changeInformation.setBeforeinformation(iPostService.queryOneByPostid(personalInformation.getPostid()).getPostname());
                } else {
                    changeInformation.setBeforeinformation("无");
                }
                changeInformation.setAfterinformation(iPostService.queryOneByPostid(postid).getPostname());
                iChangeInformationService.addOne(changeInformation);
            }
            personalInformation.setPostid(postid);
        }
        if (depid!=null || postid!=null) {
            iPersonalInformationService.modifyOne(personalInformation);
        }

        // 修改人事信息的管理信息
        ManageInformation manageInformation = new ManageInformation();
        Boolean b = true;
        ManageInformation manageInformation1 = iManageInformationService.queryOneById(personalInformation.getManageinformationid());
        if(manageInformation1==null){
            b = false;
        }else{
            manageInformation = manageInformation1;
        }
        if (manageInformation1!=null && !checkworkattendance.equals(manageInformation.getCheckworkattendance())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("考勤机卡号");
            changeInformation.setBeforeinformation(manageInformation.getCheckworkattendance());
            changeInformation.setAfterinformation(checkworkattendance);
            changeInformation.setChangereason("正常修改信息");
            iChangeInformationService.addOne(changeInformation);
        }
        manageInformation.setCheckworkattendance(checkworkattendance);
        if (manageInformation1!=null && !workingaddress.equals(manageInformation.getWorkingaddress())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("工作地点");
            changeInformation.setBeforeinformation(manageInformation.getWorkingaddress());
            changeInformation.setAfterinformation(workingaddress);
            iChangeInformationService.addOne(changeInformation);
        }
        manageInformation.setWorkingaddress(workingaddress);
        if (manageInformation1!=null && !zj.equals(manageInformation.getZj())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("职级");
            changeInformation.setBeforeinformation(manageInformation.getZj());
            changeInformation.setAfterinformation(zj);
            iChangeInformationService.addOne(changeInformation);
        }
        manageInformation.setZj(zj);
        if (manageInformation1!=null && !employeetype.equals(manageInformation.getEmployeetype())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("员工类型");
            changeInformation.setBeforeinformation(manageInformation.getEmployeetype());
            changeInformation.setAfterinformation(employeetype);
            iChangeInformationService.addOne(changeInformation);
        }
        manageInformation.setEmployeetype(employeetype);
        if (manageInformation1!=null && !entrydate.equals(manageInformation.getEntrydate())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("入职时间");
            changeInformation.setBeforeinformation(manageInformation.getEntrydate());
            changeInformation.setAfterinformation(entrydate);
            iChangeInformationService.addOne(changeInformation);
        }
        manageInformation.setEntrydate(entrydate);
        if (manageInformation1!=null && !zhuanzhengdate.equals(manageInformation.getZhuanzhengdate())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("转正时间");
            changeInformation.setBeforeinformation(manageInformation.getZhuanzhengdate());
            changeInformation.setAfterinformation(zhuanzhengdate);
            iChangeInformationService.addOne(changeInformation);
        }
        manageInformation.setZhuanzhengdate(zhuanzhengdate);
        if (b) {
            iManageInformationService.modifyOne(manageInformation);
        } else {
            Integer integer = iManageInformationService.saveOne(manageInformation);
            personalInformation.setManageinformationid(manageInformation.getId());
            iPersonalInformationService.modifyOne(personalInformation);
        }

        return gobackmessage;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改成本信息
     *@Date: 16:28 2018\4\12 0012
     */
    @RequestMapping("/updateCostInformation")
    @ResponseBody
    public String updateCostInformation(
            @RequestParam("userid") int userid,
            @RequestParam("costtype") String costtype,
            @RequestParam("costcode") String costcode,
            @RequestParam("salary") String salary,
            @RequestParam("accumulationfund") String accumulationfund,
            @RequestParam("gscdylbx") String gscdylbx,
            @RequestParam("grcdylbx") String grcdylbx,
            @RequestParam("gscdgjj") String gscdgjj,
            @RequestParam("grcdgjj") String grcdgjj,
            @RequestParam("gscdsybx") String gscdsybx,
            @RequestParam("grcdsybx") String grcdsybx,
            @RequestParam("gscdylbx2") String gscdylbx2,
            @RequestParam("grcdylbx2") String grcdylbx2,
            @RequestParam("shengyubx") String shengyubx,
            @RequestParam("gsbx") String gsbx,
            @RequestParam("salarykhh") String salarykhh,
            @RequestParam("salaryaccount") String salaryaccount,
            @RequestParam("sbjnd") String sbjnd,
            @RequestParam("sbcode") String sbcode
    ){
        // 返回信息
        String gobackmessage = "没有需要修改的信息！";

        // 添加变更信息
        ChangeInformation changeInformation = new ChangeInformation();
        changeInformation.setChangeduserid(userid);
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        changeInformation.setChangedate(changedate);
        changeInformation.setTransactoruserid(1);//权限管理还没有做，先默认办理人为管理员

        // 修改人事信息的成本信息
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        Boolean b = true;
        CostInformation costInformation = new CostInformation();
        CostInformation costInformation1 = iCostInformationService.queryOneById(personalInformation.getCostinformationid());
        if(costInformation1==null){
            b = false;
        }else{
            costInformation = costInformation1;
        }
        if (costInformation1!=null && !costtype.equals(costInformation.getCosttype())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("成本类型");
            changeInformation.setBeforeinformation(costInformation.getCosttype());
            changeInformation.setAfterinformation(costtype);
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setCosttype(costtype);
        if (costInformation1!=null && !costcode.equals(costInformation.getCostcode())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("成本编号");
            changeInformation.setBeforeinformation(costInformation.getCostcode());
            changeInformation.setAfterinformation(costcode);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setCostcode(costcode);
        if (costInformation1!=null && !salary.equals(costInformation.getSalary())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("工资");
            changeInformation.setBeforeinformation(costInformation.getSalary());
            changeInformation.setAfterinformation(salary);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setSalary(salary);
        if (costInformation1!=null && !accumulationfund.equals(costInformation.getAccumulationfund())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("公积金账号");
            changeInformation.setBeforeinformation(costInformation.getAccumulationfund());
            changeInformation.setAfterinformation(accumulationfund);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setAccumulationfund(accumulationfund);
        if (costInformation1!=null && !gscdylbx.equals(costInformation.getGscdylbx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("公司承担养老保险");
            changeInformation.setBeforeinformation(costInformation.getGscdylbx());
            changeInformation.setAfterinformation(gscdylbx);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGscdylbx(gscdylbx);
        if (costInformation1!=null && !grcdylbx.equals(costInformation.getGrcdylbx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("个人承担养老保险");
            changeInformation.setBeforeinformation(costInformation.getGrcdylbx());
            changeInformation.setAfterinformation(grcdylbx);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGrcdylbx(grcdylbx);
        if (costInformation1!=null && !gscdgjj.equals(costInformation.getGscdgjj())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("公司承担公积金");
            changeInformation.setBeforeinformation(costInformation.getGscdgjj());
            changeInformation.setAfterinformation(gscdgjj);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGscdgjj(gscdgjj);
        if (costInformation1!=null && !grcdgjj.equals(costInformation.getGrcdgjj())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("个人承担公积金");
            changeInformation.setBeforeinformation(costInformation.getGrcdgjj());
            changeInformation.setAfterinformation(grcdgjj);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGrcdgjj(grcdgjj);
        if (costInformation1!=null && !gscdsybx.equals(costInformation.getGscdsybx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("公司承担失业保险");
            changeInformation.setBeforeinformation(costInformation.getGscdsybx());
            changeInformation.setAfterinformation(gscdsybx);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGscdsybx(gscdsybx);
        if (costInformation1!=null && !grcdsybx.equals(costInformation.getGrcdsybx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("个人承担失业保险");
            changeInformation.setBeforeinformation(costInformation.getGrcdsybx());
            changeInformation.setAfterinformation(grcdsybx);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGrcdsybx(grcdsybx);
        if (costInformation1!=null && !gscdylbx2.equals(costInformation.getGscdylbx2())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("公司承担医疗保险");
            changeInformation.setBeforeinformation(costInformation.getGscdylbx2());
            changeInformation.setAfterinformation(gscdylbx2);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGscdylbx2(gscdylbx2);
        if (costInformation1!=null && !grcdylbx2.equals(costInformation.getGrcdylbx2())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("个人承担医疗保险");
            changeInformation.setBeforeinformation(costInformation.getGrcdylbx2());
            changeInformation.setAfterinformation(grcdylbx2);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGrcdylbx2(grcdylbx2);
        if (costInformation1!=null && !shengyubx.equals(costInformation.getShengyubx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("生育保险");
            changeInformation.setBeforeinformation(costInformation.getShengyubx());
            changeInformation.setAfterinformation(shengyubx);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setShengyubx(shengyubx);
        if (costInformation1!=null && !gsbx.equals(costInformation.getGsbx())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("工伤保险");
            changeInformation.setBeforeinformation(costInformation.getGsbx());
            changeInformation.setAfterinformation(gsbx);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setGsbx(gsbx);
        if (costInformation1!=null && !salarykhh.equals(costInformation.getSalarykhh())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("工资开户行");
            changeInformation.setBeforeinformation(costInformation.getSalarykhh());
            changeInformation.setAfterinformation(salarykhh);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setSalarykhh(salarykhh);
        if (costInformation1!=null && !salaryaccount.equals(costInformation.getSalaryaccount())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("工资账号");
            changeInformation.setBeforeinformation(costInformation.getSalaryaccount());
            changeInformation.setAfterinformation(salaryaccount);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setSalaryaccount(salaryaccount);
        if (costInformation1!=null && !sbjnd.equals(costInformation.getSbjnd())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("社保缴纳地");
            changeInformation.setBeforeinformation(costInformation.getSbjnd());
            changeInformation.setAfterinformation(sbjnd);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setSbjnd(sbjnd);
        if (costInformation1!=null && !sbcode.equals(costInformation.getSbcode())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("社保账号");
            changeInformation.setBeforeinformation(costInformation.getSbcode());
            changeInformation.setAfterinformation(sbcode);
            changeInformation.setTransactoruserid(79);//权限管理还没有做，先默认办理人为管理员
            iChangeInformationService.addOne(changeInformation);
        }
        costInformation.setSbcode(sbcode);
        if (b) {
            iCostInformationService.modifyOne(costInformation);
        } else {
            Integer integer = iCostInformationService.saveOne(costInformation);
            personalInformation.setCostinformationid(costInformation.getId());
            iPersonalInformationService.modifyOne(personalInformation);
        }

        return gobackmessage;
    }

    /**
     *@Author:ShiYun;
     *@Description:修改其他信息
     *@Date: 16:46 2018\4\12 0012
     */
    @RequestMapping("/updateOtherInformation")
    @ResponseBody
    public String updateOtherInformation(
            @RequestParam("userid") int userid,
            @RequestParam("telphone") String telphone,
            @RequestParam("mobilephone") String mobilephone,
            @RequestParam("companyemail") String companyemail,
            @RequestParam("privateemail") String privateemail,
            @RequestParam("emergencycontract") String emergencycontract,
            @RequestParam("emergencyphone") String emergencyphone,
            @RequestParam("address") String address,
            @RequestParam("remark") String remark
    ){
        // 返回信息
        String gobackmessage = "没有需要修改的信息！";

        // 添加变更信息
        ChangeInformation changeInformation = new ChangeInformation();
        changeInformation.setChangeduserid(userid);
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        changeInformation.setChangedate(changedate);
        changeInformation.setTransactoruserid(1);//权限管理还没有做，先默认办理人为管理员

        // 修改人事信息
        PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(userid);
        if (!telphone.equals(personalInformation.getTelphone())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("办公电话");
            changeInformation.setBeforeinformation(personalInformation.getTelphone());
            changeInformation.setAfterinformation(telphone);
            iChangeInformationService.addOne(changeInformation);
        }
        personalInformation.setTelphone(telphone);
        if (!mobilephone.equals(personalInformation.getMobilephone())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("移动电话");
            changeInformation.setBeforeinformation(personalInformation.getMobilephone());
            changeInformation.setAfterinformation(mobilephone);
            iChangeInformationService.addOne(changeInformation);
        }
        personalInformation.setMobilephone(mobilephone);
        iPersonalInformationService.modifyOne(personalInformation);

        // 修改人事信息的其它信息
        Boolean b = true;
        OtherInformation otherInformation = new OtherInformation();
        OtherInformation otherInformation1 = iOtherInformationService.queryOneById(personalInformation.getOtherinformationid());
        if(otherInformation1==null){
            b = false;
        }else {
            otherInformation = otherInformation1;
        }
        if (otherInformation1!=null && !companyemail.equals(otherInformation.getCompanyemail())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("公司邮箱");
            changeInformation.setBeforeinformation(otherInformation.getCompanyemail());
            changeInformation.setAfterinformation(companyemail);
            iChangeInformationService.addOne(changeInformation);
        }
        otherInformation.setCompanyemail(companyemail);
        if (otherInformation1!=null && !privateemail.equals(otherInformation.getPrivateemail())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("私人邮箱");
            changeInformation.setBeforeinformation(otherInformation.getPrivateemail());
            changeInformation.setAfterinformation(privateemail);
            iChangeInformationService.addOne(changeInformation);
        }
        otherInformation.setPrivateemail(privateemail);
        if (otherInformation1!=null && !emergencycontract.equals(otherInformation.getEmergencycontract())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("紧急联系人");
            changeInformation.setBeforeinformation(otherInformation.getEmergencycontract());
            changeInformation.setAfterinformation(emergencycontract);
            iChangeInformationService.addOne(changeInformation);
        }
        otherInformation.setEmergencycontract(emergencycontract);
        if (otherInformation1!=null && !emergencyphone.equals(otherInformation.getEmergencyphone())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("紧急联系人电话");
            changeInformation.setBeforeinformation(otherInformation.getEmergencyphone());
            changeInformation.setAfterinformation(emergencyphone);
            iChangeInformationService.addOne(changeInformation);
        }
        otherInformation.setEmergencyphone(emergencyphone);
        if (otherInformation1!=null && !address.equals(otherInformation.getAddress())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("住址");
            changeInformation.setBeforeinformation(otherInformation.getAddress());
            changeInformation.setAfterinformation(address);
            iChangeInformationService.addOne(changeInformation);
        }
        otherInformation.setAddress(address);
        if (otherInformation1!=null && !remark.equals(otherInformation.getRemark())){
            gobackmessage = "修改信息成功！";
            changeInformation.setChangedinformation("备注");
            changeInformation.setBeforeinformation(otherInformation.getRemark());
            changeInformation.setAfterinformation(remark);
            iChangeInformationService.addOne(changeInformation);
        }
        otherInformation.setRemark(remark);
        if (b) {
            iOtherInformationService.modifyOne(otherInformation);
        } else {
            Integer integer = iOtherInformationService.saveOne(otherInformation);
            personalInformation.setOtherinformationid(otherInformation.getId());
            iPersonalInformationService.modifyOne(personalInformation);
        }

        return gobackmessage;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门id查询人事信息
     *@Date: 16:40 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByDepid")
    @ResponseBody
    public List<PersonalInformation> queryPersonalInformationsByDepid(
            PersonalInformation personalInformation
    ){
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryPIs(personalInformation);
        for(Integer i=0;i<personalInformations.size();i++){
            Dept dept = iDeptService.queryOneDepByDepid(personalInformations.get(i).getDepid());
            personalInformations.get(i).setDepname(dept.getDepname());
            User user = iUserService.getById(personalInformations.get(i).getUserid());
            personalInformations.get(i).setTruename(user.getTruename());
        }
        return personalInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有员工不分页
     *@Date: 17:23 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByNull")
    @ResponseBody
    public List<PersonalInformation> queryPersonalInformationsByNull(){
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryAllByNull();
        for(Integer i=0;i<personalInformations.size();i++){
            Dept dept = iDeptService.queryOneDepByDepid(personalInformations.get(i).getDepid());
            personalInformations.get(i).setDepname(dept.getDepname());
            User user = iUserService.getById(personalInformations.get(i).getUserid());
            personalInformations.get(i).setTruename(user.getTruename());
        }
        return personalInformations;
    }
}
