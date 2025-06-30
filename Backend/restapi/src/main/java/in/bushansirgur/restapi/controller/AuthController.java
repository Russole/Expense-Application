package in.bushansirgur.restapi.controller;

import in.bushansirgur.restapi.dto.ProfileDTO;
import in.bushansirgur.restapi.io.AuthRequest;
import in.bushansirgur.restapi.io.AuthResponse;
import in.bushansirgur.restapi.io.ProfileRequest;
import in.bushansirgur.restapi.io.ProfileResponse;
import in.bushansirgur.restapi.service.CustomUserDetailsService;
import in.bushansirgur.restapi.service.ProfileService;
import in.bushansirgur.restapi.service.TokenBlacklistService;
import in.bushansirgur.restapi.util.JwtTokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
// 結合了 @Controller 和 @ResponseBody，用於標註 Spring Boot RESTful API 的控制器類，讓所有方法的回傳值自動序列化為 JSON 格式（不需要再用 @ResponseBody
@Slf4j // 在類別內可以直接使用 log.info()、log.error()、log.debug() 來記錄日誌
@RequiredArgsConstructor
// 自動生成所有 final 欄位的建構子
// 適合依賴注入（Dependency Injection, DI），讓 Spring Boot 自動注入 final 欄位的 Bean
public class AuthController {

    private final ModelMapper modelMapper;
    private final ProfileService profileService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final TokenBlacklistService tokenBlacklistService;

    /**
     * API endpoint to register new user
     * @param profileRequest
     * @return profileResponse
     * */
    @ResponseStatus(HttpStatus.CREATED) // 設定 HTTP 回應狀態碼
    @PostMapping("/register") // 用於處理 HTTP POST 請求
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest) {
        log.info("API /register is called {}", profileRequest);
        ProfileDTO profileDto = mapToProfileDTO(profileRequest);
        log.info("profileRequest transfer to profileDto result {}", profileDto);
        profileDto = profileService.createProfile(profileDto);
        log.info("Printing the profile dto details {}", profileDto);
        return mapToProfileResponse(profileDto);
    }

    @PostMapping("/login")
    public AuthResponse authenticateProfile(@RequestBody AuthRequest authRequest) throws Exception {
        log.info("API /login is called {}", authRequest);
        authenticate(authRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return new AuthResponse(token, authRequest.getEmail());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/signout")
    public void signout(HttpServletRequest request) {
        String jwtToken = extractJwtTokenFromRequest(request);
        if(jwtToken != null) {
            tokenBlacklistService.addTokenToBlacklist(jwtToken);
        }
    }

    private String extractJwtTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void authenticate(AuthRequest authRequest) throws Exception {
        try {
            // sernamePasswordAuthenticationToken 物件被建立，封裝 email & password。
            // AuthenticationManager 會遍歷已註冊的 AuthenticationProvider 來進行驗證
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        }catch (DisabledException ex) {
            throw new Exception("Profile disabled");
        }catch (BadCredentialsException ex) {
            throw new Exception("Bad Credentials");
        }
    }

    /**
     * Mapper method to map values from profile request to profile dto
     * @param profileRequest
     * @return profileDto
     * */
    private ProfileDTO mapToProfileDTO(ProfileRequest profileRequest) {
        return modelMapper.map(profileRequest, ProfileDTO.class);
    }

    /**
     * Mapper method to map values from profile dto to profile response
     * @param profileDTO
     * @return profileResponse
     * */
    private ProfileResponse mapToProfileResponse(ProfileDTO profileRequest) {
        return modelMapper.map(profileRequest, ProfileResponse.class);
    }
}
