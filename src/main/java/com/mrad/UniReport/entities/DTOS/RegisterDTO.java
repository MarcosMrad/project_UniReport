package com.mrad.UniReport.entities.DTOS;

import com.mrad.UniReport.entities.enums.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {

}
