package cn.com.wyxt;

import cn.com.wyxt.base.email.MailService;
import cn.com.wyxt.base.exception.DiyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;


//@ServletComponentScan 去掉该注解后filter登录验证无效
@EnableAutoConfiguration(
//        exclude = {
//        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
//}
)
@EnableAsync
@EnableCaching //redis 普通缓存
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 7200)// redis 共享session
@SpringBootApplication
@EnableTransactionManagement
public class HuZhuApplication extends SpringBootServletInitializer {


   private static MailService mailService;

    @Autowired
   void setEmail(MailService mailService){
        this.mailService=mailService;
    }



    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HuZhuApplication.class);
    }

    public static void main(String[] args) throws IOException, DiyException {
        SpringApplication.run(HuZhuApplication.class, args);
        //mailService.sendHtmlMail("momojy@vip.qq.com","胡天宁是大佬","说什么都是对的");
    }


}