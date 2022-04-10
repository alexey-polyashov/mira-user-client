package mira.users.ms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtTokenUtil {

    @Value("${mira.userservice.url}")
    private String miraUserServiceUrl;

    public String getAuthorisationUrl(){
        return miraUserServiceUrl + "/api/v1/auth";
    }

}
