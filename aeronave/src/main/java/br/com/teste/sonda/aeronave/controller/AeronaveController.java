package br.com.teste.sonda.aeronave.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.service.AeronaveService;

@RestController
@RequestMapping("/aeronaves")
public class AeronaveController {
	
	@Autowired
	private AeronaveService aeronaveService;
	
	//Retorna todos as aeronaves 
	@GetMapping()
	public ResponseEntity<List<Aeronave>> findAll(){
		List<Aeronave> aeronaves = aeronaveService.findAll();
		return ResponseEntity.ok().body(aeronaves);
	}
	
	
}
