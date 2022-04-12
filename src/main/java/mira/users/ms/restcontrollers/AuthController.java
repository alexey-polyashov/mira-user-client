package mira.users.ms.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mira.users.ms.config.MyRestTemplate;
import mira.users.ms.config.JwtTokenUtil;
import mira.users.ms.dto.JwtRequestDTO;
import mira.users.ms.dto.JwtResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Api(value = "AuthController", tags = "Контролер для авторизации")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    private final MyRestTemplate myRestTemplate;

    @PostMapping("")
    public ResponseEntity<JwtResponseDTO> getAuthToken(@RequestBody JwtRequestDTO authRequest) {
        log.info("AuthController, getAuthToken, - {}", authRequest.getUsername());
        String apiUrl = jwtTokenUtil.getAuthorisationUrl();
        ResponseEntity<JwtResponseDTO> response = myRestTemplate.postForEntity("", authRequest, apiUrl, JwtResponseDTO.class);
        log.info("AuthController, getAuthToken, succes - {}", authRequest.getUsername());
        return response;
    }

}