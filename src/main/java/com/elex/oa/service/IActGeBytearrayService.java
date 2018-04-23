package com.elex.oa.service;

import com.elex.oa.entity.ActGeBytearray;

/**
 *@author hugo.zhao
 *@since 2018/4/9 13:33
*/
public interface IActGeBytearrayService extends BaseService<ActGeBytearray>{
    String getDefXmlByDeployId(String deployId);
}
