package com.carledwinti.postrest.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.carledwinti.postrest.model.Curso;

@RestController
public class CursoResource {

	private Map<Integer, Curso> cursos;
	
	public CursoResource() {
		cursos = new HashMap<Integer, Curso>();
		cursos.put(1, new Curso(1, "Curso de SpringBoot Rest", "48hs"));
		cursos.put(2, new Curso(2, "Curso de SpringBoot MVC", "72hs"));
		cursos.put(3, new Curso(3, "Curso de SpringBoot Data", "24hs"));
	}
	
	@RequestMapping(value="/cursos", method=RequestMethod.GET)
	public ResponseEntity<List<Curso>> listar(){
		return new ResponseEntity<List<Curso>>(new ArrayList<Curso>(cursos.values()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/cursos/{id}", method=RequestMethod.GET)
	public ResponseEntity<Curso> buscar(@PathVariable("id") Integer id){
		Curso curso = cursos.get(id);
		
		if(curso == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cursos/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?>deletar(@PathVariable("id")Integer id){
		Curso curso = cursos.remove(id);
		
		if(curso == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Curso>(HttpStatus.NO_CONTENT);
	}
}
