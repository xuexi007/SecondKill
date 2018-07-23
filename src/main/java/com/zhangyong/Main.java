package com.zhangyong;

import java.util.HashMap;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/23 7:19
 */
public class Main {
    private static HashMap<Integer, Integer> map = new HashMap<>(2, 0.75f);

    public static void main(String[] args) {
        map.put(5, 55);
        new Thread("Thread-1") {
            public void run() {
                map.put(1, 11);
                System.out.println(map);
            }
        }.start();
        new Thread("Thread-2") {
            public void run() {
                map.put(2, 22);
                System.out.println(map);
            }
        }.start();
    }
}
