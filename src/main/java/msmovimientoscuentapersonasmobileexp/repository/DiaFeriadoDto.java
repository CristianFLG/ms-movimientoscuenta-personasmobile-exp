package msmovimientoscuentapersonasmobileexp.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter @Setter
public class DiaFeriadoDto {

    /**
     * Indica si es dia habil
     */
    @JsonProperty("esFeriado")
    private boolean festivo;

    /**
     * Fecha en formato dd/M/yyyy
     */
    @JsonProperty("feriadoObservado")
    private String fecha;

    /**
     * Descripcion del dia festivo
     */
    private String descripcion;

    /**
     * Pais del dia festivo (chile, estados unidos (miami))
     */
    private String pais;

    /**
     * Fecha en localDate
     */
    @JsonIgnore
    private LocalDate local;

}
