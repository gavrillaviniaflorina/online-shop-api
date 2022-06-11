package com.example.onlineshopapi.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserPermission {
    PIECE_READ("piece:read"),
    PIECE_WRITE("piece:write");
    private String permission;
    public String getPermision(){
        return permission;
    }

}
