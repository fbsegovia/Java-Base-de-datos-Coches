package main;

import java.util.List;
import java.util.Scanner;
import entidades.Coche;
import negocio.GestorCoches;

public class Main {

	static GestorCoches gc = new GestorCoches();
	
	public static void main(String[] args) {
		Main main = new Main();
		int opcion = 0;
		do{
			opcion = main.mostrarMenu(); 
			switch (opcion) {
			case 1://Dar de Alta un Coche
				main.alta();
				break;
			case 2://Dar de Baja un Coche
				main.baja();
				break;
			case 3://Modificar los datos
				main.modificar();
				break;
			case 4://Mostrar datos de un Coche
				main.obtener();
				break;
			case 5:
				main.listar();
				break;
			case 6://Salir Programa
				System.out.println("Fin del programa");
				break;
			default:
				System.out.println("El sistema solo admite valores de 1 a 5");
				System.out.println("Por favor introduzca un valor válido en el sistema");
				break;
			}
		}while(main.continuar(opcion));

	}


	/**
	 * Método que muestra al usuario las opciones diponibles.
	 * @return Devuelve la opcion tomada por el ususario.
	 */
	private int mostrarMenu() {
		System.out.println("Seleccione la opción que desee realizar");
		System.out.println("1. Dar de alta un Coche en la base de datos.");
		System.out.println("2. Dar deun Coche en la base de datos.");
		System.out.println("3. Modificar los datos de un Coche en la base de datos.");
		System.out.println("4. Mostrar los datos de un Coche en concreto.");
		System.out.println("5. Listar los datos de los Coches de la base de datos.");
		System.out.println("6. Salir de la aplicación.");
		Scanner sc = new Scanner(System.in);
		int opcion = sc.nextInt();
		return opcion;
	}
	
	/**
	 * Método que pide datos al usuario y los añade a la tabla Coches.
	 */
	private void alta() {
		Coche coche = new Coche();
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Cual es la Matricula del coche?");
		String matricula = sc.next();
		coche.setMatricula(matricula);
		System.out.println("¿Cual es la Marca del coche?");
		String marca = sc.next();
		coche.setMarca(marca);
		System.out.println("¿Cual es el Modelo del coche?");
		String modelo = sc.next();
		coche.setModelo(modelo);
		System.out.println("¿Cuantos Kilometros tiene el coche?");
		int kilometros = sc.nextInt();
		coche.setKilometros(kilometros);
	
		GestorCoches gestorCoches = new GestorCoches();
		boolean alta = gestorCoches.alta(coche);
		if(alta){
			System.out.println("El coche se ha dado de alta");
		}else{
			System.out.println("El coche no se ha dado de alta");
		}
	}
	
	/**
	 * Método que elimina un coche de la base de datos introduciendo su matricula.
	 */
	private void baja(){
		Scanner sc = new Scanner(System.in);
		GestorCoches gc = new GestorCoches();
		System.out.println("¿Cual es la Matricula del coche?");
		String matricula = sc.next();
		boolean baja = gc.baja(matricula);
		if(baja){
			System.out.println("El coche se ha dado de baja");
		}else{
			System.out.println("El coche NO se ha dado de baja");
		}
	}
	
	/**
	 * Método que modifica los datos de un coche de la base de datos introduciendo su ID.
	 */
	private void modificar(){
		Coche coche = new Coche();
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Cual es el ID del coche que desea modificar?");
		int id = sc.nextInt();
		coche.setId(id);
		System.out.println("¿Cual es la nueva Matricula del coche?");
		String matricula = sc.next();
		coche.setMatricula(matricula);
		System.out.println("¿Cual es la nueva Marca del coche?");
		String marca = sc.next();
		coche.setMarca(marca);
		System.out.println("¿Cual es el nuevo Modelo del coche?");
		String modelo = sc.next();
		coche.setModelo(modelo);
		System.out.println("¿Cuantos nuevos Kilometros ha recorrido el coche?");
		int kilometros = sc.nextInt();
		coche.setKilometros(kilometros);

		GestorCoches gc = new GestorCoches();
		boolean modificar = gc.modificar(coche);
		if(modificar){
			System.out.println("El coche se ha modificado");
		}else{
			System.out.println("El coche NO se ha modificado");
		}
	}
	
	/**
	 * Método que muestra los datos de un coche introduciendo su ID.
	 */
	private void obtener(){
		Scanner sc = new Scanner(System.in);
		GestorCoches gc = new GestorCoches();
		System.out.println("¿Cual es la ID del coche que desea buscar?");
		int id = sc.nextInt();
		Coche coche = gc.obtener(id);
		System.out.println(coche);
	}
	
	/**
	 * Método que lista todos los datos de los coches contenidos en la base de datos.
	 */
	private void listar(){
		GestorCoches gc = new GestorCoches();
		List<Coche> listaCoches = gc.listar();
		System.out.println(listaCoches);
	}
	
	/**
	 * Método que permite salir del bucle del Do...While del menu principal.
	 * @param En función de la opción introducida por el usuario en el menu.
	 * @return Devuelde un valor booleano que permite o niega salir del bucle.
	 */
	private boolean continuar(int opcion) {
		if(opcion==6){
			return false;
		}else{
			return true;
		}
	}
	
}
