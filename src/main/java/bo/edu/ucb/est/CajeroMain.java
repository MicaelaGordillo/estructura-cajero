package bo.edu.ucb.est;

//Librerías implementadas.
import java.util.ArrayList;
import javax.swing.*;

//Clase CajeroMain.
public class CajeroMain {
	
	//Declaracion de variables estáticas.
	static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	static ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
	static int oportunidad = 0;
	static String codigo = null;
	
	//Funcion principal main.
	public static void main(String[] args) {
		cuentasIniciales();
		existencia();
		for(;true;){
			String[] menus={
					"Consultar saldo",
					"Retirar dinero",
					"Depositar dinero",
					"Salir de mi cuenta",
					"Salir"};
			String opcion=(String) JOptionPane.showInputDialog(null,"Bienvenid@!!!\nSeleccione una opción:","Menú de opciones",JOptionPane.DEFAULT_OPTION,null,menus,menus[0]);
		    switch (opcion) {
		    	case "Consultar saldo":{
		    		JTextArea jt = new JTextArea();
		    		jt.setText(mostrarDatos(mostrarCuentas("Consultar saldo", "la consulta")));
		    		JOptionPane.showMessageDialog(null,jt);
		    		break;
		    	}
		    	case "Retirar dinero": retiro(); break;
		    	case "Depositar dinero": deposito(); break;
		    	case "Salir de mi cuenta": existencia(); break;
		    	case "Salir": System.exit(0);; break;} 
		}
	}
	
	//Función cuentasIniciales, ingresa los diferentes datos a los ArrayList declarados previamente.
	public static void cuentasIniciales() {
		Cliente cliente1 = new Cliente("Juan Perez", "jperez", 3333);
		Cliente cliente2 = new Cliente("Maria Gomez", "mgomez", 4444);
		Cliente cliente3 = new Cliente("Jaime Lopez", "jlopez", 5555);
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		Cuenta cli1cnta1  = new Cuenta("jperez", 111122, "Bolivianos", "Caja de Ahorros", 1200);
		Cuenta cli1cnta2  = new Cuenta("jperez", 112211, "USD", "Cuenta Corriente", 100);
		Cuenta cli2cnta1  = new Cuenta("mgomez", 221122, "Bolivianos", "Caja de Ahorros", 0);
		Cuenta cli3cnta1  = new Cuenta("jlopez", 331122, "Bolivianos", "Caja de Ahorros", 100);
		Cuenta cli3cnta2  = new Cuenta("jlopez", 332211, "USD", "Cuenta Corriente", 1000);
		Cuenta cli3cnta3  = new Cuenta("jlopez", 332233, "Bolivianos", "Caja de Ahorros", 100000);
		cuentas.add(cli1cnta1);
		cuentas.add(cli1cnta2);
		cuentas.add(cli2cnta1);
		cuentas.add(cli3cnta1);
		cuentas.add(cli3cnta2);
		cuentas.add(cli3cnta3);
	}
	
	//Función existencia, sirve para comprobar que los datos: código del cliente y pin del cliente
	//existan y sean correctos.
	public static void existencia() {
		boolean flag = false;
		int oportunidad = 0;
		while (flag==false && oportunidad<3) {
			String codCliente = JOptionPane.showInputDialog(null, "Ingrese su código de cliente:", "Ingreso", 3);
			for (Cliente c: clientes) {
				if (c.getCodigoCliente().equalsIgnoreCase(codCliente)) {
					try{
						int pinCliente = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su pin de seguridad:", "Ingreso", 3));
						if (c.getPinCliente() == pinCliente) {
							flag = true;
							codigo = codCliente;
						}
			    	}catch(NumberFormatException ex){
			    		JOptionPane.showMessageDialog(null, "El pin ingresado no es válido.", "Error", 2);
			    	}
				}
			}
			if (flag == false) {
				JOptionPane.showMessageDialog(null, "Lo sentimos, uno de los datos ingresados es incorrecto. Vuelva a intentarlo.", "Error", 2);
				oportunidad++;
			}
		}
		if (oportunidad == 3) {
			JOptionPane.showMessageDialog(null, "Excedió en número de oportunidades. Hasta luego.", "Error", 2);
			System.exit(0);
		}
	}
	
