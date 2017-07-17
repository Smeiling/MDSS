package com.mdss.smeiling.mdss.model;

/**
 * Created by songmeiling on 2016/1/19.
 */
public class TestItemBean {
    private String title;
    private int iconId;
    private int result;


    public TestItemBean() {
        super();
    }

    public TestItemBean(String testTitle, int iconId,int result) {
        super();
        this.title = testTitle;
        this.iconId = iconId;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
 
