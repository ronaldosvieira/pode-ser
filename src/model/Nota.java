package model;

import java.util.Date;

public class Nota {
	private int usuarioId;
	private int nota;
	private Date data;
	
	public Nota(int usuarioId, int nota, Date data) {
		this.usuarioId = usuarioId;
		this.nota = nota;
		this.data = data;
	}
	
	public int getUsuarioId() {
		return usuarioId;
	}

	public int getNota() {
		return nota;
	}
	
	public Date getData() {
		return data;
	}
}
