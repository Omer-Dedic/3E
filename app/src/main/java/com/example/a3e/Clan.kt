package com.example.a3e

import java.util.Date

class Clan {
    var idClan: Int = 0
    var ime: String
    var prezime: String
    var datumRodjenja: String
    var kategorija: String
    var napomena: String

    constructor(idClan: Int, ime: String, prezime: String, datumRodjenja: String, kategorija: String, napomena: String){
        this.idClan = idClan
        this.ime = ime
        this.prezime = prezime
        this.datumRodjenja = datumRodjenja
        this.kategorija = kategorija
        this.napomena = napomena
    }
}