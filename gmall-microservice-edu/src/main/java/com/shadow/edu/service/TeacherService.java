package com.shadow.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shadow.edu.entity.QueryTeacher;
import com.shadow.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
public interface TeacherService extends IService<Teacher> {

    void pagewithCondition(Page<Teacher> pageParam, QueryTeacher queryTeacher);
}
