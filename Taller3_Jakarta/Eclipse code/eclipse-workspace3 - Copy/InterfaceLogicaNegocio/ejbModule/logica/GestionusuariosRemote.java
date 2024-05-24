package logica;

import jakarta.ejb.Remote;
import logic.Usuario;

@Remote
public interface GestionusuariosRemote {

	Usuario validarUsuario(String userName, String password);

	String pagar();

}
