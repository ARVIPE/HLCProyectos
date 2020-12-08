package proyecto.model;

public enum ClavesProd {
	ID("id"),
	PRODUCT("producto"),
	PRECIO("precio");

	
	private final String text;
	
	ClavesProd(final String text) {
		this.text = text;
	}
	
	public String toString() {
		return this.text;
	}
	
}

