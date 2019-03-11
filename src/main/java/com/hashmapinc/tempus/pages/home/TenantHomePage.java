package com.hashmapinc.tempus.pages.home;

import com.hashmapinc.tempus.managers.WebDriverManager;
import org.springframework.stereotype.Component;

@Component
public class TenantHomePage extends HomePage {
    protected TenantHomePage(WebDriverManager webDriverManager) {
        super(webDriverManager);
    }
}
