/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 19:58
 */
package com.liang.cloudmusic.utils;


import com.liang.cloudmusic.mapping.TokenMapper;
import com.liang.cloudmusic.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据库相关的通用方法
 */
@Component
public class DatabaseUtil {


    @Autowired
    private TokenMapper tokenMapper;


    /**
     * 获取 access_token
     * @return access_token
     */
    public String getAccessToken(){
        Token token = tokenMapper.getAccessToken();
        String access_token = token.getAccessToken();
        return access_token;
    }
}
