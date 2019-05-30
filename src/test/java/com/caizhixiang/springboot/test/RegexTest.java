package com.caizhixiang.springboot.test;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caizhixiang
 * @description: 测试正则表达式的贪婪模式、懒惰模式、独占模式
 * @Date 2019-05-30 8:39
 * @Version 1.0
 **/
@Slf4j
public class RegexTest {
    public static void main(String[] args) throws InterruptedException {
        String text = "abc";
        String tl = "ab{1,3}c";//贪婪模式
        String ld = "ab{1,3}?c";//懒惰模式
        String dz = "ab{1,3}+c";//独占模式
        log.info("贪婪模式结果:{}",matcher(tl,text));
        log.info("懒惰模式结果:{}",matcher(ld,"abbbc"));
        log.info("独占模式结果:{}",matcher(dz,text));

    }

    private static boolean matcher(String regex, String input) throws InterruptedException {
        long start = System.currentTimeMillis();
        boolean matches = Pattern.compile(regex).matcher(input).matches();
        Thread.sleep(100);
        log.info("耗费时间————" + (System.currentTimeMillis() - start));
        return matches;
    }

}
