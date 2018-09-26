package com.flower.mine.config;

import com.flower.mine.session.SessionUtil;
import com.flower.mine.util.ServletUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class PersistenceConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return (AuditorAware) () -> Optional.ofNullable(ServletUtil.currentRequest() == null ? "sys" : SessionUtil.currentUserId() );
    }
}
