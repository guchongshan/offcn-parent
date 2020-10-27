package com.offcn.order.vo.req;

import lombok.Data;
import lombok.ToString;

/**
 * @author 胡长生
 * @create 2020-10-26 15:10
 */
@Data
@ToString
public class OrderInfoSubmit {

    private String accessToken;
    private Integer projectid;//项目ID
    private Integer returnid;//回报ID
    private Integer rtncount;//回报数量
    private String address;//收货地址
    private String invoice;//是否开发票 0 - 不开发票， 1 - 开发票
    private String invoictitle;//发票名头
    private String remark;//备注


}
