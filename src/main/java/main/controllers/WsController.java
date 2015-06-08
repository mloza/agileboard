package main.controllers;

import main.entites.Story;
import main.entites.Task;
import main.entites.constants.State;
import main.repositories.StoryRepository;
import main.repositories.TaskRepository;
import org.jboss.logging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by Michal
 * 2015-01-24.
 */
@Controller
public class WsController {

    @Autowired
    StoryRepository stories;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    MessageSendingOperations<String> messagingTemplate;

    @SubscribeMapping("/stories")
    public Iterable<Story> getAllStories() {
        return stories.findAll();
    }

    @MessageMapping("/task/update")
    public void updateTask(Task task) {
        taskRepository.save(task);
        messagingTemplate.convertAndSend("/topic/stories", stories.findNotInState(State.CLOSED));
    }

    @MessageMapping("/story/update")
    public void updateStory(Story story) {
        if(story.getId() != null) {
            story.setTasks(taskRepository.findByStoryId(story.getId()));
        }

        stories.save(story);
        messagingTemplate.convertAndSend("/topic/stories", stories.findNotInState(State.CLOSED));
    }

    @MessageMapping("/pos")
    @SendToUser("/queue/testing")
    public String wsReplayTest() {
        System.out.println("Subscribed");
        return "jejejejejej";
    }

}
