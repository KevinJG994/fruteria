package com.domain;

import java.util.ArrayList;

public class Catalogo {
	
	private String nombre;
	private ArrayList<Producto> productos;
	
	public Catalogo(String nombre, ArrayList<Producto> productos) {
		super();
		this.nombre = nombre;
		this.productos = productos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Producto> productos) {
		this.productos = productos;
	}
	
	
	

}
