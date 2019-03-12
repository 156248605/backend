package com.elex.oa.service.ou_service.impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.ou.OuDepDao;
import com.elex.oa.dao.ou.OuPostDao;
import com.elex.oa.entity.ou.OuDep;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.service.ou_service.IOuDepService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:16
 * @Version 1.0
 **/
@Service
public class OuDepServiceImpl implements IOuDepService {
    @Resource
    private OuDepDao ouDepDao;
    @Resource
    private OuPostDao ouPostDao;
    @Resource
    private HrUtils hrUtils;

    private static Logger logger = LoggerFactory.getLogger(OuDepServiceImpl.class);

    @Override
    public Object addOuDep(OuDep ouDep) {
        //先判断部门编号
        if(StringUtils.isBlank(ouDep.getCode()))return RespUtil.response("500",Commons.RESP_FAIL_DEPCODEISNOTNULL,null);
        List<OuDep> allOuDepList = ouDepDao.selectAll();
        //判断部门编号
        if (addOuDepOfDepcode(ouDep, allOuDepList)) return RespUtil.response("500", "部门编号已存在", null);
        //判断其它值
        if(StringUtils.isBlank(ouDep.getName()))return RespUtil.response("500",Commons.RESP_FAIL_DEPCODEISNOTNULL,null);
        if(null==ouDep.getFunctionalTypeid())return RespUtil.response("500","职能类型不能为空",null);
        if(StringUtils.isBlank(ouDep.getParentDepcode()))return RespUtil.response("500","上级部门编号不能为空",null);
        OuDep parentOuDep = null;
        if (!allOuDepList.isEmpty()) {
            List<OuDep> parentDepListTemp = ouDepDao.select(new OuDep(ouDep.getParentDepcode(),null,Commons.DEP_ON));
            if(parentDepListTemp.isEmpty())return RespUtil.response("500","上级部门编号所在的部门不存在",null);
            parentOuDep = parentDepListTemp.get(0);
        }
        if(null==ouDep.getDepTypeid())return RespUtil.response("500","部门类型不能为空",null);
        //设置默认的排序码
        addOuDepOfDefaultOrder(ouDep);
        //判断部门层级
        if(StringUtils.isBlank(ouDep.getLevel()))return RespUtil.response("500","部门层级不能为空",null);
        try {
            Integer curLevelNumber = Integer.parseInt(ouDep.getLevel());
            if (addOuDepOfDeplevel(allOuDepList, parentOuDep, curLevelNumber)) {
                return RespUtil.response("500", "当前部门层级必须大于上级部门层级", null);
            }
        } catch (NumberFormatException e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","部门层级必须为数字类型",null);
        }
        //添加部门信息
        ouDep.setId("dep_"+System.currentTimeMillis());
        ouDep.setState(Commons.DEP_ON);
        //添加包含的岗位
        if (addOuDepOfPost(ouDep)) return RespUtil.response("500", "添加部门信息失败", null);
        //刷新上级部门的子部门信息
        if (addOuDepOfFlushSubDep(ouDep, allOuDepList, parentOuDep)) {
            return RespUtil.response("500", "更新上级部门的子部门信息时失败导致添加部门失败", null);
        }
        return RespUtil.response("200","添加部门成功",null);
    }
    //部门层级判断
    private boolean addOuDepOfDeplevel(List<OuDep> allOuDepList, OuDep parentOuDep, Integer curLevelNumber) {
        if(!allOuDepList.isEmpty() && null!=parentOuDep){
            //有上级部门则当前部门层级必须大于上级部门层级数字
            Integer parentLevelNumber = Integer.parseInt(parentOuDep.getLevel());
            if(curLevelNumber.intValue()<=parentLevelNumber.intValue()) return true;
        }
        return false;
    }
    //判断部门编号
    private boolean addOuDepOfDepcode(OuDep ouDep, List<OuDep> allOuDepList) {
        if(!allOuDepList.isEmpty()){
            List<OuDep> ouDepListTemp = ouDepDao.select(new OuDep(ouDep.getCode()));
            if(!ouDepListTemp.isEmpty()) return true;
        }else {
            ouDep.setParentDepcode("top");//如果是首次添加部门则提前补充上级部门编号
        }
        return false;
    }
    //设置默认的排序码
    private void addOuDepOfDefaultOrder(OuDep ouDep) {
        if(StringUtils.isBlank(ouDep.getOrder())){
            //1.总公司
            if("ELEXTEC".equals(ouDep.getCode()))ouDep.setOrder("0");
            if(!"ELEXTEC".equals(ouDep.getCode()) && ouDep.getCode().indexOf("ELEX")!=-1)ouDep.setOrder(ouDep.getCode().substring(ouDep.getCode().length()-2,ouDep.getCode().length()));//截取后两位
            if(ouDep.getCode().indexOf("ELEX")==-1)ouDep.setOrder(ouDep.getCode());
        }
    }
    //添加包含的岗位
    private boolean addOuDepOfPost(OuDep ouDep) {
        List<String> postcodeList = ouDep.getPostcodeList();
        StringBuilder posts = new StringBuilder();
        if (null!=postcodeList && !postcodeList.isEmpty()) {
            for (String postcode:postcodeList
                 ) {
                posts.append(postcode).append(";");
            }
            posts.deleteCharAt(posts.length()-1);
        }
        ouDep.setPosts(posts.toString());
        try {
            ouDepDao.insert(ouDep);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return true;
        }
        return false;
    }
    //刷新上级部门的子部门信息
    private boolean addOuDepOfFlushSubDep(OuDep ouDep, List<OuDep> allOuDepList, OuDep parentOuDep) {
        if(null==ouDep||null==allOuDepList||null==parentOuDep){
            return false;
        }else if(!allOuDepList.isEmpty()){
            String oldParentSubdepartments = parentOuDep.getSubdepartments();
            String newParentSubdepartments = "";
            if(StringUtils.isBlank(oldParentSubdepartments)){
                newParentSubdepartments = ouDep.getCode();
            }else {
                newParentSubdepartments = oldParentSubdepartments+";"+ouDep.getCode();
            }
            parentOuDep.setSubdepartments(newParentSubdepartments);
            try {
                ouDepDao.updateByPrimaryKeySelective(parentOuDep);
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));//需要回滚
                ouDepDao.deleteByPrimaryKey(ouDep.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public Object listDepts() {
        OuDep topOuDep = ouDepDao.selectOne(new OuDep(null, "top",Commons.DEP_ON));
        if(null==topOuDep)return RespUtil.response("500","没有顶级节点",null);
        Map<String, Object> treeData = new HashMap<>();
        treeData.put("title",topOuDep.getName());
        treeData.put("code",topOuDep.getCode());
        treeData.put("expand",true);
        treeData.put(Commons.DEP_ORDER,topOuDep.getOrder());
        getTreeData(treeData);
        return RespUtil.response("200","获取树结构数据成功",treeData);
    }

    @Override
    public Object queryOneDepByDepcode(String code) {
        if(StringUtils.isBlank(code))return RespUtil.response("500","部门编号不能为空",null);
        OuDep ouDep = ouDepDao.selectOne(new OuDep(code,null,Commons.DEP_ON));
        if(null==ouDep)return RespUtil.response("500","部门编号所在部门不存在或已经删除",code);
        //获得子部门名称
        queryOneDepByDepcodeOfSubDep(ouDep);
        //获得岗位详情[岗位名称:姓名1,姓名2]和岗位编号
        queryOneDepByDepcodeOfPost(ouDep);
        return RespUtil.response("200","查询成功",ouDep);
    }
    //获得岗位详情[岗位名称:姓名1,姓名2]和岗位编号
    private void queryOneDepByDepcodeOfPost(OuDep ouDep) {
        if (null!=ouDep.getPosts()) {
            String[] postcodeAndEmployeenumberArray = ouDep.getPosts().split(";");
            if(postcodeAndEmployeenumberArray.length>0 && postcodeAndEmployeenumberArray[0].length()>0){
                List<String> postListDetail = new ArrayList<>();
                List<String> postcodeList = new ArrayList<>();
                for (String postcodeAndEmployeenumber:postcodeAndEmployeenumberArray
                     ) {
                    //获得岗位编号
                    String[] arrayTemp = postcodeAndEmployeenumber.split(":");
                    String postcode = arrayTemp[0];
                    postcodeList.add(postcode);
                    //获得岗位详情
                    String postName = ouPostDao.selectOne(new OuPost(postcode)).getPostname();
                    String postNameAndTruename = postName;
                    postNameAndTruename = queryOneDepByDepcodeOfPostOfPostdetail(arrayTemp, postNameAndTruename);
                    postListDetail.add(postNameAndTruename);
                }
                ouDep.setPostListDetail(postListDetail);
                ouDep.setPostcodeList(postcodeList);
            }
        }
    }
    //获得岗位详情
    private String queryOneDepByDepcodeOfPostOfPostdetail(String[] arrayTemp, String postNameAndTruename) {
        if(arrayTemp.length==2 && StringUtils.isNotBlank(arrayTemp[1])){
            StringBuilder sb = new StringBuilder(postNameAndTruename).append(":");
            String[] employeenumberArray = arrayTemp[1].split(",");
            for (String employeenumber:employeenumberArray
                 ) {
                sb.append(hrUtils.getTruenameByEmployeenumber(employeenumber)).append(",");
            }
            sb.deleteCharAt(sb.length()-1);
            postNameAndTruename = sb.toString();
        }
        return postNameAndTruename;
    }
    //获得子部门名称
    private void queryOneDepByDepcodeOfSubDep(OuDep ouDep) {
        if (null!=ouDep.getSubdepartments()) {
            String[] depcodeList = ouDep.getSubdepartments().split(";");
            if(depcodeList.length==0 || depcodeList[0].length()==0){
                ouDep.setSubdepartmentnames("");
            }else {
                StringBuilder subdepartmentnames = new StringBuilder();
                for (String depcode : depcodeList
                ) {
                    OuDep ouDepTemp = ouDepDao.selectOne(new OuDep(depcode, null, Commons.DEP_ON));
                    if (null != ouDepTemp) subdepartmentnames.append(ouDepTemp.getName()).append(":");
                }
                subdepartmentnames.deleteCharAt(subdepartmentnames.length()-1);
                ouDep.setSubdepartmentnames(subdepartmentnames.toString());
            }
        }
    }

    @Override
    public Object queryAllDepinfoButSelf(String depcode) {
        List<OuDep> respDepList = null;
        try {
            //根据部门编号获得所有的子部门及本部门
            OuDep ouDep = ouDepDao.selectOne(new OuDep(depcode, null, Commons.DEP_ON));
            List<OuDep> removeDepList = new ArrayList<>();
            removeDepList.add(ouDep);
            removeDepList = getSubdepList(depcode,removeDepList);
            StringBuilder removeDepcode = new StringBuilder();
            for (OuDep removeSignal:removeDepList
                 ) {
                removeDepcode.append(removeSignal.getCode()).append(";");
            }
            //获得所有部门
            List<OuDep> allDepList = ouDepDao.select(new OuDep(null, null, Commons.DEP_ON));
            //获得可能的上级部门
            respDepList = new ArrayList<>();
            for (OuDep allSignal:allDepList
                 ) {
                if(removeDepcode.indexOf(allSignal.getCode())==-1)respDepList.add(allSignal);
            }
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","查询失败！",e.getStackTrace());
        }
        return RespUtil.response("200","查询成功！",respDepList);
    }

    @Override
    public Object queryAllDepByDepON() {
        List<OuDep> ouDepList = null;
        try {
            ouDepList = ouDepDao.select(new OuDep(null, null, Commons.DEP_ON));
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","查询失败",e.getStackTrace());
        }
        return RespUtil.response("200","查询成功",ouDepList);
    }

    @Override
    public Object modifyOuDep(OuDep ouDep, String username) {
        //处理脏数据
        modifyOuDepOfDirtydata(ouDep);
        OuDep oldOuDep = ouDepDao.selectByPrimaryKey(ouDep.getId());
        //判断岗位编号
        if(StringUtils.isBlank(ouDep.getCode()))return RespUtil.response("500","部门编号不能为空",null);
        if (modifyOuDepOfPostcode(ouDep, oldOuDep)) return RespUtil.response("500", "部门编号已经存在", ouDep.getCode());
        //公司判断
        if(StringUtils.isBlank(ouDep.getCompanyname()))return RespUtil.response("500","公司名称不能为空",ouDep.getCode());
        //部门类型
        if(null==ouDep.getDepTypeid())return RespUtil.response("500","部门类型不能为空",ouDep.getCode());
        //判断部门名称
        if(StringUtils.isBlank(ouDep.getName()))return RespUtil.response("500","部门名称不能为空",ouDep.getCode());
        //判断上级部门
        if(StringUtils.isBlank(ouDep.getParentDepcode()))return RespUtil.response("500","上级部门编号不能为空",ouDep.getCode());
        //判断包含岗位
        String postnameListOfString = "";
        if(!ouDep.getPostcodeList().isEmpty()){
            //将返回岗位编号换成岗位名称串成字符串
            postnameListOfString = modifyOuDepOfPostcodestr(ouDep, postnameListOfString);
        }
        //判断是否有外部引用的岗位
        if (!ouDep.getPostListDetail().isEmpty()) {
            for (String postNameAndTruenmaes:ouDep.getPostListDetail()
                 ) {
                if (modifyOuDepOfValidateoutquote(postnameListOfString, postNameAndTruenmaes)) {
                    return RespUtil.response("500", postNameAndTruenmaes.split(":")[0] + "有外部引用的岗位不能移除", postNameAndTruenmaes);
                }
            }
        }
        //获得包含岗位
        modifyOuDepOfPosts(ouDep);
        try {
            ouDepDao.updateByPrimaryKeySelective(ouDep);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","修改失败",e.getStackTrace());
        }
        return RespUtil.response("200","修改成功",ouDep);
    }
    //判断岗位编号是否已经存在
    private boolean modifyOuDepOfPostcode(OuDep ouDep, OuDep oldOuDep) {
        if(!oldOuDep.getCode().equals(ouDep.getCode())){
            OuDep ouDepTemp = ouDepDao.selectOne(new OuDep(ouDep.getCode()));
            if(null!=ouDepTemp) return true;
        }
        return false;
    }
    //判断是否有外部引用的岗位
    private boolean modifyOuDepOfValidateoutquote(String postnameListOfString, String postNameAndTruenmaes) {
        return postNameAndTruenmaes.indexOf(':')!=-1 && postnameListOfString.indexOf(postNameAndTruenmaes.split(":")[0])==-1;
    }
    //处理脏数据
    private void modifyOuDepOfDirtydata(OuDep ouDep) {
        if(null!=ouDep.getPostListDetail() && ouDep.getPostListDetail().size()==1 && "null".equals(ouDep.getPostListDetail().get(0))){
            ouDep.setPostListDetail(null);
        }
        if("null".equals(ouDep.getPosts())){
            ouDep.setPosts(null);
        }
    }
    //获得包含岗位
    private void modifyOuDepOfPosts(OuDep ouDep) {
        if(null==ouDep.getPosts() || StringUtils.isBlank(ouDep.getPosts())){
            StringBuilder posts = new StringBuilder();
            for (String postcode:ouDep.getPostcodeList()
                 ) {
                posts.append(postcode).append(";");
            }
            ouDep.setPosts(posts.toString());
        }else {
            String[] postcodeAndEmployeenumbersArray = ouDep.getPosts().split(";");
            List<String> postcodeList = ouDep.getPostcodeList();
            StringBuilder posts = new StringBuilder();
            for (String postcodeAndEmployeenumbers:postcodeAndEmployeenumbersArray
                 ) {
                if(postcodeAndEmployeenumbers.indexOf(':')!=-1){
                    posts.append(postcodeAndEmployeenumbers).append(";");
                    postcodeList.remove(postcodeAndEmployeenumbers.split(":")[0]);
                }
            }
            for (String postcode:postcodeList
                 ) {
                posts.append(postcode).append(";");
            }
            posts.deleteCharAt(posts.length()-1);
            ouDep.setPosts(posts.toString());
        }
    }
    //将返回岗位编号换成岗位名称串成字符串
    private String modifyOuDepOfPostcodestr(OuDep ouDep, String postnameListOfString) {
        if (null!=ouDep.getPostcodeList() && !ouDep.getPostcodeList().isEmpty()) {
            List<String> ouPostnameList = new ArrayList<>();
            for (String ouPostCode:ouDep.getPostcodeList()
            ) {
                OuPost ouPostTemp = ouPostDao.selectOne(new OuPost(ouPostCode, Commons.POST_ON));
                ouPostnameList.add(ouPostTemp.getPostname());
            }
            postnameListOfString = hrUtils.getListStringFromString(ouPostnameList, "@");
        }
        return postnameListOfString;
    }

    @Override
    public Object removeDeptsByDepcode(String depcode) {
        if(StringUtils.isBlank(depcode))return RespUtil.response("500","选择的部门编号不能为空",depcode);
        OuDep parentOuDep = ouDepDao.selectOne(new OuDep(depcode, null, Commons.DEP_ON));
        if(null==parentOuDep)return RespUtil.response("500","选择的部门编号不存在",depcode);
        //获得子部门
        List<OuDep> depList = new ArrayList<>();
        depList.add(parentOuDep);
        depList = getSubdepList(depcode,depList);
        //判断是否有外部引用
        for (OuDep ouDep:depList
             ) {
            String posts = ouDep.getPosts();
            if(posts.indexOf(':')!=-1){
                return RespUtil.response("500","要删除的部门有外部引用",ouDep);
            }
        }
        //遍历删除部门
        for (OuDep ouDep:depList
             ) {
            try {
                ouDep.setState(Commons.DEP_OFF);
                ouDepDao.updateByPrimaryKeySelective(ouDep);
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));
                return RespUtil.response("500","删除失败",e.getStackTrace());
            }
        }
        //删除完之后上级部门的子部门信息需要改变
        String subdepartments = parentOuDep.getSubdepartments();
        String[] subs = subdepartments.split(";");
        StringBuilder subStrs = new StringBuilder();
        for (String str:subs
             ) {
            if(!str.equals(depcode)){
                subStrs.append(str).append(";");
            }
        }
        subdepartments = subStrs.deleteCharAt(subStrs.length()-1).toString();
        parentOuDep.setSubdepartments(subdepartments);
        try {
            ouDepDao.updateByPrimaryKeySelective(parentOuDep);
        } catch (Exception e) {
            logger.info(String.valueOf(e.getCause()));
            return RespUtil.response("500","更新上级部门的子部门信息失败",e.getStackTrace());
        }
        return RespUtil.response("200","删除成功",depcode);
    }

