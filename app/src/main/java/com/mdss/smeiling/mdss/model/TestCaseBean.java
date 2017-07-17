package com.mdss.smeiling.mdss.model;

/**
 * Created by songmeiling on 2016/1/20.
 */
public class TestCaseBean {
    private Integer id;
    private String title;
    private int code;
    private String imgId;
    private String contextId;
    private String successId;
    private String failId;
    private String testingAct;

    public String getTestingAct() {
        return testingAct;
    }

    public void setTestingAct(String testingAct) {
        this.testingAct = testingAct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContextId() {
        return contextId;
    }

    public void setContextId(String contextId) {
        this.contextId = contextId;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getSuccessId() {
        return successId;
    }

    public void setSuccessId(String successId) {
        this.successId = successId;
    }

    public String getFailId() {
        return failId;
    }

    public void setFailId(String failId) {
        this.failId = failId;
    }
}
