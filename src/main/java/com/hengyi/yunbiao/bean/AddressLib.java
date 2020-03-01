package com.hengyi.yunbiao.bean;

import lombok.Data;

@Data
public class AddressLib {
    private Integer addressNumber;
    private String addressName;
    private Integer preAddressNumber;
    private Integer addresShierarchy;
    private String addressPath;
    private String isValid;
    private String searchPreAddressNumber;
}