    @Override
    public Object querySortdataByParentDepcode(String parentDepcode) {
        if(StringUtils.isBlank(parentDepcode))return RespUtil.response("500","上级部门不能为空",null);
        OuDep parentOuDep = ouDepDao.selectOne(new OuDep(parentDepcode, null, Commons.DEP_ON));
        if(null==parentOuDep)return RespUtil.response("500","上级部门查不到",parentDepcode);
        List<OuDep> subDepList = ouDepDao.select(new OuDep(null, parentDepcode, Commons.DEP_ON));
        if(null==subDepList || subDepList.isEmpty())return RespUtil.response("500","没有需要排序的子部门",parentDepcode);
        if(subDepList.size()==1)return RespUtil.response("500","同级部门只有一个，不需要排序",parentDepcode);
        List<Map<String,Object>> respList = new ArrayList<>();
        for (OuDep ouDep:subDepList
             ) {
            Map<String,Object> depObj = new HashMap<>();
            depObj.put("depName",ouDep.getName());
            depObj.put("depCode",ouDep.getCode());
            depObj.put("depOrder",ouDep.getOrder());
            respList.add(depObj);
        }
        return RespUtil.response("200","请求成功",respList);
    }

    @Override
    public Object submitSortdata(List<Map> sortData) {
        if(null==sortData || sortData.isEmpty())return RespUtil.response("500","没有需要更新的排序信息",null);
        if(sortData.size()==1)return RespUtil.response("500","需要更新的排序信息只有一条",sortData);
        for (Map map:sortData
             ) {
            Example example = new Example(OuDep.class);
            example.createCriteria().andEqualTo("code", map.get("depCode"));
            example.createCriteria().andEqualTo("state", Commons.DEP_ON);
            OuDep ouDep = new OuDep();
            ouDep.setOrder((String) map.get("depOrder"));
            try {
                ouDepDao.updateByExampleSelective(ouDep,example);
            } catch (Exception e) {
                logger.info(String.valueOf(e.getCause()));
                return RespUtil.response("500","排序码更新失败",e.getStackTrace());
            }
        }
        return RespUtil.response("200","提交成功",null);
    }


