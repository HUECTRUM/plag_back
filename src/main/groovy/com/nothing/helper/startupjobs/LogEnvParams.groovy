package com.nothing.helper.startupjobs

import com.nothing.helper.annotations.springcomponents.InjectableComponent
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

import static java.lang.System.getProperties
import static java.lang.System.getenv

@InjectableComponent
class LogEnvParams {
    @EventListener(ApplicationReadyEvent) void logProperties() {
        (getenv() + getProperties()).each { log.info("[CONFIGURATION] Property ${it.key}: ${it.value}") }
    }
}
