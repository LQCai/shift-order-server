package org.celery.shift.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@ColumnWidth(25)
@HeadRowHeight(20)
@ContentRowHeight(18)
public class ShiftOrderExcel implements Serializable {
    private static final long serialVersionUID = 1L;

    @ColumnWidth(15)
    @ExcelProperty("工号")
    private String code;

    @ColumnWidth(10)
    @ExcelProperty("姓名")
    private String realName;

    @ColumnWidth(15)
    @ExcelProperty("手机")
    private String phone;

    @ColumnWidth(15)
    @ExcelProperty("提交时间")
    private String createTime;

    @ColumnWidth(15)
    @ExcelProperty("备注")
    private String remark;

}
