package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import control.ControladorItem;
import control.ControladorLogin;
import control.ControladorUsuario;
import model.Filme;
import model.Item;
import model.Nota;

public class TelaPrincipal {
	private static TelaPrincipal instancia;
	
	private JFrame frmPodeSer;
	private TelaLogin telaLogin;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private TelaPrincipal() {
		initialize();
		this.telaLogin = new TelaLogin(frmPodeSer);
	}
	
	public static TelaPrincipal getInstance() {
		if (instancia == null) instancia = new TelaPrincipal();
		
		return instancia;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPodeSer = new JFrame();
		frmPodeSer.setTitle("Pode Ser? - Tela Principal");
		frmPodeSer.setBounds(100, 100, 700, 500);
		frmPodeSer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblBemVindoAo = new JLabel("Bem vindo ao Pode Ser!");
		lblBemVindoAo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		JSplitPane splitPane = new JSplitPane();
		
		GroupLayout groupLayout = new GroupLayout(frmPodeSer.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblBemVindoAo, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblBemVindoAo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		
		List<Item> filmes = ControladorItem.getInstance().getItens();
		Object[][] dadosFilmes = new Object[filmes.size()][5];
		
		if (ControladorLogin.getInstance().logado()) {
			for (int i = 0; i < filmes.size(); ++i) {
				dadosFilmes[i][0] = ((Filme) filmes.get(i)).getNome();
				if(((Filme) filmes.get(i)).getDataEstreia() != null) {
					dadosFilmes[i][1] = new SimpleDateFormat("dd-MMM-yyyy").format(((Filme) filmes.get(i)).getDataEstreia());
				} else dadosFilmes[i][1] = "-";
	
				float avaliacaoMedia = 0.0f;
				List<Nota> notas = ((Filme) filmes.get(i)).getNotas();
				for (int j = 0; j < notas.size(); ++j) {
					avaliacaoMedia += notas.get(j).getNota();
				}
				
				dadosFilmes[i][2] = avaliacaoMedia / notas.size();
				
				if(ControladorUsuario.getInstance().assistiu(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), (Filme) filmes.get(i))) {
					dadosFilmes[i][3] = "Sim";
				} else dadosFilmes[i][3] = "Não";
				
				if(ControladorUsuario.getInstance().avaliou(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), (Filme) filmes.get(i))) {
					dadosFilmes[i][4] = "Sim";
				} else dadosFilmes[i][4] = "Não";
			}
		}
		
		table.setModel(new DefaultTableModel(
				dadosFilmes,
				new String[] {
					"Nome", "Lan\u00E7ado em", "Avalia\u00E7\u00E3o M\u00E9dia", "Visto?", "Avaliado?"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, Float.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(2).setPreferredWidth(95);
			table.getColumnModel().getColumn(3).setPreferredWidth(45);
			table.getColumnModel().getColumn(4).setPreferredWidth(61);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		splitPane.setRightComponent(panel);
		
		JButton btnVerPerfil = new JButton("Ver Perfil");
		btnVerPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaPerfil telaPerfil = new TelaPerfil(frmPodeSer);
			}
		});
		toolBar.add(btnVerPerfil);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControladorLogin.getInstance().deslogar();
				sair();
			}
		});
		toolBar.add(btnSair);
		frmPodeSer.getContentPane().setLayout(groupLayout);
	}
	
	public void sair() {
		this.frmPodeSer.setVisible(false);
		this.telaLogin = new TelaLogin(frmPodeSer);
	}
	
	public void updateTable() {
		List<Item> filmes = ControladorItem.getInstance().getItens();
		Object[][] dadosFilmes = new Object[filmes.size()][5];
		
		if (ControladorLogin.getInstance().logado()) {
			for (int i = 0; i < filmes.size(); ++i) {
				dadosFilmes[i][0] = ((Filme) filmes.get(i)).getNome();
				
				if(((Filme) filmes.get(i)).getDataEstreia() != null) {
					dadosFilmes[i][1] = new SimpleDateFormat("dd-MMM-yyyy").format(((Filme) filmes.get(i)).getDataEstreia());
				} else dadosFilmes[i][1] = "-";
	
				float avaliacaoMedia = 0.0f;
				List<Nota> notas = ((Filme) filmes.get(i)).getNotas();
				for (int j = 0; j < notas.size(); ++j) {
					avaliacaoMedia += notas.get(j).getNota();
				}
				
				dadosFilmes[i][2] = avaliacaoMedia / notas.size();
				
				if(ControladorUsuario.getInstance().assistiu(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), (Filme) filmes.get(i))) {
					dadosFilmes[i][3] = "Sim";
				} else dadosFilmes[i][3] = "Não";
				
				if(ControladorUsuario.getInstance().avaliou(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), (Filme) filmes.get(i))) {
					dadosFilmes[i][4] = "Sim";
				} else dadosFilmes[i][4] = "Não";
			}
		}
		
		table.setModel(new DefaultTableModel(
				dadosFilmes,
				new String[] {
					"Nome", "Lan\u00E7ado em", "Avalia\u00E7\u00E3o M\u00E9dia", "Visto?", "Avaliado?"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, Float.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false, true, true
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			table.getColumnModel().getColumn(2).setPreferredWidth(95);
			table.getColumnModel().getColumn(3).setPreferredWidth(45);
			table.getColumnModel().getColumn(4).setPreferredWidth(61);
	}
}
