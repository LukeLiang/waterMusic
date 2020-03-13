/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/7 19:02
 */
package com.liang.cloudmusic.task;

import com.liang.cloudmusic.api.WeChatController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class FetchAccessTokenTask {

    @Autowired
    private WeChatController weChatController;


    /**
     * 每100分钟获取一次access_token
     */
    @Scheduled(cron = "0 0/40 0/1 * * ? ")
    public void updateAccessToken(){
        weChatController.getAccessToken();
        System.out.println("access_token被重新获取");
    }
}
