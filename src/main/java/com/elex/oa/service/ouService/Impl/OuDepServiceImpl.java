package com.elex.oa.service.ouService.Impl;

import com.elex.oa.common.hr.Commons;
import com.elex.oa.dao.ou.OuDepDao;
import com.elex.oa.dao.ou.OuPostDao;
import com.elex.oa.entity.ou.OuDep;
import com.elex.oa.entity.ou.OuPost;
import com.elex.oa.service.ouService.IOuDepService;
import com.elex.oa.util.hr_util.HrUtils;
import com.elex.oa.util.resp.Resp;
import com.elex.oa.util.resp.RespUtil;
import org.apache.commons.lang.StringUtils;
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

    @Override
    public Object addOuDep(OuDep ouDep) {
        //先判断部门编号
        if(StringUtils.isBlank(ouDep.getCode()))return RespUtil.response("500","部门编号不能为空",null);
        List<OuDep> allOuDepList = ouDepDao.selectAll();
        if(allOuDepList.size()>0){
            List<OuDep> ouDepListTemp = ouDepDao.select(new OuDep(ouDep.getCode()));
            if(ouDepListTemp.size()!=0)return RespUtil.response("500","部门编号已存在",null);
        }else {
            ouDep.setParentDepcode("top");//如果是首次添加部门则提前补充上级部门编号
        }
        //判断其它值
        if(StringUtils.isBlank(ouDep.getName()))return RespUtil.response("500","部门名称不能为空",null);
        if(null==ouDep.getFunctionalTypeid())return RespUtil.response("500","职能类型不能为空",null);
        if(StringUtils.isBlank(ouDep.getParentDepcode()))return RespUtil.response("500","上级部门编号不能为空",null);
        OuDep parentOuDep = null;
        if (allOuDepList.size()>0) {
            List<OuDep> parentDepListTemp = ouDepDao.select(new OuDep(ouDep.getParentDepcode(),null,Commons.DEP_ON));
            if(parentDepListTemp.size()==0)return RespUtil.response("500","上级部门编号所在的部门不存在",null);
            parentOuDep = parentDepListTemp.get(0);
        }
        if(null==ouDep.getDepTypeid())return RespUtil.response("500","部门类型不能为空",null);
        if(StringUtils.isBlank(ouDep.getOrder())){
            //如果排序号不存在则1.用部门编号代替;2.随机数;
            //1.总公司
            if("ELEXTEC".equals(ouDep.getCode()))ouDep.setOrder("0");
            if(!"ELEXTEC".equals(ouDep.getCode()) && ouDep.getCode().indexOf("ELEX")!=-1)ouDep.setOrder(ouDep.getCode().substring(ouDep.getCode().length()-2,ouDep.getCode().length()));//截取后两位
            if(ouDep.getCode().indexOf("ELEX")==-1)ouDep.setOrder(ouDep.getCode());
        }
        //判断部门层级
        if(StringUtils.isBlank(ouDep.getLevel()))return RespUtil.response("500","部门层级不能为空",null);
        try {
            Integer curLevelNumber = Integer.parseInt(ouDep.getLevel());
            if(allOuDepList.size()>0){
                //有上级部门则当前部门层级必须大于上级部门层级数字
                Integer parentLevelNumber = Integer.parseInt(parentOuDep.getLevel());
                if(curLevelNumber.intValue()<=parentLevelNumber.intValue())return RespUtil.response("500","当前部门层级必须大于上级部门层级",null);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return RespUtil.response("500","部门层级必须为数字类型",null);
        }
        //添加部门信息
        ouDep.setId("dep_"+System.currentTimeMillis());
        ouDep.setState(Commons.DEP_ON);
        //添加包含的岗位
        List<String> postcodeList = ouDep.getPostcodeList();
        String posts = "";
        if (null!=postcodeList && postcodeList.size()>0) {
            for (String postcode:postcodeList
                 ) {
                posts += postcode+";";
            }
            posts = posts.substring(0,posts.length()-1);
        }
        ouDep.setPosts(posts);
        try {
            ouDepDao.insert(ouDep);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.response("500","添加部门信息失败",null);
        }
        //刷新上级部门的子部门信息
        if(allOuDepList.size()>0){
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
                e.printStackTrace();//需要回滚
                ouDepDao.deleteByPrimaryKey(ouDep.getId());
                return RespUtil.response("500","更新上级部门的子部门信息时失败导致添加部门失败",null);
            }
        }
        return RespUtil.response("200","添加部门成功",null);
    }

    @Override
    public Object listDepts() {
        OuDep topOuDep = ouDepDao.selectOne(new OuDep(null, "top",Commons.DEP_ON));
        if(null==topOuDep)return RespUtil.response("500","没有顶级节点",null);
        Map<String, Object> treeData = new HashMap<>();
        treeData.put("title",topOuDep.getName());
        treeData.put("code",topOuDep.getCode());
        treeData.put("expand",true);
        treeData.put("order",topOuDep.getOrder());
        treeData = getTreeData(treeData);
        return RespUtil.response("200","获取树结构数据成功",treeData);
    }

    @Override
    public Object queryOneDepByDepcode(String code) {
        if(StringUtils.isBlank(code))return RespUtil.response("500","部门编号不能为空",null);
        OuDep ouDep = ouDepDao.selectOne(new OuDep(code,null,Commons.DEP_ON));
        if(null==ouDep)return RespUtil.response("500","部门编号所在部门不存在或已经删除",code);
        //获得子部门名称
        if (null!=ouDep.getSubdepartments()) {
            String[] depcodeList = ouDep.getSubdepartments().split(";");
            if(depcodeList.length==0 || depcodeList[0].length()==0){
                ouDep.setSubdepartmentnames("");
            }else {
                String subdepartmentnames = "";
                for (String depcode : depcodeList
                ) {
                    OuDep ouDepTemp = ouDepDao.selectOne(new OuDep(depcode, null, Commons.DEP_ON));
                    if (null != ouDepTemp) subdepartmentnames += ouDepTemp.getName() + ";";
                }
                subdepartmentnames = subdepartmentnames.substring(0, subdepartmentnames.length() - 1);
                ouDep.setSubdepartmentnames(subdepartmentnames);
            }
        }
        //获得岗位详情[岗位名称:姓名1,姓名2]和岗位编号
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
                    if(arrayTemp.length==2 && StringUtils.isNotBlank(arrayTemp[1])){
                        postNameAndTruename +=":";
                        String[] employeenumberArray = arrayTemp[1].split(",");
                        for (String employeenumber:employeenumberArray
                             ) {
                            postNameAndTruename +=hrUtils.getTruenameByEmployeenumber(employeenumber)+",";
                        }
                        postNameAndTruename = postNameAndTruename.substring(0,postNameAndTruename.length()-1);
                    }
                    postListDetail.add(postNameAndTruename);
                }
                ouDep.setPostListDetail(postListDetail);
                ouDep.setPostcodeList(postcodeList);
            }
        }
        return RespUtil.response("200","查询成功",ouDep);
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
            String removeDepcode = "";
            for (OuDep removeSignal:removeDepList
                 ) {
                removeDepcode += removeSignal.getCode()+";";
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
            e.printStackTrace();
            return RespUtil.response("500","查询失败！",e.getStackTrace());
        }
        return RespUtil.response("200","查询成功！",respDepList);
    }

    @Override
    public Object queryAllDepByDep_ON() {
        List<OuDep> ouDepList = null;
        try {
            ouDepList = ouDepDao.select(new OuDep(null, null, Commons.DEP_ON));
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.response("500","查询失败",e.getStackTrace());
        }
        return RespUtil.response("200","查询成功",ouDepList);
    }

    @Override
    public Object modifyOuDep(OuDep ouDep, String username) {
        //处理脏数据
        if(null!=ouDep.getPostListDetail() && ouDep.getPostListDetail().size()==1 && "null".equals(ouDep.getPostListDetail().get(0))){
            ouDep.setPostListDetail(null);
        }
        if("null".equals(ouDep.getPosts())){
            ouDep.setPosts(null);
        }
        OuDep oldOuDep = ouDepDao.selectByPrimaryKey(ouDep.getId());
        //判断岗位编号
        if(StringUtils.isBlank(ouDep.getCode()))return RespUtil.response("500","部门编号不能为空",null);
        if(!oldOuDep.getCode().equals(ouDep.getCode())){
            OuDep ouDepTemp = ouDepDao.selectOne(new OuDep(ouDep.getCode()));
            if(null!=ouDepTemp)return RespUtil.response("500","部门编号已经存在",ouDep.getCode());
        }
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

        if(null==ouDep.getPostListDetail() || ouDep.getPostListDetail().size()==0){
            //几乎没有影响
        }else {
            //将返回岗位编号换成岗位名称串成字符串
            if (null!=ouDep.getPostcodeList() && ouDep.getPostcodeList().size()>0) {
                List<String> ouPostnameList = new ArrayList<>();
                for (String ouPostCode:ouDep.getPostcodeList()
                ) {
                    OuPost ouPostTemp = ouPostDao.selectOne(new OuPost(ouPostCode, Commons.POST_ON));
                    ouPostnameList.add(ouPostTemp.getPostname());
                }
                postnameListOfString = hrUtils.getListStringFromString(ouPostnameList, "@");
            }
            //判断是否有外部引用的岗位
            for (String postNameAndTruenmaes:ouDep.getPostListDetail()
                 ) {
                if(postNameAndTruenmaes.indexOf(":")!=1 && postnameListOfString.indexOf(postNameAndTruenmaes.split(":")[0])==-1){
                    return RespUtil.response("500",postNameAndTruenmaes.split(":")[0]+"有外部引用的岗位不能移除",postNameAndTruenmaes);
                }
            }
        }

        if(null==ouDep.getPosts() || StringUtils.isBlank(ouDep.getPosts())){
            String posts = "";
            for (String postcode:ouDep.getPostcodeList()
                 ) {
                posts += postcode+";";
            }
            ouDep.setPosts(posts);
        }else {
            String[] postcodeAndEmployeenumbersArray = ouDep.getPosts().split(";");
            List<String> postcodeList = ouDep.getPostcodeList();
            String posts = "";
            for (String postcodeAndEmployeenumbers:postcodeAndEmployeenumbersArray
                 ) {
                if(postcodeAndEmployeenumbers.indexOf(":")!=-1){
                    posts += postcodeAndEmployeenumbers+";";
                    postcodeList.remove(postcodeAndEmployeenumbers.split(":")[0]);
                }
            }
            for (String postcode:postcodeList
                 ) {
                posts += postcode+";";
            }
            posts = posts.substring(0,posts.length()-1);
            ouDep.setPosts(posts);
        }
        try {
            ouDepDao.updateByPrimaryKeySelective(ouDep);
        } catch (Exception e) {
            e.printStackTrace();
            return RespUtil.response("500","修改失败",e.getStackTrace());
        }
        return RespUtil.response("200","修改成功",ouDep);
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
            if(posts.indexOf(":")!=-1){
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
                e.printStackTrace();
                return RespUtil.response("500","删除失败",e.getStackTrace());
            }
        }
        //删除完之后上级部门的子部门信息需要改变
        String subdepartments = parentOuDep.getSubdepartments();
        String[] subs = subdepartments.split(";");
        String subStrs = "";
        for (String str:subs
             ) {
            if(!str.equals(depcode)){
                subStrs += str+";";
            }
        }
        subdepartments = subStrs.substring(0,subStrs.length()-1);
        parentOuDep.setSubdepartments(subdepartments);
        try {
            ouDepDao.updateByPrimaryKeySelective(parentOuDep);
        } catch (Exception e) {
            e.printStackTrace();
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
        if(null==subDepList || subDepList.size()==0)return RespUtil.response("500","没有需要排序的子部门",parentDepcode);
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
        if(null==sortData || sortData.size()==0)return RespUtil.response("500","没有需要更新的排序信息",null);
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
                e.printStackTrace();
                return RespUtil.response("500","排序码更新失败",e.getStackTrace());
            }
        }
        return RespUtil.response("200","提交成功",null);
    }


    //获得树结构数据
    private Map<String,Object> getTreeData(Map<String,Object> treeData){
        String parentDepcode = (String) treeData.get("code");
        List<OuDep> ouDepList = ouDepDao.select(new OuDep(null, parentDepcode, Commons.DEP_ON));
        if(null==ouDepList || ouDepList.size()==0)return treeData;
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
            children.sort(new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    Integer order1 = Integer.parseInt((String) o1.get("order"));
                    Integer order2 = Integer.parseInt((String) o2.get("order"));
                    return order1.compareTo(order2);
                }
            });
        }
        treeData.put("children",children);
        return treeData;
    }

    //根据部门编号获得所有的子部门
    private List<OuDep> getSubdepList(String depcode,List<OuDep> depList){
        List<OuDep> children = ouDepDao.select(new OuDep(null, depcode, Commons.DEP_ON));
        if(null==children || children.size()==0)return depList;
        for (OuDep ouDep:children
             ) {
            depList.add(ouDep);
            depList = getSubdepList(ouDep.getCode(),depList);
        }
        return depList;
    }
}