package com.example.Inventario.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.Inventario.model.Inventario;
import com.example.Inventario.repository.InventarioRepository;

public class InventarioServiceTest {
    @Mock
    private InventarioRepository inventarioRepository;

    @InjectMocks
    private InventarioService inventarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGuardarProducto() {
        Inventario inventario = new Inventario(0, "Mando", 12.5, 8);
        Inventario productoGuardado = new Inventario(1, "Mando", 12.5, 8);
        when(inventarioRepository.save(inventario)).thenReturn(productoGuardado);
    
        Inventario resultado = inventarioService.guardarProductos(inventario);
        assertThat(resultado.getId()).isEqualTo(1);
        assertThat(resultado.getNombre()).isEqualTo("Mando");
        assertThat(resultado.getPrecio()).isEqualTo(12.5);
        verify(inventarioRepository).save(inventario);
    }

    @Test
    void testMostrarProductos() {
        Inventario inventario = new Inventario(1, "Mando", 13.5, 7);
        when(inventarioRepository.findAll()).thenReturn(List.of(inventario));

        List<Inventario> resultado = inventarioService.listarProductos();
        assertThat(resultado).hasSize(1);
        verify(inventarioRepository).findAll();
    }

    @Test
    void testBuscadorId() {
        Inventario inventario = new Inventario(1, "Mando", 13.5, 7);
        when(inventarioRepository.findById(1)).thenReturn(java.util.Optional.of(inventario));

        java.util.Optional<Inventario> resultado = inventarioService.buscarxId(1);
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNombre()).isEqualTo("Mando");
        verify(inventarioRepository).findById(1);
    }
    
    @Test
    void testModificarProducto() {
        Inventario inventario = new Inventario(1, "Mando", 13.5, 7);
        Inventario productoModificado = new Inventario(1, "Mando Modificado" , 15.0, 5);
        when(inventarioRepository.findById(1)).thenReturn(java.util.Optional.of(inventario));
        when(inventarioRepository.save(inventario)).thenReturn(productoModificado);

        Inventario resultado = inventarioService.modificarProducto(1, productoModificado);
        assertThat(resultado.getNombre()).isEqualTo("Mando Modificado");
        verify(inventarioRepository).findById(1);
        verify(inventarioRepository).save(inventario);
    }

    @Test
    void testEliminarProducto() {
        int id = 1;
        inventarioService.eliminarProducto(id);
        verify(inventarioRepository).deleteById(id);
    }

}
 