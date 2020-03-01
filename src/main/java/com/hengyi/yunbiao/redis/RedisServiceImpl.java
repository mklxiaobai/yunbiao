package com.hengyi.yunbiao.redis;

import com.hengyi.yunbiao.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisUtil redisUtil;

    @Override
    public String insertEverSheetFieldCommentMap(String tableName, Map<String, String> fieldComment) {
        if(redisUtil.exists(tableName)==true){
            return "此表名已存在";
        }
        return redisUtil.hmset(tableName,fieldComment,1,2000000000);
    }
}
