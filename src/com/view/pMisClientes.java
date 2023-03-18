package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.data.DataConnection;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import com.domain.Cliente;
import com.domain.Producto;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;
import java.awt.event.ActionEvent;

public class pMisClientes extends JFrame {

	private JPanel contenedorMisClientes;
	private JTable table;
	private ObjectContainer db = DataConnection.getInstance();
	List<Cliente> cl;


	/**
	 * Create the frame.
	 */
	public pMisClientes() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contenedorMisClientes = new JPanel();
		contenedorMisClientes.setBackground(new Color(153, 204, 204));
		contenedorMisClientes.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedorMisClientes);
		
		JScrollPane tablaClientes = new JScrollPane();
		
		JButton botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pAdmin ad = new pAdmin();
				ad.mostrarpAdmin();
				setVisible(false);
			}
		});
		
		JLabel imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(pMisClientes.class.getResource("/imagenes/dinero.png")));
		
		JLabel titulo = new JLabel("Mis Clientes");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contenedorMisClientes = new GroupLayout(contenedorMisClientes);
		gl_contenedorMisClientes.setHorizontalGroup(
			gl_contenedorMisClientes.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contenedorMisClientes.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contenedorMisClientes.createParallelGroup(Alignment.LEADING)
						.addComponent(tablaClientes, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
						.addComponent(titulo))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addGroup(gl_contenedorMisClientes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contenedorMisClientes.createSequentialGroup()
							.addComponent(imagen)
							.addGap(24))
						.addGroup(gl_contenedorMisClientes.createSequentialGroup()
							.addComponent(botonVolver)
							.addContainerGap())))
		);
		gl_contenedorMisClientes.setVerticalGroup(
			gl_contenedorMisClientes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contenedorMisClientes.createSequentialGroup()
					.addGroup(gl_contenedorMisClientes.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_contenedorMisClientes.createSequentialGroup()
							.addContainerGap()
							.addComponent(imagen)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(botonVolver))
						.addGroup(Alignment.LEADING, gl_contenedorMisClientes.createSequentialGroup()
							.addGap(23)
							.addComponent(titulo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tablaClientes, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DNI", "Nombre","Apellido","Teléfono"
			}
		));
		tablaClientes.setViewportView(table);
		contenedorMisClientes.setLayout(gl_contenedorMisClientes);
		
		init();
	}
	
	void init(){
		cl = db.query(new Predicate<Cliente>() {
			public boolean match(Cliente c) {
				return true;
			}
		}, new Comparator<Cliente>() {
			public int compare(Cliente c1, Cliente c2) {
				return c1.getNombre().compareTo(c2.getNombre());
			}
		});
		

		DefaultTableModel modelo = (DefaultTableModel)table.getModel();
		while (modelo.getRowCount() > 0) modelo.removeRow(0);
		int numCols = modelo.getColumnCount();
		for (Cliente usr : cl) {
			Object [] fila = new Object[numCols]; 
			
			fila[0] = usr.getDni();
			fila[1] = usr.getNombre();
			fila[2] = usr.getApellido();
			fila[3] = usr.getTelefono();
			
			modelo.addRow(fila);
				
		}
	}
}