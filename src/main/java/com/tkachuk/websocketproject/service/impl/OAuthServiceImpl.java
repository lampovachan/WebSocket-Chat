package com.tkachuk.websocketproject.service.impl;

import com.tkachuk.websocketproject.model.LoggedUserModel;
import com.tkachuk.websocketproject.service.OAuthService;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class OAuthServiceImpl implements OAuthService {

    @Override
    public LoggedUserModel parseFacebookUser(AccessGrant accessGrant) {
        FacebookTemplate template = new FacebookTemplate(accessGrant.getAccessToken());
        String[] fields = {"id", "picture", "first_name", "last_name", "gender", "email"};
        User profile = template.fetchObject("me", User.class, fields);
        String id = profile.getId();
        String firstName = profile.getFirstName();
        String lastName = profile.getLastName();

        byte[] image = template.userOperations().getUserProfileImage();
        StringBuilder imageString = new StringBuilder();
        imageString.append("data:image/png;base64,");
        imageString.append(Base64.getEncoder().encodeToString(image));

        return new LoggedUserModel(id, firstName + " " + lastName, imageString.toString());
    }

}
