package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import control.ControladorLogin;
import control.ControladorUsuario;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import model.Genero;
import java.awt.Color;

public class TelaPerfil extends JDialog {
	private JFrame parent;

	private final JPanel contentPanel = new JPanel();
	private JTextField tFId;
	private JTextField tFNome;
	private JTextField tFIdade;
	private JTextField tFOcupacao;
	private JTextField tFCep;

	/**
	 * Create the dialog.
	 */
	public TelaPerfil(JFrame parent) {
		this.parent = parent;
		initialize();
	}
	
	private void initialize() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		
		setResizable(false);
		setTitle("Ver perfil: " + ControladorLogin.getInstance().getUsuarioLogado().getNome());
		setBounds(100, 100, 265, 280);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tFId = new JTextField();
		tFId.setEditable(false);
		tFId.setText(String.valueOf(ControladorLogin.getInstance().getUsuarioLogado().getId()));
		tFId.setColumns(15);
		
		tFNome = new JTextField();
		tFNome.setEditable(false);
		tFNome.setText(ControladorLogin.getInstance().getUsuarioLogado().getNome());
		tFNome.setColumns(20);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tFIdade = new JTextField();
		tFIdade.setEditable(false);
		tFIdade.setText(String.valueOf(ControladorLogin.getInstance().getUsuarioLogado().getIdade()));
		tFIdade.setColumns(5);
		
		JLabel lblGnero = new JLabel("G\u00EAnero");
		lblGnero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JComboBox cBGenero = new JComboBox();
		cBGenero.setEnabled(false);
		cBGenero.setEditable(true);
		cBGenero.setModel(new DefaultComboBoxModel(Genero.values()));

		for (int i = 0; i < cBGenero.getModel().getSize(); ++i) {
			if (((Genero) cBGenero.getModel().getElementAt(i)) == ControladorLogin.getInstance().getUsuarioLogado().getGenero()) {
				cBGenero.setSelectedIndex(i);
				break;
			}
		}
		
		JLabel lblOcupao = new JLabel("Ocupa\u00E7\u00E3o");
		lblOcupao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tFOcupacao = new JTextField();
		tFOcupacao.setText(ControladorLogin.getInstance().getUsuarioLogado().getOcupacao());
		tFOcupacao.setEditable(false);
		tFOcupacao.setColumns(18);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		tFCep = new JTextField();
		tFCep.setText(ControladorLogin.getInstance().getUsuarioLogado().getCep());
		tFCep.setEditable(false);
		tFCep.setColumns(20);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblId)
								.addComponent(lblNome))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(tFId, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
									.addComponent(lblIdade)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tFIdade, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
								.addComponent(tFNome, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCep)
							.addGap(18)
							.addComponent(tFCep, 192, 192, 192))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblOcupao)
							.addGap(18)
							.addComponent(tFOcupacao, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblGnero)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cBGenero, 0, 173, Short.MAX_VALUE)))
					.addGap(15))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblId)
						.addComponent(tFId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tFIdade, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblIdade))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNome)
						.addComponent(tFNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGnero)
						.addComponent(cBGenero, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOcupao)
						.addComponent(tFOcupacao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCep)
						.addComponent(tFCep, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
				.addComponent(contentPanel, GroupLayout.PREFERRED_SIZE, 259, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addComponent(contentPanel, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnEntrar = new JButton("Editar");
		btnEntrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnEntrar.getText().equals("Editar")) {
					tFIdade.setEditable(true);
					tFOcupacao.setEditable(true);
					tFCep.setEditable(true);
					btnEntrar.setText("Salvar");
				} else {
					tFIdade.setEditable(false);
					tFOcupacao.setEditable(false);
					tFCep.setEditable(false);
					btnEntrar.setText("Editar");
					
					ControladorUsuario.getInstance().alterarUsuario(
							ControladorLogin.getInstance().getUsuarioLogado().getId(), 
							tFOcupacao.getText(), tFCep.getText(), 
							Integer.parseInt(tFIdade.getText()));
				}
			}
		});
		panel.add(btnEntrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		JButton btnRemoverConta = new JButton("Excluir");
		btnRemoverConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(getDialog(), 
						"Você deseja REALMENTE excluir sua conta? Esta ação não poderá ser desfeita.", 
						"Excluir conta?", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					if (ControladorUsuario.getInstance().removerUsuario(ControladorLogin.getInstance().getUsuarioLogado().getId())) {
						// TODO back to login
					} else {
						JOptionPane.showMessageDialog(getDialog(), 
								"A conta não pode ser excluida pois contém um ou mais filmes assistidos ou avaliados.", 
								"Erro", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnRemoverConta.setForeground(new Color(255, 0, 0));
		panel.add(btnRemoverConta);
		panel.add(btnCancelar);
		getContentPane().setLayout(groupLayout);
	}
	
	public JDialog getDialog() {
		return this;
	}
}
