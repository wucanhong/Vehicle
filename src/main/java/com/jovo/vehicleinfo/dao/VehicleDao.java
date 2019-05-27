package com.jovo.vehicleinfo.dao;

import com.jovo.vehicleinfo.entity.Vehicle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: VehicleDao
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/5/23 10:39
 * @Version: 1.0
 */
public interface VehicleDao {

    /**
     * 向数据库插入车辆行驶信息
     * @param list
     */
    void insertVehicle(@Param(value = "list") List<Vehicle> list);
}
