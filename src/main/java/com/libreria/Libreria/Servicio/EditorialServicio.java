/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Servicio;

import com.libreria.Libreria.Entidades.Editorial;
import com.libreria.Libreria.Excepciones.ExcepcionLibreria;
import com.libreria.Libreria.Repository.EditorialRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


public class EditorialServicio {
    
    @Autowired
    private EditorialRepo edit;
    
    public void crearEditorial(String nombre) throws ExcepcionLibreria{
       if(nombre.isEmpty()|| nombre == null){
                throw new ExcepcionLibreria("Tenes que ingresar los dats pedidos salamin/a");
            } 
       Editorial editor = new Editorial();
       editor.setNombre(nombre);
       editor.setAlta(true);
       edit.save(editor);
    }
    public void modificarEditorial(String nombre, String Id) throws ExcepcionLibreria{
      
        if(nombre.isEmpty()|| nombre == null){
                throw new ExcepcionLibreria("Tenes que ingresar los datos pedidos salamin/a");
            }
        
        Optional<Editorial> respuesta = edit.findById(Id);
        
        if(respuesta.isPresent()){
            Editorial edi = respuesta.get();
            edi.setNombre(nombre);
            edit.save(edi);
        }else{
            throw new ExcepcionLibreria("No existe la Editorial ingresada");
        }
    }
    
    public void eliminarEditorial(String Id) throws ExcepcionLibreria{
        
        Optional<Editorial> respuesta = edit.findById(Id);
        if(respuesta.isPresent()){
            Editorial edi = respuesta.get();
            edi.setAlta(false);
            edit.save(edi);
        }else{
            throw new ExcepcionLibreria("No existe la Editorial ingresada");
        }
    }
}
