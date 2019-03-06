package com.hashmapinc.tempus.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("user")
public class UserConfig {
    private TenantUser tenant;
    private CustomerUser customer;
    private SysAdminUser sysAdmin;

    @Getter
    @Setter
    public static class TenantUser extends User {

    }

    @Getter
    @Setter
    public static class CustomerUser extends User {

    }

    @Getter
    @Setter
    public static class SysAdminUser extends User {

    }

    @Getter
    @Setter
    public static class User {
        protected String username;
        protected String password;
    }
}
