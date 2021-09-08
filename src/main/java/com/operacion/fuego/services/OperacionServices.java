package com.operacion.fuego.services;

import com.operacion.fuego.entities.Satellites;
import com.operacion.fuego.entities.SpaceShip;
import com.operacion.fuego.exceptions.OperacionException;

public interface OperacionServices {
	 public  SpaceShip getSpacheship(Satellites satellites) throws OperacionException;
}
