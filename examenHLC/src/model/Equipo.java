package model;

public class Equipo {

	protected int id;
	protected String nombre;
	protected int puntos;
	protected int v;
	protected int e;
	protected int d;
	protected int gf;
	protected int gc;
		
	public Equipo(int id, String nombre, int puntos, int v, int e, int d, int gf, int gc) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.v = v;
		this.e = e;
		this.d = d;
		this.gf = gf;
		this.gc = gc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getV() {
		return v;
	}

	public void setV(int v) {
		this.v = v;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getGf() {
		return gf;
	}

	public void setGf(int gf) {
		this.gf = gf;
	}

	public int getGc() {
		return gc;
	}

	public void setGc(int gc) {
		this.gc = gc;
	}
	
	
	
}
