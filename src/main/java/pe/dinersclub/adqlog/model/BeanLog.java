package pe.dinersclub.adqlog.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeanLog {

	@JsonProperty("identificador")
	private String identificador = "";
	@JsonProperty("idUsuario")
	private String idUsuario = "";
	@JsonProperty("solicitud")
	private String solicitud = "";
	@JsonProperty("nombreOperacion")
	private String nombreOperacion = "";
	@JsonProperty("metodo")
	private String metodo = "";
	@JsonProperty("codigoMensaje")
	private String codigoMensaje = "";
	@JsonProperty("descripcionMensaje")
	private String descripcionMensaje = "";
	@JsonProperty("duracion")
	private long duracion;
	@JsonProperty("consulta")
	private String consulta = "";
	@JsonProperty("parametrosConsulta")
	private String parametrosConsulta = "";
	@JsonProperty("tipoMensaje")
	private String tipoMensaje = "";
	@JsonProperty("fechaRegistro")
	private String fechaRegistro = "";
	@JsonProperty("horaRegistro")
	private String horaRegistro = "";
	@JsonProperty("aplicacion")
	private String aplicacion = "";
	@JsonProperty("solicitudOrigen")
	private String solicitudOrigen = "";
	@JsonProperty("esPadre")
	private boolean esPadre;
	@JsonProperty("direccionIP")
	private String direccionIP = "";

	public BeanLog() {

	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(String solicitud) {
		this.solicitud = solicitud;
	}

	public String getNombreOperacion() {
		return nombreOperacion;
	}

	public void setNombreOperacion(String nombreOperacion) {
		this.nombreOperacion = nombreOperacion;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

	public String getCodigoMensaje() {
		return codigoMensaje;
	}

	public void setCodigoMensaje(String codigoMensaje) {
		this.codigoMensaje = codigoMensaje;
	}

	public String getDescripcionMensaje() {
		return descripcionMensaje;
	}

	public void setDescripcionMensaje(String descripcionMensaje) {
		this.descripcionMensaje = descripcionMensaje;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getParametrosConsulta() {
		return parametrosConsulta;
	}

	public void setParametrosConsulta(String parametrosConsulta) {
		this.parametrosConsulta = parametrosConsulta;
	}

	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getHoraRegistro() {
		return horaRegistro;
	}

	public void setHoraRegistro(String horaRegistro) {
		this.horaRegistro = horaRegistro;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public boolean isEsPadre() {
		return esPadre;
	}

	public void setEsPadre(boolean esPadre) {
		this.esPadre = esPadre;
	}

	public String getSolicitudOrigen() {
		return solicitudOrigen;
	}

	public void setSolicitudOrigen(String solicitudOrigen) {
		this.solicitudOrigen = solicitudOrigen;
	}

	public String getDireccionIP() {
		return direccionIP;
	}

	public void setDireccionIP(String direccionIP) {
		this.direccionIP = direccionIP;
	}

	@Override
	public String toString() {
		return "BeanLog [identificador=" + identificador + ", idUsuario=" + idUsuario + ", solicitud=" + solicitud
				+ ", nombreOperacion=" + nombreOperacion + ", metodo=" + metodo + ", codigoMensaje=" + codigoMensaje
				+ ", descripcionMensaje=" + descripcionMensaje + ", duracion=" + duracion + ", consulta=" + consulta
				+ ", parametrosConsulta=" + parametrosConsulta + ", tipoMensaje=" + tipoMensaje + ", fechaRegistro="
				+ fechaRegistro + ", horaRegistro=" + horaRegistro + ", aplicacion=" + aplicacion + ", esPadre="
				+ esPadre + ", solicitudOrigen=" + solicitudOrigen + ", direccionIP=" + direccionIP + "]";
	}
	
}