package com.humanbooster.garage;

import java.util.LinkedList;
import java.util.List;

public class Garage {
	
	private long nbPlaces;
	
	private List<Vehicule> vehicules;
	
	public Garage(long nbPlaces) {
		super();
		this.nbPlaces = nbPlaces;
	}
	
	private void checkListInstatiation() {
		if(this.vehicules == null) {
			this.vehicules = new LinkedList<>();
		}
	}
	
	public void enterVehicule(Vehicule vehicule) throws NoMoreRoomException {
		this.checkListInstatiation();
		
		if(hasRoom()) {
			this.vehicules.add(vehicule);
		} else {
			throw new NoMoreRoomException();
		}
	}
	
	public void vehiculeLeaves(Vehicule vehicule) {
		this.checkListInstatiation();
		
		this.vehicules.remove(vehicule);
	}
	
	public Boolean hasRoom() {
		return nbPlaces > vehicules.size();
	}

	public List<Vehicule> getVehicules() {
		return vehicules;
	}

	public long getNbPlaces() {
		return nbPlaces;
	}

	public long getNbPlacesLeft() {
		return nbPlaces - vehicules.size();
	}

	public void setNbPlaces(long nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
	
	

}
