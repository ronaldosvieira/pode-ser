package control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Date;

import model.Filme;
import model.Nota;

public class ControladorGeral {
	private static ControladorGeral instancia;
	
	static ControladorUsuario cUsuario = ControladorUsuario.getInstance();
	static ControladorItem cItem = ControladorItem.getInstance();

	private ControladorGeral() {
		try {
			deserializarBancoDeDados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ControladorGeral getInstance() {
		if (instancia == null) instancia = new ControladorGeral();
		
		return instancia;
	}
	
	public void serializarBancoDeDados() {
		cUsuario.serializarBancoDeDados();
		cItem.serializarBancoDeDados();
	}

	public void deserializarBancoDeDados() throws FileNotFoundException, IOException {
		cUsuario.deserializarBancoDeDados();
		cItem.deserializarBancoDeDados();
	}
	
	public void votar(int usuarioId, int filmeId, int notaInt) throws InvalidParameterException {
		if (notaInt > 5 || notaInt < 0) throw new InvalidParameterException("Vc fez merda seu babaca!");
		
		Filme filme = ((Filme) cItem.getItens().get(filmeId - 1));
		Nota notaObj;
		
		filme.inserirNota(new Nota(usuarioId, notaInt, new Date()));
	}
	
	public static void main(String[] args) {
		ControladorGeral cg = ControladorGeral.getInstance();
		
		//cg.serializarBancoDeDados();
	}
}