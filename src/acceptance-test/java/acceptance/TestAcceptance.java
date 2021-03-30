package acceptance;
import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;

/**
 * Utilizar @CucumberOptions (descomentar) solo para realizar pruebas locales con Junit.
 * Asegurar que esta notacion no quede habilitada para ejecucion del pipeline, debido a que sobrescribe
 * las configuracion del test.gradle, donde el package de test ejecutara es variable de acuerdo a la varible de ambiente PIPE.
 *
 *
 * @author BCI
 *
 */
@RunWith(Cucumber.class)
//@CucumberOptions(features = "src/acceptance-test/resources", tags = "~@ignore")
public class TestAcceptance {

}
