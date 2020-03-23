package com.liziczh.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagedUserDTO extends UserDTO {

    @Size(min = 4, max = 18)
    private String password;
}
