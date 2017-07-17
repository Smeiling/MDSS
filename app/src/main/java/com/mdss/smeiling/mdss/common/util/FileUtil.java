package com.mdss.smeiling.mdss.common.util;

import android.content.Context;

import com.mdss.smeiling.mdss.R;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by songmeiling on 2016/1/19.
 */
public class FileUtil {
    //新建文件
    public static void newfile(Context context) {
        String[] titles = context.getResources().getStringArray(R.array.testcase_name);
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput("result.txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("[SMMI Test Result]\r\n");
            for (String title : titles) {
                writer.write(title + ",Initialize\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //单测结果写入
    public static void writeSingle(Context context, String title, String result) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput("result.txt", Context.MODE_APPEND);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(title + "," + result + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //全测结果写入
    public static void writeAllTest(Context context, String[] titles, int[] results) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = context.openFileOutput("result.txt", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write("[SMMI Test Result]\r\n");
            for (int i = 0; i < titles.length; i++) {
                if(results[i]==1){
                    writer.write(titles[i] + ",passed\r\n");
                }else if (results[i] ==2){
                    writer.write(titles[i] + ",failed\r\n");
                }else {
                    writer.write(titles[i] + ",Initialize\r\n");
                }

            }
            writer.write("[SMMI All Test Done]\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
 
