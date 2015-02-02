package main.repositories;

import main.entites.Story;
import main.entites.Task;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Michal
 * 2015-01-23.
 */

public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {

    public List<Task> findByStoryId(Long storyId);

}
