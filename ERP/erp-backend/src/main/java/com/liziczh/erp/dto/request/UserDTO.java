package com.liziczh.erp.dto.request;

import com.liziczh.erp.entity.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private UserRole role;

}
