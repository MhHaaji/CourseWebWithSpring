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
public class Course {

    private @Id
    @GeneratedValue
    Long id;
    private String title;
    private int unit;

    public Course(String title, int unit) {
        this.title = title;
        this.unit = unit;
    }
}
