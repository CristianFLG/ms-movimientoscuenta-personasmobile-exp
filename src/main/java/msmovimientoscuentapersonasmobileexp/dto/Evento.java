package msmovimientoscuentapersonasmobileexp.dto;

import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/** 
 * 
 * Evento Jornal 
 * 
 *@author: Patricio Zúñiga
 */
@Getter
public class Evento {

    private String uuid = "";

    //rut-dv , rut-dv + rut-apoderado , ejecutivo
    private String idUsuario;
    private String producto;
    private String evento;
    private String subevento;
    private String fechaEvento;
    private Map<String,Object> contexto;
    private Map<String,Object> request;
    private Map<String,Object> payload;
    private Map<String,Object> retrocompatibilidad;
    private String status;
    private String error = "0";

    private Evento() {
    }

    public String getUuid() {
        return uuid;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getProducto() {
        return producto;
    }

    public String getEvento() {
        return evento;
    }

    public String getSubevento() {
        return subevento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public Map<String, Object> getContexto() {
        return contexto;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }
    
    public Map<String, Object> getRetrocompatibilidad() {
    	return retrocompatibilidad;
    }
    
    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public static EventoBuilder newBuilder(){
        return new EventoBuilder();
    }

	/** 
	 * 
	 * 
	 *@author: Everis S.A. 
	 */
    public final static class EventoBuilder {// NOSONAR

        final Evento instance;

        EventoBuilder() {
            this.instance =  new Evento();
        }

        public EventoBuilder uuid(String uuid) {
            this.instance.uuid = uuid;
            return this;
        }

        public EventoBuilder idUsuario(String idUsuario) {
            this.instance.idUsuario = idUsuario;
            return this;
        }

        public EventoBuilder producto(String producto) {
            this.instance.producto = producto;
            return this;
        }

        public EventoBuilder evento(String evento) {
            this.instance.evento = evento;
            return this;
        }

        public EventoBuilder subevento(String subevento) {
            this.instance.subevento = subevento;
            return this;
        }

        public EventoBuilder fechaEvento(String fechaEvento) {
            this.instance.fechaEvento = fechaEvento;
            return this;
        }

        public EventoBuilder generatedNow() {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        	this.instance.fechaEvento = sdf.format(new Date());
            return this;
        }

        public EventoBuilder contexto(Map<String, Object> contexto) {
            this.instance.contexto = contexto;
            return this;
        }
        
        public EventoBuilder payload(Map<String, Object> payload) {
            this.instance.payload = payload;
            return this;
        }

        public EventoBuilder retrocompatibilidad(Map<String, Object> retrocompatibilidad) {
        	this.instance.retrocompatibilidad = retrocompatibilidad;
            return this;
        }
        
        public EventoBuilder status(String status) {
            this.instance.status = status;
            return this;
        }

        public EventoBuilder error(String error) {
            this.instance.error = error;
            return this;
        }

        public Evento build(){
            return this.instance;
        }

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Evento{");
        sb.append("uuid='").append(uuid).append('\'');
        sb.append(", idUsuario='").append(idUsuario).append('\'');
        sb.append(", producto='").append(producto).append('\'');
        sb.append(", evento='").append(evento).append('\'');
        sb.append(", subevento='").append(subevento).append('\'');
        sb.append(", fechaEvento='").append(fechaEvento).append('\'');
        sb.append(", contexto=").append(contexto);
        sb.append(", payload=").append(payload);
        sb.append(", retrocompatibilidad=").append(retrocompatibilidad);
        sb.append(", status='").append(status).append('\'');
        sb.append(", error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
