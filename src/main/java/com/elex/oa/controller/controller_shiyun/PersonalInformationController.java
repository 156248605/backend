package com.elex.oa.controller.controller_shiyun;

import com.elex.oa.common.common_shiyun.Commons;
import com.elex.oa.entity.entity_shiyun.*;
import com.elex.oa.service.project.ProjectBoardService;
import com.elex.oa.service.service_shiyun.*;
import com.elex.oa.util.resp.RespUtil;
import com.elex.oa.util.util_per.SpellUtils;
import com.elex.oa.util.util_shiyun.IDcodeUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    IHRsetRaceService ihRsetRaceService;//民族
    @Autowired
    IHRsetChildrenService ihRsetChildrenService;//生育
    @Autowired
    IHRsetZzmmService ihRsetZzmmService;//政治面貌
    @Autowired
    IHRsetZgxlService ihRsetZgxlService;//最高学历
    @Autowired
    IHRsetByyxService ihRsetByyxService;//毕业院校
    @Autowired
    IHRsetSxzyService ihRsetSxzyService;//所学专业
    @Autowired
    IHRsetPyfsService ihRsetPyfsService;//培养方式
    @Autowired
    IHRsetFlaService ihRsetFlaService;//外语
    @Autowired
    IHRsetPosttitleService ihRsetPosttitleService;//职称
    @Autowired
    IHRsetZyzstypeService ihRsetZyzstypeService;//职业证书类型
    @Autowired
    IHRsetZyzsnameService ihRsetZyzsnameService;//职业证书名称
    @Autowired
    IHRsetParentcompanyService ihRsetParentcompanyService;//上家公司
    @Autowired
    IHRsetRankService ihRsetRankService;//职级
    @Autowired
    IHRsetEmployeetypeService ihRsetEmployeetypeService;//员工类型
    @Autowired
    IHRsetSalarystandardService ihRsetSalarystandardService;//薪资标准
    @Autowired
    IHRsetSsbService ihRsetSsbService;//社保基数
    @Autowired
    IHRsetSsbgscdService ihRsetSsbgscdService;//社保公司缴费比例
    @Autowired
    IHRsetSsbgrcdService ihRsetSsbgrcdService;//社保个人缴费比例
    @Autowired
    IHRsetGjjService ihRsetGjjService;//公积金基数
    @Autowired
    IHRsetGjjgscdService ihRsetGjjgscdService;//公积金公司缴费比例
    @Autowired
    IHRsetGjjgrcdService ihRsetGjjgrcdService;//公积金个人缴费比例
    @Autowired
    IHRsetKhhService ihRsetKhhService;//开户行
    @Autowired
    IHRsetSbjndService ihRsetSbjndService;//社保缴纳地
    @Autowired
    IHRsetTelphoneService ihRsetTelphoneService;//办公电话
    @Autowired
    IHRsetEmergencyrpService ihRsetEmergencyrpService;//应急联系人关系
    @Autowired
    private IGzrzService iGzrzService;//工作日志

    @Autowired
    private ProjectBoardService projectBoardService;//高晓飞


    /**
     * @Author:ShiYun;
     * @Description:人事信息的查询
     * @Date: 9:49 2018\4\8 0008
     */
    @RequestMapping("/queryPersonalInformations")
    @ResponseBody
    public PageInfo<PersonalInformation> queryPersonalInformation(@RequestParam("page") int page,
                                                                  @RequestParam("rows") int rows,
                                                                  PersonalInformation personalInformation
    ) throws ParseException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("pageNum", page);
        paramMap.put("pageSize", rows);
        paramMap.put("entity", personalInformation);
        PageInfo<PersonalInformation> list = iPersonalInformationService.queryPIs(paramMap);
        if (list!=null) {
            List<PersonalInformation> list1 = list.getList();
            for (PersonalInformation pi : list1
                    ) {
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
                for(PerAndPostRs perAndPostRs:perAndPostRss){
                    strs.add(iPostService.queryOneByPostid(perAndPostRs.getPostid()).getPostname());
                }
                pi.setPostnames(IDcodeUtil.getArrayToString(strs,";"));
                //添加办公电话
                if (ihRsetTelphoneService.queryById(pi.getTelphoneid())!=null) {
                    pi.setTelphone(ihRsetTelphoneService.queryById(pi.getTelphoneid()).getTelphone());
                }
                //添加baseinformation的28个字段
                BaseInformation baseInformation = iBaseInformationService.queryOneById(pi.getBaseinformationid());
                pi.setEnglishname(baseInformation.getEnglishname());
                pi.setIdcode(baseInformation.getIdcode());
                pi.setUserphoto(baseInformation.getUserphoto());
                pi.setBirthday(baseInformation.getBirthday());
                pi.setAge(IDcodeUtil.getAge(pi.getBirthday()));
                pi.setConstellation(baseInformation.getConstellation());
                pi.setChinesecs(baseInformation.getChinesecs());
                if (ihRsetRaceService.queryById(baseInformation.getRaceid())!=null) {
                    pi.setRace(ihRsetRaceService.queryById(baseInformation.getRaceid()).getRace());
                }
                pi.setMarriage(baseInformation.getMarriage());
                if (ihRsetChildrenService.queryById(baseInformation.getChildrenid())!=null) {
                    pi.setChildren(ihRsetChildrenService.queryById(baseInformation.getChildrenid()).getChildren());
                }
                if (ihRsetZzmmService.queryById(baseInformation.getZzmmid())!=null) {
                    pi.setZzmm(ihRsetZzmmService.queryById(baseInformation.getZzmmid()).getZzmm());
                }
                if (ihRsetZgxlService.queryById(baseInformation.getZgxlid())!=null) {
                    pi.setZgxl(ihRsetZgxlService.queryById(baseInformation.getZgxlid()).getZgxl());
                }
                if (ihRsetByyxService.queryById(baseInformation.getByyxid())!=null) {
                    pi.setByyx(ihRsetByyxService.queryById(baseInformation.getByyxid()).getByyx());
                }
                if (ihRsetSxzyService.queryById(baseInformation.getSxzyid())!=null) {
                    pi.setSxzy(ihRsetSxzyService.queryById(baseInformation.getSxzyid()).getSxzy());
                }
                if (ihRsetPyfsService.queryById(baseInformation.getPyfsid())!=null) {
                    pi.setPyfs(ihRsetPyfsService.queryById(baseInformation.getPyfsid()).getPyfs());
                }
                if (ihRsetFlaService.queryById(baseInformation.getFirstlaid())!=null) {
                    pi.setFirstla(ihRsetFlaService.queryById(baseInformation.getFirstlaid()).getFla());
                }
                if (ihRsetFlaService.queryById(baseInformation.getElselaid())!=null) {
                    pi.setElsela(ihRsetFlaService.queryById(baseInformation.getElselaid()).getFla());
                }
                if (ihRsetPosttitleService.queryById(baseInformation.getPosttitleid())!=null) {
                    pi.setPosttitle(ihRsetPosttitleService.queryById(baseInformation.getPosttitleid()).getPosttitle());
                }
                if (ihRsetZyzstypeService.queryById(baseInformation.getZyzstypeid())!=null) {
                    pi.setZyzstype(ihRsetZyzstypeService.queryById(baseInformation.getZyzstypeid()).getZyzstype());
                }
                if (ihRsetZyzsnameService.queryById(baseInformation.getZyzsnameid())!=null) {
                    pi.setZyzsname(ihRsetZyzsnameService.queryById(baseInformation.getZyzsnameid()).getZyzsname());
                }
                pi.setFirstworkingtime(baseInformation.getFirstworkingtime());
                if (baseInformation.getFirstworkingtime()!=null && !"".equals(baseInformation.getFirstworkingtime())) {
                    pi.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
                }
                if (ihRsetParentcompanyService.queryById(baseInformation.getParentcompanyid())!=null) {
                    pi.setParentcompany(ihRsetParentcompanyService.queryById(baseInformation.getParentcompanyid()).getParentcompanyname());
                }
                //添加manageinformation的六个字段
                ManageInformation manageInformation = iManageInformationService.queryOneById(pi.getManageinformationid());
                //添加costinformation的18个字段
                CostInformation costInformation = iCostInformationService.queryOneById(pi.getCostinformationid());
                //添加otherinformation的六个字段
                OtherInformation otherInformation = iOtherInformationService.queryOneById(pi.getOtherinformationid());
            }
            list.setList(list1);
        }
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
        map1.put("title","姓名");
        map1.put("value",personalInformation.getTruename());
        list.add(map1);
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("title","性别");
        map2.put("value",personalInformation.getSex());
        list.add(map2);
        HashMap<String, String> map3 = new HashMap<>();
        map3.put("title","出生年月");
        map3.put("value",personalInformation.getBirthday());
        list.add(map3);
        HashMap<String, String> map4 = new HashMap<>();
        map4.put("title","最高学历");
        map4.put("value",personalInformation.getZgxl());
        list.add(map4);
        HashMap<String, String> map5 = new HashMap<>();
        map5.put("title","毕业院校");
        map5.put("value",personalInformation.getByyx());
        list.add(map5);
        HashMap<String, String> map6 = new HashMap<>();
        map6.put("title","婚姻状态");
        map6.put("value",personalInformation.getMarriage());
        list.add(map6);
        HashMap<String, String> map7 = new HashMap<>();
        map7.put("title","手机号");
        map7.put("value",personalInformation.getMobilephone());
        list.add(map7);
        HashMap<String, String> map8 = new HashMap<>();
        map8.put("title","邮箱");
        map8.put("value",personalInformation.getCompanyemail());
        list.add(map8);
        HashMap<String, String> map9 = new HashMap<>();
        map9.put("title","岗位");
        map9.put("value",personalInformation.getPostnames());
        list.add(map9);
        HashMap<String, String> map10 = new HashMap<>();
        map10.put("title","住址");
        map10.put("value",personalInformation.getAddress());
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
        if (user.getId()!=null) {
            onePersonalinformation = iPersonalInformationService.queryOneByUserid(user.getId());
        }
        PersonalInformation personalInformation = getOnePersonalinformation(onePersonalinformation.getId());

        return personalInformation;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据perid查询信息
     *@Date: 17:36 2018\5\17 0017
     */
    public  PersonalInformation getOnePersonalinformation(Integer personalInformationId) throws ParseException {
        PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationId);
        if(personalInformation==null){
            return null;
        }
        //1.获得User信息
        User user = iUserService.getById(personalInformation.getUserid());
        System.out.println(456);
        if (user!=null) {
            personalInformation.setIsactive(user.getIsactive());
            personalInformation.setUsername(user.getUsername());
            personalInformation.setTruename(user.getTruename());
        }
        //2.获得基本信息
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation.getBaseinformationid());
        if (baseInformation!=null) {
            personalInformation.setUserphoto(baseInformation.getUserphoto());
            personalInformation.setIdphoto1(baseInformation.getIdphoto1());
            personalInformation.setIdphoto2(baseInformation.getIdphoto2());
            personalInformation.setEnglishname(baseInformation.getEnglishname());
            personalInformation.setIdcode(baseInformation.getIdcode());
            personalInformation.setBirthday(baseInformation.getBirthday());
            personalInformation.setAge(IDcodeUtil.getAge(baseInformation.getBirthday()));
            personalInformation.setConstellation(baseInformation.getConstellation());
            personalInformation.setChinesecs(baseInformation.getChinesecs());
            if (ihRsetRaceService.queryById(baseInformation.getRaceid())!=null) {
                personalInformation.setRace(ihRsetRaceService.queryById(baseInformation.getRaceid()).getRace());
            }
            personalInformation.setMarriage(baseInformation.getMarriage());
            if (ihRsetChildrenService.queryById(baseInformation.getChildrenid())!=null) {
                personalInformation.setChildren(ihRsetChildrenService.queryById(baseInformation.getChildrenid()).getChildren());
            }
            if (ihRsetZzmmService.queryById(baseInformation.getZzmmid())!=null) {
                personalInformation.setZzmm(ihRsetZzmmService.queryById(baseInformation.getZzmmid()).getZzmm());
            }
            if (ihRsetZgxlService.queryById(baseInformation.getZgxlid())!=null) {
                personalInformation.setZgxl(ihRsetZgxlService.queryById(baseInformation.getZgxlid()).getZgxl());
            }
            if (ihRsetByyxService.queryById(baseInformation.getByyxid())!=null) {
                personalInformation.setByyx(ihRsetByyxService.queryById(baseInformation.getByyxid()).getByyx());
            }
            if (ihRsetSxzyService.queryById(baseInformation.getSxzyid())!=null) {
                personalInformation.setSxzy(ihRsetSxzyService.queryById(baseInformation.getSxzyid()).getSxzy());
            }
            if (ihRsetPyfsService.queryById(baseInformation.getPyfsid())!=null) {
                personalInformation.setPyfs(ihRsetPyfsService.queryById(baseInformation.getPyfsid()).getPyfs());
            }
            if (ihRsetFlaService.queryById(baseInformation.getFirstlaid())!=null) {
                personalInformation.setFirstla(ihRsetFlaService.queryById(baseInformation.getFirstlaid()).getFla());
            }
            if (ihRsetFlaService.queryById(baseInformation.getElselaid())!=null) {
                personalInformation.setElsela(ihRsetFlaService.queryById(baseInformation.getElselaid()).getFla());
            }
            if (ihRsetPosttitleService.queryById(baseInformation.getPosttitleid())!=null) {
                personalInformation.setPosttitle(ihRsetPosttitleService.queryById(baseInformation.getPosttitleid()).getPosttitle());
            }
            if (ihRsetZyzstypeService.queryById(baseInformation.getZyzstypeid())!=null) {
                personalInformation.setZyzstype(ihRsetZyzstypeService.queryById(baseInformation.getZyzstypeid()).getZyzstype());
            }
            if (ihRsetZyzsnameService.queryById(baseInformation.getZyzsnameid())!=null) {
                personalInformation.setZyzsname(ihRsetZyzsnameService.queryById(baseInformation.getZyzsnameid()).getZyzsname());
            }
            personalInformation.setFirstworkingtime(baseInformation.getFirstworkingtime());
            if (baseInformation.getFirstworkingtime()!=null && !"".equals(baseInformation.getFirstworkingtime())) {
                personalInformation.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
            }
            if (ihRsetParentcompanyService.queryById(baseInformation.getParentcompanyid())!=null) {
                personalInformation.setParentcompany(ihRsetParentcompanyService.queryById(baseInformation.getParentcompanyid()).getParentcompanyname());
            }
        }
        //3.获得管理信息
        ManageInformation manageInformation = iManageInformationService.queryOneById(personalInformation.getManageinformationid());
        if (manageInformation!=null) {
            if (iDeptService.queryOneDepByDepid(personalInformation.getDepid())!=null) {
                personalInformation.setCompany(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getCompanyname());
                personalInformation.setDepname(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getDepname());
                if (iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid())!=null) {
                    personalInformation.setPrincipaltruename(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getTruename());
                    if (iPersonalInformationService.queryOneByUserid(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getId())!=null) {
                        personalInformation.setPrincipalemployeenumber(iPersonalInformationService.queryOneByUserid(iUserService.getById(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getPrincipaluserid()).getId()).getEmployeenumber());
                    }
                }
            }
            List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(personalInformationId);
            List<String> strs = new ArrayList<>();
            List<Integer> postids = new ArrayList<>();
            if (perAndPostRs.size()!=0) {
                for(PerAndPostRs perAndPost:perAndPostRs){
                    strs.add(iPostService.queryOneByPostid(perAndPost.getPostid()).getPostname());
                    postids.add(perAndPost.getPostid());
                }
            }
            personalInformation.setPostnames(IDcodeUtil.getArrayToString(strs,";"));
            personalInformation.setPostids(postids);
            if (ihRsetRankService.queryById(manageInformation.getRankid())!=null) {
                personalInformation.setZj(ihRsetRankService.queryById(manageInformation.getRankid()).getRank());
            }
            personalInformation.setEntrydate(manageInformation.getEntrydate());
            personalInformation.setSn(IDcodeUtil.getCompanyAge(manageInformation.getEntrydate()));
            personalInformation.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
            if (ihRsetEmployeetypeService.queryById(manageInformation.getEmployeetypeid())!=null) {
                personalInformation.setEmployeetype(ihRsetEmployeetypeService.queryById(manageInformation.getEmployeetypeid()).getEmployeetype());
            }
        }
        //获得成本信息
        CostInformation costInformation = iCostInformationService.queryOneById(personalInformation.getCostinformationid());
        if (costInformation!=null) {
            if (ihRsetSalarystandardService.queryById(costInformation.getSalarystandardid())!=null) {
                personalInformation.setSalary(ihRsetSalarystandardService.queryById(costInformation.getSalarystandardid()).getSalarystandard());
            }
            if (ihRsetSsbService.queryById(costInformation.getSsbid())!=null) {
                personalInformation.setSsb(ihRsetSsbService.queryById(costInformation.getSsbid()).getSsb());
            }
            if (ihRsetSsbgscdService.queryById(costInformation.getSsbgscdid())!=null) {
                personalInformation.setSsbgscd(ihRsetSsbgscdService.queryById(costInformation.getSsbgscdid()).getSsbgscd());
            }
            if (ihRsetSsbgrcdService.queryById(costInformation.getSsbgrcdid())!=null) {
                personalInformation.setSsbgrcd(ihRsetSsbgrcdService.queryById(costInformation.getSsbgrcdid()).getSsbgrcd());
            }
            if (ihRsetGjjService.queryById(costInformation.getGjjid())!=null) {
                personalInformation.setGjj(ihRsetGjjService.queryById(costInformation.getGjjid()).getGjj());
            }
            if (ihRsetGjjgscdService.queryById(costInformation.getGjjgscdid())!=null) {
                personalInformation.setGjjgscd(ihRsetGjjgscdService.queryById(costInformation.getGjjgscdid()).getGjjgscd());
            }
            if (ihRsetGjjgrcdService.queryById(costInformation.getGjjgrcdid())!=null) {
                personalInformation.setGjjgrcd(ihRsetGjjgrcdService.queryById(costInformation.getGjjgrcdid()).getGjjgrcd());
            }
            if (ihRsetKhhService.queryById(costInformation.getKhhid())!=null) {
                personalInformation.setKhh(ihRsetKhhService.queryById(costInformation.getKhhid()).getKhh());
            }
            personalInformation.setSalaryaccount(costInformation.getSalaryaccount());
            if (ihRsetSbjndService.queryById(costInformation.getSbjndid())!=null) {
                personalInformation.setSbjnd(ihRsetSbjndService.queryById(costInformation.getSbjndid()).getSbjnd());
            }
            personalInformation.setSbcode(costInformation.getSbcode());
            personalInformation.setGjjcode(costInformation.getGjjcode());
        }
        //获得其他信息
        OtherInformation otherInformation = iOtherInformationService.queryOneById(personalInformation.getOtherinformationid());
        if (otherInformation!=null) {
            if (ihRsetTelphoneService.queryById(personalInformation.getTelphoneid())!=null) {
                personalInformation.setTelphone(ihRsetTelphoneService.queryById(personalInformation.getTelphoneid()).getTelphone());
            }
            personalInformation.setPrivateemail(otherInformation.getPrivateemail());
            personalInformation.setCompanyemail(otherInformation.getCompanyemail());
            personalInformation.setEmergencycontract(otherInformation.getEmergencycontract());
            if (ihRsetEmergencyrpService.queryById(otherInformation.getEmergencyrpid())!=null) {
                personalInformation.setEmergencyrp(ihRsetEmergencyrpService.queryById(otherInformation.getEmergencyrpid()).getEmergencyrp());
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
            HttpServletRequest request
    ) throws ParseException {
        //工号校验
        List<PersonalInformation> personalInformationList = iPersonalInformationService.queryByEmployeenumber(personalInformation.getEmployeenumber());
        if(personalInformationList.size()>0){
            return "工号已存在，请重新输入！";
        }
        //username校验
        List<User> users;
        if (personalInformation.getUsername()!=null && !personalInformation.getUsername().equals("")) {
            users = iUserService.queryByUsername(personalInformation.getUsername());
        } else {
            users = iUserService.queryByUsername(SpellUtils.phoneticize(personalInformation.getTruename()));
        }
        if(users.size()>0){
            return "登录ID已存在，请重新输入（不输入则默认为姓名的汉语拼音）！";
        }
        //校验省份证号码
        if(personalInformation.getIdcode()!=null && !"".equals(personalInformation.getIdcode())){
            String birthday = IDcodeUtil.getBirthday(personalInformation.getIdcode());
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
            if(year>1953 && year<year2 && month>0 && month<13){
                Integer daysByDate = IDcodeUtil.getDaysByDate(simpleDateFormat.parse(birthday));
                if(day>1 && day<=daysByDate){
                    b = true;
                }
            }
            if(b==false){
                return "身份证号码输入有误请重新输入";
            }
        }

        User user = new User();
        BaseInformation baseInformation = new BaseInformation();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> idphoto2s= multipartRequest.getFiles("idphoto22");
        List<MultipartFile> idphoto1s= multipartRequest.getFiles("idphoto11");
        List<MultipartFile> userphoto2s= multipartRequest.getFiles("userphoto2");

        // 将三个文件在服务器中保存下来
        try {
            if (userphoto2s.size()!=0 || idphoto2s.size()!=0 || idphoto1s.size()!=0) {
                String realPath = Commons.realpath + "/hr/image";
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/" + l);
                file.mkdirs();
                if (userphoto2s.size()!=0) {
                    String userphoto = "/hr/image/" + l+ "/" + userphoto2s.get(0).getOriginalFilename();
                    userphoto2s.get(0).transferTo(new File(realPath + "/" + l,userphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setUserphoto(userphoto);
                }
                if (idphoto1s.size()!=0) {
                    String idphoto1 = "/hr/image/" + l+ "/" + idphoto1s.get(0).getOriginalFilename();
                    idphoto1s.get(0).transferTo(new File(realPath + "/" + l,idphoto1s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto1(idphoto1);
                }
                if (idphoto2s.size()!=0) {
                    String idphoto2 = "/hr/image/" + l+ "/" + idphoto2s.get(0).getOriginalFilename();
                    idphoto2s.get(0).transferTo(new File(realPath + "/" + l,idphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto2(idphoto2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 在tb_id_user表中保存用户
        user.setIsactive(personalInformation.getIsactive());
        if (personalInformation.getUsername()!=null && !"".equals(personalInformation.getUsername())) {
            user.setUsername(personalInformation.getUsername());
        } else {
            user.setUsername(SpellUtils.phoneticize(personalInformation.getTruename()));//如果没有人工输入，则自动将名字的汉字转换为汉语拼音
        }
        user.setTruename(personalInformation.getTruename());
        Integer userid = iUserService.saveOne(user);

        // 在tb_id_baseinformation表中保存用户基本信息
        baseInformation.setEnglishname(personalInformation.getEnglishname());
        baseInformation.setIdcode(personalInformation.getIdcode());
        if(personalInformation.getIdcode()!=null && !"".equals(personalInformation.getIdcode())){
            baseInformation.setBirthday(IDcodeUtil.getBirthday(personalInformation.getIdcode()));//出生日期
            baseInformation.setConstellation(IDcodeUtil.getConstellation(personalInformation.getIdcode()));//星座
            baseInformation.setChinesecs(IDcodeUtil.getChinesecs(personalInformation.getIdcode()));//属相
            personalInformation.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));//性别
        }
        if (ihRsetRaceService.queryByRace(personalInformation.getRace())!=null) {
            baseInformation.setRaceid(ihRsetRaceService.queryByRace(personalInformation.getRace()).getId());
        }
        baseInformation.setMarriage(personalInformation.getMarriage());
        if (ihRsetChildrenService.queryByChildren(personalInformation.getChildren())!=null) {
            baseInformation.setChildrenid(ihRsetChildrenService.queryByChildren(personalInformation.getChildren()).getId());
        }
        if (ihRsetZzmmService.queryByZzmm(personalInformation.getZzmm())!=null) {
            baseInformation.setZzmmid(ihRsetZzmmService.queryByZzmm(personalInformation.getZzmm()).getId());
        }
        if (ihRsetZgxlService.queryByZgxl(personalInformation.getZgxl())!=null) {
            baseInformation.setZgxlid(ihRsetZgxlService.queryByZgxl(personalInformation.getZgxl()).getId());
        }
        if ("选择录入".equals(byyxvalue)) {
            HRsetByyx hRsetByyx = ihRsetByyxService.queryByByyx(personalInformation.getByyx());
            if(hRsetByyx!=null){
                baseInformation.setByyxid(hRsetByyx.getId());
            }else {
                if (!personalInformation.getByyx().equals("")) {
                    HRsetByyx hRsetByyx1 = new HRsetByyx();
                    hRsetByyx1.setByyx(personalInformation.getByyx());
                    Integer byyxid = ihRsetByyxService.addOne(hRsetByyx1);
                    baseInformation.setByyxid(byyxid);
                }
            }
        } else {
            if (ihRsetByyxService.queryByByyx(personalInformation.getByyx())!=null) {
                baseInformation.setByyxid(ihRsetByyxService.queryByByyx(personalInformation.getByyx()).getId());
            }
        }
        if ("选择录入".equals(sxzyvalue)) {
            HRsetSxzy hRsetSxzy = ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy());
            if(hRsetSxzy!=null){
                baseInformation.setSxzyid(hRsetSxzy.getId());
            }else {
                if (!personalInformation.getSxzy().equals("")) {
                    HRsetSxzy hRsetSxzy1 = new HRsetSxzy();
                    hRsetSxzy1.setSxzy(personalInformation.getSxzy());
                    Integer sxzyid = ihRsetSxzyService.addOne(hRsetSxzy1);
                    baseInformation.setSxzyid(sxzyid);
                }
            }
        } else {
            if (ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy())!=null) {
                baseInformation.setSxzyid(ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy()).getId());
            }
        }
        if (ihRsetPyfsService.queryByPyfs(personalInformation.getPyfs())!=null) {
            baseInformation.setPyfsid(ihRsetPyfsService.queryByPyfs(personalInformation.getPyfs()).getId());
        }
        if (ihRsetFlaService.queryByFla(personalInformation.getFirstla())!=null) {
            baseInformation.setFirstlaid(ihRsetFlaService.queryByFla(personalInformation.getFirstla()).getId());
        }
        if (ihRsetFlaService.queryByFla(personalInformation.getElsela())!=null) {
            baseInformation.setElselaid(ihRsetFlaService.queryByFla(personalInformation.getElsela()).getId());
        }
        if (ihRsetPosttitleService.queryByPosttitle(personalInformation.getPosttitle())!=null) {
            baseInformation.setPosttitleid(ihRsetPosttitleService.queryByPosttitle(personalInformation.getPosttitle()).getId());
        }
        if (ihRsetZyzstypeService.queryByZyzstype(personalInformation.getZyzstype())!=null) {
            baseInformation.setZyzstypeid(ihRsetZyzstypeService.queryByZyzstype(personalInformation.getZyzstype()).getId());
        }
        if ("选择录入".equals(zyzsnamevalue)) {
            HRsetZyzsname hRsetZyzsname = ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname());
            if(hRsetZyzsname!=null){
                baseInformation.setZyzsnameid(hRsetZyzsname.getId());
            }else {
                if (!personalInformation.getZyzsname().equals("")) {
                    HRsetZyzsname hRsetZyzsname1 = new HRsetZyzsname();
                    hRsetZyzsname1.setZyzsname(personalInformation.getZyzsname());
                    Integer zyzsnameid = ihRsetZyzsnameService.addOne(hRsetZyzsname1);
                    baseInformation.setZyzsnameid(zyzsnameid);
                }
            }
        } else {
            if (ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname())!=null) {
                baseInformation.setZyzsnameid(ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname()).getId());
            }
        }
        baseInformation.setFirstworkingtime(personalInformation.getFirstworkingtime());
        if ("选择录入".equals(parentcompanyvalue)) {
            HRsetParentcompany hRsetParentcompany = ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany());
            if(hRsetParentcompany!=null){
                baseInformation.setParentcompanyid(hRsetParentcompany.getId());
            }else {
                if (!personalInformation.getParentcompany().equals("")) {
                    HRsetParentcompany hRsetParentcompany1 = new HRsetParentcompany();
                    hRsetParentcompany1.setParentcompanyname(personalInformation.getParentcompany());
                    Integer parentcompanyid = ihRsetParentcompanyService.addOne(hRsetParentcompany1);
                    baseInformation.setParentcompanyid(parentcompanyid);
                }
            }
        } else {
            if (ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany())!=null) {
                baseInformation.setParentcompanyid(ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany()).getId());
            }
        }
        Integer baseInformationId = iBaseInformationService.saveOne(baseInformation);

        // 在tb_id_personalinformation表中添加数据
        personalInformation.setUserid(userid);
        personalInformation.setBaseinformationid(baseInformationId);
        Integer personalInformationId = iPersonalInformationService.saveOne(personalInformation);
        projectBoardService.informationUpdate();
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的管理信息
     *@Date: 11:36 2018\4\11 0011
     */
    @RequestMapping("/addManageInformation")
    @ResponseBody
    public String addManageInformation(
            PersonalInformation personalInformation
    ){
        // 保存人事信息的管理信息
        ManageInformation manageInformation = new ManageInformation();
        if (ihRsetRankService.queryByRank(personalInformation.getZj())!=null) {
            manageInformation.setRankid(ihRsetRankService.queryByRank(personalInformation.getZj()).getId());
        }
        if (ihRsetEmployeetypeService.queryByEmployeetype(personalInformation.getEmployeetype())!=null) {
            manageInformation.setEmployeetypeid(ihRsetEmployeetypeService.queryByEmployeetype(personalInformation.getEmployeetype()).getId());
        }
        manageInformation.setEntrydate(personalInformation.getEntrydate());
        if (null!=personalInformation.getEntrydate() && !"".equals(personalInformation.getEntrydate())) {
            manageInformation.setZhuanzhengdate(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
        }
        Integer manageInformationId = iManageInformationService.saveOne(manageInformation);

        // 修改人事信息的主体信息
        PersonalInformation personalInformation1 = iPersonalInformationService.queryOneByUserid(personalInformation.getUserid());
        personalInformation.setId(personalInformation1.getId());
        personalInformation.setManageinformationid(manageInformationId);
        iPersonalInformationService.modifyOne(personalInformation);

        //添加人事与岗位关系表的信息
        List<Integer> postids = personalInformation.getPostids();
        for(Integer postid:postids){
            PerAndPostRs perAndPostRs = new PerAndPostRs(personalInformation.getId(),postid);
            iPerandpostrsService.addOne(perAndPostRs);
        }
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
            PersonalInformation personalInformation,
            @RequestParam("khhvalue") String khhvalue,
            @RequestParam("sbjndvalue") String sbjndvalue
    ){
        // 保存人事信息的成本信息
        CostInformation costInformation = new CostInformation();
        if (ihRsetSalarystandardService.queryBySalarystandard(personalInformation.getSalary())!=null) {
            costInformation.setSalarystandardid(ihRsetSalarystandardService.queryBySalarystandard(personalInformation.getSalary()).getId());
        }
        if (ihRsetSsbService.queryBySsb(personalInformation.getSsb())!=null) {
            costInformation.setSsbid(ihRsetSsbService.queryBySsb(personalInformation.getSsb()).getId());
        }
        if (ihRsetSsbgscdService.queryBySsbgscd(personalInformation.getSsbgscd())!=null) {
            costInformation.setSsbgscdid(ihRsetSsbgscdService.queryBySsbgscd(personalInformation.getSsbgscd()).getId());
        }
        if (ihRsetSsbgrcdService.queryBySsbgrcd(personalInformation.getSsbgrcd())!=null) {
            costInformation.setSsbgrcdid(ihRsetSsbgrcdService.queryBySsbgrcd(personalInformation.getSsbgrcd()).getId());
        }
        if (ihRsetGjjService.queryByGjj(personalInformation.getGjj())!=null) {
            costInformation.setGjjid(ihRsetGjjService.queryByGjj(personalInformation.getGjj()).getId());
        }
        if (ihRsetGjjgscdService.queryByGjjgscd(personalInformation.getGjjgscd())!=null) {
            costInformation.setGjjgscdid(ihRsetGjjgscdService.queryByGjjgscd(personalInformation.getGjjgscd()).getId());
        }
        if (ihRsetGjjgrcdService.queryByGjjgrcd(personalInformation.getGjjgrcd())!=null) {
            costInformation.setGjjgrcdid(ihRsetGjjgrcdService.queryByGjjgrcd(personalInformation.getGjjgrcd()).getId());
        }
        if (khhvalue.equals("选择录入")) {
            HRsetKhh hRsetKhh = ihRsetKhhService.queryByKhh(personalInformation.getKhh());
            if (hRsetKhh!=null) {
                costInformation.setKhhid(hRsetKhh.getId());
            }else {
                if (!personalInformation.getKhh().equals("")) {
                    HRsetKhh hRsetKhh1 = new HRsetKhh();
                    hRsetKhh1.setKhh(personalInformation.getKhh());
                    Integer khhid = ihRsetKhhService.addOne(hRsetKhh1);
                    costInformation.setKhhid(khhid);
                }
            }
        } else {
            if (ihRsetKhhService.queryByKhh(personalInformation.getKhh())!=null) {
                costInformation.setKhhid(ihRsetKhhService.queryByKhh(personalInformation.getKhh()).getId());
            }
        }
        costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
        if (sbjndvalue.equals("选择录入")) {
            HRsetSbjnd hRsetSbjnd = ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd());
            if(hRsetSbjnd!=null){
                costInformation.setSbjndid(hRsetSbjnd.getId());
            }else{
                if (!personalInformation.getSbjnd().equals("")) {
                    HRsetSbjnd hRsetSbjnd1 = new HRsetSbjnd();
                    hRsetSbjnd1.setSbjnd(personalInformation.getSbjnd());
                    Integer sbjndid = ihRsetSbjndService.addOne(hRsetSbjnd1);
                    costInformation.setSbjndid(sbjndid);
                }
            }
        } else {
            if (ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd())!=null) {
                costInformation.setSbjndid(ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd()).getId());
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
     *@Author:ShiYun;
     *@Description:添加人事信息的其它信息
     *@Date: 18:42 2018\4\11 0011
     */
    @RequestMapping("/addOtherInformation")
    @ResponseBody
    public String addOtherInformation(
            PersonalInformation personalInformation
    ){
        // 添加人事信息的其它信息
        OtherInformation otherInformation = new OtherInformation();
        otherInformation.setCompanyemail(personalInformation.getCompanyemail());
        otherInformation.setPrivateemail(personalInformation.getPrivateemail());
        otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
        if (ihRsetEmergencyrpService.queryByEmergencyrp(personalInformation.getEmergencyrp())!=null) {
            otherInformation.setEmergencyrpid(ihRsetEmergencyrpService.queryByEmergencyrp(personalInformation.getEmergencyrp()).getId());
        }
        otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
        otherInformation.setAddress(personalInformation.getAddress());
        otherInformation.setRemark(personalInformation.getRemark());
        Integer otherInformationId = iOtherInformationService.saveOne(otherInformation);

        // 修改人事信息
        PersonalInformation personalInformation1 = iPersonalInformationService.queryOneByUserid(personalInformation.getUserid());
        personalInformation.setId(personalInformation1.getId());
        if (ihRsetTelphoneService.queryByTelphone(personalInformation.getTelphone())!=null) {
            personalInformation.setTelphoneid(ihRsetTelphoneService.queryByTelphone(personalInformation.getTelphone()).getId());
        }
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
        List<MultipartFile> idphoto2s= multipartRequest.getFiles("idphoto22");
        List<MultipartFile> idphoto1s= multipartRequest.getFiles("idphoto11");
        List<MultipartFile> userphoto2s= multipartRequest.getFiles("userphoto2");

        //tb_id_baseinformation表的标识
        Boolean baseBL = false;
        // 将三个文件在服务器中保存下来
        BaseInformation baseInformation = iBaseInformationService.queryOneById(personalInformation2.getBaseinformationid());
        if(baseInformation==null){
            baseInformation = new BaseInformation();//如果不存在则新建实例，下面就是添加信息了
        }
        try {
            if (userphoto2s.size()!=0 || idphoto2s.size()!=0 || idphoto1s.size()!=0) {
                String realPath = Commons.realpath + "/hr/image";
                Long l = Calendar.getInstance().getTimeInMillis();
                File file = new File(realPath + "/" + l);
                file.mkdirs();
                if (userphoto2s.size()!=0) {
                    String userphoto = "/hr/image/" + l+ "/" + userphoto2s.get(0).getOriginalFilename();
                    userphoto2s.get(0).transferTo(new File(realPath + "/" + l,userphoto2s.get(0).getOriginalFilename()));
                    baseInformation.setUserphoto(userphoto);
                    changeInformation.setChangeinformation("免冠照片");
                    changeInformation.setBeforeinformation(personalInformation2.getUserphoto());
                    changeInformation.setAfterinformation(userphoto);
                    iChangeInformationService.addOne(changeInformation);
                    baseBL = true;
                }
                if (idphoto1s.size()!=0) {
                    String idphoto1 = "/hr/image/" + l+ "/" + idphoto1s.get(0).getOriginalFilename();
                    idphoto1s.get(0).transferTo(new File(realPath + "/" + l,idphoto1s.get(0).getOriginalFilename()));
                    baseInformation.setIdphoto1(idphoto1);
                    changeInformation.setChangeinformation("身份证扫描件正面");
                    changeInformation.setBeforeinformation(personalInformation2.getIdphoto1());
                    changeInformation.setAfterinformation(idphoto1);
                    iChangeInformationService.addOne(changeInformation);
                    baseBL = true;
                }
                if (idphoto2s.size()!=0) {
                    String idphoto2 = "/hr/image/" + l+ "/" + idphoto2s.get(0).getOriginalFilename();
                    idphoto2s.get(0).transferTo(new File(realPath + "/" + l,idphoto2s.get(0).getOriginalFilename()));
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
        }
        //tb_id_user表的标识
        Boolean userBL = false;
        // 在tb_id_user表中更新用户信息
        User user = iUserService.getById(personalInformation.getUserid());
        if (personalInformation2.getIsactive()!=personalInformation.getIsactive()) {
            user.setIsactive(personalInformation.getIsactive());
            changeInformation.setChangeinformation("账号激活状态");
            changeInformation.setBeforeinformation(personalInformation2.getIsactive()==1?"已激活":"未激活");
            changeInformation.setAfterinformation(personalInformation.getIsactive()==1?"已激活":"未激活");
            iChangeInformationService.addOne(changeInformation);
            userBL = true;
        }
        if (!personalInformation.getUsername().equals(personalInformation2.getUsername())) {
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
        if(personalInformation.getIdcode()!=null && !"".equals(personalInformation.getIdcode())){
            baseInformation.setBirthday(IDcodeUtil.getBirthday(personalInformation.getIdcode()));//出生日期
            baseInformation.setConstellation(IDcodeUtil.getConstellation(personalInformation.getIdcode()));//星座
            baseInformation.setChinesecs(IDcodeUtil.getChinesecs(personalInformation.getIdcode()));//属相
            personalInformation2.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));//性别
        }
        if (ihRsetRaceService.queryByRace(personalInformation.getRace())!=null) {
            if (!personalInformation.getRace().equals(personalInformation2.getRace())) {
                baseInformation.setRaceid(ihRsetRaceService.queryByRace(personalInformation.getRace()).getId());
                changeInformation.setChangeinformation("民族");
                changeInformation.setBeforeinformation(personalInformation2.getRace());
                changeInformation.setAfterinformation(personalInformation.getRace());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (!personalInformation.getMarriage().equals(personalInformation2.getMarriage())) {
            baseInformation.setMarriage(personalInformation.getMarriage());
            changeInformation.setChangeinformation("婚姻");
            changeInformation.setBeforeinformation(personalInformation2.getMarriage());
            changeInformation.setAfterinformation(personalInformation.getMarriage());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (ihRsetChildrenService.queryByChildren(personalInformation.getChildren())!=null) {
            if (!personalInformation.getChildren().equals(personalInformation2.getChildren())) {
                baseInformation.setChildrenid(ihRsetChildrenService.queryByChildren(personalInformation.getChildren()).getId());
                changeInformation.setChangeinformation("生育");
                changeInformation.setBeforeinformation(personalInformation2.getChildren());
                changeInformation.setAfterinformation(personalInformation.getChildren());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (ihRsetZzmmService.queryByZzmm(personalInformation.getZzmm())!=null) {
            if (!personalInformation.getZzmm().equals(personalInformation2.getZzmm())) {
                baseInformation.setZzmmid(ihRsetZzmmService.queryByZzmm(personalInformation.getZzmm()).getId());
                changeInformation.setChangeinformation("政治面貌");
                changeInformation.setBeforeinformation(personalInformation2.getZzmm());
                changeInformation.setAfterinformation(personalInformation.getZzmm());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (ihRsetZgxlService.queryByZgxl(personalInformation.getZgxl())!=null) {
            if (!personalInformation.getZgxl().equals(personalInformation2.getZgxl())) {
                baseInformation.setZgxlid(ihRsetZgxlService.queryByZgxl(personalInformation.getZgxl()).getId());
                changeInformation.setChangeinformation("最高学历");
                changeInformation.setBeforeinformation(personalInformation2.getZgxl());
                changeInformation.setAfterinformation(personalInformation.getZgxl());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (!personalInformation.getByyx().equals(personalInformation2.getByyx())) {
            if ("选择录入".equals(byyxvalue)) {
                HRsetByyx hRsetByyx = ihRsetByyxService.queryByByyx(personalInformation.getByyx());
                if(hRsetByyx!=null){
                    baseInformation.setByyxid(hRsetByyx.getId());
                }else {
                    if (!personalInformation.getByyx().equals("")) {
                        HRsetByyx hRsetByyx1 = new HRsetByyx();
                        hRsetByyx1.setByyx(personalInformation.getByyx());
                        Integer byyxid = ihRsetByyxService.addOne(hRsetByyx1);
                        baseInformation.setByyxid(byyxid);
                    }
                }
            } else {
                if (ihRsetByyxService.queryByByyx(personalInformation.getByyx())!=null) {
                    baseInformation.setByyxid(ihRsetByyxService.queryByByyx(personalInformation.getByyx()).getId());
                }
            }
            changeInformation.setChangeinformation("毕业院校");
            changeInformation.setBeforeinformation(personalInformation2.getByyx());
            changeInformation.setAfterinformation(personalInformation.getByyx());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (!personalInformation.getSxzy().equals(personalInformation2.getSxzy())) {
            if ("选择录入".equals(sxzyvalue)) {
                HRsetSxzy hRsetSxzy = ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy());
                if(hRsetSxzy!=null){
                    baseInformation.setSxzyid(hRsetSxzy.getId());
                }else {
                    if (!personalInformation.getSxzy().equals("")) {
                        HRsetSxzy hRsetSxzy1 = new HRsetSxzy();
                        hRsetSxzy1.setSxzy(personalInformation.getSxzy());
                        Integer sxzyid = ihRsetSxzyService.addOne(hRsetSxzy1);
                        baseInformation.setSxzyid(sxzyid);
                    }
                }
            } else {
                if (ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy())!=null) {
                    baseInformation.setSxzyid(ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy()).getId());
                }
            }
            changeInformation.setChangeinformation("所学专业");
            changeInformation.setBeforeinformation(personalInformation2.getSxzy());
            changeInformation.setAfterinformation(personalInformation.getSxzy());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (ihRsetPyfsService.queryByPyfs(personalInformation.getPyfs())!=null) {
            if (!personalInformation.getPyfs().equals(personalInformation2.getPyfs())) {
                baseInformation.setPyfsid(ihRsetPyfsService.queryByPyfs(personalInformation.getPyfs()).getId());
                changeInformation.setChangeinformation("培养方式");
                changeInformation.setBeforeinformation(personalInformation2.getPyfs());
                changeInformation.setAfterinformation(personalInformation.getPyfs());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (ihRsetFlaService.queryByFla(personalInformation.getFirstla())!=null) {
            if (!personalInformation.getFirstla().equals(personalInformation2.getFirstla())) {
                baseInformation.setFirstlaid(ihRsetFlaService.queryByFla(personalInformation.getFirstla()).getId());
                changeInformation.setChangeinformation("第一外语");
                changeInformation.setBeforeinformation(personalInformation2.getFirstla());
                changeInformation.setAfterinformation(personalInformation.getFirstla());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (ihRsetFlaService.queryByFla(personalInformation.getElsela())!=null) {
            if (!personalInformation.getElsela().equals(personalInformation2.getElsela())) {
                baseInformation.setElselaid(ihRsetFlaService.queryByFla(personalInformation.getElsela()).getId());
                changeInformation.setChangeinformation("其他外语");
                changeInformation.setBeforeinformation(personalInformation2.getElsela());
                changeInformation.setAfterinformation(personalInformation.getElsela());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (ihRsetPosttitleService.queryByPosttitle(personalInformation.getPosttitle())!=null) {
            if (!personalInformation.getPosttitle().equals(personalInformation2.getPosttitle())) {
                baseInformation.setPosttitleid(ihRsetPosttitleService.queryByPosttitle(personalInformation.getPosttitle()).getId());
                changeInformation.setChangeinformation("职称");
                changeInformation.setBeforeinformation(personalInformation2.getPosttitle());
                changeInformation.setAfterinformation(personalInformation.getPosttitle());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (ihRsetZyzstypeService.queryByZyzstype(personalInformation.getZyzstype())!=null) {
            if (!personalInformation.getZyzstype().equals(personalInformation2.getZyzstype())) {
                baseInformation.setZyzstypeid(ihRsetZyzstypeService.queryByZyzstype(personalInformation.getZyzstype()).getId());
                changeInformation.setChangeinformation("职业证书类型");
                changeInformation.setBeforeinformation(personalInformation2.getZyzstype());
                changeInformation.setAfterinformation(personalInformation.getZyzstype());
                iChangeInformationService.addOne(changeInformation);
                baseBL = true;
            }
        }
        if (!personalInformation.getZyzsname().equals(personalInformation2.getZyzsname())) {
            if ("选择录入".equals(zyzsnamevalue)) {
                HRsetZyzsname hRsetZyzsname = ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname());
                if(hRsetZyzsname!=null){
                    baseInformation.setZyzsnameid(hRsetZyzsname.getId());
                }else {
                    if (!personalInformation.getZyzsname().equals("")) {
                        HRsetZyzsname hRsetZyzsname1 = new HRsetZyzsname();
                        hRsetZyzsname1.setZyzsname(personalInformation.getZyzsname());
                        Integer zyzsnameid = ihRsetZyzsnameService.addOne(hRsetZyzsname1);
                        baseInformation.setZyzsnameid(zyzsnameid);
                    }
                }
            } else {
                if (ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname())!=null) {
                    baseInformation.setZyzsnameid(ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname()).getId());
                }
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
        if (!personalInformation.getParentcompany().equals(personalInformation2.getParentcompany())) {
            if ("选择录入".equals(parentcompanyvalue)) {
                HRsetParentcompany hRsetParentcompany = ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany());
                if(hRsetParentcompany!=null){
                    baseInformation.setParentcompanyid(hRsetParentcompany.getId());
                }else {
                    if (!personalInformation.getParentcompany().equals("")) {
                        HRsetParentcompany hRsetParentcompany1 = new HRsetParentcompany();
                        hRsetParentcompany1.setParentcompanyname(personalInformation.getParentcompany());
                        Integer parentcompanyid = ihRsetParentcompanyService.addOne(hRsetParentcompany1);
                        baseInformation.setParentcompanyid(parentcompanyid);
                    }
                }
            } else {
                if (ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany())!=null) {
                    baseInformation.setParentcompanyid(ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany()).getId());
                }
            }
            changeInformation.setChangeinformation("上家雇主");
            changeInformation.setBeforeinformation(personalInformation2.getParentcompany());
            changeInformation.setAfterinformation(personalInformation.getParentcompany());
            iChangeInformationService.addOne(changeInformation);
            baseBL = true;
        }
        if (baseBL) {
            if (baseInformation.getId()==null) {
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
            personalInformation2.setEmployeenumber(personalInformation.getEmployeenumber());
            changeInformation.setAfterinformation(personalInformation2.getEmployeenumber());
            iChangeInformationService.addOne(changeInformation);
        }
        iPersonalInformationService.modifyOne(personalInformation);
        projectBoardService.informationUpdate();
        return "信息提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息的管理信息
     *@Date: 15:59 2018\4\12 0012
     */
    @RequestMapping("/updateManageInformation")
    @ResponseBody
    public String updateManageInformation(
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

        if (personalInformation2.getDepid()!=personalInformation.getDepid()) {
            changeInformation.setChangeinformation("部门");
            changeInformation.setBeforeinformation(personalInformation2.getDepname());
            changeInformation.setAfterinformation(iDeptService.queryOneDepByDepid(personalInformation.getDepid()).getDepname());
            personalInformation2.setDepid(personalInformation.getDepid());
            iChangeInformationService.addOne(changeInformation);
            listBL = true;
        }

        List<Integer> postids = personalInformation.getPostids();
        List<String>  strs = new ArrayList<>();
        for(int i=0;i<postids.size();i++){
            strs.add(iPostService.queryOneByPostid(postids.get(i)).getPostname());
        }
        if(personalInformation2.getPostnames()!=null && !personalInformation2.getPostnames().equals(IDcodeUtil.getArrayToString(strs,";"))){
            changeInformation.setChangeinformation("岗位");
            changeInformation.setBeforeinformation(personalInformation2.getPostnames());
            changeInformation.setAfterinformation(IDcodeUtil.getArrayToString(strs,";"));
            iPerandpostrsService.removeByPerid(personalInformation2.getId());//先删除在添加（岗位）
            for(int i=0;i<postids.size();i++){
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
        if(manageInformation==null){
            manageInformation = new ManageInformation();//不存在则新建
        }
        if (ihRsetRankService.queryByRank(personalInformation.getZj())!=null) {
            if (!personalInformation.getZj().equals(personalInformation2.getZj())) {
                changeInformation.setChangeinformation("职级");
                changeInformation.setBeforeinformation(personalInformation2.getZj());
                changeInformation.setAfterinformation(personalInformation.getZj());
                manageInformation.setRankid(ihRsetRankService.queryByRank(personalInformation.getZj()).getId());
                iChangeInformationService.addOne(changeInformation);
                manBl = true;
            }
        }

        if (ihRsetEmployeetypeService.queryByEmployeetype(personalInformation.getEmployeetype())!=null) {
            if (!personalInformation.getEmployeetype().equals(personalInformation2.getEmployeetype())) {
                changeInformation.setChangeinformation("员工类型");
                changeInformation.setBeforeinformation(personalInformation2.getEmployeetype());
                changeInformation.setAfterinformation(personalInformation.getEmployeetype());
                manageInformation.setEmployeetypeid(ihRsetEmployeetypeService.queryByEmployeetype(personalInformation.getEmployeetype()).getId());
                iChangeInformationService.addOne(changeInformation);
                manBl = true;
            }
        }

        if (personalInformation2.getEntrydate()!=null && !personalInformation2.getEntrydate().equals(personalInformation.getEntrydate())) {
            changeInformation.setChangeinformation("入职时间");
            changeInformation.setBeforeinformation(personalInformation2.getEntrydate());
            changeInformation.setAfterinformation(personalInformation.getEntrydate());
            manageInformation.setEntrydate(personalInformation.getEntrydate());
            iChangeInformationService.addOne(changeInformation);
            manBl = true;
        }

        //入职时间存在的情况再设置转正时间
        if(personalInformation.getEmployeetype()!=null && !"".equals(personalInformation.getEntrydate())){
            //转正时间不存在则自动向后两个月
            if(personalInformation.getZhuanzhengdate()!=null && !"".equals(personalInformation.getZhuanzhengdate())){
                if (!personalInformation.getZhuanzhengdate().equals(personalInformation2.getZhuanzhengdate())) {
                    changeInformation.setChangeinformation("转正日期");
                    changeInformation.setBeforeinformation(personalInformation2.getZhuanzhengdate());
                    changeInformation.setAfterinformation(personalInformation.getZhuanzhengdate());
                    manageInformation.setZhuanzhengdate(personalInformation.getZhuanzhengdate());
                    iChangeInformationService.addOne(changeInformation);
                    manBl = true;
                }
            }else {
                if (!IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()).equals(personalInformation2.getZhuanzhengdate())) {
                    changeInformation.setChangeinformation("转正日期");
                    changeInformation.setBeforeinformation(personalInformation2.getZhuanzhengdate());
                    changeInformation.setAfterinformation(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
                    manageInformation.setZhuanzhengdate(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
                    iChangeInformationService.addOne(changeInformation);
                    manBl = true;
                }
            }
        }

        if(manBl){
            if(manageInformation.getId()!=null){
                iManageInformationService.modifyOne(manageInformation);
            }else {
                Integer manageinformationid = iManageInformationService.saveOne(manageInformation);
                personalInformation2.setManageinformationid(manageinformationid);
            }
        }

        iPersonalInformationService.modifyOne(personalInformation2);
        projectBoardService.informationUpdate();
        return "提交信息成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:修改成本信息
     *@Date: 16:28 2018\4\12 0012
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
        if(changeInformation==null){
            costInformation = new CostInformation();//不存在则新建
        }

        if (ihRsetSalarystandardService.queryBySalarystandard(personalInformation.getSalary())!=null) {
            if (!personalInformation.getSalary().equals(personalInformation2.getSalary())) {
                changeInformation.setChangeinformation("薪资标准");
                changeInformation.setBeforeinformation(personalInformation2.getSalary());
                changeInformation.setAfterinformation(personalInformation.getSalary());
                costInformation.setSalarystandardid(ihRsetSalarystandardService.queryBySalarystandard(personalInformation.getSalary()).getId());
                iChangeInformationService.addOne(changeInformation);
                costBL = true;
            }
        }

        if (personalInformation.getKhh()!=null && !"".equals(personalInformation.getKhh()) && !personalInformation.getKhh().equals(personalInformation2.getKhh())) {
            if("选择录入".equals(khhvalue)){
                HRsetKhh hRsetKhh = ihRsetKhhService.queryByKhh(personalInformation.getKhh());
                if(hRsetKhh!=null){
                    costInformation.setKhhid(hRsetKhh.getId());
                }else {
                    HRsetKhh hRsetKhh1 = new HRsetKhh();
                    hRsetKhh1.setKhh(personalInformation.getKhh());
                    Integer hrsetkhhid = ihRsetKhhService.addOne(hRsetKhh1);
                    costInformation.setKhhid(hrsetkhhid);
                }
            }else {
                costInformation.setKhhid(ihRsetKhhService.queryByKhh(personalInformation.getKhh()).getId());
            }
            changeInformation.setChangeinformation("开户行");
            changeInformation.setBeforeinformation(personalInformation2.getKhh());
            changeInformation.setAfterinformation(personalInformation.getKhh());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getSalaryaccount()!=null && !"".equals(personalInformation.getSalaryaccount()) && !personalInformation.getSalaryaccount().equals(personalInformation2.getSalaryaccount())) {
            changeInformation.setChangeinformation("工资账号");
            changeInformation.setBeforeinformation(personalInformation2.getSalaryaccount());
            changeInformation.setAfterinformation(personalInformation.getSalaryaccount());
            costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getSbjnd()!=null && !"".equals(personalInformation.getSbjnd()) && !personalInformation.getSbjnd().equals(personalInformation2.getSbjnd())){
            if("选择录入".equals(sbjndvalue)){
                HRsetSbjnd hRsetSbjnd = ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd());
                if(hRsetSbjnd!=null){
                    costInformation.setSbjndid(hRsetSbjnd.getId());
                }else {
                    HRsetSbjnd hRsetSbjnd1 = new HRsetSbjnd();
                    hRsetSbjnd1.setSbjnd(personalInformation.getSbjnd());
                    Integer sbjndid = ihRsetSbjndService.addOne(hRsetSbjnd1);
                    costInformation.setSbjndid(sbjndid);
                }
            }else {
                costInformation.setSbjndid(ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd()).getId());
            }
            changeInformation.setChangeinformation("社保缴纳地");
            changeInformation.setBeforeinformation(personalInformation2.getSbjnd());
            changeInformation.setAfterinformation(personalInformation.getSbjnd());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if (personalInformation.getSbcode()!=null && !"".equals(personalInformation.getSbcode()) && !personalInformation.getSbcode().equals(personalInformation2.getSbcode())) {
            changeInformation.setChangeinformation("社保账号");
            changeInformation.setBeforeinformation(personalInformation2.getSbcode());
            changeInformation.setAfterinformation(personalInformation.getSbcode());
            costInformation.setSbcode(personalInformation.getSbcode());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getGjjcode()!=null && !"".equals(personalInformation.getGjjcode()) && !personalInformation.getGjjcode().equals(personalInformation2.getGjjcode())){
            changeInformation.setChangeinformation("公积金账号");
            changeInformation.setBeforeinformation(personalInformation2.getGjjcode());
            changeInformation.setAfterinformation(personalInformation.getGjjcode());
            costInformation.setGjjcode(personalInformation.getGjjcode());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getSsb()!=null && !"".equals(personalInformation.getSsb()) && !personalInformation.getSsb().equals(personalInformation2.getSsb())){
            changeInformation.setChangeinformation("社保基数");
            changeInformation.setBeforeinformation(personalInformation2.getSsb());
            changeInformation.setAfterinformation(personalInformation.getSsb());
            costInformation.setSsbid(ihRsetSsbService.queryBySsb(personalInformation.getSsb()).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getSsbgscd()!=null && !"".equals(personalInformation.getSsbgscd()) && !personalInformation.getSsbgscd().equals(personalInformation2.getSsbgscd())){
            changeInformation.setChangeinformation("社保公司缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getSsbgscd());
            changeInformation.setAfterinformation(personalInformation.getSsbgscd());
            costInformation.setSsbgscdid(ihRsetSsbgscdService.queryBySsbgscd(personalInformation.getSsbgscd()).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getSsbgrcd()!=null && !"".equals(personalInformation.getSsbgrcd()) && !personalInformation.getSsbgrcd().equals(personalInformation2.getSsbgrcd())){
            changeInformation.setChangeinformation("社保个人缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getSsbgrcd());
            changeInformation.setAfterinformation(personalInformation.getSsbgrcd());
            costInformation.setSsbgrcdid(ihRsetSsbgrcdService.queryBySsbgrcd(personalInformation.getSsbgrcd()).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getGjj()!=null && !"".equals(personalInformation.getGjj()) && !personalInformation.getGjj().equals(personalInformation2.getGjj())){
            changeInformation.setChangeinformation("公积金基数");
            changeInformation.setBeforeinformation(personalInformation2.getGjj());
            changeInformation.setAfterinformation(personalInformation.getGjj());
            costInformation.setGjjid(ihRsetGjjService.queryByGjj(personalInformation.getGjj()).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getGjjgscd()!=null && !"".equals(personalInformation.getGjjgscd()) && !personalInformation.getGjjgscd().equals(personalInformation2.getGjjgscd())){
            changeInformation.setChangeinformation("公积金公司缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getGjjgscd());
            changeInformation.setAfterinformation(personalInformation.getGjjgscd());
            costInformation.setGjjgscdid(ihRsetGjjgscdService.queryByGjjgscd(personalInformation.getGjjgscd()).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(personalInformation.getGjjgrcd()!=null && !"".equals(personalInformation.getGjjgrcd()) && !personalInformation.getGjjgrcd().equals(personalInformation2.getGjjgrcd())){
            changeInformation.setChangeinformation("公积金个人缴费比例");
            changeInformation.setBeforeinformation(personalInformation2.getGjjgrcd());
            changeInformation.setAfterinformation(personalInformation.getGjjgrcd());
            costInformation.setGjjgrcdid(ihRsetGjjgrcdService.queryByGjjgrcd(personalInformation.getGjjgrcd()).getId());
            iChangeInformationService.addOne(changeInformation);
            costBL = true;
        }

        if(costBL){
            if(costInformation.getId()!=null){
                iCostInformationService.modifyOne(costInformation);
            }else {
                Integer costinformationid = iCostInformationService.saveOne(costInformation);
                personalInformation2.setCostinformationid(costinformationid);
            }
        }

        iPersonalInformationService.modifyOne(personalInformation2);
        return "提交信息成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:修改其他信息
     *@Date: 16:46 2018\4\12 0012
     */
    @RequestMapping("/updateOtherInformation")
    @ResponseBody
    public String updateOtherInformation(
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
        if(otherInformation==null){
            otherInformation = new OtherInformation();//不存在则新建
        }

        if (ihRsetTelphoneService.queryByTelphone(personalInformation.getTelphone())!=null && !personalInformation.getTelphone().equals(personalInformation2.getTelphone())) {
            changeInformation.setChangeinformation("办公电话");
            changeInformation.setBeforeinformation(personalInformation2.getTelphone());
            changeInformation.setAfterinformation(personalInformation.getTelphone());
            personalInformation2.setTelphoneid(ihRsetTelphoneService.queryByTelphone(personalInformation.getTelphone()).getId());
            iChangeInformationService.addOne(changeInformation);
            listBL = true;
        }

        if (personalInformation.getMobilephone()!=null && !"".equals(personalInformation.getTelphone()) && !personalInformation.getMobilephone().equals(personalInformation2.getTelphone())) {
            changeInformation.setChangeinformation("移动电话");
            changeInformation.setBeforeinformation(personalInformation2.getMobilephone());
            changeInformation.setAfterinformation(personalInformation.getMobilephone());
            personalInformation2.setMobilephone(personalInformation.getMobilephone());
            iChangeInformationService.addOne(changeInformation);
            listBL = true;
        }

        if (personalInformation.getCompanyemail()!=null && !"".equals(personalInformation.getCompanyemail()) && !personalInformation.getCompanyemail().equals(personalInformation2.getCompanyemail())) {
            changeInformation.setChangeinformation("公司邮件");
            changeInformation.setBeforeinformation(personalInformation2.getCompanyemail());
            changeInformation.setAfterinformation(personalInformation.getCompanyemail());
            otherInformation.setCompanyemail(personalInformation.getCompanyemail());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getPrivateemail()!=null && !"".equals(personalInformation.getPrivateemail()) && !personalInformation.getPrivateemail().equals(personalInformation2.getPrivateemail())) {
            changeInformation.setChangeinformation("私人邮件");
            changeInformation.setBeforeinformation(personalInformation2.getPrivateemail());
            changeInformation.setAfterinformation(personalInformation.getPrivateemail());
            otherInformation.setPrivateemail(personalInformation.getPrivateemail());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getEmergencycontract()!=null && !"".equals(personalInformation.getEmergencycontract()) && !personalInformation.getEmergencycontract().equals(personalInformation2.getEmergencycontract())) {
            changeInformation.setChangeinformation("应急联系人");
            changeInformation.setBeforeinformation(personalInformation2.getEmergencycontract());
            changeInformation.setAfterinformation(personalInformation.getEmergencycontract());
            otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getEmergencyphone()!=null && !"".equals(personalInformation.getEmergencyphone()) && !personalInformation.getEmergencyphone().equals(personalInformation2.getEmergencyphone())) {
            changeInformation.setChangeinformation("应急联系电话");
            changeInformation.setBeforeinformation(personalInformation2.getEmergencyphone());
            changeInformation.setAfterinformation(personalInformation.getEmergencyphone());
            otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getAddress()!=null && !"".equals(personalInformation.getAddress()) && !personalInformation.getAddress().equals(personalInformation2.getAddress())) {
            changeInformation.setChangeinformation("应急联系地址");
            changeInformation.setBeforeinformation(personalInformation2.getAddress());
            changeInformation.setAfterinformation(personalInformation2.getAddress());
            otherInformation.setAddress(personalInformation.getAddress());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (personalInformation.getRemark()!=null && !"".equals(personalInformation.getRemark()) && !personalInformation.getRemark().equals(personalInformation2.getRemark())) {
            changeInformation.setChangeinformation("备注");
            changeInformation.setBeforeinformation(personalInformation2.getRemark());
            changeInformation.setAfterinformation(personalInformation.getRemark());
            otherInformation.setRemark(personalInformation.getRemark());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if (ihRsetEmergencyrpService.queryByEmergencyrp(personalInformation.getEmergencyrp())!=null && !personalInformation.getEmergencyrp().equals(personalInformation2.getEmergencyrp())) {
            changeInformation.setChangeinformation("应急联系人关系");
            changeInformation.setBeforeinformation(personalInformation2.getEmergencyrp());
            changeInformation.setAfterinformation(personalInformation.getEmergencyrp());
            otherInformation.setEmergencyrpid(ihRsetEmergencyrpService.queryByEmergencyrp(personalInformation.getEmergencyrp()).getId());
            iChangeInformationService.addOne(changeInformation);
            otherBL = true;
        }

        if(otherBL){
            if(otherInformation.getId()!=null){
                iOtherInformationService.modifyOne(otherInformation);
            }else {
                Integer otherinformationid = iOtherInformationService.saveOne(otherInformation);
                personalInformation2.setOtherinformationid(otherinformationid);
                listBL = true;
            }
        }

        if(listBL){
            iPersonalInformationService.modifyOne(personalInformation2);
        }
        return "提交成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门id查询人事信息
     *@Date: 16:40 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByDepid")
    @ResponseBody
    public List<PersonalInformation> queryPersonalInformationsByDepid(
            @RequestParam("depid") Integer depid
    ){
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setDepid(depid);
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
     *@Description:查询所有员工不分页(导出用)
     *@Date: 17:23 2018\4\18 0018
     */
    @RequestMapping("/queryPersonalInformationsByNull")
    @ResponseBody
    public List<PersonalInformation> queryPersonalInformationsByNull() throws ParseException {
        List<PersonalInformation> personalInformations = iPersonalInformationService.queryAllByNull();
        for(PersonalInformation pi: personalInformations){
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
            } else {
                pi.setDepname("部门信息还未添加");
            }

            //获得岗位信息
            List<PerAndPostRs> perAndPostRs = iPerandpostrsService.queryPerAndPostRsByPerid(pi.getId());
            List<String> postnames = new ArrayList<>();
            if(perAndPostRs.size()!=0){
                for(int i=0;i<perAndPostRs.size();i++){
                    if (iPostService.queryOneByPostid(perAndPostRs.get(i).getPostid())!=null) {
                        postnames.add(iPostService.queryOneByPostid(perAndPostRs.get(i).getPostid()).getPostname());
                    }
                }
            }
            pi.setPostnames(IDcodeUtil.getArrayToString(postnames,";"));

            //获得办公电话
            if (ihRsetTelphoneService.queryById(pi.getTelphoneid())!=null) {
                pi.setTelphone(ihRsetTelphoneService.queryById(pi.getTelphoneid()).getTelphone());
            }


            //添加baseinformation的28个字段
            BaseInformation baseInformation = iBaseInformationService.queryOneById(pi.getBaseinformationid());
            if (baseInformation!=null) {
                pi.setUserphoto(baseInformation.getUserphoto());
                pi.setIdphoto1(baseInformation.getIdphoto1());
                pi.setIdphoto2(baseInformation.getIdphoto2());
                pi.setEnglishname(baseInformation.getEnglishname());
                pi.setIdcode("\t" + baseInformation.getIdcode());
                pi.setAge(IDcodeUtil.getAge(baseInformation.getBirthday()));
                if (ihRsetRaceService.queryById(baseInformation.getRaceid())!=null) {
                    pi.setRace(ihRsetRaceService.queryById(baseInformation.getRaceid()).getRace());
                }
                pi.setMarriage(baseInformation.getMarriage());
                if (ihRsetChildrenService.queryById(baseInformation.getChildrenid())!=null) {
                    pi.setChildren(ihRsetChildrenService.queryById(baseInformation.getChildrenid()).getChildren());
                }
                if (ihRsetZzmmService.queryById(baseInformation.getZzmmid())!=null) {
                    pi.setZzmm(ihRsetZzmmService.queryById(baseInformation.getZzmmid()).getZzmm());
                }
                if (ihRsetZgxlService.queryById(baseInformation.getZgxlid())!=null) {
                    pi.setZgxl(ihRsetZgxlService.queryById(baseInformation.getZgxlid()).getZgxl());
                }
                if (ihRsetByyxService.queryById(baseInformation.getByyxid())!=null) {
                    pi.setByyx(ihRsetByyxService.queryById(baseInformation.getByyxid()).getByyx());
                }
                if (ihRsetSxzyService.queryById(baseInformation.getSxzyid())!=null) {
                    pi.setSxzy(ihRsetSxzyService.queryById(baseInformation.getSxzyid()).getSxzy());
                }
                if (ihRsetPyfsService.queryById(baseInformation.getPyfsid())!=null) {
                    pi.setPyfs(ihRsetPyfsService.queryById(baseInformation.getPyfsid()).getPyfs());
                }
                if (ihRsetFlaService.queryById(baseInformation.getFirstlaid())!=null) {
                    pi.setFirstla(ihRsetFlaService.queryById(baseInformation.getFirstlaid()).getFla());
                }
                if (ihRsetFlaService.queryById(baseInformation.getElselaid())!=null) {
                    pi.setElsela(ihRsetFlaService.queryById(baseInformation.getElselaid()).getFla());
                }
                if (ihRsetPosttitleService.queryById(baseInformation.getPosttitleid())!=null) {
                    pi.setPosttitle(ihRsetPosttitleService.queryById(baseInformation.getPosttitleid()).getPosttitle());
                }
                if (ihRsetZyzstypeService.queryById(baseInformation.getZyzstypeid())!=null) {
                    pi.setZyzstype(ihRsetZyzstypeService.queryById(baseInformation.getZyzstypeid()).getZyzstype());
                }
                if (ihRsetZyzsnameService.queryById(baseInformation.getZyzsnameid())!=null) {
                    pi.setZyzsname(ihRsetZyzsnameService.queryById(baseInformation.getZyzsnameid()).getZyzsname());
                }
                pi.setFirstworkingtime(baseInformation.getFirstworkingtime());
                if (baseInformation.getFirstworkingtime()!=null && !baseInformation.getFirstworkingtime().equals("")) {
                    pi.setWorkingage(IDcodeUtil.getWorkingage(baseInformation.getFirstworkingtime()));
                }
                if (ihRsetParentcompanyService.queryById(baseInformation.getParentcompanyid())!=null) {
                    pi.setParentcompany(ihRsetParentcompanyService.queryById(baseInformation.getParentcompanyid()).getParentcompanyname());
                }
            }

            //添加manageinformation的六个字段
            ManageInformation manageInformation = iManageInformationService.queryOneById(pi.getManageinformationid());
            if (manageInformation!=null) {
                if (ihRsetRankService.queryById(manageInformation.getRankid())!=null) {
                    pi.setZj(ihRsetRankService.queryById(manageInformation.getRankid()).getRank());
                }
                pi.setEntrydate(manageInformation.getEntrydate());
                pi.setZhuanzhengdate(manageInformation.getZhuanzhengdate());
                if (ihRsetEmployeetypeService.queryById(manageInformation.getEmployeetypeid())!=null) {
                    pi.setEmployeetype(ihRsetEmployeetypeService.queryById(manageInformation.getEmployeetypeid()).getEmployeetype());
                }
            }

            //添加costinformation的18个字段
            CostInformation costInformation = iCostInformationService.queryOneById(pi.getCostinformationid());
            if (costInformation!=null) {
                if (ihRsetSalarystandardService.queryById(costInformation.getSalarystandardid())!=null) {
                    pi.setSalary(ihRsetSalarystandardService.queryById(costInformation.getSalarystandardid()).getSalarystandard());
                }
                if (ihRsetSsbService.queryById(costInformation.getSsbid())!=null) {
                    pi.setSsb(ihRsetSsbService.queryById(costInformation.getSsbid()).getSsb());
                }
                if (ihRsetSsbgscdService.queryById(costInformation.getSsbgscdid())!=null) {
                    pi.setSsbgscd(ihRsetSsbgscdService.queryById(costInformation.getSsbgscdid()).getSsbgscd());
                }
                if (ihRsetSsbgrcdService.queryById(costInformation.getSsbgrcdid())!=null) {
                    pi.setSsbgrcd(ihRsetSsbgrcdService.queryById(costInformation.getSsbgrcdid()).getSsbgrcd());
                }
                if (ihRsetGjjService.queryById(costInformation.getGjjid())!=null) {
                    pi.setGjj(ihRsetGjjService.queryById(costInformation.getGjjid()).getGjj());
                }
                if (ihRsetGjjgscdService.queryById(costInformation.getGjjgscdid())!=null) {
                    pi.setGjjgscd(ihRsetGjjgscdService.queryById(costInformation.getGjjgscdid()).getGjjgscd());
                }
                if (ihRsetGjjgrcdService.queryById(costInformation.getGjjgrcdid())!=null) {
                    pi.setGjjgrcd(ihRsetGjjgrcdService.queryById(costInformation.getGjjgrcdid()).getGjjgrcd());
                }
                if (ihRsetKhhService.queryById(costInformation.getKhhid())!=null) {
                    pi.setKhh(ihRsetKhhService.queryById(costInformation.getKhhid()).getKhh());
                }
                pi.setSalaryaccount(costInformation.getSalaryaccount());
                if (ihRsetSbjndService.queryById(costInformation.getSbjndid())!=null) {
                    pi.setSbjnd(ihRsetSbjndService.queryById(costInformation.getSbjndid()).getSbjnd());
                }
                pi.setSbcode(costInformation.getSbcode());
                pi.setGjjcode(costInformation.getGjjcode());
            }

            //添加otherinformation的六个字段
            OtherInformation otherInformation = iOtherInformationService.queryOneById(pi.getOtherinformationid());
            if (otherInformation!=null) {
                pi.setPrivateemail(otherInformation.getPrivateemail());
                pi.setCompanyemail(otherInformation.getCompanyemail());
                pi.setEmergencycontract(otherInformation.getEmergencycontract());
                if (ihRsetEmergencyrpService.queryById(otherInformation.getEmergencyrpid())!=null) {
                    pi.setEmergencyrp(ihRsetEmergencyrpService.queryById(otherInformation.getEmergencyrpid()).getEmergencyrp());
                }
                pi.setEmergencyphone(otherInformation.getEmergencyphone());
                pi.setAddress(otherInformation.getAddress());
                pi.setRemark(otherInformation.getRemark());
            }
        }
        return personalInformations;
    }

    /**
     *@Author:ShiYun;
     *@Description:数据的导入
     *@Date: 15:09 2018\5\7 0007
     */
    @RequestMapping("/importPersonalInformations")
    @ResponseBody
    public String importPersonalInformations(
        @RequestParam("file") MultipartFile multipartFile
    ){
        ReadPersonalinformationExcel readExcel = new ReadPersonalinformationExcel();
        List<PersonalInformation> personalInformationList = readExcel.getExcelInfo(multipartFile);
        /*if (personalInformationList.size()!=0) {
            PersonalInformation personalInformation = personalInformationList.get(0);
            System.out.println(personalInformation.getIsactive());
            System.out.println(personalInformation.getUsername());
            System.out.println(personalInformation.getTruename());
            System.out.println(personalInformation.getUserphoto());
            System.out.println(personalInformation.getIdphoto1());
            System.out.println(personalInformation.getIdphoto2());
            System.out.println(personalInformation.getEmployeenumber());
            System.out.println(personalInformation.getEnglishname());
            System.out.println(personalInformation.getIdcode());
            System.out.println(personalInformation.getRace());
            System.out.println(personalInformation.getMarriage());
            System.out.println(personalInformation.getChildren());
            System.out.println(personalInformation.getZzmm());
            System.out.println(personalInformation.getZgxl());
            System.out.println(personalInformation.getByyx());
            System.out.println(personalInformation.getSxzy());
            System.out.println(personalInformation.getPyfs());
            System.out.println(personalInformation.getFirstla());
            System.out.println(personalInformation.getElsela());
            System.out.println(personalInformation.getPosttitle());
            System.out.println(personalInformation.getZyzstype());
            System.out.println(personalInformation.getZyzsname());
            System.out.println(personalInformation.getFirstworkingtime());
            System.out.println(personalInformation.getParentcompany());
            System.out.println(personalInformation.getDepname());
            System.out.println(personalInformation.getPostnames());
            System.out.println(personalInformation.getZj());
            System.out.println(personalInformation.getEntrydate());
            System.out.println(personalInformation.getZhuanzhengdate());
            System.out.println(personalInformation.getEmployeetype());
            System.out.println(personalInformation.getSalary());
            System.out.println(personalInformation.getSsb());
            System.out.println(personalInformation.getSsbgscd());
            System.out.println(personalInformation.getSsbgrcd());
            System.out.println(personalInformation.getGjj());
            System.out.println(personalInformation.getGjjgscd());
            System.out.println(personalInformation.getGjjgrcd());
            System.out.println(personalInformation.getKhh());
            System.out.println(personalInformation.getSalaryaccount());
            System.out.println(personalInformation.getSbjnd());
            System.out.println(personalInformation.getSbcode());
            System.out.println(personalInformation.getGjjcode());
            System.out.println(personalInformation.getMobilephone());
            System.out.println(personalInformation.getTelphone());
            System.out.println(personalInformation.getPrivateemail());
            System.out.println(personalInformation.getCompanyemail());
            System.out.println(personalInformation.getEmergencycontract());
            System.out.println(personalInformation.getEmergencyrp());
            System.out.println(personalInformation.getEmergencyphone());
            System.out.println(personalInformation.getAddress());
            System.out.println(personalInformation.getRemark());
        }*/
        for(int i = 0;i<personalInformationList.size();i++){
            PersonalInformation personalInformation = personalInformationList.get(i);
            //1.先保存用户表
            User user = new User();
            user.setIsactive(personalInformation.getIsactive());
            user.setUsername(personalInformation.getUsername());
            user.setTruename(personalInformation.getTruename());
            Integer userid = iUserService.saveOne(user);
            personalInformation.setUserid(userid);
            //2.再查询部门ID
            Dept dept = iDeptService.queryOneDepByDepname(personalInformationList.get(i).getDepname());
            if (dept!=null) {
                personalInformation.setDepid(dept.getId());
            }
            //3.再保存用户基本信息表
            BaseInformation baseInformation = new BaseInformation();
            baseInformation.setUserphoto(personalInformation.getUserphoto());
            baseInformation.setIdphoto1(personalInformation.getIdphoto1());
            baseInformation.setIdphoto2(personalInformation.getIdphoto2());
            baseInformation.setEnglishname(personalInformation.getEnglishname());
            if (personalInformation.getIdcode()!=null && !"".equals(personalInformation.getIdcode())) {
                baseInformation.setIdcode(personalInformation.getIdcode());
                baseInformation.setBirthday(IDcodeUtil.getBirthday(personalInformation.getIdcode()));
                personalInformation.setSex(IDcodeUtil.getSex(personalInformation.getIdcode()));
                baseInformation.setConstellation(IDcodeUtil.getConstellation(personalInformation.getIdcode()));
                baseInformation.setChinesecs(IDcodeUtil.getChinesecs(personalInformation.getIdcode()));
            }
            if (ihRsetRaceService.queryByRace(personalInformation.getRace())!=null) {
                baseInformation.setRaceid(ihRsetRaceService.queryByRace(personalInformation.getRace()).getId());
            }
            baseInformation.setMarriage(personalInformation.getMarriage());
            if (ihRsetChildrenService.queryByChildren(personalInformation.getChildren())!=null) {
                baseInformation.setChildrenid(ihRsetChildrenService.queryByChildren(personalInformation.getChildren()).getId());
            }
            if (ihRsetZzmmService.queryByZzmm(personalInformation.getZzmm())!=null) {
                baseInformation.setZzmmid(ihRsetZzmmService.queryByZzmm(personalInformation.getZzmm()).getId());
            }
            if (ihRsetZgxlService.queryByZgxl(personalInformation.getZgxl())!=null) {
                baseInformation.setZgxlid(ihRsetZgxlService.queryByZgxl(personalInformation.getZgxl()).getId());
            }
            if(personalInformation.getByyx()!=null && !"".equals(personalInformation.getByyx().trim())){
                if (ihRsetByyxService.queryByByyx(personalInformation.getByyx())!=null) {
                    baseInformation.setByyxid(ihRsetByyxService.queryByByyx(personalInformation.getByyx()).getId());
                } else {
                    HRsetByyx hRsetByyx = new HRsetByyx();
                    hRsetByyx.setByyx(personalInformation.getByyx());
                    Integer byyxid = ihRsetByyxService.addOne(hRsetByyx);
                    baseInformation.setByyxid(byyxid);
                }
            }
            if(personalInformation.getSxzy()!=null && !"".equals(personalInformation.getSxzy().trim())){
                if (ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy())!=null) {
                    baseInformation.setSxzyid(ihRsetSxzyService.queryBySxzy(personalInformation.getSxzy()).getId());
                } else {
                    HRsetSxzy hRsetSxzy = new HRsetSxzy();
                    hRsetSxzy.setSxzy(personalInformation.getSxzy());
                    Integer sxzyid = ihRsetSxzyService.addOne(hRsetSxzy);
                    baseInformation.setSxzyid(sxzyid);
                }
            }
            if (ihRsetPyfsService.queryByPyfs(personalInformation.getPyfs())!=null) {
                baseInformation.setPyfsid(ihRsetPyfsService.queryByPyfs(personalInformation.getPyfs()).getId());
            }
            if (ihRsetFlaService.queryByFla(personalInformation.getFirstla())!=null) {
                baseInformation.setFirstlaid(ihRsetFlaService.queryByFla(personalInformation.getFirstla()).getId());
            }
            if (ihRsetFlaService.queryByFla(personalInformation.getElsela())!=null) {
                baseInformation.setElselaid(ihRsetFlaService.queryByFla(personalInformation.getElsela()).getId());
            }
            if (ihRsetPosttitleService.queryByPosttitle(personalInformation.getPosttitle())!=null) {
                baseInformation.setPosttitleid(ihRsetPosttitleService.queryByPosttitle(personalInformation.getPosttitle()).getId());
            }
            if (ihRsetZyzstypeService.queryByZyzstype(personalInformation.getZyzstype())!=null) {
                baseInformation.setZyzstypeid(ihRsetZyzstypeService.queryByZyzstype(personalInformation.getZyzstype()).getId());
            }
            if(personalInformation.getZyzsname()!=null && !"".equals(personalInformation.getZyzsname().trim())){
                if (ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname())!=null) {
                    baseInformation.setZyzsnameid(ihRsetZyzsnameService.queryByZyzsname(personalInformation.getZyzsname()).getId());
                } else {
                    HRsetZyzsname hRsetZyzsname = new HRsetZyzsname();
                    hRsetZyzsname.setZyzsname(personalInformation.getZyzsname());
                    Integer zyzsnameid = ihRsetZyzsnameService.addOne(hRsetZyzsname);
                    baseInformation.setZyzsnameid(zyzsnameid);
                }
            }
            baseInformation.setFirstworkingtime(personalInformation.getFirstworkingtime());
            if(personalInformation.getParentcompany()!=null && !"".equals(personalInformation.getParentcompany().trim())){
                if (ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany())!=null) {
                    baseInformation.setParentcompanyid(ihRsetParentcompanyService.queryByParentcompanyname(personalInformation.getParentcompany()).getId());
                } else {
                    HRsetParentcompany hRsetParentcompany = new HRsetParentcompany();
                    hRsetParentcompany.setParentcompanyname(personalInformation.getParentcompany());
                    Integer parentcompanyid = ihRsetParentcompanyService.addOne(hRsetParentcompany);
                    baseInformation.setParentcompanyid(parentcompanyid);
                }
            }
            Integer baseinformationid = iBaseInformationService.saveOne(baseInformation);
            personalInformation.setBaseinformationid(baseinformationid);
            //4.再保存用户管理信息表
            ManageInformation manageInformation = new ManageInformation();
            if (ihRsetRankService.queryByRank(personalInformation.getZj())!=null) {
                manageInformation.setRankid(ihRsetRankService.queryByRank(personalInformation.getZj()).getId());
            }
            if(personalInformation.getEntrydate()!=null && !"".equals(personalInformation.getEntrydate().trim())){
                manageInformation.setEntrydate(personalInformation.getEntrydate());
                if(personalInformation.getZhuanzhengdate()!=null && !"".equals(personalInformation.getZhuanzhengdate().trim())){
                    manageInformation.setZhuanzhengdate(personalInformation.getZhuanzhengdate());
                }else {
                    manageInformation.setZhuanzhengdate(IDcodeUtil.getZhuanzhengdate(personalInformation.getEntrydate()));
                }
            }
            if (ihRsetEmployeetypeService.queryByEmployeetype(personalInformation.getEmployeetype())!=null) {
                manageInformation.setEmployeetypeid(ihRsetEmployeetypeService.queryByEmployeetype(personalInformation.getEmployeetype()).getId());
            }
            Integer manageinformationid = iManageInformationService.saveOne(manageInformation);
            personalInformation.setManageinformationid(manageinformationid);
            //5.再保存用户成本信息表
            CostInformation costInformation = new CostInformation();
            if (ihRsetSalarystandardService.queryBySalarystandard(personalInformation.getSalary())!=null) {
                costInformation.setSalarystandardid(ihRsetSalarystandardService.queryBySalarystandard(personalInformation.getSalary()).getId());
            }
            if (ihRsetSsbService.queryBySsb(personalInformation.getSsb())!=null) {
                costInformation.setSsbid(ihRsetSsbService.queryBySsb(personalInformation.getSsb()).getId());
            }
            if (ihRsetSsbgscdService.queryBySsbgscd(personalInformation.getSsbgscd())!=null) {
                costInformation.setSsbgscdid(ihRsetSsbgscdService.queryBySsbgscd(personalInformation.getSsbgscd()).getId());
            }
            if (ihRsetSsbgrcdService.queryBySsbgrcd(personalInformation.getSsbgrcd())!=null) {
                costInformation.setSsbgrcdid(ihRsetSsbgrcdService.queryBySsbgrcd(personalInformation.getSsbgrcd()).getId());
            }
            if (ihRsetGjjService.queryByGjj(personalInformation.getGjj())!=null) {
                costInformation.setGjjid(ihRsetGjjService.queryByGjj(personalInformation.getGjj()).getId());
            }
            if (ihRsetGjjgscdService.queryByGjjgscd(personalInformation.getGjjgscd())!=null) {
                costInformation.setGjjgscdid(ihRsetGjjgscdService.queryByGjjgscd(personalInformation.getGjjgscd()).getId());
            }
            if (ihRsetGjjgrcdService.queryByGjjgrcd(personalInformation.getGjjgrcd())!=null) {
                costInformation.setGjjgrcdid(ihRsetGjjgrcdService.queryByGjjgrcd(personalInformation.getGjjgrcd()).getId());
            }
            if (personalInformation.getKhh()!=null && !"".equals(personalInformation.getKhh().trim())) {
                if (ihRsetKhhService.queryByKhh(personalInformation.getKhh())!=null) {
                    costInformation.setKhhid(ihRsetKhhService.queryByKhh(personalInformation.getKhh()).getId());
                }else{
                    HRsetKhh hRsetKhh = new HRsetKhh();
                    hRsetKhh.setKhh(personalInformation.getKhh());
                    Integer khhid = ihRsetKhhService.addOne(hRsetKhh);
                    costInformation.setKhhid(khhid);
                }
            }
            costInformation.setSalaryaccount(personalInformation.getSalaryaccount());
            if(personalInformation.getSbjnd()!=null && !"".equals(personalInformation.getSbjnd().trim())){
                if (ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd())!=null) {
                    costInformation.setSbjndid(ihRsetSbjndService.queryBySbjnd(personalInformation.getSbjnd()).getId());
                } else {
                    HRsetSbjnd hRsetSbjnd = new HRsetSbjnd();
                    hRsetSbjnd.setSbjnd(personalInformation.getSbjnd());
                    Integer sbjndid = ihRsetSbjndService.addOne(hRsetSbjnd);
                    costInformation.setSbjndid(sbjndid);
                }
            }
            costInformation.setSbcode(personalInformation.getSbcode());
            costInformation.setGjjcode(personalInformation.getGjjcode());
            Integer costinformationid = iCostInformationService.saveOne(costInformation);
            personalInformation.setCostinformationid(costinformationid);
            //6.再保存用户其它信息表
            OtherInformation otherInformation = new OtherInformation();
            otherInformation.setPrivateemail(personalInformation.getPrivateemail());
            otherInformation.setCompanyemail(personalInformation.getCompanyemail());
            otherInformation.setEmergencycontract(personalInformation.getEmergencycontract());
            if (ihRsetEmergencyrpService.queryByEmergencyrp(personalInformation.getEmergencyrp())!=null) {
                otherInformation.setEmergencyrpid(ihRsetEmergencyrpService.queryByEmergencyrp(personalInformation.getEmergencyrp()).getId());
            }
            otherInformation.setEmergencyphone(personalInformation.getEmergencyphone());
            otherInformation.setAddress(personalInformation.getAddress());
            otherInformation.setRemark(personalInformation.getRemark());
            Integer otherinformationid = iOtherInformationService.saveOne(otherInformation);
            personalInformation.setOtherinformationid(otherinformationid);
            //7.再保存用户概况信息
            if (ihRsetTelphoneService.queryByTelphone(personalInformation.getTelphone())!=null) {
                personalInformation.setTelphoneid(ihRsetTelphoneService.queryByTelphone(personalInformation.getTelphone()).getId());
            }
            Integer personalinformationid = iPersonalInformationService.saveOne(personalInformationList.get(i));
            //8.最后再保存岗位IDs
            if (personalinformationid!=null) {
                PerAndPostRs perAndPostRs = new PerAndPostRs();
                perAndPostRs.setPerid(personalinformationid);
                String[] split = personalInformation.getPostnames().split(";");
                for(String postname:split){
                    Integer postid = iPostService.queryOneByPostname(postname).getId();
                    perAndPostRs.setPostid(postid);
                    iPerandpostrsService.addOne(perAndPostRs);
                }
            }
        }
        projectBoardService.informationUpdate();
        return "数据导入成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:人事信息的删除
     *@Date: 13:56 2018\5\10 0010
     */
    @RequestMapping("/deleteInformationsByIds")
    @ResponseBody
    public String deleteInformationsByIds(
            @RequestParam("personalInformationIds") List<Integer> personalInformationIds
    ){

        for(int i = 0;i<personalInformationIds.size();i++){
            PersonalInformation personalInformation = iPersonalInformationService.queryOneById(personalInformationIds.get(i));
            //1.先删除用户表（本质上是修改状态）
            iUserService.removeOne(personalInformation.getUserid());
            //2.再修改部门表
            //注：如果将要删除的员工是某部门的正职、副职、秘书则需要修改该字段
            iDeptService.modifyOne(personalInformation.getUserid());
        }
        projectBoardService.informationUpdate();
        return "删除成功！";
    }

    /**
     *@Author:ShiYun;
     *@Description:所有人员的工号、人名、部门（ID、名称）、岗位（ID、名称）
     *@Date: 14:15 2018\7\9 0009
     */
    @RequestMapping("/queryGXF001")
    @ResponseBody
    public List<Object> queryGXF001() throws ParseException {
        List<Object> list = new ArrayList<>();
        List<PersonalInformation> personalInformationList = iPersonalInformationService.queryAllByNull();
        for(PersonalInformation per:personalInformationList){
            PersonalInformation onePersonalinformation = getOnePersonalinformation(per.getId());
            if(onePersonalinformation==null){
                continue;
            }
            Map<String,Object> map = new HashMap<String,Object>();
           // HashMap<String, Object> deptMap = new HashMap<>();
            ArrayList<Object> postList = new ArrayList<>();
            map.put("employeeName",onePersonalinformation.getEmployeenumber());
            map.put("id",onePersonalinformation.getTruename());
            map.put("deptId",onePersonalinformation.getDepid());
            map.put("deptName",iDeptService.queryOneDepByDepid(onePersonalinformation.getDepid()).getDepname());
            //map.put("dept",deptMap);
            List<PerAndPostRs> perAndPostRsList = iPerandpostrsService.queryPerAndPostRsByPerid(onePersonalinformation.getId());
            for(PerAndPostRs perAndPostRs:perAndPostRsList){
                HashMap<String, Object> postMap = new HashMap<>();
                postMap.put("postId",perAndPostRs.getPostid());
                postMap.put("postName",iPostService.queryOneByPostid(perAndPostRs.getPostid()).getPostname());
                postList.add(postMap);
            }
            map.put("post",postList);
            list.add(map);
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:所有部门的ID、名称
     *@Date: 14:33 2018\7\9 0009
     */
    @RequestMapping("/queryGXF002")
    @ResponseBody
    public List<Object> queryGXF002(){
        List<Object> list = new ArrayList<>();
        List<Dept> depts = iDeptService.queryAllDepts();
        for(Dept dept:depts){
            Map<String, Object> deptMap = new HashMap<>();
            deptMap.put("deptId",dept.getId());
            deptMap.put("deptName",dept.getDepname());
            list.add(deptMap);
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:所有岗位的ID、名称
     *@Date: 14:38 2018\7\9 0009
     */
    @RequestMapping("/queryGXF003")
    @ResponseBody
    public List<Object> queryGXF003(){
        ArrayList<Object> list = new ArrayList<>();
        List<Post> posts = iPostService.queryAllPosts();
        for(Post post:posts){
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("postId",post.getId());
            postMap.put("postName",post.getPostname());
            list.add(postMap);
        }
        return list;
    }

    /**
     *@Author:ShiYun;
     *@Description:根据部门ID查询部门信息
     *@Date: 11:34 2018\8\10 0010
     */
    @RequestMapping("/queryGXF004")
    @ResponseBody
    public String queryGXF004(
            @RequestParam("depid")Integer depid
    ){
        Dept dept = iDeptService.queryOneDepByDepid(depid);
        if (dept!=null) {
            if (dept.getPrincipaluserid()!=null) {
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
     *@Author:ShiYun;
     *@Description:赵宏钢的接口，根据账号查询信息
     *@Date: 18:30 2018\7\17 0017
     */
    @RequestMapping("/queryZHG001")
    @ResponseBody
    public Object queryZHG001(
            @RequestParam("username")String username
    ){
        User user = new User();
        user.setUsername(username);
        User user1 = iUserService.selectOne(user);
        if(user1==null){
            return RespUtil.successResp("205","没有查到此用户信息",null);
        }else{
            PersonalInformation personalInformation = iPersonalInformationService.queryOneByUserid(user1.getId());
            try {
               // return this.getOnePersonalinformation(personalInformation.getId());
                return RespUtil.successResp("205","没有查到此用户信息",this.getOnePersonalinformation(personalInformation.getId()));
            } catch (ParseException e) {
                return RespUtil.successResp("205","没有查到此用户信息",null);
            }
        }
    }

    /**
     *@Author:ShiYun;
     *@Description:根据日期查询工作日志（填写）
     *@Date: 9:40 2018\8\3 0003
     */
    @RequestMapping("/queryWriteGzrz")
    @ResponseBody
    public Object queryWriteGzrz(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ){
        Object o = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(year + "/" + month + "/01 00:00:00");
            o = iGzrzService.queryGzrzByTime(date);
        } catch (ParseException e) {
            System.out.println("格式转换出错！");
        }
       return RespUtil.successResp("205","相应成功！",o);
    }

    /**
     *@Author:ShiYun;
     *@Description:根据日期查询工作日志（审查）
     *@Date: 14:52 2018\8\3 0003
     */
    @RequestMapping("/queryApproveGzrz")
    @ResponseBody
    public Object queryApproveGzrz(
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month
    ){
        Object o = false;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(year + "/" + month + "/01 00:00:00");
            o = iGzrzService.queryGzrzByTime2(date);
        } catch (ParseException e) {
            System.out.println("格式转换出错！");
        }
        return RespUtil.successResp("205","相应成功！",o);
    }
}
