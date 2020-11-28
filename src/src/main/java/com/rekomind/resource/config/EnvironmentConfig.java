package com.rekomind.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Environment Config using maven profiles.
 *
 * @author Estanislao Pérez Nartallo
 */
@Configuration
@PropertySource("classpath:/${info.app.env.id}/application-${info.app.env.id}.properties")
public class EnvironmentConfig {

}
