package com.liziczh.erp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1868235999580055837L;

    private Long userId;       // 用户ID
    private String username;  // 用户名
    private String email;    // 用户邮箱
    private String password; // 密码（明文）
}
