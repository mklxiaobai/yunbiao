package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.AddressLib;
import com.hengyi.yunbiao.bean.Pallet;
import com.hengyi.yunbiao.util.YunbiaoUtil;
import loa.biz.LOAFormDataObject;
import org.springframework.stereotype.Service;

@Service
public class TmsAddressServiceImpl implements TmsAddressService {

    @Override
    public AddressLib selectTmsAdress(Long addressNumber) {
        AddressLib addressLib = new AddressLib();
        String addressNumberStr= String.valueOf(addressNumber) ;
        LOAFormDataObject object = YunbiaoUtil.getObject("TMS_地址库", addressNumberStr, "地址编号");
        if (object!=null){
            Object addressNumber1=YunbiaoUtil.getField(object,"地址编号");
            Object addressName=YunbiaoUtil.getField(object,"地址名称");
            Object preAddressNumber=YunbiaoUtil.getField(object,"上级地址编号");
            Object addresShierarchy=YunbiaoUtil.getField(object,"地址层级");
            Object addressPath=YunbiaoUtil.getField(object,"路径");
            Object isValid=YunbiaoUtil.getField(object,"是否有效");
            Object searchPreAddressNumber=YunbiaoUtil.getField(object,"查找上级地址编号");
            if (addressNumber1!=null&&!addressNumber1.equals("null")){
                addressLib.setAddressNumber( (Integer) addressNumber1);
            }
            if (addressName!=null&&!addressName.equals("null")){
                addressLib.setAddressName((String) addressName);
            }
            if (preAddressNumber!=null&&!preAddressNumber.equals("null")){
                addressLib.setPreAddressNumber((Integer) preAddressNumber);
            }
            if (addresShierarchy!=null&&!addresShierarchy.equals("null")){
                addressLib.setAddresShierarchy((Integer) addresShierarchy);
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
        }
        return addressLib;
    }

}
