package com.example.MultiAppBackend.homizer;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.MultiAppBackend.homizer.homizerItem.HomizerItemDto;
import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorageDto;
import com.example.MultiAppBackend.user.LoginResponse;
import com.example.MultiAppBackend.user.MyUser;
import com.example.MultiAppBackend.user.UserLoginDto;
import com.example.MultiAppBackend.user.UserRegisterDto;
import java.util.Arrays;
import java.util.Objects;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integrationTest")
class HomizerIntegrationTest {

  @Autowired TestRestTemplate testRestTemplate = new TestRestTemplate();

  @LocalServerPort private int port;

  private String jwtToken;

  static PostgreSQLContainer<?> postgres =
      new PostgreSQLContainer<>("postgres:15")
          .withDatabaseName("testdb")
          .withUsername("test")
          .withPassword("test");

  @BeforeAll
  static void startContainer() {
    postgres.start();
  }

  @DynamicPropertySource
  static void configureProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgres::getJdbcUrl);
    registry.add("spring.datasource.username", postgres::getUsername);
    registry.add("spring.datasource.password", postgres::getPassword);
  }

  @Test
  void fullHomizerWorkflowTest() {

    // Register
    UserRegisterDto userRegisterDto = new UserRegisterDto();
    userRegisterDto.setEmail("test@mail.com");
    userRegisterDto.setPassword("123");
    userRegisterDto.setPasswordRepeat("123");

    ResponseEntity<MyUser> registerResponse =
        testRestTemplate.postForEntity("/api/user/register", userRegisterDto, MyUser.class);
    assertThat(registerResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    // Login
    UserLoginDto userLoginDto = new UserLoginDto();
    userLoginDto.setEmail("test@mail.com");
    userLoginDto.setPassword("123");

    ResponseEntity<LoginResponse> loginResponse =
        testRestTemplate.postForEntity("/api/user/login", userLoginDto, LoginResponse.class);

    assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(Objects.requireNonNull(loginResponse.getBody()).getToken()).isNotBlank();

    String token = loginResponse.getBody().getToken();

    // New storage1
    HomizerStorageDto homizerStorageDto1 = new HomizerStorageDto();
    homizerStorageDto1.setName("testStorage1");
    homizerStorageDto1.setDescription("testDescription1");
    homizerStorageDto1.setImage("testImage1");
    ResponseEntity<Void> responseNewStorage1 =
        testRestTemplate.exchange(
            "/api/homizer/storage/",
            HttpMethod.POST,
            new HttpEntity<>(homizerStorageDto1, createHeader(token)),
            Void.class);

    assertThat(responseNewStorage1.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // New storage2
    HomizerStorageDto homizerStorageDto2 = new HomizerStorageDto();
    homizerStorageDto2.setName("testStorage2");
    homizerStorageDto2.setDescription("testDescription2");
    homizerStorageDto2.setImage("testImage2");
    ResponseEntity<Void> responseNewStorage2 =
        testRestTemplate.exchange(
            "/api/homizer/storage/",
            HttpMethod.POST,
            new HttpEntity<>(homizerStorageDto2, createHeader(token)),
            Void.class);

    assertThat(responseNewStorage2.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // Get storages
    ResponseEntity<HomizerStorageDto[]> responseStorages =
        testRestTemplate.exchange(
            "/api/homizer/storage/",
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            HomizerStorageDto[].class);

    assertThat(responseStorages.getBody()).hasSize(2);

    String homizerStorageId = Arrays.stream(responseStorages.getBody()).findFirst().get().getId();

    // Get storage
    ResponseEntity<HomizerStorageDto> responseStorage =
        testRestTemplate.exchange(
            "/api/homizer/storage/" + homizerStorageId,
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            HomizerStorageDto.class);

    assertThat(Objects.requireNonNull(responseStorage.getBody()).getName())
        .isEqualTo("testStorage1");

    // New item1
    HomizerItemDto homizerItemDto1 = new HomizerItemDto();
    homizerItemDto1.setName("testItem1");
    homizerItemDto1.setDescription("testDescription1");
    homizerItemDto1.setNumber(1);
    homizerItemDto1.setImage("testImage1");

    ResponseEntity<Void> responseNewItem1 =
        testRestTemplate.exchange(
            "/api/homizer/item/",
            HttpMethod.POST,
            new HttpEntity<>(homizerItemDto1, createHeader(token)),
            Void.class);

    assertThat(responseNewItem1.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // New item2
    HomizerItemDto homizerItemDto2 = new HomizerItemDto();
    homizerItemDto2.setName("testItem2");
    homizerItemDto2.setDescription("testDescription2");
    homizerItemDto2.setNumber(1);
    homizerItemDto2.setImage("testImage2");

    ResponseEntity<Void> responseNewItem2 =
        testRestTemplate.exchange(
            "/api/homizer/item/",
            HttpMethod.POST,
            new HttpEntity<>(homizerItemDto2, createHeader(token)),
            Void.class);

    assertThat(responseNewItem2.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // Get items
    ResponseEntity<HomizerItemDto[]> responseItems =
        testRestTemplate.exchange(
            "/api/homizer/item/",
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            HomizerItemDto[].class);

    assertThat(responseItems.getBody()).hasSize(2);

    String homizerItemId = Arrays.stream(responseItems.getBody()).findFirst().get().getId();

    // Get item
    ResponseEntity<HomizerStorageDto> responseItem =
        testRestTemplate.exchange(
            "/api/homizer/item/" + homizerItemId,
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            HomizerStorageDto.class);

    assertThat(Objects.requireNonNull(responseItem.getBody()).getName()).isEqualTo("testItem1");

    // Update item
    homizerItemDto1.setId(homizerItemId);
    homizerItemDto1.setName("updatedItem");
    homizerItemDto1.setHomizerStorageId(homizerStorageId);

    ResponseEntity<HomizerItemDto> responseUpdateItem =
        testRestTemplate.exchange(
            "/api/homizer/item/",
            HttpMethod.POST,
            new HttpEntity<>(homizerItemDto1, createHeader(token)),
            HomizerItemDto.class);

    assertThat(responseUpdateItem.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // Get updated item
    ResponseEntity<HomizerItemDto> responseUpdatedItem =
        testRestTemplate.exchange(
            "/api/homizer/item/" + homizerItemId,
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            HomizerItemDto.class);

    assertThat(Objects.requireNonNull(responseUpdatedItem.getBody()).getName())
        .isEqualTo("updatedItem");
    assertThat(Objects.requireNonNull(responseUpdatedItem.getBody()).getId())
        .isEqualTo(homizerItemId);
    assertThat(Objects.requireNonNull(responseUpdatedItem.getBody()).getHomizerStorageId())
        .isEqualTo(homizerStorageId);

    // Update storage
    homizerStorageDto1.setId(homizerStorageId);
    homizerStorageDto1.setName("updatedStorage");

    ResponseEntity<HomizerStorageDto> responseUpdateStorage =
        testRestTemplate.exchange(
            "/api/homizer/storage/",
            HttpMethod.POST,
            new HttpEntity<>(homizerStorageDto1, createHeader(token)),
            HomizerStorageDto.class);

    assertThat(responseUpdateStorage.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    // Get updated storage
    ResponseEntity<HomizerStorageDto> responseUpdatedStorage =
        testRestTemplate.exchange(
            "/api/homizer/storage/" + homizerStorageId,
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            HomizerStorageDto.class);

    assertThat(Objects.requireNonNull(responseUpdatedStorage.getBody()).getName())
        .isEqualTo("updatedStorage");

    // Delete item
    ResponseEntity<Void> responseDeleteItem =
        testRestTemplate.exchange(
            "/api/homizer/item/delete/" + homizerItemId,
            HttpMethod.DELETE,
            new HttpEntity<>(createHeader(token)),
            Void.class);

    assertThat(responseDeleteItem.getStatusCode()).isEqualTo(HttpStatus.OK);

    // Try to get deleted item
    ResponseEntity<String> responseDeleted =
        testRestTemplate.exchange(
            "/api/homizer/item/" + homizerItemId,
            HttpMethod.GET,
            new HttpEntity<>(createHeader(token)),
            String.class);

    assertThat(responseDeleted.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseDeleted.getBody()).contains("Item with id: " + homizerItemId + " not found");

    // Delete storage
    ResponseEntity<Void> responseDeleteStorage =
        testRestTemplate.exchange(
            "/api/homizer/storage/delete/" + homizerStorageId,
            HttpMethod.DELETE,
            new HttpEntity<>(createHeader(token)),
            Void.class);

    assertThat(responseDeleteStorage.getStatusCode()).isEqualTo(HttpStatus.OK);

    // Try to get deleted storage
    ResponseEntity<String> responseDeletedStorage =
            testRestTemplate.exchange(
                    "/api/homizer/storage/" + homizerStorageId,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeader(token)),
                    String.class);

    assertThat(responseDeletedStorage.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    assertThat(responseDeletedStorage.getBody()).contains("Storage with id: " + homizerStorageId + " not found");
  }

  private HttpHeaders createHeader(String token) {
    String headerValue = "Bearer " + token;
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", headerValue);
    return httpHeaders;
  }
}
