package in.bushansirgur.restapi.controller;

import in.bushansirgur.restapi.io.ProfileRequest;
import in.bushansirgur.restapi.io.ProfileResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}", profileRequest);
        return mapToProfileResponse(profileRequest);
    }

    private ProfileResponse mapToProfileResponse(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileResponse.class);
    }
}
