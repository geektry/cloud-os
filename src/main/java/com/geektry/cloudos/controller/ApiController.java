package com.geektry.cloudos.controller;

import com.geektry.cloudos.entity.Node;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ApiController {

    @GetMapping("/api/child_nodes")
    public Mono<List<Node>> listChildNodes(@RequestParam("path") String path) throws IOException {

        return Flux.fromStream(Files.list(Paths.get(path)))
                .map(p -> new Node(
                        Files.isDirectory(p) ? Node.Type.DIR : Node.Type.FILE,
                        p.toString(),
                        p.getFileName().toString()))
                .sort((n1, n2) -> {
                    if (n1.getType().equals(n2.getType())) {
                        return 0;
                    }
                    return n1.getType().equals(Node.Type.DIR) ? -1 : 1;
                })
                .collectList();
    }

    @GetMapping("/api/file")
    public Mono<List<String>> listFileLines(@RequestParam("path") String path) throws IOException {

        return Flux.fromStream(Files.lines(Paths.get(path)))
                .collectList();
    }
}
