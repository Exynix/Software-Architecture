package modelo;

import jakarta.ejb.Remote;
import logic.Usuario;

@Remote
public interface DatosusuarioRemote {

	Usuario validarUsuario(String name, String password);

}
