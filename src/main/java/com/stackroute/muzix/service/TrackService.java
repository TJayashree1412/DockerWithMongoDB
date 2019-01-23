package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackDoesNotExistException;
import com.stackroute.muzix.exceptions.TrackListEmptyException;
import com.stackroute.muzix.exceptions.TrackalreadyExistsException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackalreadyExistsException;

    public Track getTrack(int trackId) throws TrackDoesNotExistException;

    public Track getTrackbyName(String trackName) throws TrackDoesNotExistException;

    public Track getTrackbyComment(String comment) throws TrackDoesNotExistException;

    public List<Track> getAllTracks() throws TrackListEmptyException;

    public Track updateTrack(Track track) throws TrackDoesNotExistException;

    public Track removeTrack(int trackId) throws TrackDoesNotExistException;

}
