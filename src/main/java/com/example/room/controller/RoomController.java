package com.example.room.controller;


import com.example.room.domain.request.RoomRequest;
import com.example.room.service.RoomService;
import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;



    @PostMapping("/apply")
    public void apply(@RequestBody List<RoomRequest> roomlist){

        roomService.apply(roomlist);




    }

}
