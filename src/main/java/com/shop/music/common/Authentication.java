package com.shop.music.common;


import com.shop.music.dto.LoginDTO;
import com.shop.music.model.User;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class Authentication {

    public User authenticate(LoginDTO loginDTO, User user) throws Exception {
        if (user  == null || user.getRole() != loginDTO.getRole()){
            return null;
        }
        
        BCrypt.Result  result = BCrypt.verifyer().verify(loginDTO.getPassword().toCharArray(), user.getPassword());
        if (result == null || !result.verified){
            return null;
        }
        return user;
    }
}
