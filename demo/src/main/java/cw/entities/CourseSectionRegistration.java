package cw.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CourseSectionRegistration {
    @Id
    @GeneratedValue
    private Long id;
}
