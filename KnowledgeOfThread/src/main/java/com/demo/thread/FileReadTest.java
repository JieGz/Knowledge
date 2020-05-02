package com.demo.thread;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 揭光智
 * @date 2020/04/16
 */
public class FileReadTest {

    public static void main(String[] args) {
        String buildTeam = "F:\\test\\buildTeam\\buildTeam.txt";


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(buildTeam)))) {
            List<String> lines = reader.lines().collect(Collectors.toList());
            for (String line : lines) {
                String[] arr = line.split("-");
                for (int i = 0; i < arr.length; i++) {
                    System.out.println("第" + (i + 1) + "条:" + arr[i]);
                }
                System.out.println("----------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
