package com.web.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.web.dto.BookDto;
import com.web.madel.Books;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@SpringBootTest
public class BookControllerTest {

    private final Gson gson = new GsonBuilder().create();

    @Resource
    private WebApplicationContext applicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
            mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
    }

    @Test
    public void testAll() throws Exception {
        testCreate();
        testUpdate();
        testDelete();
    }

    @Test
    public void testCreate() throws Exception {
        BookDto bookDto = new BookDto();
        bookDto.setName("Java Book");
        bookDto.setCity("UK");
        String json = gson.toJson(bookDto);
        MockHttpServletRequestBuilder requestBuilderOne =
                MockMvcRequestBuilders.post("/onetooneunidiractional/create");
        requestBuilderOne.contentType(MediaType.APPLICATION_JSON);
        requestBuilderOne.content(json.getBytes());
        this.mockMvc.perform(requestBuilderOne).andExpect(MockMvcResultMatchers.status().isOk());

    }

    public void testUpdate() throws Exception {
        MockHttpServletRequestBuilder requestBuilder2 =
                MockMvcRequestBuilders.get("/onetooneunidiractional/findAll");
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder2).andReturn();
        String respone = mvcResult.getResponse().getContentAsString();
        Type listType = new TypeToken<List<BookDto>>(){}.getType();
        List<BookDto> bookDtos = gson.fromJson(respone, listType);
        BookDto bookDto = bookDtos.get(0);
        bookDto.setName("Java EE");
        String json = gson.toJson(bookDto);
        MockHttpServletRequestBuilder requestBuilder3 = MockMvcRequestBuilders
                .post("/onetooneunidiractional/edit");
        requestBuilder3.contentType(MediaType.APPLICATION_JSON);
        requestBuilder3.content(json.getBytes());
        this.mockMvc.perform(requestBuilder3).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testDelete() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/onetooneunidiractional/findAll");
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();
        String response2 = mvcResult.getResponse().getContentAsString();
        Type listType = new TypeToken<List<BookDto>>(){}.getType();
        List<BookDto> dtoList= gson.fromJson(response2, listType);
        BookDto bookDto = dtoList.get(0);
        MockHttpServletRequestBuilder requestBuilder1 =
                MockMvcRequestBuilders.post("/onetooneunidiractional/remove/"+bookDto.getId());
            requestBuilder1.contentType(MediaType.APPLICATION_JSON);
            this.mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().is(204));
    }
}
