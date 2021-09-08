package com.operacion.fuego.services.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.operacion.fuego.entities.Position;
import com.operacion.fuego.entities.Satellites;
import com.operacion.fuego.entities.SpaceShip;
import com.operacion.fuego.entities.Transporter;
import com.operacion.fuego.exceptions.OperacionException;
import com.operacion.fuego.services.MessageServices;
import com.operacion.fuego.services.OperacionServices;
import com.operacion.fuego.services.UbicationServices;



@Service
public class OperacionServicesImpl implements OperacionServices{

	@Autowired
    MessageServices messageService;
	
	@Autowired
    UbicationServices ubicationService;

	@Autowired
    private Environment environment;
	
	@Override
	public SpaceShip getSpacheship(Satellites satellites) throws OperacionException {

		if(satellites.getMessages().size() < 2)
			throw new OperacionException("Cantidad de mensajes inválida");
		
		uploadPositions(satellites);
		
		if( (satellites.getPositions().length < 2) || (satellites.getDistances().length < 2) )
            throw new OperacionException("Número de posición o distancias inválidas");
		
		if(satellites.getPositions().length != satellites.getDistances().length) {
			throw new IllegalArgumentException("El número de posiciones que proporcionó, " + satellites.getDistances().length + ", no coincide con el número de distancias, " + satellites.getDistances().length + ".");
		}
		
		String message = messageService.getMessage(satellites.getMessages());
		
		 double [] points = this.ubicationService.getLocation(satellites.getPositions(), satellites.getDistances());
		 Position position = new Position(points);
		
		
		return new Transporter(position, message);
		
	}
	
	public void uploadPositions(Satellites satellites){

        if(satellites.getPositions()[0] == null) {
            int countSat = Integer.parseInt(environment.getProperty("satellites.numbers"));
            double[][] pointsList = new double[countSat][];
            String[] satellitePos;
            for (int i = 0; i < satellites.getSatellites().size(); i++) {
                satellitePos = environment.getProperty("satellites." + i + ".position").split(",");
               
                pointsList[i] = Arrays.stream(satellitePos)
                        .map(Double::valueOf)
                        .mapToDouble(Double::doubleValue)
                        .toArray();
            }
            satellites.setPositions(pointsList);
        }
    }


}
