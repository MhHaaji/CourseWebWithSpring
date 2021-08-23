package cw.repositoryInterfaces;

import cw.entities.Term;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermRepo extends JpaRepository<Term, Long> {
}
