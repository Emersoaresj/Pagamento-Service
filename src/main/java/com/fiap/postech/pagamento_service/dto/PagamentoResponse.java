package com.fiap.postech.pagamento_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resposta do processamento de pagamento")
public class PagamentoResponse {

    @Schema(description = "Indica se o pagamento foi aprovado", example = "true")
    private boolean aprovado;

    @Schema(description = "Mensagem detalhando o resultado do pagamento", example = "Pagamento aprovado (mock)")
    private String mensagem;
}
