package cw.DTO.userDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadUserDTO {
    Long id;
    private String phone;
    private String name;
    private String username;
}
