package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.dao.ViagemRepository;
import com.viacaovv.trabalhoengenharia.entity.Motorista;
import com.viacaovv.trabalhoengenharia.entity.Viagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViagemServiceImpl implements ViagemService{

    private ViagemRepository viagemRepository;

    @Autowired
    public ViagemServiceImpl(ViagemRepository viagemRepository) {
        this.viagemRepository = viagemRepository;
    }

    @Override
    public List<Viagem> findAll() {
        return viagemRepository.findAll();
    }

    @Override
    public Viagem findById(int id) {
        Optional<Viagem> result = viagemRepository.findById(id);

        Viagem viagem = null;

        if (result.isPresent()) {
            viagem = result.get();
        }
        else {
            // viagem nao encontrado
            throw new RuntimeException("Viagem de id " + id + " nao encontrado.");
        }

        return viagem;
    }

    @Override
    public Viagem save(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    @Override
    public void deleteById(int id) {
        viagemRepository.deleteById(id);
    }

    @Override
    public List<Viagem> findViagemByOrigemAndDestinoAndData(String origem, String destino, String dataViagem) {
        return viagemRepository.findViagemByOrigemAndDestinoAndData(origem, destino, dataViagem);
    }

    @Override
    public List<Viagem> findViagemByData(String dataViagem) {
        return viagemRepository.findViagemByData(dataViagem);
    }

    @Override
    public List<Viagem> findViagemByOrigem(String origem) {
        return viagemRepository.findViagemByOrigem(origem);
    }
}
