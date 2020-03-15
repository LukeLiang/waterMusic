/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/9 18:26
 */
package com.liang.cloudmusic.model;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
//@Getter
//@Setter
public class PostParameter {

    private String access_token;    // access_token
    private String env;             // 云开发环境ID
    private String name;            // 云函数名称
    private List<String> POSTBODY;  // 云函数的传入参数，具体结构由开发者定义。

    public PostParameter(String access_token, String env, String name, List<String> POSTBODY) {
        this.access_token = access_token;
        this.env = env;
        this.name = name;
        this.POSTBODY = POSTBODY;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPOSTBODY() {
        return POSTBODY;
    }

    public void setPOSTBODY(List<String> POSTBODY) {
        this.POSTBODY = POSTBODY;
    }
}
