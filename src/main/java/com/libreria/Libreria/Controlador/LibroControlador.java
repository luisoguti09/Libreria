/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Controlador;

import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Servicio.LibroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registroLibro(ModelMap m, @RequestParam Long isbn,
            @RequestParam String titulo, @RequestParam Integer anio,
            @RequestParam Integer ejemplares,
            @RequestParam Integer ejemplaresPrestados,
            @RequestParam Integer ejemplaresRestantes,
            @RequestParam String autor, @RequestParam String editorial) {

        try {

            servL.crearLibro(isbn, titulo, anio, ejemplares,
                    ejemplaresPrestados, ejemplaresRestantes,
                    autor, editorial);
            return "index.html";
        } catch (ExcepcionLibreria ex) {
            m.put("error", ex.getMessage());
            m.put("isbn", isbn);
            m.put("titulo", titulo);
            m.put("anio", anio);
            m.put("ejemplares", ejemplares);
            m.put("ejemplaresPrestados", ejemplaresPrestados);
            m.put("ejemplaresRestantes", ejemplaresRestantes);
            m.put("autor", autor);
            m.put("editorial", editorial);
            return "registroLibro.html";
        }

    }

}
