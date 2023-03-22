
package org.vikviper.welldemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.vikviper.welldemo.res.Space;

import java.util.HashSet;

@Configuration
public class AppConfig {

    @Bean
    public Space space() {
        return new Space(new HashSet<>());
    }
}
