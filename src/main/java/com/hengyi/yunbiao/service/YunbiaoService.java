package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.Pallet;

/**
 * @author liuyuan
 * @create 2019-12-13 10:30
 * @description
 **/
public interface YunbiaoService {
    Pallet getPalletCode(String codeNumber);

    Pallet updatePallet(Pallet pallet);

    Pallet bindPalletCode(Pallet pallet);

}
