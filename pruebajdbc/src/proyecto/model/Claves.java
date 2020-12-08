package proyecto.model;

public enum Claves {
	ID("id"),
	USERNAME("usuario"),
	CONTRASENA("contrasena"),
	EMAIL("email"),
	ADMIN("admin");
	
	private final String text;
	
	Claves(final String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
	
}

