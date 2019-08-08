package com.shadow.edu.service;

import com.shadow.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow.edu.form.CourseInfoForm;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
public interface CourseService extends IService<Course> {
    /**
     * 保存课程和课程详情信息
     * @param courseInfoForm
     * @return 新生成的课程id
     */
    String saveCourseInfo(CourseInfoForm courseInfoForm);
}
