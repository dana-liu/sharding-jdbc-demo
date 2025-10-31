package com.lww.sharding.user.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StudentBoughtClassDto {

    /**
     * 班级编号
     */
    private long classNumber;

    /**
     * 班级名称
     */
    private String className;

    /**
     * 开课时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 上课时间，周几
     */
    private String weekdays;

    /**
     * 上课时间，时分
     */
    private long dayTime;

    /**
     * 主讲老师编号
     */
    private long lecturerNumber;

    /**
     * 辅导老师编号
     */
    private long adviserNumber;

    /**
     * 班级状态
     */
    private byte classStatus;

    /**
     * 付款数量统计，记录条数
     */
    private int count;

    /**
     * 小班number
     */
    private long miniClassNumber;

    /**
     * 订单编号
     */
    private long orderNumber;

    private long createTime;

    private byte status;
}
