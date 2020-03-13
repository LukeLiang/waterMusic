/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/9 18:29
 */
package com.liang.cloudmusic.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseCode {

    private String errcode;     // 错误码
    private String errmsg;      // 错误信息
    private String resp_data;   // 云函数返回的buffer
}
