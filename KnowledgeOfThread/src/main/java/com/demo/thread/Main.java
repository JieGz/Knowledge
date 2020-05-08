package com.demo.thread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author 揭光智
 * @date 2019/09/16
 */
public class Main {

    private final static int EACH_ROW_COUNT = 10;
    private final static int TOTAL_ROW = 30;
    private static Set<String> sentenceSet = new HashSet<>(EACH_ROW_COUNT * TOTAL_ROW);
    private static List<String> sentenceList = new ArrayList<>(TOTAL_ROW);


    public static void main(String[] args) {

        String target = "一条来人/一条来各种/一条龙了/一条/素质一条/豪华一条/日常一条/豪华一条龙/日常豪华一条龙";
        String[] targetArr = target.split("/");

        String teamMembers = "来稳定的/要清白的/来不跳车的/要不跑的/奶妈和狮驼优先/来强力高战的/跟车不跑的那种/稳定挂机来/来清白挂机/要强力各种/来高战的/要高战稳定/来辅助,ST,稳定输出/你挂好机不掉线就可以了";
        String[] teamMembersArr = teamMembers.split("/");


        String taskProps = "满人开车.../速度来人/组完立马出发/立马出发的/立马开车咯/啥都做/任务全清光!/马上开始清/马上就清了/全部清光光/做完全部/是任务都做";
        String[] taskPropsArr = taskProps.split("/");


        String face = "#0/#3/#17/#23/#2/#37/#39/#42/#36/#38/#22/#62/#85/#86/#90/#101/#122/#126/#164/#13/#6/#18/#11/#53";
        String[] faceArr = face.split("/");


        String propsSt = "来稳定狮驼/要清白的狮驼/死跟不走不跳的狮驼/半路不跑的狮驼/来个ST/来个肉狮驼/跟车不跑的狮驼/不跳车的狮驼/来强力狮驼/来稳定st/要清白的ST/死跟不走不跳的st/半路不跑的ST";
        String[] stArr = propsSt.split("/");

        String propsPt = "来稳定普陀，化生/要清白的普陀，化生/死跟不走不跳的普陀，化生/半路不跑的普陀，化生/来个治疗/来个辅助/跟车不跑的普陀，化生/不跳车的普陀，化生/来强力普陀，化生/要强力的辅助/稳定不跳车的辅助/来稳定辅助/要清白的辅助/死跟不走不跳的辅助/半路不跑的辅助/来稳定治疗/要清白的治疗/死跟不走不跳的治疗/半路不跑的治疗/来稳定pt，hs/要清白的PT，HS/死跟不走不跳的pt，hs/半路不跑的PT，HS";
        String[] ptArr = propsPt.split("/");

        String propsStPt = "来稳定狮驼，辅助/要清白的狮驼和辅助/死跟不走不跳的狮驼，辅助/半路不跑的狮驼和辅助/来个ST和治疗/来个肉狮驼和辅助/跟车不跑的狮驼和治疗/不跳车的狮驼，治疗/来强力狮驼，治疗/要强力的ST，辅助/稳定不跳车的ST，辅助/来稳定ST，治疗/要清白的ST和治疗/死跟不走不跳的ST和辅助/半路不跑的ST，治疗/来稳定st，pt/要清白的st，pt/死跟不走不跳的ST，PT/半路不跑的st，pt";
        String[] stptArr = propsStPt.split("/");

        String propsCarry = "来稳定输出/要清白的输出/死跟不走不跳的输出/半路不跑的输出/来个输出/跟车不跑的输出/不跳车的输出/来强力输出/稳定不跳车的输出/来稳定的输出/要清白的法术物理/死跟不走不跳的法术物理/半路不跑的法术物理/来稳定lg，dt/要清白的lg，dt/死跟不走不跳的LG，DT/半路不跑的lg，dt";
        String[] carryArr = propsCarry.split("/");


        String buildTeam = "F:\\test\\buildTeam\\组队一条.txt";
        String st = "F:\\test\\buildTeam\\组队一条来ST.txt";
        String qst = "F:\\test\\buildTeam\\组队一条缺ST.txt";
        String pt = "F:\\test\\buildTeam\\组队一条来PT.txt";
        String qpt = "F:\\test\\buildTeam\\组队一条缺PT.txt";
        String stpt = "F:\\test\\buildTeam\\组队一条来STPT.txt";
        String qstpt = "F:\\test\\buildTeam\\组队一条缺STPT.txt";
        String carry = "F:\\test\\buildTeam\\组队一条缺输出.txt";


        proc(buildTeam, targetArr, teamMembersArr, taskPropsArr, faceArr);
        proc(st, targetArr, stArr, taskPropsArr, faceArr);
        proc(qst, targetArr, stArr, taskPropsArr, faceArr);
        proc(pt, targetArr, ptArr, taskPropsArr, faceArr);
        proc(qpt, targetArr, ptArr, taskPropsArr, faceArr);
        proc(stpt, targetArr, stptArr, taskPropsArr, faceArr);
        proc(qstpt, targetArr, stptArr, taskPropsArr, faceArr);
        proc(carry, targetArr, carryArr, taskPropsArr, faceArr);


    }


