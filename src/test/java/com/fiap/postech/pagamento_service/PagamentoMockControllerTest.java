package com.fiap.postech.pagamento_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.postech.pagamento_service.dto.PagamentoRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PagamentoMockController.class)
class PagamentoMockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Deve aprovar pagamento quando valor não é múltiplo de 13")
    void testProcessarPagamentoAprovado() throws Exception {
        PagamentoRequest request = new PagamentoRequest();
        request.setValorTotalPedido(new BigDecimal("25")); // 25 % 13 != 0

        mockMvc.perform(post("/api/pagamentos/processar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.aprovado").value(true))
                .andExpect(jsonPath("$.mensagem").value("Pagamento aprovado (mock)"));
    }

    @Test
    @DisplayName("Deve recusar pagamento quando valor é múltiplo de 13")
    void testProcessarPagamentoRecusado() throws Exception {
        PagamentoRequest request = new PagamentoRequest();
        request.setValorTotalPedido(new BigDecimal("26")); // 26 % 13 == 0

        mockMvc.perform(post("/api/pagamentos/processar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isPaymentRequired())
                .andExpect(jsonPath("$.aprovado").value(false))
                .andExpect(jsonPath("$.mensagem").value("Pagamento recusado (mock)"));
    }

    @Test
    @DisplayName("Deve recusar pagamento quando valorTotalPedido é null")
    void testProcessarPagamentoNull() throws Exception {
        PagamentoRequest request = new PagamentoRequest();
        request.setValorTotalPedido(null);

        mockMvc.perform(post("/api/pagamentos/processar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isPaymentRequired())
                .andExpect(jsonPath("$.aprovado").value(false))
                .andExpect(jsonPath("$.mensagem").value("Pagamento recusado (mock)"));
    }
}
