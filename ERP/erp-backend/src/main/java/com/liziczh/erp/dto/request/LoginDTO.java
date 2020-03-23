package com.liziczh.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    @NotNull
    @Size(min = 1, max = 20)
    private String username;
    @NotNull
    @Size(min = 4, max = 50)
    private String password;
}
