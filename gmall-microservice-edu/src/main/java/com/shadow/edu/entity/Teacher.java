package com.shadow.edu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author SIX_OCLOCK
 * @since 2019-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("edu_teacher")
@ApiModel(value="Teacher对象", description="讲师")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

//    @ApiModelProperty(value = "讲师ID")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "讲师姓名",example = "苍老师")
    private String name;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师",example = "博士后")
    private String intro;

    @ApiModelProperty(value = "讲师简介",example = "一生育人无数")
    private String career;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师",example = "1高级讲师")
    private Integer level;

    @ApiModelProperty(value = "讲师头像",example = "http://pic25.nipic.com/20121210/7447430_215152474000_2.jpg")
    private String avatar;

    @ApiModelProperty(value = "排序",example = "0")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除",example = "1|2")
    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;

    @ApiModelProperty(value = "创建时间",example = "2000-10-10 20:20:20")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间",example = "2000-10-10 20:20:20")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
