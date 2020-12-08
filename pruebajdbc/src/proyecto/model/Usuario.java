package proyecto.model;



public class Usuario {
	protected int id;
	protected String usuario;
	protected String email;
	protected String contrasena;
	protected Boolean admin;
		
	public Usuario(int id, String usuario, String email, String contrasena, Boolean admin) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.email = email;
		this.admin = admin;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
	
	
	
}
