package com.elex.oa.service.impl;
import com.elex.oa.dao.ISysAccountDao;
import com.elex.oa.entity.SysAccount;
import com.elex.oa.service.ISysAccountService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 *@author hugo.zhao
 *@since 2018/5/10 15:55
*/
@Service
public class SysAccountServiceImpl implements ISysAccountService {
    @Resource
    private ISysAccountDao sysAccountDao;
    @Override
    public SysAccount getByName(String name) {
        String[] names = name.split("@");
        SysAccount sysAccount = new SysAccount();
        if(names.length == 2) {
            sysAccount.setName(names[0]);
            sysAccount.setDomain(names[1]);
        }else {
            sysAccount.setName(name);
        }
        return sysAccountDao.selectOne(sysAccount);

    }
}
