package msmovimientoscuentapersonasmobileexp.service;
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto;


import java.time.LocalDate;
import java.util.List;


public interface CalendarioService {

    /**
     * Retorna los proximos dias hábiles hasta el tope definido en config server
     *
     * @param cantDiasHabilesDesde
     * @return
     */
    List<String> proximosDiasHabiles(int cantDiasHabilesDesde, LocalDate fechaIngresada);
    List<String> proximosDiasHabiles(int cantDiasHabilesDesde, List<String> diasComuna, List<DiaFeriadoDto> _feriados);

}