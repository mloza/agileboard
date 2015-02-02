package main.repositories;

import main.entites.Story;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Michal
 * 2015-01-23.
 */

public interface StoryRepository extends PagingAndSortingRepository<Story, Long> {
}
