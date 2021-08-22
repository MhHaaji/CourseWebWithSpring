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
public class Student{
    private @Id
    @GeneratedValue
    Long id;
    private Long userID;

    public Student(Long userID) {
        this.userID = userID;
    }
}
