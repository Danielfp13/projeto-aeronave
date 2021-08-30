package br.com.teste.sonda.aeronave.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioDecadaDTO;
import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.repository.AeronaveRepository;

@Service
public class AeronaveService {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	//Buscar todas aeronaves
	public List<Aeronave> findAll() {
		return aeronaveRepository.findAll();
	}

	//Buscar aeronave por id
	public Aeronave find(Long id) {
		return aeronaveRepository.findById(id).get();
	}

	//inserir aeronave
	public Aeronave insert(Aeronave aeronave) {
		aeronave.setId(null);
		aeronave.setCreated(LocalDateTime.now());
		return aeronaveRepository.save(aeronave);
	}
	
	//Alterar aeronave
	public Aeronave update(Aeronave aeronave, Long id) {
		Aeronave newAeronave = find(id);
		aeronave.setCreated(newAeronave.getCreated());
		BeanUtils.copyProperties(aeronave, newAeronave);
		newAeronave.setId(id);
		newAeronave.setUpdated(LocalDateTime.now());
		return aeronaveRepository.save(newAeronave);
	}
	
	//Excluir aeronve
	public void delete(Long id) {
		find(id);
		aeronaveRepository.deleteById(id);
	}
	
	//Somatório de aeronaves por década
	public List<AeronaveSomatorioDecadaDTO> countAeronave() {
		return aeronaveRepository.somatorioAeronave();
	}
	
}
