package br.com.teste.sonda.aeronave.service;

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
}
