package com.hashmapinc.tempus.config;

import com.hashmapinc.tempus.enums.DriverType;
import com.hashmapinc.tempus.enums.EnvironmentType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@ComponentScan("com.hashmapinc.tempus")
public class AppConfig {
    private EnvironmentType environmentType;
    private DriverType driverType;
    private boolean windowMaximize;
    private String driverPath;
    private int implicitlyWait;
    private String host;
    private String testDataResourcePath;
    //private String reportConfigPath;
}
