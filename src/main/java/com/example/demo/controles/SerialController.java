package com.example.demo.controles;

import com.example.demo.Dto.Serial.SerialD2;
import com.example.demo.Dto.Serial.SerialFrontPageInfo;
import com.example.demo.service.SerialService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/serial")
public class SerialController {

    private final SerialService serialService;

    @PostMapping("/public/getAllSerialById")
    public ResponseEntity<SerialD2> getSerialById(@RequestParam long id){
        SerialD2 serial = serialService.getSerialByIdFetchAudios(id);
        return new ResponseEntity<>(serial, HttpStatus.OK);
    }


//    @Secured("ROLE_user")
//    @PreAuthorize("hasAnyAuthority('SAVE_OWN_POST')")
    @PostMapping("/getAllSerialByIdForSideBar")
    public ResponseEntity<List<SerialFrontPageInfo>> getSerialByUserId(@RequestParam long id){
        List<SerialFrontPageInfo> allSerialsByUserId = serialService.getAllSerialsByUserId(id);
        return new ResponseEntity<>(allSerialsByUserId,HttpStatus.OK);
    }


    @GetMapping("/public/getFrontPageInfo")
    public ResponseEntity<List<SerialFrontPageInfo>> getAllSerialByPage(){

        List<SerialFrontPageInfo> pageInfos =
                serialService.findAllBy(PageRequest.of(0,30));
        return new ResponseEntity<>(pageInfos,HttpStatus.OK);
    }

}
