package com.elex.oa.service.service_shiyun.impl;

import com.elex.oa.dao.dao_shiyun.IDeptDao;
import com.elex.oa.dao.dao_shiyun.IPersonalInformationDao;
import com.elex.oa.dao.dao_shiyun.IUserDao;
import com.elex.oa.entity.entity_shiyun.Dept;
import com.elex.oa.entity.entity_shiyun.HRManageCard;
import com.elex.oa.entity.entity_shiyun.PersonalInformation;
import com.elex.oa.entity.entity_shiyun.User;
import com.elex.oa.service.service_shiyun.IDeptService;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.util_shiyun.PageHelper;
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
     *@Description:���ݲ������ƻ�ò���
     *@Date: 15:29 2018\6\1 0001
     */
    @Override
    public Dept queryOneDepByDepname(String depname) {
        return iDeptDao.selectDeptByDeptname(depname);
    }

    /**
     *@Author:ShiYun;
     *@Description:���ݲ���code��ѯ������Ϣ
     *@Date: 10:20 2018\7\16 0016
     */
    @Override
    public Dept queryOneByDepcode(String depcode) {
        Dept dept = iDeptDao.selectDeptByDeptcode(depcode);
        return dept;
    }

    /**
     *@Author:ShiYun;
     *@Description:���ݲ���codeģ����ѯ������Ϣ
     *@Date: 17:08 2018\8\14 0014
     */
    @Override
    public List<Dept> queryDeptsByDepcode(String depcode) {
        List<Dept> depts = iDeptDao.selectDeptByDeptcode2(depcode);
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:���ݲ���ID��ò���
     *@Date: 15:30 2018\6\1 0001
     */
    @Override
    public Dept queryOneDepByDepid(Integer id) {
        return iDeptDao.selectDeptByDepid(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:������в���
     *@Date: 15:30 2018\6\1 0001
     */
    @Override
    public List<Dept> queryAllDepts() {
        List<Dept> depts = iDeptDao.selectAllDept();
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:�����ϼ�����ID��ѯ������Ϣ
     *@Date: 10:52 2018\4\16 0016
     */
    @Override
    public List<Dept> queryByParentId(Integer parentid) {
        List<Dept> depts = iDeptDao.selectByParentId(parentid);
        return depts;
    }

    /**
     *@Author:ShiYun;
     *@Description:��Ӳ�����Ϣ����������
     *@Date: 11:00 2018\4\23 0023
     */
    @Override
    public Integer addOne(Dept dept) {
        Integer integer = iDeptDao.insertOne(dept);
        return dept.getId();
    }

    /**
     *@Author:ShiYun;
     *@Description:�޸Ĳ�����Ϣ
     *@Date: 9:58 2018\5\2 0002
     */
    @Override
    public void modifyOne(Dept dept) {
        iDeptDao.updateOne(dept);
    }

    /**
     *@Author:ShiYun;
     *@Description:��Ҫ���ò����µ�����Ա���Ĳ�����Ϣ��Ϊ�޲��ţ��ٸ��ݲ���IDɾ��������Ϣ
     *@Date: 14:07 2018\5\2 0002
     */
    @Override
    public void removeOne(Integer id) {
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid(id);
        PersonalInformation personalInformation = new PersonalInformation();
        //�������Ա����Ӧ������Ϣ
        for (PersonalInformation personalinformation:personalInformationList
             ) {
            personalInformation.setId(personalinformation.getId());
            personalInformation.setDepid(personalinformation.getDepid());
            iPersonalInformationDao.updateDeptinformation(personalInformation);
        }
        //��ɾ����Ӧ�Ĳ���
        iDeptDao.deleteOne(id);
    }

    /**
     *@Author:ShiYun;
     *@Description:ɾ���û�ʱ�޸Ĳ�����Ϣ
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
     *@Description:�޸Ĳ���ʱ�����������ŵ���Ϣ��Ϣ��
     *@Date: 10:33 2018\6\20 0020
     */
    @Override
    public void modifyByDeptidAndOtherinformation(Dept dept){
        iDeptDao.updateByDeleteUser(dept);
    }

    /**
     *@Author:ShiYun;
     *@Description:���ݲ���ID��ò������ơ�������������
     *@Date: 15:31 2018\6\1 0001
     */
    @Override
    public HRManageCard getParamMap1(Integer depid) {
        HRManageCard hrManageCard = new HRManageCard();
        //��ò�������
        Dept dept;
        if (depid!=null) {
            dept = iDeptDao.selectDeptByDepid(depid);
        } else {
            dept = iDeptDao.selectDeptByDeptname("���ղ�������Ƽ��ɷ����޹�˾");
        }
        hrManageCard.setDepname(dept.getDepname());
        //��ò���ID
        hrManageCard.setDeptid(dept.getId());
        //����ϼ�����ID
        hrManageCard.setParentid(dept.getParentdepid());
        //��ò��ŵ���Ա
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
        //����Ӳ���
        List<Dept> depts = iDeptDao.selectByParentId(dept.getId());
        hrManageCard.setChildDepts(depts);
        return hrManageCard;
    }

    /**
     *@Author:ShiYun;
     *@Description:��ְ�����¹�����
     *@Date: 11:53 2018\6\28 0028
     */
    @Override
    public Object getHRManageCard(String sdate,String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            HashMap<String, Object> paramMap = new HashMap<>();
            List<HRManageCard> hrManageCardList = new ArrayList<>();
            List<Dept> depts = iDeptDao.selectAllDept();

            //���������(edateʱ������ְ������)
            Integer num;
            Resp resp2 = (Resp) this.getHRManageCard2(5, 1, sdate, edate);
            if(resp2.getBody()!=null){
                PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp2.getBody();
                num = pageHelper2.getTotal();
                paramMap.put("allNum",num);
            }else {
                return resp2;
            }

            //�����ְ������(edateʱ������ְ������)
            Resp resp3 = (Resp) this.getHRManageCard3(5, 1, sdate, edate);
            if(resp3.getBody()!=null){
                PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp3.getBody();
                paramMap.put("intoNum",pageHelper2.getTotal());
            }else {
                return resp3;
            }

            //�����ְ������(edateʱ������ְ������)
            Resp resp4 = (Resp) this.getHRManageCard4(5, 1, sdate, edate);
            if(resp4.getBody()!=null){
                PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp4.getBody();
                paramMap.put("outNum",pageHelper2.getTotal());
            }else {
                return resp4;
            }

            for (Dept dept:depts
                 ) {
                HRManageCard hrManageCard = new HRManageCard();

                //��ò�������
                hrManageCard.setDeptid(dept.getId());
                hrManageCard.setDepname(dept.getDepname());

                //������ڲ�����ְ����(edateʱ������ְ������)(ע��ת����Ա����Ӱ�죬ע���������ǰ�ڰ汾�ݲ�����)
                Integer ratio;
                Resp resp5 = (Resp) this.getHRManageCard5(5, 1, dept.getId(), sdate, edate);
                if(resp5.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp5.getBody();
                    ratio = pageHelper2.getTotal();
                    hrManageCard.setNum(ratio);
                }else {
                    return resp5;
                }

                //����ռ��
                System.out.println("ratio:"+ratio);
                System.out.println("num:"+num);
                Double db = ratio.doubleValue()/num.doubleValue()*100;
                System.out.println("db:"+db);
                BigDecimal bg = new BigDecimal(db).setScale(2, RoundingMode.UP);
                hrManageCard.setRatio(bg.doubleValue() + "%");

                //������ڲ�����ְ����(edateʱ������ְ������)(ע��ת����Ա����Ӱ�죬ע���������ǰ�ڰ汾�ݲ�����)
                Resp resp6 = (Resp) this.getHRManageCard6(5, 1, dept.getId(), sdate, edate);
                if(resp6.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp6.getBody();
                    hrManageCard.setIntoNum(pageHelper2.getTotal());
                }else {
                    return resp6;
                }

                //������ڲ�����ְ����(edateʱ������ְ������)(ע��ת����Ա����Ӱ�죬ע���������ǰ�ڰ汾�ݲ�����)
                Resp resp7 = (Resp) this.getHRManageCard7(5, 1, dept.getId(), sdate, edate);
                if(resp7.getBody()!=null){
                    PageHelper<PersonalInformation> pageHelper2 = (PageHelper<PersonalInformation>)resp7.getBody();
                    hrManageCard.setOutNum(pageHelper2.getTotal());
                }else {
                    return resp7;
                }

                //��ò�����Ӧ����Ա(edateʱ������ְ��Ա)
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
            return RespUtil.successResp("205","���سɹ���",paramMap);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }
    //����ʱ���������ʱ��ĩ�����Ĺ���
    private Map<String,String> getTwoDate(String sdate,String edate){
        HashMap<String, String> map = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String curDate = sdf.format(date);
        Boolean d1 = sdate==null || sdate.equals("");
        Boolean d2 = edate==null || edate.equals("");
        //1.sdate=null,  edate=null=>��Ϊ��ǰʱ��
        if(d1 && d2){
            sdate = edate =curDate;
        }
        //2.sdate!=null, edate=null=>�ޣ�sdate<=��ǰʱ��
        //                           �᣺edate=sdate
        if(!d1 && d2){
            if(sdate.compareTo(curDate)<=0){
                edate=sdate;
            }else {
                return null;
            }
        }
        //3.sdate=null, edate!=null=>�ޣ�edate<=��ǰʱ��
        //                           �᣺sdate=edate
        if(d1 && !d2){
            if(edate.compareTo(curDate)<=0){
                sdate = edate;
            }else {
                return null;
            }
        }
        //4.sdate!=null,edate!=null=>�ޣ�sdate<=edate<=��ǰʱ��
        //                           �᣺sdate,edate
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
     *@Description:���������(edateʱ������ְ������)
     *@Date: 10:13 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard2(Integer rows, Integer page, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList1 = iPersonalInformationDao.selectAll2(null,edate);//ʱ��ڵ�edateǰ����ְ��Ա
            System.out.println("personalInformationList1.size():"+personalInformationList1.size());
            List<PersonalInformation> personalInformationList2 = iPersonalInformationDao.selectAll3(null,edate);//ʱ��ڵ�edateǰ����ְ��Ա
            List<PersonalInformation> personalInformationList = new ArrayList<>();
            if (personalInformationList2.size()>0) {
                for (PersonalInformation per:personalInformationList1
                     ) {
                    if(!personalInformationList2.contains(per)){
                        personalInformationList.add(per);
                    }
                }
            }else {
                personalInformationList = personalInformationList1;
            }
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page,rows,personalInformationList);
            return RespUtil.successResp("205","�ύ�ɹ���",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:�����ְ������(edateʱ������ְ������)
     *@Date: 10:13 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard3(Integer rows, Integer page, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectAll2(sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","�ύ�ɹ���",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:�����ְ������(edateʱ������ְ������)
     *@Date: 10:14 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard4(Integer rows, Integer page, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectAll3(sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","�ύ�ɹ���",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:������ڲ�����ְ����(edateʱ������ְ������)(ע��ת����Ա����Ӱ�죬ע���������ǰ�ڰ汾�ݲ�����)
     *@Date: 10:14 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard5(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
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
            return RespUtil.successResp("205","�ύ�ɹ���",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:������ڲ�����ְ����(edateʱ������ְ������)(ע��ת����Ա����Ӱ�죬ע���������ǰ�ڰ汾�ݲ�����)
     *@Date: 10:15 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard6(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid2(depid, sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","�ύ�ɹ���",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:������ڲ�����ְ����(edateʱ������ְ������)(ע��ת����Ա����Ӱ�죬ע���������ǰ�ڰ汾�ݲ�����)
     *@Date: 10:15 2018\8\15 0015
     */
    @Override
    public Object getHRManageCard7(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        try {
            Map<String, String> twoDate = this.getTwoDate(sdate, edate);
            if(twoDate==null){
                return RespUtil.successResp("505","ʱ��ѡ�����",null);
            }
            sdate = twoDate.get("sdate");
            edate = twoDate.get("edate");
            List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid3(depid, sdate, edate);
            PageHelper<PersonalInformation> pageHelper = new PageHelper<>(page, rows, personalInformationList);
            return RespUtil.successResp("205","�ύ�ɹ���",pageHelper);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("405","ϵͳ����æ�����Ժ����ԣ�",null);
        }
    }

    @Override
    public Object getHRManageCard2(Integer rows, Integer page, String sdate, String edate) {
        return null;
    }

    @Override
    public Object getHRManageCard3(Integer rows, Integer page, String sdate, String edate) {
        return null;
    }

    @Override
    public Object getHRManageCard4(Integer rows, Integer page, String sdate, String edate) {
        return null;
    }

    @Override
    public Object getHRManageCard5(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        return null;
    }

    @Override
    public Object getHRManageCard6(Integer rows, Integer page, Integer depid, String sdate, String edate) {
        return null;
    }

    /**
     *@Author:ShiYun;
     *@Description:forGXF������������ѯ��Ӧ�Ĺ�˾���ƣ�
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
        /*return dept.getCompanyname();*/
        return dept.getDepcode().substring(0,2);
    }
}
