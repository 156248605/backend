package com.elex.oa.service.hr_service;

import com.elex.oa.entity.hr_entity.PersonalInformation;
import com.elex.oa.entity.project.Staff;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ShiYun;
 * @Description:人事信息
 * @Date:Created in  9:58 2018\4\8 0008
 * @Modify By:
 */
public interface IPersonalInformationService{
    List<Staff> queryUseridTruenameDepidDepnamePerid();
    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息
     *@Date: 10:03 2018\4\8 0008
     */
    PageInfo<PersonalInformation> queryPIs(Map<String,Object> paramMap) throws ParseException;
    Map<String,List<String>> getParamsForFirstpage();

    /**
     *@Author:ShiYun;
     *@Description:根据ID查询人事信息
     *@Date: 18:06 2018\4\8 0008
     */
    PersonalInformation queryOneById(Integer id);
    PersonalInformation queryOneById2(Integer id);

    /**
     *@Author:ShiYun;
     *@Description:根据员工ID查询人事信息
     *@Date: 10:59 2018\4\9 0009
     */
    PersonalInformation queryOneByUserid(Integer userid);
    ArrayList<HashMap> queryByUseridForIOS(Integer userid);
    PersonalInformation queryPersonalInformationByTruename(String truename);

    /**
     *@Author:ShiYun;
     *@Description:添加人事信息的主要信息并返回主键
     *@Date: 18:47 2018\4\10 0010
     */
    Integer saveOne(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:修改人事信息
     *@Date: 16:30 2018\4\11 0011
     */
    void modifyOne(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:根据条件查询人事信息（不分页）
     *@Date: 16:28 2018\4\13 0013
     */
    List<PersonalInformation> queryPIs(PersonalInformation personalInformation);

    /**
     *@Author:ShiYun;
     *@Description:查询所有人事信息（不分页）
     *@Date: 17:27 2018\4\18 0018
     */
    List<PersonalInformation> queryAllByNull();

    /**
     *@Author:ShiYun;
     *@Description:根据员工号查询员工
     *@Date: 10:01 2018\8\9 0009
     */
    PersonalInformation queryByEmployeenumber(String employeenumber);

    /**
     *@Author:ShiYun;
     *@Description:根据ID删除人事主体信息
     *@Date: 16:10 2018\8\20 0020
     */
    void removeOne(Integer perid);

    void removeAll();

    PersonalInformation queryPersonalInformationById(Integer personalInformationId);

    Map<String,Object> addOtherInformation(PersonalInformation personalInformation);

    Map<String,Object> updateManageInformation(PersonalInformation personalInformation,String transactorusername);

    Map<String,Object> updateBaseInformation(PersonalInformation personalInformation,String transactorusername);

    Boolean updateCostInformation(PersonalInformation personalInformation,String transactorusername);

    Map<String,Object> updateOtherInformation(PersonalInformation personalInformation,String transactorusername);

    Map<String,String> importPersonalInformations(MultipartFile multipartFile);

    Map<String, Object> addManageInformation(PersonalInformation personalInformation);

    Map<String,Object> queryIdcodeInfoByAnalyzeIdcode(String idcode);
}
