package com.elex.oa.service.impl;

import com.elex.oa.dao.ISysTreeDao;
import com.elex.oa.entity.SysTree;
import com.elex.oa.service.ISysTreeService;
import com.elex.oa.util.IdUtil;
import com.elex.oa.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/15 11:31
*/
@Service
public class SysTreeServiceImpl extends BaseServiceImpl<SysTree> implements ISysTreeService {

    private ISysTreeDao sysTreeDao;
    @Autowired
    public SysTreeServiceImpl(ISysTreeDao sysTreeDao) {
        this.sysTreeDao = sysTreeDao;
    }
    public List<SysTree> selectByCatKey(Map<String,String> map){
      return   this.sysTreeDao.selectByCatKey(map);
    }

    /**
     * 保存表单分类
     * @param formCategoryName  表单分类名称
     * @param formCategoryLabelKey  表单分类标识键
     * @param formCategoryCode  表单分类编码
     * @param formCategoryNumber  表单分类序号
     * @param formCategoryDesc  表单分类描述
     * @param parentId  父节点ID
     * @param parentDepth 父节点深度
     * @return
     */
    public int addFormCategory(String formCategoryName,String formCategoryLabelKey,String formCategoryCode,String formCategoryNumber,String formCategoryDesc,String parentId,String parentDepth,String type){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        //获取表单分类树id
//        String treeId = UUID.randomUUID().toString();
        String treeId = IdUtil.getId();
        paramMap.put("treeId",treeId);
        paramMap.put("name",formCategoryName);
        int nodeDepth = 0;
        if("".equals(parentDepth) || parentDepth==null){
            nodeDepth = 1;
        }else{
            nodeDepth = Integer.parseInt(parentDepth) + 1;
        }
        paramMap.put("depth",nodeDepth);
        paramMap.put("parentId",parentId);
        paramMap.put("key",formCategoryLabelKey);
        paramMap.put("code",formCategoryCode);
        paramMap.put("desc",formCategoryDesc);
        if("CAT_BPM_DEF".equals(type)){
            paramMap.put("catKey","CAT_BPM_DEF");
        }else if("CAT_FORM_VIEW".equals(type)){
            paramMap.put("catKey","CAT_FORM_VIEW");
        }
        paramMap.put("sn",formCategoryNumber);
        paramMap.put("dataShowType","FLAT");
        paramMap.put("tenantId","1");
        //获取当前时间
        String currentTime = TimeUtil.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss");
        paramMap.put("createTime",currentTime);
        paramMap.put("updateTime",currentTime);

        int num = sysTreeDao.addFormCategory(paramMap);
        return num;
    }

    //删除表单分类
    public int deleteFormCategory(String id){
        return sysTreeDao.deleteFormCategory(id);
    }

    @Override
    public List<SysTree> getByCatKeyTenantId(String catKey,String tenantId) {
        Map<String,String> map = new HashMap<>();
        map.put("catKey",catKey);
        map.put("tenantId",tenantId);
        return this.sysTreeDao.getByCatKeyTenantId(map);
    }

    //根据treeId查询表单分类
    public SysTree getTreeById(String id){
        return sysTreeDao.getTreeById(id);
    }
}
