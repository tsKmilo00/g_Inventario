package com.example.Inventario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Inventario postPersona(@RequestBody Inventario inventario) {
        for (Inventario id : inventarioService.listarProductos()) {
            if (id.getNombre().equalsIgnoreCase(inventario.getNombre())) {
                return null; // Producto ya existe
            }
        }
        return inventarioService.guardarProductos(inventario);
    }

    @GetMapping
    public List<Inventario> getProductos() {
        return inventarioService.listarProductos();
    }

    @GetMapping("/buscar/{id}")
    public Inventario getProducto(@PathVariable int id) {
        return inventarioService.buscarxId(id);
    }

    @GetMapping("/buscar/{nombre}")
    public Inventario getProductoNombre(@PathVariable String nombre) {
        return inventarioService.buscaxNombre(nombre);
    }

    @PutMapping("/modificar/{id}")
    public Inventario putProducto(@PathVariable int id, @RequestBody Inventario producto) {
        return inventarioService.modificarProducto(id, producto);
    }

    @PostMapping("/eliminar/{id}")
    public String deleteProducto(@PathVariable int id) {
        return inventarioService.eliminarProducto(id);
    }
}
