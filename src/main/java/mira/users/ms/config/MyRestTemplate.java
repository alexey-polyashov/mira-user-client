package mira.users.ms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;

@Component
public class MyRestTemplate {
    @Autowired
    private RestTemplate restTemplate;

    private HttpHeaders createHeaders(String token){
        return new HttpHeaders(){{
            set("Authorization", token);
        }};
    }
    public <T> ResponseEntity<T> exchange(String authorisation, String url, HttpMethod method, Class<T> responseType, Object... uriVariables) throws RestClientException {
        try {
            HttpEntity<HttpHeaders> httpEntity = new HttpEntity<>(createHeaders(authorisation));
            return restTemplate.exchange(url, method, httpEntity, responseType);
        }catch(HttpClientErrorException.Forbidden e){
            throw new AccessDeniedException(e.getMessage());
        }
    }

    public <T> ResponseEntity<T> postForEntity(String authorisation, @Nullable Object body, String url, Class<T> responseType, Object... uriVariables) throws RestClientException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if(!authorisation.isEmpty()) {
            headers.set("Authorisation", authorisation);
        }

        HttpEntity<T> request =
                new HttpEntity<>((T)body, headers);

        return restTemplate.postForEntity(url, request, responseType);

    }

}
