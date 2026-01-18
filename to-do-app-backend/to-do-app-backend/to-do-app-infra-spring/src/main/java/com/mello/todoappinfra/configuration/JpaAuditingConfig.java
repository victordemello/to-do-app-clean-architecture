package com.mello.todoappinfra.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Configuração para habilitar JPA Auditing.
 * Permite que @CreatedDate e @LastModifiedDate funcionem automaticamente.
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
