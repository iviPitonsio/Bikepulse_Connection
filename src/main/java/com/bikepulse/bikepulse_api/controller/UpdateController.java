package com.bikepulse.bikepulse_api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikepulse.bikepulse_api.DataUpdater;

@RestController
@RequestMapping("/admin")
public class UpdateController {

    private final DataUpdater dataUpdater;

    @Value("${UPDATE_TOKEN}")
    private String updateToken;

    public UpdateController(DataUpdater dataUpdater) {
        this.dataUpdater = dataUpdater;
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(
            @RequestHeader("X-Update-Token") String token) {

        if (!updateToken.equals(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token incorrecto");
        }

        dataUpdater.updateData();

        return ResponseEntity.ok("Datos actualizados");
    }
}
