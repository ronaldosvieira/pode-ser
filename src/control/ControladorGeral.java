package control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Categoria;
import model.Filme;
import model.Item;

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
	
	public static void main(String[] args) {
		ControladorGeral cg = ControladorGeral.getInstance();
		
		cg.serializarBancoDeDados();
	}
}