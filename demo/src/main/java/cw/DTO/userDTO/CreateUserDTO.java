package cw.DTO.userDTO;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
@Getter
@Setter
public class CreateUserDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String phone;



}
