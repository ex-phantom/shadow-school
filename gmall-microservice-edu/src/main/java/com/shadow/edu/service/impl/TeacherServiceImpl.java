package com.shadow.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shadow.edu.entity.QueryTeacher;
import com.shadow.edu.entity.Teacher;
import com.shadow.edu.mapper.TeacherMapper;
import com.shadow.edu.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void pagewithCondition(Page<Teacher> pageParam, QueryTeacher queryTeacher) {

        QueryWrapper<Teacher> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if(Objects.isNull(queryTeacher)){
            this.teacherMapper.selectPage(pageParam,queryWrapper);
            return ;
        }
        String begin = queryTeacher.getBegin();
        String end = queryTeacher.getEnd();
        Integer level = queryTeacher.getLevel();
        String name = queryTeacher.getName();
        if(StringUtils.isNotBlank(begin)){
            queryWrapper.ge("gmt_create",begin);
        }
        if(StringUtils.isNotBlank(end)){
            queryWrapper.le("gmt_modified",end);
        }
        if(!Objects.isNull(level)){
            queryWrapper.eq("level",level);
        }
        if(StringUtils.isNotBlank(name)){
            queryWrapper.likeRight("name",name.substring(0,1));
        }
        this.teacherMapper.selectPage(pageParam,queryWrapper);
    }
}
