/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.libreria.Libreria.Repository;

import com.libreria.Libreria.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends JpaRepository<Cliente, String>{
    
     @Query("SELECT c FROM Cliente c WHERE c.mail = :mail")
    public Cliente buscarPorMail(@Param("mail") String mail);
    
}
