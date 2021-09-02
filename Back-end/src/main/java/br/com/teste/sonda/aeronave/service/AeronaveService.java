package br.com.teste.sonda.aeronave.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.teste.sonda.aeronave.dto.AeronaveDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioDecadaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioMarcaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioSemanaDTO;
import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.repository.AeronaveRepository;
import br.com.teste.sonda.aeronave.service.exception.ObjectNotFoundException;

@Service
public class AeronaveService {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	// Buscar todas aeronaves
	public List<Aeronave> findAll() {
		return aeronaveRepository.findAll();
	}

	// Buscar aeronave por id
	public Aeronave find(Long id) {
		return aeronaveRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não exite aeronave com id = " + id + "."));
	}

	// inserir aeronave
	public Aeronave insert(AeronaveDTO aeronaveDTO) {
		Aeronave aeronave = new Aeronave();
		BeanUtils.copyProperties(aeronaveDTO, aeronave);
		aeronave.setCreated(LocalDateTime.now());
		return aeronaveRepository.save(aeronave);
	}

	// Alterar aeronave
	public Aeronave update(AeronaveDTO aeronaveDTO, Long id) {
		Aeronave newAeronave = find(id);
		BeanUtils.copyProperties(aeronaveDTO, newAeronave);
		newAeronave.setId(id);
		newAeronave.setUpdated(LocalDateTime.now());
		return aeronaveRepository.save(newAeronave);
	}

	// Excluir aeronave
	public void delete(Long id) {
		find(id);
		try {
			aeronaveRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não pode excluir aeronaves com associações.");
		}
	}

	// Somatório de aeronaves por década
	public List<AeronaveSomatorioDecadaDTO> countAeronave() {
		return aeronaveRepository.somatorioAeronave();
	}

	// Somatório de aeronaves por marca
	public List<AeronaveSomatorioMarcaDTO> countMarca() {
		return aeronaveRepository.somatorioMarca();
	}
	
	//pagina de aeronaves
	public Page<Aeronave> findPage(Integer page, Integer linePerPage, String direction, String orderBy,String id, String marca) {
		PageRequest pageRequest = PageRequest.of(page, linePerPage, Direction.valueOf(direction),orderBy);
		
		if(id.isEmpty() && marca.isEmpty()) {
			return aeronaveRepository.findAll(pageRequest);
		}else {
			return aeronaveRepository.findByIdOrMarcaIgnoreCase(pageRequest, id, marca);
			
		}
		
	}
	
	// Somatorio de aeronaves cadastradas na ultima semana
	public List<AeronaveSomatorioSemanaDTO> countSemana() {
		return aeronaveRepository.somatorioSemana(LocalDateTime.now().minusWeeks(1));
	}

}
