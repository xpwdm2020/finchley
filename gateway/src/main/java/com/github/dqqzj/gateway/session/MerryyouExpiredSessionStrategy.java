package com.github.dqqzj.gateway.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * session过期
 * Created on 2018/1/13 0013.
 * @author qzj
 * @since 1.0
 */
public class MerryyouExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    /**
     * @param invalidSessionUrl
     */
    public MerryyouExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {

//        onSessionInvalid(event.getRequest(), event.getResponse());
    }

}
