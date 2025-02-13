package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.dao.MotoristaRepository;
import com.viacaovv.trabalhoengenharia.entity.Motorista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotoristaServiceImpl implements MotoristaService {

    private MotoristaRepository motoristaRepository;

    @Autowired
    public MotoristaServiceImpl(MotoristaRepository motoristaRepository) {
        this.motoristaRepository = motoristaRepository;
    }

    @Override
    public List<Motorista> findAll() {
        return motoristaRepository.findAll();
    }

    @Override
    public Motorista findById(int id) {
        Optional<Motorista> result = motoristaRepository.findById(id);

        Motorista motorista = null;

        if (result.isPresent()) {
            motorista = result.get();
        }
        else {
            // motorista nao encontrado
            throw new RuntimeException("Motorista de id " + id + " nao encontrado.");
        }

        return motorista;
    }

    @Override
    public Motorista save(Motorista motorista) {
         return motoristaRepository.save(motorista);
    }

    @Override
    public void deleteById(int id) {
        motoristaRepository.deleteById(id);
    }
}
