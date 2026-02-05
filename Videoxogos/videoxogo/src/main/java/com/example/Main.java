package com.example;


import com.example.Controller.VideoxogoController;
import com.example.Model.Videoxogo;
import com.example.View.VistaConsola;


public class Main {

    public static void main(String[] args) {
        VideoxogoController controller = new VideoxogoController();
        VistaConsola vista = new VistaConsola();
        controller.addVideoxogo(new Videoxogo("Mario", "SpaceInvaders", 1200, 15, 3));
        // vista.consulta1();
    }

}