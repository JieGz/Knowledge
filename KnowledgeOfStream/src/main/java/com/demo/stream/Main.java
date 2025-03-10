package com.demo.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 揭光智
 * @date 2019/02/07
 */
public class Main {

    public static void main(String[] args) {
        //  groupBY2Set();

        // test20210915();

        //  limit();

        // test20220117();

        //getMates().forEach(Main::test20220118);
        //System.out.println(map);
        test20230526();
    }

    public static void test() {
        List<DatabaseInfo> databaseInfos = new ArrayList<>();

        final DatabaseInfo info = new DatabaseInfo();
        info.setDbname("d1");
        List<String> ids = Lists.newArrayList("t1", "t2", "t3");
        info.setTables(ids);
        info.setPermissions(Lists.newArrayList(1));
        databaseInfos.add(info);

        final DatabaseInfo info2 = new DatabaseInfo();
        info2.setDbname("d1");
        List<String> ids2 = Lists.newArrayList("t3", "t1", "t2");
        info2.setTables(ids2);
        info2.setPermissions(Lists.newArrayList(2));
        databaseInfos.add(info2);

        final DatabaseInfo info3 = new DatabaseInfo();
        info3.setDbname("d1");
        List<String> ids3 = Lists.newArrayList("t1", "t2");
        info3.setTables(ids3);
        info3.setPermissions(Lists.newArrayList(3));
        databaseInfos.add(info3);

        // System.out.println(databaseInfos);

        Map<List<String>, DatabaseInfo> map = new HashMap<>();

        for (DatabaseInfo databaseInfo : databaseInfos) {
            final Set<List<String>> lists = map.keySet();
            final List<String> tables = databaseInfo.getTables();
            boolean sampleTables = false;
            for (List<String> list : lists) {
                if (list.size() == tables.size() && list.containsAll(tables)) {
                    final List<Integer> permissions = map.get(list).getPermissions();
                    permissions.addAll(databaseInfo.getPermissions());
                    sampleTables = true;
                    break;
                }
            }
            if (!sampleTables) {
                map.put(tables, databaseInfo);
            }
        }
        System.out.println(map);

    }


