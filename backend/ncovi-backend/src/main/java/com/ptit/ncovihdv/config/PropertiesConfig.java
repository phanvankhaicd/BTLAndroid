package com.ptit.ncovihdv.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@PropertySource("classpath:application.properties")
public class PropertiesConfig {
    @Value("${token.expire.time}")
    private Long expireTime;

    @Value("${fb.app.secret}")
    private String facebookAppSecret;

    @Value("${fb.app.id}")
    private String facebookAppId;

    @Value("${corona.crawl.info.api1}")
    private String coronaCrawlApiDantri;

    @Value("${corona.crawl.info.api2}")
    private String coronaCrawlApiCoronaApi;

    @Value("${corona.crawl.info.api3}")
    private String coronaCrawlApiTrackCorona;

    @Value("${corona.crawl.info.api4}")
    private String coronaCrawlApiVnpost;

}
