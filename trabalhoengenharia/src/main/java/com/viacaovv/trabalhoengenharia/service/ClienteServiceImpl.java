package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.dao.ClienteRepository;
import com.viacaovv.trabalhoengenharia.entity.Cliente;
import com.viacaovv.trabalhoengenharia.entity.Motorista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(int id) {
        Optional<Cliente> result = clienteRepository.findById(id);

        Cliente cliente = null;

        if (result.isPresent()) {
            cliente = result.get();
        }
        else {
            // cliente nao encontrado
            throw new RuntimeException("Cliente de id " + id + " nao encontrado.");
        }

        return cliente;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente findClienteByEmailAndSenha(String email, String senha) {
        return clienteRepository.findClienteByEmailAndSenha(email, senha);
    }
}
