package com.elex.oa.controller.hr;

import com.alibaba.fastjson.JSON;
import com.elex.oa.entity.hr_entity.*;
import com.elex.oa.service.hr_service.*;
import com.elex.oa.util.hr_util.AppProperties;
import com.elex.oa.util.hr_util.ConfigUtils;
import com.elex.oa.util.hr_util.HrUtils;
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

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    HrUtils hrUtils;
    @Autowired
    AppProperties appProperties;

    @RequestMapping("/getRealpath")
    @ResponseBody
    public String getRealpath(){
       return appProperties.getAttachmentRealpath();
    }



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
        return list;
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
        return iPersonalInformationService.queryPersonalInformationById(personalInformationId);
    }

    /**
     * @Author:ShiYun;
     * @Description:人事信息查询（一条）(IOS端)
     * @Date: 18:02 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformationByUserid")
    @ResponseBody
    public ArrayList<HashMap> queryPersonalInformationByUserid(
            @RequestParam("userid") int userid
    ) throws ParseException {
        ArrayList<HashMap> respMapList = iPersonalInformationService.queryByUseridForIOS(userid);
        return respMapList;
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
        PersonalInformation personalInformation = iPersonalInformationService.queryPersonalInformationByTruename(truename);
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
            if (ihRsetService.queryById(manageInformation.getPostlevelid()) != null) {
                personalInformation.setPostlevel(ihRsetService.queryById(manageInformation.getPostlevelid()).getDatavalue());
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
        // 将三个文件在服务器中保存下来
        baseInformation.setUserphoto(hrUtils.getSignalFileAddress(request,"userphoto2","/hr/image/"));
        baseInformation.setIdphoto1(hrUtils.getSignalFileAddress(request,"idphoto11","/hr/image/"));
        baseInformation.setIdphoto2(hrUtils.getSignalFileAddress(request,"idphoto22","/hr/image/"));
        // 在tb_id_user表中保存用户
        user.setIsactive(personalInformation.getIsactive());
        if (personalInformation.getUsername() != null && !"".equals(personalInformation.getUsername())) {
            user.setUsername(personalInformation.getUsername());
        } else {
            user.setUsername(SpellUtils.phoneticize(personalInformation.getTruename()));//如果没有人工输入，则自动将名字的汉字转换为汉语拼音
        }
        user.setTruename(personalInformation.getTruename());
        user.setEmployeenumber(personalInformation.getEmployeenumber());
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
        Map<String, Object> respMap = iPersonalInformationService.addManageInformation(personalInformation);
        return null!=respMap?RespUtil.successResp("200", "管理信息添加成功！", respMap):RespUtil.successResp("500","管理信息添加失败！",null);
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
        Map<String, Object> map = iPersonalInformationService.addOtherInformation(personalInformation);
        return null!=map?RespUtil.successResp("200", "添加其他信息成功！", map):RespUtil.successResp("500","信息添加失败！",null);
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
        //获取照片地址
        personalInformation.setUserphoto(hrUtils.getSignalFileAddress(request,"userphoto2","/hr/image/"));
        personalInformation.setIdphoto1(hrUtils.getSignalFileAddress(request,"idphoto11","/hr/image/"));
        personalInformation.setIdphoto2(hrUtils.getSignalFileAddress(request,"idphoto22","/hr/image/"));
        //判断是否需要录入并添加相应的HR设置信息
        if(StringUtils.isNotBlank(byyxvalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"byyx",personalInformation.getByyx());
        if(StringUtils.isNotBlank(sxzyvalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"syxz",personalInformation.getByyx());
        if(StringUtils.isNotBlank(zyzsnamevalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"zyzsname",personalInformation.getByyx());
        if(StringUtils.isNotBlank(parentcompanyvalue) && byyxvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"parentcompany",personalInformation.getByyx());
        Map<String, Object> map = iPersonalInformationService.updateBaseInformation(personalInformation, transactorusername);
        return RespUtil.successResp("200", "信息提交成功！", map.get("postids"));
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
        Map<String, Object> map = iPersonalInformationService.updateManageInformation(personalInformation, transactorusername);
        return null!=map?RespUtil.successResp("200", "提交信息成功！", map):RespUtil.successResp("500","修改失败(没有需要修改的信息)！",null);
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
        //判断是否需要录入并添加相应的HR设置信息
        if(StringUtils.isNotBlank(khhvalue) && khhvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"khh",personalInformation.getKhh());
        if(StringUtils.isNotBlank(sbjndvalue) && sbjndvalue.equals("选择录入"))hrUtils.addHrsetByDatavalue(null,"sbjnd",personalInformation.getSbjnd());
        Boolean aBoolean = iPersonalInformationService.updateCostInformation(personalInformation, transactorusername);
        return aBoolean?"提交信息成功！":"提交失败！";
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
        Map<String, Object> map = iPersonalInformationService.updateOtherInformation(personalInformation, transactorusername);
        return map!=null?RespUtil.successResp("200", "提交成功！", map):RespUtil.successResp("500","提交失败！",null);
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
            pi.setState(user.getState());

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
                if (ihRsetService.queryById(manageInformation.getPostlevelid()) != null) {
                    pi.setPostlevel(ihRsetService.queryById(manageInformation.getPostlevelid()).getDatavalue());
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
        Map<String, String> map = iPersonalInformationService.importPersonalInformations(multipartFile);
        return map.size()==0?RespUtil.successResp("200", "请求成功", null):RespUtil.successResp("500","导入部分失败！",JSON.toJSONString(map));
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

    @RequestMapping("/queryUseridTruenameDepidDepnamePerid")
    @ResponseBody
    public Object queryUseridTruenameDepidDepnamePerid(){
        return iPersonalInformationService.queryUseridTruenameDepidDepnamePerid();
    }

    @RequestMapping("/queryIdcodeInfoByAnalyzeIdcode")
    @ResponseBody
    public Object queryIdcodeInfoByAnalyzeIdcode(
            @RequestParam("idcode")String idcode
    ){
        Map<String, Object> respMap = iPersonalInformationService.queryIdcodeInfoByAnalyzeIdcode(idcode);
        return null==respMap?RespUtil.successResp("500","身份证解析失败！",null):RespUtil.successResp("200","身份证解析成功！",respMap);
    }
}
