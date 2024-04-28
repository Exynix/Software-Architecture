package modelo;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import logic.Usuario;

/**
 * Session Bean implementation class Datosusuario
 */
@Stateless
public class Datosusuario implements DatosusuarioRemote {

	public Datosusuario() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Usuario validarUsuario(String userName, String password) {
		String consult = "SELECT u FROM Usuario u WHERE u.nombre=:userName AND u.password=:password";
		TypedQuery<Usuario> query = entityManager.createQuery(consult, Usuario.class);
		query.setParameter("userName", userName);
		query.setParameter("password", password);
		try {
			Usuario result = query.getSingleResult();
			return result;
		} catch (NoResultException e) {
			return null;
		}
	}

}
