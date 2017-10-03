package logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2014/2015
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;
    private Batoh batoh;
    private int brilliantsCount = 0; //počet diamantu na začatku hry
 
    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        batoh = new Batoh();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        PrikazJdi prikazJdi = new PrikazJdi(herniPlan);
        prikazJdi.setHra(this);
        platnePrikazy.vlozPrikaz(prikazJdi);
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazLearn(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazTakeBrilliant(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan,batoh));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan,batoh));
        Prostor.hra = this;
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return "Vítejte!\n" +
        "To je špionský příběh o dvou amerických špionech.\n" +
        "Pár let zpátky jeden z nich se ztratil při plněni tajné mise někde v Evropě.\n" +
        "A jeho starý kamarád (Vy) po práce, jakmile se dozvěděl o tom,\n" +
        "okamžitě začal ho hledat. Musíte nejdřiv najit Nůž, Pistole a Klíče a dát to do Kufru\n" +
        "v Něměcku, a už pak hledát kamarada v různých státech.\n" +
        "\n" + herniPlan.getAktualniProstor().dlouhyPopis();
    }

    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Dík, že jste si zahráli.  Ahoj.";
    }

    /** 
     * Vrací true, pokud hra skončila.
     */
    public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
    public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
            parametry[i]= slova[i+1];   
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.proved(parametry);
            if (herniPlan.vitezstvi()){
                konecHry = true;
                textKVypsani+= " vyhra!!!";

            }}
            else {
                textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
            }
            return textKVypsani;
        
    }

    /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }

    /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
    public HerniPlan getHerniPlan(){
        return herniPlan;
    }
    
      /**
     *  Metoda vrátí počet brilliantů u hrača.
     *  
     *  @return  počet brilliantu u hrača
     */
    
    public int getBrilliantsCount() {
        return brilliantsCount;
    }
    
    
   /**
     *  Metoda pomici ni můžeme zadat počet brilliantů u hrača.
     *  
     *  @return     počet brilliantu u hrača
     */
    public void setBrilliantsCount(int brilliantsCount) {
        this.brilliantsCount = brilliantsCount;
    }

}

