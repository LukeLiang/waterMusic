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
import lombok.Getter;
import lombok.Setter;

public class DatabaseCode {
    private Long errcode;
    private String errmsg;
    private Object pager;
    private String[] data;

    public Long getErrcode() {
        return errcode;
    }

    public void setErrcode(Long errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getPager() {
        return pager;
    }

    public void setPager(Object pager) {
        this.pager = pager;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
