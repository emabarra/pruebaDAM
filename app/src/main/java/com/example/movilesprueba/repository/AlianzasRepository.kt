package com.example.movilesprueba.repository

import com.example.movilesprueba.model.Alianza

class AlianzasRepository {

    fun getAsociadosAcademia(): List<Alianza> {
        return listOf(
            Alianza("1", "Centro UC de Innovación en Madera", null, "Asociados", "Academia"),
            Alianza("2", "Universidad de Concepción", null, "Asociados", "Academia"),
            Alianza("3", "Universidad de Talca", null, "Asociados", "Academia"),
            Alianza("4", "Universidad del Bío-Bío", null, "Asociados", "Academia"),
            Alianza("5", "Universidad de La Frontera", null, "Asociados", "Academia"),
            Alianza("6", "Unidad de Desarrollo Técnico", "UDT", "Asociados", "Academia")
        )
    }

    fun getAsociadosSectorPublico(): List<Alianza> {
        return listOf(
            Alianza("7", "Instituto Forestal", "INFOR", "Asociados", "Sector Público")
        )
    }

    fun getAportantesSectorPublico(): List<Alianza> {
        return listOf(
            Alianza("8", "Agencia Nacional de Investigación y Desarrollo", "ANID", "Aportantes", "Sector Público")
        )
    }

    fun getAportantesSectorPrivado(): List<Alianza> {
        return listOf(
            Alianza("9", "Corporación Chilena de la Madera", "CORMA", "Aportantes", "Sector privado"),
            Alianza("10", "Arauco", null, "Aportantes", "Sector privado"),
            Alianza("11", "CMPC", null, "Aportantes", "Sector privado"),
            Alianza("12", "Aserraderos JCE", null, "Aportantes", "Sector privado"),
            Alianza("13", "Simpson Strong-Tie", null, "Aportantes", "Sector privado"),
            Alianza("14", "Protección de Madera Ltda.", "by Arxada Quimetal", "Aportantes", "Sector privado"),
            Alianza("15", "TecnoPanel", null, "Aportantes", "Sector privado"),
            Alianza("16", "Mamut", "www.fijaciones.com", "Aportantes", "Sector privado")
        )
    }
}
