package com.nothing

import com.nothing.helper.annotations.ApplicationStarter

import static org.springframework.boot.SpringApplication.run

@ApplicationStarter
class AppEntrypoint {
    static void main(String[] args) {
        run(AppEntrypoint)
    }
}
