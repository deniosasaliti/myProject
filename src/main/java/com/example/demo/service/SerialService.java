package com.example.demo.service;

import com.example.demo.Dto.Serial.SerialD2;
import com.example.demo.Dto.Serial.SerialFrontPageInfo;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SerialService {

    SerialD2 getSerialByIdFetchAudios(long serialId);

    List<SerialFrontPageInfo> getAllSerialsByUserId(long id);

    List<SerialFrontPageInfo> findAllBy(PageRequest of);
}
