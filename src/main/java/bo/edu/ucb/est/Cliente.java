package bo.edu.ucb.est;

//Clase Cliente.
public class Cliente {
	
	//Declaración de los atributos de la clase Cliente.
	private String nombre;
	private String codigoCliente; 
	private int pinCliente;
    
	public Cliente(){
        nombre = null;
        codigoCliente = null;
		pinCliente = 0;
    }

	//Constructor de la clase Cliente.
	public Cliente(String nombre, String codigoCliente, int pinCliente) {
		this.nombre = nombre;
		this.codigoCliente = codigoCliente;
		this.pinCliente = pinCliente;
	}

	//Getters de la clase Cliente.
	public String getNombre() {
		return nombre;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public int getPinCliente() {
		return pinCliente;
	}

	@Override
	public String toString() {
		return getNombre()+";"+getCodigoCliente()+";"+getPinCliente();
	}
	
}