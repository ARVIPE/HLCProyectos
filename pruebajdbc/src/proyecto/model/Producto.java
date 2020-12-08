package proyecto.model;

public class Producto {

	
	protected int id;
	protected String producto;
	protected double precio;
	
	public Producto(int id, String producto, double precio) {
		super();
		this.id = id;
		this.producto = producto;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Producto [precio=" + precio + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
