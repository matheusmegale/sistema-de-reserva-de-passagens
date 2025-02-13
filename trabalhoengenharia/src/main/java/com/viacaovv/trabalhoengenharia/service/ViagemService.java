package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.entity.Viagem;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViagemService {

    List<Viagem> findAll();

    Viagem findById(int id);

    Viagem save(Viagem viagem);

    void deleteById(int id);

    List<Viagem> findViagemByOrigemAndDestinoAndData(String origem, String destino, String dataViagem);

    List<Viagem> findViagemByData(String dataViagem);

    List<Viagem> findViagemByOrigem(String origem);
}


