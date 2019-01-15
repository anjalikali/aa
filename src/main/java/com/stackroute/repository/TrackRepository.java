package com.stackroute.repository;

import com.stackroute.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

// for sql repository
//public interface TrackRepository extends CrudRepository<Track, Integer>
public interface TrackRepository extends MongoRepository<Track, Integer> {

   // @Query(value="from track where track_comments='nice'")
    // Track findByTrackComments(String comment);
}