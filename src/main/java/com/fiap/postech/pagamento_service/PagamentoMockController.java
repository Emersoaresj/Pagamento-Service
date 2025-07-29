package com.fiap.postech.pagamento_service;

import com.fiap.postech.pagamento_service.dto.PagamentoRequest;
import com.fiap.postech.pagamento_service.dto.PagamentoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoMockController {

    @Operation(
            summary = "Processa um pagamento (mock)",
            description = "Retorna aprovação ou recusa do pagamento de acordo com o valor informado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento aprovado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PagamentoResponse.class),
                    examples = @ExampleObject(value = """
                {
                    "aprovado": true,
                    "mensagem": "Pagamento aprovado (mock)"
                }
            """)
            )),
            @ApiResponse(responseCode = "402", description = "Pagamento recusado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = PagamentoResponse.class),
                    examples = @ExampleObject(value = """
                {
                    "aprovado": false,
                    "mensagem": "Pagamento recusado (mock)"
                }
            """)
            )),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor", content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(value = "{\"mensagem\": \"Erro interno!\"}")
            ))
    })
    @PostMapping("/processar")
    public ResponseEntity<PagamentoResponse> processarPagamento(@RequestBody PagamentoRequest request) {
        boolean aprovado = request.getValorTotalPedido() != null && request.getValorTotalPedido().remainder(BigDecimal.valueOf(13)).compareTo(BigDecimal.ZERO) != 0;
        PagamentoResponse response;
        if (aprovado) {
            response = new PagamentoResponse(true, "Pagamento aprovado (mock)");
            return ResponseEntity.ok(response);
        } else {
            response = new PagamentoResponse(false, "Pagamento recusado (mock)");
            return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
        }
    }
}

