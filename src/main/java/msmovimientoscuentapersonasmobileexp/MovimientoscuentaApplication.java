package msmovimientoscuentapersonasmobileexp;

import org.apache.tomcat.util.net.SSLUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;


/** 
 * 
 * Clase Main de proyecto Movimientoscuenta 
 * 
 *@author: Patricio Zúñiga
 */

@SpringBootApplication
public class MovimientoscuentaApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MovimientoscuentaApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {

	}
}
