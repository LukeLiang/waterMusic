/**
 * @作者 Luke
 * @微信公号 欧诺的小书屋
 * @开源项目 $ http://7yue.pro
 * @免费专栏 $ http://course.7yue.pro
 * @我的课程 $ http://imooc.com/t/4294850
 * @创建时间 2020/3/10 13:46
 */
package com.liang.cloudmusic.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cors跨域
 */
@Configuration
public class CorsConfiguration {

    public CorsConfiguration(){

    }


    @Bean
    public CorsFilter corsFilter(){
        // 1.添加cors配置信息
        org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();
        configuration.addAllowedOrigin("http://127.0.0.1:9528");

        // 设置是否发送cookie信息
        configuration.setAllowCredentials(true);

        // 设置允许请求的方式
        configuration.addAllowedMethod("*");

        // 设置允许的header
        configuration.addAllowedHeader("*");

        // 2.为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource =new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", configuration);

        // 3.重新定义好的corsSource
        return new CorsFilter(corsSource);
    }
}
