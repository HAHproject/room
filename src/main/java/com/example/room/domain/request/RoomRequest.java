package com.example.room.domain.request;

import com.example.room.domain.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomRequest {

    private String roomName;
    private String roomSize;
    private Integer price;
    private String img;
    private String imgName;
    private String imgType;
    private String accoId;


    public Room toEntity(String url){

        return Room.builder()
                .roomSize(roomSize)
                .imgName(imgName)
                .ImgUrl(url)
                .price(price)
                .accoId(accoId)
                .roomNAME(roomName)
                .build();

    }

}
