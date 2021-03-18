package msmovimientoscuentapersonasmobileexp.service;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/** 
 * 
 *  
 * 
 *@author: Patricio Zúñiga
 */


@Slf4j
@Service
@RequiredArgsConstructor
public class MovimientoscuentaServiceImpl implements MovimientoscuentaService,InitializingBean {

	private final DozerBeanMapper dozerBeanMapper;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		BeanMappingBuilder builder = new BeanMappingBuilder() {
			protected void configure() {
				mapping(Object.class, Object.class, TypeMappingOptions.oneWay());
			}
		};
		dozerBeanMapper.addMapping(builder);
	}

}
