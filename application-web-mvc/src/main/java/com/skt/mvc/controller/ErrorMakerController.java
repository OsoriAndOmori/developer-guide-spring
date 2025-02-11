package com.skt.mvc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ErrorMakerController {
    private List<byte[]> memoryLeaks = new ArrayList<>();


    @GetMapping("/heap-full")
    public String fillHeap() {
        boolean b = true;
        while (b) {
            memoryLeaks.add(new byte[10 * 1024 * 1024]); // 10MB 객체 추가
        }
        return "Heap Full 발생: ";
    }

    @GetMapping("/heap-clear")
    public String clear() {
        memoryLeaks = new ArrayList<>();
        return "Heap Cleared";
    }

    @GetMapping("/stack-overflow")
    public String triggerStackOverflow() {
        return recursiveCall(0);
    }

    private String recursiveCall(int depth) {
        System.out.println(depth);
        return recursiveCall(depth + 1); // 무한 재귀 호출
    }
}
