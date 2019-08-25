package org.java.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 马果
 * @Date: 2019/7/7 16:46
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/leftmenu").setViewName("leftmenu");
        registry.addViewController("/homepage").setViewName("homepage");
        registry.addViewController("/").setViewName("leftmenu");
        registry.addViewController("/personnel").setViewName("personnel");



    }
}
