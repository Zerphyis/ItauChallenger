package dev.zerphyis.itauChallenger.Infra.Controller;

import dev.zerphyis.itauChallenger.Application.Dto.EstatisticasResponse;
import dev.zerphyis.itauChallenger.Application.UseCase.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {
    private final TransacaoService service;

    public EstatisticaController(TransacaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<EstatisticasResponse> calcular() {
        EstatisticasResponse response = service.calcularEstatisticas();
        return ResponseEntity.ok(response);
    }
}
