package org.celery;

import org.celery.common.constant.LauncherConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springblade.core.launch.BladeApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created on 2021/10/24
 *
 * @author luoqc
 */
@EnableConfigurationProperties
@EnableScheduling
@SpringBootApplication
@ComponentScans(value = {
        @ComponentScan("org.springblade")
})
@MapperScan("org.celery.**.mapper")
public class CeleryApp {
    public static void main(String[] args) {
        BladeApplication.run(LauncherConstant.APPLICATION_NAME, CeleryApp.class, args);
    }
}
