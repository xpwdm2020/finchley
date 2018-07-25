package com.github.dqqzj.gateway.session;

/**
 * Created on 2018/1/27 0027.
 *
 * @author qzj
 * @since 1.0
 */
public class MerryyouInvalidSessionStrategy extends AbstractSessionStrategy {
    public MerryyouInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

//    @Override
//    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        onSessionInvalid(request, response);
//    }
}
