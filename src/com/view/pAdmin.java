package com.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class pAdmin extends JFrame {

	private JPanel panelAdmin;

	/**
	 * Create the frame.
	 */
	public pAdmin() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelAdmin = new JPanel();
		panelAdmin.setBackground(new Color(153, 204, 204));
		panelAdmin.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelAdmin);
		
		JLabel tituloAdmin = new JLabel("Administrador");
		tituloAdmin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton botonDesconectar = new JButton("Desconectar");
		botonDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lo = new Login();
				lo.mostrarPrincipal();
				setVisible(false);
			}
		});
		
		
		JLabel tituloClientes = new JLabel("Mis Clientes");
		tituloClientes.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel tituloProductos = new JLabel("Productos");
		tituloProductos.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel imagenCliente = new JLabel("");
		imagenCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pMisClientes mc = new pMisClientes();
				mc.setVisible(true);
				setVisible(false);
			}
		});
		imagenCliente.setIcon(new ImageIcon(pAdmin.class.getResource("/imagenes/cliente.png")));
		
		JLabel imagenProductos = new JLabel("");
		imagenProductos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pCliente u = new pCliente();
				u.setVisible(true);
				setVisible(false);
			}
		});
		imagenProductos.setIcon(new ImageIcon(pAdmin.class.getResource("/imagenes/frutas-y-vegetales.png")));
		GroupLayout gl_panelAdmin = new GroupLayout(panelAdmin);
		gl_panelAdmin.setHorizontalGroup(
			gl_panelAdmin.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAdmin.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addComponent(botonDesconectar)
					.addGap(10))
				.addGroup(Alignment.LEADING, gl_panelAdmin.createSequentialGroup()
					.addGap(65)
					.addGroup(gl_panelAdmin.createParallelGroup(Alignment.LEADING)
						.addComponent(tituloClientes)
						.addComponent(imagenCliente, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
					.addGroup(gl_panelAdmin.createParallelGroup(Alignment.TRAILING)
						.addComponent(tituloProductos)
						.addComponent(imagenProductos))
					.addGap(74))
				.addGroup(Alignment.LEADING, gl_panelAdmin.createSequentialGroup()
					.addGap(154)
					.addComponent(tituloAdmin)
					.addContainerGap(154, Short.MAX_VALUE))
		);
		gl_panelAdmin.setVerticalGroup(
			gl_panelAdmin.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAdmin.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAdmin.createParallelGroup(Alignment.TRAILING)
						.addComponent(imagenCliente, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelAdmin.createSequentialGroup()
							.addComponent(tituloAdmin)
							.addGap(38)
							.addComponent(imagenProductos)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelAdmin.createParallelGroup(Alignment.BASELINE)
						.addComponent(tituloClientes)
						.addComponent(tituloProductos))
					.addGap(66)
					.addComponent(botonDesconectar)
					.addGap(9))
		);
		panelAdmin.setLayout(gl_panelAdmin);
	}
	public void mostrarpAdmin() {
		setContentPane(panelAdmin);
		setVisible(true);
	}
}
