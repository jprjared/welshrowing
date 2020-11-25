package com.team1.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {

    private Long user_id;
    private String user_name;
    private String user_type;
    private String email;
    private String password;
}
