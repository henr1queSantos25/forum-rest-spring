package com.henr1que.forumspring.service;


import com.henr1que.forumspring.domain.Topic;
import com.henr1que.forumspring.dto.topic.TopicDetailsDTO;
import com.henr1que.forumspring.dto.topic.TopicPostDTO;
import com.henr1que.forumspring.dto.topic.TopicUpdateDTO;
import com.henr1que.forumspring.repository.CourseRepository;
import com.henr1que.forumspring.repository.TopicRepository;
import jakarta.validation.Valid;
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
        return topicRepository.findAllByStatusTrue(pageable)
                .map(TopicDetailsDTO::new);
    }

    public TopicDetailsDTO detailTopic(long id) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Topic with this ID does not exist"));

        return new TopicDetailsDTO(topic);
    }

    public TopicDetailsDTO updateTopic(long id, @Valid TopicUpdateDTO topicUpdateDTO) {
        var topic = topicRepository.findById(id);

        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic with this ID does not exist");
        }

        topic.get().updateInformations(topicUpdateDTO);

        return new TopicDetailsDTO(topic.get());
    }

    public void deleteTopic(long id) {
        var topic = topicRepository.findById(id);

        if (topic.isEmpty()) {
            throw new IllegalArgumentException("Topic with this ID does not exist");
        }

        topic.get().delete();
    }
}
