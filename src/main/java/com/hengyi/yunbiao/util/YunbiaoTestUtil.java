package com.hengyi.yunbiao.util;
import loa.biz.*;
import loa.models.LOADataObject;
import loa.models.LOARawValue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class YunbiaoTestUtil {
    static String appName = "e7cdbb3b-7f9f-42bd-910c-f4091c6b12a2";
    static String appKey = "360057f7-9295-4e77-afcd-aea3384906cf";
    public final static LOAApp vApp = LOAApp.getInstance();

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

    /***
     * 创建云表对象
     * @param tableName
     * @param map
     */
    public static void createObject(String tableName,Map<String,String> map){
        LOAFormDataObject object;
        try {
            object = vApp.newFormDataObject(tableName);
            saveObject(object,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * 保存到云表中
     * @param object
     * @param map
     */
    public static void saveObject(LOAFormDataObject object, Map<String, String> map) {
        int count=0;
        for (String s : map.keySet()) {
            setFiled(object, s, map.get(s));
        }
        try {
            object.save();
        } catch (Exception e) {
            try {
                vApp.login("hywsc", "123456");
                if (count<2){//超时时候再次登录调用，不超过两次递归
                    saveObject(object, map);
                }
                count++;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void saveObjectNew(LOAFormDataObject object, Map<String, String> map) {
        int count=0;
        for (String s : map.keySet()) {
            setFiled(object, s, map.get(s));
        }
        try {
            object.save();
            for(String s:map.keySet()){
                Object o = object.getRawValue(s).get();
            }
        } catch (Exception e) {
            try {
                vApp.login("hywsc", "123456");
                if (count<2){//超时时候再次登录调用，不超过两次递归
                    saveObject(object, map);
                }
                count++;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }


    /***
     * 获取云表对象
     * @param tableName
     * @param fieldValue
     * @param fieldName
     * @return
     */
    public static LOAFormList getObject(String tableName, LOAFilterExpressionItem.FilterOperator filterOperator, HashMap<String,String> queryCondition) {
        LOAFormList formList = getFormList(tableName, filterOperator,queryCondition);
        int count=0;
        try {
            if (formList != null && formList.count() > 0) {
                return formList;
            }
            return null;
        } catch (Exception e) {
            try {
                vApp.login("hywsc", "123456");
                if (count<2){//超时时候再次登录调用，不超过两次递归
                    getObject(tableName, filterOperator, queryCondition);
                }
                count++;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return null;
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
