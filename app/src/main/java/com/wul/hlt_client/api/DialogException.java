package com.wul.hlt_client.api;

public class DialogException extends Exception {

    public String message;

    public DialogException(String message) {
        super(message);
        this.message = message;
    }


}
