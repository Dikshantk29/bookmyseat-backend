package com.dikshant.bookmyseat.Controller;


import com.dikshant.bookmyseat.dto.ScreenRequest;
import com.dikshant.bookmyseat.entity.Screen;
import com.dikshant.bookmyseat.service.ScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/screens")
@RequiredArgsConstructor
public class ScreenController {
    private final ScreenService screenService;

    // Add screen
    @PostMapping
    public ResponseEntity<Screen> addScreen(@RequestBody ScreenRequest screenRequest) {
        return ResponseEntity.ok(screenService.addScreen(screenRequest));
    }

    // Get all screens
    @GetMapping
    public ResponseEntity<List<Screen>> getAllScreens() {
        return ResponseEntity.ok(screenService.getAllScreens());
    }

    // Get screen by id
    @GetMapping("/{id}")
    public ResponseEntity<Screen> getScreenById(@PathVariable Long id) {
        return ResponseEntity.ok(screenService.getScreenById(id));
    }

    // Get screens by theater id
    @GetMapping("/theater/{theaterId}")
    public ResponseEntity<List<Screen>> getScreensByTheater(@PathVariable Long theaterId) {
        return ResponseEntity.ok(screenService.getScreensByTheater(theaterId));
    }
}

