package com.jovo.vehicleinfo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.jovo.vehicleinfo.dao.VehicleDao;
import com.jovo.vehicleinfo.entity.Vehicle;
import com.jovo.vehicleinfo.service.VehicleService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: VehicleServiceImpl
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/5/23 11:08
 * @Version: 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VehicleServiceImpl implements VehicleService {

    @Resource
    private VehicleDao vehicleDao;
    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    final static Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    /**
     * 向数据库中存储车辆行驶信息
     *
     * @param jsonArray
     */
    @Override
    public void saveVehicle(JSONArray jsonArray) {

        //开启session
        SqlSession session = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        VehicleDao dao = session.getMapper(VehicleDao.class);

        int size = jsonArray.size();

        List<Vehicle> vehicles = new ArrayList<Vehicle>();

        try {
            for (int i = 0; i < size; i++) {
                Vehicle vehicle = new Vehicle();
                vehicle.setRegistrationNo(jsonArray.getJSONObject(i).getString("registrationNO"));
                vehicle.setDriverName(jsonArray.getJSONObject(i).getString("driverName"));
                vehicle.setAlarmKindName(jsonArray.getJSONObject(i).getString("alarmKindName"));
                vehicle.setGpsSpeed(Double.valueOf(jsonArray.getJSONObject(i).getString("gpsSpeed")) / 10);
                vehicle.setLatitude_D(Double.valueOf(jsonArray.getJSONObject(i).getString("latitude_D")));
                vehicle.setLongitude_D(Double.valueOf(jsonArray.getJSONObject(i).getString("longitude_D")));
                vehicle.setAlarmTime(jsonArray.getJSONObject(i).getString("alarmTime"));
                vehicle.setIdeaId(Integer.valueOf(jsonArray.getJSONObject(i).getString("ideaId")));
                vehicle.setDealTime(jsonArray.getJSONObject(i).getString("dealTime"));
                vehicle.setTransact_result(jsonArray.getJSONObject(i).getString("transact_result"));
                vehicle.setDealContent(jsonArray.getJSONObject(i).getString("dealContent"));

                vehicles.add(vehicle);

                if (i % 100 == 0 || i == size - 1) {
                    dao.insertVehicle(vehicles);
                    //清除集合中的元素，防止反复插入
                    vehicles.clear();
                    //每100条记录提交一次，提交后无法回滚
                    session.commit();
                    //清除缓存，防止溢出
                    session.clearCache();
                }
            }
        } catch (Exception e) {
            log.error("批量保存失败：", e);
            session.rollback();
        } finally {
            session.close();
        }
    }
}
