package com.jz1yer.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jz1yer.eduservice.entity.EduSubject;
import com.jz1yer.eduservice.entity.excel.SubjectData;
import com.jz1yer.eduservice.listener.SubjectExcelListener;
import com.jz1yer.eduservice.mapper.EduSubjectMapper;
import com.jz1yer.eduservice.service.EduSubjectService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author jz1yer
 * @since 2020-04-28
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {


    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try{
            InputStream in = file.getInputStream();
            EasyExcel.read(in, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
