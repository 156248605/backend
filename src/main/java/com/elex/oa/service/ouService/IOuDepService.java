package com.elex.oa.service.ouService;

import com.elex.oa.entity.ou.OuDep;

/**
 * @Description: DOTO
 * @Author shiyun
 * @Date 2019\1\22 0022 15:12
 * @Version 1.0
 **/
public interface IOuDepService {
    Object addOuDep(OuDep ouDep);

    Object listDepts();

    Object queryOneDepByDepcode(String code);
}