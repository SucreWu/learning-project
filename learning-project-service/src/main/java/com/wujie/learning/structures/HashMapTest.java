package com.wujie.learning.structures;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @Description:
 * @Auther: wujie
 * @Date: 2019/10/11 11:58
 * @version: V1.0
 */
public class HashMapTest {
    public static void main(String[] args) {

        Map<String, String> map = new HashMap<String, String>();
        map.put("aaa", "z");
        map.put("ddd", "z");
        map.put("bbb", "z");

        ArrayList<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (Map.Entry<String, String> mapping :list){
            System.out.println(mapping.getKey());
        }

    }

}
