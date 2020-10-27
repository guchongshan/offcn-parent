package com.offcn.user.controller;

import com.offcn.response.AppResponse;
import com.offcn.user.bean.TMember;
import com.offcn.user.bean.TMemberAddress;
import com.offcn.user.bean.vo.resp.UserRespVo;
import com.offcn.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import java.util.List;

/**
 * @author 胡长生
 * @create 2020-10-23 17:20
 */
@RestController
@RequestMapping("/userInfo")
@Api(tags = "用户管理")
public class UserInfoController {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("用户基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required = true)
    })
    @GetMapping("/findUser/{id}")
    public AppResponse<UserRespVo> findUser(@PathVariable("id") Integer id){
        TMember member = userService.findTmemberById(id);
        UserRespVo vo = new UserRespVo();
        BeanUtils.copyProperties(member,vo);

        return AppResponse.ok(vo);
    }

    @ApiOperation("获取用户地址")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "访问令牌",name = "accessToken",required = true)
    })
    @GetMapping("/findAddressList")
    public AppResponse<List<TMemberAddress>> findAddressList(String accessToken){
        String memberId = stringRedisTemplate.opsForValue().get(accessToken);
        if(StringUtils.isEmpty(memberId)) {
            return AppResponse.fail(null);
        }
        List<TMemberAddress> addressList = userService.findAddressList(Integer.parseInt(memberId));
        return AppResponse.ok(addressList);
    }
}
