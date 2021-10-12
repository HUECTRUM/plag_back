package com.nothing;

import com.nothing.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class Test {
    private final MatchService matchService;

    @EventListener(ApplicationReadyEvent.class)
    public void go() {
        String res = matchService.getMatches().block();
        log.info("RES {}", res);
    }
}
