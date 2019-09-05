package cn.stylefeng.guns.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {
        // 使用Apache HttpClient构建RestTemplate, 支持的比Spring自带的更多
        RestTemplate restTemplate = new RestTemplate();
        // 去除默认的String转换器
        restTemplate.getMessageConverters().removeIf(converter -> converter instanceof StringHttpMessageConverter);
        // 添加自定义的String转换器, 支持UTF-8
        restTemplate.getMessageConverters()
                .add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

}
