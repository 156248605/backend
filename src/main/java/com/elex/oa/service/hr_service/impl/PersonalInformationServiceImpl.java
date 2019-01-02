package com.elex.oa.service.hr_service.impl;

import com.elex.oa.dao.hr.*;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.IPersonalInformationService;
import com.elex.oa.util.hr_util.HrUtilsTemp;
import com.elex.oa.util.hr_util.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:人事信息
 * @Date:Created in  10:00 2018\4\8 0008
 * @Modify By:
 */
@Service
public class PersonalInformationServiceImpl implements IPersonalInformationService {

    @Resource
    IPersonalInformationDao iPersonalInformationDao;
    @Resource
    IBaseInformationDao iBaseInformationDao;
    @Resource
    IPerandpostrsDao iPerandpostrsDao;
    @Resource
    IDeptDao iDeptDao;
    @Resource
    IPostDao iPostDao;
    @Resource
    IUserDao iUserDao;
    @Resource
    IHRsetDao ihRsetDao;
    @Resource
    HrUtilsTemp hrUtilsTemp;
    @Resource
    IManageInformationDao iManageInformationDao;
    @Resource
    ICostInformationDao iCostInformationDao;
    @Resource
    IOtherInformationDao iOtherInformationDao;
    @Resource
    IChangeInformaionDao iChangeInformaionDao;

    /**
     *@Author:ShiYun;
     *@Description:查询人事信息
     *@Date: 10:09 2018\4\8 0008
     */
    @Override
    public PageInfo<PersonalInformation> queryPIs(Map<String, Object> paramMap) throws ParseException {

        PersonalInformation personalInformation = (PersonalInformation) paramMap.get("entity");
        if (personalInformation.getAge()!=null && !"".equals(personalInformation.getAge())) {
            HashMap<String, String> birdayByAge = IDcodeUtil.getBirdayByAge(personalInformation.getAge());
            personalInformation.setSbir(birdayByAge.get("sbir"));
            personalInformation.setEbir(birdayByAge.get("ebir"));
        }//年龄转换成出生日期
        if(personalInformation.getAges()!=null && personalInformation.getAges().size()!=0){
            Map<String,String> ageMap = new HashMap<>();
            for (String age:personalInformation.getAges()
                 ) {
                HashMap<String, String> birdayByAge = IDcodeUtil.getBirdayByAge(age);
                ageMap.put(birdayByAge.get("sbir"),birdayByAge.get("ebir"));
            }
            personalInformation.setAgeMap(ageMap);
        }//年龄数组转换成出生日期
        if (personalInformation.getWorkingage()!=null && !"".equals(personalInformation.getWorkingage())) {
            HashMap<String, String> fwtByWorkingage = IDcodeUtil.getFwtByWorkingage(personalInformation.getWorkingage());
            personalInformation.setSfwt(fwtByWorkingage.get("sfwt"));
            personalInformation.setEfwt(fwtByWorkingage.get("efwt"));
        }//工龄转换成首次工作时间
        if(personalInformation.getWorkingages()!=null && personalInformation.getWorkingages().size()!=0){
            Map<String,String> workingageMap = new HashMap<>();
            for (String workingage:personalInformation.getWorkingages()
                 ) {
                HashMap<String, String> fwtByWorkingage = IDcodeUtil.getBirdayByAge(workingage);
                workingageMap.put(fwtByWorkingage.get("sbir"),fwtByWorkingage.get("ebir"));
            }
            personalInformation.setWorkingageMap(workingageMap);
        }//工龄数组转换成首次工作时间
        if (personalInformation!=null) {
            List<Integer> baseinformationids = new ArrayList<>();
            List<BaseInformation> baseInformations = iBaseInformationDao.selectByConditions(personalInformation);//先进行基本信息的查询
            for(int i=0;i<baseInformations.size();i++){
                baseinformationids.add(baseInformations.get(i).getId());
            }
            if (baseinformationids.size()!=0) {
                personalInformation.setBaseinformationids(baseinformationids);
            }else {
                return null;
            }//再进行基本信息的id集合提取

            if (StringUtils.isNotEmpty(personalInformation.getPostname()) || (null!=personalInformation.getPostnameList() && personalInformation.getPostnameList().size() > 0)) {
                if("不包含".equals(personalInformation.getPostnamevalue())){
                    List<PerAndPostRs> perAndPostRs = iPerandpostrsDao.selectByConditions2(personalInformation);
                    //这里查出来的是包含的部分，到时候只需not in 一下这部分即可
                    ArrayList<Integer> integers = new ArrayList<>();
                    for(PerAndPostRs pp:perAndPostRs){
                        integers.add(pp.getPerid());
                    }
                    if (integers.size()!=0) {
                        personalInformation.setPpids(integers);
                    }
                }

                List<Integer> perids = new ArrayList<>();
                List<PerAndPostRs> perAndPostRsList = iPerandpostrsDao.selectByConditions(personalInformation);
                for(int i = 0;i<perAndPostRsList.size();i++){
                    perids.add(perAndPostRsList.get(i).getPerid());
                }
                if (perids.size()!=0) {
                    personalInformation.setPerids(perids);
                }
            }//将符合岗位条件的perid集合提取出来
        }
        Integer pageNum = Integer.parseInt(paramMap.get("pageNum").toString());
        Integer pageSize = Integer.parseInt(paramMap.get("pageSize").toString());
        com.github.pagehelper.PageHelper.startPage(pageNum,pageSize);
        List<PersonalInformation> list = iPersonalInformationDao.selectByConditions(personalInformation);
        return new PageInfo<PersonalInformation>(list);
    }

