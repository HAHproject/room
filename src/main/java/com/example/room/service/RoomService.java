package com.example.room.service;


import com.example.room.domain.entity.Room;
import com.example.room.domain.repository.RoomRepository;
import com.example.room.domain.request.RoomRequest;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    @Value("${app.firebase-bucket}")
    private String firebaseBucket;

    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;




    @Transactional
    public void apply(List<RoomRequest> roomRequestList){


        for (RoomRequest request:
             roomRequestList) {

           String imgUrl = uploadImg(request);

           roomRepository.save(request.toEntity(imgUrl));

        }

    }



    private String uploadImg(RoomRequest request){

        Bucket bucket = StorageClient.getInstance().bucket(firebaseBucket);

        String data = request.getImg().split(",")[1];

        byte[] bytes = Base64.decodeBase64(data);

        InputStream content = new ByteArrayInputStream(bytes);

        Blob blob = bucket.create("images/" + request.getImgName(), content,request.getImgType());
        // Blob 객체의 공개 URL 생성
        String publicUrl = blob.getMediaLink();

        return publicUrl;

    }
}
