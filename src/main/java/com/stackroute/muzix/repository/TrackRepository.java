package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@EnableMongoRepositories
public interface TrackRepository extends MongoRepository<Track,Integer>
{


    @Query
    public Track findBytrackName(String trackName);

    @Query
    public Track findByComment(String comment);

    public Track findBytrackId(int id);


}