    @Override
    public Map<String, List<String>> getParamsForFirstpage() {
        Map<String, List<String>> resp = new HashMap<>();
        //get depnames
        List<String> depnames = iDeptDao.selectAllDepnames();
        resp.put("depnames",depnames);
        //get postnames
        List<String> postnames = iPostDao.getAllPostnames();
        resp.put("postnames",postnames);
        //get truenames and usernames
        List<User> userList = iUserDao.selectAll();
        List<String> truenames = new ArrayList<>();
        List<String> usernames = new ArrayList<>();
        List<String> employeenumbers = new ArrayList<>();
        for (User u:userList
             ) {
            truenames.add(u.getTruename());
            usernames.add(u.getUsername());
            employeenumbers.add(u.getEmployeenumber());
        }
        resp.put("truenames",truenames);
        resp.put("usernames",usernames);
        resp.put("employeenumbers",employeenumbers);
        //get hrsets
        List<HRset> hRsetList = ihRsetDao.selectAll();
        List<String> races = new ArrayList<>();
        List<String> childs = new ArrayList<>();
        List<String> zzmms = new ArrayList<>();
        List<String> zgxls = new ArrayList<>();
        List<String> byyxs = new ArrayList<>();
        List<String> pyfss = new ArrayList<>();
        List<String> flas = new ArrayList<>();
        List<String> sxzys = new ArrayList<>();
        List<String> posttitles = new ArrayList<>();
        List<String> zyzstypes = new ArrayList<>();
        List<String> zyzsnames = new ArrayList<>();
        List<String> telphones = new ArrayList<>();
        List<String> parentcompanys = new ArrayList<>();
        for (HRset h:hRsetList
             ) {
            if(h.getDatatype().equals("race"))races.add(h.getDatavalue());
            if(h.getDatatype().equals("children"))childs.add(h.getDatavalue());
            if(h.getDatatype().equals("zzmm"))zzmms.add(h.getDatavalue());
            if(h.getDatatype().equals("zgxl"))zgxls.add(h.getDatavalue());
            if(h.getDatatype().equals("byyx"))byyxs.add(h.getDatavalue());
            if(h.getDatatype().equals("pyfs"))pyfss.add(h.getDatavalue());
            if(h.getDatatype().equals("fla"))flas.add(h.getDatavalue());
            if(h.getDatatype().equals("sxzy"))sxzys.add(h.getDatavalue());
            if(h.getDatatype().equals("posttitle"))posttitles.add(h.getDatavalue());
            if(h.getDatatype().equals("zyzstype"))zyzstypes.add(h.getDatavalue());
            if(h.getDatatype().equals("zyzsname"))zyzsnames.add(h.getDatavalue());
            if(h.getDatatype().equals("telphone"))telphones.add(h.getDatavalue());
            if(h.getDatatype().equals("parentcompany"))parentcompanys.add(h.getDatavalue());
        }
        resp.put("races",races);
        resp.put("childs",childs);
        resp.put("zzmms",zzmms);
        resp.put("zgxls",zgxls);
        resp.put("byyxs",byyxs);
        resp.put("pyfss",pyfss);
        resp.put("flas",flas);
        resp.put("sxzys",sxzys);
        resp.put("posttitles",posttitles);
        resp.put("zyzstypes",zyzstypes);
        resp.put("zyzsnames",zyzsnames);
        resp.put("telphones",telphones);
        resp.put("parentcompanys",parentcompanys);
        //get mobilephones\englishnames\idcodes\ages\workingages
        List<String> mobilephones = iPersonalInformationDao.selectAllmobilephones();
        List<String> englishnames = iPersonalInformationDao.selectAllenglishnames();
        List<String> idcodes = iPersonalInformationDao.selectAllidcodes();
        List<String> ages = iPersonalInformationDao.selectAllages();
        List<String> workingages = iPersonalInformationDao.selectAllworkingages();
        resp.put("mobilephones",mobilephones);
        resp.put("englishnames",englishnames);
        resp.put("idcodes",idcodes);
        resp.put("ages",ages);
        resp.put("workingages",workingages);
        return resp;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息（不包括离职的）
     *@Date: 18:48 2018\4\10 0010
     */
    @Override
    public PersonalInformation queryOneById(Integer id) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectById(id);
        return personalInformation;
    }
    //包括离职的
    @Override
    public PersonalInformation queryOneById2(Integer id) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectById2(id);
        return personalInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据账号ID查询人事信息
     *@Date: 18:49 2018\4\10 0010
     */
    @Override
    public PersonalInformation queryOneByUserid(Integer userid) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(userid);
        return personalInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息
     *@Date: 18:49 2018\4\10 0010
     */
    @Override
    public Integer saveOne(PersonalInformation personalInformation) {
        Integer personalInformationId = iPersonalInformationDao.insertOne(dosomethingBeforeSaveone(personalInformation));
        return personalInformation.getId();
    }



