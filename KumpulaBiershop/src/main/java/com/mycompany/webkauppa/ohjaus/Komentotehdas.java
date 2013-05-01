package com.mycompany.webkauppa.ohjaus;

import com.mycompany.webkauppa.sovelluslogiikka.Ostoskori;

public class Komentotehdas {
    
    Komento komento;
    
    public Komentotehdas() {
    }
    
    public OstoksenLisaysKoriin ostoksenLisaysKoriin(Ostoskori ostoskori, long tuoteId) {
        komento = new OstoksenLisaysKoriin(ostoskori, tuoteId);
        return (OstoksenLisaysKoriin)komento;
    }
    
    public OstoksenPoistoKorista ostoksenPoistoKorista(Ostoskori ostoskori, long tuoteId) {
        komento = new OstoksenPoistoKorista(ostoskori, tuoteId);
        return (OstoksenPoistoKorista)komento; 
    }
    
    public OstoksenSuoritus ostoksenSuoritus(String nimi, String osoite, String luottokorttinumero, Ostoskori kori) {
        komento = new OstoksenSuoritus(nimi, osoite, luottokorttinumero, kori);
        return (OstoksenSuoritus)komento;
    }
    
}
