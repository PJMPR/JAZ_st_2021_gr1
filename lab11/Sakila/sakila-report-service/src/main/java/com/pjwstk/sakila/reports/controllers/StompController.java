package com.pjwstk.sakila.reports.controllers;

import com.pjwstk.sakila.websockets.contract.SakilaWebsocketConnectionProvider;
import com.pjwstk.sakila.websockets.contract.dto.MyMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("stomp")
@RequiredArgsConstructor
public class StompController {

    private final SakilaWebsocketConnectionProvider provider;

    @GetMapping
    public ResponseEntity sendMessage() throws ExecutionException, InterruptedException {
        provider.getSession().send("websocket/chat", new MyMessage("stomp", "message"));
        return ResponseEntity.ok("dzialam");
    }
}
