package com.zhangyong;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

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
public class SecondKillApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecondKillApplication.class, args);
    }
}
