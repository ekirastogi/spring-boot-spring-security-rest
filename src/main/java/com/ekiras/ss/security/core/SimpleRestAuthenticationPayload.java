package com.ekiras.ss.security.core;

/**
 * @author ekansh
 * @since 15/4/16
 */
public class SimpleRestAuthenticationPayload implements RestAuthenticationPayload {

    private String authToken;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public SimpleRestAuthenticationPayload(String authToken){
        this.authToken=authToken;
    }

    public SimpleRestAuthenticationPayload(String authToken, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled){
        this.authToken=authToken;
        this.accountNonExpired=accountNonExpired;
        this.accountNonLocked=accountNonLocked;
        this.credentialsNonExpired=credentialsNonExpired;
        this.enabled=enabled;
    }

    @Override
    public String toString() {
        return "SimpleRestAuthenticationPayload{" +
                "authToken='" + authToken + '\'' +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}