/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 10:01
 */
package com.liang.cloudmusic.model;

import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class FileResponse {

    private Long errcode;

    private String errmsg;

    private String url;

    private String token;

    private String authorization;

    private String file_id;

    private String cos_file_id;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getFile_id() {
        return file_id;
    }

    public void setFile_id(String file_id) {
        this.file_id = file_id;
    }

    public String getCos_file_id() {
        return cos_file_id;
    }

    public void setCos_file_id(String cos_file_id) {
        this.cos_file_id = cos_file_id;
    }
}
