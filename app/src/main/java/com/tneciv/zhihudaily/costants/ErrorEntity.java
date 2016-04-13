package com.tneciv.zhihudaily.costants;

/**
 * Created by Tneciv on 2-5-0005.
 */
public class ErrorEntity {
    private String msg;
    private String type;

    public ErrorEntity(String msg, String type) {
        this.msg = msg;
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
