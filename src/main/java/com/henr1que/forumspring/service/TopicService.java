package com.henr1que.forumspring.service;


import com.henr1que.forumspring.domain.Topic;
import com.henr1que.forumspring.dto.topic.TopicDetailsDTO;
import com.henr1que.forumspring.dto.topic.TopicPostDTO;
import com.henr1que.forumspring.repository.CourseRepository;
import com.henr1que.forumspring.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    CourseRepository courseRepository;

    public Topic addTopic (TopicPostDTO data) {
        if(topicRepository.existsByTitle(data.title()) || topicRepository.existsByMessage(data.message())) {
            throw new IllegalArgumentException("Topic with this title already exists");
        }

        var course = courseRepository.findById(data.courseId())
                .orElseThrow(() -> new IllegalArgumentException("Course with this ID does not exist"));

        var topic = new Topic(data.title(), data.message(), course);
        topicRepository.save(topic);

        return topic;
    }

    public Page<TopicDetailsDTO> listTopics(Pageable pageable) {
        return topicRepository.findAll(pageable)
                .map(TopicDetailsDTO::new);
    }
}
