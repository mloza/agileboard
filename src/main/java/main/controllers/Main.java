package main.controllers;

import main.entites.Story;
import main.entites.Task;
import main.repositories.StoryRepository;
import main.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Michal
 * 2015-01-23.
 */
@Controller
@RequestMapping("/")
public class Main {
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StoryRepository storyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/xxx")
    @ResponseBody
    public Iterable<Story> index2() {
        return storyRepository.findAll();
    }
}
