/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/12 15:51
 */
package com.liang.cloudmusic.api;

import com.liang.cloudmusic.dto.BlogDTO;
import com.liang.cloudmusic.model.UnifyResponse;
import com.liang.cloudmusic.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wx/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;


    @GetMapping("/list")
    public UnifyResponse getBlogList(@RequestParam(defaultValue = "0") Integer start,
                                     @RequestParam(defaultValue = "50")Integer count){
        UnifyResponse res = blogService.getBlogList(start, count);
        return res;
    }


    @PostMapping("/del")
    public UnifyResponse delBlog(@RequestBody BlogDTO blogDTO){
        UnifyResponse res = blogService.delBlog(blogDTO);

        return res;
    }
}
