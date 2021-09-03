package com.Stream;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class StreamDemo {
    public static void main(String[] args) {
        Stream.of(1,2,3,4,5,6,7,8,9)
                .parallel()
                .reduce((a,b)->{
                    System.out.println(String.format("%s: %d + %d = %d",Thread.currentThread().getName(),a,b,a+b));
                    return a+b;
                }).ifPresent(System.out::println);

    }
}
