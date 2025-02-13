package com.viacaovv.trabalhoengenharia.dao;

import com.viacaovv.trabalhoengenharia.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query("SELECT c FROM Cliente c WHERE c.email = :email AND c.senha = :senha")
    Cliente findClienteByEmailAndSenha(@Param("email") String email, @Param("senha") String senha);

}
