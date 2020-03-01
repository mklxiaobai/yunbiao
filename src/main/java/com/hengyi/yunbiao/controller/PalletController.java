package com.hengyi.yunbiao.controller;

import com.hengyi.yunbiao.bean.Pallet;
import com.hengyi.yunbiao.service.YunbiaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author liuyuan
 * @create 2019-12-13 9:25
 * @description
 **/
@RestController
@CrossOrigin
@RequestMapping(value = "/palletController")
public class PalletController {
    @Autowired
    private YunbiaoService yunbiaoService;
    /***
     * 根据码单号获取托盘信息
     * @param codeNumber
     * @return
     */
    @RequestMapping(value = "/getPalletCode",method = RequestMethod.GET)
    public Pallet getPalletCode(@RequestParam String codeNumber){
        Pallet pallet=yunbiaoService.getPalletCode(codeNumber);
        return pallet;
    }

    /***
     * 托盘绑定
     * @param pallet
     * @return
     */
    @RequestMapping(value = "/updatePallet",method = RequestMethod.PUT)
    public Pallet updatePallet(@RequestBody Pallet pallet){
      return yunbiaoService.updatePallet(pallet);
    }

    /***
     * 绑定托盘（新增托盘）（废弃）
     * @param pallet
     * @return
     */
    @RequestMapping(value = "/bindPalletCode",method = RequestMethod.POST)
    public Pallet bindPalletCode(@RequestBody Pallet pallet){

        return yunbiaoService.bindPalletCode(pallet);
    }
}
