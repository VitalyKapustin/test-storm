package test.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kapustin Vitaly on 8/1/15.
 */
@Configuration
@ComponentScan("test")
@PropertySource({ "classpath:application.properties" })
@EnableCouchbaseRepositories(basePackages = "test.repository", repositoryImplementationPostfix = "Custom")
public class ApplicationConfig extends AbstractCouchbaseConfiguration {

    @Value("${couchbase.host}")
    private String couchbaseHost;

    @Value("${couchbase.bucket.name}")
    private String couchbaseBucketName;

    @Value("${couchbase.password}")
    private String couchbasePassword;

    @Bean
    public PropertyPlaceholderConfigurer getPropertyPlaceholderConfigurer() {
        return new PropertyPlaceholderConfigurer();
    }

    @Override
    protected List<String> bootstrapHosts() {
        return Arrays.asList(couchbaseHost);
    }

    @Override
    protected String getBucketName() {
        return couchbaseBucketName;
    }

    @Override
    protected String getBucketPassword() {
        return couchbasePassword;
    }
}
