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
public class Term {
    private @Id
    @GeneratedValue
    Long id;
    @NotNull
    private String title;
    @NotNull
    private boolean isOpen;

    public Term(String title) {
        this.title = title;
        this.isOpen = false;
    }
}
