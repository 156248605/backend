package com.elex.oa.controller;

import com.elex.oa.entity.SysTree;
import com.elex.oa.service.ISysTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author hugo.zhao
 *@since 2018/3/15 11:33
*/
@RestController
@CrossOrigin
@RequestMapping("/sysTree")
public class SysTreeController {

    private ISysTreeService sysTreeService;
    @Autowired
    public SysTreeController(ISysTreeService sysTreeService) {
        this.sysTreeService = sysTreeService;
    }
    @RequestMapping({"/listAllByCatKey"})
    public List<SysTree> listAllByCatKey(HttpServletRequest request){
        String catKey = request.getParameter("catKey");
        Map<String,String> map = new HashMap<>();
        map.put("catKey",catKey);
        map.put("tenantId","1");
        return sysTreeService.selectByCatKey(map);
    }
    @RequestMapping("/listDicTree")
    public List<SysTree> listDicTree(){
        Map<String,String> map = new HashMap<>();
        map.put("catKey","CAT_DIM");
        map.put("tenantId","1");
        return this.sysTreeService.selectByCatKey(map);
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
    @RequestMapping({"/addFormCategory"})
    public int addFormCategory(@RequestParam("formCategoryName") String formCategoryName,
                               @RequestParam("formCategoryLabelKey") String formCategoryLabelKey,
                               @RequestParam("formCategoryCode") String formCategoryCode,
                               @RequestParam("formCategoryNumber") String formCategoryNumber,
                               @RequestParam("formCategoryDesc") String formCategoryDesc,
                               @RequestParam("parentId") String parentId,
                               @RequestParam("parentDepth") String parentDepth){
        int addNum = sysTreeService.addFormCategory(formCategoryName,formCategoryLabelKey,formCategoryCode,formCategoryNumber,formCategoryDesc,parentId,parentDepth);
        return addNum;
    }

    /**
     * 删除表单分类
     * @param id 当前待删除节点的ID
     */
    @RequestMapping({"/deleteFormCategory"})
    public int deleteFormCategory(@RequestParam("id") String id){
        int deleteNum = sysTreeService.deleteFormCategory(id);
        return deleteNum;
    }

}
