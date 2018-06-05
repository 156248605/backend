package com.elex.oa.service.impl;


import com.elex.oa.dao.IFormViewRightDao;
import com.elex.oa.form.entity.FormViewRight;
import com.elex.oa.service.IFormViewRightService;
import com.elex.oa.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FormViewRightServiceImpl implements IFormViewRightService{

    @Autowired
    private IFormViewRightDao formViewRightDao;


    @Override
    public List<FormViewRight> getByFormAliasNodeIdActDefIdSolId(String formAlias, String nodeId, String actDefId, String solId) {
        FormViewRight formViewRight = new FormViewRight();
        List<FormViewRight> FormViewRights;
        if(StringUtil.isEmpty(actDefId)){
            formViewRight.setFormAlias(formAlias);
            formViewRight.setNodeId(nodeId);
        }else {
            formViewRight.setFormAlias(formAlias);
            formViewRight.setNodeId(nodeId);
            formViewRight.setActDefId(actDefId);
            formViewRight.setSolId(solId);
        }
        FormViewRights = formViewRightDao.select(formViewRight);
        return FormViewRights ;
    }

    @Override
    public List<FormViewRight> getByFormKeyNodeId(String formKey, String nodeId) {
        FormViewRight formViewRight = new FormViewRight();
        formViewRight.setFormAlias(formKey);
        formViewRight.setNodeId(nodeId);
        return formViewRightDao.select(formViewRight);
    }
}
