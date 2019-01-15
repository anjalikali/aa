package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@RestController is a specialized version of the controller. It includes the
//@Controller and @ResponseBody annotations and as a result, simplifies the controller implementation:

@RestController
@RequestMapping("api/v1/")
public class TrackController {

    private TrackService trackService;

    private ResponseEntity <?>responseEntity= null;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @PostMapping(value = "track")
    public ResponseEntity<?> addTrack(@RequestBody Track track){

        try {
            Track track1 = trackService.saveTrack(track);
            responseEntity= new ResponseEntity<Track>(track1, HttpStatus.CREATED);
        }catch (TrackAlreadyExistsException trackAlreadyExistsException){
            responseEntity =  new ResponseEntity<>(trackAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
        }catch(Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return responseEntity;
    }
//Annotation for mapping HTTP GET requests onto specific handler methods.
    //Specifically, @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod.GET).
    @GetMapping(value = "tracks")
    public ResponseEntity<?> getAllTracks(){
        try {
            List<Track> tracks = trackService.getAllTracks();
            responseEntity = new ResponseEntity<List<Track>>(tracks, HttpStatus.OK);
        }catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }



    @DeleteMapping(value = "track/{id}")
    public ResponseEntity<?> deleteTrackById(@PathVariable(value = "id") int id){

        try {
            Track track = (Track) trackService.deleteTrackById(id);
            responseEntity = new ResponseEntity<Track>(track,HttpStatus.OK);
        }
        catch (TrackNotFoundException trackNotFoundException){
            responseEntity = new ResponseEntity<>(trackNotFoundException.getMessage(),HttpStatus.CONFLICT);

        }
        catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
//    @RequestMapping(value = "track/{id}/{comments}", method = RequestMethod.PUT)
    @PutMapping(value = "track/{id}")
    public ResponseEntity<?> updateTrack(@RequestBody Track track){
        try{
            Track track1 = (Track) trackService.updateTrack(track);
            responseEntity = new ResponseEntity<Track>(track1, HttpStatus.OK);
        }catch (TrackNotFoundException trackNotFoundException){
            responseEntity = new ResponseEntity<>(trackNotFoundException.getMessage(), HttpStatus.CONFLICT);
        }
        catch (Exception exception){
            responseEntity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
