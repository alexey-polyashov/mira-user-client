package mira.users.ms.restcontrollers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping()
    public String getUsers(){
        return restTemplate.getForObject(apiUrl + "/users", String.class);
    }

}
