/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/11 11:21
 */
package com.liang.cloudmusic.dto;

import lombok.Data;


@Data
public class MusicDTO {

    private String _id;

    private String alg;

    private Boolean canDislike;

    private String copywriter;

//    private Date createTime;

    private Boolean highQuality;

    private Long id;

    private String name;

    private String picUrl;

    private Long playCount;

    private Long trackCount;

//    private Date trackNumberUpdateTime;

    private Integer type;
}
