package com.elex.oa.service;


import com.elex.oa.entity.SysAccount;

public interface ISysAccountService {
    SysAccount getByName(String name);
}
