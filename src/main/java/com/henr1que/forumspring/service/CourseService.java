package com.henr1que.forumspring.service;

import com.henr1que.forumspring.domain.Course;
import com.henr1que.forumspring.dto.course.CoursePostDTO;
import com.henr1que.forumspring.dto.course.CourseUpdateDTO;
import com.henr1que.forumspring.repository.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public Course addCourse(CoursePostDTO data) {
        if(courseRepository.existsByName(data.name()) ) {
            throw new IllegalArgumentException("Course with this name already exists");
        }

        var course = new Course(data);

        courseRepository.save(course);

        return course;
    }

    public Page<CoursePostDTO> listCourses(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(c -> new CoursePostDTO(c.getName(), c.getCategory()));
    }

    public CoursePostDTO detailCourse(long id) {
        var course = courseRepository.getReferenceById(id);

        return new CoursePostDTO(course.getName(), course.getCategory());
    }

    public CoursePostDTO updateCourse(long id, @Valid CourseUpdateDTO courseUpdateDTO) {
        var course = courseRepository.getReferenceById(id);
        course.updateInformations(courseUpdateDTO);

        return new CoursePostDTO(course.getName(), course.getCategory());
    }

    public void deleteCourse(long id) {
        var course =  courseRepository.findById(id);
        if(course.isEmpty()) {
            throw new EntityNotFoundException ("Course with this ID does not exist");
        }
        courseRepository.deleteById(id);
    }
}
