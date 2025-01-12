package in.bushansirgur.restapi.service;

import in.bushansirgur.restapi.dto.ProfileDTO;

public interface ProfileService {

    /**
     * It will save the user details to database
     * @param profileDTO
     * @return profileDto
     * */
    ProfileDTO createProfile(ProfileDTO profileDTO);
}
