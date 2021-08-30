package br.com.teste.sonda.aeronave.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.repository.AeronaveRepository;

@Service
public class AeronaveService {

	@Autowired
	private AeronaveRepository aeronaveRepository;

	public List<Aeronave> findAll() {
		return aeronaveRepository.findAll();
	}

	public Aeronave find(Long id) {
		return aeronaveRepository.findById(id).get();
	}

	public Aeronave insert(Aeronave aeronave) {
		aeronave.setId(null);
		aeronave.setCreated(LocalDateTime.now());
		return aeronaveRepository.save(aeronave);
	}
}
