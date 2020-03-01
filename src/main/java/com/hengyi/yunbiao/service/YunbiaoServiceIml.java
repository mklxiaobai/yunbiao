package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.Pallet;
import com.hengyi.yunbiao.util.YunbiaoUtil;
import loa.biz.LOAFormDataObject;
import loa.biz.LOAFormList;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuyuan
 * @create 2019-12-13 10:34
 * @description
 **/
@Service
public class YunbiaoServiceIml implements YunbiaoService {

    @Override
    public Pallet getPalletCode(String codeNumber) {
        Pallet pallet=new Pallet();
        LOAFormDataObject object = YunbiaoUtil.getObject("产品码单3", codeNumber, "新码单号");
        if (object!=null){
            Object palletCode=YunbiaoUtil.getField(object,"托盘编号");
            Object userName=YunbiaoUtil.getField(object,"托盘绑定工号");
            Object code=YunbiaoUtil.getField(object,"新码单号");
            if (palletCode!=null&&!palletCode.equals("null")){
                pallet.setPalletCode((String) palletCode);
            }
            if (userName!=null&&!userName.equals("null")){
                pallet.setUserName((String) userName);
            }
            if (code!=null&&!code.equals("null")){
                pallet.setCodeNumber((String) code);
            }
        }
        return pallet;
    }

    @Override
    public Pallet updatePallet(Pallet pallet) {
        Map<String,String> map=new HashMap<>();
        LOAFormDataObject object = YunbiaoUtil.getObject("产品码单3", pallet.getCodeNumber(), "新码单号");
        map.put("托盘编号",pallet.getPalletCode());
        map.put("托盘绑定工号",pallet.getUserName());
        YunbiaoUtil.saveObject(object,map);
//        try {
//            object.save();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return pallet;
    }

    @Override
    public Pallet bindPalletCode(Pallet pallet) {
        Map<String,String> map=new HashMap<>();
        map.put("新码单号",pallet.getCodeNumber());
        map.put("托盘编号",pallet.getPalletCode());
        map.put("托盘绑定工号",pallet.getUserName());
        YunbiaoUtil.createObject("产品码单3",map);
        return pallet;
    }
}
