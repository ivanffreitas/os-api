package com.ivanilson.os.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.ivanilson.os.domain.Tecnico;
import com.ivanilson.os.dtos.TecnicoDTO;
import com.ivanilson.os.services.TecnicoService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/tecnicos") // localhost:8080/tecnicos/
public class TecnicoResource {

	@Autowired
	private TecnicoService service;

	// recebe os dados de um tecnico vindo da class TecnicoService para ser exibida
	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		TecnicoDTO objDTO = new TecnicoDTO(service.findById(id));
		return ResponseEntity.ok().body(objDTO);
	}

	// recebe os dados de tecnico vindo da class TecnicoService para ser exibida
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {

		// List<Tecnico> list = service.findAll();
		// List<TecnicoDTO> listDTO = new ArrayList<>();
		// for(Tecnico obj : list) {
		// listDTO.add(new TecnicoDTO(obj));
		// }
		// essa linha substitui o for acima
		// list.forEach(obj -> listDTO.add(new TecnicoDTO(obj)));

		// essa linha pode ser substituida pelo codigo comentado acima
		List<TecnicoDTO> listDTO = service.findAll().stream().map(obj -> new TecnicoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}
	
	
	//PASSAR DADOS PARA O TecnicoService INSERIR NA BASE DE DADOS
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	// ATUALIZAR DADOS DO TECNICO NO BRANCO DE DADOS
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoDTO objDTO){
		TecnicoDTO newObj = new TecnicoDTO(service.update(id, objDTO));
		return ResponseEntity.ok().body(newObj);
	}
	
	//DELETAR UM TECNICO DO BANCO
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}








