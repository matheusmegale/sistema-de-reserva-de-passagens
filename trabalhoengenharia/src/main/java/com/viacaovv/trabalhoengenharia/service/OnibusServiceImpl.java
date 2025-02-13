package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.dao.OnibusRepository;
import com.viacaovv.trabalhoengenharia.entity.Motorista;
import com.viacaovv.trabalhoengenharia.entity.Onibus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OnibusServiceImpl implements OnibusService{

    private OnibusRepository onibusRepository;

    @Autowired
    public OnibusServiceImpl(OnibusRepository onibusRepository) {
        this.onibusRepository = onibusRepository;
    }

    @Override
    public List<Onibus> findAll() {
        return onibusRepository.findAll();
    }

    @Override
    public Onibus findById(int id) {
        Optional<Onibus> result = onibusRepository.findById(id);

        Onibus onibus = null;

        if (result.isPresent()) {
            onibus = result.get();
        }
        else {
            // onibus nao encontrado
            throw new RuntimeException("Onibus de id " + id + " nao encontrado.");
        }

        return onibus;
    }

    @Override
    public Onibus save(Onibus onibus) {
        return onibusRepository.save(onibus);
    }

    @Override
    public void deleteById(int id) {
        onibusRepository.deleteById(id);
    }
}
