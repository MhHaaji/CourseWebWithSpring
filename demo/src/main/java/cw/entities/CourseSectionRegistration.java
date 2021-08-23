package cw.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class CourseSectionRegistration {
    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long StudentID;
    @NotNull
    private Double score;
    @NotNull
    private String courseTitle;
    @NotNull
    private String courseSectionNumber;

    public CourseSectionRegistration(Long studentID, String courseTitle, String courseSectionNumber) {
        StudentID = studentID;
        this.courseTitle = courseTitle;
        this.courseSectionNumber = courseSectionNumber;
    }
}
