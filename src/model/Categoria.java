package model;

import java.util.HashMap;
import java.util.Map;

public enum Categoria {
	Unknown(0, "Desconhecido"), Action(1, "Ação"), Adventure(2, "Aventura"),
	Animation(3, "Animação"), Childrens(4, "Infantil"), Comedy(5, "Comédia"), 
	Crime(6, "Crime"), Documentary(7, "Documentário"), Drama(8, "Drama"),
	Fantasy(9, "Fantasia"), FilmNoir(10, "Film-Noir"), Horror(11, "Horror"),
	Musical(12, "Musical"), Mystery(13, "Mistério"), Romance(14, "Romance"),
	SciFi(15, "Sci-fi"), Thriller(16, "Thriller"), War(17, "Guerra"),
	Western(18, "Velho Oeste");
	
	private int id;
	private String nome;
	
	private static final Map<Integer, Categoria> lookup = new HashMap<>();
	
	Categoria(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	static {
	    for(Categoria c : Categoria.values())
	        lookup.put(c.getId(), c);
	}
	
	public static Categoria get(int id) { 
	     return lookup.get(id); 
	}
}
