package com.study.backendeum.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lostitem")
public class LostItem_controller {
    @PostMapping("/post")
    public void Post() {

    }

    @GetMapping("/view")
    public String View() {
        return "";
    }

    @GetMapping("/remove")
    public void Remove() {

    }
}