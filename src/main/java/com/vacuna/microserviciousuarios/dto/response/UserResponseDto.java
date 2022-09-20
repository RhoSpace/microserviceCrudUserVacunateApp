package com.vacuna.microserviciousuarios.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDto {

    //Estructura del JSON
    @JsonProperty(value = "rut", index = 1)
    private String rut;

    @JsonProperty(value = "name", index = 2)
    private String name;

    @JsonProperty(value = "phone", index = 4)
    private String phone;

    @JsonProperty(value = "email", index = 5)
    private String email;

    @JsonProperty(value = "password", index = 6)
    private String password;

    @JsonProperty(value = "role", index = 7)
    private boolean role;
}
