package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.AddressLib;

import java.util.List;

public interface YunbiaoTestService {

    public List<AddressLib> selectTmsAdressTest(String preAddressNumber, String addresShierarchy) throws Exception;
}
