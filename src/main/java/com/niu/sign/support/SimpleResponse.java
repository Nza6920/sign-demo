package com.niu.sign.support;

import lombok.Data;

/**
 * 统一响应
 *
 * @author [nza]
 * @version 1.0 2020/9/20
 * @createTime 2020/9/20
 */
@Data
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }
}
