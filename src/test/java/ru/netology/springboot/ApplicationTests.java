package ru.netology.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.shaded.okhttp3.ResponseBody;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

    private final GenericContainer<?> devAppTest = new GenericContainer<>("devapptestd:1.0")
            .withExposedPorts(8080);
    private final GenericContainer<?> prodAppTest = new GenericContainer<>("prodapptestd:1.0")
            .withExposedPorts(8081);
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        devAppTest.start();
        prodAppTest.start();
    }

    @Test
    void validTestDev() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devAppTest.getMappedPort(8080) + "/profile", String.class);
        assertEquals(forEntity.getBody(), "Current profile is dev");
    }

    @Test
    void validTestProd() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodAppTest.getMappedPort(8081) + "/profile", String.class);
        assertEquals(forEntity.getBody(), "Current profile is production");
    }
}