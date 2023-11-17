package com.proyectoprogra3.proyectoprogra3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProyectController.class)
public class ProyectoControllerListTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetInfoList() throws Exception {
        // Perform a GET request to the /getinfo/list endpoint
        mockMvc.perform(get("/getinfo/list"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("3 Dylan Mathews")));
    }

    @Test
    public void testAddMemberToList() throws Exception {
        // Define the request body with the member data to add
        String memberToAdd = "testparam";
        mockMvc.perform(get("/getinfo/list"));
        // Perform a POST request to the /getinfo/list/add endpoint
        mockMvc.perform(post("/getinfo/list/add")
                .param("member", memberToAdd)
                .contentType("text/plain"))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"1 Bob Jones\",\"2 Ann Harrick\",\"3 Dylan Mathews\",\"testparam\"]"));
    }
}
