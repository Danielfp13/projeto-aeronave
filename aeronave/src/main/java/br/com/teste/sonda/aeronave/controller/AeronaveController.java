package br.com.teste.sonda.aeronave.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.teste.sonda.aeronave.dto.AeronaveDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioDecadaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioMarcaDTO;
import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.service.AeronaveService;

@RestController
@RequestMapping("/aeronaves")
@CrossOrigin("http://localhost:4200")
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
	public ResponseEntity<Aeronave> insert(@Valid @RequestBody AeronaveDTO aeronaveDTO) {
		Aeronave aeronave = aeronaveService.insert(aeronaveDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aeronave.getId())
				.toUri();
		return ResponseEntity.created(uri).body(aeronave);
	}

	// Atualiza os dados de uma aeronave
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody AeronaveDTO aeronaveDTO, @PathVariable Long id) {
		aeronaveService.update(aeronaveDTO, id);
		return ResponseEntity.noContent().build();
	}

	// Excluir a aeronave
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		aeronaveService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Somatório de aeronaves por décadas
	@GetMapping("/decadas")
	public ResponseEntity<List<AeronaveSomatorioDecadaDTO>> countAeronave() {
		List<AeronaveSomatorioDecadaDTO> aeronavesDTO = aeronaveService.countAeronave();
		return ResponseEntity.ok().body(aeronavesDTO);
	}

	// Somatório de aeronaves por marca
	@GetMapping("/marcas")
	public ResponseEntity<List<AeronaveSomatorioMarcaDTO>> countMarca() {
		List<AeronaveSomatorioMarcaDTO> aeronavesDTO = aeronaveService.countMarca();
		return ResponseEntity.ok().body(aeronavesDTO);
	}
}
