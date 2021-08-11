package cw.repositoryInterfaces;

import cw.entities.CourseSection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseSectionRepo extends JpaRepository<CourseSection, Long> {
}