    /**
     *@Author:ShiYun;
     *@Description:修改人事信息
     *@Date: 16:31 2018\4\11 0011
     */
    @Override
    public void modifyOne(PersonalInformation personalInformation) {
        iPersonalInformationDao.updateOne(dosomethingBeforeSaveone(personalInformation));
    }

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息（不分页）
     *@Date: 16:30 2018\4\13 0013
     */
    @Override
    public List<PersonalInformation> queryPIs(PersonalInformation personalInformation) {
        List<PersonalInformation> personalInformations = iPersonalInformationDao.selectByConditions(personalInformation);
        return personalInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID查询人事信息
     *@Date: 16:22 2018\5\28 0028
     */
    public List<PersonalInformation> queryByDepid(Integer depid){
        List<PersonalInformation> personalInformationList = iPersonalInformationDao.selectByDepid(depid);
        return personalInformationList;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据员工号查询员工
     *@Date: 10:02 2018\8\9 0009
     */
    @Override
    public PersonalInformation queryByEmployeenumber(String employeenumber) {
        PersonalInformation personalInformation = iPersonalInformationDao.selectByEmployeenumber(employeenumber);
        return personalInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除人事主体信息
     *@Date: 16:10 2018\8\20 0020
     */
    @Override
    public void removeOne(Integer perid) {
        iPersonalInformationDao.deleteById(perid);
    }

    @Override
    public void removeAll() {
        iPersonalInformationDao.deleteAll();
    }

    /**
     *@Author:ShiYun;
     *@Description:查询所有人事信息（不分页）
     *@Date: 17:28 2018\4\18 0018
     */
    @Override
    public List<PersonalInformation> queryAllByNull() {
        List<PersonalInformation> personalInformations = iPersonalInformationDao.selectAll();
        return personalInformations;
    }

    @Override
    public PersonalInformation queryPersonalInformationById(Integer personalInformationId) {
        if(null==personalInformationId)return null;
        //获取人事粗略信息（离职人员除外）
        PersonalInformation personalInformation = iPersonalInformationDao.selectById(personalInformationId);
        if(null==personalInformation)return null;
        //获取人事详细信息
        personalInformation = getDetailPersonalinformationByCursorPersonalinformation(personalInformation);
        return personalInformation;
    }

    @Override
    public Map<String, Object> updateManageInformation(PersonalInformation personalInformation, String transactorusername) {
        if(null==personalInformation)return null;
        if(StringUtils.isBlank(transactorusername))return null;
        Map<String,Object> respMap = null;
        PersonalInformation personalInformationTemp = iPersonalInformationDao.selectByUserid(personalInformation.getUserid());
        Integer perid = personalInformationTemp.getId();//此处可能有空指针
        personalInformation.setId(perid);
        personalInformation.setManageinformationid(personalInformationTemp.getManageinformationid());
        //获得原始的管理信息
        Map<String, String> oldManageinformation = getOldManageinformationByUserid(personalInformation.getUserid());
        //获得当前的管理信息
        Map<String, String> newManageinformation = getNewManageinformationByPersonalinformation(personalInformation);
        //判断部门是否相同并添加日志
        Boolean isUpdate = getIsEqualForManageinformation(personalInformation.getUserid(), oldManageinformation, newManageinformation, transactorusername);
        //修改管理信息：部门、岗位、管理
        if (isUpdate) {
            iPersonalInformationDao.updateOne(personalInformation);//更新部门信息
            updatePostandpersonalinformationByPostids(personalInformation.getPostids(),perid);//更新岗位信息
            iManageInformationDao.updateOne(getManageinformationByPersonalinformation(personalInformation));//更新管理信息
            //下面的数据是为了同步赵宏钢的人事信息所准备的
            respMap = getSynchronizeMapByUserid(personalInformation.getUserid(),personalInformation.getPostids());
        }
        return respMap;
    }

    //获取UpdateManage的返回值
    private Map<String,Object> getSynchronizeMapByUserid(Integer userid,List<Integer> postids){
        if(null==userid)return null;
        User user = iUserDao.selectById(userid);
        if(null==user)return null;
        Map<String,Object> respMap = new HashMap<>();
        respMap.put("username", user.getUsername());
        respMap.put("isactive", user.getIsactive());
        respMap.put("truename", user.getTruename());
        respMap.put("postids", postids);
        return respMap;
    }

    //将人事信息装换成管理信息
    private ManageInformation getManageinformationByPersonalinformation(PersonalInformation personalInformation){
        if(null==personalInformation)return null;
        ManageInformation manageInformation = new ManageInformation();
        manageInformation.setId(personalInformation.getManageinformationid());
        manageInformation.setPostlevelid(hrUtilsTemp.getHrsetidByDatavalue("postlevel",personalInformation.getPostlevel()));
        manageInformation.setEmployeetypeid(hrUtilsTemp.getHrsetidByDatavalue("employeetype",personalInformation.getEmployeetype()));
        manageInformation.setEntrydate(personalInformation.getEntrydate());
        manageInformation.setZhuanzhengdate(personalInformation.getZhuanzhengdate());
        return manageInformation;
    }

    //根据postids更新人事岗位中间表信息
    private void updatePostandpersonalinformationByPostids(List<Integer> postids,Integer perid){
        if(null==postids || postids.size()==0)return;
        //先将原岗位删除
        iPerandpostrsDao.deleteByPerid(perid);
        for (Integer postid:postids
             ) {
            iPerandpostrsDao.insertOne(new PerAndPostRs(perid,postid));
        }
    }

    //判断两个管理信息并添加相应的日志
    private Boolean getIsEqualForManageinformation(Integer changeduserid,Map<String, String> oldManageinformation,Map<String, String> newManageinformation,String transactorusername){
        if(null==oldManageinformation || null==newManageinformation)return false;
        Boolean isUpdate = false;
        //判断部门信息并添加相应的日志
        Boolean isEqual = getIsEqualByBeforeinfoAndAfterinfo(changeduserid, oldManageinformation.get("depinfo"), newManageinformation.get("depinfo"), transactorusername, "部门信息");
        if(isEqual)isUpdate=true;
        //判断岗位信息并添加相应的日志
        isEqual = getIsEqualByBeforeinfoAndAfterinfo(changeduserid, oldManageinformation.get("postinfo"), newManageinformation.get("postinfo"), transactorusername, "岗位信息");
        if(isEqual)isUpdate=true;
        //判断级别信息并添加相应的日志
        isEqual = getIsEqualByBeforeinfoAndAfterinfo(changeduserid, oldManageinformation.get("postlevel"), newManageinformation.get("postlevel"), transactorusername, "级别");
        if(isEqual)isUpdate=true;
        //判断员工类型信息并添加相应的日志
        isEqual = getIsEqualByBeforeinfoAndAfterinfo(changeduserid, oldManageinformation.get("employeetype"), newManageinformation.get("employeetype"), transactorusername, "员工类型");
        if(isEqual)isUpdate=true;
        //判断入职时间并添加相应的日志
        isEqual = getIsEqualByBeforeinfoAndAfterinfo(changeduserid, oldManageinformation.get("entrydate"), newManageinformation.get("entrydate"), transactorusername, "入职时间");
        if(isEqual)isUpdate=true;
        //判断转正时间并添加相应的日志
        isEqual = getIsEqualByBeforeinfoAndAfterinfo(changeduserid, oldManageinformation.get("zhuanzhengdate"), newManageinformation.get("zhuanzhengdate"), transactorusername, "转正时间");
        if(isEqual)isUpdate=true;
        return isUpdate;
    }

    //根据Personalinformation获得新的Mange信息
    private Map<String,String> getNewManageinformationByPersonalinformation(PersonalInformation personalInformation){
        if(null==personalInformation)return null;
        Map<String,String> respMap = new HashMap<>();
        //获得部门信息
        Dept dept = iDeptDao.selectDeptByDepid(personalInformation.getDepid());
        if(null!=dept){
            String depinfo = dept.getDepname()+"--"+dept.getId();
            respMap.put("depinfo",depinfo);
        }
        //获得岗位信息
        List<Integer> postids = personalInformation.getPostids();
        List<Post> postList = getPostListByPostids(postids);
        List<String> postListInfo = getPostListInfoByPostList(postList);
        String postinfo = IDcodeUtil.getArrayToString(postListInfo, ";");
        if(null!=postinfo){
            respMap.put("postinfo",postinfo);
        }
        //获得级别、员工类型、入职时间、转正时间
        respMap.put("postlevel",personalInformation.getPostlevel());
        respMap.put("employeetype",personalInformation.getEmployeetype());
        respMap.put("entrydate",personalInformation.getEntrydate());
        respMap.put("zhuanzhengdate",personalInformation.getZhuanzhengdate());
        return respMap;
    }

    //根据postids获得PostList
    private List<Post> getPostListByPostids(List<Integer> postids){
        if(null==postids)return null;
        List<Post> postList = new ArrayList<>();
        for (Integer postid:postids
             ) {
            Post post = iPostDao.selectPostByPostid(postid);
            postList.add(post);
        }
        return postList;
    }

    //根据账号ID获得旧的管理信息
    private Map<String,String> getOldManageinformationByUserid(Integer userid){
        if(null==userid)return null;
        Map<String,String> respMap = new HashMap<>();
        //获得部门信息
        PersonalInformation personalInformation = iPersonalInformationDao.selectByUserid(userid);
        if(null==personalInformation)return null;
        Dept dept = iDeptDao.selectDeptByDepid(personalInformation.getDepid());
        if(null!=dept){
            String depinfo = dept.getDepname()+"--"+dept.getId();
            respMap.put("depinfo",depinfo);
        }
        //获得岗位信息
        List<Post> postList = getPostListByPersonalinformationid(personalInformation.getId());
        List<String> postListInfo = getPostListInfoByPostList(postList);
        String postinfo = IDcodeUtil.getArrayToString(postListInfo, ";");
        if(null!=postinfo){
            respMap.put("postinfo",postinfo);
        }
        //获得管理信息
        ManageInformation manageInformation = iManageInformationDao.selectById(personalInformation.getManageinformationid());
        //获得级别、员工类型、入职时间、转正时间
        respMap.put("postlevel",hrUtilsTemp.getDatavalueByHrsetid(manageInformation.getPostlevelid()));
        respMap.put("employeetype",hrUtilsTemp.getDatavalueByHrsetid(manageInformation.getEmployeetypeid()));
        respMap.put("entrydate",manageInformation.getEntrydate());
        respMap.put("zhuanzhengdate",manageInformation.getZhuanzhengdate());
        return respMap;
    }

    //获得postListInfo
    private List<String> getPostListInfoByPostList(List<Post> postList){
        if(null==postList)return null;
        List<String> respString = new ArrayList<>();
        for (Post p:postList
             ) {
            String str = p.getPostname()+"--"+p.getId();
            respString.add(str);
        }
        return respString;
    }

    //判断两个字段是否相同并添加相应的日志
    private Boolean getIsEqualByBeforeinfoAndAfterinfo(Integer changeduserid,String beforeinformation,String afterinformation,String transactorusername,String changeinformationName){
        if(StringUtils.isBlank(afterinformation))return false;
        if(afterinformation.equals(beforeinformation))return false;
        ChangeInformation changeInformation = new ChangeInformation();
        changeInformation.setChangeduserid(changeduserid);
        changeInformation.setChangeinformation(changeinformationName);
        changeInformation.setBeforeinformation(beforeinformation);
        changeInformation.setAfterinformation(afterinformation);
        changeInformation.setChangedate(hrUtilsTemp.getDateStringByTimeMillis(System.currentTimeMillis()));
        changeInformation.setTransactoruserid(hrUtilsTemp.getUseridByUsername(transactorusername));
        changeInformation.setChangereason("业务需要");
        iChangeInformaionDao.insertOne(changeInformation);
        return true;
    }

    //根据人事粗略信息获取人事详细信息
    private PersonalInformation getDetailPersonalinformationByCursorPersonalinformation(PersonalInformation personalInformation){
        //获取账号信息
        User user = iUserDao.selectById(personalInformation.getUserid());
        personalInformation = setUserByPersonalinformation(personalInformation,user);
        //获取部门信息
        Dept dept = iDeptDao.selectDeptByDepid(personalInformation.getDepid());
        personalInformation = setDepByPersonalinformation(personalInformation,dept);
        //获取岗位信息
        List<Post> postList = getPostListByPersonalinformationid(personalInformation.getId());
        personalInformation.setPostList(postList);
        personalInformation.setPostids(getPostidsByPostList(postList));
        //获取办公电话
        personalInformation.setTelphone(hrUtilsTemp.getDatavalueByHrsetid(personalInformation.getTelphoneid()));
        //获取人事基本信息
        BaseInformation baseInformation = iBaseInformationDao.selectById(personalInformation.getBaseinformationid());
        personalInformation = setBaseinformationByPersonalinformation(personalInformation,baseInformation);
        //获取管理信息
        ManageInformation manageInformation = iManageInformationDao.selectById(personalInformation.getManageinformationid());
        personalInformation = setManageinformationByPersonalinformation(personalInformation,manageInformation);
        //获取成本信息
        CostInformation costInformation = iCostInformationDao.selectById(personalInformation.getCostinformationid());
        personalInformation = setCostinformationByPersonalinformation(personalInformation,costInformation);
        //获取其它信息
        OtherInformation otherInformation = iOtherInformationDao.selectById(personalInformation.getOtherinformationid());
        personalInformation = setOtherinformationByPersonalinformation(personalInformation,otherInformation);
        return personalInformation;
    }

    //根据PostList获取postids
    private List<Integer> getPostidsByPostList(List<Post> postList){
        if(null==postList)return null;
        List<Integer> postids = new ArrayList<>();
        for (Post p:postList
             ) {
            postids.add(p.getId());
        }
        return postids;
    }

    //根据人事ID查询岗位列表
    private List<Post> getPostListByPersonalinformationid(Integer personalinformationid) {
        if(null == personalinformationid)return null;
        List<PerAndPostRs> perAndPostRsList = iPerandpostrsDao.selectPostidsByPerid(personalinformationid);
        if(null==perAndPostRsList)return null;
        List<Post> postList = new ArrayList<>();
        for (PerAndPostRs perAndPostRs:perAndPostRsList
             ) {
            Post post = iPostDao.selectPostByPostid(perAndPostRs.getPostid());
            //获得岗位详细信息
            post = getDetailPostByCursoryPost(post);
            postList.add(post);
        }
        return postList;
    }

    //根据岗位粗略信息获得岗位详细信息
    private Post getDetailPostByCursoryPost(Post post){
        if(null==post)return null;
        //获得职级
        post.setPostrank(hrUtilsTemp.getDatavalueByHrsetid(post.getPostrankid()));
        return post;
    }

    //将其它信息塞入人事信息
    private PersonalInformation setOtherinformationByPersonalinformation(PersonalInformation personalInformation,OtherInformation otherInformation){
        if(null==otherInformation)return personalInformation;
        personalInformation.setPrivateemail(otherInformation.getPrivateemail());
        personalInformation.setCompanyemail(otherInformation.getCompanyemail());
        personalInformation.setEmergencycontract(otherInformation.getEmergencycontract());
        personalInformation.setEmergencyrp(hrUtilsTemp.getDatavalueByHrsetid(otherInformation.getEmergencyrpid()));
        personalInformation.setEmergencyphone(otherInformation.getEmergencyphone());
        personalInformation.setAddress(otherInformation.getAddress());
        personalInformation.setRemark(otherInformation.getRemark());
        return personalInformation;
    }

    //将成本信息塞入人事信息
    private PersonalInformation setCostinformationByPersonalinformation(PersonalInformation personalInformation,CostInformation costInformation){
        if(null == costInformation)return personalInformation;
        personalInformation.setSalary(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getSalarystandardid()));
        personalInformation.setSsb(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getSsbid()));
        personalInformation.setSsbgscd(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getSsbgscdid()));
        personalInformation.setSsbgrcd(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getSsbgrcdid()));
        personalInformation.setGjj(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getGjjid()));
        personalInformation.setGjjgscd(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getGjjgscdid()));
        personalInformation.setGjjgrcd(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getGjjgrcdid()));
        personalInformation.setKhh(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getKhhid()));
        personalInformation.setSbjnd(hrUtilsTemp.getDatavalueByHrsetid(costInformation.getSbjndid()));
        personalInformation.setSalaryaccount(costInformation.getSalaryaccount());
        personalInformation.setSbcode(costInformation.getSbcode());
        personalInformation.setGjjcode(costInformation.getGjjcode());
        return personalInformation;
    }

    //将管理信息塞入人事信息
    private PersonalInformation setManageinformationByPersonalinformation(PersonalInformation personalInformation,ManageInformation manageInformation){
        if(null==manageInformation)return personalInformation;
        personalInformation.setPostlevel(hrUtilsTemp.getDatavalueByHrsetid(manageInformation.getPostlevelid()));
        personalInformation.setEmployeetype(hrUtilsTemp.getDatavalueByHrsetid(manageInformation.getEmployeetypeid()));
        personalInformation.setEntrydate(manageInformation.getEntrydate());
        personalInformation.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
        return personalInformation;
    }

    //将基本信息塞入人事信息
    private PersonalInformation setBaseinformationByPersonalinformation(PersonalInformation personalInformation,BaseInformation baseInformation){
        if(null==baseInformation)return personalInformation;
        personalInformation.setUserphoto(baseInformation.getUserphoto());
        personalInformation.setIdphoto1(baseInformation.getIdphoto1());
        personalInformation.setIdphoto2(baseInformation.getIdphoto2());
        personalInformation.setEnglishname(baseInformation.getEnglishname());
        personalInformation.setIdcode(baseInformation.getIdcode());
        personalInformation.setBirthday(baseInformation.getBirthday());
        personalInformation.setConstellation(baseInformation.getConstellation());
        personalInformation.setChinesecs(baseInformation.getChinesecs());
        personalInformation.setRace(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getRaceid()));
        personalInformation.setMarriage(baseInformation.getMarriage());
        personalInformation.setChildren(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getChildrenid()));
        personalInformation.setZzmm(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getZzmmid()));
        personalInformation.setZgxl(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getZgxlid()));
        personalInformation.setByyx(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getByyxid()));
        personalInformation.setSxzy(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getSxzyid()));
        personalInformation.setPyfs(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getPyfsid()));
        personalInformation.setFirstla(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getFirstlaid()));
        personalInformation.setElsela(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getElselaid()));
        personalInformation.setPosttitle(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getPosttitleid()));
        personalInformation.setZyzstype(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getZyzstypeid()));
        personalInformation.setZyzsname(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getZyzsnameid()));
        personalInformation.setParentcompany(hrUtilsTemp.getDatavalueByHrsetid(baseInformation.getParentcompanyid()));
        personalInformation.setFirstworkingtime(baseInformation.getFirstworkingtime());
        personalInformation.setWorkingage(hrUtilsTemp.getWorkingageByFirstworkingtime(personalInformation.getFirstworkingtime()));
        personalInformation.setHj(baseInformation.getHj());
        return personalInformation;
    }

    //将部门信息塞入人事信息
    private PersonalInformation setDepByPersonalinformation(PersonalInformation personalInformation,Dept dept){
        if(null==dept)return personalInformation;
        personalInformation.setCompany(dept.getCompanyname());
        personalInformation.setDepname(dept.getDepname());
        personalInformation.setPrincipaltruename(hrUtilsTemp.getTruenameByUserid(dept.getPrincipaluserid()));
        personalInformation.setPrincipalemployeenumber(hrUtilsTemp.getEmployeenumberByUserid(dept.getPrincipaluserid()));
        return personalInformation;
    }

    //将账号信息塞入人事信息
    private PersonalInformation setUserByPersonalinformation(PersonalInformation personalInformation,User user){
        if(null==user)return null;
        personalInformation.setIsactive(user.getIsactive());
        personalInformation.setUsername(user.getUsername());
        personalInformation.setTruename(user.getTruename());
        personalInformation.setState(user.getState().toString());
        return personalInformation;
    }

    //人事添加前先将"null"等一些乱七八糟的字符串过滤掉
    private PersonalInformation dosomethingBeforeSaveone(PersonalInformation personalInformation) {
        if(null!=personalInformation.getEmployeenumber() && "null".equals(personalInformation.getEmployeenumber()))personalInformation.setEmployeenumber(null);
        if(null!=personalInformation.getSex() && "null".equals(personalInformation.getSex()))personalInformation.setSex(null);
        if(null!=personalInformation.getMobilephone() && "null".equals(personalInformation.getMobilephone()))personalInformation.setMobilephone(null);
        return personalInformation;
    }
}
