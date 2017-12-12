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
 * Created by Bruinx on 2017/12/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestObjectController {

    public MockMvc mockMvc;

    @Autowired
    public WebApplicationContext context;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testSetObject0() throws Exception {

        String jsonString = "{\"objects\":[1]}";

        String respStr0 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(0)))
                .andExpect(jsonPath("$.info",is("正常")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost0.resp:"+respStr0);

    }

    @Test
    public void testSetObject1() throws Exception {

        String jsonString = "{\"objects\":[1,2]}";

        String respStr1 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(0)))
                .andExpect(jsonPath("$.info",is("正常")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost1.resp:"+respStr1);

    }

    @Test
    public void testSetObject2() throws Exception {

        String jsonString = "{\"objects\":\"testString\"}";

        String respStr2 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(1)))
                .andExpect(jsonPath("$.info",is("参数类型错误")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr2);

    }

    @Test
    public void testSetObject3() throws Exception {

        String jsonString = "{\"objects\":1}";

        String respStr3 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(1)))
                .andExpect(jsonPath("$.info",is("参数类型错误")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr3);

    }

    @Test
    public void testSetObject4() throws Exception {

        String jsonString = "{\"objects\":[500,600]}";

        String respStr4 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(2)))
                .andExpect(jsonPath("$.info",is("找不到对象")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr4);

    }

    @Test
    public void testSetObject5() throws Exception {

        String jsonString = "{\"objects\":[\"500\",600]}";

        String respStr4 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(1)))
                .andExpect(jsonPath("$.info",is("参数类型错误")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr4);

    }

    @Test
    public void testSetObject6() throws Exception {

        String jsonString = "{\"objects\":500}";

        String respStr4 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(1)))
                .andExpect(jsonPath("$.info",is("参数类型错误")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr4);

    }

    @Test
    public void testSetObject7() throws Exception {

        String jsonString = "{\"objects\":true}";

        String respStr4 = mockMvc.perform(post("/setObjects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(1)))
                .andExpect(jsonPath("$.info",is("参数类型错误")))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr4);

    }

//    @Test
//    public void test() throws Exception{
//        this.testController0();
//        this.testController1();
//    }

}

