package com.hengyi.yunbiao.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ObjectUtil {
    /**
     * 获取属性名数组
     * */
    public static String[] getFiledName(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    public static void setObjectFiledValue(Object o, HashMap<String,Object> filedValueMap){
        //循环遍历OaInfoAssess实体中的属性与值
        for (Field field : o.getClass().getDeclaredFields()){
            //设置可以获取私人属性
            field.setAccessible(true);
            try {
                Class type = field.getType();// 得到此属性的类型
                if(type == String.class){
                    //属性名
                    String valueName =field.getName();

                    Object value = filedValueMap.get(valueName);
//                    属性类型
                    String fieldType = field.getGenericType().toString();
                    if(fieldType.equals("class java.lang.String")){
                        field.set(o,String.valueOf(value));
                    }
                    if(fieldType.equals("class java.lang.Integer")){

                        field.set(o,Integer.parseInt(value.toString()));
                    }
                    if(fieldType.equals("class java.lang.Double")){
                        field.set(o,Double.parseDouble(value.toString()));
                    }
                    if(fieldType.equals("class java.lang.Boolean")){
                        field.set(o,Boolean.parseBoolean(value.toString()));
                    }
                    if(fieldType.equals("class java.util.Date")){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        String dateStr=(String) value;
                        field.set(o,simpleDateFormat.parse(dateStr));
                    }
                    //属性值
//                    Object value=  field.get(o);
                    /**
                     * 设置更新后属性的值，此处如不设置则值不会发生改变
                     */
//                     field.set(o,value);
                }
            } catch (IllegalAccessException | ParseException e) {
                e.printStackTrace();
            }
        }
    }

}
