package com.offcn.user.bean.vo.req;

/**
 * @author 胡长生
 * @create 2020-10-22 22:32
 */

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户注册需要提交的数据
 */
@ApiModel
@Data
public class UserRegisterVo {

    @ApiModelProperty("手机号")
    private String loginacct;

    @ApiModelProperty("密码")
    private String userpswd;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("验证码")
    private String code;

}
