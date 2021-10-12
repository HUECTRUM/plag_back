package com.nothing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

import static org.springframework.boot.SpringApplication.run

@SpringBootApplication
@EnableConfigurationProperties
class AppEntrypoint {
    static void main(String[] args) {
        run AppEntrypoint
    }
}
