package com.nothing.modules.crawlers.leetcode


import com.nothing.helper.utils.WebClientUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration class LCWebClient {
    static def LC_WEB_API_URL = 'https://leetcode.com/contest/api/'
    static def LC_US_GENERAL_API_URL = 'https://leetcode.com/api/'
    static def LC_CN_GENERAL_API_URL = 'https://leetcode.cn/api/'

    @Bean WebClient lcHttpClient() { WebClientUtils.buildWebClient(LC_WEB_API_URL, [
            'User-Agent': "Mediapartners-Google",
            "Referrer-Policy": "strict-origin-when-cross-origin",
    ]) }

    @Bean WebClient lcUsWebClient() { WebClientUtils.buildWebClient(LC_US_GENERAL_API_URL, [
            'User-Agent': "Mediapartners-Google",
            "Referrer-Policy": "strict-origin-when-cross-origin",
    ]) }

    @Bean WebClient lcCnWebClient() { WebClientUtils.buildWebClient(LC_CN_GENERAL_API_URL, [
            'User-Agent': "Mediapartners-Google",
            "Referrer-Policy": "strict-origin-when-cross-origin",
    ]) }
}
