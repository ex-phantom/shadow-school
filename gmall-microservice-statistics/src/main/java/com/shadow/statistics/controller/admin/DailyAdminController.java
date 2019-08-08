package com.shadow.statistics.controller.admin;


import com.shadow.common.constant.R;
import com.shadow.statistics.service.DailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-08
 */
@RestController
@RequestMapping("/admin/statistics/daily")
@CrossOrigin
@Api(description = "每日统计分析")
public class DailyAdminController {

    @Autowired
    private DailyService dailyService;

    @ApiOperation(value = "根据某日用户注册数进行分析")
    @GetMapping("{day}")
    public R createStatisticsByDate(
            @ApiParam(name = "day", value = "日期", required = true)
            @PathVariable String day) {
        dailyService.createStatisticsByDay(day);
        return R.ok();
    }

    @GetMapping("show-chart/{begin}/{end}/{type}")
    public R showChart(
            @PathVariable String begin,
            @PathVariable String end,
            @PathVariable String type){
        Map<String, Object> map = dailyService.getChartData(begin, end, type);
        return R.ok().data(map);
    }
}

