package com.israelgestaoos.apitechone.repository;

import com.israelgestaoos.apitechone.model.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    List<OrdemServico> findByTecnicoId(Long tecnicoId);
}
