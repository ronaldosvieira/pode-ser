package view;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import control.ControladorGeral;
import control.ControladorItem;
import control.ControladorLogin;
import control.ControladorUsuario;
import model.Categoria;
import model.Filme;
import model.Item;
import model.Nota;

public class TelaPrincipal {
	private static TelaPrincipal instancia;
	
	private JFrame frmPodeSer;
	private TelaLogin telaLogin;
	private JTable table;
	private JButton btnMarcarVisto;
	private JSlider slider;
	private JButton btnEnviarAvaliao;
	
	int lastSelectedRowIndex;

	private JSplitPane splitPane;

	private JLabel lblTtulo;

	private JLabel labelAval;

	private JLabel lblVistoPorVoc;

	private JLabel lblListadegeneros;
	private JButton btnFilmesMaisVotados;

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
	 * @wbp.parser.entryPoint
	 */
	private TelaPrincipal() {
		initialize();
		ControladorGeral.getInstance();
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
		frmPodeSer.setBounds(100, 100, 750, 500);
		frmPodeSer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblBemVindoAo = new JLabel("Bem vindo ao Pode Ser!");
		lblBemVindoAo.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		splitPane = new JSplitPane();
		
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
		scrollPane.setMinimumSize(new Dimension(450, 0));
		splitPane.setLeftComponent(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setCellSelectionEnabled(true);
		updateTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		splitPane.setRightComponent(panel);
		
		btnMarcarVisto = new JButton("Marcar como visto");
		btnMarcarVisto.setEnabled(false);
		btnMarcarVisto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnMarcarVisto.getText().equals("Marcar como visto")) {
					btnMarcarVisto.setText("Marcar como não visto");
					lblVistoPorVoc.setText("Visto por você");
					lblVistoPorVoc.setForeground(new Color(50, 205, 50));
				} else {
					btnMarcarVisto.setText("Marcar como visto");
					lblVistoPorVoc.setText("Não visto por você");
					lblVistoPorVoc.setForeground(new Color(205, 50, 50));
				}
				
				ControladorItem.getInstance().setVisto(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), 
						((Filme) ControladorItem.getInstance().getItens().get(lastSelectedRowIndex)).getId());
				updateTableModel();
			}
		});
		
		JLabel lblAvaliar = new JLabel("Avaliar:");
		lblAvaliar.setForeground(Color.WHITE);
		
		slider = new JSlider();
		slider.setEnabled(false);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setForeground(Color.WHITE);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setValue(3);
		slider.setSnapToTicks(true);
		slider.setMaximum(5);
		slider.setMinimum(1);
		slider.setBackground(Color.DARK_GRAY);
		
		btnEnviarAvaliao = new JButton("Enviar avaliação");
		btnEnviarAvaliao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnEnviarAvaliao.setText("Mudar avaliação");
				ControladorItem.getInstance().avaliar(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), 
						lastSelectedRowIndex + 1, slider.getValue());
				
				TelaPrincipal.getInstance().updateTableModel();
			}
		});
		btnEnviarAvaliao.setEnabled(false);
		
		lblTtulo = new JLabel("Título");
		lblTtulo.setFont(new Font("Dialog", Font.BOLD, 14));
		lblTtulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTtulo.setForeground(Color.WHITE);
		
		labelAval = new JLabel("");
		labelAval.setForeground(Color.WHITE);
		
		JLabel lblAvaliaoMdia = new JLabel("Avaliação média:");
		lblAvaliaoMdia.setForeground(Color.WHITE);
		
		lblVistoPorVoc = new JLabel("");
		
		JLabel lblGneros = new JLabel("Gêneros:");
		lblGneros.setForeground(new Color(255, 255, 255));
		
		lblListadegeneros = new JLabel("");
		lblListadegeneros.setForeground(new Color(255, 255, 255));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTtulo, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(btnMarcarVisto)
									.addComponent(lblAvaliar, Alignment.TRAILING))
								.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(btnEnviarAvaliao, Alignment.TRAILING))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblAvaliaoMdia)
							.addGap(18)
							.addComponent(labelAval))
						.addComponent(lblVistoPorVoc, Alignment.TRAILING)
						.addComponent(lblGneros, Alignment.TRAILING)
						.addComponent(lblListadegeneros, Alignment.TRAILING))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTtulo, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelAval)
						.addComponent(lblAvaliaoMdia))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblVistoPorVoc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGneros)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblListadegeneros)
					.addPreferredGap(ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
					.addComponent(btnMarcarVisto)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblAvaliar)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(slider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEnviarAvaliao)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
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
		
		JButton btnObterRecomendaes = new JButton("Obter Recomendações");
		btnObterRecomendaes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRecomendacoes tR = new TelaRecomendacoes(
						ControladorItem.getInstance().getRecomendacoes(
								ControladorLogin.getInstance().getUsuarioLogado().getId()));
				tR.setVisible(true);
			}
		});
		toolBar.add(btnObterRecomendaes);
		
		btnFilmesMaisVotados = new JButton("Filmes Mais Votados");
		btnFilmesMaisVotados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaMaisVotados tMV = new TelaMaisVotados(
						ControladorItem.getInstance().getMaisVotados());
				tMV.setVisible(true);
			}
		});
		toolBar.add(btnFilmesMaisVotados);
		toolBar.add(btnSair);
		frmPodeSer.getContentPane().setLayout(groupLayout);
	}
	
	public void sair() {
		this.frmPodeSer.setVisible(false);
		this.telaLogin = new TelaLogin(frmPodeSer);
	}
	
	public void updateTable() {
		updateTableModel();
		
		ListSelectionModel lsm = table.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent event) {
				if (event.getSource() == table.getSelectionModel() 
						&& event.getFirstIndex() >= 0
						&& table.getSelectedRow() >= 0) {
					table.setRowSelectionInterval(table.getSelectedRow(), table.getSelectedRow());
					table.setColumnSelectionInterval(0, table.getColumnCount() - 1);
					lastSelectedRowIndex = table.getSelectedRow();
					
					Nota nota = ControladorItem.getInstance().obterAvaliacao(
							ControladorLogin.getInstance().getUsuarioLogado().getId(),
							lastSelectedRowIndex + 1);
					
					if (nota != null) {
						slider.setValue(nota.getNota());
						btnEnviarAvaliao.setText("Mudar avaliação");
					}
					else {
						slider.setValue(3);
						btnEnviarAvaliao.setText("Enviar avaliação");
					}
					
					if (ControladorItem.getInstance().visto(
							ControladorLogin.getInstance().getUsuarioLogado().getId(),
							lastSelectedRowIndex + 1)) {
						btnMarcarVisto.setText("Marcar como não visto");
					} else {
						btnMarcarVisto.setText("Marcar como visto");
					}
					
					lblTtulo.setText(((Filme) ControladorItem.getInstance().getItens().get(lastSelectedRowIndex)).getNome());
					labelAval.setText(String.valueOf(table.getValueAt(lastSelectedRowIndex, 3)));
					
					if (ControladorItem.getInstance().visto(
							ControladorLogin.getInstance().getUsuarioLogado().getId(), lastSelectedRowIndex + 1)) {
						lblVistoPorVoc.setText("Visto por você");
						lblVistoPorVoc.setForeground(new Color(50, 205, 50));
					} else {
						lblVistoPorVoc.setText("Não visto por você");
						lblVistoPorVoc.setForeground(new Color(205, 50, 50));
					}
					
					List<Categoria> categorias = ((Filme) ControladorItem.getInstance().getItens().get(lastSelectedRowIndex)).getCategorias();
					String cats = "";
					
					for (Categoria categoria : categorias) {
						cats += categoria.getNome() + " ";
					}
					
					lblListadegeneros.setText(cats);
					
					slider.setEnabled(true);
					btnEnviarAvaliao.setEnabled(true);
					btnMarcarVisto.setEnabled(true);
					splitPane.setResizeWeight(1.0);
				}
			}
		});
		
		table.setSelectionModel(lsm);
	}

	public void updateTableModel() {
		List<Item> filmes = ControladorItem.getInstance().getItens();
		Object[][] dadosFilmes = new Object[filmes.size()][6];
		
		if (ControladorLogin.getInstance().logado()) {
			for (int i = 0; i < filmes.size(); ++i) {
				dadosFilmes[i][1] = ((Filme) filmes.get(i)).getNome();
				
				if(((Filme) filmes.get(i)).getDataEstreia() != null) {
					dadosFilmes[i][2] = new SimpleDateFormat("dd-MMM-yyyy").format(((Filme) filmes.get(i)).getDataEstreia());
				} else dadosFilmes[i][2] = "-";
	
				float avaliacaoMedia = 0.0f;
				List<Nota> notas = ((Filme) filmes.get(i)).getNotas();
				for (int j = 0; j < notas.size(); ++j) {
					avaliacaoMedia += notas.get(j).getNota();
				}
				
				dadosFilmes[i][3] = avaliacaoMedia / notas.size();
				
				if(ControladorUsuario.getInstance().assistiu(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), (Filme) filmes.get(i))) {
					dadosFilmes[i][4] = "Sim";
				} else dadosFilmes[i][4] = "Não";
				
				if(ControladorUsuario.getInstance().avaliou(
						ControladorLogin.getInstance().getUsuarioLogado().getId(), (Filme) filmes.get(i))) {
					dadosFilmes[i][5] = "Sim";
				} else dadosFilmes[i][5] = "Não";
			}
		}
		
		table.setModel(new DefaultTableModel(
			dadosFilmes,
			new String[] {
				"ID", "Nome", "Lan\u00E7ado em", "Avalia\u00E7\u00E3o M\u00E9dia", "Visto?", "Avaliado?"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Float.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false , false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(3).setPreferredWidth(95);
		table.getColumnModel().getColumn(4).setPreferredWidth(45);
		table.getColumnModel().getColumn(5).setPreferredWidth(61);
	}
}
