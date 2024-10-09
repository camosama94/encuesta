package com.cmolina.encuestaSatis.repository;

import com.cmolina.encuestaSatis.entity.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta,Long>{
    List<Encuesta> findByMotivo(String motivo);
}

