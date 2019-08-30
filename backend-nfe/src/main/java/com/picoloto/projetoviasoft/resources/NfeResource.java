package com.picoloto.projetoviasoft.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.picoloto.projetoviasoft.domain.Nfe;
import com.picoloto.projetoviasoft.dto.NfeDTO;
import com.picoloto.projetoviasoft.services.NfeService;

@RestController
@RequestMapping(value = "/nfe")
public class NfeResource {
	@Autowired
	private NfeService service;
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Nfe> find(@PathVariable Integer id) {
		Nfe obj = service.find(id);
		return ResponseEntity.ok(obj);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody NfeDTO objDTO) {
		Nfe obj = service.fromDTO(objDTO);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody NfeDTO objDTO, @PathVariable Integer id) {
		Nfe obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<NfeDTO>> findAll() {
		List<Nfe> list = service.findAll();
		List<NfeDTO> listDTO = list.stream().map(obj -> new NfeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(listDTO);
	}

}
