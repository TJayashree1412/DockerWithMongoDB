package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackDoesNotExistException;
import com.stackroute.muzix.exceptions.TrackListEmptyException;
import com.stackroute.muzix.exceptions.TrackalreadyExistsException;
import com.stackroute.muzix.service.TrackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v3")
@Api(value = "Music Resource class level",description = "shows the track information")
public class TrackController {
    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @ApiOperation(value = "Accepts tracks into repository")
    @PostMapping("track")
    public ResponseEntity<?> savetrack(@RequestBody Track track) {
        try {
            return new ResponseEntity<>(trackService.saveTrack(track), HttpStatus.OK);
        }
        catch (TrackalreadyExistsException e)
        {
            return new ResponseEntity<>("Track not found",HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Returns all tracks present in repository")
    @ApiResponses(
            value ={
                  @ApiResponse(code = 100,message = "100 is the message"),
                    @ApiResponse(code = 200,message = "Successfull")
    }
    )
    @GetMapping("track")
    public  ResponseEntity<?> getAllTracks() throws TrackListEmptyException{
            return new ResponseEntity<>(trackService.getAllTracks(),HttpStatus.OK);
    }

    @ApiOperation(value = "Returns the track requested by user")
// localhost:8082/api/v3/trackbyId?trackId=1
    @GetMapping("trackbyId")
    public ResponseEntity<?> getTrackbyid(@RequestParam int trackId) throws TrackDoesNotExistException {
            Track track=trackService.getTrack(trackId);

        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

    @GetMapping("trackbyName")
    public ResponseEntity<?> getTrackbyName(@RequestParam String trackName) throws TrackDoesNotExistException{

        Track track=trackService.getTrackbyName(trackName);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

    @GetMapping("trackbyComment")
    public ResponseEntity<?> getTrackbyComment(@RequestParam String comment) throws TrackDoesNotExistException{

        Track track=trackService.getTrackbyComment(comment);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track) throws TrackDoesNotExistException {

        trackService.updateTrack(track);
        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }

    @DeleteMapping("track/{trackId}")
    public ResponseEntity<?> deletetrack(@PathVariable("trackId") int trackId) throws TrackDoesNotExistException{

            Track track=trackService.removeTrack(trackId);

        return new ResponseEntity<Track>(track,HttpStatus.OK);
    }
}
