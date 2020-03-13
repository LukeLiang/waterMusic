/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 15:51
 */
package com.liang.cloudmusic.service;

import com.liang.cloudmusic.dto.BlogDTO;
import com.liang.cloudmusic.model.UnifyResponse;

public interface BlogService {

    UnifyResponse getBlogList(Integer start, Integer count);

    UnifyResponse delBlog(BlogDTO blogDTO);
}
