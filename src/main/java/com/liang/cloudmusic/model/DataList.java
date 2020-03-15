/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/11 21:04
 */
package com.liang.cloudmusic.model;

import lombok.Getter;
import lombok.Setter;
public class DataList {

    private String _id;
    private String fileid;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFileid() {
        return fileid;
    }

    public void setFileid(String fileid) {
        this.fileid = fileid;
    }
}
