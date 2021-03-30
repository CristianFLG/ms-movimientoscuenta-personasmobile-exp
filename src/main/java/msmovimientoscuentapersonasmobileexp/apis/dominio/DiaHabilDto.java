package msmovimientoscuentapersonasmobileexp.apis.dominio;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * Objeto de respuesta para servicios de Gestion Horaria
 *
 * @author BCI
 *
 * <p>
 * <b>Todos los derechos reservados por Banco de Crédito e Inversiones.</b>
 * </p>
 */
@Getter
@Setter
public class DiaHabilDto {
    /**
     * Especifica si la hora actual es valida entre los rangos especificados.
     */
    @ApiModelProperty("Especifica si la hora actual es valida entre los rangos especificados.")
    private boolean esHorarioHabil;

    /**
     * Especifica el dia habil mas cercano.
     */
    @ApiModelProperty("Especifica el dia habil mas cercano.")
    @Getter
    private String diaHabil;

    /**
     * Especifica el hora habil dentro del rango especificado.
     */
    @ApiModelProperty("Especifica la hora habil.")
    private String horaHabil;
    /**
     * Especifica el proximo dia habil mas cercano.
     */
    @ApiModelProperty("Especifica el proximo dia habil mas cercano.")
    private String proximoDiaHabil;

    /**
     * Especifica si la hora actual.
     */
    @ApiModelProperty("Especifica si la hora actual.")
    private String fechaActual;

    public DiaHabilDto() {
    }

    @Builder
    public DiaHabilDto(boolean esHorarioHabil, String diaHabil, String horaHabil, String proximoDiaHabil, String fechaActual) {
        this.esHorarioHabil = esHorarioHabil;
        this.diaHabil = diaHabil;
        this.horaHabil = horaHabil;
        this.proximoDiaHabil = proximoDiaHabil;
        this.fechaActual = fechaActual;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DiaHabilDto").append('[');
        sb.append(", esHorarioHabil[").append(esHorarioHabil).append(']');
        sb.append(", diaHabil[").append(diaHabil).append(']');
        sb.append(", horaHabil[").append(horaHabil).append(']');
        sb.append(", proximoDiaHabil[").append(proximoDiaHabil).append(']');
        sb.append(", fechaActual[").append(fechaActual).append(']');
        sb.append(']');
        return sb.toString();
    }
}