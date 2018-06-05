package com.elex.oa.sys.bo.service;

import com.elex.oa.core.entity.SqlModel;
import com.elex.oa.entity.bo.SysBoEnt;

public interface ISqlBuilder {
    SqlModel getByFk(SysBoEnt var1, String var2);
}