    private static void learnStream() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);
        lists.add(4);
        lists.add(5);
        lists.add(6);

        Optional<Integer> sum = lists.parallelStream().reduce((a, b) -> a + b);
        //21
        sum.ifPresent(integer -> System.out.println("list的总和为:" + integer));
        /*<====> lists.stream().reduce((a, b) -> a + b).ifPresent(System.out::println);*/


        Integer sum2 = lists.stream().reduce(0, (a, b) -> a + b);
        //21
        System.out.println("list的总和为:" + sum2);

        Optional<Integer> product = lists.stream().reduce((a, b) -> a * b);
        //720
        product.ifPresent(integer -> System.out.println("list的积为:" + integer));

        Integer product2 = lists.parallelStream().reduce(1, (a, b) -> a * b);
        //720
        System.out.println("list的积为:" + product2);
    }


    private static void reduce3th() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);

        Integer product = lists.parallelStream().reduce(1, (a, b) -> a * b * 2, (a, b) -> a * b * 2);
        //48
        System.out.println("reduce3th:" + product);
    }

    private static void limit() {
        List<Integer> lists = new ArrayList<>();
        lists.add(1);
        lists.add(2);
        lists.add(3);

        System.out.println(lists.stream().limit(-1).collect(Collectors.toList()));

    }

    /**
     * 将多个元素,收集成一个{@link List}
     */
    private static void collectEle() {
        List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(list);
    }


    private static void groupBY() {
        Set<ClassMate> mates = getMates();
        Map<String, List<ClassMate>> map = mates.stream().collect(Collectors.groupingBy(ClassMate::grade));
        System.out.println(map);
    }

    private static void groupBY2Set() {
        Set<ClassMate> mates = getMates();
        Map<String, Set<ClassMate>> map = mates.stream().collect(Collectors.groupingBy(ClassMate::grade, Collectors.toSet()));
        System.out.println(map);
    }

    private static void multipleGroupingBy() {
        Set<ClassMate> mates = getMates();
        Map<String, Map<String, List<ClassMate>>> map = mates.stream().collect(Collectors.groupingBy(ClassMate::grade, Collectors.groupingBy(ClassMate::clazz)));
        System.out.println(map);
    }


    private static void groupingByAndJoining() {
        Set<ClassMate> mates = getMates();
        Map<String, Map<String, String>> map = mates.stream().collect(Collectors.groupingBy(ClassMate::grade, Collectors.groupingBy(ClassMate::clazz, Collectors.mapping(ClassMate::name, Collectors.joining("-")))));
        System.out.println(map);
    }

    private static void groupingByCustomer() {
        Set<ClassMate> mates = getMates();
        Map<String, Set<String>> map = mates.stream().collect(Collectors.groupingBy(ClassMate::grade, Collectors.mapping(ClassMate::name, Collectors.toSet())));
        System.out.println(map);
    }

    private static void selectCourseType() {
        Set<Student> students = getStudent();
        students.stream().map(Student::getCourse).flatMap(Collection::stream).collect(Collectors.toSet()).forEach(System.out::println);
        System.out.println();
        //Function<Student, List<String>> course = student -> student.getCourse();
        Function<Student, List<String>> course = Student::getCourse;
        students.stream().map(course).collect(Collectors.toSet()).forEach(System.out::println);
        //output:
        //[history, math, geography]
        //[biology, science, english]
        //[economics, chinese, math]

        System.out.println();
        students.stream().flatMap(student -> student.getCourse().stream()).collect(Collectors.toSet()).forEach(System.out::println);

    }

    private static void functionInterface() {
        Set<Student> students = getStudent();
        //Luke-VIP-SSP-No.1
        Function<Student, String> sspUser = student -> student.getName() + "-SSP";
      //  Function<String, Student> before = name -> new Student(name + "-VIP", "五年级", Arrays.asList("math", "science"));
        Function<String, String> after = name -> name + "-No.1";
      //  String name = sspUser.compose(before).andThen(after).apply("Luke");
        //Output:
        //Luke-VIP-SSP-No.1

        //Map<String, Student> map = students.stream().collect(Collectors.toMap(Student::getName, Function.identity()));
        //System.out.println(map);


        //Student xiaoQiang = Student.builder().name("小强").grade("三年级").course(Arrays.asList("economics", "chinese", "math")).build();
        //Student xiaoHao = Student.builder().name("小豪").grade("四年级").course(Arrays.asList("biology", "science", "english")).build();
        //现在有要求,小智的年级和小强的一样,但是上的课程和小豪一样
        Function<String, Student> nameToStudent = userName -> Student.builder().name(userName).build();
        Function<Student, Student> gradeFunction = student -> {
            student.setGrade("三年级");
            return student;
        };
        Function<Student, Student> courseFunction = student -> {
            student.setCourse(Arrays.asList("economics", "chinese", "math"));
            return student;
        };
        Student student = gradeFunction.compose(nameToStudent).andThen(courseFunction).apply("Luke");
        System.out.println(student);

    }

    private static void test20210621() {
        Map<String, Student> collect = getStudent().stream().collect(Collectors.toMap(Student::getName, Function.identity(), (student, student2) -> student2));
        System.out.println(collect);
    }

    private static void predicateInterface() {
        Set<Student> students = getStudent();
        Predicate<Student> namePredicate = student -> Objects.equals(student.getName(), "小强");
        students.stream().filter(namePredicate).collect(Collectors.toSet()).forEach(System.out::println);
    }

    private static void test20210915() {
        Map<String, List<ClassMate>> collect = getMates().stream().collect(Collectors.groupingBy(ClassMate::grade));
        List<ClassMateTree> tree = tree(collect);
        System.out.println(tree);
    }

    private static List<ClassMateTree> tree(Map<String, List<ClassMate>> collect) {
        List<ClassMateTree> trees = new ArrayList<>();
        collect.forEach((product, classMates) -> {
            trees.add(doTree(product, classMates));
        });
        return trees;
    }

    private static ClassMateTree doTree(String flag, List<ClassMate> classMates) {
        ClassMateTree tree = new ClassMateTree();
        tree.setName(flag);
        Map<String, List<ClassMate>> map = classMates.stream().collect(Collectors.groupingBy(ClassMate::clazz));
        map.forEach((clazz, mates) -> {
            ClassMateTree node = new ClassMateTree();
            node.setName(clazz);
            Set<String> collect = classMates.stream().map(ClassMate::name).collect(Collectors.toSet());
            for (String s : collect) {
                ClassMateTree classMateTree = new ClassMateTree();
                classMateTree.setName(s);
                classMateTree.setV("true");
                node.getChildren().add(classMateTree);
            }
            tree.getChildren().add(node);
        });
        return tree;
    }


    private static Set<ClassMate> getMates() {
        return Stream.of(ClassMate.builder().grade("五级年").clazz("二班").name("小明").sex("男").build(), ClassMate.builder().grade("三级年").clazz("二班").name("小强").sex("男").build(), ClassMate.builder().grade("四级年").clazz("三班").name("小美").sex("女").build(), ClassMate.builder().grade("五级年").clazz("三班").name("小花").sex("女").build()).collect(Collectors.toSet());
    }

    private static Set<Student> getStudent() {
        return Stream.of(Student.builder().name("小美").grade("三年级").course(Arrays.asList("history", "math", "geography")).build(), Student.builder().name("小强").grade("三年级").course(Arrays.asList("economics", "chinese", "math")).build(), Student.builder().name("小豪").grade("四年级").course(Arrays.asList("biology", "science", "english")).build(), Student.builder().name("小豪").grade("五年级").course(Arrays.asList("biology", "science", "english")).build()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static Set<Student> getStudentV2() {
        return Stream.of(Student.builder().name("小美").money(100).grade("三年级").course(Arrays.asList("history", "math", "geography")).build(), Student.builder().name("小强").money(10).grade("三年级").course(Arrays.asList("economics", "chinese", "math")).build()).collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static void test20230526() {
        final Set<Student> students = getStudentV2();
        final Student student = students.stream().max(Comparator.comparingInt(Student::getMoney)).get();
        System.out.println(student);
    }

    private static void test20220117() {
        final Set<Student> students = getStudent();
        final Map<String, Set<String>> map = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.mapping(Student::getGrade, Collectors.toSet())));
        System.out.println(map);

        final Map<String, Set<Student>> mapSet = students.stream().collect(Collectors.groupingBy(Student::getName, Collectors.toSet()));
        System.out.println(mapSet);

        final Set<String> names = students.stream().map(Student::getName).collect(Collectors.toSet());
        names.addAll(students.stream().map(Student::getGrade).collect(Collectors.toSet()));
        System.out.println(names);

        final Map<String, Student> listMap = students.stream().collect(Collectors.toMap(Student::getName, Function.identity(), (student, student2) -> student2));
        listMap.putAll(students.stream().collect(Collectors.toMap(Student::getGrade, Function.identity(), (student, student2) -> student2)));
        System.out.println(listMap);
    }

    private static final Map<String, ClassMate> map = new HashMap<>();

    private static void test20220118(ClassMate classMate) {
        ClassMate c = null;
        if (map.containsKey(classMate.grade())) {
            c = map.get(classMate.grade());
        } else {
            c = new ClassMate();
            map.put(classMate.grade(), c);
            c.name(classMate.name());
            c.clazz(classMate.clazz());
            c.grade(classMate.grade());
        }
        c.sex("666");
    }

    private static void testB() {
        System.out.println("这个要Cherry-Pick");
    }
}
