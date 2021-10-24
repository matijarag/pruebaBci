package es.springboot.bci.model;

import java.util.List;

public class UsuarioJson extends Usuario{

	private List<Phones> phones;

	
	
	public UsuarioJson() {
	}



	public UsuarioJson(List<Phones> phones) {
		this.phones = phones;
	}



	public List<Phones> getPhones() {
		return phones;
	}



	public void setPhones(List<Phones> phones) {
		this.phones = phones;
	}



	@Override
	public String toString() {
		return "UsuarioJson [phones=" + phones + "]";
	}
	
	
}
