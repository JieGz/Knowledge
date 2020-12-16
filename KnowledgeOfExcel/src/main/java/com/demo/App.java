package com.demo;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.google.common.collect.Lists;

import java.util.*;

public class App {
    private static List<String> name = Lists.newArrayList("郭芬吟", "許瑞瓊", "李國華", "林君怡"
            , "林欣宜", "張容瑄", "劉怡蘭", "陳雅如", "張詠璧");

    private static Map<String, List<Record>> map = new HashMap<>();
    private static Map<String, List<Record>> subListMap = new HashMap<>();

    private static Map<String, Integer> eachMemberDispatchNumber = new HashMap<>();
    private static Map<String, Integer> readEachMemberDispatchNumber = new HashMap<>();

    private static Map<String, List<Record>> totalSubSourceMap = new HashMap<>();

    public static void main(String[] args) {
        List<Record> questions = readExcel("TW Account Owner PROD.xlsx");
        System.out.println(questions.size());

        //1.分別統計需要分配和不需要分配的記錄
        for (Record question : questions) {
            if (!name.contains(question.getOwnerName())) {
                subListMap.computeIfAbsent(question.getTwListSubSource(), v -> Lists.newArrayList()).add(question);
            } else {
                map.computeIfAbsent(question.getOwnerName() + question.getTwListSubSource(), v -> Lists.newArrayList()).add(question);
            }

            totalSubSourceMap.computeIfAbsent(question.getTwListSubSource(), v -> Lists.newArrayList()).add(question);
        }

        subListMap.forEach((subSource, list) -> {
            eachMemberDispatchNumber.clear();
            readEachMemberDispatchNumber.clear();

            //2.计算每一次所有人需要分配的条数
            int eachMember = totalSubSourceMap.get(subSource).size() / 9;
            int yu = totalSubSourceMap.get(subSource).size() % 9;
            for (int i = 0; i < name.size() || yu > 0; i++) {
                int nextYu = disYu % name.size();
                if (yu > 0 && i == nextYu) {
                    ++disYu;
                    eachMemberDispatchNumber.put(name.get(i), eachMember + 1);
                    yu--;
                } else if (yu > 0 && i > nextYu) {
                    ++disYu;
                    eachMemberDispatchNumber.put(name.get(nextYu), eachMember + 1);
                    yu--;
                } else {
                    eachMemberDispatchNumber.put(name.get(i), eachMember);
                }
            }

            eachMemberDispatchNumber.forEach((name, total) -> {
                map.computeIfAbsent(name + subSource, v -> Lists.newArrayList());
                readEachMemberDispatchNumber.put(name, total - map.get(name + subSource).size());
            });

            //3.开始分配
            Collections.shuffle(list);
            for (Record question : list) {
                for (String ownerName : name) {
                    final Integer size = readEachMemberDispatchNumber.get(ownerName);
                    if (size > 0) {
                        question.setOwnerName(ownerName);
                        map.computeIfAbsent(question.getOwnerName() + subSource, v -> Lists.newArrayList()).add(question);
                        readEachMemberDispatchNumber.put(ownerName, size - 1);
                        break;
                    }
                }
            }
        });

        //4.最终分配的结果
        questions.clear();

        map.forEach((k, v) -> {
            questions.addAll(v);
        });

        System.out.println(questions.size() + "  " + disYu);

        //5.写Excel
        EasyExcel.write("out.xlsx", Record.class).sheet("Luke模板").doWrite(questions);
    }

    private static int disYu = 0;

    /**
     * 将Excel表格中的题目,转换成一个 List<Record>
     *
     * @param excelFileName Excel文件
     * @return List<ItBrainHoleQuestion>
     */
    private static List<Record> readExcel(String excelFileName) {
        final List<Record> rows = new ArrayList<>();
        EasyExcel.read(excelFileName, Record.class, new AnalysisEventListener<Record>() {
            @Override
            public void invoke(Record data, AnalysisContext context) {
                rows.add(data);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {

            }
        }).sheet()
                // 这里可以设置1，因为头就是一行。如果多行头，可以设置其他值。不传入也可以，因为默认会根据DemoData 来解析，他没有指定头，也就是默认1行
                .headRowNumber(1).doRead();
        return rows;
    }
}
