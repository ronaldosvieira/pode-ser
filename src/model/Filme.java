package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filme extends Item {
	private int id;
	private String nome;
	private Date dataEstreia;
	private Date dataEstreiaVideo;
	private String imdbUrl;
	private List<Categoria> categorias;
	private List<Nota> notas;
	private List<Integer> assistidoPor;
	
	public Filme(int id, String nome, Date dataEstreia, Date dataEstreiaVideo,
			String imdbUrl, List<Categoria> categorias) {
		this.id = id;
		this.nome = nome;
		this.dataEstreia = dataEstreia;
		this.dataEstreiaVideo = dataEstreiaVideo;
		this.imdbUrl = imdbUrl;
		this.categorias = categorias;
		this.notas = new ArrayList<>();
		this.assistidoPor = new ArrayList<Integer>();
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Date getDataEstreia() {
		return dataEstreia;
	}

	public Date getDataEstreiaVideo() {
		return dataEstreiaVideo;
	}

	public String getImdbUrl() {
		return imdbUrl;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}
	
	public List<Nota> getNotas() {
		return notas;
	}
	
	public List<Integer> getAssistidoPor() {
		return assistidoPor;
	}
	
	public void inserirNota(Nota nota) {
		for (int i = 0; i < notas.size(); ++i) {
			if (notas.get(i).getUsuarioId() == nota.getUsuarioId()) {
				notas.set(i, nota);
				return;
			}
		}
		
		this.notas.add(nota);
	}
	
	public void inserirAssistidoPor(int usuarioId) {
		this.assistidoPor.add(usuarioId);
	}
}
