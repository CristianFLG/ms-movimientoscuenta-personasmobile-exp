package msmovimientoscuentapersonasmobileexp.configuration;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

;

/** 
 * 
 * Configuracion utilitaria transversal  
 * 
 *@author: Patricio Zúñiga
 */
@Configuration
public class UtilConfiguration {

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		return new DozerBeanMapper();
	}
}
