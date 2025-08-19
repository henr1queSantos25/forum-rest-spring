package com.henr1que.forumspring.controller;

import com.henr1que.forumspring.domain.Topic;
import com.henr1que.forumspring.dto.topic.TopicDetailsDTO;
import com.henr1que.forumspring.dto.topic.TopicPostDTO;
import com.henr1que.forumspring.dto.topic.TopicUpdateDTO;
import com.henr1que.forumspring.service.TopicService;
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
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    TopicService topicService;

    @PostMapping
    @Transactional
    public ResponseEntity add(@RequestBody @Valid TopicPostDTO topicPostDTO, UriComponentsBuilder Builder) {

        var topic = topicService.addTopic(topicPostDTO);

        var uri = Builder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();

        var detailsDTO = new TopicDetailsDTO(topic);

        return ResponseEntity.created(uri).body(detailsDTO);
    }

    @GetMapping
    public ResponseEntity<Page<TopicDetailsDTO>> list (@PageableDefault(size = 10, sort = {"title"})
                                                           Pageable pageable) {
        var page = topicService.listTopics(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable long id) {
        var topicDetailsDTO = topicService.detailTopic(id);
        return ResponseEntity.ok(topicDetailsDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@PathVariable long id, @RequestBody @Valid TopicUpdateDTO topicUpdateDTO) {
        var topicDetails = topicService.updateTopic(id, topicUpdateDTO);
        return ResponseEntity.ok(topicDetails);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
