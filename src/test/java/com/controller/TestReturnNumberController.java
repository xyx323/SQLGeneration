package com.controller;

import com.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by Bruinx on 2017/12/11.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestReturnNumberController {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testReturnNumber0() throws Exception {

        String jsonString = "{\"returnNumber\":100}";

        String respStr0 = mockMvc.perform(post("/setReturnNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(0)))
                .andExpect(jsonPath("$.info",is("正常")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost0.resp:"+respStr0);

    }
    @Test
    public void testReturnNumber1() throws Exception {

        String jsonString = "{\"rettttttttttttttturnNumber\":100}";

        String respStr0 = mockMvc.perform(post("/setReturnNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(4)))
                .andExpect(jsonPath("$.info",is("未找到对应参数")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost0.resp:"+respStr0);

    }
    @Test
    public void testReturnNumber3() throws Exception {

        String jsonString = "{\"returnNumber\":\"teststring\"}";

        String respStr0 = mockMvc.perform(post("/setReturnNumber")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(1)))
                .andExpect(jsonPath("$.info",is("参数类型错误")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost0.resp:"+respStr0);

    }

}



