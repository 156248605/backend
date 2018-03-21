package com.elex.oa.util;

import org.springframework.beans.factory.InitializingBean;

/**
 *@author hugo.zhao
 *@since 2018/3/19 15:41
*/
public interface IdGenerator extends InitializingBean {
    Long getLID();
    String getSID();
}
