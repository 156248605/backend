package com.elex.oa.org.service;

import com.elex.oa.org.model.IUser;

import java.util.List;

/**
 *@author hugo.zhao
 *@since 2018/4/26 11:20
*/
public interface UserService {
    IUser getByUserId(String var1);

    IUser getByUsername(String var1);

    List<IUser> getByGroupIdAndType(String var1, String var2);

    List<IUser> getByGroupId(String var1);
}
