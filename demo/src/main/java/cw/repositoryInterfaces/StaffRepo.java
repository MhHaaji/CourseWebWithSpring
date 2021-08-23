package cw.repositoryInterfaces;

import cw.entities.Staff;
import cw.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepo extends JpaRepository<Staff, Long> {
    List<Staff> findAllByUserID(Long userID);
    boolean existsStaffByUserID(Long userID);
}
