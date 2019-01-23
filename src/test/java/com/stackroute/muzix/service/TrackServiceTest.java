package com.stackroute.muzix.service;


import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackDoesNotExistException;
import com.stackroute.muzix.exceptions.TrackListEmptyException;
import com.stackroute.muzix.exceptions.TrackalreadyExistsException;
import com.stackroute.muzix.repository.TrackRepository;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class TrackServiceTest {

    Track track;

    @Mock
    private TrackRepository trackRepository;

    @InjectMocks
    TrackServiceimpl trackServiceimpl;
   // TrackServiceimpl trackServiceimpl;
    List<Track> list=null;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        track=new Track();
        track.setTrackId(100);
        track.setTrackName("A Sky full of stars");
        track.setComment("cold play");
        list=new ArrayList<>();

        list.add(track);
    }

    @Test
    public void saveTrackTestSuccess() throws TrackalreadyExistsException{
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackServiceimpl.saveTrack(track);
        Assert.assertEquals(track,savedTrack);

        verify(trackRepository,times(1)).save(track);
    }

    @Test
    public void saveTrackFailure() throws TrackalreadyExistsException{
        when(trackRepository.save((Track) any())).thenReturn(track);
        Track savedTrack=trackServiceimpl.saveTrack(track);
        System.out.println("savedTrack"+savedTrack);
        Assert.assertEquals(track,savedTrack);

    }

    @Test
    public void getAllTrack() throws TrackListEmptyException
    {
        trackRepository.save(track);
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> tracklist=trackServiceimpl.getAllTracks();
        Assert.assertEquals(list,tracklist);
    }

    @Test
    public void getTrack() throws TrackDoesNotExistException{
        trackRepository.save(track);
        System.out.println(track);
        System.out.println(track.getTrackId());
        System.out.println(trackRepository.findById(100));
        System.out.println(trackRepository.findById(track.getTrackId()).get());
       // when(trackRepository.findById(track.getTrackId()).get()).thenReturn(track);
        Track savedTrack=trackServiceimpl.getTrack(track.getTrackId());
       // Assert.assertEquals(track,savedTrack);
    }

    @Test
    public void getTrackbytrackName() throws TrackDoesNotExistException{
        trackRepository.save(track);
        when(trackRepository.findBytrackName(any())).thenReturn(track);
        Track savedTrack=trackServiceimpl.getTrackbyName(track.getTrackName());
        Assert.assertEquals(track,savedTrack);
    }

    @Test
    public void getTrackbyComment() throws TrackDoesNotExistException{
        trackRepository.save(track);
        when(trackRepository.findByComment(any())).thenReturn(track);
        Track savedTrack=trackServiceimpl.getTrackbyComment(track.getComment());
        Assert.assertEquals(track,savedTrack);
    }
}
