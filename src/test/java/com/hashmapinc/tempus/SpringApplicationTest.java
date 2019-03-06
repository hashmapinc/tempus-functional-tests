package com.hashmapinc.tempus;

import com.hashmapinc.tempus.config.AppConfig;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfig.class)
public abstract class SpringApplicationTest {
}
