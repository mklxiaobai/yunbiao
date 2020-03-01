package com.hengyi.yunbiao.redis;

import java.util.Map;

public interface RedisService {
    String insertEverSheetFieldCommentMap(String tableName, Map<String,String> fieldComment);
}
