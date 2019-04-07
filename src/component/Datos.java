package component;

import java.util.Calendar;

public class Datos {

	// atributos
	private String bd;
	private String user;
	private String tipusConsulta;
	private String sentencia;
	private Calendar dataConsulta;
	private int numRegistres;

	// constructor vacio de la clase
	public Datos() {

	}

	// constructor de la clase
	public Datos(String bd, String user, String tipusConsulta, String sentencia, Calendar dataConsulta,
			int numRegistres) {
		this.bd = bd;
		this.user = user;
		this.tipusConsulta = tipusConsulta;
		this.sentencia = sentencia;
		this.dataConsulta = dataConsulta;
		this.numRegistres = numRegistres;
	}

	// getters y setters
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

	public String getTipusConsulta() {
		return tipusConsulta;
	}

	public void setTipusConsulta(String tipusConsulta) {
		this.tipusConsulta = tipusConsulta;
	}

	public String getSentencia() {
		return sentencia;
	}

	public void setSentencia(String sentencia) {
		this.sentencia = sentencia;
	}

	public Calendar getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(Calendar dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public int getNumRegistres() {
		return numRegistres;
	}

	public void setNumRegistres(int numRegistres) {
		this.numRegistres = numRegistres;
	}

	// metodo toString
	@Override
	public String toString() {
		return "Datos [bd=" + bd + ", user=" + user + ", tipusConsulta=" + tipusConsulta + ", sentencia=" + sentencia
				+ ", dataConsulta=" + dataConsulta + ", numRegistres=" + numRegistres + "]";
	}

	// metodo del formato del log (fecha y hora)
	public String formatoData() {
		return dataConsulta.get(Calendar.YEAR) + "-" + dataConsulta.get(Calendar.MONTH) + "-"
				+ dataConsulta.get(Calendar.DAY_OF_MONTH) + " " + dataConsulta.get(Calendar.HOUR_OF_DAY) + ":"
				+ dataConsulta.get(Calendar.MINUTE) + ":" + dataConsulta.get(Calendar.SECOND);
	}
}