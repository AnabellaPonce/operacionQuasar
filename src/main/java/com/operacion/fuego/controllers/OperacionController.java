package com.operacion.fuego.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.operacion.fuego.entities.Satellites;
import com.operacion.fuego.exceptions.OperacionException;
import com.operacion.fuego.services.OperacionServices;


public class OperacionController {
	private OperacionServices operacionService;
	
	public OperacionController(OperacionServices operacionService) {
		this.operacionService = operacionService;
// TODO Auto-generated constructor stub
}
	
	/**
	* Servicio que registra un satelite y la informacion enviada por el mismo.
	*
	* @param Satellites Informacion de los satelites
	* @return La posicion y el mensaje de la nave que emitio el mensaje, o
	* mensaje de error.
	*/
		
	@RequestMapping(path = "${topsecret.context.path}")
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<?> topSecret(RequestEntity<Satellites> requestEntity){
		
		 try {
			 Satellites satellites = (Satellites) requestEntity.getBody();
			 
	         return ResponseEntity.status(HttpStatus.OK).body(this.operacionService.getSpacheship(satellites));
	     }catch (OperacionException e){
	         throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(), e);
	     }
		
	}
}
