package bo.edu.ucb.est;

//Librerías implementadas.
import javax.swing.JOptionPane;

//Clase Cuenta.
public class Cuenta {

	//Declaración de los atributos de la clase Cuenta.
	private String codCliente;
	private int numeroDeCuenta; 
	private String moneda;
	private String tipo;
    private int saldo;
    
    //Constructor de la clase Cuenta.
    public Cuenta(String codCliente, int numeroDeCuenta, String moneda, String tipo, int saldo) {
		this.codCliente = codCliente;
		this.numeroDeCuenta = numeroDeCuenta;
		this.moneda = moneda;
		this.tipo = tipo;
		this.saldo = saldo;
	}

    //Getters de la clase Cuenta.
	public String getCodCliente() {
		return codCliente;
	}
	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}
	public String getMoneda() {
		return moneda;
	}
	public String getTipo() {
		return tipo;
	}
	public int getSaldo() {
		return saldo;
	}

	//Función datos, sirve para mostrar los datos de un objeto de la clase.
	public String datos(int numero) {
		return "Cuenta "+numero+": "+getNumeroDeCuenta()+"\nMoneda: "+getMoneda()+"\nTipo: "+getTipo()+"\nSaldo: "+getSaldo();
	}
	
	//Función retirar, sirve para realizar el retiro de un monto de la cuenta de un cliente
	//tomando en cuenta diferentes especificaciones.
	public boolean retirar (int monto) {
		boolean exito = false;
		if (monto <= getSaldo()) {
			if (monto != 0) {
				if (monto > 0) {
					this.saldo = getSaldo() - monto;
					exito = true;
					JOptionPane.showMessageDialog(null, "El monto ha sido retirado de la cuenta correctamente.");
				}
				else {
					JOptionPane.showMessageDialog(null, "El monto ingresado es negativo.", "Error", 2);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "El monto ingresado es 0.", "Error", 2);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "El monto ingresado es mayor al saldo actual de la cuenta.", "Error", 2);
		}	
		return exito;
	}
	
	//Función depositar, sirve para realizar el deposito de un monto a la cuenta de un cliente
	//tomando en cuenta algunas especificaciones.
	public boolean depositar (int monto) {
		boolean exito = false;
		if (monto > 0) {
			this.saldo = getSaldo() + monto;
			exito = true;
			JOptionPane.showMessageDialog(null, "El monto ha sido depositado a la cuenta correctamente.");
		}
		else {
			JOptionPane.showMessageDialog(null, "El monto ingresado no es válido.", "Error", 2);
		}
		return exito;
	}
}

