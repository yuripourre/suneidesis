package com.medievalperson.beign.taxonomy;

import com.medievalperson.Thing;

public class TaxonomyClass extends Thing{

	private Phylum phylum;
	
	private TaxonomyClass superClass;
	
	public TaxonomyClass(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public TaxonomyClass getSuperClass() {
		return superClass;
	}

	public void setSuperClass(TaxonomyClass superClass) {
		this.superClass = superClass;
	}

	public Phylum getPhylum() {
		return phylum;
	}

	public void setPhylum(Phylum phylum) {
		this.phylum = phylum;
	}
		
}