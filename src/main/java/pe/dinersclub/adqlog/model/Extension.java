package pe.dinersclub.adqlog.model;

import java.io.Serializable;

public class Extension implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}