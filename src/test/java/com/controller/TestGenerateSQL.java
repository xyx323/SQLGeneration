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

import javax.annotation.Resource;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by Bruinx on 2017/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class TestGenerateSQL {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestObjectController testObjectController;

    private TestFilterController testFilterController;

    private TestDistinctRecordsController testDistinctRecordsController;

    private TestOrderController testOrderController;

    private TestReturnNumberController testReturnNumberController;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testGenerateSQL0() throws Exception {
        testObjectController.testSetObject0();
        testFilterController.testSetFilter0();
        testFilterController.testSetPredefinedFilter0();
        testOrderController.testSetOrder0();
        testDistinctRecordsController.testSetDistinctRecords0();
        testReturnNumberController.testReturnNumber0();

        String respStr0 = mockMvc.perform(get("/generateSQL"))
                .andReturn().getResponse().getContentAsString();

        System.out.println("testPost.resp:"+respStr0);
    }

}



