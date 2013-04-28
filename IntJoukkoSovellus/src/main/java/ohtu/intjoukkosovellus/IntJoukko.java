package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 

    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;          // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;     // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        olionAlustus(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        olionAlustus(kapasiteetti, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            return;
        }
        olionAlustus(kapasiteetti, kasvatuskoko);
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            lisaaTyhjaanTaulukkoon(luku);
        }
        if (!kuuluu(luku)) {
            lisaaPuolitayteenTaulukkoon(luku);
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        if (!kuuluu(luku)) {
            return false;
        }
        int kohta = luvunPoistoTaulukosta(luku);
        return kohdistaLuvutTaulukossa(kohta);

    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        System.arraycopy(vanha, 0, uusi, 0, vanha.length);
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        }
        return "{" + luvut() + "}";
    }

    private String luvut() {
        String luvut = "";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            luvut += ljono[i];
            luvut += ", ";
        }
        luvut += ljono[alkioidenLkm - 1];
        return luvut;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        System.arraycopy(ljono, 0, taulu, 0, taulu.length);
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        yhdiste = lisaaJoukkoon(a, yhdiste);
        yhdiste = lisaaJoukkoon(b, yhdiste);
        return yhdiste;
    }

    private static IntJoukko lisaaJoukkoon(IntJoukko lisattava, IntJoukko yhdiste) {
        int[] taulu = lisattava.toIntArray();
        for (int i = 0; i < taulu.length; i++) {
            yhdiste.lisaa(taulu[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        vahennys(aTaulu, erotus, bTaulu);
        return erotus;
    }

    private void olionAlustus(int kapasiteetti, int kasvatuskoko) {
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    private boolean lisaaTyhjaanTaulukkoon(int luku) {
        ljono[0] = luku;
        alkioidenLkm++;
        return true;
    }

    private void luoSuurempiTaulukko() {
        int[] taulukkoOld = new int[ljono.length];
        taulukkoOld = ljono;
        kopioiTaulukko(ljono, taulukkoOld);
        ljono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, ljono);
    }

    private boolean lisaaPuolitayteenTaulukkoon(int luku) {
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
        if (alkioidenLkm % ljono.length == 0) {
            luoSuurempiTaulukko();
        }
        return true;
    }

    private int luvunPoistoTaulukosta(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                ljono[i] = 0;
                alkioidenLkm--;
                return i;
            }
        }
        return -1;
    }

    private boolean kohdistaLuvutTaulukossa(int kohta) {
        if (kohta != -1) {
            for (int i = kohta; i < alkioidenLkm; i++) {
                int apu = ljono[i];
                ljono[i] = ljono[i + 1];
                ljono[i + 1] = apu;
            }
            return true;
        }
        return false;
    }
    
    private static void vahennys(int[] aTaulu, IntJoukko erotus, int[] bTaulu) {
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(i);
        }
    }
}