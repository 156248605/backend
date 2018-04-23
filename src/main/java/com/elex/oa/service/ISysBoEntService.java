package com.elex.oa.service;
import com.elex.oa.entity.bo.SysBoEnt;
/**
 *@author hugo.zhao
 *@since 2018/4/13 11:50
*/
public interface ISysBoEntService extends BaseService<SysBoEnt>  {
    SysBoEnt getByBoDefId(String boDefId);
}
