package com.elex.oa.saweb.security.consts;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
public class SecurityConsts {
    public static final ConfigAttribute ROLE_ANONYMOUS = new SecurityConfig("ROLE_ANONYMOUS");
    public static final ConfigAttribute ROLE_PUB = new SecurityConfig("ROLE_PUB");
    public static String S_ROLE_PUBLIC = "ROLE_PUBLIC";
    public static String S_ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

    public SecurityConsts() {
    }
}