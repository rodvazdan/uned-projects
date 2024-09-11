package es.uned.sidi.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author Daniel Vázquez Rodríguez
 *         dvazquez277@alumno.uned.es
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8028908185430642163L;
	
	private String name = null;
	private String nick = null;
	private String password = null;
	
	private boolean isOnline = false;
	
	private List<User> followers = null;
	
	//private List<Trino> trinos = null;
	
	public User (String nick, String password) {
		this.nick = nick;
		this.password = password;
	}
	
	public User (String name, String nick, String password) {
		this.name = name;
		this.nick = nick;
		this.password = password;
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void isOnline (boolean isOnline) {
		this.isOnline = isOnline;
	}
	
	public boolean isOnline() {
		return isOnline;
	}
	
	@Override
	public boolean equals (Object anObject) {
		return this.nick.equals(((User)anObject).getNick())
			&& this.password.equals(((User)anObject).getPassword());
	}

}