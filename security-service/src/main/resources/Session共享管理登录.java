public class MySessionRegistryImpl implements SessionRegistry, ApplicationListener<SessionDestroyedEvent> {


    private static final String SESSIONIDS = "sessionIds";

    private static final String PRINCIPALS = "principals";

    @Resource
    private RedisTemplate redisTemplate;

    protected final Log logger = LogFactory.getLog(SessionRegistryImpl.class);
//    private final ConcurrentMap<Object, Set<String>> principals = new ConcurrentHashMap();
//    private final Map<String, SessionInformation> sessionIds = new ConcurrentHashMap();

    public MySessionRegistryImpl() {
    }

    public List<Object> getAllPrincipals() {
        return new ArrayList(this.getPrincipalsKeySet());
    }

    public List<SessionInformation> getAllSessions(Object principal, boolean includeExpiredSessions) {
        Set<String> sessionsUsedByPrincipal =  this.getPrincipals(((UserDetails)principal).getUsername());
        if (sessionsUsedByPrincipal == null) {
            return Collections.emptyList();
        } else {
            List<SessionInformation> list = new ArrayList(sessionsUsedByPrincipal.size());
            Iterator var5 = sessionsUsedByPrincipal.iterator();

            while (true) {
                SessionInformation sessionInformation;
                do {
                    do {
                        if (!var5.hasNext()) {
                            return list;
                        }

                        String sessionId = (String) var5.next();
                        sessionInformation = this.getSessionInformation(sessionId);
                    } while (sessionInformation == null);
                } while (!includeExpiredSessions && sessionInformation.isExpired());

                list.add(sessionInformation);
            }
        }
    }

    public SessionInformation getSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        return (SessionInformation) this.getSessionInfo(sessionId);
    }

    public void onApplicationEvent(SessionDestroyedEvent event) {
        String sessionId = event.getId();
        this.removeSessionInformation(sessionId);
    }

    public void refreshLastRequest(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        SessionInformation info = this.getSessionInformation(sessionId);
        if (info != null) {
            info.refreshLastRequest();
        }

    }

    public void registerNewSession(String sessionId, Object principal) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        Assert.notNull(principal, "Principal required as per interface contract");
        if (this.logger.isDebugEnabled()) {
            this.logger.debug("Registering session " + sessionId + ", for principal " + principal);
        }

        if (this.getSessionInformation(sessionId) != null) {
            this.removeSessionInformation(sessionId);
        }

        this.addSessionInfo(sessionId, new SessionInformation(principal, sessionId, new Date()));

//        this.sessionIds.put(sessionId, new SessionInformation(principal, sessionId, new Date()));
        Set<String> sessionsUsedByPrincipal = (Set) this.getPrincipals(principal.toString());
        if (sessionsUsedByPrincipal == null) {
            sessionsUsedByPrincipal = new CopyOnWriteArraySet();
            Set<String> prevSessionsUsedByPrincipal = (Set) this.putIfAbsentPrincipals(principal.toString(), sessionsUsedByPrincipal);
            if (prevSessionsUsedByPrincipal != null) {
                sessionsUsedByPrincipal = prevSessionsUsedByPrincipal;
            }
        }

        ((Set) sessionsUsedByPrincipal).add(sessionId);
        this.putPrincipals(principal.toString(), sessionsUsedByPrincipal);
        if (this.logger.isTraceEnabled()) {
            this.logger.trace("Sessions used by '" + principal + "' : " + sessionsUsedByPrincipal);
        }

    }

    public void removeSessionInformation(String sessionId) {
        Assert.hasText(sessionId, "SessionId required as per interface contract");
        SessionInformation info = this.getSessionInformation(sessionId);
        if (info != null) {
            if (this.logger.isTraceEnabled()) {
                this.logger.debug("Removing session " + sessionId + " from set of registered sessions");
            }

            this.removeSessionInfo(sessionId);
            Set<String> sessionsUsedByPrincipal = (Set) this.getPrincipals(info.getPrincipal().toString());
            if (sessionsUsedByPrincipal != null) {
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug("Removing session " + sessionId + " from principal's set of registered sessions");
                }

                sessionsUsedByPrincipal.remove(sessionId);
                if (sessionsUsedByPrincipal.isEmpty()) {
                    if (this.logger.isDebugEnabled()) {
                        this.logger.debug("Removing principal " + info.getPrincipal() + " from registry");
                    }

                    this.removePrincipal(((UserDetails)info.getPrincipal()).getUsername());
                }

                if (this.logger.isTraceEnabled()) {
                    this.logger.trace("Sessions used by '" + info.getPrincipal() + "' : " + sessionsUsedByPrincipal);
                }

            }
        }
    }


    public void addSessionInfo(final String sessionId, final SessionInformation sessionInformation) {
        BoundHashOperations<String, String, SessionInformation> hashOperations = redisTemplate.boundHashOps(SESSIONIDS);
        hashOperations.put(sessionId, sessionInformation);
    }

    public SessionInformation getSessionInfo(final String sessionId) {
        BoundHashOperations<String, String, SessionInformation> hashOperations = redisTemplate.boundHashOps(SESSIONIDS);
        return hashOperations.get(sessionId);
    }

    public void removeSessionInfo(final String sessionId) {
        BoundHashOperations<String, String, SessionInformation> hashOperations = redisTemplate.boundHashOps(SESSIONIDS);
        hashOperations.delete(sessionId);
    }

    public Set<String> putIfAbsentPrincipals(final String key, final Set<String> set) {
        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
        hashOperations.putIfAbsent(key, set);
        return hashOperations.get(key);
    }

    public void putPrincipals(final String key, final Set<String> set) {
        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
        hashOperations.put(key,set);
    }

    public Set<String> getPrincipals(final String key) {
        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
        return hashOperations.get(key);
    }

    public Set<String> getPrincipalsKeySet() {
        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
        return hashOperations.keys();
    }

    public void removePrincipal(final String key) {
        BoundHashOperations<String, String, Set<String>> hashOperations = redisTemplate.boundHashOps(PRINCIPALS);
        hashOperations.delete(key);
    }


}


