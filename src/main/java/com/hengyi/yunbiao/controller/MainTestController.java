package com.hengyi.yunbiao.controller;

import com.hengyi.yunbiao.bean.MainDataTest;
import com.hengyi.yunbiao.service.MainTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class MainTestController {
    @Autowired
    MainTestService mainTestService;

    @GetMapping("/selecttest")
    public List<MainDataTest> selecttest() throws Exception {
        List<MainDataTest> mainDataTests=mainTestService.selecttest();
        return mainDataTests;
    }
    @GetMapping("/selecttestnew")
    public List<MainDataTest> selecttestnew() throws Exception {
        List<MainDataTest> mainDataTests=mainTestService.selecttestNew();
        return mainDataTests;
    }
    @PutMapping("/updatetest")
    public String updatetest(@RequestBody MainDataTest mainDataTest){
        String state= mainTestService.updatetest(mainDataTest);
        return state;
    }
}
