package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import model.Genero;
import control.ControladorUsuario;

public class TelaCadastrarUsuario extends JDialog {
	
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	private JTextField campoNome;
	private JTextField campoOcupacao;
	private JTextField campoCEP;
	private JPasswordField campoSenha;
	
	private JLabel nomeInvalido;
	private JLabel ocupacaoInvalida;
	private JLabel cepInvalido;
	private JLabel senhaInvalida;
	private JLabel generoInvalido;
	
	private JSpinner campoIdade;
	
	private JList campoGenero;
	
	private boolean hasErro;
	
	public TelaCadastrarUsuario(){		
		initialize();
	}
	private void initialize() {
		setResizable(false);
		setTitle("Novo Usuario");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 645, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDados = new JPanel();
		panelDados.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelDados.setBounds(23, 11, 389, 358);
		contentPane.add(panelDados);
		
		JLabel labelNome = new JLabel("Nome:");
		labelNome.setBounds(15, 17, 57, 16);
		labelNome.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoNome = new JTextField();
		campoNome.setBounds(90, 16, 272, 20);
		campoNome.setColumns(15);
		
		JLabel labelOcupacao = new JLabel("Ocupa\u00E7\u00E3o:");
		labelOcupacao.setBounds(15, 48, 65, 16);
		labelOcupacao.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel labelSenha = new JLabel("Senha:");
		labelSenha.setBounds(15, 124, 43, 16);
		labelSenha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JLabel labelCep = new JLabel("CEP:");
		labelCep.setBounds(15, 87, 43, 16);
		labelCep.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoOcupacao = new JTextField();
		campoOcupacao.setBounds(90, 47, 272, 20);
		campoOcupacao.setColumns(15);
		
		campoCEP = new JTextField();
		campoCEP.setBounds(90, 86, 272, 20);
		campoCEP.setColumns(15);
		
		campoSenha = new JPasswordField();
		campoSenha.setBounds(90, 123, 272, 20);
		campoSenha.setColumns(15);
		panelDados.setLayout(null);
		panelDados.add(labelNome);
		panelDados.add(campoNome);
		panelDados.add(labelOcupacao);
		panelDados.add(labelSenha);
		panelDados.add(labelCep);
		panelDados.add(campoOcupacao);
		panelDados.add(campoCEP);
		panelDados.add(campoSenha);
		
		JLabel labelGenero = new JLabel("G\u00EAnero:");
		labelGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelGenero.setBounds(15, 151, 57, 14);
		panelDados.add(labelGenero);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 154, 268, 130);
		panelDados.add(scrollPane);
		
		campoGenero = new JList();
		campoGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		
		campoGenero.setListData(Genero.values());	
		campoGenero.setSelectedIndex(0);
		scrollPane.setViewportView(campoGenero);
		campoGenero.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JLabel labelIdade = new JLabel("Idade:");
		labelIdade.setBounds(15, 307, 43, 16);
		panelDados.add(labelIdade);
		labelIdade.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		campoIdade = new JSpinner();
		campoIdade.setModel(new SpinnerNumberModel(18, 10, 200, 1));
		campoIdade.setBounds(90, 306, 57, 20);
		panelDados.add(campoIdade);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hasErro=false;
				
		    	nomeInvalido.setVisible(false);
		    	cepInvalido.setVisible(false);
		    	ocupacaoInvalida.setVisible(false);
		    	senhaInvalida.setVisible(false);
		    	generoInvalido.setVisible(false);
		    	
		    
		    	if(campoNome.getText().trim().length()==0 ){		    		
		    		hasErro = true;
		    		nomeInvalido.setVisible(true);
		    	} 
		    	
		    	if(campoCEP.getText().trim().length()==0 ){		    		
		    		hasErro = true;
		    		cepInvalido.setVisible(true);
		    	}
		    	
		    	if(campoOcupacao.getText().trim().length()== 0) {		    		
		    		hasErro = true;
		    		ocupacaoInvalida.setVisible(true);		    		
		    	}		    	
		        
		    	if (campoSenha.getPassword().length ==0){
		    		
		    		hasErro = true;
		    		senhaInvalida.setVisible(true);
		    	}
		    	
		    	if (campoGenero.isSelectionEmpty()){		    		
		    		hasErro = true;
		    		generoInvalido.setVisible(true);
		    	}
		    	
		    	if(!hasErro) {
		    		int idade = Integer.parseInt(campoIdade.getValue().toString());
		    		
		    		ControladorUsuario.getInstance().inserirUsuario(campoNome.getText().trim(), idade
		    				, (Genero)campoGenero.getSelectedValue(), campoOcupacao.getText().trim(), campoCEP.getText().trim());
		    			    		
		    		dispose();
		    	}
			}
		});
		btnConfirmar.setBounds(406, 380, 93, 23);
		contentPane.add(btnConfirmar);
		
		JButton button_1 = new JButton("Cancelar");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		button_1.setBounds(509, 380, 88, 23);
		contentPane.add(button_1);
		
		nomeInvalido = new JLabel("Preencha o campo!");
		nomeInvalido.setForeground(Color.RED);
		nomeInvalido.setBounds(450, 33, 150, 14);
		nomeInvalido.setVisible(false);
		contentPane.add(nomeInvalido);
		
		ocupacaoInvalida = new JLabel("Preencha o campo!!");
		ocupacaoInvalida.setForeground(Color.RED);
		ocupacaoInvalida.setBounds(450, 58, 150, 14);
		ocupacaoInvalida.setVisible(false);
		contentPane.add(ocupacaoInvalida);
		
		cepInvalido = new JLabel("Preencha o campo!");
		cepInvalido.setForeground(Color.RED);
		cepInvalido.setBounds(450, 100, 150, 14);
		cepInvalido.setVisible(false);
		contentPane.add(cepInvalido);
		
		senhaInvalida = new JLabel("Preencha o campo!");
		senhaInvalida.setForeground(Color.RED);
		senhaInvalida.setBounds(450, 136, 150, 14);
		senhaInvalida.setVisible(false);
		contentPane.add(senhaInvalida);
		
		
		generoInvalido = new JLabel("Preencha o campo!");
		generoInvalido.setForeground(Color.RED);
		generoInvalido.setBounds(450, 218, 150, 14);
		generoInvalido.setVisible(false);
		contentPane.add(generoInvalido);
	}
	
	public JDialog getDialog() {
		return this;
	}
}
