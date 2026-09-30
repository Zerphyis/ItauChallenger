package dev.zerphyis.itauChallenger.Infra.Controller;


import dev.zerphyis.itauChallenger.Application.Dto.TransacaoRequest;
import dev.zerphyis.itauChallenger.Application.UseCase.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService service;

    public TransacaoController(TransacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody TransacaoRequest dto){
        service.criarTransacao(dto.valor(),dto.datahora());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar() {
        service.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