    //获得树结构数据
    private Map<String,Object> getTreeData(Map<String,Object> treeData){
        String parentDepcode = (String) treeData.get("code");
        List<OuDep> ouDepList = ouDepDao.select(new OuDep(null, parentDepcode, Commons.DEP_ON));
        if(null==ouDepList || ouDepList.isEmpty())return treeData;
        List<Map<String,Object>> children = new ArrayList<>();
        for (OuDep ouDep:ouDepList
             ) {
            Map<String, Object> mapTemp = new HashMap<>();
            mapTemp.put("title",ouDep.getName());
            mapTemp.put("code",ouDep.getCode());
            mapTemp.put("expand",true);
            mapTemp.put("order",ouDep.getOrder());
            mapTemp = getTreeData(mapTemp);
            children.add(mapTemp);
        }
        //按照排序码同级排序
        if (children.size()>1) {
            children.sort((o1,o2)->{
                Integer order1 = Integer.parseInt((String) o1.get(Commons.DEP_ORDER));
                Integer order2 = Integer.parseInt((String) o2.get(Commons.DEP_ORDER));
                return order1.compareTo(order2);
            });
        }
        treeData.put("children",children);
        return treeData;
    }

    //根据部门编号获得所有的子部门
    private List<OuDep> getSubdepList(String depcode,List<OuDep> depList){
        List<OuDep> children = ouDepDao.select(new OuDep(null, depcode, Commons.DEP_ON));
        if(null==children || children.isEmpty())return depList;
        for (OuDep ouDep:children
             ) {
            depList.add(ouDep);
            depList = getSubdepList(ouDep.getCode(),depList);
        }
        return depList;
    }
}
