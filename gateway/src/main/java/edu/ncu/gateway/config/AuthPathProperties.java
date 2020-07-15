package edu.ncu.gateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "auth")
public class AuthPathProperties {

    private List<String> authPaths;
    private List<String> loginPaths;

    public List<String> getAuthPaths() {
        return authPaths;
    }

    public void setAuthPaths(List<String> authPaths) {
        this.authPaths = authPaths;
    }

    public List<String> getLoginPaths() {
        return loginPaths;
    }

    public void setLoginPaths(List<String> loginPaths) {
        this.loginPaths = loginPaths;
    }
}
