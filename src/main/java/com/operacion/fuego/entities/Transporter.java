package com.operacion.fuego.entities;



public class Transporter extends SpaceShip{
	
	private String message;

	public Transporter(Position position, String message) {
		this.setPosition(position);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
