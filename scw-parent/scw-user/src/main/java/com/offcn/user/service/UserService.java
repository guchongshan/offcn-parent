package com.offcn.user.service;

import com.offcn.user.bean.TMember;
import com.offcn.user.bean.TMemberAddress;

import java.util.List;

/**
 * @author 胡长生
 * @create 2020-10-23 15:35
 */
public interface UserService {

    public void registerUser(TMember tMember);

    public TMember login(String username,String password);

    //根据用户id，获取用户信息
    public TMember findTmemberById(Integer id);

    /**
     * 获取用户对应地址
     */
    public List<TMemberAddress> findAddressList(Integer member);
}
