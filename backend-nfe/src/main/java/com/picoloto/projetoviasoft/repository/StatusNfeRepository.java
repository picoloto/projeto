package com.picoloto.projetoviasoft.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.picoloto.projetoviasoft.domain.StatusNfe;

public interface StatusNfeRepository extends JpaRepository<StatusNfe, Integer> {

	/*
	 * Retorna lista com todos que houveram indisponibilidade, pois pelos testes
	 * executados pode haver mais de um registro com maior indisponibilidade.
	 */
	@Transactional(readOnly = true)
	@Query("SELECT MAX(obj.autorizador), COUNT(obj.status) FROM StatusNfe obj WHERE obj.status = 'OFF' GROUP BY obj.autorizador")
	List<?> findMaiorIndisponibilidade();
	
	@Transactional(readOnly = true)
	List<?> findByDataHoraStatus(Date dataHoraStatus);

}
