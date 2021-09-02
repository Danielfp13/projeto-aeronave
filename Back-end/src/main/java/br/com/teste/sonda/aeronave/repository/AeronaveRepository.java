package br.com.teste.sonda.aeronave.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioDecadaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioMarcaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioSemanaDTO;
import br.com.teste.sonda.aeronave.entity.Aeronave;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {

	@Query("SELECT new br.com.teste.sonda.aeronave.dto.AeronaveSomatorioDecadaDTO( (obj.ano / 10) * 10 , COUNT(obj.ano) ) FROM Aeronave AS obj GROUP BY (obj.ano/ 10) * 10 ")
	public List<AeronaveSomatorioDecadaDTO> somatorioAeronave();

	@Query("SELECT NEW br.com.teste.sonda.aeronave.dto.AeronaveSomatorioMarcaDTO(obj.marca, COUNT(obj.marca) ) FROM Aeronave AS obj GROUP BY obj.marca")
	public List<AeronaveSomatorioMarcaDTO> somatorioMarca();
	
	@Query("SELECT NEW br.com.teste.sonda.aeronave.dto.AeronaveSomatorioSemanaDTO( COUNT(obj.id) ) FROM Aeronave AS obj WHERE obj.created >= :data")
	public List<AeronaveSomatorioSemanaDTO> somatorioSemana(LocalDateTime data);

	@Query(value = "select * from aeronave a where a.marca like :marca or CAST(id AS TEXT) LIKE :id",nativeQuery = true)
	public Page<Aeronave> findByIdOrMarcaIgnoreCase(Pageable pageRequest, String id, String marca);
	
}
