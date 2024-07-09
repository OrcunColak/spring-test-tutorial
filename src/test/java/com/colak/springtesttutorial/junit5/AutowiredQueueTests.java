package com.colak.springtesttutorial.junit5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

// See https://github.com/spring-projects/spring-framework/issues/30022#issuecomment-1443924257
@SpringJUnitConfig
class AutowiredQueueTests {

    @Autowired
    Queue<String> queue;

    @Test
    void test() {
        assertThat(this.queue).containsExactly("foo", "bar");
    }

    @Configuration
    static class Config {

        @Bean
        String foo() {
            return "foo";
        }

        @Bean
        String bar() {
            return "bar";
        }

        @Bean
        Queue<String> queue() {
            Queue<String> queue = new ArrayDeque<>();
            queue.add(foo());
            queue.add(bar());
            return queue;
        }
    }

}
