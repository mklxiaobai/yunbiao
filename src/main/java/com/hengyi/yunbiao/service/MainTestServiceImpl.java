package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.MainDataTest;
import com.hengyi.yunbiao.common.EverSheetFieldCommentMap;
import com.hengyi.yunbiao.util.YunbiaoTestUtil;
import com.hengyi.yunbiao.util.YunbiaoUtil2;
import loa.biz.LOAFilterExpressionItem;
import loa.biz.LOAFormList;
import loa.models.LOADataObject;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainTestServiceImpl implements MainTestService{
    @Override
    public List<MainDataTest> selecttest() throws Exception {
        HashMap<String, String> map = new HashMap<>();
        List<MainDataTest> mainDataTests=new ArrayList<>();
        LOAFormList vObjList= YunbiaoTestUtil.getObject("TMS_接口测试主表", LOAFilterExpressionItem.FilterOperator.Equal, map);
        for (int i = 0; i < vObjList.getCount(); i++){
            MainDataTest mainDataTest = new MainDataTest();
            Object shengshu = YunbiaoTestUtil.getField(vObjList.get(i), "整数");
            Object xiaoshu = YunbiaoTestUtil.getField(vObjList.get(i), "小数");
            Object str = YunbiaoTestUtil.getField(vObjList.get(i), "字符串");
            Object date = YunbiaoTestUtil.getField(vObjList.get(i), "日期时间");
            Object shifou = YunbiaoTestUtil.getField(vObjList.get(i), "是否");
            if (shengshu!=null&&!shengshu.equals("null")){
                mainDataTest.setZhengshu( (Integer) shengshu);
            }
            if (xiaoshu!=null&&!xiaoshu.equals("null")){
                mainDataTest.setXiaoshu((Double) xiaoshu);
            }
            if (str!=null&&!str.equals("null")){
                mainDataTest.setStr((String) str);
            }
            if (date!=null&&!date.equals("null")){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr=(String) date;
                System.out.println(dateStr);
                mainDataTest.setDate(simpleDateFormat.parse(dateStr));
            }
            if (shifou!=null&&!shifou.equals("null")){
                mainDataTest.setShifou((boolean) shifou);
            }
            mainDataTests.add(mainDataTest);
        }

        return mainDataTests;
    }

    @Override
    public List<MainDataTest> selecttestNew() {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<MainDataTest> list = YunbiaoUtil2.getObject("TMS_接口测试主表", MainDataTest.class, LOAFilterExpressionItem.FilterOperator.Equal, map);
        return list;
    }

    @Override
    public String updatetest(MainDataTest mainDataTest) {
//        HashMap<String, String> map = new HashMap<>();
//        map.put("整数",String.valueOf(mainDataTest.getZhengshu()));
//        LOAFormList vObjList = YunbiaoTestUtil.getObject("TMS_接口测试主表", LOAFilterExpressionItem.FilterOperator.Equal, map);
//        for(int i = 0; i < vObjList.getCount(); i++){
//            Map<String,String> map=new HashMap<>();
//            LOADataObject loaDataObject = vObjList.get(i);
//            map.put("整数",mainDataTest.getZhengshu());
//            map.put("小数",mainDataTest.getXiaoshu());
//            map.put("字符串",mainDataTest.getStr());
//            map.put("日期时间",mainDataTest.getDate());
//            YunbiaoTestUtil.saveObject(loaDataObject,map);
//        }
        return null;
    }
}
