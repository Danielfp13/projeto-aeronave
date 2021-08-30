package br.com.teste.sonda.aeronave.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.teste.sonda.aeronave.entity.Aeronave;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {

}
