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
        def response = calendarioService.proximosDiasHabiles(diasDesde, diasComuna, fechaAEvaluar)

        then: "valido los campos"

        response != null
        response.get(0) == primero
        response.get(response.size()-1) == ultimo


        where:"Salida de la primera fecha y de la ultima agregada año 2021 y 2022"
         diasComuna| diasDesde | fechaAEvaluar                  || primero     || ultimo
         ['LUN']   | 2         | LocalDate.of(2021,06,21)       || "05/07/2021"|| "29/11/2021"
         ['MAR']   | 2         | LocalDate.of(2021,04,21)       || "27/04/2021"|| "07/09/2021"
         ['MIE']   | 2         | LocalDate.of(2021,04,7)        || "14/04/2021"|| "25/08/2021"
         ['JUE']   | 2         | LocalDate.of(2021,12,07)       || "16/12/2021"|| "28/04/2022"
         ['VIE']   | 2         | LocalDate.of(2021,9,16)        || "24/09/2021"|| "11/02/2022"
    ['LUN','MAR','MIE'] | 2    | LocalDate.of(2021,04,7)        || "12/04/2021"|| "25/05/2021"
    ['LUN','MIE','VIE'] | 2    | LocalDate.of(2021,9,13)        || "15/09/2021"|| "05/11/2021"
 ['LUN','MAR','MIE','VIE'] | 2 | LocalDate.of(2021,9,06)        || "08/09/2021"|| "15/10/2021"
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


