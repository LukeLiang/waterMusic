/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 13:02
 */
package com.liang.cloudmusic.dto;

import lombok.Data;


@Data
public class SwiperDataDTO {

    private String fileid;

    private String download_url;

    private int status;

    private String errmsg;
}
