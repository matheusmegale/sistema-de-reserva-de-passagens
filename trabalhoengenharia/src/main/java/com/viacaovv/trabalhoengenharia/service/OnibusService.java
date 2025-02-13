package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.entity.Motorista;
import com.viacaovv.trabalhoengenharia.entity.Onibus;

import java.util.List;

public interface OnibusService {

    List<Onibus> findAll();

    Onibus findById(int id);

    Onibus save(Onibus onibus);

    void deleteById(int id);

}
