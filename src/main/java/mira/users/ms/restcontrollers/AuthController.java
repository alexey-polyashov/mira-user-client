package mira.users.ms.restcontrollers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(
            value = "Авторизация",
            notes = "Администратор - admin-100, Обычный пользователь - user-100"
    )
    @ApiImplicitParams(value={
            @ApiImplicitParam(name="username", value = "admin", required = true),
            @ApiImplicitParam(name="password", value = "100", required = true)
    })
    public ResponseEntity<JwtResponseDTO> getAuthToken(@RequestBody JwtRequestDTO authRequest) {
        log.info("AuthController, getAuthToken, - {}", authRequest.getUsername());
        String apiUrl = jwtTokenUtil.getAuthorisationUrl();
        ResponseEntity<JwtResponseDTO> response = myRestTemplate.postForEntity("", authRequest, apiUrl, JwtResponseDTO.class);
        log.info("AuthController, getAuthToken, succes - {}", authRequest.getUsername());
        return response;
    }

}