package com.jz1yer.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.jz1yer.eduservice.entity.EduSubject;
import com.jz1yer.eduservice.entity.excel.SubjectData;
import com.jz1yer.eduservice.entity.subject.OneSubject;
import com.jz1yer.eduservice.entity.subject.TwoSubject;
import com.jz1yer.eduservice.listener.SubjectExcelListener;
import com.jz1yer.eduservice.mapper.EduSubjectMapper;
import com.jz1yer.eduservice.service.EduSubjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OneSubject> getAllSubject() {
        //查询一级分类QueryWrapper
        QueryWrapper<EduSubject> queryOneWrapper= new QueryWrapper<>();
        queryOneWrapper.eq("parent_id", "0");
        //查询出一级分类结果集
        List<EduSubject> selectEduOneSubjects = this.baseMapper.selectList(queryOneWrapper);
        //查询二级分类QueryWrapper
        QueryWrapper<EduSubject> queryTwoWrapper= new QueryWrapper<>();
        queryTwoWrapper.ne("parent_id", "0");
        //查询出二级分类结果集
        List<EduSubject> selectEduTwoSubjects = this.baseMapper.selectList(queryTwoWrapper);
        //新建树状一级分类集合
        List<OneSubject> oneSubjects = new ArrayList<>();
        //双重循环
        for (EduSubject n : selectEduOneSubjects){

            //new一个一级树状分类
            OneSubject oneSubject = new OneSubject();
            //新建树状二级分类集合
            List<TwoSubject> twoSubjects = new ArrayList<>();
            //把查询出的一级分类结果集循环存入新建树状一级分类集合
            BeanUtils.copyProperties(n,oneSubject);
            oneSubjects.add(oneSubject);
            for (EduSubject m:selectEduTwoSubjects){

                if(n.getId().equals(m.getParentId()) ){
                    //new一个二级树状分类
                    TwoSubject twoSubject = new TwoSubject();
                    //把查询出的二级分类结果集循环存入新建树状二级分类集合
                    BeanUtils.copyProperties(m,twoSubject);
                    twoSubjects.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoSubjects);
        }
        return oneSubjects;
    }
}
