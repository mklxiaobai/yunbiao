package com.hengyi.yunbiao;

//import com.hengyi.yunbiao.util.JedisUtil;
import com.hengyi.yunbiao.redis.RedisService;
import com.hengyi.yunbiao.util.MD5Util;
import com.hengyi.yunbiao.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.sound.midi.Soundbank;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@SpringBootTest
class YunbiaoApplicationTests {
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisService redisService;
    @Autowired
    JedisPool jedisPool;

    @Test
    void contextLoads() throws Exception {
        redisUtil.set("20182018","这是一条测试数据", 1);
        Long resExpire = redisUtil.expire("20182018", 60, 1);//设置key过期时间
        log.info("resExpire="+resExpire);
        String res = redisUtil.get("20182018", 1);
        System.out.println(res);

    }

    @Test
    public void testJedisPool(){
        Jedis jedis = new Jedis("121.199.37.8",6379);
        //设置密码
        jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
    }

    @Test
    public void testJedisPool1(){
        HashMap<String, String> map = new HashMap<>();
        map.put("整数","zhengshu");
        map.put("小数","xiaoshu");
        map.put("字符串","str");
        map.put("日期时间","date");
        map.put("是否","shifou");
        String result = redisService.insertEverSheetFieldCommentMap("TMS_接口测试主表", map);
        System.out.println(result);
    }
}
