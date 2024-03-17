package co.edu.unbosque.syscourier.syscourier;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource("classpath:config/appConfig.properties")
class SysCourierBackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
