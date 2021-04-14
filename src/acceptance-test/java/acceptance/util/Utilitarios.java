package acceptance.util;

import gherkin.deps.com.google.gson.Gson;

import java.util.Optional;

/**
 * Clase con utilitarios para obtener variables de ambiente
 *
 */
public class Utilitarios {

/**
 * ambiente en el cual se esta ejecutando
 */
public static final String ambiente = Optional.ofNullable(System.getenv("TEST_ACCEPTANCE_FEATURES")).orElse("");
	
   public static String createJsonFromObject(Object object, Class<?> clase){
        Gson sgon = new Gson();
        return sgon.toJson(clase.cast(object));
    }
	
   public static final String obtenerVariableEntorno(String var) {
    	return Optional.ofNullable(System.getenv(var)).orElse("");
   }
   
}
