package com.offcn.user.service.impl;

import com.offcn.user.bean.TMember;
import com.offcn.user.bean.TMemberAddress;
import com.offcn.user.bean.TMemberAddressExample;
import com.offcn.user.bean.TMemberExample;
import com.offcn.user.enums.UserExceptionEnum;
import com.offcn.user.exception.UserException;
import com.offcn.user.mapper.TMemberAddressMapper;
import com.offcn.user.mapper.TMemberMapper;
import com.offcn.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 胡长生
 * @create 2020-10-23 15:41
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TMemberMapper meberMapper;

    @Autowired
    private TMemberAddressMapper memberAddressMapper;

    @Override
    public void registerUser(TMember tMember) {

        //1.检查系统中此手机号是否存在
        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(tMember.getLoginacct());
        long l = meberMapper.countByExample(example);

        if(l > 0) {
            throw new UserException(UserExceptionEnum.LOGINACCT_EXIST);
        }
        //2.手机号未被注册
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(tMember.getUserpswd());
        //设置密码
        tMember.setUserpswd(encode);
        tMember.setUsername(tMember.getLoginacct());
        tMember.setEmail(tMember.getEmail());

        tMember.setAuthstatus("0");
        tMember.setUsertype("0");
        tMember.setAccttype("2");

        System.out.println("插入数据："+tMember.getLoginacct());

        meberMapper.insertSelective(tMember);
    }

    @Override
    public TMember login(String username, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        TMemberExample example = new TMemberExample();
        example.createCriteria().andLoginacctEqualTo(username);
        List<TMember> list = meberMapper.selectByExample(example);

        if(list != null && list.size() > 0) {
            TMember member = list.get(0);
            boolean matches = encoder.matches(password, member.getUserpswd());
            return matches?member:null;
        }
        return null;
    }

    @Override
    public TMember findTmemberById(Integer id) {

        return meberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TMemberAddress> findAddressList(Integer memberId) {
        TMemberAddressExample example = new TMemberAddressExample();
        example.createCriteria().andMemberidEqualTo(memberId);
        return memberAddressMapper.selectByExample(example);
    }
}
