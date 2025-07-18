package com.fiap.postech.pagamento_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoResponse {
    private boolean aprovado;
    private String mensagem;
}
