package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IDeptDao;
import com.elex.oa.dao.dao_shiyun.IPersonalInformationDao;
import com.elex.oa.dao.dao_shiyun.IUserDao;
import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.entity_shiyun.HRManageCard;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author:ShiYun;
 * @Description:
 * @Date:Created in  13:33 2018\3\16 0016
 * @Modify By:
 */
@Service
public class DeptServiceImpl implements IDeptService {

    @Autowired
    IDeptDao iDeptDao;
    @Autowired
    IPersonalInformationDao iPersonalInformationDao;
    @Autowired
    IUserDao iUserDao;

    /**
     *@Author:ShiYun;
     *@Description:根据部门名称获得部门
     *@Date: 15:29 2018\6\1 0001
     */
    @Override
    public Dept queryOneDepByDepname(String depname) {
        return iDeptDao.selectDeptByDeptname(depname);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门code查询部门信息
     *@Date: 10:20 2018\7\16 0016
     */
    @Override
    public Dept queryOneByDepcode(String depcode) {
        Dept dept = iDeptDao.selectDeptByDeptcode(depcode);
        return dept;
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
            dept = iDeptDao.selectDeptByDeptname("江苏博智软件科技股份有限公司");
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
    public Object getHRManageCard(String sdate,String edate) {
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
                return "所选时间必须<=当前时间！";
            }
        }
        //3.sdate=null, edate!=null=>限：edate<=当前时间
        //                           结：sdate=edate
        if(d1 && !d2){
            if(edate.compareTo(curDate)<=0){
                sdate = edate;
            }else {
                return "所选时间必须<=当前时间！";
            }
        }
        //4.sdate!=null,edate!=null=>限：sdate<=edate<=当前时间
        //                           结：sdate,edate
        if(!d1 && !d2){
            if(sdate.compareTo(edate)>0 || edate.compareTo(curDate)>0){
                return "所选时间必须满足期初数<=期末数<=当前时间！";
            }
        }
        HashMap<String, Object> paramMap = new HashMap<>();
        List<HRManageCard> hrManageCardList = new ArrayList<>();
        List<Dept> depts = iDeptDao.selectAllDept();

        //获得总人数(edate时间点的在职总人数)
        List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll2(sdate,edate);
        List<PersonalInformation> personalInformationList2 = iPersonalInformationDao.selectAll3(sdate,edate);
        Integer num = personalInformationList1.size();
        if(personalInformationList2.size()>0){
            num = num - personalInformationList2.size();
        }
        paramMap.put("allNum",num);

        for (Dept dept:depts
             ) {
            HRManageCard hrManageCard = new HRManageCard();
            //获得部门名称
            hrManageCard.setDeptid(dept.getId());
            hrManageCard.setDepname(dept.getDepname());
            //获得部门的在职人数(edate时间点的在职人数)
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid2(dept.getId(),sdate,edate);
            List<PersonalInformation> personalList2 = iPersonalInformationDao.selectByDepid2(dept.getId(),sdate,edate);
            Integer ratio = personalInformationList.size();
            if(personalList2.size()>0){
                ratio = ratio - personalList2.size();
            }
            hrManageCard.setNum(ratio);
            //人数占比
            Double db = ratio.doubleValue()/num.doubleValue()*100;
            BigDecimal bg = new BigDecimal(db).setScale(2, RoundingMode.UP);
            hrManageCard.setRatio(bg.doubleValue() + "%");
            //获得部门相应的人员(edate时间点的在职人员)
            List<Map> users = new ArrayList<>();
            for (PersonalInformation per:personalInformationList
                 ) {
                HashMap<String, Object> map = new HashMap<>();
                User user = iUserDao.selectById(per.getUserid());
                map.put("id",user.getId());
                map.put("truename",user.getTruename());
                map.put("deptname",iDeptDao.selectDeptByDepid(per.getDepid()).getDepname());
                users.add(map);
            }
            hrManageCard.setUsers(users);
            hrManageCardList.add(hrManageCard);
        }
        paramMap.put("HRManageCards",hrManageCardList);
        return paramMap;
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
            return null;
        }
        PersonalInformation personalInformation;
        if (user!=null) {
            personalInformation = iPersonalInformationDao.selectByUserid(user.getId());
        } else {
            return null;
        }
        Dept dept = null;
        if (personalInformation!=null && personalInformation.getDepid()!=null) {
            dept = iDeptDao.selectDeptByDepid(personalInformation.getDepid());
        }else {
            return null;
        }
        return dept.getCompanyname();
    }
}
