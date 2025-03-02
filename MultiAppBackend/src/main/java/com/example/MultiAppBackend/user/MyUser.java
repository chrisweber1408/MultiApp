  package com.example.MultiAppBackend.user;

  import com.example.MultiAppBackend.homizer.homizerItem.HomizerItem;
  import com.example.MultiAppBackend.homizer.homizerStorage.HomizerStorage;
  import jakarta.persistence.*;
  import lombok.AllArgsConstructor;
  import lombok.Data;
  import lombok.NoArgsConstructor;
  import org.springframework.security.core.GrantedAuthority;
  import org.springframework.security.core.userdetails.UserDetails;

  import java.util.ArrayList;
  import java.util.Collection;
  import java.util.List;
  import java.util.UUID;

  @Entity
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public class MyUser implements UserDetails {

    @Id private final String id = UUID.randomUUID().toString();

    @Column(unique = true)
    private String email;

    private String password;
    private List<String> roles;

    private List<String> homizerStorageIds;

    private List<String> homizerItemIds;

    public void addHomizerStorage(HomizerStorage homizerStorage) {
      if (this.homizerStorageIds == null) {
        this.homizerStorageIds = new ArrayList<>();
      } else {
        this.homizerStorageIds.add(homizerStorage.getId());
      }
    }

    public void addHomizerItem(HomizerItem homizerItem) {
      if (homizerItemIds == null) {
        homizerItemIds = new ArrayList<>();
      }
      homizerItemIds.add(homizerItem.getId());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of();
    }

    @Override
    public String getPassword() {
      return password;
    }

    @Override
    public String getUsername() {
      return email;
    }

    @Override
    public boolean isAccountNonExpired() {
      return true;
    }

    @Override
    public boolean isAccountNonLocked() {
      return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
      return true;
    }

    @Override
    public boolean isEnabled() {
      return true;
    }
  }
