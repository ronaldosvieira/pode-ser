package model;

public class Usuario {
	private String nome;
	private int idade;
	private Genero genero;
	private String ocupacao;
	private String cep;
	
	public Usuario(String nome, int idade, Genero genero, 
			String ocupacao, String cep) {
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.ocupacao = ocupacao;
		this.cep = cep;
	}
	
	public void alterarDados(String ocupacao, String cep, Integer idade) {
		if (ocupacao != null) this.ocupacao = ocupacao;
		if (cep != null) this.cep = cep;
		if (idade != null) this.idade = idade;
	}
}
