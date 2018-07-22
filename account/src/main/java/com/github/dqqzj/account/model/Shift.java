package com.github.dqqzj.account.model;

import com.github.dqqzj.account.domain.RestStatus;
import com.github.dqqzj.account.exception.RestStatusException;
import com.github.dqqzj.account.model.response.ErrorEntity;
import java.util.Optional;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author qinzhongjian
 * @date created in 2018/6/25 21:56
 * @since 1.0.0
 */
public class Shift {
    private Shift() {
    }

    /**
     * 抛出具体的{@code RestStatus}异常
     *
     * @param status  自定义异常实体
     * @param details 额外添加至details字段中的任意实体, 最终会被解析成JSON
     */
    public static void fatal(RestStatus status, Object... details) {
        checkNotNull(status);
        final ErrorEntity entity = new ErrorEntity(status);
        // inject details
        if (details.length > 0) {
            Optional.of(details).ifPresent(entity::setDetails);
        }
        // put it into request, details entity by Rest Status's name
        String errorCode = String.valueOf(status.code());
        throw new RestStatusException(errorCode);
    }


}
