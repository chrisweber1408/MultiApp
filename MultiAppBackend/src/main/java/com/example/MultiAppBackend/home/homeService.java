package com.example.MultiAppBackend.home;

import com.example.MultiAppBackend.homizer.homizerItem.HomizerItemService;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class homeService {

    private final HomizerItemService homizerItemService;
    private final HomizerStorageService homizerStorageService;
    
    public UserInfosDto getUserInfos(Principal principal) {
        UserInfosDto userInfosDto = new UserInfosDto();
        userInfosDto.setEmail(principal.getName());
        userInfosDto.setItemCount(
                homizerItemService.getAllHomizerItemsFromUser(principal).size()
        );
        userInfosDto.setStorageCount(
                homizerStorageService.getAllHomizerStoragesfromUser(principal).size()
        );
        return userInfosDto;
    }
    
}
