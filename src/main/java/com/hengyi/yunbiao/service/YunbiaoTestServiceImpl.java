package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.AddressLib;
import com.hengyi.yunbiao.util.YunbiaoTestUtil;
import com.hengyi.yunbiao.util.YunbiaoUtil;
import loa.biz.LOAApp;
import loa.biz.LOAFilterExpressionItem;
import loa.biz.LOAFormList;
import loa.models.LOADataObject;
import loa.models.LOARawValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class YunbiaoTestServiceImpl implements YunbiaoTestService {


    @Override
    public List<AddressLib> selectTmsAdressTest(String preAddressNumber, String addresShierarchy) throws Exception {
        HashMap<String, String> map = new HashMap<>();
        List<AddressLib> addressLibs = new ArrayList<AddressLib>();
        map.put("上级地址编号",preAddressNumber);
        map.put("地址层级",addresShierarchy);
        LOAFormList vObjList= YunbiaoTestUtil.getObject("TMS_地址库", LOAFilterExpressionItem.FilterOperator.Equal, map);
        for (int i = 0; i < vObjList.getCount(); i++) {
            AddressLib addressLib = new AddressLib();
            Object addressNumber = YunbiaoTestUtil.getField(vObjList.get(i), "地址编号");
            Object addressName= YunbiaoTestUtil.getField(vObjList.get(i),"地址名称");
            Object preAddressNumber1=YunbiaoTestUtil.getField(vObjList.get(i),"上级地址编号");
            Object addresShierarchy1=YunbiaoTestUtil.getField(vObjList.get(i),"地址层级");
            Object addressPath=YunbiaoTestUtil.getField(vObjList.get(i),"路径");
            Object isValid=YunbiaoTestUtil.getField(vObjList.get(i),"是否有效");
            Object searchPreAddressNumber=YunbiaoTestUtil.getField(vObjList.get(i),"查找上级地址编号");
            if (addressNumber!=null&&!addressNumber.equals("null")){
                addressLib.setAddressNumber( (Integer) addressNumber);
            }
            if (addressName!=null&&!addressName.equals("null")){
                addressLib.setAddressName((String) addressName);
            }
            if (preAddressNumber1!=null&&!preAddressNumber1.equals("null")){
                addressLib.setPreAddressNumber((Integer) preAddressNumber1);
            }
            if (addresShierarchy1!=null&&!addresShierarchy1.equals("null")){
                addressLib.setAddresShierarchy((Integer) addresShierarchy1);
            }
            if (addressPath!=null&&!addressPath.equals("null")){
                addressLib.setAddressPath((String) addressPath);
            }
            if (isValid!=null&&!isValid.equals("null")){
                addressLib.setIsValid((String) isValid);
            }
            if (searchPreAddressNumber!=null&&!searchPreAddressNumber.equals("null")){
                addressLib.setSearchPreAddressNumber((String) searchPreAddressNumber);
            }
            addressLibs.add(addressLib);
        }
        return addressLibs;
    }
}
