package com.demo.thread;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.CharSink;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;

import java.io.File;
import java.util.*;

/**
 * @author 揭光智
 * @date 2020/05/09
 */
public class SpeechCraft {

    private final static int EACH_ROW_COUNT = 2;
    private final static int TOTAL_ROW = 30;
    private static Set<String> sentenceSet = new HashSet<>(EACH_ROW_COUNT * TOTAL_ROW);
    private static List<String> sentenceList = new ArrayList<>(TOTAL_ROW);
    private static Random random = new Random();

    public static void main(String[] args) throws Exception {
        final String target = "地宫来人/活跃塔来各种/塔123来人了/地宫123来人/活跃塔来人了/爬塔来人/爬塔来各种/地宫来各种/地宫123来各种/塔123来各种/组队爬塔来人/组队地宫来各种";
        //final String target = "20鬼来人了/20鬼来各种了/活跃鬼来各种/捉鬼来人了/捉鬼来各种了/稳定鬼队来人/活跃鬼来人了/20活跃鬼来各种/稳定活跃鬼来人/组队捉鬼来人/组队捉鬼来各种";
        //final String target = "皇宫飞贼来人了/皇宫飞贼来各种/皇宫来人了/飞贼来人了/活跃皇宫来人了/活跃飞贼来人了/稳定皇宫来人/稳定飞贼来人/皇宫飞贼刷活跃了/组队皇宫来人/组队皇宫来各种";
        List<String> targetEles = Splitter.on("/").trimResults().omitEmptyStrings().splitToList(target);

        final String memberProps = "来稳定的/稳定挂机来/要强力各种/来高战的/要高战稳定/随便来人/来不跳车的/来不掉线的/稳定跟队就行/要不跳车那种/不要半路跑路的";
        List<String> memberPropsEles = Splitter.on("/").trimResults().omitEmptyStrings().splitToList(memberProps);

        final String taskProps = "满人开车.../速度来人/组完立马出发/立马出发的/立马开车咯";
        List<String> taskPropsEles = Splitter.on("/").trimResults().omitEmptyStrings().splitToList(taskProps);

        final String face = "#0/#3/#17/#23/#2/#37/#39/#42/#36/#38/#22/#62/#85/#86/#90/#101/#122/#126/#164/#13/#6/#18/#11/#53";
        List<String> faceProps = Splitter.on("/").trimResults().omitEmptyStrings().splitToList(face);

        String ytdg = "F:\\test\\buildTeam\\组队雁塔地宫.txt";
        String zg = "F:\\test\\buildTeam\\组队捉鬼任务.txt";
        String hgfz = "F:\\test\\buildTeam\\组队皇宫飞贼.txt";

        productSpeechCraft(ytdg, targetEles, memberPropsEles, taskPropsEles, faceProps);
        //productSpeechCraft(zg, targetEles, memberPropsEles, taskPropsEles, faceProps);
        //productSpeechCraft(hgfz, targetEles, memberPropsEles, taskPropsEles, faceProps);
    }

    private static void productSpeechCraft(String path, List<String> targetEles, List<String> memberPropsEles, List<String> taskPropsEles, List<String> faceProps) throws Exception {
        File file = new File(path);
        file.delete();
        Files.touch(file);

        String sentence;
        int num_count = 1;
        int index;
        for (int i = 0; i < 3000000; ++i) {
            index = random.nextInt(targetEles.size());
            sentence = targetEles.get(index);
            sentence += ",";
            //12 13 123
            index = random.nextInt(4);
            if (index == 0) { //12
                index = random.nextInt(memberPropsEles.size());
                sentence += memberPropsEles.get(index);
            } else if (index == 1) {//13
                index = random.nextInt(taskPropsEles.size());
                sentence += taskPropsEles.get(index);
            } else if (index == 2) {//123
                index = random.nextInt(memberPropsEles.size());
                sentence += memberPropsEles.get(index);
                sentence += ",";
                index = random.nextInt(taskPropsEles.size());
                sentence += taskPropsEles.get(index);
            } else if (index == 3) {//1234
                index = random.nextInt(memberPropsEles.size());
                sentence += memberPropsEles.get(index);
                sentence += ",";
                index = random.nextInt(taskPropsEles.size());
                sentence += taskPropsEles.get(index);
                sentence = faceThird(sentence, faceProps);
            }

            sentenceSet.add(sentence);

            if (sentenceSet.size() >= EACH_ROW_COUNT * TOTAL_ROW) {
                break;
            }
        }

        StringBuilder line = new StringBuilder();
        for (String s : sentenceSet) {
            if (num_count < EACH_ROW_COUNT) {
                s += "-";
            }
            line.append(s);
            ++num_count;
            if (num_count > EACH_ROW_COUNT) {
                sentenceList.add(line.append("\n").toString());
                line = new StringBuilder();
                num_count = 1;
            }
        }

        CharSink charSink = Files.asCharSink(file, Charsets.UTF_8, FileWriteMode.APPEND);
        for (String s : sentenceList) {
            charSink.write(s);
        }
    }

    private static String faceThird(String sentence, List<String> faceProps) {
        int faceIndex = random.nextInt(3) + 1;
        StringBuilder sentenceBuilder = new StringBuilder(sentence);
        for (int i = 0; i < faceIndex; ++i) {
            sentenceBuilder.append(faceProps.get(random.nextInt(faceProps.size())));
        }
        sentence = sentenceBuilder.toString();
        return sentence;
    }
}
