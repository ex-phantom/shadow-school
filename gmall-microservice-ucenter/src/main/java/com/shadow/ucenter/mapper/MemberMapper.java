package com.shadow.ucenter.mapper;

import com.shadow.ucenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-08
 */
@Repository
public interface MemberMapper extends BaseMapper<Member> {

    Integer selectRegisterCount(@Param("day") String day);
}
