package com.offcn.vo;

import lombok.Data;

/**
 * @author 胡长生
 * @create 2020-10-24 19:50
 */
@Data
public class BaseVo {

    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
