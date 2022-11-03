package com.example.demo.repos;

import com.example.demo.Dto.Audio.AudioD2;
import com.example.demo.Dto.Audio.AudioDto;
import com.example.demo.Entity.AudioTrack;
import com.example.demo.Entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface AudiRepository extends JpaRepository<AudioTrack,Long> {

    List<AudioDto> findAllAudioBySerial(Serial serial);
    @Query(value = "select new com.example.demo.Dto.Audio.AudioD2(a.id,a.name) from AudioTrack a  where a.serial.id = :id")
    List<AudioD2> getSlimOfAllAudiosBySerialId(long id);
    @Query(value = "select a.id,a.name from AudioTrack a where a.serial.id = :id")
    List<Tuple> getAdiosLikeTuples(long id);



}
