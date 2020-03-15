/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/9 18:29
 */
package com.liang.cloudmusic.model;

import lombok.Data;

//@Data
public class ResponseCode {

    private String errcode;     // 错误码
    private String errmsg;      // 错误信息
    private String resp_data;   // 云函数返回的buffer

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getResp_data() {
        return resp_data;
    }

    public void setResp_data(String resp_data) {
        this.resp_data = resp_data;
    }
}
