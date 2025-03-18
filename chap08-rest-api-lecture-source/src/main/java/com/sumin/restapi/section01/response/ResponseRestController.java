package com.sumin.restapi.section01.response;

import jakarta.annotation.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/* 설명. @RestController 어노테이션을 통해 Controller의 모든 핸들러 메소드에 @ResponseBody 를 적용한 효과 */
/* 설명. 이제 더이상 view resolver가 처리하지 않는다 (어떤 반환 값이든 반환 됨) */
@RestController
@RequestMapping("/response")
public class ResponseRestController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/random")
    public int getRandomNumber() {
        return (int) (Math.random() * 10) + 1;
    }

    @GetMapping("message")
    public Message getMessage() {
        return new Message(200, "메세지를 응답합니다.");
    }

    @GetMapping("test")
    public List<Map<String, Object>> getTest() {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("test1", new Message(200, "성공1"));
        map.put("test2", new Message(200, "성공2"));
        map.put("nextPageLink", "https://localhost:8080/hello");
        list.add(map);

        return list;
    }

    @GetMapping("/map")
    public Map<Integer, String> getMapping() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(200, "정상응답"));
        messageList.add(new Message(404, "페이지를 찾을 수 없습니다"));
        messageList.add(new Message(500, "개발자의 잘못입니다"));

        /* 설명. List -> Map 으로 바꿔 JSON Object 형태로 응답하기 위한 stream 적용 */
        return messageList.stream()
                .collect(Collectors.toMap(Message::getHttpStatusCode, Message::getMessage));

    }

    /* 설명. 이미지 응답하기 */
    @GetMapping(value= "/image/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String filename) throws IOException {
        Path path = Paths.get("/Users/sumin/Downloads/test.jpg", filename);
        UrlResource resource = new UrlResource(path.toUri());

        byte[] imageBytes = resource.getInputStream().readAllBytes();

        return imageBytes;
    }

}
