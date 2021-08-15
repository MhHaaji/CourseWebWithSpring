package cw.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CourseSection {
    private @Id
    @GeneratedValue
    Long id;
    private int sectionNumber;
    private String courseTitle;
    private Long instructorID;
    private int numberOfStudents;

    public CourseSection(int sectionNumber, String courseTitle, Long instructorID) {
        this.sectionNumber = sectionNumber;
        this.courseTitle = courseTitle;
        this.instructorID = instructorID;
    }
}
