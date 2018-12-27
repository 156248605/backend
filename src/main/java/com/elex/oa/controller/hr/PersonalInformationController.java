package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.common.hr.Commons;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.util_per.SpellUtils;
import com.elex.oa.util.hr_util.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    IPerandpostrsService iPerandpostrsService;
    @Autowired
    IHRsetService ihRsetService;//HRset字段
    @Autowired
    IGzrzService iGzrzService;//工作日志
    @Autowired
    IContractInformationService iContractInformationService;



    @RequestMapping("/PersonalInformationController/queryPersonalInformations")
    @ResponseBody
    public Map<String,Object> getInitPage(
            @RequestParam("page") Integer page,
            @RequestParam("rows") Integer rows,
            PersonalInformation personalInformation
    ) throws ParseException {
        Map<String, Object> resp = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageNum", page);
        paramMap.put("pageSize", rows);
        paramMap.put("entity", personalInformation);
        PageInfo<PersonalInformation> list = iPersonalInformationService.queryPIs(paramMap);
        if (list != null) {
            List<PersonalInformation> list1 = list.getList();
            for (PersonalInformation pi : list1
            ) {
                pi = getPageinfoInstance(pi);
            }
            list.setList(list1);
        }
        resp.put("pageData",list);
        Map<String, List<String>> paramsForFirstpage = iPersonalInformationService.getParamsForFirstpage();
        resp.put("selections",paramsForFirstpage);
       return resp;
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息的查询
     * @Date: 9:49 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformations")
    @ResponseBody
    public PageInfo<PersonalInformation> queryPersonalInformation(@RequestParam("page") Integer page,
                                                                  @RequestParam("rows") Integer rows,
                                                                  PersonalInformation personalInformation
    ) throws ParseException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageNum", page);
        paramMap.put("pageSize", rows);
        paramMap.put("entity", personalInformation);
        PageInfo<PersonalInformation> list = iPersonalInformationService.queryPIs(paramMap);
        if (list != null) {
            List<PersonalInformation> list1 = list.getList();
            for (PersonalInformation pi : list1
            ) {
                pi = getPageinfoInstance(pi);
            }
            list.setList(list1);
        }
        return list;
    }

    private PersonalInformation getPageinfoInstance(PersonalInformation pi) {
        //添加信息user的三个的字段
        User user = iUserService.getById(pi.getUserid());
        pi.setIsactive(user.getIsactive());
        pi.setTruename(user.getTruename());
        pi.setUsername(user.getUsername());
        //添加信息personalinformation的六个字段
        Dept dept = new Dept();
        dept = iDeptService.queryOneDepByDepid(pi.getDepid());
        if (dept != null) {
            pi.setDepname(dept.getDepname());
        } else {
            pi.setDepname("部门信息还未添加");
        }
        //添加岗位信息
        List<PerAndPostRs> perAndPostRss = iPerandpostrsService.queryPerAndPostRsByPerid(pi.getId());
        List<String> strs = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        for (PerAndPostRs perAndPostRs : perAndPostRss) {
            Post post = iPostService.queryOneByPostid(perAndPostRs.getPostid());
            postList.add(post);
            strs.add(iPostService.queryOneByPostid(perAndPostRs.getPostid()).getPostname());
        }
        pi.setPostList(postList);
        pi.setPostnames(IDcodeUtil.getArrayToString(strs, ";"));
        //添加办公电话
        if (ihRsetService.queryById(pi.getTelphoneid()) != null) {
            pi.setTelphone(ihRsetService.queryById(pi.getTelphoneid()).getDatavalue());
        }
        //添加baseinformation的28个字段
        BaseInformation baseInformation = iBaseInformationService.queryOneById(pi.getBaseinformationid());
        pi.setEnglishname(baseInformation.getEnglishname());
        pi.setIdcode(baseInformation.getIdcode());
        pi.setUserphoto(baseInformation.getUserphoto());
        pi.setBirthday(baseInformation.getBirthday());
        try {
            pi.setAge(IDcodeUtil.getAge(pi.getBirthday()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pi.setConstellation(baseInformation.getConstellation());
        pi.setChinesecs(baseInformation.getChinesecs());
        if (ihRsetService.queryById(baseInformation.getRaceid()) != null) {
            pi.setRace(ihRsetService.queryById(baseInformation.getRaceid()).getDatavalue());
        }
        pi.setMarriage(baseInformation.getMarriage());
        if (baseInformation.getChildrenid()!=null && ihRsetService.queryById(baseInformation.getChildrenid())!= null) {
            pi.setChildren(ihRsetService.queryById(baseInformation.getChildrenid()).getDatavalue());
        }
        if (baseInformation.getZzmmid()!=null && ihRsetService.queryById(baseInformation.getZzmmid()) != null) {
            pi.setZzmm(ihRsetService.queryById(baseInformation.getZzmmid()).getDatavalue());
        }
        if (baseInformation.getZgxlid()!=null && ihRsetService.queryById(baseInformation.getZgxlid()) != null) {
            pi.setZgxl(ihRsetService.queryById(baseInformation.getZgxlid()).getDatavalue());
        }
        if (baseInformation.getByyxid()!=null && ihRsetService.queryById(baseInformation.getByyxid()) != null) {
            pi.setByyx(ihRsetService.queryById(baseInformation.getByyxid()).getDatavalue());
        }
        if (baseInformation.getSxzyid()!=null && ihRsetService.queryById(baseInformation.getSxzyid()) != null) {
            pi.setSxzy(ihRsetService.queryById(baseInformation.getSxzyid()).getDatavalue());
        }
        if (baseInformation.getPyfsid()!=null && ihRsetService.queryById(baseInformation.getPyfsid()) != null) {
            pi.setPyfs(ihRsetService.queryById(baseInformation.getPyfsid()).getDatavalue());
        }
        if (baseInformation.getFirstlaid()!=null && ihRsetService.queryById(baseInformation.getFirstlaid()) != null) {
            pi.setFirstla(ihRsetService.queryById(baseInformation.getFirstlaid()).getDatavalue());
        }
        if (baseInformation.getElselaid()!=null && ihRsetService.queryById(baseInformation.getElselaid()) != null) {
            pi.setElsela(ihRsetService.queryById(baseInformation.getElselaid()).getDatavalue());
        }
        if (baseInformation.getPosttitleid()!=null && ihRsetService.queryById(baseInformation.getPosttitleid()) != null) {
            pi.setPosttitle(ihRsetService.queryById(baseInformation.getPosttitleid()).getDatavalue());
        }
        if (baseInformation.getZyzstypeid()!=null && ihRsetService.queryById(baseInformation.getZyzstypeid()) != null) {
            pi.setZyzstype(ihRsetService.queryById(baseInformation.getZyzstypeid()).getDatavalue());
        }
        if (baseInformation.getZyzsnameid()!=null && ihRsetService.queryById(baseInformation.getZyzsnameid()) != null) {
            pi.setZyzsname(ihRsetService.queryById(baseInformation.getZyzsnameid()).getDatavalue());
        }
        pi.setFirstworkingtime(baseInformation.getFirstworkingtime());
        if (baseInformation.getFirstworkingtime() != null && !"".equals(baseInformation.getFirstworkingtime())) {
            try {
                pi.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (baseInformation.getParentcompanyid()!=null && ihRsetService.queryById(baseInformation.getParentcompanyid()) != null) {
            pi.setParentcompany(ihRsetService.queryById(baseInformation.getParentcompanyid()).getDatavalue());
        }
        //添加manageinformation的六个字段
        ManageInformation manageInformation = iManageInformationService.queryOneById(pi.getManageinformationid());
        //添加costinformation的18个字段
        CostInformation costInformation = iCostInformationService.queryOneById(pi.getCostinformationid());
        //添加otherinformation的六个字段
        OtherInformation otherInformation = iOtherInformationService.queryOneById(pi.getOtherinformationid());
        return pi;
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationById")
    @ResponseBody
    public PersonalInformation queryPersonalInformationById(
            @RequestParam("personalInformationId") int personalInformationId
    ) throws ParseException {
        //清理数据per表中的数据
        List<PersonalInformation> personalInformationList = iPersonalInformationService.queryAllByNull();
        List<User> users = iUserService.selectAll();
        List<Integer> userids = new ArrayList<>();
        for (User u : users
        ) {
            userids.add(u.getId());
        }
        for (PersonalInformation per : personalInformationList
        ) {
            if (!userids.contains(per.getUserid())) {
                iPersonalInformationService.removeOne(per.getId());
            }
        }
        return getOnePersonalinformation(personalInformationId);
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationByUserid")
    @ResponseBody
    public ArrayList<HashMap> queryPersonalInformationByUserid(
            @RequestParam("userid") int userid
    ) throws ParseException {
        PersonalInformation onePersonalinformation = iPersonalInformationService.queryOneByUserid(userid);
        PersonalInformation personalInformation = getOnePersonalinformation(onePersonalinformation.getId());
        ArrayList<HashMap> list = new ArrayList<>();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("title", "姓名");
        map1.put("value", personalInformation.getTruename());
        list.add(map1);
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("title", "性别");
        map2.put("value", personalInformation.getSex());
        list.add(map2);
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("title", "出生年月");
        map3.put("value", personalInformation.getBirthday());
        list.add(map3);
        HashMap<String, String> map4 = new HashMap<>();
        map4.put("title", "最高学历");
        map4.put("value", personalInformation.getZgxl());
        list.add(map4);
        HashMap<String, String> map5 = new HashMap<>();
        map5.put("title", "毕业院校");
        map5.put("value", personalInformation.getByyx());
        list.add(map5);
        HashMap<String, String> map6 = new HashMap<>();
        map6.put("title", "婚姻状态");
        map6.put("value", personalInformation.getMarriage());
        list.add(map6);
        HashMap<String, String> map7 = new HashMap<>();
        map7.put("title", "手机号");
        map7.put("value", personalInformation.getMobilephone());
        list.add(map7);
        HashMap<String, String> map8 = new HashMap<>();
        map8.put("title", "邮箱");
        map8.put("value", personalInformation.getCompanyemail());
        list.add(map8);
        HashMap<String, String> map9 = new HashMap<>();
        map9.put("title", "岗位");
        map9.put("value", personalInformation.getPostnames());
        list.add(map9);
        HashMap<String, String> map10 = new HashMap<>();
        map10.put("title", "住址");
        map10.put("value", personalInformation.getAddress());
        list.add(map10);
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationByTruename")
    @ResponseBody
    public PersonalInformation queryPersonalInformationByTruename(
            @RequestParam("truename") String truename
    ) throws ParseException {
        User user = iUserService.queryByTruename(truename);
        PersonalInformation onePersonalinformation = null;
        if (user.getId() != null) {
            onePersonalinformation = iPersonalInformationService.queryOneByUserid(user.getId());
        }
        PersonalInformation personalInformation;
        if (onePersonalinformation != null) {
            personalInformation = getOnePersonalinformation(onePersonalinformation.getId());
        } else {
            return null;
        }

        return personalInformation;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据perid查询信息
     * @Date: 17:36 2018\5\17 0017
     */
    public PersonalInformation getOnePersonalinformation(Integer personalInformationId) throws ParseException {
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById2(personalInformationId);
        if (personalInformation == null) {
            return null;
        }
        //1.获得User信息
        User user = iUserService.getById(personalInformation.getUserid());
        if (user != null) {
            personalInformation.setIsactive(user.getIsactive());
            personalInformation.setUsername(user.getUsername());
            personalInformation.setTruename(user.getTruename());
        }
        //2.获得基本信息
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation.getBaseinformationid());
        if (baseInformation != null) {
            personalInformation.setUserphoto(baseInformation.getUserphoto());
            personalInformation.setIdphoto1(baseInformation.getIdphoto1());
            personalInformation.setIdphoto2(baseInformation.getIdphoto2());
            personalInformation.setEnglishname(baseInformation.getEnglishname());
            personalInformation.setIdcode(baseInformation.getIdcode());
            personalInformation.setBirthday(baseInformation.getBirthday());
            try {
                personalInformation.setAge(IDcodeUtil.getAge(baseInformation.getBirthday()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            personalInformation.setConstellation(baseInformation.getConstellation());
            personalInformation.setChinesecs(baseInformation.getChinesecs());
            if (ihRsetService.queryById(baseInformation.getRaceid()) != null) {
                personalInformation.setRace(ihRsetService.queryById(baseInformation.getRaceid()).getDatavalue());
            }
            personalInformation.setMarriage(baseInformation.getMarriage());
            if (ihRsetService.queryById(baseInformation.getChildrenid()) != null) {
                personalInformation.setChildren(ihRsetService.queryById(baseInformation.getChildrenid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZzmmid()) != null) {
                personalInformation.setZzmm(ihRsetService.queryById(baseInformation.getZzmmid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZgxlid()) != null) {
                personalInformation.setZgxl(ihRsetService.queryById(baseInformation.getZgxlid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getByyxid()) != null) {
                personalInformation.setByyx(ihRsetService.queryById(baseInformation.getByyxid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getSxzyid()) != null) {
                personalInformation.setSxzy(ihRsetService.queryById(baseInformation.getSxzyid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getPyfsid()) != null) {
                personalInformation.setPyfs(ihRsetService.queryById(baseInformation.getPyfsid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getFirstlaid()) != null) {
                personalInformation.setFirstla(ihRsetService.queryById(baseInformation.getFirstlaid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getElselaid()) != null) {
                personalInformation.setElsela(ihRsetService.queryById(baseInformation.getElselaid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getPosttitleid()) != null) {
                personalInformation.setPosttitle(ihRsetService.queryById(baseInformation.getPosttitleid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZyzstypeid()) != null) {
                personalInformation.setZyzstype(ihRsetService.queryById(baseInformation.getZyzstypeid()).getDatavalue());
            }
            if (ihRsetService.queryById(baseInformation.getZyzsnameid()) != null) {
                personalInformation.setZyzsname(ihRsetService.queryById(baseInformation.getZyzsnameid()).getDatavalue());
            }
            personalInformation.setFirstworkingtime(baseInformation.getFirstworkingtime());
            if (baseInformation.getFirstworkingtime() != null && !"".equals(baseInformation.getFirstworkingtime())) {
                try {
                    personalInformation.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (ihRsetService.queryById(baseInformation.getParentcompanyid()) != null) {
                personalInformation.setParentcompany(ihRsetService.queryById(baseInformation.getParentcompanyid()).getDatavalue());
            }
        }
        //先获得岗位信息
        List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(personalInformationId);
        List<String> strs = new ArrayList<>();
        List<Integer> postids = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        if (perAndPostRs.size() != 0) {
            for (PerAndPostRs perAndPost : perAndPostRs) {
                Post post = iPostService.queryOneByPostid(perAndPost.getPostid());
                postList.add(post);
                strs.add(iPostService.queryOneByPostid(perAndPost.getPostid()).getPostname());
                postids.add(perAndPost.getPostid());
            }
        }
        personalInformation.setPostList(postList);
        personalInformation.setPostnames(IDcodeUtil.getArrayToString(strs, ";"));
        personalInformation.setPostids(postids);
        //3.获得管理信息
        ManageInformation manageInformation = iManageInformationService.queryOneById(personalInformation.getManageinformationid());
        if (manageInformation != null) {
            if (iDeptService.queryOneDepByDepid(personalInformation.getDepid()) != null) {
                personalInformation.setCompany(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getCompanyname());
                personalInformation.setDepname(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getDepname());
                if (iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()) != null) {
                    personalInformation.setPrincipaltruename(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getTruename());
                    if (iPersonalInformationService.queryOneByUserid(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getId()) != null) {
                        personalInformation.setPrincipalemployeenumber(iPersonalInformationService.queryOneByUserid(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getId()).getEmployeenumber());
                    }
                }
            }
            /*List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(personalInformationId);
            List<String> strs = new ArrayList<>();
            List<Integer> postids = new ArrayList<>();
            if (perAndPostRs.size()!=0) {
                for(PerAndPostRs perAndPost:perAndPostRs){
                    strs.add(iPostService.queryOneByPostid(perAndPost.getPostid()).getPostname());
                    postids.add(perAndPost.getPostid());
                }
            }
            personalInformation.setPostnames(IDcodeUtil.getArrayToString(strs,";"));
            personalInformation.setPostids(postids);*/
            if (ihRsetService.queryById(manageInformation.getRankid()) != null) {
                personalInformation.setZj(ihRsetService.queryById(manageInformation.getRankid()).getDatavalue());
            }
            personalInformation.setEntrydate(manageInformation.getEntrydate());
            try {
                personalInformation.setSn(IDcodeUtil.getCompanyAge(manageInformation.getEntrydate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            personalInformation.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
            if (ihRsetService.queryById(manageInformation.getEmployeetypeid()) != null) {
                personalInformation.setEmployeetype(ihRsetService.queryById(manageInformation.getEmployeetypeid()).getDatavalue());
            }
        }
        //获得成本信息
        CostInformation costInformation = iCostInformationService.queryOneById(personalInformation.getCostinformationid());
        if (costInformation != null) {
            if (ihRsetService.queryById(costInformation.getSalarystandardid()) != null) {
                personalInformation.setSalary(ihRsetService.queryById(costInformation.getSalarystandardid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getSsbid()) != null) {
                personalInformation.setSsb(ihRsetService.queryById(costInformation.getSsbid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getSsbgscdid()) != null) {
                personalInformation.setSsbgscd(ihRsetService.queryById(costInformation.getSsbgscdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getSsbgrcdid()) != null) {
                personalInformation.setSsbgrcd(ihRsetService.queryById(costInformation.getSsbgrcdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getGjjid()) != null) {
                personalInformation.setGjj(ihRsetService.queryById(costInformation.getGjjid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getGjjgscdid()) != null) {
                personalInformation.setGjjgscd(ihRsetService.queryById(costInformation.getGjjgscdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getGjjgrcdid()) != null) {
                personalInformation.setGjjgrcd(ihRsetService.queryById(costInformation.getGjjgrcdid()).getDatavalue());
            }
            if (ihRsetService.queryById(costInformation.getKhhid()) != null) {
                personalInformation.setKhh(ihRsetService.queryById(costInformation.getKhhid()).getDatavalue());
            }
            personalInformation.setSalaryaccount(costInformation.getSalaryaccount());
            if (ihRsetService.queryById(costInformation.getSbjndid()) != null) {
                personalInformation.setSbjnd(ihRsetService.queryById(costInformation.getSbjndid()).getDatavalue());
            }
            personalInformation.setSbcode(costInformation.getSbcode());
            personalInformation.setGjjcode(costInformation.getGjjcode());
        }
        //获得其他信息
        OtherInformation otherInformation = iOtherInformationService.queryOneById(personalInformation.getOtherinformationid());
        if (otherInformation != null) {
            if (personalInformation.getTelphoneid()!=null && ihRsetService.queryById(personalInformation.getTelphoneid()) != null) {
                personalInformation.setTelphone(ihRsetService.queryById(personalInformation.getTelphoneid()).getDatavalue());
            }
            personalInformation.setPrivateemail(otherInformation.getPrivateemail());
            personalInformation.setCompanyemail(otherInformation.getCompanyemail());
            personalInformation.setEmergencycontract(otherInformation.getEmergencycontract());
            if (otherInformation.getEmergencyrpid()!=null && ihRsetService.queryById(otherInformation.getEmergencyrpid()) != null) {
                personalInformation.setEmergencyrp(ihRsetService.queryById(otherInformation.getEmergencyrpid()).getDatavalue());
            }
            personalInformation.setEmergencyphone(otherInformation.getEmergencyphone());
            personalInformation.setAddress(otherInformation.getAddress());
            personalInformation.setRemark(otherInformation.getRemark());
        }
        return personalInformation;
    }

    /**
     * @Author:ShiYun;
     * @Description:添加用户人事信息（基本信息）
     * @Date: 18:07 2018\4\10 0010
     */
    @RequestMapping(value = "/addBaseInformation", consumes = "multipart/form-data")
    @ResponseBody
    public Object addBaseInformation(
            PersonalInformation personalInformation,
            @RequestParam("byyxvalue") String byyxvalue,
            @RequestParam("sxzyvalue") String sxzyvalue,
            @RequestParam("zyzsnamevalue") String zyzsnamevalue,
            @RequestParam("parentcompanyvalue") String parentcompanyvalue,
            @RequestParam("lysqdid") String lysqdid,
            HttpServletRequest request
    ) throws ParseException {
        //工号校验
        PersonalInformation perTemp = iPersonalInformationService.queryByEmployeenumber(personalInformation.getEmployeenumber());
        if (null!=perTemp) {
            return RespUtil.successResp("500", "工号已存在，请重新输入！", null);
        }
        //username校验
        User u;
        if (personalInformation.getUsername() != null && !personalInformation.getUsername().equals("")) {
            u = iUserService.queryByUsername(personalInformation.getUsername());
            if (u != null) {
                return RespUtil.successResp("500", "登录ID已存在，请重新输入（不输入则默认为姓名）！", null);
            }
        } else {
            u = iUserService.queryByUsername(personalInformation.getTruename());
            int i = 2;
            while (u != null) {
                u = iUserService.queryByUsername(personalInformation.getTruename() + i);
                i++;
            }
            if (i == 2) {
                personalInformation.setUsername(personalInformation.getTruename());
            } else {
                i--;
                personalInformation.setUsername(personalInformation.getTruename() + i);
            }
        }
        //校验省份证号码
        if (personalInformation.getIdcode() != null && !"".equals(personalInformation.getIdcode())) {
            String birthday = null;
            try {
                birthday = IDcodeUtil.getBirthday(personalInformation.getIdcode());
            } catch (Exception e) {
                e.printStackTrace();
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String format = simpleDateFormat.format(date);
            String[] split1 = format.split("/");
            String[] split = birthday.split("/");
            Integer year = Integer.parseInt(split[0]);
            Integer year2 = Integer.parseInt(split1[0]) - 18;
            Integer month = Integer.parseInt(split[1]);
            Integer day = Integer.parseInt(split[2]);
            Boolean b = false;
            if (year > 1953 && year < year2 && month > 0 && month < 13) {
                Integer daysByDate = IDcodeUtil.getDaysByDate(simpleDateFormat.parse(birthday));
                if (day > 1 && day <= daysByDate) {
                    b = true;
                }
            }
            if (b == false) {
                return RespUtil.successResp("500", "身份证号码输入有误请重新输入", null);
            }
        }

        User user = new User();
        BaseInformation baseInformation = new BaseInformation();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> idphoto2s = multipartRequest.getFiles("idphoto22");
        List<MultipartFile> idphoto1s = multipartRequest.getFiles("idphoto11");
        List<MultipartFile> userphoto2s = multipartRequest.getFiles("userphoto2");

        // 将三个文件在服务器中保存下来
        try {
            if (userphoto2s.size() != 0 || idphoto2s.size() != 0 || idphoto1s.size() != 0) {
                String realPath = Commons.realpath + "/hr/image";
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/" + l);
                file.mkdirs();
                if (userphoto2s.size() != 0) {
                    String userphoto = "/hr/image/" + l + "/" + userphoto2s.get(0).getOriginalFilename();
                    userphoto2s.get(0).transferTo(new File(realPath + "/" + l, userphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setUserphoto(userphoto);
                }
                if (idphoto1s.size() != 0) {
                    String idphoto1 = "/hr/image/" + l + "/" + idphoto1s.get(0).getOriginalFilename();
                    idphoto1s.get(0).transferTo(new File(realPath + "/" + l, idphoto1s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto1(idphoto1);
                }
                if (idphoto2s.size() != 0) {
                    String idphoto2 = "/hr/image/" + l + "/" + idphoto2s.get(0).getOriginalFilename();
                    idphoto2s.get(0).transferTo(new File(realPath + "/" + l, idphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto2(idphoto2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 在tb_id_user表中保存用户
        user.setIsactive(personalInformation.getIsactive());
        if (personalInformation.getUsername() != null && !"".equals(personalInformation.getUsername())) {
            user.setUsername(personalInformation.getUsername());
        } else {
            user.setUsername(SpellUtils.phoneticize(personalInformation.getTruename()));//如果没有人工输入，则自动将名字的汉字转换为汉语拼音
        }
        user.setTruename(personalInformation.getTruename());
        Integer userid = iUserService.saveOne(user);

        // 在tb_id_baseinformation表中保存用户基本信息
        baseInformation.setEnglishname(personalInformation.getEnglishname());
        baseInformation.setIdcode(personalInformation.getIdcode());
        if (personalInformation.getIdcode() != null && !"".equals(personalInformation.getIdcode())) {
            try {
                baseInformation.setBirthday(IDcodeUtil.getBirthday(personalInformation.getIdcode()));//出生日期
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                baseInformation.setHj(IDcodeUtil.getProvinceByIdcode(personalInformation.getIdcode()));//户籍
            } catch (Exception e) {
                e.printStackTrace();
            }
            baseInformation.setConstellation(IDcodeUtil.getConstellation(personalInformation.getIdcode()));//星座
            try {
                baseInformation.setChinesecs(IDcodeUtil.getChinesecs(personalInformation.getIdcode()));//属相
            } catch (Exception e) {
                e.printStackTrace();
                return RespUtil.successResp("500", e.getMessage(), null);
            }
            personalInformation.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));//性别
        }
        List<HRset> hRsetRaceList = ihRsetService.queryByConditions(new HRset("race", personalInformation.getRace()));
        if (hRsetRaceList != null && hRsetRaceList.size() == 1) {
            baseInformation.setRaceid(hRsetRaceList.get(0).getId());
        }
        baseInformation.setMarriage(personalInformation.getMarriage());
        List<HRset> hRsetChildrenList = ihRsetService.queryByConditions(new HRset("children", personalInformation.getChildren()));
        if (hRsetChildrenList != null && hRsetChildrenList.size() == 1) {
            baseInformation.setChildrenid(hRsetChildrenList.get(0).getId());
        }
        List<HRset> hRsetZzmmList = ihRsetService.queryByConditions(new HRset("zzmm", personalInformation.getZzmm()));
        if (hRsetZzmmList != null && hRsetZzmmList.size() == 1) {
            baseInformation.setZzmmid(hRsetZzmmList.get(0).getId());
        }
        List<HRset> hRsetZgxlList = ihRsetService.queryByConditions(new HRset("zgxl", personalInformation.getZgxl()));
        if (hRsetZgxlList != null && hRsetZgxlList.size() == 1) {
            baseInformation.setZgxlid(hRsetZgxlList.get(0).getId());
        }
        List<HRset> hRsetByyxList = ihRsetService.queryByConditions(new HRset("byyx", personalInformation.getByyx()));
        if ("选择录入".equals(byyxvalue) && (hRsetByyxList == null || hRsetByyxList.size() == 0)) {
            if (!personalInformation.getByyx().equals("")) {
                Integer byyxid = ihRsetService.addOne(new HRset("byyx", personalInformation.getByyx()));
                baseInformation.setByyxid(byyxid);
            }
        } else {
            if (hRsetByyxList != null && hRsetByyxList.size() == 1) {
                baseInformation.setByyxid(hRsetByyxList.get(0).getId());
            }
        }
        List<HRset> hRsetSxzyList = ihRsetService.queryByConditions(new HRset("sxzy", personalInformation.getSxzy()));
        if ("选择录入".equals(sxzyvalue) && (hRsetSxzyList == null || hRsetSxzyList.size() == 0)) {
            if (!personalInformation.getSxzy().equals("")) {
                Integer sxzyid = ihRsetService.addOne(new HRset("sxzy", personalInformation.getSxzy()));
                baseInformation.setSxzyid(sxzyid);
            }
        } else {
            if (hRsetSxzyList != null && hRsetSxzyList.size() == 1) {
                baseInformation.setSxzyid(hRsetSxzyList.get(0).getId());
            }
        }
        List<HRset> hRsetPyfsList = ihRsetService.queryByConditions(new HRset("pyfs", personalInformation.getPyfs()));
        if (hRsetPyfsList != null && hRsetPyfsList.size() == 1) {
            baseInformation.setPyfsid(hRsetPyfsList.get(0).getId());
        }
        List<HRset> hRsetFirstlaList = ihRsetService.queryByConditions(new HRset("fla", personalInformation.getFirstla()));
        if (hRsetFirstlaList != null && hRsetFirstlaList.size() == 1) {
            baseInformation.setFirstlaid(hRsetFirstlaList.get(0).getId());
        }
        List<HRset> hRsetElselaList = ihRsetService.queryByConditions(new HRset("fla", personalInformation.getElsela()));
        if (hRsetElselaList != null && hRsetElselaList.size() == 1) {
            baseInformation.setElselaid(hRsetElselaList.get(0).getId());
        }
        List<HRset> hRsetPosttitleList = ihRsetService.queryByConditions(new HRset("posttitle", personalInformation.getPosttitle()));
        if (hRsetPosttitleList != null && hRsetPosttitleList.size() == 1) {
            baseInformation.setPosttitleid(hRsetPosttitleList.get(0).getId());
        }
        List<HRset> hRsetZyzstypeList = ihRsetService.queryByConditions(new HRset("zyzstype", personalInformation.getZyzstype()));
        if (hRsetZyzstypeList != null && hRsetZyzstypeList.size() == 1) {
            baseInformation.setZyzstypeid(hRsetZyzstypeList.get(0).getId());
        }
        List<HRset> hRsetZyzsnameList = ihRsetService.queryByConditions(new HRset("zyzsname", personalInformation.getZyzsname()));
        if ("选择录入".equals(zyzsnamevalue) && (hRsetZyzsnameList == null || hRsetZyzsnameList.size() == 0)) {
            if (!personalInformation.getZyzsname().equals("")) {
                Integer zyzsnameid = ihRsetService.addOne(new HRset("zyzsname", personalInformation.getZyzsname()));
                baseInformation.setZyzsnameid(zyzsnameid);
            }
        } else {
            if (hRsetZyzsnameList != null && hRsetZyzsnameList.size() == 1) {
                baseInformation.setZyzsnameid(hRsetZyzsnameList.get(0).getId());
            }
        }
        baseInformation.setFirstworkingtime(personalInformation.getFirstworkingtime());
        List<HRset> hRsetParentcompanyList = ihRsetService.queryByConditions(new HRset("parentcompany", personalInformation.getParentcompany()));
        if ("选择录入".equals(parentcompanyvalue) && (hRsetParentcompanyList == null && hRsetParentcompanyList.size() == 0)) {
            if (!personalInformation.getParentcompany().equals("")) {
                Integer parentcompanyid = ihRsetService.addOne(new HRset("parentcompany", personalInformation.getParentcompany()));
                baseInformation.setParentcompanyid(parentcompanyid);
            }
        } else {
            if (hRsetParentcompanyList != null && hRsetParentcompanyList.size() == 1) {
                baseInformation.setParentcompanyid(hRsetParentcompanyList.get(0).getId());
            }
        }
        Integer baseInformationId = iBaseInformationService.saveOne(baseInformation);

        // 在tb_id_personalinformation表中添加数据
        personalInformation.setUserid(userid);
        personalInformation.setBaseinformationid(baseInformationId);
        Integer personalInformationId = iPersonalInformationService.saveOne(personalInformation);
        personalInformation.setId(personalInformationId);
        if (lysqdid != null && !"".equals(lysqdid)) {
            //处理录用申请单（户籍、性别等信息时身份证号解析出来的）
            //添加岗位信息
            Lysqd lysqd = iGzrzService.queryLysqdById(lysqdid);
            PerAndPostRs perAndPostRs = new PerAndPostRs();
            perAndPostRs.setPerid(personalInformationId);
            perAndPostRs.setPostid(Integer.valueOf(lysqd.getF_lygw().split("_")[1]));
            iPerandpostrsService.addOne(perAndPostRs);
            //部门信息没有对接好，不好操作
            //添加合同信息(缺少合同编号等必填信息)，光有入职日期和合同期限是不够的
            //添加人事成本信息(其中薪资是HR设置里面选择的信息，不好匹配)
            //添加其它信息
            OtherInformation otherInformation = new OtherInformation();
            otherInformation.setAddress(lysqd.getF_jzdz());
            personalInformation.setMobilephone(lysqd.getF_lxdh());
            otherInformation.setEmergencycontract(lysqd.getF_yjlxr());
            otherInformation.setEmergencyphone(lysqd.getF_yjlxfs());
            Integer otherinformationid = iOtherInformationService.saveOne(otherInformation);
            personalInformation.setOtherinformationid(otherinformationid);
            iPersonalInformationService.modifyOne(personalInformation);
            //将录用申请单中的状态改掉
            iGzrzService.modifyLysqdById(lysqdid);
        }
        return RespUtil.successResp("200", "提交成功！", userid);
    }

    /**
     * @Author:ShiYun;
     * @Description:添加人事信息的管理信息
     * @Date: 11:36 2018\4\11 0011
     */
    @RequestMapping("/addManageInformation")
    @ResponseBody
    public Object addManageInformation(
            PersonalInformation personalInformation
    ) {
        if (personalInformation.getUserid() == null || iPersonalInformationService.queryOneByUserid(personalInformation.getUserid()) == null) {
            return RespUtil.successResp("500", "系统正在忙，请稍后", null);
        }
        // 保存人事信息的管理信息
        ManageInformation manageInformation = new ManageInformation();
        List<HRset> hRsetRankList = ihRsetService.queryByConditions(new HRset("rank", personalInformation.getZj()));
        if (hRsetRankList != null && hRsetRankList.size() == 1) {
            manageInformation.setRankid(hRsetRankList.get(0).getId());
        }
        List<HRset> hRsetEmployeetypeList = ihRsetService.queryByConditions(new HRset("employeetype", personalInformation.getEmployeetype()));
        if (hRsetEmployeetypeList != null && hRsetEmployeetypeList.size() == 1) {
            manageInformation.setEmployeetypeid(hRsetEmployeetypeList.get(0).getId());
        }
        manageInformation.setEntrydate(personalInformation.getEntrydate());
        if (null != personalInformation.getEntrydate() && !"".equals(personalInformation.getEntrydate())) {
            try {
                manageInformation.setZhuanzhengdate(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Integer manageInformationId = iManageInformationService.saveOne(manageInformation);

        // 修改人事信息的主体信息
        PersonalInformation personalInformation1 = iPersonalInformationService.queryOneByUserid(personalInformation.getUserid());
        personalInformation.setId(personalInformation1.getId());
        personalInformation.setManageinformationid(manageInformationId);
        iPersonalInformationService.modifyOne(personalInformation);

        //添加人事与岗位关系表的信息
        List<Integer> postids = personalInformation.getPostids();
        if (postids != null && postids.size() != 0) {
            for (Integer postid : postids) {
                PerAndPostRs perAndPostRs = new PerAndPostRs(personalInformation.getId(), postid);
                iPerandpostrsService.addOne(perAndPostRs);
            }
        }
        User user = iUserService.getById(personalInformation.getUserid());
        return RespUtil.successResp("200", "管理信息添加成功！", user);
    }

    /**
     * @Author:ShiYun;
     * @Description:添加人事信息的成本信息
     * @Date: 17:41 2018\4\11 0011
     */
    @RequestMapping("/addCostInformation")
    @ResponseBody
    public String addCostInformation(
            PersonalInformation personalInformation,
            @RequestParam("khhvalue") String khhvalue,
            @RequestParam("sbjndvalue") String sbjndvalue
    ) {
        // 保存人事信息的成本信息
        CostInformation costInformation = new CostInformation();
        List<HRset> hRsetSalaryList = ihRsetService.queryByConditions(new HRset("salary", personalInformation.getSalary()));
        if (hRsetSalaryList != null && hRsetSalaryList.size() == 1) {
            costInformation.setSalarystandardid(hRsetSalaryList.get(0).getId());
        }
        List<HRset> hRsetSsbList = ihRsetService.queryByConditions(new HRset("ssb", personalInformation.getSsb()));
        if (hRsetSsbList != null && hRsetSsbList.size() == 1) {
            costInformation.setSsbid(hRsetSsbList.get(0).getId());
        }
        List<HRset> hRsetSsbgscdList = ihRsetService.queryByConditions(new HRset("ssbgscd", personalInformation.getSsbgscd()));
        if (hRsetSsbgscdList != null && hRsetSsbgscdList.size() == 1) {
            costInformation.setSsbgscdid(hRsetSsbgscdList.get(0).getId());
        }
        List<HRset> hRsetSsbgrcdList = ihRsetService.queryByConditions(new HRset("ssbgrcd", personalInformation.getSsbgrcd()));
        if (hRsetSsbgrcdList != null && hRsetSsbgrcdList.size() == 1) {
            costInformation.setSsbgrcdid(hRsetSsbgrcdList.get(0).getId());
        }
        List<HRset> hRsetGjjList = ihRsetService.queryByConditions(new HRset("gjj", personalInformation.getGjj()));
        if (hRsetGjjList != null && hRsetGjjList.size() == 1) {
            costInformation.setGjjid(hRsetGjjList.get(0).getId());
        }
        List<HRset> hRsetGjjgscdList = ihRsetService.queryByConditions(new HRset("gjjgscd", personalInformation.getGjjgscd()));
        if (hRsetGjjgscdList != null && hRsetGjjgscdList.size() == 1) {
            costInformation.setGjjgscdid(hRsetGjjgscdList.get(0).getId());
        }
        List<HRset> hRsetGjjgrcdList = ihRsetService.queryByConditions(new HRset("gjjgrcd", personalInformation.getGjjgrcd()));
        if (hRsetGjjgrcdList != null && hRsetGjjgrcdList.size() == 1) {
            costInformation.setGjjgrcdid(hRsetGjjgrcdList.get(0).getId());
        }
        List<HRset> hRsetKhhList = ihRsetService.queryByConditions(new HRset("khh", personalInformation.getKhh()));
        if (khhvalue.equals("选择录入") && (hRsetKhhList == null || hRsetKhhList.size() == 0)) {
            if (!personalInformation.getKhh().equals("")) {
                Integer khhid = ihRsetService.addOne(new HRset("khh", personalInformation.getKhh()));
                costInformation.setKhhid(khhid);
            }
        } else {
            if (hRsetKhhList != null && hRsetKhhList.size() == 1) {
                costInformation.setKhhid(hRsetKhhList.get(0).getId());
            }
        }
        costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
        List<HRset> hRsetSbjndList = ihRsetService.queryByConditions(new HRset("sbjnd", personalInformation.getSbjnd()));
        if (sbjndvalue.equals("选择录入") && (hRsetSbjndList == null || hRsetSbjndList.size() == 0)) {
            if (!personalInformation.getSbjnd().equals("")) {
                Integer sbjndid = ihRsetService.addOne(new HRset("sbjnd", personalInformation.getSbjnd()));
                costInformation.setSbjndid(sbjndid);
            }
        } else {
            if (hRsetSbjndList != null && hRsetSbjndList.size() == 1) {
                costInformation.setSbjndid(hRsetSbjndList.get(0).getId());
            }
        }
        costInformation.setSbcode(personalInformation.getSbcode());
        costInformation.setGjjcode(personalInformation.getGjjcode());
        Integer costInformationId = iCostInformationService.saveOne(costInformation);

        // 修改人事信息
        PersonalInformation personalInformation1 = iPersonalInformationService.queryOneByUserid(personalInformation.getUserid());
        personalInformation.setId(personalInformation1.getId());
        personalInformation.setCostinformationid(costInformationId);
        iPersonalInformationService.modifyOne(personalInformation);

        return "添加成本信息成功！";
    }

    /**
     * @Author:ShiYun;
     * @Description:添加人事信息的其它信息
     * @Date: 18:42 2018\4\11 0011
     */
    @RequestMapping("/addOtherInformation")
    @ResponseBody
    public Object addOtherInformation(
            PersonalInformation personalInformation
    ) throws ParseException {
        // 添加人事信息的其它信息
        OtherInformation otherInformation = new OtherInformation();
        otherInformation.setCompanyemail(personalInformation.getCompanyemail());
        otherInformation.setPrivateemail(personalInformation.getPrivateemail());
        otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
        List<HRset> hRsetEmergencyrpList = ihRsetService.queryByConditions(new HRset("emergencyrp", personalInformation.getEmergencyrp()));
        if (hRsetEmergencyrpList != null && hRsetEmergencyrpList.size() == 1) {
            otherInformation.setEmergencyrpid(hRsetEmergencyrpList.get(0).getId());
        }
        otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
        otherInformation.setAddress(personalInformation.getAddress());
        otherInformation.setRemark(personalInformation.getRemark());
        Integer otherInformationId = iOtherInformationService.saveOne(otherInformation);

        // 修改人事信息
        PersonalInformation personalInformation1 = getOnePersonalinformation(iPersonalInformationService.queryOneByUserid(personalInformation.getUserid()).getId());
        personalInformation.setId(personalInformation1.getId());
        List<HRset> hRsetTelphoneList = ihRsetService.queryByConditions(new HRset("telphone", personalInformation.getTelphone()));
        if (hRsetTelphoneList != null && hRsetTelphoneList.size() == 1) {
            personalInformation.setTelphoneid(hRsetTelphoneList.get(0).getId());
        }
        personalInformation.setOtherinformationid(otherInformationId);
        iPersonalInformationService.modifyOne(personalInformation);

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", personalInformation1.getUsername());
        map.put("isactive", personalInformation1.getIsactive());
        map.put("truename", personalInformation1.getTruename());
        map.put("postids", personalInformation1.getPostids());

        return RespUtil.successResp("200", "添加其他信息成功！", map);
    }

    /**
     * @Author:ShiYun;
     * @Description:修改人事信息的基本信息
     * @Date: 13:59 2018\4\12 0012
     */
    @RequestMapping("/updateBaseInformation")
    @ResponseBody
    public Object updateBaseInformation(
            PersonalInformation personalInformation,
            @RequestParam("byyxvalue") String byyxvalue,
            @RequestParam("sxzyvalue") String sxzyvalue,
            @RequestParam("zyzsnamevalue") String zyzsnamevalue,
            @RequestParam("parentcompanyvalue") String parentcompanyvalue,
            @RequestParam("transactorusername") String transactorusername,
            HttpServletRequest request
    ) throws ParseException {
        //修改信息痕迹的总标识
        Boolean listBL = false;
        //原来的信息
        PersonalInformation personalInformation2 = getOnePersonalinformation(iPersonalInformationService.queryOneByUserid(personalInformation.getUserid()).getId());
        personalInformation.setId(personalInformation2.getId());
        //添加修改信息
        ChangeInformation changeInformation = new ChangeInformation();
        //1.姓名
        changeInformation.setChangeduserid(personalInformation.getUserid());
        //2.变更原因
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        //3.变更时间
        changeInformation.setChangedate(changedate);
        User transactor = new User();
        transactor.setUsername(transactorusername);
        //4.办理人
        changeInformation.setTransactoruserid(iUserService.selectByCondition(transactor).get(0).getId());
        //5.变更项目；6.变更前内容；7.变更后内容

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> idphoto2s = multipartRequest.getFiles("idphoto22");
        List<MultipartFile> idphoto1s = multipartRequest.getFiles("idphoto11");
        List<MultipartFile> userphoto2s = multipartRequest.getFiles("userphoto2");

        //tb_id_baseinformation表的标识
        Boolean baseBL = false;
        // 将三个文件在服务器中保存下来
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation2.getBaseinformationid());
        if (baseInformation == null) {
            baseInformation = new BaseInformation();//如果不存在则新建实例，下面就是添加信息了
        }
        try {
            if (userphoto2s.size() != 0 || idphoto2s.size() != 0 || idphoto1s.size() != 0) {
                String realPath = Commons.realpath + "/hr/image";
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/" + l);
                file.mkdirs();
                if (userphoto2s.size() != 0) {
                    String userphoto = "/hr/image/" + l + "/" + userphoto2s.get(0).getOriginalFilename();
                    userphoto2s.get(0).transferTo(new File(realPath + "/" + l, userphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setUserphoto(userphoto);
                    changeInformation.setChangeinformation("免冠照片");
                    changeInformation.setBeforeinformation(personalInformation2.getUserphoto());
                    changeInformation.setAfterinformation(userphoto);
                    iChangeInformationService.addOne(changeInformation);
                    baseBL = true;
                }
                if (idphoto1s.size() != 0) {
                    String idphoto1 = "/hr/image/" + l + "/" + idphoto1s.get(0).getOriginalFilename();
                    idphoto1s.get(0).transferTo(new File(realPath + "/" + l, idphoto1s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto1(idphoto1);
                    changeInformation.setChangeinformation("身份证扫描件正面");
                    changeInformation.setBeforeinformation(personalInformation2.getIdphoto1());
                    changeInformation.setAfterinformation(idphoto1);
                    iChangeInformationService.addOne(changeInformation);
                    baseBL = true;
                }
                if (idphoto2s.size() != 0) {
                    String idphoto2 = "/hr/image/" + l + "/" + idphoto2s.get(0).getOriginalFilename();
                    idphoto2s.get(0).transferTo(new File(realPath + "/" + l, idphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto2(idphoto2);
                    changeInformation.setChangeinformation("身份证扫描件背面");
                    changeInformation.setBeforeinformation(personalInformation2.getIdphoto2());
                    changeInformation.setAfterinformation(idphoto2);
                    iChangeInformationService.addOne(changeInformation);
                    baseBL = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return RespUtil.successResp("400", "提交失败！", null);
        }
        //tb_id_user表的标识
        Boolean userBL = false;
        // 在tb_id_user表中更新用户信息
        User user = iUserService.getById(personalInformation.getUserid());
        if (personalInformation2.getIsactive() != personalInformation.getIsactive()) {
            user.setIsactive(personalInformation.getIsactive());
            changeInformation.setChangeinformation("账号激活状态");
            changeInformation.setBeforeinformation(personalInformation2.getIsactive() == 1 ? "已激活" : "未激活");
            changeInformation.setAfterinformation(personalInformation.getIsactive() == 1 ? "已激活" : "未激活");
            iChangeInformationService.addOne(changeInformation);
            userBL = true;
        }
        if (!personalInformation.getUsername().equals(personalInformation2.getUsername())) {
            //username校验
            User u;
            if (personalInformation.getUsername() != null && !personalInformation.getUsername().equals("")) {
                u = iUserService.queryByUsername(personalInformation.getUsername());
                if (u != null) {
                    return RespUtil.successResp("500", "登录ID已存在，请重新输入（不输入则默认为姓名）！", null);
                }
            } else {
                u = iUserService.queryByUsername(personalInformation.getTruename());
                int i = 2;
                while (u != null) {
                    u = iUserService.queryByUsername(personalInformation.getTruename() + i);
                    i++;
                }
                if (i == 2) {
                    personalInformation.setUsername(personalInformation.getTruename());
                } else {
                    i--;
                    personalInformation.setUsername(personalInformation.getTruename() + i);
                }
            }
            user.setUsername(personalInformation.getUsername());
            changeInformation.setChangeinformation("登录ID");
            changeInformation.setBeforeinformation(personalInformation2.getUsername());
            changeInformation.setAfterinformation(personalInformation.getUsername());
            iChangeInformationService.addOne(changeInformation);
            userBL = true;
        }
        if (!personalInformation.getTruename().equals(personalInformation2.getTruename())) {
            user.setTruename(personalInformation.getTruename());
            changeInformation.setChangeinformation("姓名");
            changeInformation.setBeforeinformation(personalInformation2.getTruename());
            changeInformation.setAfterinformation(personalInformation.getTruename());
            iChangeInformationService.addOne(changeInformation);
            userBL = true;
        }
        if (userBL) {
            iUserService.update(user);
        }

        // 在tb_id_baseinformation表中更新用户基本信息
        if (!personalInformation.getEnglishname().equals(personalInformation2.getEnglishname())) {
            baseInformation.setEnglishname(personalInformation.getEnglishname());
            changeInformation.setChangeinformation("英文名");
            changeInformation.setBeforeinformation(personalInformation2.getEnglishname());
            changeInformation.setAfterinformation(personalInformation.getEnglishname());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (!personalInformation.getIdcode().equals(personalInformation2.getIdcode())) {
            baseInformation.setIdcode(personalInformation.getIdcode());
            changeInformation.setChangeinformation("身份证号码");
            changeInformation.setBeforeinformation(personalInformation2.getIdcode());
            changeInformation.setAfterinformation(personalInformation.getIdcode());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (personalInformation.getIdcode() != null && !"".equals(personalInformation.getIdcode())) {
            try {
                baseInformation.setBirthday(IDcodeUtil.getBirthday(personalInformation.getIdcode()));//出生日期
            } catch (Exception e) {
                e.printStackTrace();
            }
            baseInformation.setConstellation(IDcodeUtil.getConstellation(personalInformation.getIdcode()));//星座
            try {
                baseInformation.setChinesecs(IDcodeUtil.getChinesecs(personalInformation.getIdcode()));//属相
            } catch (Exception e) {
                e.printStackTrace();
                return RespUtil.successResp("500", e.getMessage(), null);
            }
            personalInformation.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));//性别
        }
        List<HRset> hRsetRaceList = ihRsetService.queryByConditions(new HRset("race", personalInformation.getRace()));
        if (hRsetRaceList != null && hRsetRaceList.size() == 1 && !personalInformation.getRace().equals(personalInformation2.getRace())) {
            baseInformation.setRaceid(hRsetRaceList.get(0).getId());
            changeInformation.setChangeinformation("民族");
            changeInformation.setBeforeinformation(personalInformation2.getRace());
            changeInformation.setAfterinformation(personalInformation.getRace());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (!personalInformation.getMarriage().equals(personalInformation2.getMarriage())) {
            baseInformation.setMarriage(personalInformation.getMarriage());
            changeInformation.setChangeinformation("婚姻");
            changeInformation.setBeforeinformation(personalInformation2.getMarriage());
            changeInformation.setAfterinformation(personalInformation.getMarriage());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        List<HRset> hRsetChildrenList = ihRsetService.queryByConditions(new HRset("children", personalInformation.getChildren()));
        if (hRsetChildrenList != null && hRsetChildrenList.size() == 1 && !personalInformation.getChildren().equals(personalInformation2.getChildren())) {
            baseInformation.setChildrenid(hRsetChildrenList.get(0).getId());
            changeInformation.setChangeinformation("生育");
            changeInformation.setBeforeinformation(personalInformation2.getChildren());
            changeInformation.setAfterinformation(personalInformation.getChildren());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        List<HRset> hRsetZzmmList = ihRsetService.queryByConditions(new HRset("zzmm", personalInformation.getZzmm()));
        if (hRsetZzmmList != null && hRsetZzmmList.size() == 1 && !personalInformation.getZzmm().equals(personalInformation2.getZzmm())) {
            baseInformation.setZzmmid(hRsetZzmmList.get(0).getId());
            changeInformation.setChangeinformation("政治面貌");
            changeInformation.setBeforeinformation(personalInformation2.getZzmm());
            changeInformation.setAfterinformation(personalInformation.getZzmm());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        List<HRset> hRsetZgxlList = ihRsetService.queryByConditions(new HRset("zgxl", personalInformation.getZgxl()));
        if (hRsetZgxlList != null && hRsetZgxlList.size() == 1 && !personalInformation.getZgxl().equals(personalInformation2.getZgxl())) {
            baseInformation.setZgxlid(hRsetZgxlList.get(0).getId());
            changeInformation.setChangeinformation("最高学历");
            changeInformation.setBeforeinformation(personalInformation2.getZgxl());
            changeInformation.setAfterinformation(personalInformation.getZgxl());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (personalInformation.getByyx() != null && !"null".equals(personalInformation.getByyx())
                && !"".equals(personalInformation.getByyx()) && !personalInformation.getByyx().equals(personalInformation2.getByyx())) {
            List<HRset> hRseByyxList = ihRsetService.queryByConditions(new HRset("byyx", personalInformation.getByyx()));
            if (hRseByyxList != null && hRseByyxList.size() == 1) {
                baseInformation.setByyxid(hRseByyxList.get(0).getId());
            } else if (hRseByyxList == null || hRseByyxList.size() == 0) {
                Integer byyxid = ihRsetService.addOne(new HRset("byyx", personalInformation.getByyx()));
                baseInformation.setByyxid(byyxid);
            }
            changeInformation.setChangeinformation("毕业院校");
            changeInformation.setBeforeinformation(personalInformation2.getByyx());
            changeInformation.setAfterinformation(personalInformation.getByyx());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        if (personalInformation.getSxzy() != null && !"null".equals(personalInformation.getSxzy())
                && !"".equals(personalInformation.getSxzy()) && !personalInformation.getSxzy().equals(personalInformation2.getSxzy())) {
            List<HRset> hRsetSxzyList = ihRsetService.queryByConditions(new HRset("sxzy", personalInformation.getSxzy()));
            if (hRsetSxzyList != null && hRsetSxzyList.size() == 1) {
                baseInformation.setSxzyid(hRsetSxzyList.get(0).getId());
            } else if (hRsetSxzyList == null || hRsetSxzyList.size() == 0) {
                Integer sxzyid = ihRsetService.addOne(new HRset("sxzy", personalInformation.getSxzy()));
                baseInformation.setSxzyid(sxzyid);
            }
            changeInformation.setChangeinformation("所学专业");
            changeInformation.setBeforeinformation(personalInformation2.getSxzy());
            changeInformation.setAfterinformation(personalInformation.getSxzy());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        List<HRset> hRsetPyfsList = ihRsetService.queryByConditions(new HRset("pyfs", personalInformation.getPyfs()));
        if (hRsetPyfsList != null && hRsetPyfsList.size() == 1 && !personalInformation.getPyfs().equals(personalInformation2.getPyfs())) {
            baseInformation.setPyfsid(hRsetPyfsList.get(0).getId());
            changeInformation.setChangeinformation("培养方式");
            changeInformation.setBeforeinformation(personalInformation2.getPyfs());
            changeInformation.setAfterinformation(personalInformation.getPyfs());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        List<HRset> hRsetFirstlaList = ihRsetService.queryByConditions(new HRset("fla", personalInformation.getFirstla()));
        if (hRsetFirstlaList != null && hRsetFirstlaList.size() == 1 && !personalInformation.getFirstla().equals(personalInformation2.getFirstla())) {
            baseInformation.setFirstlaid(hRsetFirstlaList.get(0).getId());
            changeInformation.setChangeinformation("第一外语");
            changeInformation.setBeforeinformation(personalInformation2.getFirstla());
            changeInformation.setAfterinformation(personalInformation.getFirstla());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        List<HRset> hRsetElselaList = ihRsetService.queryByConditions(new HRset("fla", personalInformation.getElsela()));
        if (hRsetElselaList != null && hRsetElselaList.size() == 1 && !personalInformation.getElsela().equals(personalInformation2.getElsela())) {
            baseInformation.setElselaid(hRsetElselaList.get(0).getId());
            changeInformation.setChangeinformation("其他外语");
            changeInformation.setBeforeinformation(personalInformation2.getElsela());
            changeInformation.setAfterinformation(personalInformation.getElsela());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        List<HRset> hRsetPosttitleList = ihRsetService.queryByConditions(new HRset("posttitle", personalInformation.getPosttitle()));
        if (hRsetPosttitleList != null && hRsetPosttitleList.size() == 1 && !personalInformation.getPosttitle().equals(personalInformation2.getPosttitle())) {
            baseInformation.setPosttitleid(hRsetPosttitleList.get(0).getId());
            changeInformation.setChangeinformation("职称");
            changeInformation.setBeforeinformation(personalInformation2.getPosttitle());
            changeInformation.setAfterinformation(personalInformation.getPosttitle());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        List<HRset> hRsetZyzstypeList = ihRsetService.queryByConditions(new HRset("zyzstype", personalInformation.getZyzstype()));
        if (hRsetZyzstypeList != null && hRsetZyzstypeList.size() == 1 && !personalInformation.getZyzstype().equals(personalInformation2.getZyzstype())) {
            baseInformation.setZyzstypeid(hRsetZyzstypeList.get(0).getId());
            changeInformation.setChangeinformation("职业证书类型");
            changeInformation.setBeforeinformation(personalInformation2.getZyzstype());
            changeInformation.setAfterinformation(personalInformation.getZyzstype());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }

        if (personalInformation.getZyzsname() != null && !"null".equals(personalInformation.getZyzsname())
                && !"".equals(personalInformation.getZyzsname()) && !personalInformation.getZyzsname().equals(personalInformation2.getZyzsname())) {
            List<HRset> hRsetZyzsnameList = ihRsetService.queryByConditions(new HRset("zyzsname", personalInformation.getZyzsname()));
            if (hRsetZyzsnameList != null && hRsetZyzsnameList.size() == 1) {
                baseInformation.setZyzsnameid(hRsetZyzsnameList.get(0).getId());
            } else if (hRsetZyzsnameList == null && hRsetZyzsnameList.size() == 0) {
                Integer zyzsnameid = ihRsetService.addOne(new HRset("zyzsname", personalInformation.getZyzsname()));
                baseInformation.setZyzsnameid(zyzsnameid);
            }
            changeInformation.setChangeinformation("职业证书名称");
            changeInformation.setBeforeinformation(personalInformation2.getZyzsname());
            changeInformation.setAfterinformation(personalInformation.getZyzsname());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (!personalInformation.getFirstworkingtime().equals(personalInformation2.getFirstworkingtime())) {
            baseInformation.setFirstworkingtime(personalInformation.getFirstworkingtime());
            changeInformation.setChangeinformation("首次参加工作时间");
            changeInformation.setBeforeinformation(personalInformation2.getFirstworkingtime());
            changeInformation.setAfterinformation(personalInformation.getFirstworkingtime());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (personalInformation.getParentcompany() != null && !"null".equals(personalInformation.getParentcompany())
                && !"".equals(personalInformation.getParentcompany()) && !personalInformation.getParentcompany().equals(personalInformation2.getParentcompany())) {
            List<HRset> hRsetParentcompanyList = ihRsetService.queryByConditions(new HRset("parentcompany", personalInformation.getParentcompany()));
            if (hRsetParentcompanyList != null && hRsetParentcompanyList.size() == 1) {
                baseInformation.setParentcompanyid(hRsetParentcompanyList.get(0).getId());
            } else if (hRsetParentcompanyList == null && hRsetParentcompanyList.size() == 0) {
                Integer parentcompanyid = ihRsetService.addOne(new HRset("parentcompany", personalInformation.getParentcompany()));
                baseInformation.setParentcompanyid(parentcompanyid);
            }
            changeInformation.setChangeinformation("上家雇主");
            changeInformation.setBeforeinformation(personalInformation2.getParentcompany());
            changeInformation.setAfterinformation(personalInformation.getParentcompany());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (baseBL) {
            if (baseInformation.getId() == null) {
                Integer baseInformationId = iBaseInformationService.saveOne(baseInformation);
                personalInformation.setBaseinformationid(baseInformationId);
            } else {
                iBaseInformationService.modifyOne(baseInformation);
            }
        }

        // 在tb_id_personalinformation表中添加数据
        if (!personalInformation.getEmployeenumber().equals(personalInformation2.getEmployeenumber())) {
            changeInformation.setChangeinformation("工号");
            changeInformation.setBeforeinformation(personalInformation2.getEmployeenumber());
            changeInformation.setAfterinformation(personalInformation.getEmployeenumber());
            iChangeInformationService.addOne(changeInformation);
        }
        iPersonalInformationService.modifyOne(personalInformation);
        List<Integer> postids = new ArrayList<>();
        List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(personalInformation2.getId());
        for (PerAndPostRs pp : perAndPostRs
        ) {
            postids.add(pp.getPostid());
        }
        return RespUtil.successResp("200", "信息提交成功！", postids);
    }

    /**
     * @Author:ShiYun;
     * @Description:修改人事信息的管理信息
     * @Date: 15:59 2018\4\12 0012
     */
    @RequestMapping("/updateManageInformation")
    @ResponseBody
    public Object updateManageInformation(
            PersonalInformation personalInformation,
            @RequestParam("transactorusername") String transactorusername
    ) throws ParseException {
        //修改信息痕迹的总标识
        Boolean listBL = false;
        //原来的信息
        PersonalInformation personalInformation2 = getOnePersonalinformation(iPersonalInformationService.queryOneByUserid(personalInformation.getUserid()).getId());
        //添加修改信息
        ChangeInformation changeInformation = new ChangeInformation();
        //1.姓名
        changeInformation.setChangeduserid(personalInformation.getUserid());
        //2.变更原因
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        //3.变更时间
        changeInformation.setChangedate(changedate);
        User transactor = new User();
        transactor.setUsername(transactorusername);
        //4.办理人
        changeInformation.setTransactoruserid(iUserService.selectByCondition(transactor).get(0).getId());
        //5.变更项目；6.变更前内容；7.变更后内容

        if (personalInformation2.getDepid() != personalInformation.getDepid()) {
            changeInformation.setChangeinformation("部门");
            changeInformation.setBeforeinformation(personalInformation2.getDepname());
            changeInformation.setAfterinformation(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getDepname());
            personalInformation2.setDepid(personalInformation.getDepid());
            iChangeInformationService.addOne(changeInformation);
            listBL = true;
        }

        List<Integer> postids = personalInformation.getPostids();
        //返回值
        Boolean re = false;
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < postids.size(); i++) {
            strs.add(iPostService.queryOneByPostid(postids.get(i)).getPostname());
        }
        if (personalInformation2.getPostnames() != null && !personalInformation2.getPostnames().equals(IDcodeUtil.getArrayToString(strs, ";"))) {
            re = true;
            changeInformation.setChangeinformation("岗位");
            changeInformation.setBeforeinformation(personalInformation2.getPostnames());
            changeInformation.setAfterinformation(IDcodeUtil.getArrayToString(strs, ";"));
            if (personalInformation2.getId() != null) {
                iPerandpostrsService.removeByPerid(personalInformation2.getId());//先删除在添加（岗位）
            }
            for (int i = 0; i < postids.size(); i++) {
                PerAndPostRs perAndPostRs = new PerAndPostRs();
                perAndPostRs.setPostid(postids.get(i));
                perAndPostRs.setPerid(personalInformation2.getId());
                iPerandpostrsService.addOne(perAndPostRs);
            }
            iChangeInformationService.addOne(changeInformation);
        }

        //添加manageinformation标识
        Boolean manBl = false;
        ManageInformation manageInformation = iManageInformationService.queryOneById(personalInformation2.getManageinformationid());
        if (manageInformation == null) {
            manageInformation = new ManageInformation();//不存在则新建
        }

        List<HRset> hRsetRankList = ihRsetService.queryByConditions(new HRset("rank", personalInformation.getZj()));
        if (hRsetRankList != null && hRsetRankList.size() == 1 && !personalInformation.getZj().equals(personalInformation2.getZj())) {
            changeInformation.setChangeinformation("职级");
            changeInformation.setBeforeinformation(personalInformation2.getZj());
            changeInformation.setAfterinformation(personalInformation.getZj());
            manageInformation.setRankid(hRsetRankList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            manBl = true;
        }

        List<HRset> hRsetEmployeetypeList = ihRsetService.queryByConditions(new HRset("employeetype", personalInformation.getEmployeetype()));
        if (hRsetEmployeetypeList != null && hRsetEmployeetypeList.size() == 1 && !personalInformation.getEmployeetype().equals(personalInformation2.getEmployeetype())) {
            changeInformation.setChangeinformation("员工类型");
            changeInformation.setBeforeinformation(personalInformation2.getEmployeetype());
            changeInformation.setAfterinformation(personalInformation.getEmployeetype());
            manageInformation.setEmployeetypeid(hRsetEmployeetypeList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            manBl = true;
        }

        if (personalInformation2 != null && personalInformation2.getEntrydate() != null && !personalInformation2.getEntrydate().equals(personalInformation.getEntrydate())) {
            changeInformation.setChangeinformation("入职时间");
            changeInformation.setBeforeinformation(personalInformation2.getEntrydate());
            changeInformation.setAfterinformation(personalInformation.getEntrydate());
            manageInformation.setEntrydate(personalInformation.getEntrydate());
            iChangeInformationService.addOne(changeInformation);
            manBl = true;
        } else if (personalInformation2 != null && personalInformation2.getEntrydate() == null) {
            manageInformation.setEntrydate(personalInformation.getEntrydate());
            manBl = true;
        }

        //入职时间存在的情况再设置转正时间
        if (personalInformation.getEntrydate() != null && !"".equals(personalInformation.getEntrydate())) {
            //转正时间不存在则自动向后两个月
            if (personalInformation.getZhuanzhengdate() != null && !personalInformation.getZhuanzhengdate().equals("null") && !personalInformation.getZhuanzhengdate().equals(" ")) {
                /* if(personalInformation.getZhuanzhengdate()!=null  && !"".equals(personalInformation.getZhuanzhengdate())){*/
                if (!personalInformation.getZhuanzhengdate().equals(personalInformation2.getZhuanzhengdate())) {
                    changeInformation.setChangeinformation("转正日期");
                    changeInformation.setBeforeinformation(personalInformation2.getZhuanzhengdate());
                    changeInformation.setAfterinformation(personalInformation.getZhuanzhengdate());
                    manageInformation.setZhuanzhengdate(personalInformation.getZhuanzhengdate());
                    iChangeInformationService.addOne(changeInformation);
                    manBl = true;
                }
            } else {
                if (!IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate().equals("null") ? null : personalInformation.getEntrydate()).equals(personalInformation2.getZhuanzhengdate())) {
                    changeInformation.setChangeinformation("转正日期");
                    changeInformation.setBeforeinformation(personalInformation2.getZhuanzhengdate());
                    changeInformation.setAfterinformation(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
                    manageInformation.setZhuanzhengdate(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
                    iChangeInformationService.addOne(changeInformation);
                    manBl = true;
                }
            }
        }

        if (manBl) {
            if (manageInformation.getId() != null) {
                iManageInformationService.modifyOne(manageInformation);
            } else {
                Integer manageinformationid = iManageInformationService.saveOne(manageInformation);
                personalInformation2.setManageinformationid(manageinformationid);
            }
        }

        iPersonalInformationService.modifyOne(personalInformation2);
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", personalInformation2.getUsername());
        map.put("isactive", personalInformation2.getIsactive());
        map.put("truename", personalInformation2.getTruename());
        map.put("postids", personalInformation.getPostids());

        return RespUtil.successResp("200", "提交信息成功！", map);
    }

    /**
     * @Author:ShiYun;
     * @Description:修改成本信息
     * @Date: 16:28 2018\4\12 0012
     */
    @RequestMapping("/updateCostInformation")
    @ResponseBody
    public String updateCostInformation(
            PersonalInformation personalInformation,
            @RequestParam("transactorusername") String transactorusername,
            @RequestParam("khhvalue") String khhvalue,
            @RequestParam("sbjndvalue") String sbjndvalue
    ) throws ParseException {
        //修改信息痕迹的总标识
        Boolean listBL = false;
        //原来的信息
        PersonalInformation personalInformation2 = getOnePersonalinformation(iPersonalInformationService.queryOneByUserid(personalInformation.getUserid()).getId());
        //添加修改信息
        ChangeInformation changeInformation = new ChangeInformation();
        //1.姓名
        changeInformation.setChangeduserid(personalInformation.getUserid());
        //2.变更原因
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        //3.变更时间
        changeInformation.setChangedate(changedate);
        User transactor = new User();
        transactor.setUsername(transactorusername);
        //4.办理人
        changeInformation.setTransactoruserid(iUserService.selectByCondition(transactor).get(0).getId());
        //5.变更项目；6.变更前内容；7.变更后内容

        //添加成本修改标识
        Boolean costBL = false;
        CostInformation costInformation = iCostInformationService.queryOneById(personalInformation2.getCostinformationid());
        if (costInformation == null) {
            costInformation = new CostInformation();//不存在则新建
        }

        List<HRset> hRsetSalaryList = ihRsetService.queryByConditions(new HRset("salary", personalInformation.getSalary()));
        if (hRsetSalaryList != null && hRsetSalaryList.size() == 1 && !personalInformation.getSalary().equals(personalInformation2.getSalary())) {
            changeInformation.setChangeinformation("薪资标准");
            changeInformation.setBeforeinformation(personalInformation2.getSalary());
            changeInformation.setAfterinformation(personalInformation.getSalary());
            costInformation.setSalarystandardid(hRsetSalaryList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getKhh() != null && !"".equals(personalInformation.getKhh())
                && !personalInformation.getKhh().equals(personalInformation2.getKhh())) {
            List<HRset> hRsetKhhList = ihRsetService.queryByConditions(new HRset("khh", personalInformation.getKhh()));
            if (hRsetKhhList != null && hRsetKhhList.size() == 1) {
                costInformation.setKhhid(hRsetKhhList.get(0).getId());
            } else if (hRsetKhhList == null || hRsetKhhList.size() == 0) {
                Integer khhid = ihRsetService.addOne(new HRset("khh", personalInformation.getKhh()));
                costInformation.setKhhid(khhid);
            }
            changeInformation.setChangeinformation("开户行");
            changeInformation.setBeforeinformation(personalInformation2.getKhh());
            changeInformation.setAfterinformation(personalInformation.getKhh());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getSalaryaccount() != null && !"".equals(personalInformation.getSalaryaccount()) && !personalInformation.getSalaryaccount().equals(personalInformation2.getSalaryaccount())) {
            changeInformation.setChangeinformation("工资账号");
            changeInformation.setBeforeinformation(personalInformation2.getSalaryaccount());
            changeInformation.setAfterinformation(personalInformation.getSalaryaccount());
            costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getSbjnd() != null && !"".equals(personalInformation.getSbjnd())
                && !personalInformation.getSbjnd().equals(personalInformation2.getSbjnd())) {
            List<HRset> hRsetSbjndList = ihRsetService.queryByConditions(new HRset("sbjnd", personalInformation.getSbjnd()));
            if (hRsetSbjndList != null && hRsetSbjndList.size() == 1) {
                costInformation.setSbjndid(hRsetSbjndList.get(0).getId());
            } else if (hRsetSbjndList == null && hRsetSalaryList.size() == 0) {
                Integer sbjndid = ihRsetService.addOne(new HRset("sbjnd", personalInformation.getSbjnd()));
                costInformation.setSbjndid(sbjndid);
            }
            changeInformation.setChangeinformation("社保缴纳地");
            changeInformation.setBeforeinformation(personalInformation2.getSbjnd());
            changeInformation.setAfterinformation(personalInformation.getSbjnd());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getSbcode() != null && !"".equals(personalInformation.getSbcode()) && !personalInformation.getSbcode().equals(personalInformation2.getSbcode())) {
            changeInformation.setChangeinformation("社保账号");
            changeInformation.setBeforeinformation(personalInformation2.getSbcode());
            changeInformation.setAfterinformation(personalInformation.getSbcode());
            costInformation.setSbcode(personalInformation.getSbcode());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getGjjcode() != null && !"".equals(personalInformation.getGjjcode()) && !personalInformation.getGjjcode().equals(personalInformation2.getGjjcode())) {
            changeInformation.setChangeinformation("公积金账号");
            changeInformation.setBeforeinformation(personalInformation2.getGjjcode());
            changeInformation.setAfterinformation(personalInformation.getGjjcode());
            costInformation.setGjjcode(personalInformation.getGjjcode());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        List<HRset> hRsetSsbList = ihRsetService.queryByConditions(new HRset("ssb", personalInformation.getSsb()));
        if (hRsetSsbList != null && hRsetSsbList.size() == 1 && personalInformation.getSsb() != null && !"".equals(personalInformation.getSsb())
                && !personalInformation.getSsb().equals(personalInformation2.getSsb())) {
            changeInformation.setChangeinformation("社保基数");
            changeInformation.setBeforeinformation(personalInformation2.getSsb());
            changeInformation.setAfterinformation(personalInformation.getSsb());
            costInformation.setSsbid(hRsetSsbList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        List<HRset> hRsetSsbgscdList = ihRsetService.queryByConditions(new HRset("ssbgscd", personalInformation.getSsbgscd()));
        if (hRsetSsbgscdList != null && hRsetSsbgscdList.size() == 1 && personalInformation.getSsbgscd() != null
                && !"".equals(personalInformation.getSsbgscd()) && !personalInformation.getSsbgscd().equals(personalInformation2.getSsbgscd())) {
            changeInformation.setChangeinformation("社保公司缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getSsbgscd());
            changeInformation.setAfterinformation(personalInformation.getSsbgscd());
            costInformation.setSsbgscdid(hRsetSsbgscdList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        List<HRset> hRsetSsbgrcdList = ihRsetService.queryByConditions(new HRset("ssbgrcd", personalInformation.getSsbgrcd()));
        if (hRsetSsbgrcdList != null && hRsetSsbgrcdList.size() == 1 && personalInformation.getSsbgrcd() != null && !"".equals(personalInformation.getSsbgrcd()) && !personalInformation.getSsbgrcd().equals(personalInformation2.getSsbgrcd())) {
            changeInformation.setChangeinformation("社保个人缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getSsbgrcd());
            changeInformation.setAfterinformation(personalInformation.getSsbgrcd());
            costInformation.setSsbgrcdid(hRsetSsbgrcdList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        List<HRset> hRsetGjjList = ihRsetService.queryByConditions(new HRset("gjj", personalInformation.getGjj()));
        if (hRsetGjjList != null && hRsetGjjList.size() == 1 && personalInformation.getGjj() != null && !"".equals(personalInformation.getGjj()) && !personalInformation.getGjj().equals(personalInformation2.getGjj())) {
            changeInformation.setChangeinformation("公积金基数");
            changeInformation.setBeforeinformation(personalInformation2.getGjj());
            changeInformation.setAfterinformation(personalInformation.getGjj());
            costInformation.setGjjid(hRsetGjjList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        List<HRset> hRsetGjjgscdList = ihRsetService.queryByConditions(new HRset("gjjgscd", personalInformation.getGjjgscd()));
        if (hRsetGjjgscdList != null && hRsetGjjgscdList.size() == 1 && personalInformation.getGjjgscd() != null && !"".equals(personalInformation.getGjjgscd())
                && !personalInformation.getGjjgscd().equals(personalInformation2.getGjjgscd())) {
            changeInformation.setChangeinformation("公积金公司缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getGjjgscd());
            changeInformation.setAfterinformation(personalInformation.getGjjgscd());
            costInformation.setGjjgscdid(hRsetGjjgscdList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        List<HRset> hRsetGjjgrcdList = ihRsetService.queryByConditions(new HRset("gjjgrcd", personalInformation.getGjjgrcd()));
        if (hRsetGjjgrcdList != null && hRsetGjjgrcdList.size() == 1 && personalInformation.getGjjgrcd() != null && !"".equals(personalInformation.getGjjgrcd()) && !personalInformation.getGjjgrcd().equals(personalInformation2.getGjjgrcd())) {
            changeInformation.setChangeinformation("公积金个人缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getGjjgrcd());
            changeInformation.setAfterinformation(personalInformation.getGjjgrcd());
            costInformation.setGjjgrcdid(hRsetGjjgrcdList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (costBL) {
            if (costInformation.getId() != null) {
                iCostInformationService.modifyOne(costInformation);
            } else {
                Integer costinformationid = iCostInformationService.saveOne(costInformation);
                personalInformation2.setCostinformationid(costinformationid);
            }
        }

        iPersonalInformationService.modifyOne(personalInformation2);
        return "提交信息成功！";
    }

    /**
     * @Author:ShiYun;
     * @Description:修改其他信息
     * @Date: 16:46 2018\4\12 0012
     */
    @RequestMapping("/updateOtherInformation")
    @ResponseBody
    public Object updateOtherInformation(
            PersonalInformation personalInformation,
            @RequestParam("transactorusername") String transactorusername
    ) throws ParseException {
        //修改信息痕迹的总标识
        Boolean listBL = false;
        //原来的信息
        PersonalInformation personalInformation2 = getOnePersonalinformation(iPersonalInformationService.queryOneByUserid(personalInformation.getUserid()).getId());
        //添加修改信息
        ChangeInformation changeInformation = new ChangeInformation();
        //1.姓名
        changeInformation.setChangeduserid(personalInformation.getUserid());
        //2.变更原因
        changeInformation.setChangereason("正常修改信息");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String changedate = simpleDateFormat.format(new Date());
        //3.变更时间
        changeInformation.setChangedate(changedate);
        User transactor = new User();
        transactor.setUsername(transactorusername);
        //4.办理人
        changeInformation.setTransactoruserid(iUserService.selectByCondition(transactor).get(0).getId());
        //5.变更项目；6.变更前内容；7.变更后内容

        // 添加标识
        Boolean otherBL = false;
        OtherInformation otherInformation = iOtherInformationService.queryOneById(personalInformation2.getOtherinformationid());
        if (otherInformation == null) {
            otherInformation = new OtherInformation();//不存在则新建
        }

        List<HRset> hRsetTelphoneList = ihRsetService.queryByConditions(new HRset("telphone", personalInformation.getTelphone()));
        if (hRsetTelphoneList != null && hRsetTelphoneList.size() == 1 && !personalInformation.getTelphone().equals(personalInformation2.getTelphone())) {
            changeInformation.setChangeinformation("办公电话");
            changeInformation.setBeforeinformation(personalInformation2.getTelphone());
            changeInformation.setAfterinformation(personalInformation.getTelphone());
            personalInformation2.setTelphoneid(hRsetTelphoneList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            listBL = true;
        }

        if (personalInformation.getMobilephone() != null && !"".equals(personalInformation.getMobilephone()) && !personalInformation.getMobilephone().equals(personalInformation2.getMobilephone())) {
            changeInformation.setChangeinformation("移动电话");
            changeInformation.setBeforeinformation(personalInformation2.getMobilephone());
            changeInformation.setAfterinformation(personalInformation.getMobilephone());
            personalInformation2.setMobilephone(personalInformation.getMobilephone());
            iChangeInformationService.addOne(changeInformation);
            listBL = true;
        }

        if (personalInformation.getCompanyemail() != null && !"".equals(personalInformation.getCompanyemail()) && !personalInformation.getCompanyemail().equals(personalInformation2.getCompanyemail())) {
            changeInformation.setChangeinformation("公司邮件");
            changeInformation.setBeforeinformation(personalInformation2.getCompanyemail());
            changeInformation.setAfterinformation(personalInformation.getCompanyemail());
            otherInformation.setCompanyemail(personalInformation.getCompanyemail());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getPrivateemail() != null && !"".equals(personalInformation.getPrivateemail()) && !personalInformation.getPrivateemail().equals(personalInformation2.getPrivateemail())) {
            changeInformation.setChangeinformation("私人邮件");
            changeInformation.setBeforeinformation(personalInformation2.getPrivateemail());
            changeInformation.setAfterinformation(personalInformation.getPrivateemail());
            otherInformation.setPrivateemail(personalInformation.getPrivateemail());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getEmergencycontract() != null && !"".equals(personalInformation.getEmergencycontract()) && !personalInformation.getEmergencycontract().equals(personalInformation2.getEmergencycontract())) {
            changeInformation.setChangeinformation("应急联系人");
            changeInformation.setBeforeinformation(personalInformation2.getEmergencycontract());
            changeInformation.setAfterinformation(personalInformation.getEmergencycontract());
            otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getEmergencyphone() != null && !"".equals(personalInformation.getEmergencyphone()) && !personalInformation.getEmergencyphone().equals(personalInformation2.getEmergencyphone())) {
            changeInformation.setChangeinformation("应急联系电话");
            changeInformation.setBeforeinformation(personalInformation2.getEmergencyphone());
            changeInformation.setAfterinformation(personalInformation.getEmergencyphone());
            otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getAddress() != null && !"".equals(personalInformation.getAddress()) && !personalInformation.getAddress().equals(personalInformation2.getAddress())) {
            changeInformation.setChangeinformation("应急联系地址");
            changeInformation.setBeforeinformation(personalInformation2.getAddress());
            changeInformation.setAfterinformation(personalInformation2.getAddress());
            otherInformation.setAddress(personalInformation.getAddress());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getRemark() != null && !"".equals(personalInformation.getRemark()) && !personalInformation.getRemark().equals(personalInformation2.getRemark())) {
            changeInformation.setChangeinformation("备注");
            changeInformation.setBeforeinformation(personalInformation2.getRemark());
            changeInformation.setAfterinformation(personalInformation.getRemark());
            otherInformation.setRemark(personalInformation.getRemark());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        List<HRset> hRsetEmergencyrpList = ihRsetService.queryByConditions(new HRset("emergencyrp", personalInformation.getEmergencyrp()));
        if (hRsetEmergencyrpList != null && hRsetEmergencyrpList.size() == 1 && !personalInformation.getEmergencyrp().equals(personalInformation2.getEmergencyrp())) {
            changeInformation.setChangeinformation("应急联系人关系");
            changeInformation.setBeforeinformation(personalInformation2.getEmergencyrp());
            changeInformation.setAfterinformation(personalInformation.getEmergencyrp());
            otherInformation.setEmergencyrpid(hRsetEmergencyrpList.get(0).getId());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (otherBL) {
            if (otherInformation.getId() != null) {
                iOtherInformationService.modifyOne(otherInformation);
            } else {
                Integer otherinformationid = iOtherInformationService.saveOne(otherInformation);
                personalInformation2.setOtherinformationid(otherinformationid);
                listBL = true;
            }
        }

        if (listBL) {
            iPersonalInformationService.modifyOne(personalInformation2);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("username", personalInformation2.getUsername());
        map.put("isactive", personalInformation2.getIsactive());
        map.put("truename", personalInformation2.getTruename());
        map.put("postids", personalInformation2.getPostids());
        return RespUtil.successResp("200", "提交成功！", map);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据部门id查询人事信息
     * @Date: 16:40 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByDepid")
    @ResponseBody
    public List<PersonalInformation> queryPersonalInformationsByDepid(
            @RequestParam("depid") Integer depid
    ) {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setDepid(depid);
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryPIs(personalInformation);
        for (Integer i = 0; i < personalInformations.size(); i++) {
            Dept dept = iDeptService.queryOneDepByDepid(personalInformations.get(i).getDepid());
            personalInformations.get(i).setDepname(dept.getDepname());
            User user = iUserService.getById(personalInformations.get(i).getUserid());
            personalInformations.get(i).setTruename(user.getTruename());
        }
        return personalInformations;
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有员工不分页(导出用)
     * @Date: 17:23 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByNull")
    @ResponseBody
    public List<PersonalInformation> queryPersonalInformationsByNull() throws ParseException {
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryAllByNull();
        for (PersonalInformation pi : personalInformations) {
            //添加信息user的三个的字段
            User user = iUserService.getById(pi.getUserid());
            pi.setIsactive(user.getIsactive());
            pi.setUsername(user.getUsername());
            pi.setTruename(user.getTruename());
            pi.setState(user.getState().toString());

            //添加信息personalinformation的六个字段
            Dept dept = new Dept();
            dept = iDeptService.queryOneDepByDepid(pi.getDepid());
            if (dept != null) {
                pi.setDepname(dept.getDepname());
                pi.setDepcode(dept.getDepcode());
            } else {
                pi.setDepname("部门信息还未添加");
                pi.setDepcode("部门编号还未添加");
            }

            //获得岗位信息
            List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(pi.getId());
            List<String> postnames = new ArrayList<>();
            if (perAndPostRs.size() != 0) {
                for (int i = 0; i < perAndPostRs.size(); i++) {
                    if (iPostService.queryOneByPostid(perAndPostRs.get(i).getPostid()) != null) {
                        postnames.add(iPostService.queryOneByPostid(perAndPostRs.get(i).getPostid()).getPostname());
                    }
                }
            }
            pi.setPostnames(IDcodeUtil.getArrayToString(postnames, ";"));

            //获得办公电话
            HRset hRsetTelphone = ihRsetService.queryById(pi.getTelphoneid());
            if (hRsetTelphone != null) pi.setTelphone(hRsetTelphone.getDatavalue());

            //添加baseinformation的28个字段
            BaseInformation baseInformation = iBaseInformationService.queryOneById(pi.getBaseinformationid());
            if (baseInformation != null) {
                pi.setUserphoto(baseInformation.getUserphoto());
                pi.setIdphoto1(baseInformation.getIdphoto1());
                pi.setIdphoto2(baseInformation.getIdphoto2());
                pi.setEnglishname(baseInformation.getEnglishname());
                pi.setIdcode(baseInformation.getIdcode());
                try {
                    pi.setAge(IDcodeUtil.getAge(baseInformation.getBirthday()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (ihRsetService.queryById(baseInformation.getRaceid()) != null) {
                    pi.setRace(ihRsetService.queryById(baseInformation.getRaceid()).getDatavalue());
                }
                pi.setMarriage(baseInformation.getMarriage());
                if (ihRsetService.queryById(baseInformation.getChildrenid()) != null) {
                    pi.setChildren(ihRsetService.queryById(baseInformation.getChildrenid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getZzmmid()) != null) {
                    pi.setZzmm(ihRsetService.queryById(baseInformation.getZzmmid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getZgxlid()) != null) {
                    pi.setZgxl(ihRsetService.queryById(baseInformation.getZgxlid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getByyxid()) != null) {
                    pi.setByyx(ihRsetService.queryById(baseInformation.getByyxid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getSxzyid()) != null) {
                    pi.setSxzy(ihRsetService.queryById(baseInformation.getSxzyid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getPyfsid()) != null) {
                    pi.setPyfs(ihRsetService.queryById(baseInformation.getPyfsid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getFirstlaid()) != null) {
                    pi.setFirstla(ihRsetService.queryById(baseInformation.getFirstlaid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getElselaid()) != null) {
                    pi.setElsela(ihRsetService.queryById(baseInformation.getElselaid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getPosttitleid()) != null) {
                    pi.setPosttitle(ihRsetService.queryById(baseInformation.getPosttitleid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getZyzstypeid()) != null) {
                    pi.setZyzstype(ihRsetService.queryById(baseInformation.getZyzstypeid()).getDatavalue());
                }
                if (ihRsetService.queryById(baseInformation.getZyzsnameid()) != null) {
                    pi.setZyzsname(ihRsetService.queryById(baseInformation.getZyzsnameid()).getDatavalue());
                }
                pi.setFirstworkingtime(baseInformation.getFirstworkingtime());
                if (baseInformation.getFirstworkingtime() != null && !baseInformation.getFirstworkingtime().equals("")) {
                    try {
                        pi.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (ihRsetService.queryById(baseInformation.getParentcompanyid()) != null) {
                    pi.setParentcompany(ihRsetService.queryById(baseInformation.getParentcompanyid()).getDatavalue());
                }
            }

            //添加manageinformation的六个字段
            ManageInformation manageInformation = iManageInformationService.queryOneById(pi.getManageinformationid());
            if (manageInformation != null) {
                if (ihRsetService.queryById(manageInformation.getRankid()) != null) {
                    pi.setZj(ihRsetService.queryById(manageInformation.getRankid()).getDatavalue());
                }
                pi.setEntrydate(manageInformation.getEntrydate());
                pi.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
                if (ihRsetService.queryById(manageInformation.getEmployeetypeid()) != null) {
                    pi.setEmployeetype(ihRsetService.queryById(manageInformation.getEmployeetypeid()).getDatavalue());
                }
            }

            //添加costinformation的18个字段
            CostInformation costInformation = iCostInformationService.queryOneById(pi.getCostinformationid());
            if (costInformation != null) {
                if (ihRsetService.queryById(costInformation.getSalarystandardid()) != null) {
                    pi.setSalary(ihRsetService.queryById(costInformation.getSalarystandardid()).getDatavalue());
                }
                if (ihRsetService.queryById(costInformation.getSsbid()) != null) {
                    pi.setSsb(ihRsetService.queryById(costInformation.getSsbid()).getDatavalue());
                }
                if (ihRsetService.queryById(costInformation.getSsbgscdid()) != null) {
                    pi.setSsbgscd(ihRsetService.queryById(costInformation.getSsbgscdid()).getDatatype());
                }
                if (ihRsetService.queryById(costInformation.getSsbgrcdid()) != null) {
                    pi.setSsbgrcd(ihRsetService.queryById(costInformation.getSsbgrcdid()).getDatavalue());
                }
                if (ihRsetService.queryById(costInformation.getGjjid()) != null) {
                    pi.setGjj(ihRsetService.queryById(costInformation.getGjjid()).getDatavalue());
                }
                if (ihRsetService.queryById(costInformation.getGjjgscdid()) != null) {
                    pi.setGjjgscd(ihRsetService.queryById(costInformation.getGjjgscdid()).getDatavalue());
                }
                if (ihRsetService.queryById(costInformation.getGjjgrcdid()) != null) {
                    pi.setGjjgrcd(ihRsetService.queryById(costInformation.getGjjgrcdid()).getDatavalue());
                }
                if (ihRsetService.queryById(costInformation.getKhhid()) != null) {
                    pi.setKhh(ihRsetService.queryById(costInformation.getKhhid()).getDatavalue());
                }
                pi.setSalaryaccount(costInformation.getSalaryaccount());
                if (ihRsetService.queryById(costInformation.getSbjndid()) != null) {
                    pi.setSbjnd(ihRsetService.queryById(costInformation.getSbjndid()).getDatavalue());
                }
                pi.setSbcode(costInformation.getSbcode());
                pi.setGjjcode(costInformation.getGjjcode());
            }

            //添加otherinformation的六个字段
            OtherInformation otherInformation = iOtherInformationService.queryOneById(pi.getOtherinformationid());
            if (otherInformation != null) {
                pi.setPrivateemail(otherInformation.getPrivateemail());
                pi.setCompanyemail(otherInformation.getCompanyemail());
                pi.setEmergencycontract(otherInformation.getEmergencycontract());
                if (ihRsetService.queryById(otherInformation.getEmergencyrpid()) != null) {
                    pi.setEmergencyrp(ihRsetService.queryById(otherInformation.getEmergencyrpid()).getDatavalue());
                }
                pi.setEmergencyphone(otherInformation.getEmergencyphone());
                pi.setAddress(otherInformation.getAddress());
                pi.setRemark(otherInformation.getRemark());
            }
        }
        return personalInformations;
    }

    /**
     * @Author:ShiYun;
     * @Description:数据的导入
     * @Date: 15:09 2018\5\7 0007
     */
    @RequestMapping("/importPersonalInformations")
    @ResponseBody
    public Object importPersonalInformations(
            @RequestParam("file") MultipartFile multipartFile
    ) {
        Map<String, String> goToPost = new HashMap<>();
        ReadPersonalinformationExcel readExcel = new ReadPersonalinformationExcel();
        List<PersonalInformation> personalInformationList = readExcel.getExcelInfo(multipartFile);
        if (personalInformationList != null) {
            for (int i = 0; i < personalInformationList.size(); i++) {
                PersonalInformation personalInformation = personalInformationList.get(i);
                //先获得员工号
                String employeenumber = personalInformation.getEmployeenumber();
                if(StringUtils.isEmpty(employeenumber))continue;
                PersonalInformation per = iPersonalInformationService.queryByEmployeenumber(personalInformation.getEmployeenumber());
                if (null==per) {
                    if(null==personalInformation.getEmployeenumber() || "".equals(personalInformation.getEmployeenumber())){
                        goToPost.put(personalInformation.getEmployeenumber(), "此工号的数据数据为空");
                        continue;
                    }
                    //添加数据
                    goToPost = importOnePersonalInformation_ADD(personalInformation, goToPost);
                } else{
                    //说明信息已经存在，下面查询名字是否正确
                    goToPost = importOnePersonalInformation_UPDATE(personalInformation, goToPost, per);
                }
            }
        }
        return RespUtil.successResp("500", "请求成功", JSON.toJSONString(goToPost));
    }

    /**
     * @return void
     * @Author: shiyun
     * @Description: 数据导入时的每条数据添加到数据库中
     * @Date 2018\11\1 0001 10:58
     * @Param personalInformation, goToPost
     **/
    public Map<String, String> importOnePersonalInformation_ADD(PersonalInformation personalInformation, Map<String, String> goToPost) {
        //如果user表中已经有了，就更新,没有就添加
        String truename = personalInformation.getTruename();
        if(StringUtils.isEmpty(truename)){
            goToPost.put(personalInformation.getEmployeenumber(),"姓名不存在，此员工无法插入！");
        }
        User removedUser = iUserService.queryByTruename(truename);
        //1.先更新/添加User表的信息(tb_id_user)===========================================================
        if (removedUser != null) {
            Boolean userB = false;
            Integer isactive = personalInformation.getIsactive();//是否激活
            if (isactive != null && isactive != removedUser.getIsactive()) {
                removedUser.setIsactive(isactive);
                userB = true;
            }
            String state = personalInformation.getState();//是否离职状态
            if (state != null && !"".equals(state)) {
                removedUser.setState(Integer.parseInt(state));
                userB = true;
            }
            String username = personalInformation.getUsername();
            if(StringUtils.isNotEmpty(username)){
                removedUser.setUsername(username);
                userB = true;
            }else {
                removedUser.setUsername(personalInformation.getTruename());
                userB = true;
            }
            if (userB) {
                iUserService.update(removedUser);
                personalInformation.setUserid(removedUser.getId());
            }
        } else {
            User user = new User();
            if (personalInformation.getIsactive() != null) {
                user.setIsactive(personalInformation.getIsactive());
            } else {
                user.setIsactive(1);
            }
            String truenameTemp = personalInformation.getTruename();
            user.setTruename(truenameTemp);
            String usernameTemp = personalInformation.getUsername();
            if (StringUtils.isNotEmpty(usernameTemp)) {
                user.setUsername(usernameTemp);
            } else if(StringUtils.isNotEmpty(truenameTemp)){
                user.setUsername(truenameTemp);
            }
            user.setPassword("123456");
            user.setState(1);
            Integer userid = iUserService.saveOne(user);
            personalInformation.setUserid(userid);
        }

        //2.在添加Baseinformation表的信息(tb_id_baseinformation)==================================================
        BaseInformation baseInformation = new BaseInformation();
        baseInformation.setUserphoto(personalInformation.getUserphoto());//免冠照片
        baseInformation.setIdphoto1(personalInformation.getIdphoto1());//身份证正面
        baseInformation.setIdphoto2(personalInformation.getIdphoto2());//身份证反面
        baseInformation.setEnglishname(personalInformation.getEnglishname());//英文名
        baseInformation.setIdcode(personalInformation.getIdcode());//身份证号码
        if (!StringUtils.isBlank(personalInformation.getIdcode())) {
            try {
                baseInformation.setBirthday(IDcodeUtil.getBirthday(personalInformation.getIdcode()));//解析出生日
                baseInformation.setConstellation(IDcodeUtil.getConstellation(personalInformation.getIdcode()));//解析出星座
                baseInformation.setChinesecs(IDcodeUtil.getChinesecs(personalInformation.getIdcode()));//解析出属相
            } catch (Exception e) {
                e.printStackTrace();
            }
            baseInformation.setHj(IDcodeUtil.getProvinceByIdcode(personalInformation.getIdcode()));
            personalInformation.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));//为后面添加tb_id_personalinformation表做准备
        }//户籍（身份证号码解析出来的）
        //HR1字段的添加或更新(民族)----------
        baseInformation.setRaceid(getHRsetidInPer(new HRset("race", personalInformation.getRace())));
        //普通字段的添加或更新(是否已婚)----------
        baseInformation.setMarriage(personalInformation.getMarriage());
        //HR2字段的添加或更新(生育情况)----------
        baseInformation.setChildrenid(getHRsetidInPer(new HRset("children", personalInformation.getChildren())));
        //HR3字段的添加或更新(政治面貌)----------
        baseInformation.setZzmmid(getHRsetidInPer(new HRset("zzmm",personalInformation.getZzmm())));
        //HR4字段的添加或更新(最高学历)----------
        baseInformation.setZgxlid(getHRsetidInPer(new HRset("zgxl",personalInformation.getZgxl())));
        //HR5字段的添加或更新(毕业院校)----------
        baseInformation.setByyxid(getHRsetidInPer(new HRset("byyx",personalInformation.getByyx())));
        //HR6字段的添加或更新(所学专业)----------
        baseInformation.setSxzyid(getHRsetidInPer(new HRset("sxzy",personalInformation.getSxzy())));
        //HR7字段的添加或更新(培养方式)----------
        baseInformation.setPyfsid(getHRsetidInPer(new HRset("pyfs",personalInformation.getPyfs())));
        //HR8字段的添加或更新(第一外语)----------
        baseInformation.setFirstlaid(getHRsetidInPer(new HRset("fla", personalInformation.getFirstla())));
        //HR9字段的添加或更新(其它外语)----------
        baseInformation.setElselaid(getHRsetidInPer(new HRset("fla", personalInformation.getElsela())));
        //HR10字段的添加或更新(职称)----------
        baseInformation.setPosttitleid(getHRsetidInPer(new HRset("posttitle", personalInformation.getPosttitle())));
        //HR11字段的添加或更新(职业证书类型)----------
        baseInformation.setZyzstypeid(getHRsetidInPer(new HRset("zyzstype", personalInformation.getZyzstype())));
        //HR12字段的添加或更新(职业证书名称)----------
        baseInformation.setZyzsnameid(getHRsetidInPer(new HRset("zyzsname", personalInformation.getZyzsname())));
        //HR13字段的添加或更新(上家雇主)----------
        baseInformation.setParentcompanyid(getHRsetidInPer(new HRset("parentcompany", personalInformation.getParentcompany())));
        //普通字段的添加或更新(首次参加工作时间)----------
        baseInformation.setFirstworkingtime(personalInformation.getFirstworkingtime());
        Integer baseinformationid = iBaseInformationService.saveOne(baseInformation);
        personalInformation.setBaseinformationid(baseinformationid);
        //3.添加管理信息表（tb_id_managerinformation）=============================================================================
        ManageInformation manageInformation = new ManageInformation();
        //HR14字段的添加或更新(职级)----------
        manageInformation.setRankid(getHRsetidInPer(new HRset("rank", personalInformation.getZj())));
        //HR15字段的添加或更新(员工类型)----------
        manageInformation.setEmployeetypeid(getHRsetidInPer(new HRset("employeetype", personalInformation.getEmployeetype())));
        //普通字段的添加或更新(入职日期)----------
        manageInformation.setEntrydate(personalInformation.getEntrydate());
        //普通字段的添加或更新(转正日期)----------
        manageInformation.setZhuanzhengdate(personalInformation.getZhuanzhengdate());
        Integer manageinformationid = iManageInformationService.saveOne(manageInformation);
        personalInformation.setManageinformationid(manageinformationid);

        //4.添加成本信息表（tb_id_costinformation）=============================================================================
        CostInformation costInformation = new CostInformation();
        //HR16字段的添加或更新(薪资标准)----------
        costInformation.setSalarystandardid(getHRsetidInPer(new HRset("salary", personalInformation.getSalary())));
        //HR17字段的添加或更新(社保基数)----------
        costInformation.setSsbid(getHRsetidInPer(new HRset("ssb", personalInformation.getSsb())));
        //HR18字段的添加或更新(社保公司缴费比例)----------
        costInformation.setSsbgscdid(getHRsetidInPer(new HRset("ssbgscd", personalInformation.getSsbgscd())));
        //HR19字段的添加或更新(社保个人缴费比例)----------
        costInformation.setSsbgrcdid(getHRsetidInPer(new HRset("ssbgrcd", personalInformation.getSsbgrcd())));
        //HR20字段的添加或更新(公积金基数)----------
        costInformation.setGjjid(getHRsetidInPer(new HRset("gjj", personalInformation.getGjj())));
        //HR21字段的添加或更新(公积金公司缴费比例)----------
        costInformation.setGjjgscdid(getHRsetidInPer(new HRset("gjjgscd", personalInformation.getGjjgscd())));
        //HR22字段的添加或更新(公积金个人缴费比例)----------
        costInformation.setGjjgrcdid(getHRsetidInPer(new HRset("gjjgrcd", personalInformation.getGjjgrcd())));
        //HR23字段的添加或更新(开户行)----------
        costInformation.setKhhid(getHRsetidInPer(new HRset("khh", personalInformation.getKhh())));
        //普通字段的添加或更新(工资账号)----------
        costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
        //HR24字段的添加或更新(社保缴纳地)----------
        costInformation.setSbjndid(getHRsetidInPer(new HRset("sbjnd", personalInformation.getSbjnd())));
        //普通字段的添加或更新(社保账号)----------
        costInformation.setSbcode(personalInformation.getSbcode());
        //普通字段的添加或更新(公积金账号)----------
        costInformation.setGjjcode(personalInformation.getGjjcode());
        Integer costinformationid = iCostInformationService.saveOne(costInformation);
        personalInformation.setCostinformationid(costinformationid);

        //5.添加其它信息表（tb_id_otherinformation）=============================================================================
        OtherInformation otherInformation = new OtherInformation();
        //普通字段的添加或更新(私人邮箱)----------
        otherInformation.setPrivateemail(personalInformation.getPrivateemail());
        //普通字段的添加或更新(公司邮箱)----------
        otherInformation.setCompanyemail(personalInformation.getCompanyemail());
        //普通字段的添加或更新(应急联系人)----------
        otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
        //HR25字段的添加或更新(应急联系人关系)----------
        otherInformation.setEmergencyrpid(getHRsetidInPer(new HRset("emergencyrp", personalInformation.getEmergencyrp())));
        //普通字段的添加或更新(应急联系人电话)----------
        otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
        //普通字段的添加或更新(住址)----------
        otherInformation.setAddress(personalInformation.getAddress());
        //普通字段的添加或更新(备注)----------
        otherInformation.setAddress(personalInformation.getAddress());
        Integer otherinformationid = iOtherInformationService.saveOne(otherInformation);
        personalInformation.setOtherinformationid(otherinformationid);

        //6.添加人事主要信息表（tb_id_personalinformation）=============================================================================
        //普通字段的添加或更新(部门)----------
        if (!StringUtils.isBlank(personalInformation.getDepcode()) && !"部门编号还未添加".equals(personalInformation.getDepcode())) {
            Dept dept = iDeptService.queryOneByDepcode(personalInformation.getDepcode());
            if (dept == null) {
                goToPost.put(personalInformation.getUsername() + ":" + personalInformation.getDepcode(), "员工所在部门编号不存在，请手动添加/修改");
            } else {
                personalInformation.setDepid(dept.getId());
            }
        } else if (!StringUtils.isBlank(personalInformation.getDepname())) {
            //不同的公司可以拥有相同的部门名称，所以原则上不可以用部门名称作为导入信息，此处是为了对接老OA的信息
            List<Dept> depts = iDeptService.queryOneDepByDepname(personalInformation.getDepname());
            if (depts.size() == 1) {
                personalInformation.setDepid(depts.get(0).getId());
            } else if (depts.size() == 0) {
                goToPost.put(personalInformation.getUsername() + ":" + personalInformation.getDepname(), "员工所在部门名称不存在，请手动添加/修改");
            } else if (depts.size() > 1) {
                goToPost.put(personalInformation.getUsername() + ":" + personalInformation.getDepname(), "员工所在部门名称存在多个，请手动添加/修改");
            }
        }
        //HR26字段的添加或更新(办公电话)----------
        personalInformation.setTelphoneid(getHRsetidInPer(new HRset("telphone", personalInformation.getTelphone())));
        //普通字段的添加或更新(移动电话/手机号)----------//不用特殊处理
        Integer personalinformationid = iPersonalInformationService.saveOne(personalInformation);

        //7.添加人事岗位关系表主要信息表（tb_hr_per_and_post_rs）=============================================================================
        Map<Integer, Integer> newMap = new HashMap<>();
        String[] postnames = new String[0];
        if (personalInformation.getPostnames() != null && !"".equals(personalInformation.getPostnames())) {
            postnames = personalInformation.getPostnames().split("[兼;]");
        }
        if (postnames.length > 0) {
            for (String postname : postnames
            ) {
                Post post = iPostService.queryOneByPostname(postname);
                if (post != null) {
                    newMap.put(post.getId(), personalInformation.getId());
                } else {
                    goToPost.put(personalInformation.getEmployeenumber() + ":" + postname, "此岗位不存在，请联系管理员添加岗位或修改岗位信息！");
                }
            }
            newMap.forEach((postid, perid) -> {
                PerAndPostRs perAndPostRs = new PerAndPostRs(perid, postid);
                iPerandpostrsService.addOne(perAndPostRs);
            });
        }
        return goToPost;
    }

    private Integer getHRsetidInPer(HRset hRset) {
        Integer hrsetid = null;
        if (!StringUtils.isBlank(hRset.getDatavalue())) {
            List<HRset> hRsetList = ihRsetService.queryByConditions(hRset);
            if (hRsetList == null || hRsetList.size()==0) {
                hrsetid = ihRsetService.addOne(hRset);
            } else {
                hrsetid = hRsetList.get(0).getId();
            }
        }
        return hrsetid;
    }

    /**
     * @return java.util.Map<java.lang.String       ,       java.lang.String>
     * @Author: shiyun
     * @Description: 数据导入时的每条数据更新到数据库中
     * @Date 2018\11\1 0001 15:41
     * @Param [personalInformation, goToPost]
     **/
    public Map<String, String> importOnePersonalInformation_UPDATE(PersonalInformation personalInformation, Map<String, String> goToPost, PersonalInformation currentPer) {
        String truename = personalInformation.getTruename();
        System.out.println(truename);
        try {
            currentPer = getOnePersonalinformation(currentPer.getId());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(null==currentPer){
            goToPost.put(truename+":"+personalInformation.getEmployeenumber(),"不存在");
            return goToPost;
        }
        personalInformation.setId(currentPer.getId());
        if (truename.equals(currentPer.getTruename())) {
            //名字正确，下面更新信息
            //1.先更新User表的信息(tb_id_user)===========================================================
            Boolean userB = false;
            User user = iUserService.getById(currentPer.getUserid());
            Integer isactive = personalInformation.getIsactive();//是否激活
            if (isactive != null && isactive != currentPer.getIsactive()) {
                currentPer.setIsactive(isactive);
                user.setIsactive(currentPer.getIsactive());
                userB = true;
            }
            String state = personalInformation.getState();//是否离职状态
            if (state != null && !"".equals(state)) {
                currentPer.setState(state);
                user.setState(Integer.parseInt(state));
                userB = true;
            }
            if (userB) {
                iUserService.update(user);
            }
            //2.在更新Baseinformation表的信息(tb_id_baseinformation)==================================================
            //Boolean baserinformationB = false;
            BaseInformation baseInformation = new BaseInformation();
            baseInformation.setId(currentPer.getBaseinformationid());
            baseInformation.setUserphoto(personalInformation.getUserphoto());//免冠照片
            baseInformation.setIdphoto1(personalInformation.getIdphoto1());//身份证正面
            baseInformation.setIdphoto2(personalInformation.getIdphoto2());//身份证反面
            baseInformation.setEnglishname(personalInformation.getEnglishname());//英文名
            baseInformation.setIdcode(personalInformation.getIdcode());//身份证号码
            if (!StringUtils.isBlank(personalInformation.getIdcode())) {
                baseInformation.setHj(IDcodeUtil.getProvinceByIdcode(personalInformation.getIdcode()));
                personalInformation.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));//为后面修改tb_id_personalinformation表做准备
            }//户籍（身份证号码解析出来的）
            //HR1字段的添加或更新(民族)----------
            baseInformation.setRaceid(getHRsetidInPer(new HRset("race", personalInformation.getRace())));
            //普通字段的添加或更新(是否已婚)----------
            baseInformation.setMarriage(personalInformation.getMarriage());
            //HR2字段的添加或更新(生育情况)----------
            baseInformation.setChildrenid(getHRsetidInPer(new HRset("children", personalInformation.getChildren())));
            //HR3字段的添加或更新(政治面貌)----------
            baseInformation.setZzmmid(getHRsetidInPer(new HRset("zzmm",personalInformation.getZzmm())));
            //HR4字段的添加或更新(最高学历)----------
            baseInformation.setZgxlid(getHRsetidInPer(new HRset("zgxl",personalInformation.getZgxl())));
            //HR5字段的添加或更新(毕业院校)----------
            baseInformation.setByyxid(getHRsetidInPer(new HRset("byyx",personalInformation.getByyx())));
            //HR6字段的添加或更新(所学专业)----------
            baseInformation.setSxzyid(getHRsetidInPer(new HRset("sxzy",personalInformation.getSxzy())));
            //HR7字段的添加或更新(培养方式)----------
            baseInformation.setPyfsid(getHRsetidInPer(new HRset("pyfs",personalInformation.getPyfs())));
            //HR8字段的添加或更新(第一外语)----------
            baseInformation.setFirstlaid(getHRsetidInPer(new HRset("fla", personalInformation.getFirstla())));
            //HR9字段的添加或更新(其它外语)----------
            baseInformation.setElselaid(getHRsetidInPer(new HRset("fla", personalInformation.getElsela())));
            //HR10字段的添加或更新(职称)----------
            baseInformation.setPosttitleid(getHRsetidInPer(new HRset("posttitle", personalInformation.getPosttitle())));
            //HR11字段的添加或更新(职业证书类型)----------
            baseInformation.setZyzstypeid(getHRsetidInPer(new HRset("zyzstype", personalInformation.getZyzstype())));
            //HR12字段的添加或更新(职业证书名称)----------
            baseInformation.setZyzsnameid(getHRsetidInPer(new HRset("zyzsname", personalInformation.getZyzsname())));
            //HR13字段的添加或更新(上家雇主)----------
            baseInformation.setParentcompanyid(getHRsetidInPer(new HRset("parentcompany", personalInformation.getParentcompany())));
            //普通字段的添加或更新(首次参加工作时间)----------
            baseInformation.setFirstworkingtime(personalInformation.getFirstworkingtime());
            iBaseInformationService.modifyOne(baseInformation);

            //3.更新管理信息表（tb_id_managerinformation）=============================================================================
            ManageInformation manageInformation = new ManageInformation();
            manageInformation.setId(personalInformation.getManageinformationid());
            //HR14字段的添加或更新(职级)----------
            manageInformation.setRankid(getHRsetidInPer(new HRset("rank", personalInformation.getZj())));
            //HR15字段的添加或更新(员工类型)----------
            manageInformation.setEmployeetypeid(getHRsetidInPer(new HRset("employeetype", personalInformation.getEmployeetype())));
            //普通字段的添加或更新(入职日期)----------
            manageInformation.setEntrydate(personalInformation.getEntrydate());
            //普通字段的添加或更新(转正日期)----------
            manageInformation.setZhuanzhengdate(personalInformation.getZhuanzhengdate());
            iManageInformationService.modifyOne(manageInformation);

            //4.更新成本信息表（tb_id_costinformation）=============================================================================
            Boolean costinformationB = false;
            CostInformation costInformation = new CostInformation();
            costInformation.setId(currentPer.getCostinformationid());
            //HR16字段的添加或更新(薪资标准)----------
            costInformation.setSalarystandardid(getHRsetidInPer(new HRset("salary", personalInformation.getSalary())));
            //HR17字段的添加或更新(社保基数)----------
            costInformation.setSsbid(getHRsetidInPer(new HRset("ssb", personalInformation.getSsb())));
            //HR18字段的添加或更新(社保公司缴费比例)----------
            costInformation.setSsbgscdid(getHRsetidInPer(new HRset("ssbgscd", personalInformation.getSsbgscd())));
            //HR19字段的添加或更新(社保个人缴费比例)----------
            costInformation.setSsbgrcdid(getHRsetidInPer(new HRset("ssbgrcd", personalInformation.getSsbgrcd())));
            //HR20字段的添加或更新(公积金基数)----------
            costInformation.setGjjid(getHRsetidInPer(new HRset("gjj", personalInformation.getGjj())));
            //HR21字段的添加或更新(公积金公司缴费比例)----------
            costInformation.setGjjgscdid(getHRsetidInPer(new HRset("gjjgscd", personalInformation.getGjjgscd())));
            //HR22字段的添加或更新(公积金个人缴费比例)----------
            costInformation.setGjjgrcdid(getHRsetidInPer(new HRset("gjjgrcd", personalInformation.getGjjgrcd())));
            //HR23字段的添加或更新(开户行)----------
            costInformation.setKhhid(getHRsetidInPer(new HRset("khh", personalInformation.getKhh())));
            //普通字段的添加或更新(工资账号)----------
            costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
            //HR24字段的添加或更新(社保缴纳地)----------
            costInformation.setSbjndid(getHRsetidInPer(new HRset("sbjnd", personalInformation.getSbjnd())));
            //普通字段的添加或更新(社保账号)----------
            if (!StringUtils.isBlank(personalInformation.getSbcode())) {
                costinformationB = true;
                costInformation.setSbcode(personalInformation.getSbcode());
            }
            //普通字段的添加或更新(公积金账号)----------
            if (!StringUtils.isBlank(personalInformation.getGjjcode())) {
                costinformationB = true;
                costInformation.setGjjcode(personalInformation.getGjjcode());
            }
            if (costinformationB) {
                iCostInformationService.modifyOne(costInformation);
            }
            //5.更新其它信息表（tb_id_otherinformation）=============================================================================
            OtherInformation otherInformation = new OtherInformation();
            otherInformation.setId(currentPer.getOtherinformationid());
            //普通字段的添加或更新(私人邮箱)----------
            otherInformation.setPrivateemail(personalInformation.getPrivateemail());
            //普通字段的添加或更新(公司邮箱)----------
            otherInformation.setCompanyemail(personalInformation.getCompanyemail());
            //普通字段的添加或更新(应急联系人)----------
            otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
            //HR25字段的添加或更新(应急联系人关系)----------
            otherInformation.setEmergencyrpid(getHRsetidInPer(new HRset("emergencyrp", personalInformation.getEmergencyrp())));
            //普通字段的添加或更新(应急联系人电话)----------
            otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
            //普通字段的添加或更新(住址)----------
            otherInformation.setAddress(personalInformation.getAddress());
            //普通字段的添加或更新(备注)----------
            otherInformation.setAddress(personalInformation.getAddress());
            iOtherInformationService.modifyOne(otherInformation);

            //6.更新人事主要信息表（tb_id_personalinformation）=============================================================================
            //普通字段的添加或更新(部门)----------
            if (!StringUtils.isBlank(personalInformation.getDepcode()) && !"部门编号还未添加".equals(personalInformation.getDepcode())) {
                Dept dept = iDeptService.queryOneByDepcode(personalInformation.getDepcode());
                if (dept == null) {
                    goToPost.put(personalInformation.getUsername() + ":" + personalInformation.getDepcode(), "员工所在部门编号不存在，请手动添加/修改");
                } else {
                    personalInformation.setDepid(dept.getId());
                }
            } else if (!StringUtils.isBlank(personalInformation.getDepname())) {
                //不同的公司可以拥有相同的部门名称，所以原则上不可以用部门名称作为导入信息，此处是为了对接老OA的信息
                List<Dept> depts = iDeptService.queryOneDepByDepname(personalInformation.getDepname());
                if (depts.size() == 1) {
                    personalInformation.setDepid(depts.get(0).getId());
                } else if (depts.size() == 0) {
                    goToPost.put(personalInformation.getUsername() + ":" + personalInformation.getDepname(), "员工所在部门名称不存在，请手动添加/修改");
                } else if (depts.size() > 1) {
                    goToPost.put(personalInformation.getUsername() + ":" + personalInformation.getDepname(), "员工所在部门名称存在多个，请手动添加/修改");
                }
            }
            //HR26字段的添加或更新(办公电话)----------
            personalInformation.setTelphoneid(getHRsetidInPer(new HRset("telphone", personalInformation.getTelphone())));
            //普通字段的添加或更新(移动电话/手机号)----------//不用特殊处理
            iPersonalInformationService.modifyOne(personalInformation);

            //7.更新人事岗位关系表主要信息表（tb_hr_per_and_post_rs）=============================================================================
            //先准备两个集合（原数据及需要目标数据）
            Map<Integer, Integer> oldMap = new HashMap<>();
            List<PerAndPostRs> perAndPostRsList_old = iPerandpostrsService.queryPerAndPostRsByPerid(currentPer.getId());
            for (PerAndPostRs p : perAndPostRsList_old
            ) {
                oldMap.put(p.getPostid(), p.getPerid());
            }
            Map<Integer, Integer> newMap = new HashMap<>();
            String[] postnames = personalInformation.getPostnames().split("[兼;]");
            for (String postname : postnames
            ) {
                Post post = iPostService.queryOneByPostname(postname);
                if (null!=post) {
                    newMap.put(post.getId(), currentPer.getId());
                } else {
                    goToPost.put(personalInformation.getEmployeenumber(),"所在的岗位不存在["+postname+"]");
                }
            }
            //分两步：1)没有的添加上;2)多余的删除
            //1)没有的添加上;
            newMap.forEach((postid, perid) -> {
                boolean postBoolean = oldMap.containsKey(postid) && oldMap.get(postid).equals(perid);
                if (!postBoolean) {
                    iPerandpostrsService.addOne(new PerAndPostRs(perid, postid));
                }
            });
            //2)多余的删除
            oldMap.forEach((postid, perid) -> {
                boolean postBoolean = newMap.containsKey(postid);
                if (!postBoolean) {
                    Boolean removeBoolean = iPerandpostrsService.removeOneByPeridAndPostid(perid, postid);
                    if (!removeBoolean) {
                        goToPost.put(personalInformation.getUsername() + "perid(" + perid + "):postid(" + postid + ")", "这一条数据删除失败，请重新再删一次");
                    }
                }
            });
        } else {
            goToPost.put(personalInformation.getEmployeenumber() + ":" + personalInformation.getTruename() + ":" + currentPer.getTruename(), "此工号的员工姓名与数据库中的不一致！");
        }
        return goToPost;
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息的删除（无用的信息，直接在数据库中删除）
     * @Date: 13:56 2018\5\10 0010
     */
    @RequestMapping("/deleteInformationsByIds")
    @ResponseBody
    public Object deleteInformationsByIds(
            @RequestParam("personalInformationIds") List<Integer> personalInformationIds
    ) {
        List<String> usernames = new ArrayList<>();
        if (personalInformationIds.size() <= 0) {
            return RespUtil.successResp("500", "删除失败！", null);
        }
        for (int i = 0; i < personalInformationIds.size(); i++) {
            PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationIds.get(i));
            if (personalInformation == null) {
                return RespUtil.successResp("500", "删除失败！", null);
            }
            if (iUserService.getById(personalInformation.getUserid()) != null) {
                usernames.add(iUserService.getById(personalInformation.getUserid()).getUsername());
            } else {
                return RespUtil.successResp("500", "删除失败！", null);
            }


            //1.先删除用户表（直接删除）
            iUserService.removeOne(personalInformation.getUserid());
            //2.再删除相应的基本信息表（直接删除）
            if (personalInformation.getBaseinformationid() != null) {
                iBaseInformationService.removeOne(personalInformation.getBaseinformationid());
            }
            //3.再删除相应的管理信息表（直接删除）
            if (personalInformation.getManageinformationid() != null) {
                iManageInformationService.removeOne(personalInformation.getManageinformationid());
            }
            //4.再删除相应的成本信息表（直接删除）
            if (personalInformation.getCostinformationid() != null) {
                iCostInformationService.remvoeOne(personalInformation.getCostinformationid());
            }
            //5.再删除相应的人事岗位关系表（直接删除）
            iPerandpostrsService.removeByPerid(personalInformationIds.get(i));
            //6.再删除相应的其他信息表（直接删除）
            if (personalInformation.getOtherinformationid() != null) {
                iOtherInformationService.removeOne(personalInformation.getOtherinformationid());
            }
            //7.再删除相应的合同信息表（直接删除）
            List<ContractInformation> contractInformationList = iContractInformationService.queryByUserid(personalInformation.getUserid());
            for (ContractInformation c : contractInformationList
            ) {
                iContractInformationService.removeOne(c.getId());
            }
            //8.再删除相应的人事变更表（直接删除）
            List<ChangeInformation> changeInformationList = iChangeInformationService.queryByUserid(personalInformation.getUserid());
            for (ChangeInformation c : changeInformationList
            ) {
                iChangeInformationService.removeOne(c.getId());
            }
            //9.再删除人事信息主表（直接删除）
            iPersonalInformationService.removeOne(personalInformation.getId());
            //10.最后再修改部门表
            //注：如果将要删除的员工是某部门的正职、副职、秘书则需要修改该字段
            iDeptService.modifyOne(personalInformation.getUserid());


        }
        return RespUtil.successResp("200", "删除成功！", usernames);
    }

    /**
     * @return
     * @Author: shiyun
     * @Description: 测试用
     * @Date 2018\11\2 0002 12:53
     * @Param
     **/
    @RequestMapping("/deleteAllInformations")
    @ResponseBody
    public Object deleteAllInformations(
    ) {
        //1.先删除用户表（直接删除）
        iUserService.removeAll_admin();
        //2.再删除相应的基本信息表（直接删除）
        iBaseInformationService.removeAll();
        //3.再删除相应的管理信息表（直接删除）
        iManageInformationService.removeAll();
        //4.再删除相应的成本信息表（直接删除）
        iCostInformationService.remvoeAll();
        //5.再删除相应的人事岗位关系表（直接删除）
        iPerandpostrsService.removeAll();
        //6.再删除相应的其他信息表（直接删除）
        iOtherInformationService.removeAll();
        //7.再删除相应的合同信息表（直接删除）
        //8.再删除相应的人事变更表（直接删除）
        //9.再删除人事信息主表（直接删除）
        iPersonalInformationService.removeAll();
        //10.最后再修改部门表
        return RespUtil.successResp("200", "删除成功！", null);
    }

    /**
     * @Author:ShiYun;
     * @Description:所有人员的工号、人名、部门（ID、名称）、岗位（ID、名称）
     * @Date: 14:15 2018\7\9 0009
     */
    @RequestMapping("/queryGXF001")
    @ResponseBody
    public List<Object> queryGXF001() throws ParseException {
        List<Object> list = new ArrayList<>();
        List<PersonalInformation> personalInformationList = iPersonalInformationService.queryAllByNull();
        for (PersonalInformation per : personalInformationList) {
            PersonalInformation onePersonalinformation = getOnePersonalinformation(per.getId());
            if (onePersonalinformation == null) {
                continue;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            // HashMap<String, Object> deptMap = new HashMap<>();
            ArrayList<Object> postList = new ArrayList<>();
            map.put("employeeName", onePersonalinformation.getEmployeenumber());
            map.put("id", onePersonalinformation.getTruename());
            map.put("deptId", onePersonalinformation.getDepid());
            map.put("deptName", iDeptService.queryOneDepByDepid(onePersonalinformation.getDepid()).getDepname());
            //map.put("dept",deptMap);
            List<PerAndPostRs> perAndPostRsList = iPerandpostrsService.queryPerAndPostRsByPerid(onePersonalinformation.getId());
            for (PerAndPostRs perAndPostRs : perAndPostRsList) {
                HashMap<String, Object> postMap = new HashMap<>();
                postMap.put("postId", perAndPostRs.getPostid());
                postMap.put("postName", iPostService.queryOneByPostid(perAndPostRs.getPostid()).getPostname());
                postList.add(postMap);
            }
            map.put("post", postList);
            list.add(map);
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:所有部门的ID、名称
     * @Date: 14:33 2018\7\9 0009
     */
    @RequestMapping("/queryGXF002")
    @ResponseBody
    public List<Object> queryGXF002() {
        List<Object> list = new ArrayList<>();
        List<Dept> depts = iDeptService.queryAllDepts();
        for (Dept dept : depts) {
            Map<String, Object> deptMap = new HashMap<>();
            deptMap.put("deptId", dept.getId());
            deptMap.put("deptName", dept.getDepname());
            list.add(deptMap);
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:所有岗位的ID、名称
     * @Date: 14:38 2018\7\9 0009
     */
    @RequestMapping("/queryGXF003")
    @ResponseBody
    public List<Object> queryGXF003() {
        ArrayList<Object> list = new ArrayList<>();
        List<Post> posts = iPostService.queryAllPosts();
        for (Post post : posts) {
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("postId", post.getId());
            postMap.put("postName", post.getPostname());
            list.add(postMap);
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据部门ID查询部门信息(部门经理)
     * @Date: 11:34 2018\8\10 0010
     */
    @RequestMapping("/queryGXF004")
    @ResponseBody
    public String queryGXF004(
            @RequestParam("depid") Integer depid
    ) {
        Dept dept = iDeptService.queryOneDepByDepid(depid);
        if (dept != null) {
            if (dept.getPrincipaluserid() != null) {
                if (iUserService.getById(dept.getPrincipaluserid()) != null) {
                    iUserService.getById(dept.getPrincipaluserid()).getTruename();
                } else {
                    return null;
                }
                return iUserService.getById(dept.getPrincipaluserid()).getTruename();
            } else {
                return "此部门没有部门正职！";
            }
        } else {
            return "此部门不存在！";
        }
        /*if(dept!=null){
            return RespUtil.successResp("205","查询信息成功！",dept);
        }else{
            return RespUtil.successResp("503","部门信息不存在！",null);
        }*/
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有的一级公司和二级公司
     * @Date: 10:50 2018\8\21 0021
     */
    @RequestMapping("/queryGXF005")
    @ResponseBody
    public Object queryGXF005() {
        List<Dept> depts = iDeptService.queryAllCompany1and2();
        ArrayList<String> strs = new ArrayList<>();

        if(null == depts || depts.size()==0){
            strs.add("公司类型不存在，请先到人力资源-部门信息-设置部门的职能类型");
            return strs;
        }
        for (Dept d : depts
        ) {
            strs.add(d.getDepname());
        }
        return strs;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据公司名称查询其下属所有部门（不管是级的）
     * @Date: 11:08 2018\8\21 0021
     */
    @RequestMapping("/queryGXF006")
    @ResponseBody
    public Object queryGXF006(
            @RequestParam("depname") String depname
    ) {
        List<Dept> depts = iDeptService.queryByCompanyname(depname);
        List<String> strs = new ArrayList<>();
        for (Dept d : depts
        ) {
            strs.add(d.getDepname());
        }
        return strs;
    }

    /**
     * @Author:ShiYun;
     * @Description:所有部门的ID、名称(公司级的提出)
     * @Date: 14:33 2018\7\9 0009
     */
    @RequestMapping("/queryGXF007")
    @ResponseBody
    public List<Object> queryGXF007() {
        List<Object> list = new ArrayList<>();
        List<Dept> depts = iDeptService.queryAllDepts();
        if(null==depts)return null;
        for (Dept dept : depts) {
            if(null==dept.getDeptypeid())continue;
            if (dept.getDeptypeid() != 1 && dept.getDeptypeid() != 3) {
                Map<String, Object> deptMap = new HashMap<>();
                deptMap.put("deptId", dept.getId());
                deptMap.put("deptName", dept.getDepname());
                list.add(deptMap);
            }
        }
        return list;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据人员名称查询所在公司
     * @Date: 14:55 2018\9\8 0008
     */
    @RequestMapping("/queryGXF008")
    @ResponseBody
    public Object queryGXF008(
            @RequestParam("truename") String truename
    ) {
        String companyname = null;
        try {
            companyname = iDeptService.queryCompanynameByUseridOrTruename(null, truename);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.successResp("500", e.getMessage(), companyname);
        }
        /*return RespUtil.successResp("200","提交成功！",companyname);*/
        return companyname;
    }

    /**
     * @Author:ShiYun;
     * @Description:根据登录ID查询岗位名称
     * @Date: 13:39 2018\9\25 0025
     */
    @RequestMapping("/queryHLT001")
    @ResponseBody//表示该方法的返回结果直接写入 HTTP response body 中，一般在异步获取数据时使用【也就是AJAX】
    public Object queryHLT001(
            @RequestParam("username") String username
    ) {
        return iPostService.queryPostnameByUsername(username);
    }

    /**
     * @Author:ShiYun;
     * @Description:赵宏钢的接口，根据账号查询信息
     * @Date: 18:30 2018\7\17 0017
     */
    @RequestMapping("/queryZHG001")
    @ResponseBody
    public Object queryZHG001(
            @RequestParam("username") String username
    ) {
        User user = iUserService.queryByUsername(username);
        if (user == null) {
            return RespUtil.successResp("205", "没有查到此用户信息", null);
        } else {
            PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(user.getId());
            try {
                PersonalInformation onePersonalinformation;
                if (personalInformation != null) {
                    onePersonalinformation = this.getOnePersonalinformation(personalInformation.getId());
                } else {
                    return RespUtil.successResp("205", "没有查到此用户信息", null);
                }
                return RespUtil.successResp("205", "查询成功！", onePersonalinformation);
            } catch (ParseException e) {
                return RespUtil.successResp("205", "没有查到此用户信息", null);
            }
        }
    }

    /**
     * @Author:ShiYun;
     * @Description:根据岗位名称查询人员
     * @Date: 15:51 2018\8\21 0021
     */
    @RequestMapping("/queryZHG002")
    @ResponseBody
    public Object queryZHG002(
            @RequestParam("postname") String postname
    ) {
        Post post = iPostService.queryOneByPostname(postname);
        List<PerAndPostRs> perAndPostRsList = iPerandpostrsService.queryPerAndPostRsByPostid(post.getId());
        List<User> list = new ArrayList<>();
        for (PerAndPostRs p : perAndPostRsList
        ) {
            PersonalInformation personalInformation = iPersonalInformationService.queryOneById(p.getPerid());
            User byId = iUserService.getById(personalInformation.getUserid());
            list.add(byId);
        }
        return RespUtil.successResp("205", "请求成功！", list);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据日期查询工作日志（填写）
     * @Date: 9:40 2018\8\3 0003
     */
    @RequestMapping("/queryWriteGzrz")
    @ResponseBody
    public Object queryWriteGzrz(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ) {
        Object o = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(year + "/" + month + "/01 00:00:00");
            o = iGzrzService.queryGzrzByTime(date);
        } catch (ParseException e) {
            System.out.println("格式转换出错！");
        }
        return RespUtil.successResp("205", "相应成功！", o);
    }

    /**
     * @Author:ShiYun;
     * @Description:根据日期查询工作日志（审查）
     * @Date: 14:52 2018\8\3 0003
     */
    @RequestMapping("/queryApproveGzrz")
    @ResponseBody
    public Object queryApproveGzrz(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ) {
        Object o = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(year + "/" + month + "/01 00:00:00");
            o = iGzrzService.queryGzrzByTime2(date);
        } catch (ParseException e) {
            System.out.println("格式转换出错！");
        }
        return RespUtil.successResp("205", "相应成功！", o);
    }

    /**
     * @Author:ShiYun;
     * @Description:查询所有的录用申请单
     * @Date: 15:23 2018\9\28 0028
     */
    @RequestMapping("/queryAllLysqd")
    @ResponseBody
    public List<Lysqd> queryAllLysqd() {
        return iGzrzService.queryLyspd();
    }
}
