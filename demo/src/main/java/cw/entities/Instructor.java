package cw.entities;

import com.sun.istack.NotNull;
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
public class Instructor {
    private @Id
    @GeneratedValue
    Long id;
    @NotNull
    private Long userID;

    public Instructor(Long userID) {
        this.userID = userID;
    }
}
