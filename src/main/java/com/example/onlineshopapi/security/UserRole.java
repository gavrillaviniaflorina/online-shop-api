package com.example.onlineshopapi.security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.onlineshopapi.security.UserPermission.PIECE_READ;
import static com.example.onlineshopapi.security.UserPermission.PIECE_WRITE;


@AllArgsConstructor
public enum UserRole {

   PERSON(Sets.newHashSet(PIECE_READ,PIECE_WRITE));

   private final Set<UserPermission> permissions;
   public Set<UserPermission> getPermissions(){
       return permissions;
   }

   public Set<SimpleGrantedAuthority> getGrantedAuthrities(){
       Set<SimpleGrantedAuthority> collect=getPermissions()
               .stream()
               .map(e->new SimpleGrantedAuthority(e.getPermision()))
               .collect(Collectors.toSet());

       collect.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
       return collect;
   }
}
