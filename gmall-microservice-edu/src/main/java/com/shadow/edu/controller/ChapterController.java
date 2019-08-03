package com.shadow.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shadow.common.constant.R;
import com.shadow.edu.entity.QueryTeacher;
import com.shadow.edu.entity.Teacher;
import com.shadow.edu.service.TeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
@RestController
@RequestMapping("/edu/chapter")
public class ChapterController {
    @Autowired
    private TeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping
    public R select(){
        List<Teacher> list = this.teacherService.list(null);
        return R.ok().data("items",list);
    }

    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("{id}")
    public R delect(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id){
        boolean b = this.teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "根据条件查出教师,并分页")
    @GetMapping("/{page}/{limit}")
    public R getTeacher(
            @ApiParam(name = "page", value = "当前页面数", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "页面数据数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryTeacher", value = "查询条件", required = false)
            @RequestBody QueryTeacher queryTeacher){

            Page<Teacher> pageParam=new Page<>(page,limit);

            this.teacherService.pagewithCondition(pageParam, queryTeacher);

            List<Teacher> records = pageParam.getRecords();

            long total = pageParam.getTotal();
            return R.ok().data("total",total).data("records",records);
    }


}

