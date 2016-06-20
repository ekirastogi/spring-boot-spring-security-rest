package com.ekiras.ss.security.token;

import com.ekiras.ss.security.core.RestAuthenticationPayload;
import com.ekiras.ss.security.core.SimpleRestAuthenticationPayload;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ekansh
 * @since 16/4/16
 */
public class RestAuthenticationToken extends AbstractAuthenticationToken {

    private RestAuthenticationPayload payload;
    private Object principal;
    private Object credentials;

    public RestAuthenticationToken(Object principal,Object credentials){
        super(null);
        payload = new SimpleRestAuthenticationPayload(null);
        this.principal =principal;
        this.credentials=credentials;
        setAuthenticated(false);
    }

    public RestAuthenticationToken(RestAuthenticationPayload payload,Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.payload = payload;
        setAuthenticated(true);
    }

    /**
     * Creates a token with the supplied array of authorities.
     *
     * @param authorities the collection of <tt>GrantedAuthority</tt>s for the payload
     *                    represented by this authentication object.
     */
    public RestAuthenticationToken(RestAuthenticationPayload payload,Object credentials,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.payload = payload;
        this.credentials=credentials;
        setAuthenticated(false);

    }

    public RestAuthenticationPayload getPayload() {
        return payload;
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
