package com.liziczh.erp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.util.Properties;

@Configuration
public class FreeMakerConfig {

    @Value("${spring.freemaker.template-loader-path}")
    private String templateLoaderPath;

    @Value("${spring.freemaker.settings.template_update_delay}")
    private String templateUpdateDelay;

    @Value("${spring.freemaker.settings.default_encoding}")
    private String defaultEncoding;

    @Value("${spring.freemaker.settings.number_format}")
    private String numberFormat;

    @Value("${spring.freemaker.settings.datetime_format}")
    private String datetimeFormat;

    @Value("${spring.freemaker.settings.classic_compatible}")
    private String classic_compatible;

    @Value("${spring.freemaker.settings.template_exception_handler}")
    private String templateExceptionHandler;

    /**
     * FreeMaker配置
     */
    @Bean
    public FreeMarkerConfigurer freeMarkerConfigurer() {
        FreeMarkerConfigurer freeMarkerConfig = new FreeMarkerConfigurer();
        freeMarkerConfig.setTemplateLoaderPath(templateLoaderPath);
        Properties settings = new Properties();
        settings.setProperty("template_update_delay", templateUpdateDelay);
        settings.setProperty("default_encoding", defaultEncoding);
        settings.setProperty("number_format", numberFormat);
        settings.setProperty("datetime_format", datetimeFormat);
        settings.setProperty("classic_compatible", classic_compatible);
        settings.setProperty("template_exception_handler", templateExceptionHandler);
        freeMarkerConfig.setFreemarkerSettings(settings);
        return freeMarkerConfig;
    }


}
