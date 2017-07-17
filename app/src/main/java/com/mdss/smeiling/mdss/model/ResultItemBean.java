package com.mdss.smeiling.mdss.model;

/**
 * Created by songmeiling on 2016/1/19.
 */
public class ResultItemBean {
    private String testTitle;
    private int testIcon;
    private int resultIcon;
    private int successNum;
    private int failNum;
    private ResultBean resultBean;

    public ResultItemBean(String testTitle, int testIcon, ResultBean resultBean) {
        super();
        this.testTitle = testTitle;
        this.testIcon = testIcon;
        this.successNum = 0;
        this.failNum = 0;
        this.resultBean = resultBean;
    }

    public String getTestTitle() {
        return testTitle;
    }

    public void setTestTitle(String testTitle) {
        this.testTitle = testTitle;
    }

    public int getTestIcon() {
        return testIcon;
    }

    public void setTestIcon(int testIcon) {
        this.testIcon = testIcon;
    }

    public int getResultIcon() {
        return resultIcon;
    }

    public void setResultIcon(int resultIcon) {
        this.resultIcon = resultIcon;
    }

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }

    public ResultBean getResultBean() {
        return resultBean;
    }

    public void setResultBean(ResultBean resultBean) {
        this.resultBean = resultBean;
    }
}
