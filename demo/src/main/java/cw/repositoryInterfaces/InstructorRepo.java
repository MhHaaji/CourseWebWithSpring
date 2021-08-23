package cw.repositoryInterfaces;

import cw.entities.Instructor;
import cw.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {
    List<Instructor> findAllByUserID(Long userID);
    boolean existsInstructorByUserID(Long userID);
}
