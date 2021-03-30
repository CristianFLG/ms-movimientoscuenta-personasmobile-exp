package msmovimientoscuentapersonasmobileexp.service

import msmovimientoscuentapersonasmobileexp.repository.DiaFeriadoDto
import msmovimientoscuentapersonasmobileexp.service.helper.CalendarioServiceImpl
import spock.lang.Specification


/** 
 * 
 *Test Service 
 * 
 *@author: Patricio Zúñiga
 */
class MovimientoscuentaServiceSpec extends Specification {

  private MovimientoscuentaService movimientoscuentaService

  void setup() {
    this.movimientoscuentaService = new MovimientoscuentaServiceImpl()
  }

}
