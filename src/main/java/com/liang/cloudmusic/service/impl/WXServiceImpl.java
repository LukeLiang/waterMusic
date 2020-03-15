/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/7 19:20
 */
package com.liang.cloudmusic.service.impl;

import com.liang.cloudmusic.dto.MusicDTO;
import com.liang.cloudmusic.enums.DatabaseOperateType;
import com.liang.cloudmusic.mapping.TokenMapper;
import com.liang.cloudmusic.model.AccessToken;
import com.liang.cloudmusic.model.Token;
import com.liang.cloudmusic.model.UnifyResponse;
import com.liang.cloudmusic.model.WeiXinConfigParameter;
import com.liang.cloudmusic.service.WXService;
import com.liang.cloudmusic.utils.CommonUtil;
import com.liang.cloudmusic.utils.DatabaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WXServiceImpl implements WXService {

    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private DatabaseUtil databaseUtil;

    @Autowired
    private WeiXinConfigParameter WXParameter;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String getAccessToken() {
         // 1.获取access_token
        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
                "grant_type=" + WXParameter.getGrantType() + "&" +
                "appid=" + WXParameter.getAppid() + "&" +
                "secret=" + WXParameter.getSecret();

//        String url = "https://api.weixin.qq.com/cgi-bin/token?" +
//                "grant_type=client_credential&" +
//                "appid=wx86f521277310d89f&" +
//                "secret=5584c0b7c24a11ac3d03e2e4ca3815c8";

        ResponseEntity<AccessToken> responseEntity = restTemplate.getForEntity(url, AccessToken.class);
        AccessToken body = responseEntity.getBody();

        if (body == null ||body.getAccess_token() == null){
            throw new RuntimeException("获取access_token失败, 请检查传入的appid和secret是否正确");
        }

        Token token = new Token();
        token.setAccessToken(body.getAccess_token());
        // 2. 将获取到的access_token写入数据库, 以便后续使用
        Token oldToken = tokenMapper.getAccessToken();
        if (oldToken == null){
            // 数据库中没有access_token
            tokenMapper.insert(token);
        }else {
            // 数据库中有access_token 此时是定时任务触发，需要更改access_token
            tokenMapper.getNewAccessToken(oldToken.getAccessToken(), body.getAccess_token());
        }

        return body.getAccess_token();
    }


    @Override
    public UnifyResponse getLatestPlaylist(Integer start, Integer count) {
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();
        UnifyResponse res = CommonUtil.getLatestPlaylist(start, count, "music", "playlist", access_token);

        return res;
    }


    @Override
    public UnifyResponse queryDatabase(String id){
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String query = "db.collection('playlist').doc('" + id + "').get()";

        UnifyResponse res = CommonUtil.operatDatabase(id, access_token, "cloudmusic-dev-o26dl", DatabaseOperateType.DATABASE_QUERY.getValue(), query);

        return res;
    }


    @Override
    public UnifyResponse updateDatabase(MusicDTO musicDTO) {
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String query = "db.collection('playlist').doc('" + musicDTO.get_id() +"').update({data: {name: '" + musicDTO.getName() +"', copywriter: '" + musicDTO.getCopywriter() +"'}})";

        UnifyResponse res = CommonUtil.operatDatabase(musicDTO.get_id(), access_token, "cloudmusic-dev-o26dl", DatabaseOperateType.DATABASE_UPDATE.getValue(), query);

        return res;
    }


    @Override
    public UnifyResponse deleteDatabase(String id) {
        // 1. 向数据库查询最新的access_token
        String access_token = databaseUtil.getAccessToken();

        String query = "db.collection('playlist').doc('" + id + "').remove()";

        UnifyResponse res = CommonUtil.operatDatabase(id, access_token, WXParameter.getEnv(), DatabaseOperateType.DATABASE_DELETE.getValue(), query);
        return res;
    }
}
