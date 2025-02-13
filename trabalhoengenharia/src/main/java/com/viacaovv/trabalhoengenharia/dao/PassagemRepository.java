
package com.viacaovv.trabalhoengenharia.dao;

import com.viacaovv.trabalhoengenharia.entity.Passagem;
import com.viacaovv.trabalhoengenharia.entity.Viagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Integer> {

    @Query("SELECT p FROM Passagem p WHERE p.idViagem = :idViagem AND p.nPoltrona = :nPoltrona")
    Passagem findPassagemByIdViagemAndNPoltrona(@Param("idViagem") int idViagem, @Param("nPoltrona") int nPoltrona);

}