package component;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class Event implements PropertyChangeListener {

	private ArrayList<Datos> registro = new ArrayList<>();

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		registro.add((Datos) evt.getNewValue());
	}

	// getters y setters
	public ArrayList<Datos> getDatos() {
		return registro;
	}

	public void setDatos(ArrayList<Datos> datos) {
		this.registro = datos;
	}

	public Datos getDato(int i) {
		return registro.get(i);
	}

	public void setDato(Datos dato, int i) {
		this.registro.set(i, dato);
	}
}
