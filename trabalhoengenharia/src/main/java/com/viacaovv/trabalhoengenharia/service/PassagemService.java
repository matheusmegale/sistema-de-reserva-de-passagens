package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.entity.Passagem;

import java.util.List;

public interface PassagemService {

    List<Passagem> findAll();

    Passagem findById(int id);

    Passagem save(Passagem passagem);

    void deleteById(int id);

    Passagem findPassagemByIdViagemAndNPoltrona(int idViagem, int nPoltrona);

}