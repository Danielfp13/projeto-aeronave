package br.com.teste.sonda.aeronave.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.service.AeronaveService;

@RestController
@RequestMapping("/aeronaves")
public class AeronaveController {

	@Autowired
	private AeronaveService aeronaveService;

	// Retorna todos as aeronaves
	@GetMapping()
	public ResponseEntity<List<Aeronave>> findAll() {
		List<Aeronave> aeronaves = aeronaveService.findAll();
		return ResponseEntity.ok().body(aeronaves);
	}

	// Retorna uma aeronave por id
	@GetMapping("/{id}")
	public ResponseEntity<Aeronave> find(@PathVariable Long id) {
		Aeronave aeronave = aeronaveService.find(id);
		return ResponseEntity.ok().body(aeronave);
	}

	// Adiciona uma nova aeronave
	@PostMapping
	public ResponseEntity<Aeronave> insert(@RequestBody Aeronave aeronave) {
		aeronave = aeronaveService.insert(aeronave);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aeronave.getId())
				.toUri();
		return ResponseEntity.created(uri).body(aeronave);
	}
	
	//Atualiza os dados de uma aeronave 
	@PutMapping("/{id}")
	public ResponseEntity<Void> update (@RequestBody Aeronave aeronave, @PathVariable Long id){
		aeronave = aeronaveService.update(aeronave, id);
		return ResponseEntity.noContent().build();
	}
	

	//Excluir a aeronave
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		aeronaveService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
