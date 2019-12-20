package org.potholes.service;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.potholes.api.sys.SysUser;
import org.potholes.mapper.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SysUserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SessionRegistry sessionRegistry;
    @Autowired
    private ThreadPoolTaskExecutor threadPool;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        SysUser user = userDAO.selectByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("账号不存在");
        }
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 剔除相同用户多处登录
                    List<Object> o = sessionRegistry.getAllPrincipals();
                    if (CollectionUtils.isNotEmpty(o)) {
                        for (Object principal : o) {
                            if (principal instanceof SysUser
                                    && (user.getUserName().equals(((SysUser) principal).getUserName()))) {
                                // 当前账户所有<有效>的SESSION
                                List<SessionInformation> oldSessionsInfo = sessionRegistry.getAllSessions(principal,
                                        false);
                                if (CollectionUtils.isNotEmpty(oldSessionsInfo)) {
                                    for (SessionInformation seesion : oldSessionsInfo) {
                                        seesion.expireNow();
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    logger.warn("seesion.expireNow error....", e);
                }
            }
        });
        return user;
    }

}
