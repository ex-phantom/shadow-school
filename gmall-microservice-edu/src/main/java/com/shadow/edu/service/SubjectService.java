package com.shadow.edu.service;

import com.shadow.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shadow.edu.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
public interface SubjectService extends IService<Subject> {

    List<String> batchImport(MultipartFile file) throws Exception;


    List<SubjectNestedVo> nestList();
}
