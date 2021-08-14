package cw.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CourseSection {
    private @Id
    @GeneratedValue
    Long id;

}
