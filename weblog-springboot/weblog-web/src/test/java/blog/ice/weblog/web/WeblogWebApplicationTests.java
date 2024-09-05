package blog.ice.weblog.web;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class WeblogWebApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testLog(){
        log.info("test info");
        log.warn("test warn");
        log.error("test error");

        String name = "icedragon";
        log.info("Hello, I am {}", name);
    }

}
