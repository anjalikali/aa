package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {

        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        Optional<Track> fetchedtrack = trackRepository.findById (track.getId ());
        // Track track1 = null;
        if (fetchedtrack.isPresent ()) {
            throw new TrackAlreadyExistsException ("Track already exists");
        } else {
            return trackRepository.save (track);
        }


    }

    @Override
    public List<Track> getAllTracks() throws Exception {
        List<Track> trackList = (List<Track>) trackRepository.findAll ();
        return trackList;
    }

    @Override
    public Track deleteTrackById(int id) throws TrackNotFoundException {
        Optional<Track> track = trackRepository.findById (id);
        if (track.isPresent ()) {
            trackRepository.deleteById (id);
        } else {
            throw new TrackNotFoundException ("Track not found");
        }
        return track.get ();
    }


    @Override
    public Track updateTrack(@RequestBody Track track) throws TrackNotFoundException {
        Track track1 = null;
        if (trackRepository.existsById(track.getId ())) {
                track1 = trackRepository.save(track);
        } else {
            throw new TrackNotFoundException("Track not found");
        }
        return track1;
    }



}
