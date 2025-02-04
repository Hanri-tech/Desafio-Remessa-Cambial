package br.com.desafio.remessa.services;

import br.com.desafio.remessa.domains.carteira.Carteira;
import br.com.desafio.remessa.domains.carteira.TipoCarteira;
import br.com.desafio.remessa.dtos.CarteiraDTO;
import br.com.desafio.remessa.exceptions.DadosCateiraUnicoExisteException;
import br.com.desafio.remessa.exceptions.TipoUsuarioCarteiroNaoEncontradoException;
import br.com.desafio.remessa.repositories.CarteiraRepository;
import br.com.desafio.remessa.repositories.TipoCarteiraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteiraService {

    @Autowired
    private CarteiraRepository carteiraRepository;
    @Autowired
    private TipoCarteiraRepository tipoCarteiraRepository;

    @CacheEvict(value = "carteiras", allEntries = true)
    public Carteira salvar(CarteiraDTO carteiraDTO) {
        Carteira carteira = new Carteira();
        carteira.setNomeCompleto(carteiraDTO.nomeCompleto());
        carteira.setDocumento(carteiraDTO.documento());
        carteira.setEmail(carteiraDTO.email());
        carteira.setSenha(carteiraDTO.senha());
        carteira.setSaldoBRL(carteiraDTO.saldoBRL());
        carteira.setSaldoUSD(carteiraDTO.saldoUSD());

        carteiraRepository.findByDocumentoOrEmail(carteiraDTO.documento(),
                carteiraDTO.email()).ifPresent( carteira1 -> { throw new DadosCateiraUnicoExisteException(); });


        TipoCarteira tipoCarteira = tipoCarteiraRepository.findById(carteiraDTO.tipoCarteira())
                .orElseThrow(TipoUsuarioCarteiroNaoEncontradoException::new);

        carteira.setTipoCarteira(tipoCarteira);
        try {
            return carteiraRepository.save(carteira);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
//            throw new CampoObrigatorioNuloExceptions();
        }
    }

    @Cacheable("carteiras")
    public List<Carteira> buscarCarteiras() {
        return carteiraRepository.findAll();
    }

}