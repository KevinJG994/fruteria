package com.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.data.DataConnection;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import com.domain.Producto;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class pCliente extends JFrame {

	private JPanel contenedorCatalogo;
	private JTable table;
	private ObjectContainer db = DataConnection.getInstance();
	List<Producto> p;


	/**
	 * Create the frame.
	 */
	public pCliente() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contenedorCatalogo = new JPanel();
		contenedorCatalogo.setBackground(new Color(153, 204, 204));
		contenedorCatalogo.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contenedorCatalogo);
		
		JScrollPane tablaCatalogo = new JScrollPane();
		
		JButton botonDesconectar = new JButton("Desconectar");
		botonDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Login lo = new Login();
					lo.mostrarPrincipal();
					setVisible(false);
			}
		});
		
		JLabel imagen = new JLabel("");
		imagen.setIcon(new ImageIcon(pCliente.class.getResource("/imagenes/verduras.png")));
		
		JLabel titulo = new JLabel("Catálogo");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_contenedorCatalogo = new GroupLayout(contenedorCatalogo);
		gl_contenedorCatalogo.setHorizontalGroup(
			gl_contenedorCatalogo.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contenedorCatalogo.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contenedorCatalogo.createParallelGroup(Alignment.LEADING)
						.addComponent(tablaCatalogo, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
						.addComponent(titulo))
					.addGap(13)
					.addGroup(gl_contenedorCatalogo.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contenedorCatalogo.createSequentialGroup()
							.addComponent(imagen)
							.addGap(21))
						.addGroup(gl_contenedorCatalogo.createSequentialGroup()
							.addComponent(botonDesconectar)
							.addContainerGap())))
		);
		gl_contenedorCatalogo.setVerticalGroup(
			gl_contenedorCatalogo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contenedorCatalogo.createSequentialGroup()
					.addGroup(gl_contenedorCatalogo.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_contenedorCatalogo.createSequentialGroup()
							.addComponent(imagen)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(botonDesconectar))
						.addGroup(Alignment.LEADING, gl_contenedorCatalogo.createSequentialGroup()
							.addGap(23)
							.addComponent(titulo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tablaCatalogo, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Producto","Origen","Stock"
			}
		));
		tablaCatalogo.setViewportView(table);
		contenedorCatalogo.setLayout(gl_contenedorCatalogo);
		
		init();
	}
	
	void init(){
		p = db.query(new Predicate<Producto>() {
			public boolean match(Producto pro) {
				return true;
			}
		}, new Comparator<Producto>() {
			public int compare(Producto pro1, Producto pro2) {
				return pro1.getProducto().compareTo(pro2.getProducto());
			}
		});
		

		DefaultTableModel modelo = (DefaultTableModel)table.getModel();
		while (modelo.getRowCount() > 0) modelo.removeRow(0);
		int numCols = modelo.getColumnCount();
		for (Producto usr : p) {
			Object [] fila = new Object[numCols]; 
			
			fila[0] = usr.getId();
			fila[1] = usr.getProducto();
			fila[2] = usr.getOrigen();
			fila[3] = usr.getStock();
			
			modelo.addRow(fila);
				
		}
	}
}