package com.example;


import com.example.Controller.VideoxogoController;
import com.example.Model.Videoxogo;
import com.example.View.VistaConsola;


public class Main {

    public static void main(String[] args) {
        VideoxogoController controller = new VideoxogoController();
        VistaConsola vista = new VistaConsola();
        // controller.addVideoxogo(new Videoxogo("Mario", "SpaceInvaders", 1200, 15, 3));
        // controller.addVideoxogo(new Videoxogo("Luigi", "AlphaSniper", 1100, 15, 3));
        // controller.addVideoxogo(new Videoxogo("Mario", "SpaceInvaders", 2, 15, 3));
        // controller.addVideoxogo(new Videoxogo("Luigi", "AlphaSniper", 1100, 5, 4));
        // controller.addVideoxogo(new Videoxogo("Manolo", "SpaceInvaders", 200, 4, 30));
        vista.consulta1();
        vista.consulta2();
        vista.consulta3();
        vista.consulta4();
        vista.consulta5();
        vista.consulta6();
    }

}