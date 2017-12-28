package com.harium.suneidesis.material;

import com.harium.suneidesis.concept.Concept;

public class Atom extends Concept {

	private int atomicNumber;
	
	private double atomicMass;
	
	public Atom(String name){
		super(name);
	}

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public double getAtomicMass() {
		return atomicMass;
	}

	public void setAtomicMass(double atomicMass) {
		this.atomicMass = atomicMass;
	}
	
}