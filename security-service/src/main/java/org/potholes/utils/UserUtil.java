package org.potholes.utils;

import org.potholes.api.sys.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtil {

    public static SysUser getCurrentUser() {
        try {
            return (SysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }

}
