package com.domain;

public class Producto {

	private String id;
	private String producto;
	private String origen;
	private int stock;
	
	public Producto() {
		
	}
	
	public Producto(String id, String producto, String origen, int stock) {
		super();
		this.id = id;
		this.producto = producto;
		this.origen = origen;
		this.stock = stock;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
}
