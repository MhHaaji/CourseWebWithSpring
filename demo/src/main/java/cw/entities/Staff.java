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
public class Staff {
    private @Id
    @GeneratedValue
    Long id;
    @NotNull
    private Long userID;
    @NotNull
    private String personnelID;

    public Staff(Long userID, String personnelID) {
        this.userID = userID;
        this.personnelID = personnelID;
    }
}
