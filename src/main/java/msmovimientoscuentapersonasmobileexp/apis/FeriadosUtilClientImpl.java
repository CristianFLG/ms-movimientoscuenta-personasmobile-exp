package msmovimientoscuentapersonasmobileexp.apis;

import lombok.extern.slf4j.Slf4j;
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * <b>Todos los derechos reservados por Banco de Crédito e Inversiones.</b>
 * </p>
 *
 * @see FeriadosUtilClient
 */
@Component
@Slf4j
public class FeriadosUtilClientImpl implements FeriadosUtilClient {

    /**
     * Clase para invocacion a servicios.
     */



    /**
     * Clase donde se obtienen configuraciones del servicio.
     */


    /**
     * @see FeriadosUtilClient#obtenerFechaHabil(String, String)
     */
    @Override
    public List<DiaFeriadoDto> obtenerFeriadosAnio(int anio) {
        List<DiaFeriadoDto> feriados = new ArrayList<>();
        feriados.add(DiaFeriadoDto.builder().fecha("01-01-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("02-04-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("03-04-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("01-05-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("21-05-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("28-06-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("16-07-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("15-08-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("17-09-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("18-09-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("19-09-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("11-10-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("01-11-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("31-10-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("08-12-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("25-12-2021").build());
        feriados.add(DiaFeriadoDto.builder().fecha("31-12-2021").build());

        return feriados;
    }
    public List<DiaFeriadoDto> obtenerFeriadosAnio2(int anio) {
        List<DiaFeriadoDto> feriados = new ArrayList<>();

        feriados.add(DiaFeriadoDto.builder().fecha("01-01-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("15-04-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("16-04-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("01-05-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("21-05-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("27-06-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("16-07-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("15-08-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("18-09-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("19-09-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("10-10-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("01-11-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("31-10-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("08-12-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("25-12-2022").build());
        feriados.add(DiaFeriadoDto.builder().fecha("31-12-2022").build());
        return feriados;
    }
}
