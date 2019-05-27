package com.jovo.vehicleinfo.service;

import com.alibaba.fastjson.JSONArray;

/**
 * @ClassName: VehicleService
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/5/23 11:07
 * @Version: 1.0
 */
public interface VehicleService {

    /**
     * 向数据库插入车辆行驶信息
     * @param jsonArray
     */
    void saveVehicle(JSONArray jsonArray);

}
