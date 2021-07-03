package com.mitocode.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Signos;
import com.mitocode.service.ISignosServices;

@RestController
@RequestMapping("/Signos")
public class SignosController {

	@Autowired
	private ISignosServices service;

	@GetMapping
	public ResponseEntity<List<Signos>> listar() throws Exception {
		List<Signos> lista = service.listar();
		return new ResponseEntity<List<Signos>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Signos> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Signos pac = service.listarPorId(id);
		if (pac.getIdSignos() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Signos>(pac, HttpStatus.OK);
	}

	@GetMapping("/pageable")
	public ResponseEntity<Page<Signos>> listarPageable(Pageable pageable) {
		Page<Signos> signos = service.listarPageable(pageable);
		return new ResponseEntity<Page<Signos>>(signos, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Signos signos) throws Exception {
		Signos sig = service.registrar(signos);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sig.getIdSignos())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Signos> modificar(@RequestBody Signos signos) throws Exception {
		Signos sig = service.modificar(signos);
		return new ResponseEntity<Signos>(sig, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable("id") Integer id) throws Exception {
		Signos sig = service.listarPorId(id);
		if (sig.getIdSignos() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
