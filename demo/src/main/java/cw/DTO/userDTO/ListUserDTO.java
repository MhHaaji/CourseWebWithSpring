package cw.DTO.userDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListUserDTO {

    Long id;
    private String name;
    private String username;
    private boolean active;

}
