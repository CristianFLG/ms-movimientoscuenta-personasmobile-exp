package msmovimientoscuentapersonasmobileexp.service

import msmovimientoscuentapersonasmobileexp.apis.FeriadosUtilClient
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto
import msmovimientoscuentapersonasmobileexp.service.CalendarioService
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
    @Unroll
    def "Quiero obtener los prox 20 dias habiles a partir de 4 dias" () {


        when: "invoco al servicio"

        feriadosUtilClient.obtenerFeriadosAnio(_) >> obtenerFeriadosAnio()
        feriadosUtilClient.obtenerFeriadosAnio2(_) >> obtenerFeriadosAnio2()
        def response = calendarioService.proximosDiasHabiles(diasDesde, fechaAEvaluar)

        then: "valido los campos"

        response != null
        response.get(0) == primero
        response.get(response.size()-1) == ultimo


        where:"Salida de la primera fecha y de la ultima agregada año 2021 y 2022"
        diasDesde | fechaAEvaluar                  || primero     || ultimo
        4         | LocalDate.of(2021,01,04)       || "08/01/2021"|| "04/02/2021"  // "Desde lunes 4 de enero,despues de año nuevo"
        4         | LocalDate.of(2021,03,26)       || "01/04/2021"|| "29/04/2021"  // "Desde viernes 26 de marzo, pasa por el 2 y 3 de abril 'semana santa ' "
        4         | LocalDate.of(2021,04,25)       || "29/04/2021"|| "27/05/2021"  // "Desde Domingo 25 de abril, pasa por el sabado 1 'dia del trabajo' y viernes 21 'glorias navales' mes de mayo"
        4         | LocalDate.of(2021,05,06)       || "12/05/2021"|| "09/06/2021"  // "Desde jueves 6 de mayo, pasa por el 21 de mayo 'glorias navales' "
        4         | LocalDate.of(2021,06,28)       || "02/07/2021"|| "30/07/2021"  // "Desde lunes 28 de junio 'feriado san pedro y pablo' pasando por el 16 de julio 'virgen del carmen' "
        4         | LocalDate.of(2021,9,01)        || "07/09/2021"|| "05/10/2021"  // "Desde miercoles 1 de septiembra, se pasa por la 'semana de fiestas patrias' 17,18 y 19"
        4         | LocalDate.of(2021,9,18)        || "23/09/2021"|| "21/10/2021"  // "Desde sabado 18 de septiembre, pasando por el 11 de octubre 'dia de la raza' "
        4         | LocalDate.of(2021,10,04)       || "08/10/2021"|| "08/11/2021"  // "Desde lunes 4 de octubre, pasando el lunes 11 'dia de la raza' y lunes 1 de noviembre 'dia de todos los santos' "
        4         | LocalDate.of(2021,12,01)       || "07/12/2021"|| "05/01/2022"  // "Desde miercoles 1 de diciembre pasa por todos los feriados de diciembre incuido el 31 'feriado corporativo'. Cambio de año"
        4         | LocalDate.of(2021,12,07)       || "14/12/2021"|| "11/01/2022"  // "Desde martes 7 de diciembre, pasa por el miercoles 8 'inmacualada concepcion', ademas pasa por el 25 de diciembre y 31, este ultimo conciderado 'feriado corporativo'. Cambio de año"
    }

    @Unroll
    def "Quiero obtener los prox 20 dias habiles a partir de 2 dias con comunas" () {

        when: "invocion de servicios"

        feriadosUtilClient.obtenerFeriadosAnio(2021) >> obtenerFeriadosAnio()
        feriadosUtilClient.obtenerFeriadosAnio2(2022) >> obtenerFeriadosAnio2()
        def response = calendarioService.proximosDiasHabiles(diasDesde, diasComuna, fechaAEvaluar)

        then: "validacion de campos"

        response != null
        response.get(0) == primero
        response.get(response.size() - 1) == ultimo


        where: "Salida de la primera fecha y de la ultima agregada año 2021 y 2022"
        diasComuna            | diasDesde | fechaAEvaluar              || primero      || ultimo
        ['MAR', 'JUE']        | 2         | LocalDate.of(2021, 01, 04) || "07/01/2021" || "16/03/2021" //"Desde lunes  4 de enero, se habilitan los dias martes y jueves."
        ['MIE', 'VIE']        | 2         | LocalDate.of(2021, 02, 9)  || "12/02/2021" || "23/04/2021" // "Desde martes 9 de febrero, se habilitan los dias miercoles y viernes, pasa por el viernes 2 de abril 'viernes santo' "
        ['MIE']               | 2         | LocalDate.of(2021, 04, 07) || "14/04/2021" || "25/08/2021" // "Desde miercoles 7 de abril, se habilitan los dias miercoles. Pasa atravez de los dias 1 y 21 de mayo 'dia del trabajo y glorias navales' y julio 16 'virgen del carmen'"
        ['LUN', 'MAR']        | 2         | LocalDate.of(2021, 05, 05) || "10/05/2021" || "19/07/2021" // "Desde miercoles 5 de mayo, se habilitan los dias lunes y martes. Pasa atravez de los dias 21 de mayo'glorias navales' y 28 de junio 'san pedro y pablo' y 16 de julio 'virgen del carmen'"
        ['LUN']               | 2         | LocalDate.of(2021, 06, 21) || "05/07/2021" || "29/11/2021" // "Desde lunes 21 de junio, se habilita el dia lunes. pasa atravez del lunes 28 de junio 'san pedro y pablo', 16 de julio 'virgen del carmen', septiembre 'fiestas patrias' , octubre 'dia de la raza' y noviembre lunes 1 'dia de todos los santos'"
        ['VIE']               | 2         | LocalDate.of(2021, 9, 16)  || "24/09/2021" || "11/02/2022" // "Desde jueves 16 de septiembre previo a fiestas patrias, se habilita el dia viernes. viernes 17 al 19 de septiembre 'fiestas patrias', octubre 11 y 31 'dia de los santos', diciembre 8, 25 , 31 'inmaculada concepcion,navidad y feriado bancario'. cambio de año 1ro de enero feriado"
        ['LUN', 'MAR', 'MIE'] | 2         | LocalDate.of(2021, 12, 22) || "27/12/2021" || "08/02/2022" // "Desde domingo 22 de diciembre, se habilitan lunes, martes y miercoles. se pasa por 25 y 31 de diciembre 'navidad y feriado bancario', cambio de año 1ro de enero feriado"
        ['LUN', 'MIE', 'VIE'] | 2         | LocalDate.of(2021, 12, 13) || "15/12/2021" || "31/01/2022" // "Desde lunes 13 de diciembre, se hablitan lunes,miercoles y viernes. se pasa por el 25 y 31 de diciembre'navidad y feriado bancario'. cambio de año 1ro de enero feriado"
        ['JUE']               | 2         | LocalDate.of(2021, 12, 07) || "16/12/2021" || "28/04/2022" // "Desde martes 7 de diciembre, se habilita el dia jueves.se pasa por el 8,25 y 31 de diciembre 'la concepcion, navidad y feriado bancario'. cambio de año 1ro de enero feriado, 15 y 16 de abril 'viernes y sabado santo'"
        ['LUN','MAR','MIE','VIE'] | 2     | LocalDate.of(2021, 12,07)  || "10/12/2021" || "14/01/2022" // "Desde martes 7 de diciembre, se habilita el lunes, martes, miercoles y viernes. se pasa por el 8,25 y 31 de diciembre 'la concepcion, navidad y feriado bancario'. cambio de año 1ro de enero"
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


