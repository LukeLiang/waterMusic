/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/11 14:20
 */
package com.liang.cloudmusic.model;

import lombok.Data;

@Data
public class DatabaseCode {
    private Long errcode;
    private String errmsg;
    private Object pager;
    private String[] data;
}
