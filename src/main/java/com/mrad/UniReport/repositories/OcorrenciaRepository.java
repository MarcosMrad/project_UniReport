package com.mrad.UniReport.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mrad.UniReport.entities.Ocorrencia;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long>{

    @Query("SELECT o FROM Ocorrencia o WHERE o.localizacao.bloco = :bloco")
	List<Ocorrencia> findByBloco(@Param("bloco") String bloco);
}
