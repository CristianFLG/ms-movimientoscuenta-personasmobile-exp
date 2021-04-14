package msmovimientoscuentapersonasmobileexp.apis;


import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto;

import java.util.List;

/**
 * Interfaz del cliente que interactua con componente utilitario
 * para feriados y cálculos de fechas hábiles.
 *
 * @author SEnTRA
 *
 * <p>
 * <b>Todos los derechos reservados por Banco de Crédito e Inversiones.</b>
 * </p>
 */
public interface FeriadosUtilClient {

    /**
     * Obtiene fecha habil a partir de horario.
     * @param horaDesde Horario inicio para fecha contable.
     * @param horaHasta Horario de termino para fecha contable.
     *
     * @return Objeto con información de fecha habil.
     */
    List<DiaFeriadoDto> obtenerFeriadosAnio2(int anio);

    /**
     * Obtiene lista de feriados observados por anio
     *
     * /feriados
     * /feriados?anio=2020
     * @param anio
     * @return
     */
    List<DiaFeriadoDto> obtenerFeriadosAnio(int anio);
}
