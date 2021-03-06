package cn.com.wyxt.base.redis.ctl;



import cn.com.wyxt.base.redis.service.RedisUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    RedisUserService redisUserService;

    @RequestMapping("/addUser")
    public String add(){
        String uu = UUID.randomUUID().toString();
        redisUserService.set(uu);
        return uu;
    }
    @RequestMapping("/getUser")
    public String get(String uu){
        return  redisUserService.get(uu);
    }

}
