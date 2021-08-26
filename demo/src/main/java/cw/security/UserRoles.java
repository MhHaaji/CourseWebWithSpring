package cw.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static cw.security.Permission.*;

public enum UserRoles {
    STUDENT(Sets.newHashSet(STUDENT_READ, STUDENT_LIST,
            TERM_LIST, TERM_READ,
            INSTRUCTOR_LIST, INSTRUCTOR_READ,
            COURSE_LIST, COURSE_READ,
            SECTION_LIST, SECTION_READ)),


    INSTRUCTOR(Sets.newHashSet(STUDENT_READ, STUDENT_LIST,
            TERM_LIST, TERM_READ,
            INSTRUCTOR_LIST, INSTRUCTOR_READ,
            COURSE_LIST, COURSE_READ,
            SECTION_LIST, SECTION_READ, SECTION_CREATE, SECTION_DELETE, SECTION_UPDATE)),


    STAFF(Sets.newHashSet(TERM_LIST, TERM_CREATE, TERM_READ, TERM_DELETE, TERM_UPDATE,
            INSTRUCTOR_LIST, INSTRUCTOR_CREATE, INSTRUCTOR_READ, INSTRUCTOR_UPDATE, INSTRUCTOR_DELETE,
            STUDENT_READ, STAFF_LIST, STUDENT_CREATE, STUDENT_UPDATE, STAFF_DELETE,
            COURSE_LIST, COURSE_CREATE, COURSE_READ, COURSE_DELETE, COURSE_UPDATE,
            SECTION_LIST, SECTION_READ, SECTION_DELETE, SECTION_UPDATE)),


    ADMIN(Sets.newHashSet(STUDENT_READ, STAFF_LIST, STUDENT_CREATE, STUDENT_UPDATE, STAFF_DELETE,
            INSTRUCTOR_LIST, INSTRUCTOR_CREATE, INSTRUCTOR_READ, INSTRUCTOR_UPDATE, INSTRUCTOR_DELETE,
            STAFF_LIST, STAFF_CREATE, STAFF_READ, STAFF_UPDATE, STAFF_DELETE,
            COURSE_LIST, COURSE_CREATE, COURSE_READ, COURSE_DELETE, COURSE_UPDATE,
            SECTION_LIST, SECTION_CREATE, SECTION_READ, SECTION_DELETE, SECTION_UPDATE,
            TERM_LIST, TERM_CREATE, TERM_READ, TERM_DELETE, TERM_UPDATE));


    // SECTION_REGISTRATION_LIST, SECTION_REGISTRATION_CREATE, SECTION_REGISTRATION_READ, SECTION_REGISTRATION_UPDATE,
    // SECTION_REGISTRATION_DELETE,

    @Getter
    private final Set<Permission> permissions;

    UserRoles(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(Permission::getPermission)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
