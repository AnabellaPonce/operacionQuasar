package com.operacion.fuego.entities;

import java.util.List;

public class Satellite extends SpaceShip{
	 private String name;
	 private double distance;
	 private List<String> message;
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public List<String> getMessage() {
		return message;
	}
	public void setMessage(List<String> message) {
		this.message = message;
	}
	 
	 

}
