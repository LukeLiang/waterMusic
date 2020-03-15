/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/11 13:48
 */
package com.liang.cloudmusic.service.impl;

import com.liang.cloudmusic.dto.SwiperDataDTO;
import com.liang.cloudmusic.enums.DatabaseOperateType;
import com.liang.cloudmusic.model.UnifyResponse;
import com.liang.cloudmusic.model.WeiXinConfigParameter;
import com.liang.cloudmusic.service.SwiperService;
import com.liang.cloudmusic.utils.CommonUtil;
import com.liang.cloudmusic.utils.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class SwiperServiceImpl implements SwiperService {


    @Autowired
    private DatabaseUtil databaseUtil;

    @Autowired
    private WeiXinConfigParameter WXParameter;


    @Override
    public UnifyResponse getAllSwipers(){
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String query = "db.collection('swiper').get()";

        UnifyResponse res = CommonUtil.getAll(access_token, "cloudmusic-dev-o26dl", DatabaseOperateType.DATABASE_QUERY.getValue(), query);
        return res;
    }


    @Override
    public UnifyResponse uploadFile(MultipartFile file) throws IOException {
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String fileName = file.getOriginalFilename();
        byte[] bytes = file.getBytes();
        String path = "swiper/" + System.currentTimeMillis() + "-" + fileName;


        UnifyResponse res = CommonUtil.uploadFile(access_token, "cloudmusic-dev-o26dl", path, bytes);

        return res;
    }



    @Override
    public UnifyResponse delSwiper(SwiperDataDTO swiperDataDTO) {
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String query = "db.collection('swiper').where({fileid: '" + swiperDataDTO.getFileid() + "'}).remove()";
        String[] fileid = new String[swiperDataDTO.getFileid().length()];
        fileid[0] = swiperDataDTO.getFileid();

        // 1.删除云数据库中的当前swiper
        CommonUtil.operatDatabase("", access_token, "cloudmusic-dev-o26dl", DatabaseOperateType.DATABASE_DELETE.getValue(), query);

        // 2.删除云存储中的当前swiper
        UnifyResponse res = CommonUtil.delCloundStorage(access_token, "cloudmusic-dev-o26dl", fileid);

        return res;
    }
}
