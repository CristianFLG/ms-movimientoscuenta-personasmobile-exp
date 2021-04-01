package msmovimientoscuentapersonasmobileexp.service

import msmovimientoscuentapersonasmobileexp.apis.FeriadosUtilClient
import msmovimientoscuentapersonasmobileexp.apis.FeriadosUtilClientImpl
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto
import msmovimientoscuentapersonasmobileexp.service.helper.CalendarioServiceImpl
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

/** 
 * 
 *Test Service 
 * 
 *@author: Patricio Zúñiga
 */

class CalendarioServiceSpec extends Specification {


    CalendarioService calendarioService
    FeriadosUtilClient feriadosUtilClient = Mock()
    void setup() {
        calendarioService = new CalendarioServiceImpl()
        calendarioService.feriadosUtilClient = feriadosUtilClient
    }
    def "Quiero obtener los prox 20 dias habiles a partir de 4 dias a contar de este" () {

        given: "la cantidad de dias desde y haste"

        when: "invoco al servicio"
        feriadosUtilClient.obtenerFeriadosAnio(_) >> obtenerFeriadosAnio()
        feriadosUtilClient.obtenerFeriadosAnio2(_) >> obtenerFeriadosAnio2()
        def response = calendarioService.proximosDiasHabiles(diasDesde, fechaAEvaluar)

        then: "valido los campos"
        response != null
        response.get(0) == primero
        response.get(response.size()-1) == ultimo

        where:"Salida de la primera fecha y de la ultima agregada"
        diasDesde | fechaAEvaluar                  || primero     || ultimo
        4         | LocalDate.of(2022,8,9)       || "16/08/2022"|| "12/09/2022"

    }

    List<DiaFeriadoDto> obtenerFeriadosAnio() {
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
    List<DiaFeriadoDto> obtenerFeriadosAnio2() {
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


