package mira.users.ms.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${mira.userservice.url}")
    private String miraUserServiceUrl;

    private final RestTemplate restTemplate;

    private String apiUrl;

    @PostConstruct
    private void init(){
        apiUrl = miraUserServiceUrl + "/api/v1";
    }

    private HttpHeaders createHeaders(String token){
        return new HttpHeaders(){{
            set("Authorization", token);
        }};
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getUsers(@RequestHeader("Authorization") String authorisation){
        ResponseEntity<String> responce;
        try {
            responce = restTemplate.exchange(apiUrl + "/users", HttpMethod.GET, new HttpEntity<>(createHeaders(authorisation)), String.class);
        }catch(HttpClientErrorException.Forbidden e){
            throw new AccessDeniedException(e.getMessage());
        }
        return responce;
    }

}
