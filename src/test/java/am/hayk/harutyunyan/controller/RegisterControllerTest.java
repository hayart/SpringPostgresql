package am.hayk.harutyunyan.controller;

import am.hayk.harutyunyan.payload.request.ClientRequest;
import am.hayk.harutyunyan.payload.response.ClientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import am.hayk.harutyunyan.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class RegisterControllerTest {
/*
        @Autowired
        MockMvc mockMvc;

        @Autowired
        ObjectMapper objectMapper;

        @MockBean
        ClientService clientService;

        @Test
        void shouldLoadUploadedFile() throws Exception {

            ClientRequest request = ClientRequest.builder()
                    .clientId(UUID.randomUUID())
                    .referralCode("AFTH8KN")
                    .landingPage("frompage")
                    .userAgent("Google Chrome")
                    .ip("10.245.23.48")
                    .build();
            when(clientService.process(any(ClientRequest.class))).thenReturn(ClientResponse.class);

            mockMvc.perform(post("/register/client")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)))
                    .andExpect(status().isOk());
        }
*/


}
