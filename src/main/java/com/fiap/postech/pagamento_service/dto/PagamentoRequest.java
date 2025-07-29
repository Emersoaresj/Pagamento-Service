package com.fiap.postech.pagamento_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request para processar pagamento")
public class PagamentoRequest {

    @Schema(description = "ID do pedido", example = "123")
    private Integer idPedido;

    @Schema(description = "ID do cliente", example = "456")
    private Integer idCliente;

    @Schema(description = "Valor total do pedido", example = "299.90")
    private BigDecimal valorTotalPedido;
}
