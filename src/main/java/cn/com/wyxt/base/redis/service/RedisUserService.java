package cn.com.wyxt.base.redis.service;

public interface RedisUserService {
    //@CachePut(value ="momou", key = "#p0")
    String set(String key);
    //@Cacheable(value ="momou",key = "#p0")
    String get(String key);
}