    private static String normalFirst(String sentence, String[] arr, boolean flag) {
        sentence += arr[new Random().nextInt(arr.length)];
        if (flag) {
            sentence += ",";
        }
        return sentence;
    }

    private static String normalSecond(String sentence, String[] arr, boolean flag) {
        return normalFirst(sentence, arr, flag);
    }

    private static String faceThird(String sentence, String[] faceArr) {
        Random random = new Random();
        int faceIndex = random.nextInt(3) + 1;
        StringBuilder sentenceBuilder = new StringBuilder(sentence);
        for (int i = 0; i < faceIndex; ++i) {
            sentenceBuilder.append(faceArr[random.nextInt(faceArr.length)]);
        }
        sentence = sentenceBuilder.toString();
        return sentence;
    }


    public static void proc(String destination, String[] targetArr, String[] teamMembersArr, String[] taskPropsArr, String[] faceArr) {
        File file = new File(destination);
        if (file.exists()) {
            file.delete();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        String sentence = "";
        int num_count = 1;
        for (int i = 0; i < 3000000; ++i) {
            Random random = new Random();
            //第一次随机
            int index;
            sentence = targetArr[random.nextInt(targetArr.length)];

            //第二次随机
            index = random.nextInt(8);
            if (index == 0) {//123
                sentence = normalFirst(sentence, teamMembersArr, true);
                sentence = normalSecond(sentence, taskPropsArr, true);
                sentence = faceThird(sentence, faceArr);
            } else if (index == 1) {//132
                sentence = normalFirst(sentence, teamMembersArr, true);
                sentence = faceThird(sentence, faceArr);
                sentence = normalSecond(sentence, taskPropsArr, false);
            } else if (index == 2) {//213
                sentence = normalSecond(sentence, taskPropsArr, true);
                sentence = normalFirst(sentence, teamMembersArr, true);
                sentence = faceThird(sentence, faceArr);
            } else if (index == 3) {//231
                sentence = normalSecond(sentence, taskPropsArr, true);
                sentence = faceThird(sentence, faceArr);
                sentence = normalFirst(sentence, teamMembersArr, false);
            } else if (index == 4) {//312
                sentence = faceThird(sentence, faceArr);
                sentence = normalFirst(sentence, teamMembersArr, true);
                sentence = normalSecond(sentence, taskPropsArr, false);
            } else if (index == 5) {//321
                sentence = faceThird(sentence, faceArr);
                sentence = normalSecond(sentence, taskPropsArr, true);
                sentence = normalFirst(sentence, teamMembersArr, false);
            } else if (index == 6) {
                sentence = normalFirst(sentence, teamMembersArr, true);
                sentence = normalSecond(sentence, taskPropsArr, false);
            } else if (index == 7) {
                sentence = normalSecond(sentence, taskPropsArr, true);
                sentence = normalFirst(sentence, teamMembersArr, false);
            }

            sentenceSet.add(sentence);

            if (sentenceSet.size() >= EACH_ROW_COUNT * TOTAL_ROW) {
                break;
            }
        }
        String line = "";
        for (String s : sentenceSet) {
            if (num_count < EACH_ROW_COUNT) {
                s += "-";
            }
            line += s;
            ++num_count;
            if (num_count > EACH_ROW_COUNT) {
                sentenceList.add(line);
                line = "";
                num_count = 1;
            }
        }


        try (FileOutputStream downloadFile = new FileOutputStream(destination)) {
            for (String s : sentenceList) {
                s += "\n";
                downloadFile.write(s.getBytes());
            }

            sentenceSet.clear();
            sentenceList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
