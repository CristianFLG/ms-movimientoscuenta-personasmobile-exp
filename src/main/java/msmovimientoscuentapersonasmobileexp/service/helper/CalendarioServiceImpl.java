package msmovimientoscuentapersonasmobileexp.service.helper;

import msmovimientoscuentapersonasmobileexp.apis.FeriadosUtilClient;
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto;
import msmovimientoscuentapersonasmobileexp.service.CalendarioService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarioServiceImpl implements CalendarioService {

    private String cantDiasHabileshasta = "20";

    List<DiaFeriadoDto> feriados = null;

    @Autowired
    private FeriadosUtilClient feriadosUtilClient;

    @Override
    public List<String> proximosDiasHabiles(int cantDiasHabilesDesde, LocalDate fechaIngresada) {
        List<String> response = new ArrayList<>();
        int anioActual;

        try {
            //LocalDate fechaAEvaluar = LocalDate.now(); //2021-03-28
            LocalDate fechaAEvaluar = fechaIngresada;
            anioActual = fechaAEvaluar.getYear();       //2021
            feriados = feriadosUtilClient.obtenerFeriadosAnio(anioActual);
            setearLocalDate();

            int cantDiasHabilesAEvaluar = Integer.parseInt(cantDiasHabileshasta) + cantDiasHabilesDesde;
            for (int i = 0; i < cantDiasHabilesAEvaluar; i++) {
                boolean esFeriado = true;

                while (esFeriado) {
                    if (estaEnFeriados(fechaAEvaluar) || esFindeSemana(fechaAEvaluar)) {
                        fechaAEvaluar = fechaAEvaluar.plusDays(1);
                    } else {

                        esFeriado = false;
                    }
                }

                String respuesta = generarRespuesta(fechaAEvaluar.getDayOfMonth(),
                        fechaAEvaluar.getMonthValue(),
                        fechaAEvaluar.getYear());

                response.add(respuesta);
                fechaAEvaluar = fechaAEvaluar.plusDays(1);
            }


        } catch (Exception npe) {
            throw new IllegalArgumentException("Error al obtener los proximos dias habiles."+npe);
        }
        response = limpiarDiasHabilesDesde(response,cantDiasHabilesDesde,fechaIngresada);

        return response;
    }


    @Override
    public List<String> proximosDiasHabiles(int cantDiasHabilesDesde, List<String> diasComuna, List<DiaFeriadoDto> _feriados) {
        List<String> response = new ArrayList<>();
        int anioActual;

        try {
            LocalDate fechaActual = LocalDate.now();

            feriados = _feriados;
            setearLocalDate();

            LocalDate fechaAEvaluar = fechaActual.plusDays(cantDiasHabilesDesde);

            for (int i=0; i<Integer.parseInt(cantDiasHabileshasta); i++){
                boolean esFeriado = true;

                while ( esFeriado ) {
                    if (estaEnFeriados(fechaAEvaluar) || esFindeSemana(fechaAEvaluar) || !habilitadoParaDelivery(diasComuna, fechaAEvaluar) ) {
                        fechaAEvaluar = fechaAEvaluar.plusDays(1);
                    } else {
                        esFeriado = false;
                    }
                }

                String respuesta = generarRespuesta(fechaAEvaluar.getDayOfMonth(),
                        fechaAEvaluar.getMonthValue(),
                        fechaAEvaluar.getYear());

                response.add(respuesta);
                fechaAEvaluar = fechaAEvaluar.plusDays(1);
            }

        } catch (Exception npe) {
            throw new IllegalArgumentException("Error al obtener los proximos dias habiles."+npe);
        }

        return response;
    }

    private List<String>limpiarDiasHabilesDesde(List limpiar, int cantDiasHabilesDesde,LocalDate fechaAEvaluar){

        int cantDiasHabilesAEvaluar = Integer.parseInt(cantDiasHabileshasta) + cantDiasHabilesDesde;

        //se elimina la fecha actual si es dia habil
        if(fechaAEvaluar.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")).equals(limpiar.get(0))){
            limpiar.remove(0); // elimino fecha de pedido de targeta del arreglo
        }
            if(limpiar.size()==cantDiasHabilesAEvaluar) { // si parte por un dia feeriado seran 24 dias por lo que se elimina el utlimo valor
                limpiar.remove(cantDiasHabilesAEvaluar-1);
            }
            int i = 0;
            while (i < cantDiasHabilesDesde - 1) { //eliminamos los 3 dias del pedido de targeta.
                limpiar.remove(0);
                i++;
            }

        return limpiar;
    }

    private void setearLocalDate() {
        feriados.stream().map(diaFeriado -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate date = LocalDate.parse(diaFeriado.getFecha(), formatter);
            diaFeriado.setLocal(date);
            return diaFeriado;
        }).collect(Collectors.toList());
    }


    private boolean estaEnFeriados (LocalDate fechaAEvaluar) {
        boolean respuesta = feriados.stream().anyMatch(diaFeriadoDto ->
                fechaAEvaluar.compareTo(diaFeriadoDto.getLocal()) == 0);

        //revisa mes dia y año
        if (fechaAEvaluar.getMonthValue() == 12 && fechaAEvaluar.getDayOfMonth() == 31 && feriados.get(1).getLocal().getYear() != fechaAEvaluar.getYear()) {
            feriados = feriadosUtilClient.obtenerFeriadosAnio2(fechaAEvaluar.getYear()+1);
            setearLocalDate();
        }
        //revisa año en el que esta
        if (feriados.get(1).getLocal().getYear() != fechaAEvaluar.getYear()){
            feriados = feriadosUtilClient.obtenerFeriadosAnio2(fechaAEvaluar.getYear()+1);
            setearLocalDate();
        }
        return respuesta;
    }


    private boolean esFindeSemana(LocalDate fechaAEvaluar) {
        boolean esFinDeSemana = false;
        switch (fechaAEvaluar.getDayOfWeek()){
            case SATURDAY:
                esFinDeSemana = true;
                break;
            case SUNDAY:
                esFinDeSemana = true;
                break;
            default:
                break;
        }
        return esFinDeSemana;
    }

    private String generarRespuesta(int dia, int mes, int ano) {
        String respuesta = "%s/%s/%s";
        String sDia = ""+dia;
        String sMes = ""+mes;
        String sAno = ""+ano;

        if (dia < 10) sDia = "0"+sDia;
        if (mes < 10) sMes = "0"+sMes;

        return String.format(respuesta, sDia, sMes, sAno);
    }
    private boolean habilitadoParaDelivery(List<String> diasComunas, LocalDate fechaAEvaluar) {
        boolean habilitado = false;
        switch (fechaAEvaluar.getDayOfWeek()){
            case MONDAY:
                habilitado = diasComunas.contains("LUN");
                break;
            case TUESDAY:
                habilitado = diasComunas.contains("MAR");
                break;
            case WEDNESDAY:
                habilitado = diasComunas.contains("MIE");
                break;
            case THURSDAY:
                habilitado = diasComunas.contains("JUE");
                break;
            case FRIDAY:
                habilitado = diasComunas.contains("VIE");
                break;
            case SATURDAY:
                habilitado = diasComunas.contains("SAB");
                break;
            case SUNDAY:
                habilitado = diasComunas.contains("DOM");
                break;
            default:
                break;
        }
        return habilitado;
    }
}
