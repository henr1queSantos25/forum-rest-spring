package com.henr1que.forumspring.controller;


import com.henr1que.forumspring.dto.course.CoursePostDTO;
import com.henr1que.forumspring.dto.course.CourseUpdateDTO;
import com.henr1que.forumspring.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService courseService;


    @PostMapping
    public ResponseEntity addCourse(@RequestBody @Valid CoursePostDTO coursePostDTO, UriComponentsBuilder Builder) {

        var course = courseService.addCourse(coursePostDTO);

        var uri = Builder.path("/courses/{id}").buildAndExpand(course.getId()).toUri();

        var detailsDTO = new CoursePostDTO(course.getName(), course.getCategory());

        return ResponseEntity.created(uri).body(detailsDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CoursePostDTO>> list (@PageableDefault(size = 10, sort = {"name"})
                                                       Pageable pageable) {
        var page = courseService.listCourses(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable long id) {
        var courseDetailsDTO = courseService.detailCourse(id);
        return ResponseEntity.ok(courseDetailsDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable long id, @RequestBody @Valid CourseUpdateDTO courseUpdateDTO) {
        var courseDetails = courseService.updateCourse(id, courseUpdateDTO);
        return ResponseEntity.ok(courseDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

}
