package cw.DTO.courseDTO;

import com.sun.istack.NotNull;

public class CourseCreateDTO {
    Long id;
    @NotNull
    private String title;
    @NotNull
    private int unit;
}
