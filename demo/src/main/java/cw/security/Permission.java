package cw.security;

public enum Permission {
    //entities-permissions:
        //students-permission:
    STUDENT_READ("student:read"),
    STUDENT_CREATE("student:create"),
    STUDENT_LIST("student:list"),
    STUDENT_UPDATE("student:update"),
    STUDENT_DELETE("student:delete"),
        //instructor-permissions:
    INSTRUCTOR_LIST("instructor:list"),
    INSTRUCTOR_CREATE("instructor:create"),
    INSTRUCTOR_READ("instructor:read"),
    INSTRUCTOR_UPDATE("instructor:update"),
    INSTRUCTOR_DELETE("instructor:delete"),
        //staff-permissions:
    STAFF_LIST("staff:list"),
    STAFF_CREATE("staff:create"),
    STAFF_READ("staff:read"),
    STAFF_UPDATE("staff:update"),
    STAFF_DELETE("staff:delete"),
        //course-permission:
    COURSE_LIST("course:list"),
    COURSE_CREATE("course:create"),
    COURSE_READ("course:read"),
    COURSE_UPDATE("course:update"),
    COURSE_DELETE("course:delete"),
        //section-permissions:
    SECTION_LIST("section:list"),
    SECTION_CREATE("section:create"),
    SECTION_READ("section:read"),
    SECTION_UPDATE("section:update"),
    SECTION_DELETE("section:delete"),
        //sectionRegistration-permission:
    SECTION_REGISTRATION_LIST("sectionRegistration:list"),
    SECTION_REGISTRATION_CREATE("sectionRegistration:create"),
    SECTION_REGISTRATION_READ("sectionRegistration:read"),
    SECTION_REGISTRATION_UPDATE("sectionRegistration:update"),
    SECTION_REGISTRATION_DELETE("sectionRegistration:delete"),
        //term-permissions:
    TERM_LIST("term:list"),
    TERM_CREATE("term:create"),
    TERM_READ("term:read"),
    TERM_UPDATE("term:update"),
    TERM_DELETE("term:delete");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}