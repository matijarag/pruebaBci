package es.springboot.bci.model;

public class ErrorMsg {
	
	private String mensaje;

	public ErrorMsg() {
		
	}

	public ErrorMsg(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	@Override
	public String toString() {
		return "ErrorMsg [mensaje=" + mensaje + "]";
	}
	

}
