/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/7 18:18
 */
package com.liang.cloudmusic.utils;

import com.liang.cloudmusic.core.wxconfiguration.WxMappingJackson2HttpMessageConverter;
import com.liang.cloudmusic.model.*;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 常用工具类封装
 */
public class CommonUtil {


    /**
     * 向云数据库请求歌单列表
     * @param start 从第几条开始取
     * @param count 每次取多少条数据
     * @param functionName  云函数名称
     * @param url   调用哪一个路由
     * @param accessToken   调用凭证
     * @return  UnifyResponse 封装统一返回类
     */
    public static UnifyResponse getLatestPlaylist(Integer start,
                                                  Integer count,
                                                  String functionName,
                                                  String url,
                                                  String accessToken){
        String baseUrl = "https://api.weixin.qq.com/tcb/invokecloudfunction?" +
                "access_token=" + accessToken + "&" +
                "env=cloudmusic-dev-o26dl&" +
                "name=" + functionName;

        HttpHeaders headers = new HttpHeaders();
        // 设置header
        MediaType type = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(type);

        // 请求体 Body
        HashMap requestBody = new HashMap();
        requestBody.put("$url", url);
        requestBody.put("start", start);
        requestBody.put("count", count);

        HttpEntity entity = new HttpEntity(requestBody, headers);

        // 发送请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ResponseCode> responseEntity = restTemplate.postForEntity(baseUrl, entity, ResponseCode.class);
        ResponseCode res = responseEntity.getBody();
        UnifyResponse response = new UnifyResponse(20000, res.getResp_data());

        return response;
    }


    /**
     * 操作云数据库
     * @param id 前端传过来的音乐_id(没用到, 充当占位符)
     * @param accessToken 调用凭证
     * @param env   云环境ID
     * @param type 操作类型（增、删、改、查）
     * @param query 操作云数据库语句
     * @return  返回记录
     */
    public static UnifyResponse operatDatabase(String id, String accessToken, String env, String type, String query){
        String baseURL = "https://api.weixin.qq.com/tcb/" + type +"?access_token=" + accessToken;

        HttpHeaders headers = new HttpHeaders();
        // 设置header
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(mediaType);


        // 设置请求体
        HashMap requestBody = new HashMap();
//        requestBody.put("access_token", accessToken);
        requestBody.put("env", env);
        requestBody.put("query", query);

        HttpEntity entity = new HttpEntity(requestBody, headers);

        // 发送POST请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseURL, entity, String.class);
        String res = responseEntity.getBody();

        UnifyResponse response = new UnifyResponse(20000, res);
        return response;
    }


    /**
     * 真正获取到浏览器可以访问的轮播图地址
     * @param accessToken   调用凭证
     * @param res   第一次调用的返回结果
     * @param env   云环境ID
     * @return  返回结果
     */
    public static String getSwiper(String accessToken, DatabaseCode res, String env){

        HttpHeaders headers = new HttpHeaders();
        // 设置header
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(mediaType);


        String[] data = res.getData();

        List<FileList> list = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            DataList dataList = JsonUtils.jsonToPojo(data[i], DataList.class);
            FileList fileList = new FileList();
            fileList.setFileid(dataList.getFileid());
            fileList.setMax_age(7200);
            list.add(fileList);
        }

        HashMap body = new HashMap();
        body.put("env", env);
        body.put("file_list", list);

        HttpEntity httpEntity = new HttpEntity(body, headers);

        String url = "https://api.weixin.qq.com/tcb/batchdownloadfile?access_token=" + accessToken;

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> resEntity = restTemplate.postForEntity(url, httpEntity, String.class);
        String response = resEntity.getBody();

        return response;
    }


    /**
     * 获取轮播图地址
     * @param accessToken 调用凭证
     * @param env   云环境ID
     * @param type  数据库操作类型语句
     * @param query 云数据库查询条件
     * @return  前端统一返回格式
     */
    public static UnifyResponse getAll(String accessToken, String env, String type, String query){
        String baseURL = "https://api.weixin.qq.com/tcb/" + type +"?access_token=" + accessToken;

        HttpHeaders headers = new HttpHeaders();
        // 设置header
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(mediaType);


        // 设置请求体
        HashMap requestBody = new HashMap();
        requestBody.put("env", env);
        requestBody.put("query", query);

        HttpEntity entity = new HttpEntity(requestBody, headers);

        // 发送POST请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DatabaseCode> responseEntity = restTemplate.postForEntity(baseURL, entity, DatabaseCode.class);
        DatabaseCode res = responseEntity.getBody();

        // 二次发送 获取https协议的 轮播图地址
        String response = getSwiper(accessToken, res, env);



        // 后续优化代码 第二版实现
//        for (int i = 0; i < res.getData().length; i++) {
//            SwiperResponseVO vo = new SwiperResponseVO();
//            vo.set_id();
//        }

        UnifyResponse unifyResponse = new UnifyResponse(20000, response);
        return unifyResponse;

    }


    public static UnifyResponse uploadFile(String accessToken, String env, String path, byte[] bytes){
        String baseUrl = "https://api.weixin.qq.com/tcb/uploadfile?access_token=" + accessToken;

        HttpHeaders headers = new HttpHeaders();
        // 设置header
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(mediaType);

        HashMap body = new HashMap();
        body.put("env", env);
        body.put("path", path);

        HttpEntity entity = new HttpEntity(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FileResponse> responseEntity = restTemplate.postForEntity(baseUrl, entity, FileResponse.class);
        FileResponse resData = responseEntity.getBody();


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);


        MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
//        requestBody.put("url", resData.getUrl());
        requestBody.put("key", Collections.singletonList(path));
        requestBody.put("Signature", Collections.singletonList(resData.getAuthorization()));
        requestBody.put("x-cos-security-token", Collections.singletonList(resData.getToken()));
        requestBody.put("x-cos-meta-fileid", Collections.singletonList(resData.getCos_file_id()));
        requestBody.put("file", Collections.singletonList(bytes));

//        List<HashMap> list = new ArrayList<>();
//        list.add(requestBody);


        HttpEntity httpEntity = new HttpEntity(requestBody, httpHeaders);

        restTemplate.getMessageConverters().add(new WxMappingJackson2HttpMessageConverter());
        ResponseEntity<Integer> res = restTemplate.postForEntity(resData.getUrl(), httpEntity, Integer.class);

        Integer data = res.getBody();


        return new UnifyResponse(20000, data);
    }


    /**
     * 删除云存储
     * @param accessToken   调用凭证
     * @param env   云环境ID
     * @param fileIdList    要产出的云存储fileid
     * @return
     */
    public static UnifyResponse delCloundStorage(String accessToken, String env, String[] fileIdList){
        HttpHeaders headers = new HttpHeaders();
        // 设置header
        MediaType mediaType = MediaType.parseMediaType("application/json;charset=UTF-8");
        headers.setContentType(mediaType);

        String baseUrl = "https://api.weixin.qq.com/tcb/batchdeletefile?access_token=" + accessToken;
        String[] fileidList;

        HashMap body = new HashMap();
        body.put("env", env);
        body.put("fileid_list", fileIdList);

        HttpEntity entity = new HttpEntity(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(baseUrl, entity, String.class);
        String res = responseEntity.getBody();

        return new UnifyResponse(20000, res);
    }

}
