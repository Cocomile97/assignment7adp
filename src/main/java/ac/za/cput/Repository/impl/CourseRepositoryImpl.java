package ac.za.cput.Repository.impl;

import ac.za.cput.Repository.CourseRepository;
import ac.za.cput.Repository.LocationRepository;
import ac.za.cput.domain.Course;
import ac.za.cput.domain.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseRepositoryImpl implements CourseRepository {

    private static CourseRepositoryImpl repository = null;
    private Set<Course> courses;

    private CourseRepositoryImpl(){
        this.courses = new HashSet();
    }

    private Course findCourse(String courseId) {
        return this.courses.stream()
                .filter(course -> course.getCourseId().trim().equals(courseId))
                .findAny()
                .orElse(null);
    }

    public static CourseRepositoryImpl getRepository(){
        if (repository == null) repository = new CourseRepositoryImpl();
        return repository;
    }


    public Course create(Course course){
        this.courses.add(course);
        return course;
    }

    public Course read(final String courseId){
        Course course = findCourse(courseId);
        return course;
    }

    public void delete(String courseId) {
        Course course = findCourse(courseId);
        if (course != null) this.courses.remove(course);
    }

    public Course update(Course course){
        Course toDelete = findCourse(course.getCourseId());
        if(toDelete != null) {
            this.courses.remove(toDelete);
            return create(course);
        }
        return null;
    }


    public Set<Course> getAll(){
        return this.courses;
    }
}
