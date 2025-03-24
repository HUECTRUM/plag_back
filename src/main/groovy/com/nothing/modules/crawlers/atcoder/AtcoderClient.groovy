package com.nothing.modules.crawlers.atcoder

import com.nothing.helper.annotations.springcomponents.InjectableService
import com.nothing.helper.utils.WebClientUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

import java.util.function.Function

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED
import static org.springframework.web.reactive.function.BodyInserters.fromFormData
import static reactor.core.publisher.Mono.error

@InjectableService class AtcoderClient {
    static MultiValueMap<String, String> cookies = new LinkedMultiValueMap<String, String>()
    static final def wc =  WebClientUtils.buildWebClient('https://atcoder.jp/', [:])

    @Value('${atc.uname}') String uname
    @Value('${atc.pw}') String pw

    boolean init = false

    void init() {
        String initialResp = wc.get()
                .uri('/login')
                .exchangeToMono(cookieHandler())
                .block()

        def csrfToken = initialResp.lines().find { it.trim().startsWith('var csrfToken') }.split("\"")[1]

        MultiValueMap<String, String> formData = [
                "username": uname,
                "password": pw,
                "csrf_token": csrfToken
        ]

        wc.post()
                .uri('/login')
                .cookies(cookieMap -> cookieMap.addAll(cookies))
                .contentType(APPLICATION_FORM_URLENCODED)
                .body(fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block()
        log.info("Atc login passed")

        init = true
    }

    Function<ClientResponse, ? extends Mono<String>> cookieHandler() {
        (response) -> {
            if (response.statusCode().is2xxSuccessful()) {
                response.cookies().forEach((key, respCookies) -> cookies.add(key, respCookies[0].value))
                return response.bodyToMono(String)
            } else return response.createException().flatMap(e -> error(new RuntimeException("Failed initial atc GET")))
        }
    }

    WebClient getAtcClient() {
        if (!init) init()
        wc
    }
}
