package org.potholes.security;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

/***
 * 权限决策
 */
@Component
public class FuncAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        if (CollectionUtils.isNotEmpty(configAttributes)) {
            for (ConfigAttribute attr : configAttributes) {
                // 当前请求需要的权限
                String needRole = attr.getAttribute();
                if ("ROLE_LOGIN".equals(needRole)) {
                    if (authentication instanceof AnonymousAuthenticationToken) {
                        throw new BadCredentialsException("未登录");
                    } else {
                        return;
                    }
                }
                // 当前用户所具有的权限
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                for (GrantedAuthority authority : authorities) {
                    if (authority.getAuthority().equals(needRole)) {
                        return;
                    }
                }
            }
        }
        throw new AccessDeniedException("无权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return true;
    }
}