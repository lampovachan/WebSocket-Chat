package com.tkachuk.websocketproject.oauth;

import org.springframework.social.oauth2.AccessGrant;

public interface OAuthProvider {
    String getApiKey();

    String getApiSecret();

    String getAuthorizeUrl(String callback, String state);

    AccessGrant getAccessGrant(String callback, String authorizationCode);
}
