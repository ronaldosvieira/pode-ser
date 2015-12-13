package model;

import java.util.Date;
import java.util.List;

public class Filme extends Item {
	private int id;
	private String nome;
	private Date dataEstreia;
	private Date dataEstreiaVideo;
	private String imdbUrl;
	private List<Categoria> categorias;
	
	public Filme(int id, String nome, Date dataEstreia, Date dataEstreiaVideo,
			String imdbUrl, List<Categoria> categorias) {
		this.id = id;
		this.nome = nome;
		this.dataEstreia = dataEstreia;
		this.dataEstreiaVideo = dataEstreiaVideo;
		this.imdbUrl = imdbUrl;
		this.categorias = categorias;
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
}
