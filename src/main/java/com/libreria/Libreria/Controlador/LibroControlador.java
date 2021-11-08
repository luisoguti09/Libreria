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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    @Autowired
    private LibroServicio servL; 
      @GetMapping("/registro")
    public String registroLibro() {
        return "registroLibro.html";
    }       
    @PostMapping("/registro")
    public String registroLibro(ModelMap m,@RequestParam Long isbn,@RequestParam String titulo,@RequestParam Integer anio,

            @RequestParam Integer ejemplares,@RequestParam Integer ejemplaresPrestados,
            @RequestParam Integer ejemplaresRestantes, @RequestParam String idAutor,@RequestParam String idEditorial){
        
        try {
            servL.crearLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, idAutor, idEditorial);
            return "index.html";
        } catch (ExcepcionLibreria ex) {
            m.put("error", ex.getMessage());
            return "registroLibro.html";
        }
        
    }
    
}
