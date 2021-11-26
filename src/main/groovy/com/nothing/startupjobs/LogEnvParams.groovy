package com.nothing.startupjobs

import com.nothing.annotations.springcomponents.InjectableComponent
import groovy.util.logging.Slf4j
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

import static java.lang.System.getProperties
import static java.lang.System.getenv

@InjectableComponent
@Slf4j
class LogEnvParams {
    @EventListener(ApplicationReadyEvent)
    void logProperties() {
        (getenv() + getProperties())
                .each { log.info("[CONFIGURATION] Property ${it.key}: ${it.value}") }
    }
}
