package com.shadow.ucenter.service;

import com.shadow.ucenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-08
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterByDay(String day);
}
