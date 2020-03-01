package com.hengyi.yunbiao.controller;

import com.hengyi.yunbiao.bean.AddressLib;
import com.hengyi.yunbiao.service.TmsAddressService;
import com.hengyi.yunbiao.service.YunbiaoTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/Tms")
public class TmsController {
    @Autowired
    TmsAddressService tmsAddressService;

    @Autowired
    YunbiaoTestService yunbiaoTestService;

    @GetMapping("/selectTmsAdress")
    public AddressLib selectTmsAdress(@RequestParam Long addressNumber){
        AddressLib addressLib=tmsAddressService.selectTmsAdress(addressNumber);
        return addressLib;
    }
    @GetMapping("/selectTmsAdressTest")
    public List<AddressLib> selectTmsAdressTest(@RequestParam String preAddressNumber,@RequestParam String addresShierarchy) throws Exception {
        List<AddressLib> addressLibList=yunbiaoTestService.selectTmsAdressTest(preAddressNumber,addresShierarchy);
        return addressLibList;
    }
}
