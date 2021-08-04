package com.tkachuk.websocketproject.controller;

import com.tkachuk.websocketproject.model.LoggedUserModel;
import com.tkachuk.websocketproject.oauth.OAuthProvider;
import com.tkachuk.websocketproject.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping(value = "/oauth/facebook")
public class AuthController {

    @Autowired
    private OAuthProvider facebookProvider;

    @Autowired
    private OAuthService oAuthService;

    @Autowired
    private SimpMessagingTemplate template;

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ModelAndView signin(@RequestParam String state, HttpServletRequest request) {
        String authorizeUrl = facebookProvider.getAuthorizeUrl(getCallback(request), state);
        return new ModelAndView(new RedirectView(authorizeUrl, true, true, true));
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public RedirectView callback(@RequestParam(required = false) String code, @RequestParam String state, HttpServletRequest request) throws IOException {
        if (code != null) {
            LoggedUserModel loggedUser = oAuthService.parseFacebookUser(facebookProvider.getAccessGrant(getCallback(request), code));
            template.convertAndSend("/oauth/response/" + state, loggedUser);
        }
        return new RedirectView(getRootPath(request) + "/autoclose.html");
    }

    private String getCallback(HttpServletRequest request) {
        return getRootPath(request) + "/oauth/facebook/callback";
    }

    private String getRootPath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
