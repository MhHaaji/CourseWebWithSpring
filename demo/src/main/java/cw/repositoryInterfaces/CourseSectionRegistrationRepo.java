package cw.repositoryInterfaces;

import cw.entities.CourseSectionRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
@Repository
@Service
public interface CourseSectionRegistrationRepo extends JpaRepository<CourseSectionRegistration, Long> {

}
