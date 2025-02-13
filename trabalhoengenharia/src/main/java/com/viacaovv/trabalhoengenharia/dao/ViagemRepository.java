package com.viacaovv.trabalhoengenharia.dao;

import com.viacaovv.trabalhoengenharia.entity.Cliente;
import com.viacaovv.trabalhoengenharia.entity.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ViagemRepository extends JpaRepository<Viagem, Integer> {

    @Query("SELECT v FROM Viagem v WHERE v.origem = :origem AND v.destino = :destino AND v.dataViagem = :data")
    List<Viagem> findViagemByOrigemAndDestinoAndData(@Param("origem") String origem, @Param("destino") String destino, @Param("data") String dataViagem);

    @Query("SELECT v FROM Viagem v WHERE v.dataViagem = :data")
    List<Viagem> findViagemByData(@Param("data") String dataViagem);

    @Query("SELECT v FROM Viagem v WHERE v.origem = :origem")
    List<Viagem> findViagemByOrigem(@Param("origem") String origem);

}
