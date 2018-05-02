package com.elex.oa.org.context;
import java.util.Set;
/**
 * @author hugo.zhao
 * @since 2017/11/10 10:53
 */
public interface IProfileService {
    String getType();

    String getName();

    Set<String> getCurrentProfile();
}
