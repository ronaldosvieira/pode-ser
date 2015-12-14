package model;

import java.util.Random;

public class Usuario {
	private int id;
	private String nome;
	private int idade;
	private Genero genero;
	private String ocupacao;
	private String cep;
	
	private boolean ativo;
	
	private static String[] listaNomeM = {"Bruno", "Carlos Eduardo", "Fellipe", "Filipe", "Marcelo", 
			"Ricardo", "Leandro", "Marcel", "Lu�s Fernando", "Ronaldo", "Rafael"};
	private static String[] listaNomeF = {"Juliana", "Raquel", "Adria", "L�gia", "Nat�lia", "Isabel", 
			"Maria Elizabeth"};
	private static String[] listaSobrenome = {"Dembogurski", "Mello", "Duarte", "Braida", "Zamith", 
			"Corr�a", "Alvim", "Nascente", "Bravo", "Lyra", "Passos", "Silva", "Orleans", "Schots", 
			"Goldschmidt", "Fernandes", "Maiani", "Puelles"};
	
	public static String generateName(Genero genero) {
		Random r = new Random();
		String nome = "";
		
		if (genero.equals(Genero.HomemCisgenero)) {
			nome += listaNomeM[r.nextInt(listaNomeM.length)] + " ";
		} else if (genero.equals(Genero.MulherCisgenero)) {
			nome += listaNomeF[r.nextInt(listaNomeF.length)] + " ";
		} else {
			int random = r.nextInt(listaNomeM.length + listaNomeF.length);
			
			if (random < listaNomeM.length) nome += listaNomeM[random] + " ";
			else nome += listaNomeF[random - listaNomeM.length] + " ";
		}
		
		nome += listaSobrenome[r.nextInt(listaSobrenome.length)] + " ";
		nome += listaSobrenome[r.nextInt(listaSobrenome.length)];
		
		return nome;
	}
	
	public Usuario(int id, String nome, int idade, Genero genero, 
			String ocupacao, String cep) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.genero = genero;
		this.ocupacao = ocupacao;
		this.cep = cep;
		this.ativo = true;
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public Genero getGenero() {
		return genero;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public String getCep() {
		return cep;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	public void desativarUsuario() {
		this.ativo = false;
	}
}
