package acceptance.base;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import acceptance.util.Utilitarios;


@ContextConfiguration
public class TestAcceptanceBase {
	
	protected String APPLICATION_NAME = "";
	protected String MS_VERSION = Utilitarios.obtenerVariableEntorno("MS_VERSION");
	protected String URI_MS =  Utilitarios.obtenerVariableEntorno("HOST_MICROSERVICIO")+":"+Utilitarios.obtenerVariableEntorno("PORT")+"/"+Utilitarios.obtenerVariableEntorno("CONTEXT_PATH");
	protected String INGRESS_HOST = Utilitarios.obtenerVariableEntorno("HOST_MICROSERVICIO");
	protected RestTemplate restTemplate = new RestTemplate();

}
