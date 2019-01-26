package com.geektry.cloudos.controller;

import com.geektry.cloudos.entity.DirectoryFile;
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

    @GetMapping("/api/subdirectories")
    public Mono<List<DirectoryFile>> listSubdirectories(@RequestParam("directory") String directory) throws IOException {

        return Flux.fromStream(Files.list(Paths.get(directory)))
                .map(path -> new DirectoryFile(
                        Files.isDirectory(path) ? DirectoryFile.Type.DIR : DirectoryFile.Type.FILE,
                        path.toString(),
                        path.getName(path.getNameCount() - 1).toString()))
                .collectList();
    }
}
