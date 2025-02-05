package br.com.desafio.remessa.controllers;

import br.com.desafio.remessa.domains.transacao.Transacao;
import br.com.desafio.remessa.dtos.TransacaoDTO;
import br.com.desafio.remessa.services.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("remessa")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Transacao> criarTransacao(@RequestBody TransacaoDTO transacaoDTO) throws Exception {
        Transacao transacao = transacaoService.realizarRemessa(transacaoDTO);
        return ResponseEntity.ok(transacao);
    }

}
