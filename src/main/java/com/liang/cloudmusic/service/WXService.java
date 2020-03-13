/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/7 19:20
 */
package com.liang.cloudmusic.service;


import com.liang.cloudmusic.dto.MusicDTO;
import com.liang.cloudmusic.model.UnifyResponse;

public interface WXService {

    String getAccessToken();

    UnifyResponse getLatestPlaylist(Integer start, Integer count);

    UnifyResponse queryDatabase(String id);

    UnifyResponse updateDatabase(MusicDTO musicDTO);

    UnifyResponse deleteDatabase(String id);
}