	//Función mostrarCuentas, sirve para mostrar en una lista el número de cuentas que el cliente posea.
	public static int mostrarCuentas(String titulo, String dato) {
		String cad = "Tus cuentas:";
		int numCuenta = 1, numero = 0;
		for (Cuenta c: cuentas) {
			if (c.getCodCliente().equalsIgnoreCase(codigo)) {
				cad += "\n- Cuenta "+ numCuenta+": "+c.getNumeroDeCuenta();
				numCuenta++;
			}
		}
		cad += "\nIngrese el número de la cuenta a realizar "+dato+":";
		try {
			int num = Integer.parseInt(JOptionPane.showInputDialog(null, cad, titulo, 3));
			numero = num;
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(null, "El dato ingresado no corresponde a ninguna cuenta.", "Error", 2);
		}
		return numero;
	}
	
	//Función mostrarDatos, sirve para mostrar los datos de la cuenta que el cliente determino anteriormente.
	public static String mostrarDatos(int num) {
		int dato = 1;
		String consulta = null;
		boolean existe = true;
		for (Cuenta c: cuentas) {
			if (c.getCodCliente().equalsIgnoreCase(codigo)) {
				if (dato == num) {
					consulta = c.datos(num);
					existe = false;
					break;
				}
				dato++;
			}
		}
		if (existe) {
			consulta = "El número de cuenta ingresado no existe.";
		}
		return consulta;
	}
	
	//Función retiro, sirve para realizar un retiro en la cuenta que el cliente determino anteriormente.
	public static void retiro() {
		int num = mostrarCuentas("Retirar dinero", "el retiro");
		int dato = 1;
		boolean existe = true;
		for (Cuenta c: cuentas) {
			if (c.getCodCliente().equalsIgnoreCase(codigo)) {
				if (dato == num) {
					if (c.getSaldo() != 0) {
						try {
							String cad = "Cuenta "+num+": "+c.getNumeroDeCuenta()+
									"\nSaldo actual: "+c.getSaldo()+
									"\nIngrese el monto que desea retirar de la cuenta en "+c.getMoneda()+":";
							int monto = Integer.parseInt(JOptionPane.showInputDialog(null, cad, "Retiro", 3));
							while (c.retirar(monto) == false) {
								monto = Integer.parseInt(JOptionPane.showInputDialog(null, cad, "Retiro", 3));
							}
							existe = false;
							break;
						}catch(NumberFormatException ex){
							JOptionPane.showMessageDialog(null, "El monto ingresado no es válido. Por favor solo ingresar valores enteros.", "Error", 2);
						}
						existe = false;
					}
					else {
						JOptionPane.showMessageDialog(null,"No se puede realizar un retiro de esta cuenta debido a que su saldo actual es de 0 "+c.getMoneda()+".");
						existe = false;
					}
				}
				dato++;
			}
		}
		if (existe) {
			JOptionPane.showMessageDialog(null,"El número de cuenta ingresado no existe.");
		}
	}
	
	//Función deposito, sirve para realizar un deposito en la cuenta que el cliente determino anteriormente.
	public static void deposito() {
		int num = mostrarCuentas("Depositar dinero", "el deposito");
		int dato = 1;
		boolean existe = true;
		for (Cuenta c: cuentas) {
			if (c.getCodCliente().equalsIgnoreCase(codigo)) {
				if (dato == num) {
					try {
						String cad = "Cuenta "+num+": "+c.getNumeroDeCuenta()+
								"\nSaldo actual: "+c.getSaldo()+
								"\nIngrese el monto que desea depositar a la cuenta en "+c.getMoneda()+":";
						int monto = Integer.parseInt(JOptionPane.showInputDialog(null, cad, "Deposito", 3));
						while (c.depositar(monto) == false) {
							monto = Integer.parseInt(JOptionPane.showInputDialog(null, cad, "Deposito", 3));
						}
						existe = false;
						break;
					}catch(NumberFormatException ex){
						JOptionPane.showMessageDialog(null, "El monto ingresado no es válido. Por favor solo ingresar valores enteros.", "Error", 2);
					}
					existe = false;
				}
				dato++;
			}
		}
		if (existe) {
			JOptionPane.showMessageDialog(null,"El número de cuenta ingresado no existe.");
		}
	}
}


