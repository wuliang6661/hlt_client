package com.wul.hlt_client.entity;

import com.wul.hlt_client.entity.request.BaseRequest;

public class TousuBO extends BaseRequest {


    /**
     * content : string
     */

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
