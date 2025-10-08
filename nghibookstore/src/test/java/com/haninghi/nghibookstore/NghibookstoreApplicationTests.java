package com.haninghi.nghibookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import com.haninghi.nghibookstore.web.BookController;

@SpringBootTest
class NghibookstoreApplicationTests {

    @Autowired
    private BookController bookController;

    @Test
    public void contextLoads() throws Exception {
        assertThat(bookController).isNotNull();
    }

}
