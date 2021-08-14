package cw.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Instructor {
    private @Id
    @GeneratedValue
    Long id;

}
