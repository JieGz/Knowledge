package com.demo;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {

    private static final String DEVICE_DELAY = "device.delay";
    private static final String DEVICE_CLICK = "device.click(";
    private static final String DEVICE_STATE_DOWN = ", sigmaConst.STATE_DOWN";
    private static final String DEVICE_STATE_UP = ", sigmaConst.STATE_UP";
    private static final String DEVICE_SWIPE = "device.swipe([";
    private static final String STEP_CLICK = "click(";

    private static int tmpTime = 0;
    private static final int USEFUL_DELAY_TIME = 200;

    public static void main(String[] args) {
        convert("D:\\work\\project\\idea\\Knowledge\\KnowledgeOfParse\\src\\main\\java\\com\\demo\\a.txt", "d://b.txt");
    }


    private static String jsConvertJava(String content) {
        if (content.contains(DEVICE_DELAY)) {
            String replace = content.replace(DEVICE_DELAY + "(", "");
            replace = replace.replace(");", "");
            int time = Integer.valueOf(replace.trim());
            content = "";
            if (time > USEFUL_DELAY_TIME) {
                tmpTime = time;
            }
        } else if (content.contains(DEVICE_CLICK) && content.contains(DEVICE_STATE_DOWN)) {
            content = content.replace(DEVICE_CLICK, STEP_CLICK + tmpTime + "L, ");
            content = content.replace(DEVICE_STATE_DOWN, "");
            tmpTime = 0;
        } else if (content.contains(DEVICE_CLICK) && content.contains(DEVICE_STATE_UP)) {
            content = "";
        }/* else if (content.contains(DEVICE_SWIPE)) {
            content = content.replace(DEVICE_SWIPE, "");
            content = content.replace("]);", "");
            System.out.println(content);
            String[] split = content.split("],");
            int length = split.length;
            String first = split[0];
            String last = split[length - 1];

            first = first.replace("[", "");
            first = first.replace("]", "");
            String[] firstArr = first.split(",");

            last = last.replace("[", "");
            last = last.replace("]", "");
            String[] lastArr = last.split(",");

            content = "slide(" + tmpTime + "L, " + firstArr[0] + ", " + firstArr[1] + ", " + lastArr[0] + ", " + lastArr[1] + ");";
        }*/
        if (!"".equals(content)) {
            return content + "\r\n";
        }
        return "";
    }


    private static void convert(String originFile, String productFile) {
        try (FileInputStream inputStream = new FileInputStream(originFile); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String str;
            while ((str = bufferedReader.readLine()) != null) {
                String content = jsConvertJava(str);
                if (!"".equals(content)) {
                    saveAsFileWriter(productFile, content);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveAsFileWriter(String filePath, String content) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                boolean b = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fwriter = null;
        try {
            fwriter = new FileWriter(filePath, true);
            fwriter.write(content);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                fwriter.flush();
                fwriter.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
