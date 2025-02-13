package com.viacaovv.trabalhoengenharia.service;

import com.viacaovv.trabalhoengenharia.entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAll();

    Cliente findById(int id);

    Cliente save(Cliente cliente);

    Cliente findClienteByEmailAndSenha(String email, String senha);

}
