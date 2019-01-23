package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

//@SpringBootTest //starts application context and puts all beans in application context
@RunWith(SpringRunner.class) //start the application context where i need
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp()
    {
        track=new Track();
        track.setTrackId(1);
        track.setTrackName("A Sky full of stars");
        track.setComment("cold play");
    }

    @After
    public void tearDown(){

        trackRepository.deleteAll(); //clear the repo
    }

    @Test
    public void testsaveTrack(){
        trackRepository.save(track);
        Track savedTrack=trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(track,savedTrack);
    }

    @Test
    public void testSaveTrackFailure(){
        Track testTrack=new Track(1,"A Sky full of stars","cold play");
 Track trackcheck=trackRepository.save(track);
        Track savedTrack=trackRepository.findById(track.getTrackId()).get();
        Assert.assertEquals(testTrack,track);
    }

    @Test
    public  void testGetAllTracks(){
        Track track1=new Track(1,"love story","Taylor swift");
        Track track2=new Track(2,"A sky full of stars","cold play");
        trackRepository.save(track1);
        trackRepository.save(track2);

        List<Track> list=trackRepository.findAll();
        Assert.assertEquals("love story",list.get(0).getTrackName());
    }

    @Test
    public void testGetTrackById(){
        Track track1=new Track(1,"love story","Taylor swift");
        Track track2=new Track(2,"A sky full of stars","cold play");
        trackRepository.save(track1);
        trackRepository.save(track2);

        Track findTrack=trackRepository.findById(1).get();
        Assert.assertEquals(track1,findTrack);
    }

    @Test
    public void testGetTrackBytrackName(){
        Track track1=new Track(1,"love story","Taylor swift");
        Track track2=new Track(2,"A sky full of stars","cold play");
        trackRepository.save(track1);
        trackRepository.save(track2);

        Track findTrack=trackRepository.findBytrackName("love story");
        Assert.assertEquals(track1,findTrack);
    }

    @Test
    public void testGetTrackByComment(){
        Track track1=new Track(1,"love story","Taylor swift");
        Track track2=new Track(2,"A sky full of stars","cold play");
        trackRepository.save(track1);
        trackRepository.save(track2);

        Track findTrack=trackRepository.findByComment("Taylor swift");
        Assert.assertEquals(track1,findTrack);
    }

    @Test
    public void testRemoveTrack(){
        trackRepository.deleteById(track.getTrackId());
        Optional<Track> deletedTrack=trackRepository.findById(track.getTrackId());
        Optional<Track> expected= Optional.empty();
        Assert.assertEquals(expected,deletedTrack);

    }
}

