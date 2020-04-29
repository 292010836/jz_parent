package com.jz1yer.eduservice.service;

import com.jz1yer.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author jz1yer
 * @since 2020-04-28
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);
}
