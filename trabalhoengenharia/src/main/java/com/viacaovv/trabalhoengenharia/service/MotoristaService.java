package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.entity.Motorista;

import java.util.List;

public interface MotoristaService {

    List<Motorista> findAll();

    Motorista findById(int id);

    Motorista save(Motorista motorista);

    void deleteById(int id);

}
