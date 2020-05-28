package com.address.api.controller;

import com.address.api.exception.MessageErrorImpl;
import com.address.api.util.MessagesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void save() throws Exception {
        mockMvc.perform(post("/api/address")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"city\": \"OCIDENTAL\",\n" +
                        "  \"neighborhood\": \"JARDIM ABC\",\n" +
                        "  \"state\": \"GO\",\n" +
                        "  \"zipCode\": \"72888000\"\n" +
                        "}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.state", is("GO")))
                .andExpect(jsonPath("city", is("OCIDENTAL")))
                .andExpect(jsonPath("neighborhood", is("JARDIM ABC")))
                .andExpect(jsonPath("zipCode", is("72888000")));

    }

    @Test
    void insertWithInvalidRequestBody() throws Exception {
        mockMvc.perform(post("/api/address")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.path", is("/api/address")))
                .andExpect(jsonPath("$.messages[0]", is(MessagesUtil.getMessage(MessageErrorImpl.MALFORMED_REQUEST_JSON))));
    }

    @Test
    public void insertWithInvalidStateInEnglish() throws Exception {

        mockMvc.perform(post("/api/address?lang=pt")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"city\": \"OCIDENTAL\",\n" +
                        "  \"neighborhood\": \"JARDIM ABC\",\n" +
                        "  \"state\": \"\",\n" +
                        "  \"zipCode\": \"72888000\"\n" +
                        "}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error", is("Bad Request")))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.path", is("/api/address")))
                .andExpect(jsonPath("$.messages[0]", is("state deve ser preenchido")));
    }

    @Test
    void getAddress() throws Exception {

        mockMvc.perform(get("/api/address/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.zipCode", is("72954023")))
                .andExpect(jsonPath("$.city", is("BRASILIA")))
                .andExpect(jsonPath("$.neighborhood", is("ASA SUL")))
                .andExpect(jsonPath("$.state", is("DF")));
    }

    @Test
    void getAddressNotFound() throws Exception {

        mockMvc.perform(get("/api/address/19?lang=en")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error", is("Not Found")))
                .andExpect(jsonPath("$.timestamp", notNullValue()))
                .andExpect(jsonPath("$.status", is(404)))
                .andExpect(jsonPath("$.path", is("/api/address/19")))
                .andExpect(jsonPath("$.messages[0]", is("Address not found!")));

    }

    @Test
    void getAddressAll() throws Exception {
        mockMvc.perform(get("/api/address")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].zipCode", is("71005698")))
                .andExpect(jsonPath("$[1].city", is("GAMA")))
                .andExpect(jsonPath("$[1].neighborhood", is("SETOR SUL")))
                .andExpect(jsonPath("$[1].state", is("DF")))

                .andExpect(jsonPath("$[2].zipCode", is("70582695")))
                .andExpect(jsonPath("$[2].city", is("SOBRADINHO")))
                .andExpect(jsonPath("$[2].neighborhood", is("AREAL 2")))
                .andExpect(jsonPath("$[2].state", is("DF")));
    }

    @Test
    void getAddressAllFilterZipCode() throws Exception {
        mockMvc.perform(get("/api/address?zipCode=71005698")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":2,\"zipCode\":\"71005698\",\"city\":\"GAMA\",\"neighborhood\":\"SETOR SUL\",\"state\":\"DF\"}]"));
    }
    @Test
    void getAddressAllFilterState() throws Exception {
        mockMvc.perform(get("/api/address?page=0&size=3")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"zipCode\":\"72954023\",\"city\":\"BRASILIA\",\"neighborhood\":\"ASA SUL\",\"state\":\"DF\"},{\"id\":2,\"zipCode\":\"71005698\",\"city\":\"GAMA\",\"neighborhood\":\"SETOR SUL\",\"state\":\"DF\"},{\"id\":3,\"zipCode\":\"70582695\",\"city\":\"SOBRADINHO\",\"neighborhood\":\"AREAL 2\",\"state\":\"DF\"}]"));
    }


}