package com.example.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Inventario.model.Inventario;
import com.example.Inventario.service.InventarioService;

@RequestMapping("/api/inventario")

@RestController
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @PostMapping("/guardar")
    public ResponseEntity<Inventario> guardarProductos(@RequestBody Inventario inventario) {
        Inventario nuInventario = inventarioService.guardarProductos(inventario);
        return ResponseEntity.ok(nuInventario);
    }

    @GetMapping
    public ResponseEntity<List<Inventario>> listarProductos() {
        List<Inventario> inventarios = inventarioService.listarProductos();
        return ResponseEntity.ok(inventarios);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Inventario> buscarxId(@PathVariable int id) {
        return inventarioService.buscarxId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<Inventario> modificarProducto(@PathVariable int id, @RequestBody Inventario producto) {
        Inventario updatedInventario = inventarioService.modificarProducto(id, producto);
        if (updatedInventario != null) {
            return ResponseEntity.ok(updatedInventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        inventarioService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
