package com.app.exception;

import com.google.gson.annotations.SerializedName;

/****************************************
 * Copyright (c) xuning.
 * 尊重版权，禁止抄袭!
 * 如有违反，必将追究其法律责任.
 * @Auther is xuning on 17-2-19
 ****************************************/
public class ErrorInfo extends Base {
    private String code;
    @SerializedName("msg")
    private String messsage;
    private String uuid;

    public ErrorInfo() {
        setSuccess("false");
    }

    public ErrorInfo(String code, String messsage) {
        this.code = code;
        this.messsage = messsage;
        setSuccess("false");
    }

    public ErrorInfo(String code, String messsage, String uuid) {
        this.code = code;
        this.messsage = messsage;
        this.uuid = uuid;
        setSuccess("false");
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
