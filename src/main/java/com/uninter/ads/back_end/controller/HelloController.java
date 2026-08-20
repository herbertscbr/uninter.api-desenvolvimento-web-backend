package com.uninter.ads.back_end.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @GetMapping("/")
    public String hello() {
        return "Bem-vindo ao Back-End!";
    }
    
    @GetMapping("/teste")
    public String teste() {
        return "Teste funcionando!";
    }
}