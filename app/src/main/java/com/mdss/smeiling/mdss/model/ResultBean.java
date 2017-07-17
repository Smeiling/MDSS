package com.mdss.smeiling.mdss.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by songmeiling on 2016/1/27.
 */
public class ResultBean implements Parcelable {
    private int testId;
    private int pass;
    private int fail;
    private int result;

    public ResultBean() {
        pass = 0;
        fail = 0;
        result = 0;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    //内容描述接口
    @Override
    public int describeContents() {
        return 0;
    }


    //写入接口函数，用来打包
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(testId);
        dest.writeInt(pass);
        dest.writeInt(fail);
        dest.writeInt(result);
    }


    public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
        @Override
        public ResultBean createFromParcel(Parcel source) {
            return new ResultBean(source);
        }

        @Override
        public ResultBean[] newArray(int size) {
            return new ResultBean[size];
        }
    };

    public ResultBean(Parcel source) {
        testId = source.readInt();
        pass = source.readInt();
        fail = source.readInt();
        result = source.readInt();
    }
}
