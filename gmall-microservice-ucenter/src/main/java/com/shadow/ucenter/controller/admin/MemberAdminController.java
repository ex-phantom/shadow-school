package com.shadow.ucenter.controller.admin;


import com.shadow.common.constant.R;
import com.shadow.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/admin/ucenter/member")
@CrossOrigin
@Api(description = "用户管理")
public class MemberAdminController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "查询某日用户登录的总数")
    @GetMapping(value = "count-register/{day}")
    public R registerCount(
            @ApiParam(name = "day", value = "日期", required = true)
            @PathVariable("day") String day){
        Integer count = memberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }

}

