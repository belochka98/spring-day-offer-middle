package com.onedayoffer.taskdistribution.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Main configuration class of application</p>
 *
 * @author Sviridov_V_A
 * @version 1.0.0-SNAPSHOT
 * @since 2024-03-23
 */
@Configuration
public class ApplicationConfiguration {
    /**
     * <p>Add bean of model mapper</p>
     */
    @Bean
    public ModelMapper getModelMapper() {
        final var modelMapper = new ModelMapper();
        final var conf = modelMapper.getConfiguration();
        conf.setFieldMatchingEnabled(true);
        conf.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        conf.setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        return modelMapper;
    }
}