// 重写ConcurrentSessionControlAuthenticationStrategy
public class MyConcurrentSessionControlAuthenticationStrategy extends ConcurrentSessionControlAuthenticationStrategy {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();
    private final SessionRegistry sessionRegistry;
    private boolean exceptionIfMaximumExceeded = false;
    private int maximumSessions = 1;

    public MyConcurrentSessionControlAuthenticationStrategy(SessionRegistry sessionRegistry) {
        super(sessionRegistry);
        Assert.notNull(sessionRegistry, "The sessionRegistry cannot be null");
        this.sessionRegistry = sessionRegistry;
    }

    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        List<SessionInformation> sessions = this.sessionRegistry.getAllSessions(authentication.getPrincipal(), false);
        int sessionCount = sessions.size();
        int allowedSessions = this.getMaximumSessionsForThisUser(authentication);
        if(sessionCount >= allowedSessions) {
            if(allowedSessions != -1) {
                if(sessionCount == allowedSessions) {
                    HttpSession session = request.getSession(false);
                    if(session != null) {
                        Iterator var8 = sessions.iterator();

                        while(var8.hasNext()) {
                            SessionInformation si = (SessionInformation)var8.next();
                            if(si.getSessionId().equals(session.getId())) {
                                return;
                            }
                        }
                    }
                }

                this.allowableSessionsExceeded(sessions, allowedSessions, this.sessionRegistry);
            }
        }
    }

    protected int getMaximumSessionsForThisUser(Authentication authentication) {
        return this.maximumSessions;
    }

    protected void allowableSessionsExceeded(List<SessionInformation> sessions, int allowableSessions, SessionRegistry registry) throws SessionAuthenticationException {
        if(!this.exceptionIfMaximumExceeded && sessions != null) {
            SessionInformation leastRecentlyUsed = null;
            Iterator var5 = sessions.iterator();

            while(true) {
                SessionInformation session;
                do {
                    if(!var5.hasNext()) {
                        leastRecentlyUsed.expireNow();
                        ((MySessionRegistryImpl)sessionRegistry).addSessionInfo(leastRecentlyUsed.getSessionId(),leastRecentlyUsed);
                        return;
                    }

                    session = (SessionInformation)var5.next();
                } while(leastRecentlyUsed != null && !session.getLastRequest().before(leastRecentlyUsed.getLastRequest()));

                leastRecentlyUsed = session;
            }
        } else {
            throw new SessionAuthenticationException(this.messages.getMessage("ConcurrentSessionControlAuthenticationStrategy.exceededAllowed", new Object[]{Integer.valueOf(allowableSessions)}, "Maximum sessions of {0} for this principal exceeded"));
        }
    }

    public void setExceptionIfMaximumExceeded(boolean exceptionIfMaximumExceeded) {
        this.exceptionIfMaximumExceeded = exceptionIfMaximumExceeded;
    }

    public void setMaximumSessions(int maximumSessions) {
        Assert.isTrue(maximumSessions != 0, "MaximumLogins must be either -1 to allow unlimited logins, or a positive integer to specify a maximum");
        this.maximumSessions = maximumSessions;
    }

    public void setMessageSource(MessageSource messageSource) {
        Assert.notNull(messageSource, "messageSource cannot be null");
        this.messages = new MessageSourceAccessor(messageSource);
    }

}





@Bean
public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
	MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
	myUsernamePasswordAuthenticationFilter.setPostOnly(true);
	myUsernamePasswordAuthenticationFilter.setAuthenticationManager(this.authenticationManager());
	myUsernamePasswordAuthenticationFilter.setUsernameParameter("name_key");
	myUsernamePasswordAuthenticationFilter.setPasswordParameter("pwd_key");
	myUsernamePasswordAuthenticationFilter.setVerificationCodeParameter("verification_code");
	myUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/checkLogin", "POST"));
	myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(simpleUrlAuthenticationFailureHandler());
	myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
	myUsernamePasswordAuthenticationFilter.setSessionAuthenticationStrategy(new MyConcurrentSessionControlAuthenticationStrategy(sessionRegistry));
	return myUsernamePasswordAuthenticationFilter;
}