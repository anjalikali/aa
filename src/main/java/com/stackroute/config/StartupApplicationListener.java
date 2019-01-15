package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent>{

    private TrackRepository trackRepository;

    private static final Logger log = Logger.getLogger(StartupApplicationListener.class);

    @Autowired
    public StartupApplicationListener(TrackRepository trackRepository) {
        this.trackRepository=trackRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("Entering Data On Start using ApplicationListener");

        Track musicTrack1= new Track();
        musicTrack1.setId(1);
        musicTrack1.setName("Chal wha jate hai");
        musicTrack1.setComments ("thik thak song");
        trackRepository.save(musicTrack1);

        Track musicTrack2=new Track();
        musicTrack2.setId(2);
        musicTrack2.setName("chal chaiya chaiya");
        musicTrack2.setComments("hit song");
        trackRepository.save(musicTrack2);

        log.info("Initial data entered using ApplicationListener");
    }
}