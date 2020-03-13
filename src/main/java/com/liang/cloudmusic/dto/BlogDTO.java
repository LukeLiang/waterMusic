/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 16:28
 */
package com.liang.cloudmusic.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BlogDTO {

    private String _id;

    private String _openid;

    private String avatarUrl;

    private String content;

//    private LocalDateTime createTime;

    private String[] img;

    private String nickName;
}
