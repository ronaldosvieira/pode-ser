package control;

import java.util.List;

import model.Usuario;
import view.TelaPrincipal;

public class ControladorLogin {
	private static ControladorLogin instancia;
	
	private Usuario usuarioLogado;
	
	private ControladorLogin() {
		
	}
	
	public static ControladorLogin getInstance() {
		if (instancia == null) instancia = new ControladorLogin();
		
		return instancia;
	}
	
	public boolean logar(String usuarioId, String senha) {
		List<Usuario> usuarios = ControladorUsuario.getInstance().getUsuarios();
		
		for (Usuario usuario : usuarios) {
			if (usuario.getNome().equals(usuarioId) && usuario.ativo()) {
				this.usuarioLogado = usuario;
				TelaPrincipal.getInstance().updateTable();
				return true;
			}
		}
		
		return false;
	}

	public void deslogar() {
		this.usuarioLogado = null;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public boolean logado() {
		return usuarioLogado != null;
	}
}
