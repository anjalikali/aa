package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

// all provided services
public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;

    public List<Track> getAllTracks() throws Exception;

    public Track deleteTrackById(int id) throws TrackNotFoundException;

    public Track updateTrack(Track track) throws TrackNotFoundException;
}
