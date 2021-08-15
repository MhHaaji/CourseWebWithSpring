package cw.entities;

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
    private Long StudentID;
    private Double score;
    private String courseTitle;
    private String courseSectionNumber;

    public CourseSectionRegistration(Long studentID, String courseTitle, String courseSectionNumber) {
        StudentID = studentID;
        this.courseTitle = courseTitle;
        this.courseSectionNumber = courseSectionNumber;
    }
}
