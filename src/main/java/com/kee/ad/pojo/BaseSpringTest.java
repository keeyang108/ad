package com.kee.ad.pojo;

import com.kee.ad.AdApplication;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author KeeYang on 2017/8/2.
 * @Description :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/*.xml"})
@SpringBootTest(classes=AdApplication.class)
@Configuration
//@Transactional
public class BaseSpringTest {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
