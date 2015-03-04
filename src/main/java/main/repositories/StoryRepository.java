package main.repositories;

import main.entites.Story;
import main.entites.constants.State;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Michal
 * 2015-01-23.
 */

public interface StoryRepository extends PagingAndSortingRepository<Story, Long> {

    @Query("select s from Story s where s.state <> ?1 or s.state is null")
    public List<Story> findNotInState(State state);

}
