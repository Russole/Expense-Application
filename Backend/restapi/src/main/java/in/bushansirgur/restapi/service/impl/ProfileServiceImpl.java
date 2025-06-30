package in.bushansirgur.restapi.service.impl;

import in.bushansirgur.restapi.dto.ProfileDTO;
import in.bushansirgur.restapi.entity.ProfileEntity;
import in.bushansirgur.restapi.exceptions.ItemExistException;
import in.bushansirgur.restapi.repository.ProfileRepository;
import in.bushansirgur.restapi.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service // 標註類別為服務層，它本質上與 @Component 相同，都是 Spring 管理的 Bean，但 @Service 主要是用來語義化，表示該類別負責業務邏輯處理。
@Slf4j
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    // Spring Data JPA 會自動為 @Repository 介面提供 Bean 實作，不需要標註 @Repository
    // Spring Boot 啟動時，會根據 JpaRepository 介面自動產生對應的 Bean
    private final ModelMapper modelMapper;// RestapiApplication 執行類別註冊為 Bean
    private final PasswordEncoder encoder;// WebSecurityConfig 類別註冊為 Bean

    /**
     * It will save the user details to database
     * @param profileDTO
     * @return profileDto
     * */
    @Override
    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        if(profileRepository.existsByEmail(profileDTO.getEmail())) {
            throw new ItemExistException("Profile already exists"+profileDTO.getEmail());
        }
        profileDTO.setPassword(encoder.encode(profileDTO.getPassword()));
        ProfileEntity profileEntity = mapToProfileEntity(profileDTO);
        profileEntity.setProfileId(UUID.randomUUID().toString());
        //TODO: check for the email exists
        profileEntity = profileRepository.save(profileEntity); // id, createdAt, updatedAt ，這 3 個欄位在 repo save()的同時，也會給值
        log.info("Printing the profile entity details {}", profileEntity);
        return mapToProfileDTO(profileEntity);
    }

    /**
     * Mapper method to map values from profile entity to profile dto
     * @param profileEntity
     * @return profileDto
     * */
    private ProfileDTO mapToProfileDTO(ProfileEntity profileEntity) {
        return modelMapper.map(profileEntity, ProfileDTO.class);
    }

    /**
     * Mapper method to map values from profile dto to profile entity
     * @param profileDTO
     * @return profileEntity
     * */
    private ProfileEntity mapToProfileEntity(ProfileDTO profileDTO) {
        return modelMapper.map(profileDTO, ProfileEntity.class);
    }
}
