package com.zhangyong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * <p>ClassName:SecondKillApplication</p>
 * <p>Description:	</p>
 * <p>Company: www.shopin.net</p>
 *
 * @author zhangyong@shopin.cn
 * @version 1.0
 * @date 2018/6/23 17:58
 */
@SpringBootApplication
@MapperScan(value = "com.zhangyong.persistence")
public class SecondKillApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SecondKillApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(SecondKillApplication.class, args);
    }
}


