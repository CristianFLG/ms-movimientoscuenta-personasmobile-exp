package msmovimientoscuentapersonasmobileexp.controller;

import msmovimientoscuentapersonasmobileexp.service.CalendarioService;

import msmovimientoscuentapersonasmobileexp.util.LogOnOffUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CalendarioController {


    private int cantDiasDesde=4;

    private LocalDate fecha=null;

    private final Logger logger = LoggerFactory.getLogger(CalendarioController.class);

    @Autowired
    private CalendarioService calendarioService;

    @GetMapping(value = "/calendario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getCalendario() {
        List<String> respuesta;
        try {
                fecha = LocalDate.of(2022,04,8);
            respuesta = calendarioService.proximosDiasHabiles(cantDiasDesde,fecha);
        } catch (Exception e) {
            logger.error(String.format("[%s][%s] Obtener calendario", LogOnOffUtils.getMethodName(), e.getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(respuesta);
    }
}

