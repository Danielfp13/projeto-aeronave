package br.com.teste.sonda.aeronave.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.teste.sonda.aeronave.dto.AeronaveDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioDecadaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioMarcaDTO;
import br.com.teste.sonda.aeronave.dto.AeronaveSomatorioSemanaDTO;
import br.com.teste.sonda.aeronave.entity.Aeronave;
import br.com.teste.sonda.aeronave.service.AeronaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api ( tags  =  " Aeronave Endpoint " )
@RestController
@RequestMapping("/aeronaves")
@CrossOrigin("http://localhost:4200")
public class AeronaveController {

	@Autowired
	private AeronaveService aeronaveService;

	// Retorna todos as aeronaves
	@ApiOperation ( value  =  "Listar todas aeronaves " )
	@GetMapping()
	public ResponseEntity<List<Aeronave>> findAll() {
		List<Aeronave> aeronaves = aeronaveService.findAll();
		return ResponseEntity.ok().body(aeronaves);
	}

	// Retorna uma aeronave por id
	@ApiOperation ( value  =  "Buscar Aeronave por id " )
	@GetMapping("/{id}")
	public ResponseEntity<Aeronave> find(@PathVariable Long id) {
		Aeronave aeronave = aeronaveService.find(id);
		return ResponseEntity.ok().body(aeronave);
	}

	// Adiciona uma nova aeronave
	@ApiOperation ( value  =  "Cadastrar aeronave" )
	@PostMapping
	public ResponseEntity<Aeronave> insert(@Valid @RequestBody AeronaveDTO aeronaveDTO) {
		Aeronave aeronave = aeronaveService.insert(aeronaveDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aeronave.getId())
				.toUri();
		return ResponseEntity.created(uri).body(aeronave);
	}

	// Atualiza os dados de uma aeronave
	@ApiOperation ( value  =  "Editar aeronave" )
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody AeronaveDTO aeronaveDTO, @PathVariable Long id) {
		aeronaveService.update(aeronaveDTO, id);
		return ResponseEntity.noContent().build();
	}

	// Excluir a aeronave
	@ApiOperation ( value  =  "Excluir aeronave" )
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		aeronaveService.delete(id);
		return ResponseEntity.noContent().build();
	}

	// Somatório de aeronaves por décadas
	@ApiOperation ( value  =  "Somatório de aeronaves por década " )
	@GetMapping("/decadas")
	public ResponseEntity<List<AeronaveSomatorioDecadaDTO>> countAeronave() {
		List<AeronaveSomatorioDecadaDTO> aeronavesDTO = aeronaveService.countAeronave();
		return ResponseEntity.ok().body(aeronavesDTO);
	}

	// Somatório de aeronaves por marca
	@ApiOperation ( value  =  "Somatório de aeronave por marca " )
	@GetMapping("/marcas")
	public ResponseEntity<List<AeronaveSomatorioMarcaDTO>> countMarca() {
		List<AeronaveSomatorioMarcaDTO> aeronavesDTO = aeronaveService.countMarca();
		return ResponseEntity.ok().body(aeronavesDTO);
	}
	
	//Retorna pagina de aeronaves
	@ApiOperation ( value  =  "Busca páginada de aeronaves com ou sem parâmetros" )
	@GetMapping("/find")
	public ResponseEntity<Page<Aeronave>> finddPage(
			@RequestParam(value = "marca", defaultValue = "") String marca,
			@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "8") Integer linePerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "ordeBy", defaultValue = "nome") String orderBy
			){
		Page<Aeronave> aeronaves = aeronaveService.findPage(page, linePerPage, direction, orderBy, id, marca);
		return ResponseEntity.ok().body(aeronaves);
	}
	
	//Retorna somatorio da semana
	@ApiOperation ( value  =  "Somatório de aeronave na ultima semana" )
	@GetMapping("/semanas")
	public ResponseEntity<List<AeronaveSomatorioSemanaDTO>> countSemana(){
		List<AeronaveSomatorioSemanaDTO> aeronavesDTO = aeronaveService.countSemana();
		return ResponseEntity.ok().body(aeronavesDTO);
	}
}
