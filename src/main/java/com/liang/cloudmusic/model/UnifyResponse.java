/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/10 13:56
 */
package com.liang.cloudmusic.model;

import lombok.Data;

/**
 * 返回统一的编码格式
 * vue-element-admin 要求返回数据必须有一个code:20000
 * @param <T>
 */
@Data
public class UnifyResponse<T> {

    private Integer code;

    private T data;

    public UnifyResponse(Integer code, T data){
        this.code = code;
        this.data = data;
    }
}
