package msmovimientoscuentapersonasmobileexp.service.helper;


import lombok.extern.slf4j.Slf4j;
import msmovimientoscuentapersonasmobileexp.apis.FeriadosUtilClient;
import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto;
import msmovimientoscuentapersonasmobileexp.service.CalendarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CalendarioServiceImpl implements CalendarioService {

    @Autowired
    private FeriadosUtilClient feriadosUtilClient;

    private String cantDiasHabileshasta="20";

    List<DiaFeriadoDto> feriados = null;

    @Override
    public List<String> proximosDiasHabiles(int cantDiasHabilesDesde, LocalDate fechaIngresada) {
        List<String> response = new ArrayList<>();
        List<String> diasPermitidos = Arrays.asList("LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM");
        try {
            //Partimos del dia siguiente
            fechaIngresada = fechaIngresada.plusDays(1);
            //Obtenemos los primeros dias.
            List<String> primerosDias = obtenerDeltaDeDiasHabiles(fechaIngresada, cantDiasHabilesDesde, diasPermitidos);
            //Obtenemos el ultimo dia Desde. De aqui partiremos.
            LocalDate fechaDesde = LocalDate.parse(primerosDias.get(primerosDias.size()-1), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            //Obtenemos los prox 20 a partir del dia desde
            response = obtenerDeltaDeDiasHabiles(fechaDesde, Integer.valueOf(cantDiasHabileshasta), diasPermitidos);
        }catch (Exception npe) {
            throw new IllegalArgumentException("Error al recoger dias", npe);
        }
        return response;
    }

    @Override
    public List<String> proximosDiasHabiles(int cantDiasHabilesDesde, List<String> diasComuna, LocalDate fechaIngresada) {
        List<String> response = new ArrayList<>();
        List<String> diasPermitidos = Arrays.asList("LUN", "MAR", "MIE", "JUE", "VIE", "SAB", "DOM");
        try {
            //Partimos del dia siguiente
            fechaIngresada = fechaIngresada.plusDays(1);
            //Obtenemos los primeros dias.
            List<String> primerosDias = obtenerDeltaDeDiasHabiles(fechaIngresada, cantDiasHabilesDesde, diasPermitidos);
            //Obtenemos el ultimo dia Desde. De aqui partiremos.
            LocalDate fechaDesde = LocalDate.parse(primerosDias.get(primerosDias.size()-1), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            //Obtenemos los prox 20 a partir del dia desde
            response = obtenerDeltaDeDiasHabiles(fechaDesde, Integer.valueOf(cantDiasHabileshasta), diasComuna);
        } catch (Exception npe) {
            throw new IllegalArgumentException("Error al recoger dias", npe);
        }

        return response;
    }

    private List<String> obtenerDeltaDeDiasHabiles(LocalDate fechaAEvaluar, int deltaDias, List<String> diasComuna) {
        List<String> response = new ArrayList<>();
        List<DiaFeriadoDto> feriados = new ArrayList<>();
        feriados.addAll(feriadosUtilClient.obtenerFeriadosAnio(fechaAEvaluar.getYear()));
        feriados.addAll(feriadosUtilClient.obtenerFeriadosAnio2(fechaAEvaluar.getYear() + 1));
        int count = 1;
        while (count <= Integer.valueOf(deltaDias)) {

            //Verificamos que no sea feriado ni fin de semana
            if (estaEnFeriados(fechaAEvaluar, feriados) || esFindeSemana(fechaAEvaluar) || !habilitadoParaDelivery(diasComuna, fechaAEvaluar)) {
                fechaAEvaluar = fechaAEvaluar.plusDays(1);
            } else {
                response.add(generarRespuesta(fechaAEvaluar));
                fechaAEvaluar = fechaAEvaluar.plusDays(1);
                count++;
            }
        }

        return response;
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

    private boolean estaEnFeriados (LocalDate fechaAEvaluar, List<DiaFeriadoDto> feriados) {
        String fechaAComparar = fechaAEvaluar.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return feriados.stream().anyMatch(dia -> dia.getFecha().equals(fechaAComparar));
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

    private String generarRespuesta(LocalDate fecha) {
        String respuesta = "%s/%s/%s";
        String sDia = "" + fecha.getDayOfMonth();
        String sMes = "" + fecha.getMonthValue();
        String sAno = "" + fecha.getYear();
        if (fecha.getDayOfMonth() < 10) sDia = "0" + sDia;
        if (fecha.getMonthValue() < 10) sMes = "0" + sMes;
        return String.format(respuesta, sDia, sMes, sAno);
    }
}




