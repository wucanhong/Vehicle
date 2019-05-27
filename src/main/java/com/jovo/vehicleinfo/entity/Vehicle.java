package com.jovo.vehicleinfo.entity;

import java.io.Serializable;

/**
 * @ClassName: Vehicle  用于保存车辆信息
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/5/23 9:20
 * @Version: 1.0
 */
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1083296911916198484L;

    //车牌号
    private String registrationNo;
    //司机姓名
    private String driverName;
    //报警类型
    private String alarmKindName;
    //速度
    private Double gpsSpeed;
    //纬度
    private Double latitude_D;
    //经度
    private Double longitude_D;
    //报警时间
    private String alarmTime;
    //是否处理
    private Integer ideaId;
    //处理时间
    private String dealTime;
    //处理结果
    private String transact_result;
    //处理过程
    private String dealContent;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getAlarmKindName() {
        return alarmKindName;
    }

    public void setAlarmKindName(String alarmKindName) {
        this.alarmKindName = alarmKindName;
    }

    public Double getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(Double gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }

    public Double getLatitude_D() {
        return latitude_D;
    }

    public void setLatitude_D(Double latitude_D) {
        this.latitude_D = latitude_D;
    }

    public Double getLongitude_D() {
        return longitude_D;
    }

    public void setLongitude_D(Double longitude_D) {
        this.longitude_D = longitude_D;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Integer getIdeaId() {
        return ideaId;
    }

    public void setIdeaId(Integer ideaId) {
        this.ideaId = ideaId;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getTransact_result() {
        return transact_result;
    }

    public void setTransact_result(String transact_result) {
        this.transact_result = transact_result;
    }

    public String getDealContent() {
        return dealContent;
    }

    public void setDealContent(String dealContent) {
        this.dealContent = dealContent;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNo='" + registrationNo + '\'' +
                ", driverName='" + driverName + '\'' +
                ", alarmKindName='" + alarmKindName + '\'' +
                ", gpsSpeed=" + gpsSpeed +
                ", latitude_D=" + latitude_D +
                ", longitude_D=" + longitude_D +
                ", alarmTime='" + alarmTime + '\'' +
                ", ideaId=" + ideaId +
                ", dealTime='" + dealTime + '\'' +
                ", transact_result='" + transact_result + '\'' +
                ", dealContent='" + dealContent + '\'' +
                '}';
    }
}
