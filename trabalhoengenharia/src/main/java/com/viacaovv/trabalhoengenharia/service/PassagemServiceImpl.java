package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.dao.PassagemRepository;
import com.viacaovv.trabalhoengenharia.entity.Motorista;
import com.viacaovv.trabalhoengenharia.entity.Passagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassagemServiceImpl implements PassagemService {

    private PassagemRepository passagemRepository;

    @Autowired
    public PassagemServiceImpl(PassagemRepository passagemRepository) {
        this.passagemRepository = passagemRepository;
    }

    @Override
    public List<Passagem> findAll() {
        return passagemRepository.findAll();
    }

    @Override
    public Passagem findById(int id) {
        Optional<Passagem> result = passagemRepository.findById(id);

        Passagem passagem = null;

        if (result.isPresent()) {
            passagem = result.get();
        }
        else {
            // passagem nao encontrada
            throw new RuntimeException("Passagem de id " + id + " nao encontrado.");
        }

        return passagem;
    }

    @Override
    public Passagem save(Passagem passagem) {
        return passagemRepository.save(passagem);
    }

    @Override
    public void deleteById(int id) {
        passagemRepository.deleteById(id);
    }

    @Override
    public Passagem findPassagemByIdViagemAndNPoltrona(int idViagem, int nPoltrona) {
        return passagemRepository.findPassagemByIdViagemAndNPoltrona(idViagem, nPoltrona);
    }
}