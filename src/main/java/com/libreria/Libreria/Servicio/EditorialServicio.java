
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Servicio;


import com.libreria.Libreria.Entidades.Editorial;
import com.libreria.Libreria.Excepciones.ExcepcionLibreria;

import com.libreria.Libreria.Repository.EditorialRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {
    @Autowired
    private EditorialRepo er;
    
    public void crearEditorial(String nombre) throws ExcepcionLibreria{

            if(nombre.isEmpty()|| nombre == null){
                throw new ExcepcionLibreria("Tenes que ingresar los datos pedidos salamin/a");
            }
            Editorial editorial = new Editorial();
            editorial.setNombre(nombre);
            editorial.setAlta(true);
            er.save(editorial);
    }
    
   
}
