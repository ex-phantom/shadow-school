package com.shadow.edu.controller.admin;

import com.shadow.common.constant.GlobalExceptionUtil;
import com.shadow.common.constant.R;
import com.shadow.common.constant.ResultCodeEnum;
import com.shadow.edu.service.SubjectService;
import com.shadow.edu.vo.SubjectNestedVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin/edu/subject")
@CrossOrigin
@Api(description = "课程分类管理")
public class SubjectAdminController {
    @Autowired
    private SubjectService subjectService;

    @ApiOperation(value = "Excel批量导入")
    @PostMapping("import")
    public R batchImport(
            @ApiParam(name = "file", value = "Excel文件", required = true)
            @RequestParam("file") MultipartFile file) {

        try {
            List<String> errorMsg = subjectService.batchImport(file);
            if (errorMsg.size() == 0) {
                return R.ok().message("批量导入成功");
            } else {
                return R.error().message("部分数据导入失败").data("errorMsgList", errorMsg);
            }

        } catch (Exception e) {
            //无论哪种异常，只要是在excel导入时发生的，一律用转成GlobalExceptionUtil抛出
            throw new GlobalExceptionUtil(ResultCodeEnum.EXCEL_DATA_IMPORT_ERROR);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping()
    public R nestedList(){
        List<SubjectNestedVo> subjectNestedVoList=this.subjectService.nestList();
        return R.ok().data("items",subjectNestedVoList);
    }
}
