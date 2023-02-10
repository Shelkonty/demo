package com.example.entity;

import com.example.entity.roles.ERole;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Column(unique = true, updatable = false)
   private String usernames;
   @Column(nullable = false)
   private String f_name;
   private String l_name;
   @Column(unique = true)
   private String email;
   @Column(length = 20)
   private Long phone;
   @Column(length = 3000)
   private String password;

   @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role",
           joinColumns = @JoinColumn(name = "user_id"))
   private Set<ERole> role = new HashSet<>();

   @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
   private List<Post> posts = new ArrayList<>();

   @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
   @Column(updatable = false)
   private LocalDateTime createDate;

   @Transient
   private Collection<? extends GrantedAuthority> authorities;

   public User(Long id,
               String usernames,
               String email,
               Long phone,
               String password,
               Collection<? extends GrantedAuthority> authorities) {
      this.id = id;
      this.usernames = usernames;
      this.email = email;
      this.phone = phone;
      this.password = password;
      this.authorities = authorities;
   }

   public User() {}

   @PrePersist
   protected void onCreate(){
       this.createDate = LocalDateTime.now();
   }


   /**
    *
    * Security
    */
   @Override
   public String getPassword(){
      return password;
   }

   @Override
   public String getUsername() {
      return null;
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
