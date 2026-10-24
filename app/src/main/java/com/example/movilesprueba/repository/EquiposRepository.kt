package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Miembro

class EquiposRepository {

    fun getDireccion(): List<Miembro> {
        return listOf(
            Miembro("1", "José Luis Almozán", "Director CENAMAD", "Pontificia Universidad Católica de Chile"),
            Miembro("2", "Francisca Lorenzini", "Gerente General CIM-CENAMAD", "Pontificia Universidad Católica de Chile")
        )
    }

    fun getSubdirectores(): List<Miembro> {
        return listOf(
            Miembro("3", "Javiera De La Barra", "Subdirectora de Transferencia", "Pontificia Universidad Católica de Chile"),
            Miembro("4", "Raúl Araya", "Subdirector de Investigación", "Pontificia Universidad Católica de Chile"),
            Miembro("5", "Romina Rubio", "Subdirectora de Operaciones", "Pontificia Universidad Católica de Chile")
        )
    }

    fun getDirectorio(): List<Miembro> {
        return listOf(
            Miembro("6", "Juan Pablo Pereira", "Presidente del directorio", "CENAMAD"),
            Miembro("7", "José Luis Almozán", "Director", "CENAMAD"),
            Miembro("8", "Francisca Lorenzini", "Gerente General", "CENAMAD"),
            Miembro("9", "Mario Ubilla", "Facultad de Arquitectura, Diseño y Estudios Urbanos", "Universidad Católica"),
            Miembro("10", "Christian Ledezma", "Facultad de Ingeniería", "Universidad Católica"),
            Miembro("11", "Alejandra Engler", "Facultad de Agronomía y Sistemas Naturales", "Universidad Católica"),
            Miembro("12", "Pablo Maturana", "Escuela de Construcción Civil", "Universidad Católica"),
            Miembro("13", "Osvaldo Moreno", "Escuela de Arquitectura", "Universidad Católica"),
            Miembro("14", "Ramón Molina", "Director Ejecutivo Centro de Innovación", "Universidad Católica"),
            Miembro("15", "Francisco Figueroa", "Gerente Comercial", "Maderas Arauco"),
            Miembro("16", "Mario Yáñez", "Gerente de Ingeniería e Innovación", "Tecnopanel"),
            Miembro("17", "Rodrigo Besnier", "Gerente General", "Arxada Quimetal"),
            Miembro("18", "Gina Cerda", "Jefa Desarrollo & Innovación", "CMPC"),
            Miembro("19", "Enrique Mc-Manus", "Jefe Desarrollo Construcción en Madera", "Arauco"),
            Miembro("20", "Antonio Minte", "Gerente General", "CORMA"),
            Miembro("21", "Verónica Loewe", "Investigadora Principal", "INFOR"),
            Miembro("22", "Rafael Rubilar", "Investigador Principal", "Universidad de Concepción"),
            Miembro("23", "Andrés Fuentes", "Investigador Principal", "Universidad de La Frontera"),
            Miembro("24", "Roberto Pizarro", "Investigador Principal", "Universidad de Talca"),
            Miembro("25", "Mario Núñez", "Investigador Principal", "Universidad del Bío-Bío")
        )
    }
}
