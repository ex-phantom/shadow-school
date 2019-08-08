package com.shadow.ucenter.service.impl;

import com.shadow.ucenter.entity.Member;
import com.shadow.ucenter.mapper.MemberMapper;
import com.shadow.ucenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-08
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public Integer countRegisterByDay(String day) {

        return baseMapper.selectRegisterCount(day);
    }
}
