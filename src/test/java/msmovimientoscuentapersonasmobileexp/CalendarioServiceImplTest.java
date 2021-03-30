package msmovimientoscuentapersonasmobileexp;


import msmovimientoscuentapersonasmobileexp.apis.dominio.DiaHabilDto;
import msmovimientoscuentapersonasmobileexp.service.helper.CalendarioServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalendarioServiceImplTest {

    List<DiaHabilDto>  habiles;
    CalendarioServiceImpl calendarioService;

    @Before
    public void setUp(){
        habiles = new ArrayList<>();
    }

    @Test
    public void calendarioDiasHabiles(){
        when(calendarioService.proximosDiasHabiles(4, LocalDate.now()));
        assertThat(calendarioService.proximosDiasHabiles(4,LocalDate.now()).isEmpty());

    }


}