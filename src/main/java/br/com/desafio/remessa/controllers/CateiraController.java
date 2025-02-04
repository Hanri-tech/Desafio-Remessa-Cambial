package br.com.desafio.remessa.controllers;

import br.com.desafio.remessa.domains.carteira.Carteira;
import br.com.desafio.remessa.dtos.CarteiraDTO;
import br.com.desafio.remessa.services.CarteiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CateiraController {

    @Autowired
    CarteiraService carteiraService;


    @PostMapping()
    public ResponseEntity<Carteira> cadastrarCarteira(@RequestBody CarteiraDTO carteiraDTO) {
        Carteira carteira = carteiraService.salvar(carteiraDTO);

        return ResponseEntity.ok(carteira);
    }

    @GetMapping()
    public ResponseEntity<List<Carteira>> buscarTodasCarteira() {
        List<Carteira> carteiras = carteiraService.buscarCarteiras();

        return ResponseEntity.ok(carteiras);
    }
}
