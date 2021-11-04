/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Controlador;

import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Servicio.LibroServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LibroControlador {
    @Autowired
    private LibroServicio servL; 
            
    @PostMapping("/registroLibro")
    public String registroLibro(@RequestParam Long isbn,@RequestParam String titulo,@RequestParam Integer anio,
            @RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,
            @RequestParam Integer ejemplaresRestantes, @RequestParam String idAutor,@RequestParam String idEditorial){
        
        try {
            servL.crearLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
        } catch (ExcepcionLibreria ex) {
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
            return "index.html";
        }
        return "registroLibro.html";
    }
    
}
