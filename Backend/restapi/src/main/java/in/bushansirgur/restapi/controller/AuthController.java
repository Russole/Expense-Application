package in.bushansirgur.restapi.controller;

import in.bushansirgur.restapi.io.ProfileRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileRequest createProfile(@RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}", profileRequest);
        return profileRequest;
    }
}
