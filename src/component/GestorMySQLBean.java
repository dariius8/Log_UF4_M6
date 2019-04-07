package component;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

public class GestorMySQLBean implements Serializable {

	// atributos
	private String url;
	private String bd;
	private String user;
	private String password;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private Event event;
	private PropertyChangeSupport changes;

	// constructor de la clase
	public GestorMySQLBean() {
		event = new Event();
		changes = new PropertyChangeSupport(this);
		addPropertyChangeListener(event);
	}

	// metodo para conectarse a la bd
	public void connect(String url, String bd, String user, String password) {
		this.url = url;
		this.bd = bd;
		this.user = user;
		this.password = password;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url + bd, user, password);
			if (connection != null) {
				System.out.println("¡Conexión Exitosa!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getters y setters
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBd() {
		return bd;
	}

	public void setBd(String bd) {
		this.bd = bd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// metodo para hacer el select
	private void select(String query) {
		Datos dato = new Datos();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			int contador = 0;
			while (resultSet.next())
				contador++;
			dato.setBd(this.bd);
			dato.setUser(this.user);
			dato.setTipusConsulta("SELECT");
			dato.setSentencia(query);
			dato.setDataConsulta(Calendar.getInstance());
			dato.setNumRegistres(contador);
			changes.firePropertyChange("SELECT", null, dato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// metodo para hacer el insert
	private void insert(String query) {
		Datos dato = new Datos();
		try {
			int nRegistres;
			statement = connection.createStatement();
			nRegistres = statement.executeUpdate(query);
			dato.setBd(this.bd);
			dato.setUser(this.user);
			dato.setTipusConsulta("INSERT");
			dato.setSentencia(query);
			dato.setDataConsulta(Calendar.getInstance());
			dato.setNumRegistres(nRegistres);
			changes.firePropertyChange("INSERT", null, dato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// metodo para hacer el delete
	private void delete(String query) {
		Datos dato = new Datos();
		try {
			int nRegistres;
			statement = connection.createStatement();
			nRegistres = statement.executeUpdate(query);
			dato.setBd(this.bd);
			dato.setUser(this.user);
			dato.setTipusConsulta("DELETE");
			dato.setSentencia(query);
			dato.setDataConsulta(Calendar.getInstance());
			dato.setNumRegistres(nRegistres);
			changes.firePropertyChange("DELETE", null, dato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// metodo para hacer el update
	private void update(String query) {
		Datos dato = new Datos();
		try {
			int nRegistres;
			statement = connection.createStatement();
			nRegistres = statement.executeUpdate(query);
			dato.setBd(this.bd);
			dato.setUser(this.user);
			dato.setTipusConsulta("UPDATE");
			dato.setSentencia(query);
			dato.setDataConsulta(Calendar.getInstance());
			dato.setNumRegistres(nRegistres);
			changes.firePropertyChange("UPDATE", null, dato);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// consulta de datos por base de datos, usuario y tipo
	public void consultarDatos(String bbdd, String user, String tipusConsulta) {
		for (Datos datos : event.getDatos()) {
			if (datos.getBd().equalsIgnoreCase(bbdd) && datos.getUser().equalsIgnoreCase(user)
					&& datos.getTipusConsulta().equalsIgnoreCase(tipusConsulta)) {
				// sentencia - fecha y hora
				System.out.println(datos.getSentencia() + " " + datos.getDataConsulta());
			}
		}
	}

	// consulta de datos por base de datos y tipo
	public void consultarDatos(String bbdd, String query) {
		if (query.equals("SELECT") || query.equals("INSERT") || query.equals("DELETE") || query.equals("UPDATE")) {
			for (Datos datos : event.getDatos()) {
				if (datos.getBd().equalsIgnoreCase(bbdd) && datos.getTipusConsulta().equalsIgnoreCase(query)) {
					// sentencia - fecha y hora - usuario
					System.out.println(datos.getSentencia() + " " + datos.getDataConsulta() + " " + datos.getUser());
				}
			}
			// consulta de datos por base de datos y usuario
		} else {
			for (Datos datos : event.getDatos()) {
				if (datos.getBd().equalsIgnoreCase(bbdd) && datos.getUser().equalsIgnoreCase(query)) {
					// sentencia - fecha y hora - tipo de consulta
					System.out.println(
							datos.getSentencia() + " " + datos.getDataConsulta() + " " + datos.getTipusConsulta());
				}
			}
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener l) {
		changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		changes.removePropertyChangeListener(l);
	}
}