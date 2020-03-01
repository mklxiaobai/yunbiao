package com.hengyi.yunbiao.service;

import com.hengyi.yunbiao.bean.MainDataTest;

import java.util.List;

public interface MainTestService {
    List<MainDataTest> selecttest() throws Exception;
    List<MainDataTest> selecttestNew() ;

    String updatetest(MainDataTest mainDataTest);
}
