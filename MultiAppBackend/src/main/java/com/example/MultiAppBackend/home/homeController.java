package com.example.MultiAppBackend.home;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/home/")
@RequiredArgsConstructor
public class homeController {

    private final homeService homeService;

    @GetMapping("user/infos")
    public ResponseEntity<UserInfosDto> getUserInfos(Principal principal) {
        return ResponseEntity.ok(homeService.getUserInfos(principal));
    }

}
