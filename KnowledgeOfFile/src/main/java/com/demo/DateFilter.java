package com.demo;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author jieguangzhi
 * @date 2022-08-15
 */
@Slf4j
public class DateFilter implements FileFilter {

    private final String dayRegex = "20[2-9][0-9](0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[0-1])";
    private final Pattern dayPattern = Pattern.compile(dayRegex);
    private final String hourRegex = "[01][0-9]|2[0-3]";

    private final String hour = "/([01][0-9]|2[0-3])/";
    private final Pattern hourPattern = Pattern.compile(hour);
    private final String minuteRegex = "[0-5][0-9]\\.(log|txt)";

    private final String minute = "[0-5][0-9]";
    private final Pattern minutePattern = Pattern.compile(minute);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    DateTimeFormatter hourFormatter = DateTimeFormatter.ofPattern("yyyyMMddHH");
    DateTimeFormatter minuteFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    DateTimeFormatter replayTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private String replayTime;

    public DateFilter() {
        this(null);
    }

    public DateFilter(String replayTime) {
        this.replayTime = replayTime;
    }

    @Override
    public boolean accept(File file) {
        boolean accept;
        final String name = file.getName();
        final String path = file.getPath();
        if (Pattern.matches(dayRegex, name)) {
            //是一个天的目录
            accept = dayExpired(name, replayTime);
            //if (accept) log.info("{},天目录是否被扫描:{}", path, accept);
        } else if (Pattern.matches(hourRegex, name)) {
            //是一个小时的目录
            accept = hourExpired(path, name, replayTime);
            //if (accept) log.info("{},小时目录是否被扫描:{}", path, accept);
        } else if (Pattern.matches(minuteRegex, name)) {
            //是一个日志文件
            accept = minuteExpired(path, name, replayTime);
            //if (accept) log.info("{},分钟文件是否被扫描:{}", path, accept);
        } else {
            accept = true;
        }
        return accept;
    }

    private boolean dayExpired(final String date, final String replayTime) {
        //将day转成一个LocalDateTime
        final LocalDateTime dataTime = LocalDate.parse(date, formatter).atTime(LocalTime.MIN);
        //和当前的时间进行对比,判断是否在两天内
        LocalDateTime validTime;
        if (Strings.isNullOrEmpty(replayTime)) {
            validTime = LocalDateTime.now().with(LocalTime.MIN);
        } else {
            validTime = LocalDateTime.of(LocalDate.parse(replayTime, replayTimeFormatter), LocalTime.MIN);
        }
        return isValid(dataTime, validTime);
    }

    private boolean hourExpired(final String path, final String hour, final String replayTime) {
        boolean valid = false;
        final Matcher dateMatcher = dayPattern.matcher(path);
        if (dateMatcher.find()) {
            final String date = dateMatcher.group();
            LocalDateTime dataTime = LocalDateTime.parse(date + hour, hourFormatter);
            LocalDateTime validTime;
            if (Strings.isNullOrEmpty(replayTime)) {
                validTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
            } else {
                validTime = LocalDateTime.of(LocalDate.parse(replayTime, replayTimeFormatter), LocalTime.MIN);
            }
            valid = isValid(dataTime, validTime);
        }
        return valid;
    }

    private boolean minuteExpired(final String path, final String minute, final String replayTime) {
        boolean valid = false;
        final Matcher dateMatcher = dayPattern.matcher(path);
        if (dateMatcher.find()) {
            final String date = dateMatcher.group();
            final Matcher hourMatcher = hourPattern.matcher(path);
            if (hourMatcher.find()) {
                final String hour = hourMatcher.group(1);
                final Matcher matcher = minutePattern.matcher(minute);
                if (matcher.find()) {
                    final String min = matcher.group();
                    LocalDateTime dataTime = LocalDateTime.parse(date + hour + min, minuteFormatter);
                    LocalDateTime validTime;
                    if (Strings.isNullOrEmpty(replayTime)) {
                        validTime = LocalDateTime.now().withSecond(0).withNano(0);
                    } else {
                        validTime = LocalDateTime.parse(replayTime, replayTimeFormatter);
                    }
                    valid = isValid(dataTime, validTime);
                }
            }
        }
        return valid;
    }

    private boolean isValid(final LocalDateTime dataTime, final LocalDateTime validTime) {
        return validTime.isBefore(dataTime) || validTime.isEqual(dataTime);
    }
}
