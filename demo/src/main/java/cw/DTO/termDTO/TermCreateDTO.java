package cw.DTO.termDTO;

import com.sun.istack.NotNull;

public class TermCreateDTO {
    @NotNull
    private String title;

    public TermCreateDTO(String title) {
        this.title = title;
    }
}
