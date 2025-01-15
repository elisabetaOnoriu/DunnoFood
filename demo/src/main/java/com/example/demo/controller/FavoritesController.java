package com.example.demo.controller;

import com.example.demo.entity.Favorite;
import com.example.demo.repository.FavoriteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    private final FavoriteRepository favoriteRepository;

    public FavoritesController(FavoriteRepository favoriteRepository) {
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping
    public ResponseEntity<List<Favorite>> getFavorites(@RequestParam String username) {
        return ResponseEntity.ok(favoriteRepository.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<String> addFavorite(@RequestBody Favorite favorite) {
        favoriteRepository.save(favorite);
        return ResponseEntity.status(HttpStatus.CREATED).body("Rețeta a fost adăugată la favorite!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFavorite(@PathVariable Long id) {
        if (favoriteRepository.existsById(id)) {
            favoriteRepository.deleteById(id);
            return ResponseEntity.ok("Rețeta a fost eliminată din favorite!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rețeta nu a fost găsită!");
        }
    }
}
