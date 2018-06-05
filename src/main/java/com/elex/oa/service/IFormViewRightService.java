package com.elex.oa.service;


import com.elex.oa.form.entity.FormViewRight;

import java.util.List;

public interface IFormViewRightService {

    List<FormViewRight> getByFormAliasNodeIdActDefIdSolId(String formAlias, String nodeId, String actDefId, String solId);

    List<FormViewRight> getByFormKeyNodeId(String formKey, String nodeId);
}
