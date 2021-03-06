package cw.repositoryInterfaces;

import cw.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findAllByUserID(Long userID);
    boolean existsStudentByUserID(Long userID);
    void deleteAllByUserID(Long userID);
}
