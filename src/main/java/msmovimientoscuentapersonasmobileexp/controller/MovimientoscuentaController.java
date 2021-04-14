package msmovimientoscuentapersonasmobileexp.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import msmovimientoscuentapersonasmobileexp.service.MovimientoscuentaService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** 
 * 
 * Clase controlador de endpoints de Microservicio 
 * 
 *@author: Patricio Zúñiga
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class MovimientoscuentaController{

  private final MovimientoscuentaService service;

	@ApiOperation("Descripcion de recurso [ChangeMeControllerGet]")
  @GetMapping(value = "/<ChangeMeControllerGet>/{parameter}", produces = MediaType.APPLICATION_JSON_VALUE)
  //@PreAuthorize("hasAnyRole(@environment.getRequiredProperty('oauth.autorizacion.operaciones.recurso.operacion'))")
  public void changeMeControllerGet(@PathVariable("parameter") String changeParameter) {

  }

  @ApiOperation("Descripcion de recurso [ChangeMeControllerPost]")
  @PostMapping(value = "/<ChangeMeControllerPost>", produces = MediaType.APPLICATION_JSON_VALUE)
  //@PreAuthorize("hasAnyRole(@environment.getRequiredProperty('oauth.autorizacion.operaciones.recurso.operacion'))")
  public void changeMeControllerPost(@RequestBody Object object) {

  }
}
