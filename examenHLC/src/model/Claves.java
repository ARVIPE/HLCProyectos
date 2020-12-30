package model;

public enum Claves {
	ID("id"),
	NOMBRE("nombre"),
	PUNTOS("puntos"),
	V("v"),
	E("e"),
	D("d"),
	GF("gf"),
	GC("gc"),
	PASS("pass"),
        E1("e1"),
        E2("e2"),
        G1("g1"),
        G2("g2");
	
	private final String text;
	
	Claves(final String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
	
}