package cw.entities;

import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
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
    @Nullable
    private int sectionNumber;
    @NotNull
    private String courseTitle;
    @NotNull
    private Long instructorID;
    @Nullable
    private int numberOfStudents;

    public CourseSection(int sectionNumber, String courseTitle, Long instructorID) {
        this.sectionNumber = sectionNumber;
        this.courseTitle = courseTitle;
        this.instructorID = instructorID;
    }
}
