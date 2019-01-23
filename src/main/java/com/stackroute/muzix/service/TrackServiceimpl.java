package com.stackroute.muzix.service;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackDoesNotExistException;
import com.stackroute.muzix.exceptions.TrackListEmptyException;
import com.stackroute.muzix.exceptions.TrackalreadyExistsException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceimpl implements TrackService{

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceimpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackalreadyExistsException{
        if(trackRepository.existsById(track.getTrackId())){
            throw new TrackalreadyExistsException("track already exists exception");
        }
        Track savedTrack=trackRepository.save(track);

        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() throws TrackListEmptyException {
        if (trackRepository.findAll().isEmpty()){
            throw new TrackListEmptyException();
        }
        return trackRepository.findAll();
    }

    @Override
    public Track getTrack(int trackId) throws TrackDoesNotExistException{
        if(trackRepository.existsById(trackId)) {
            return trackRepository.findById(trackId).get();
        }else{
            throw new TrackDoesNotExistException("track does not exist");
        }


    }
    @Override
    public Track getTrackbyName(String trackName) throws TrackDoesNotExistException {
        int trackId=trackRepository.findBytrackName(trackName).getTrackId();
        if(!(trackRepository.existsById(trackId))){
            throw new TrackDoesNotExistException("track does not exist");
        }
        return trackRepository.findBytrackName(trackName);

    }

    @Override
    public Track getTrackbyComment(String comment) throws TrackDoesNotExistException {
        int trackId=trackRepository.findByComment(comment).getTrackId();
        System.out.println(trackId);
        if(!(trackRepository.existsById(trackId))){
            throw new TrackDoesNotExistException("track does not exist");
        }
        return trackRepository.findByComment(comment);

    }

    @Override
    public Track updateTrack(Track track) throws TrackDoesNotExistException{
        if(trackRepository.existsById(track.getTrackId())){
            track.setTrackName(track.getTrackName());
            track.setComment(track.getComment());
            trackRepository.save(track);
        }
        else
        {
            throw  new TrackDoesNotExistException("track does not exist");
        }
        return track;
    }

    @Override
    public Track removeTrack(int trackId) throws TrackDoesNotExistException {
        //if(trackRepository.existsById(track.getTrackId())) {
        try{
            trackRepository.deleteById(trackId);
        }catch (Exception e){
          throw new TrackDoesNotExistException("track does not exist");
        }

        return trackRepository.findById(trackId).get();
    }
}
