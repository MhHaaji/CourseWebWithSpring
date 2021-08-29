package cw.repositoryInterfaces;

import cw.entities.MyUser;
import jdk.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findByUsername(String username);
    List<MyUser> findAllByUsername(String username);
    boolean existsByUsername(String username);
}
