package mira.users.ms.restcontrollers;

import lombok.RequiredArgsConstructor;
import mira.users.ms.config.MyRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserController {

    @Value("${mira.userservice.url}")
    private String miraUserServiceUrl;

    private final MyRestTemplate myRestTemplate;

    private String apiUrl;

    @PostConstruct
    private void init(){
        apiUrl = miraUserServiceUrl + "/api/v1";
    }


    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> getUsers(@RequestHeader("Authorization") String authorisation){
        return myRestTemplate.exchange(authorisation, apiUrl + "/users", HttpMethod.GET, String.class);
    }

}
