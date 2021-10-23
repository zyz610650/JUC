package com.juc.conncurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zyz
 * @title:
 * @seq:
 * @address:
 * @idea:
 */
public class map {

    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("1","1");
        map.remove("1","2");
        System.out.println(map.size());
        ConcurrentMap<String,String> map1=new ConcurrentHashMap<>();
        map.put("1","1");
        map.remove("1","2");
        System.out.println(map1.size());

    }

}
