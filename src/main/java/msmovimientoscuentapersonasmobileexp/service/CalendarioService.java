package msmovimientoscuentapersonasmobileexp.service;


import msmovimientoscuentapersonasmobileexp.apis.dominio.RegionDto;

import java.time.LocalDate;
import java.util.List;


public interface CalendarioService {

    /**
     * Retorna los proximos dias hábiles hasta el tope definido en config server
     *
     * @param cantDiasHabilesDesde
     * @return
     */
    List<String> proximosDiasRegion(LocalDate fechaIngresada, RegionDto Regiones);
    List<String> proximosDiasHabiles(int cantDiasHabilesDesde, LocalDate fechaIngresada);
    List<String> proximosDiasHabiles(int cantDiasHabilesDesde, List<String> diasComuna, LocalDate fechaIngresada);

}