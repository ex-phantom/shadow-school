package com.shadow.sso.controller;


import com.shadow.common.constant.GlobalExceptionUtil;
import com.shadow.common.constant.R;
import com.shadow.common.constant.ResultCodeEnum;
import com.shadow.sso.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(description = "阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/admin/oss/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(name = "file", value = "文件")
            @RequestParam("file") MultipartFile file,
            @RequestParam(value = "fileHost") String fileHost) {

        try {
            String uploadUrl = fileService.upload(file, fileHost);
            //返回r对象
            return R.ok().message("文件上传成功").data("url", uploadUrl);
        } catch (Exception e) {
            throw new GlobalExceptionUtil(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }

    }
}