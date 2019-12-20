package org.potholes.security;

import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.potholes.enums.StatusEnum;
import org.potholes.mapper.MenuDAO;
import org.potholes.mapper.RoleDAO;
import org.potholes.model.Menu;
import org.potholes.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/***
 * 权限数据
 */
@Component
public class FuncFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private MenuDAO menuDAO;
    @Autowired
    private RoleDAO roleDAO;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        logger.info("权限控制 requestUrl={}", requestUrl);
        if ("/checkLogin".equals(requestUrl)) {
            // 放行
            return null;
        }
        List<Menu> menus = menuDAO.selectAllMenus(StatusEnum.NORMAL.getType());
        if (CollectionUtils.isNotEmpty(menus)) {
            for (Menu m : menus) {
                List<Role> roles = roleDAO.selectByMenuId(m.getMenuId());
                if (antPathMatcher.match(m.getMenuUrl(), requestUrl) && CollectionUtils.isNotEmpty(roles)) {
                    // 判断请求需要的角色权限
                    String[] values = new String[roles.size()];
                    for (int i = 0; i < roles.size(); i++) {
                        values[i] = roles.get(i).getRoleName();
                    }
                    return SecurityConfig.createList(values);
                }
            }
        }
        // 没有匹配上的资源都是登录访问
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        // TODO Auto-generated method stub
        return false;
    }
}
