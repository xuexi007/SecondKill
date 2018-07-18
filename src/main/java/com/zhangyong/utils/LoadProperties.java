package com.zhangyong.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * <p>ClassName:  </p>
 * <p>Description: </p>
 * <p>Company: http://www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0.0
 * @date 2018/7/18 7:24
 */
public class LoadProperties {
    public static String readValue(String key) {
        String fileName = "/application.properties";
        Properties props = new Properties();
        InputStream in = null;
        String value = "";
        try {
            in = LoadProperties.class.getResourceAsStream(fileName);
            props.load(in);
            value = props.getProperty(key);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return value;
    }
}
