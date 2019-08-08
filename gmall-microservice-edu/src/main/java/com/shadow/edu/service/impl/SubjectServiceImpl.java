package com.shadow.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shadow.common.util.ExcelImportUtil;
import com.shadow.edu.entity.Subject;
import com.shadow.edu.mapper.SubjectMapper;
import com.shadow.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shadow.edu.vo.SubjectNestedVo;
import com.shadow.edu.vo.SubjectVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Transactional
    @Override
    public List<String> batchImport(MultipartFile file) throws Exception {
        //错误消息列表
        List<String> errorMsg = new ArrayList<>();

        //创建工具类对象
        ExcelImportUtil excelHSSFUtil = new ExcelImportUtil(file.getInputStream());
        //获取工作表
        Sheet sheet = excelHSSFUtil.getSheet();

        int rowCount = sheet.getPhysicalNumberOfRows();
        if (rowCount <= 1) {
            errorMsg.add("请填写数据");
            return errorMsg;
        }

        for (int rowNum = 1; rowNum < rowCount; rowNum++) {

            Row rowData = sheet.getRow(rowNum);
            if (rowData != null) {// 行不为空

                //获取一级分类
                String levelOneValue = "";
                Cell levelOneCell = rowData.getCell(0);
                if (levelOneCell != null) {
                    levelOneValue = excelHSSFUtil.getCellValue(levelOneCell).trim();
                    if (StringUtils.isEmpty(levelOneValue)) {
                        errorMsg.add("第" + rowNum + "行一级分类为空");
                        continue;
                    }
                }

                //判断一级分类是否重复 TODO
                Subject byTitle = this.getByTitle(levelOneValue);
                String parentId = null;
                if (byTitle == null) {
                    //将一级分类存入数据库
                    Subject subjectLevelOne = new Subject();
                    subjectLevelOne.setTitle(levelOneValue);
                    subjectLevelOne.setSort(rowNum);
                    subjectMapper.insert(subjectLevelOne);
                    parentId = subjectLevelOne.getId();
                } else {
                    parentId = byTitle.getId();
                }
                //获取二级分类
                String levelTwoValue = "";
                Cell levelTwoCell = rowData.getCell(1);
                if (levelTwoCell != null) {
                    levelTwoValue = excelHSSFUtil.getCellValue(levelTwoCell).trim();
                    if (StringUtils.isEmpty(levelTwoValue)) {
                        errorMsg.add("第" + rowNum + "行二级分类为空");
                        continue;
                    }
                }
                //判断二级分类是否重复
                Subject subjectSub = this.getSubByTitle(levelTwoValue, parentId);
                Subject subjectLevelTwo = null;
                if (subjectSub == null) {
                    //将二级分类存入数据库
                    subjectLevelTwo = new Subject();
                    subjectLevelTwo.setTitle(levelTwoValue);
                    subjectLevelTwo.setParentId(parentId);
                    subjectLevelTwo.setSort(rowNum);
                    subjectMapper.insert(subjectLevelTwo);
                }
            }
        }
        return errorMsg;

    }

    @Override
    public List<SubjectNestedVo> nestList() {
        //从数据库中查出父科目，再查出子科目
        List<SubjectNestedVo> subjectNestedVoList=new ArrayList<>();

        //查询父类科目
        QueryWrapper<Subject> subjectNestedVoQueryWrapper = new QueryWrapper<>();
        subjectNestedVoQueryWrapper.eq("parent_id",0);
        subjectNestedVoQueryWrapper.orderByAsc("sort");
        List<Subject> subjectNestedVoListFromDB = this.subjectMapper.selectList(subjectNestedVoQueryWrapper);

        //查询子类科目
        QueryWrapper<Subject> subjectVoQueryWrapper = new QueryWrapper<>();
        subjectVoQueryWrapper.ne("parent_id",0);
        subjectVoQueryWrapper.orderByAsc("sort");
        List<Subject> subjectVoListFromDB = this.subjectMapper.selectList(subjectVoQueryWrapper);

        //将子类科目封装进入父类科目
        if(subjectNestedVoListFromDB !=null && subjectNestedVoListFromDB.size()>0){
            for (Subject subject : subjectNestedVoListFromDB) {
                SubjectNestedVo subjectNestedVo=new SubjectNestedVo();
                //转化
                BeanUtils.copyProperties(subject,subjectNestedVo);
                //装入返回集合
                subjectNestedVoList.add(subjectNestedVo);
                //是否有子科目
                if(subjectVoListFromDB !=null && subjectVoListFromDB.size()>0){
                    for (Subject subject1 : subjectVoListFromDB) {
                        if(subject.getId().equals(subject1.getParentId())){
                            //父科目的id和子科目的父id相同
                            SubjectVo subjectVo=new SubjectVo();
                            BeanUtils.copyProperties(subject1,subjectVo);
                            subjectNestedVo.getChildren().add(subjectVo);
                        }
                    }
                }
            }
        }
        return subjectNestedVoList;
    }


    /**
     * 根据分类名称查询这个一级分类中否存在
     *
     * @param title
     * @return
     */
    private Subject getByTitle(String title) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", "0");//一级分类
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据分类名称和父id查询这个二级分类中否存在
     *
     * @param title
     * @return
     */
    private Subject getSubByTitle(String title, String parentId) {

        QueryWrapper<Subject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("title", title);
        queryWrapper.eq("parent_id", parentId);
        return baseMapper.selectOne(queryWrapper);
    }
}
