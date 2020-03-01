package com.hengyi.yunbiao.util;

import loa.biz.*;

import java.util.Map;

/**
 * @author liuyuan
 * @create 2019-12-13 10:35
 * @description
 **/
public class YunbiaoUtil {
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
    public static Object getField(LOAFormDataObject object, String fieldName) {
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

    /***
     * 获取云表对象
     * @param tableName
     * @param fieldValue
     * @param fieldName
     * @return
     */
    public static LOAFormDataObject getObject(String tableName, String fieldValue, String fieldName) {
        LOAFormList formList = getFormList(tableName, fieldValue, fieldName);
        LOAFormDataObject loaFormDataObject;
        int count=0;
        try {
            if (formList != null && formList.count() > 0) {
                loaFormDataObject = vApp.getFormDataObject(tableName, formList.get(0).getObjectID());
                return loaFormDataObject;
            }
            return null;
        } catch (Exception e) {
            try {
                vApp.login("hywsc", "123456");
                if (count<2){//超时时候再次登录调用，不超过两次递归
                    getObject(tableName, fieldValue, fieldName);
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
    public static LOAFormList getFormList(String tableName, String fieldValue, String fieldName) {
        LOAQueryInfo queryInfo = vApp.createQueryInfo();
        LOAFilterItem vFilterItem = queryInfo.getFilterList().add(fieldName);
        LOAFilterExpressionItem item = vFilterItem.getExpressionList().add();
        item.IsAnd = false;
        item.Operator = LOAFilterExpressionItem.FilterOperator.Equal;
        item.Value = fieldValue;
        LOAFormList vObjList = null;
        try {
            vObjList = vApp.getFormList(tableName, queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vObjList;
    }

}
