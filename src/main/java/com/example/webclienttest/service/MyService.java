package com.example.webclienttest.service;


import com.example.webclienttest.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MyService {

    private final WebClient webClient;

    @Autowired
    public MyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> fetchHello() {
        Mono<String> hello = webClient
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToMono(String.class);
        return hello;
    }

    public Mono<List<Student>> fetchStudents() {
        Mono<List<Student>> students = webClient
                .get()
                .uri("/Students")
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Student>>() {});
        return students;
    }


}
