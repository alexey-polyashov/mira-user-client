package mira.users.ms.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mira.users.ms.config.JwtTokenUtil;
import mira.users.ms.dto.JwtRequestDTO;
import mira.users.ms.dto.JwtResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
@Api(value = "AuthController", tags = "Контролер для авторизации")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    private final RestTemplate restTemplate;

    @PostMapping("")
    public ResponseEntity<JwtResponseDTO> getAuthToken(@RequestBody JwtRequestDTO authRequest) throws JsonProcessingException {

        String apiUrl = jwtTokenUtil.getAuthorisationUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JwtRequestDTO> request =
                new HttpEntity<>(authRequest, headers);

        ResponseEntity<JwtResponseDTO> response = restTemplate.postForEntity(apiUrl, request, JwtResponseDTO.class);

        log.info("AuthController, createAuthToken, succes - {}", authRequest.getUsername());
        return response;
    }



}