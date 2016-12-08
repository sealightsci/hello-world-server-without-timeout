package io.demo;

import org.testng.annotations.Test;

import java.io.IOException;

public class AppTest {

    @Test
    public void test1() throws IOException {
        App app = new App();
        app.startServer();
    }

}
