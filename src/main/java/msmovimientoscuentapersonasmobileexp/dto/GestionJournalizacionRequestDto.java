package msmovimientoscuentapersonasmobileexp.dto;

import lombok.Builder;
import lombok.Data;

/** 
 * 
 * Clase de solicitud para api eventos  
 * 
 *@author: Patricio Zúñiga
 */
@Data
@Builder
public class GestionJournalizacionRequestDto {

	private String rutCliente;
	private String evento;
	private String subEvento;
	private String estadoEventoNegocio;	
	private String clavePrincipal;
	private String medio;
	private String monto;
	private String campoVariable;
}
