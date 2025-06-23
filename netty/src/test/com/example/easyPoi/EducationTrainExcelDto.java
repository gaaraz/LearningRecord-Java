package com.example.easyPoi;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @description:
 * @author: zzy
 * @createDate: 2023/12/14
 */
@Data
public class EducationTrainExcelDto {
    @Excel(name = "ID",orderNum = "1")
    @NotBlank(message = "ID未填写")
    private String id;

    @Excel(name = "项目名称",orderNum = "2")
    @NotBlank(message = "项目名称")
    private String projectName;

    @Excel(name = "课程名称",orderNum = "3")
    @NotBlank(message = "课程名称未填写")
    @Size(max = 20, message = "课程名称长度不超过20个字段")
    private String courseName;

    @Excel(name = "培训类型",orderNum = "4")
    @NotBlank(message = "请选择培训类型")
    private String trainType;

    @Excel(name = "培训日期",orderNum = "5", importFormat = "yyyy/MM/dd")
    @NotNull(message = "培训日期未填写")
    private LocalDate trainTime;

    @Excel(name = "培训时长（小时）",orderNum = "6")
    @Pattern(regexp = "^(0|\\+?[1-9][0-9]{0,4})(\\.\\d{1,2})?$", message = "培训时长支持两位小数")
    @NotBlank(message = "培训时长未填写")
    private String trainHour;

    @Excel(name = "培训地点",orderNum = "7")
    private String trainAddress;

    @Excel(name = "培训人数",orderNum = "8")
    @Pattern(regexp = "^[0-9]{0,5}", message = "培训人数格式为1-5个数字")
    private String trainSum;

    @Excel(name = "讲师",orderNum = "9")
    @Size(max = 10, message = "讲师长度不超过10个字段")
    private String lecturer;

    @Excel(name = "备注",orderNum = "10")
    @Size(max = 100, message = "备注长度不超过100个字段")
    private String remark;

    @Excel(name = "培训人员姓名",orderNum = "11")
    private String name;

    @Excel(name = "身份证号",orderNum = "12")
    private String papersNo;

    public Boolean checkNull(){
        if (StringUtils.isEmpty(id) && StringUtils.isEmpty(projectName) && StringUtils.isEmpty(courseName)
                && StringUtils.isEmpty(trainType) && StringUtils.isEmpty(trainAddress) && StringUtils.isEmpty(trainSum)
                && StringUtils.isEmpty(lecturer) && StringUtils.isEmpty(remark) && StringUtils.isEmpty(name)
                && StringUtils.isEmpty(papersNo) && StringUtils.isEmpty(trainHour) && Objects.isNull(trainTime)){
            return false;
        }
        return true;
    }
}
