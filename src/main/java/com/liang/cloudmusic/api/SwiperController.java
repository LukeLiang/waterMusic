/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/11 13:47
 */
package com.liang.cloudmusic.api;


import com.liang.cloudmusic.dto.SwiperDataDTO;
import com.liang.cloudmusic.model.UnifyResponse;
import com.liang.cloudmusic.service.SwiperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/wx/swiper")
public class SwiperController {

    @Autowired
    private SwiperService swiperService;


    @GetMapping("/list")
    public UnifyResponse list(){
        UnifyResponse res = swiperService.getAllSwipers();
        return res;
    }


    @PostMapping("/upload")
    public UnifyResponse upload(MultipartFile file) throws IOException {
        UnifyResponse res = swiperService.uploadFile(file);

        return res;
    }


    @GetMapping("/del")
    public UnifyResponse delSwiper(@RequestBody SwiperDataDTO swiperDataDTO){
        UnifyResponse res = swiperService.delSwiper(swiperDataDTO);

        return res;
    }
}
