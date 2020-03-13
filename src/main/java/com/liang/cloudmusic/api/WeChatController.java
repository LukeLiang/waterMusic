/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/7 18:01
 */
package com.liang.cloudmusic.api;

import com.liang.cloudmusic.dto.MusicDTO;
import com.liang.cloudmusic.model.UnifyResponse;
import com.liang.cloudmusic.model.WeiXinConfigParameter;
import com.liang.cloudmusic.service.WXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/wx")
public class WeChatController {


   @Autowired
   private WXService wxService;


    @GetMapping("/getAccessToken")
    public String getAccessToken(){
        return wxService.getAccessToken();
    }


    @GetMapping("/playlist")
    public UnifyResponse getPlaylist(@RequestParam(defaultValue = "0") Integer start,
                                     @RequestParam(defaultValue = "50")Integer count){
        return wxService.getLatestPlaylist(start, count);
    }


    @GetMapping("/getById")
    public UnifyResponse queryDatabase(String id) throws Exception {
        if (id == null){
            throw new Exception("id不能为空");
        }
        return wxService.queryDatabase(id);
    }


    @PostMapping("/updatePlaylist")
    public UnifyResponse updateDatabase(@RequestBody MusicDTO musicDTO){
        UnifyResponse res = wxService.updateDatabase(musicDTO);
        return res;
    }


    @GetMapping("/delDatabase")
    public UnifyResponse deleteDatabase(String id) throws Exception {
        if (id == null){
            throw new Exception("id不能为空");
        }
        return wxService.deleteDatabase(id);
    }
}
