package com.hengyi.yunbiao.util;

import com.hengyi.yunbiao.bean.MainDataTest;
import loa.biz.*;
import loa.models.LOADataObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class YunbiaoUtil2 {
    static String appName = "e7cdbb3b-7f9f-42bd-910c-f4091c6b12a2";
    static String appKey = "360057f7-9295-4e77-afcd-aea3384906cf";
    public final static LOAApp vApp = LOAApp.getInstance();
    private static RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil){
        this.redisUtil=redisUtil;
    }

    static {
        vApp.init("http://wms.hengyi.com:8400/10001/openapi/1.0", appName, appKey, true);
        try {
            vApp.login("hywsc", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /***
     *获取字段值
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getField(LOADataObject object, String fieldName) {
        Object o = null;
        try {
            o = object.getRawValue(fieldName).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

    /***
     * 设置字段值
     * @param object
     * @param fieldName
     * @param fieldValue
     */
    public static void setFiled(LOAFormDataObject object, String fieldName, String fieldValue) {
        try {
            object.addRawValue(fieldName, fieldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static<T> ArrayList<T> getObject(String tableName,Class<T> tableClass, LOAFilterExpressionItem.FilterOperator filterOperator, HashMap<String,String> queryCondition) {
        LOAFormList formList = getFormList(tableName, filterOperator,queryCondition);
        int count=0;
        try {
            if (formList != null && formList.count() > 0) {
                ArrayList<T> ts = assignmentEachFiled(tableName, formList, tableClass);
                return ts;
            }
            return null;
        } catch (Exception e) {
            try {
                vApp.login("hywsc", "123456");
                if (count<2){//超时时候再次登录调用，不超过两次递归
                    getObject(tableName, tableClass,filterOperator, queryCondition);
                }
                count++;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
    }

    private static <T> ArrayList<T> assignmentEachFiled(String tableName, LOAFormList formList,Class<T> tableClass) throws Exception {
        Map<String, String> filedMap = redisUtil.hgetall(tableName, 1);
        ArrayList<T> tlist = new ArrayList<T>();
        HashMap<String, Object> filedValueMap = new HashMap<>();
        try {
            for (int i = 0; i < formList.getCount(); i++){
    //            MainDataTest mainDataTest = new MainDataTest();
                T object = tableClass.newInstance();
                LOADataObject loaDataObject =formList.get(i);
                for(String key: filedMap.keySet()){
                    filedValueMap.put(filedMap.get(key),getField(loaDataObject, key));
                }
                ObjectUtil.setObjectFiledValue(object,filedValueMap);
                tlist.add(object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tlist;
    }

    /***
     * 云表获取表单数据
     * @param tableName 表单名
     * @param fieldValue 查询的字段值
     * @param fieldName 查询的字段名
     * @return
     * @throws Exception
     */
    public static LOAFormList getFormList(String tableName,LOAFilterExpressionItem.FilterOperator filterOperator, HashMap<String,String> queryCondition) {
        LOAQueryInfo queryInfo = vApp.createQueryInfo();
        for(String key:queryCondition.keySet()){
            LOAFilterItem vFilterItem = queryInfo.getFilterList().add(key);
            LOAFilterExpressionItem item = vFilterItem.getExpressionList().add();
            item.IsAnd = true;
            item.Operator = filterOperator;
            item.Value = queryCondition.get(key);
        }
        LOAFormList vObjList = null;
        try {
            vObjList = vApp.getFormList(tableName, queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vObjList;
    }
}
