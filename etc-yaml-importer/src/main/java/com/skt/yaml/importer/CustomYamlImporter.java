package com.skt.yaml.importer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;

@Slf4j
public class CustomYamlImporter implements EnvironmentPostProcessor, Ordered {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        String[] activeProfiles = environment.getActiveProfiles();
        Assert.notEmpty(activeProfiles, "Profiles should not empty");

        String activeProfile = activeProfiles[0];
        String resourcesFilePath = "classpath*:*-" + activeProfile + ".yaml";

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

        try {
            Resource[] resources = resourcePatternResolver.getResources(resourcesFilePath);
            for (Resource resource : resources) {
                List<PropertySource<?>> result = null;

                if (resource.exists()) {
                    result = loader.load(resource.getDescription(), resource);
                }

                if (result != null) {
                    result.forEach(environment.getPropertySources()::addLast);
                }
            }
        } catch (IOException e) {
            log.error("err loading of yaml files of " + resourcesFilePath);
            throw new IllegalStateException("err loading of yaml files of " + resourcesFilePath, e);
        } finally {
            log.info("br");
        }
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}