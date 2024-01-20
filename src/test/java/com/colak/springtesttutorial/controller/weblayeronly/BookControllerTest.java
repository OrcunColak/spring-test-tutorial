package com.colak.springtesttutorial.controller.weblayeronly;

import com.colak.springtesttutorial.controller.BookController;
import com.colak.springtesttutorial.jpa.Book;
import com.colak.springtesttutorial.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

/**
 * Instantiate only the web layer rather than the whole context
 * These tests are not very useful
 */
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreate() throws Exception {
        var resultBook = new Book(1L, "John Doe", "123", LocalDate.now(), false);
        Mockito.when(bookService.create(Mockito.any())).thenReturn(resultBook);

        var sentBook = new Book(null, "John Doe", "123", LocalDate.now(), false);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/book")
                .content(objectMapper.writeValueAsString(sentBook))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andDo(MockMvcResultHandlers.print());
    }
}
