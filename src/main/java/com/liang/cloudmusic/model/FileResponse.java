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

@Getter
@Setter
public class FileResponse {

    private Long errcode;

    private String errmsg;

    private String url;

    private String token;

    private String authorization;

    private String file_id;

    private String cos_file_id;
}
