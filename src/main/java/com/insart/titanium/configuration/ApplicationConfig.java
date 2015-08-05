package com.insart.titanium.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Collections;
import java.util.List;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
@Configuration
@ComponentScan("com.insart.titanium")
@PropertySource("classpath:application.properties")
@EnableCouchbaseRepositories(basePackages = "com.insart.titanium.repository", repositoryImplementationPostfix = "CustomImpl")
public class ApplicationConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.host}")
    private String COUCHBASE_HOST;

    @Value("${couchbase.bucket.name}")
    private String COUCHBASE_BUCKET_NAME;

    @Value("${couchbase.password}")
    private String COUCHBASE_PASSWORD;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    protected List<String> bootstrapHosts() {
        return Collections.singletonList(COUCHBASE_HOST);
    }

    @Override
    protected String getBucketName() {
        return COUCHBASE_BUCKET_NAME;
    }

    @Override
    protected String getBucketPassword() {
        return COUCHBASE_PASSWORD;
    }
}
