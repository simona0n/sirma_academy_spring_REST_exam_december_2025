package com.sirma.santa.s_workshop.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping
    public Map<String, Object> getApiInfo() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("appName", "Santa's Workshop API");
        response.put("version", "1.0");
        response.put("currentServerTime", LocalDateTime.now());
        response.put("availableResources", List.of("gifts", "elves", "deliveries"));
        return response;
    }
}