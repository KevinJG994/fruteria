package com.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.data.DataConnection;
import com.db4o.ObjectContainer;
import com.db4o.query.Predicate;
import com.domain.Persona;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel panelPrincipal;
	private ObjectContainer db = DataConnection.getInstance();
	private JPasswordField txtPass;
	private JTextField txtNom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("panelPrincipal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(153, 204, 204));
		panelPrincipal.setForeground(new Color(0, 0, 0));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		
		JButton botonLogin = new JButton("Login");
		botonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		
		JLabel tituloUser = new JLabel("Usuario:");
		tituloUser.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel tituloContasenia = new JLabel("Contraseña:");
		tituloContasenia.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtPass = new JPasswordField();
		
		txtNom = new JTextField();
		txtNom.setColumns(10);
		
		JLabel imagen = new JLabel("");
		imagen.setHorizontalAlignment(SwingConstants.CENTER);
		imagen.setIcon(new ImageIcon(Login.class.getResource("/imagenes/vegetal.png")));
		
		JLabel titulo = new JLabel("FRUTERIA: EL HUERTO");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panelPrincipal = new GroupLayout(panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelPrincipal.createSequentialGroup()
							.addGap(181)
							.addComponent(botonLogin))
						.addGroup(gl_panelPrincipal.createSequentialGroup()
							.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelPrincipal.createSequentialGroup()
									.addGap(9)
									.addComponent(tituloContasenia))
								.addGroup(gl_panelPrincipal.createSequentialGroup()
									.addContainerGap()
									.addComponent(tituloUser)))
							.addGap(18)
							.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPass)
								.addComponent(txtNom, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))))
					.addContainerGap(85, Short.MAX_VALUE))
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addGap(176)
					.addComponent(imagen, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(177, Short.MAX_VALUE))
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addGap(143)
					.addComponent(titulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(144))
		);
		gl_panelPrincipal.setVerticalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addComponent(titulo, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addComponent(imagen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.BASELINE)
						.addComponent(tituloUser)
						.addComponent(txtNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tituloContasenia))
					.addGap(27)
					.addComponent(botonLogin)
					.addContainerGap())
		);
		panelPrincipal.setLayout(gl_panelPrincipal);
	}
	
	void login() {
		List<Persona> personas = db.query(new Predicate<Persona>(){
			public boolean match (Persona o) {
				return o.getNombre().equals(txtNom.getText()) && o.getPassword().equals(new String(txtPass.getPassword()));
			}
		});
		
		if(personas.size()>0) {
			if(personas.get(0).isAdmin()) {
			 //mostrar la pantalla de admin
				pAdmin a = new pAdmin();
				a.setVisible(true);
				setVisible(false);
			}else {
				//mostrar la pantalla de clientes
				pCliente u = new pCliente();
				u.setVisible(true);
				setVisible(false);
			}
		}else {
			JOptionPane.showMessageDialog(panelPrincipal, "La contraseña o el usuario son incorrectos.");
		}
	}
	
	public void mostrarPrincipal() {
		setContentPane(panelPrincipal);
		setVisible(true);
	}
}
