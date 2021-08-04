package com.tkachuk.websocketproject.service;

import com.tkachuk.websocketproject.model.LoggedUserModel;
import org.springframework.social.oauth2.AccessGrant;

public interface OAuthService {
    LoggedUserModel parseFacebookUser(AccessGrant accessGrant);
}