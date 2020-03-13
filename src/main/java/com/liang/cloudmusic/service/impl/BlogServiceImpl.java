/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 15:51
 */
package com.liang.cloudmusic.service.impl;

import com.liang.cloudmusic.dto.BlogDTO;
import com.liang.cloudmusic.enums.DatabaseOperateType;
import com.liang.cloudmusic.model.UnifyResponse;
import com.liang.cloudmusic.model.WeiXinConfigParameter;
import com.liang.cloudmusic.service.BlogService;
import com.liang.cloudmusic.utils.CommonUtil;
import com.liang.cloudmusic.utils.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {


    @Autowired
    private DatabaseUtil databaseUtil;

    @Autowired
    private WeiXinConfigParameter WXParameter;



    @Override
    public UnifyResponse getBlogList(Integer start, Integer count) {
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String query = "db.collection('blog').skip(" + start + ").limit(" + count + ").orderBy('createTime', 'desc').get()";

        UnifyResponse res = CommonUtil.operatDatabase("", access_token, WXParameter.getEnv(), DatabaseOperateType.DATABASE_QUERY.getValue(), query);

        return res;
    }



    @Override
    public UnifyResponse delBlog(BlogDTO blogDTO) {
        // 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        // 1.删除云数据库中相关内容
        String deleteBlog = "db.collection('blog').doc('" + blogDTO.get_id() + "').remove()";
        CommonUtil.operatDatabase("", access_token, WXParameter.getEnv(), DatabaseOperateType.DATABASE_DELETE.getValue(), deleteBlog);

        // 2.删除blog-comment相关内容
        String deleteBlogComment = "db.collection('blog-comment').where({blogId: '" + blogDTO.get_id() + "'}).remove()";
        CommonUtil.operatDatabase("", access_token, WXParameter.getEnv(), DatabaseOperateType.DATABASE_DELETE.getValue(), deleteBlogComment);

        // 3.删除云存储中的相关照片信息
        CommonUtil.delCloundStorage(access_token, WXParameter.getEnv(), blogDTO.getImg());

        UnifyResponse res = new UnifyResponse(20000, "成功");
        return res;
    }
}
