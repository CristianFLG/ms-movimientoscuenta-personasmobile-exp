package acceptance.util;

import acceptance.base.TestAcceptanceBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class Login  extends TestAcceptanceBase{
	
	private RestTemplate restTemplate = new RestTemplate();
	
	private final String BASIC_AUTH = "Basic YXBwLW1vdmlsLXBlcnNvbmFzOnNlY3JldGlzaW1v";
	//TODO: Editar el valores de variable VERSION_LOGIN
	private final String VERSION_LOGIN= "version-login-editar";
	private final String LOGIN_URI = "operaciones/seguridad-y-acceso/ms-loginclientes-util/"+VERSION_LOGIN+"/oauth/token";
	private final String LOGIN_URL = (INGRESS_HOST+"/"+LOGIN_URI).trim();
	private static String loggedInToken;
	
	public String getToken(String rut, String password) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", BASIC_AUTH);
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
		requestBody.add("username", rut);
		requestBody.add("password", password);
		requestBody.add("grant_type", "password");
		HttpEntity<MultiValueMap<String, String>> formEntity = new HttpEntity<>(requestBody, headers);
		
		return this.restTemplate.exchange(LOGIN_URL, HttpMethod.POST, formEntity, AuthenticationResponse.class)
				.getBody().getAccessToken();
	}
	
	public String getLoggedInToken() {
		return loggedInToken;
	}
	
	public void setLoggedInToken(String tk) {
		loggedInToken = tk;
	}

	static class AuthenticationResponse {
		@JsonProperty(value = "access_token")
		private String accessToken;

		public String getAccessToken() {
			return accessToken;
		}
	}
}
