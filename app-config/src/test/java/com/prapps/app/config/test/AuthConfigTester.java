package com.prapps.app.config.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthConfigTester {
	 @Autowired
	 private MockMvc mvc;

	@Test
	public void testAuth() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/rest/rail/findRegion?lat=22.51986383&lon=88.37203592").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"name\":\"Howrah-Sealdah\",\"code\":\"er-suburban\"},{\"name\":\"Secunderabad\",\"code\":\"hyd-mmts\"}]"));
	}
}
