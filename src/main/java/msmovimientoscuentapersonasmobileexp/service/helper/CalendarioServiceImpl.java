package msmovimientoscuentapersonasmobileexp.service.helper;

import msmovimientoscuentapersonasmobileexp.apis.FeriadosUtilClient;
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto;
import msmovimientoscuentapersonasmobileexp.service.CalendarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CalendarioServiceImpl implements CalendarioService {

    private String cantDiasHabilesHasta = "20";

    List<DiaFeriadoDto> feriados = null;

    @Autowired
    private FeriadosUtilClient feriadosUtilClient;

    @Override
    public List<String> proximosDiasHabiles(int cantDiasHabilesDesde, LocalDate fechaIngresada) {

        List<String> response;
        List<String> fakeList= new ArrayList<>();

        try {
            LocalDate fechaAEvaluar = fechaIngresada.plusDays(1);
            int anioActual = fechaAEvaluar.getYear();
            feriados = feriadosUtilClient.obtenerFeriadosAnio(anioActual);
            setearLocalDate();

            response = quitarDiaDesdeyHasta(cantDiasHabilesDesde, fechaAEvaluar,fakeList);
            fechaAEvaluar = LocalDate.parse(response.get(response.size()-1),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
             // boolean resp =  response.stream().anyMatch(dias -> (estaEnFeriados(fechaAEvaluar)));
            response = quitarDiaDesdeyHasta(Integer.parseInt(cantDiasHabilesHasta), fechaAEvaluar, fakeList);

        } catch (Exception npe) {
            throw new IllegalArgumentException("Error al obtener los proximos dias habiles."+npe);
        }
        /*
             boolean respuesta = feriados.stream().anyMatch(diaFeriadoDto ->
                fechaAEvaluar.compareTo(diaFeriadoDto.getLocal()) == 0);
        }*/
       return response;
    }

    @Override
    public List<String> proximosDiasHabiles(int cantDiasHabilesDesde, List<String> diasComuna, LocalDate fechaIngresada) {
        List<String> response;
        List<String> fakeList= new ArrayList<>();
        int anioActual;
        try {
            LocalDate fechaAEvaluar = fechaIngresada;
            anioActual = fechaAEvaluar.getYear();
            feriados = feriadosUtilClient.obtenerFeriadosAnio(anioActual);
            setearLocalDate();

            response = quitarDiaDesdeyHasta(cantDiasHabilesDesde, fechaAEvaluar, fakeList);
            fechaAEvaluar = LocalDate.parse(response.get(response.size()-1),DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            response = quitarDiaDesdeyHasta(Integer.parseInt(cantDiasHabilesHasta), fechaAEvaluar, diasComuna);

        } catch (Exception npe) {
            throw new IllegalArgumentException("Error al obtener los proximos dias habiles."+npe);
        }
        return response;
    }

    private List<String> quitarDiaDesdeyHasta(int dias, LocalDate fechaATratar, List diaComuna){
        List<String> response = new ArrayList<>();
        if (diaComuna.isEmpty()){
            diaComuna.add("LUN");
            diaComuna.add("MAR");
            diaComuna.add("MIE");
            diaComuna.add("JUE");
            diaComuna.add("VIE");
        }
        for(int i = 0; i< dias; i++) {
            boolean esFeriado = true;

            while (esFeriado) {
                if (estaEnFeriados(fechaATratar) || esFindeSemana(fechaATratar)|| !habilitadoParaDelivery(diaComuna, fechaATratar)) {
                    fechaATratar = fechaATratar.plusDays(1);
                } else {
                    esFeriado = false;
                }
            }
            String respuesta = generarRespuesta(fechaATratar.getDayOfMonth(),
                    fechaATratar.getMonthValue(),
                    fechaATratar.getYear());

            response.add(respuesta);
            fechaATratar = fechaATratar.plusDays(1);
        }
            return response;
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
