/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/11 13:48
 */
package com.liang.cloudmusic.service;

import com.liang.cloudmusic.dto.SwiperDataDTO;
import com.liang.cloudmusic.model.UnifyResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SwiperService {

    UnifyResponse getAllSwipers();

    UnifyResponse uploadFile(MultipartFile file) throws IOException;

    UnifyResponse delSwiper(SwiperDataDTO swiperDataDTO);
}
