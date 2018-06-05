package com.elex.oa.saweb.security.provider;
import com.elex.oa.entity.OsGroup;
import com.elex.oa.entity.OsRelInst;
import com.elex.oa.entity.OsUser;
import com.elex.oa.org.model.IGroup;
import com.elex.oa.org.model.IUser;
import com.elex.oa.org.service.GroupService;
import com.elex.oa.org.service.UserService;
import com.elex.oa.service.IOsRelInstService;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class UserDetailsProvider implements UserDetailsService {
    @Resource
    UserService userService;
    @Resource
    GroupService groupService;
    @Resource
    IOsRelInstService osRelInstService;
    @Override
    public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {
        OsUser user =(OsUser)this.userService.getByUsername(username);
        if(user == null) {
            return null;
        }else {
            List groupList = this.groupService.getGroupsByUserId(user.getUserId());
            IGroup mainGroup = this.groupService.getMainByUserId(user.getUserId());
            if(mainGroup != null) {
                OsGroup roles = (OsGroup)mainGroup;
                user.setMainDep(roles);
                if(StringUtils.isNotEmpty(roles.getPath())) {
                    String[] upLowRelInst = roles.getPath().split("[.]");
                    StringBuffer group = new StringBuffer();
                    String[] role = upLowRelInst;
                    int var9 = upLowRelInst.length;
                    for(int var10 = 0; var10 < var9; ++var10) {
                        String depId = role[var10];
                        if(!"0".equals(depId)) {
                            OsGroup group1 = (OsGroup)this.groupService.getById(depId);
                            if(group1 != null) {
                                group.append(group1.getName()).append("/");
                            }
                        }
                    }
                    user.setDepPathNames(group.toString());
                }
            }
            ArrayList var13 = new ArrayList();
            Iterator var14 = groupList.iterator();
            while(var14.hasNext()) {
                IGroup var16 = (IGroup)var14.next();
                user.getGroupIds().add(var16.getIdentityId());
                SimpleGrantedAuthority var17 = new SimpleGrantedAuthority(var16.getKey());
                var13.add(var17);
            }
            user.setAuthorities(var13);
            OsRelInst var15 = this.osRelInstService.getByParty2RelTypeId(user.getUserId(), "3");
            user.setUpLowRelInst(var15);
            return user;
        }
    }
}
