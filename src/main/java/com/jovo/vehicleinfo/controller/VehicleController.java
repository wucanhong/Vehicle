package com.jovo.vehicleinfo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jovo.vehicleinfo.service.VehicleService;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @ClassName: VehicleController
 * @Author: 吴灿洪
 * @Description:
 * @Date: 2019/5/23 11:12
 * @Version: 1.0
 */
@Controller
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    /**
     * 每天早上6点触发
     */
    @Scheduled(cron = "0 0 6 * * ?")
    @PostMapping(value = "/saveVehicle")
    @ResponseBody
    public void saveVehicle() {
        String userId = "";
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String time = dateFormat.format(new Date());
        String time = "2019-4-28";
        String data = "{\"userId\":\"" + userId + "\",\"time\":\"" + time + "\"}";

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost("你的接口地址");
        // 我这里利用阿里的fastjson，将Object转换为json字符串;
        // (需要导入com.alibaba.fastjson.JSON包)
//        JSONObject object = JSONObject.parseObject(data);
//        String jsonString = JSON.toJSONString(object);

//        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        StringEntity entity = new StringEntity(data, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);

        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            //响应的状态码
            int statue = response.getStatusLine().getStatusCode();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                if (statue == 200) {
                    System.out.println("**************获取成功**************");
                    String responseData = EntityUtils.toString(responseEntity);
                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                    System.out.println("响应内容为:" + responseData);

                    //保存数据库
                    JSONObject jsonObject = JSONObject.parseObject(responseData);
                    JSONArray jsonArray = jsonObject.getJSONArray("data");

                    vehicleService.saveVehicle(jsonArray);

                } else {
                    System.out.println("****************获取失败****************");
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
