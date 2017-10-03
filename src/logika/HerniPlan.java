
package logika;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2014/2015
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    private Prostor viteznyProstor;
    private Map<String, Prostor> prostoryCollection;
    private Map<String, Prostor> learntProstoryCollection;
   private boolean kufr = false;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        prostoryCollection = new HashMap<String, Prostor>(); 
        learntProstoryCollection = new HashMap<String, Prostor>(); 
        Prostor czech_republic = new Prostor("Česko","Česko, jste prijeli do srdce evropy a tady začína vač příbeh");
        prostoryCollection.put("Česko", czech_republic);
        Prostor germany = new Prostor("Něměcko", "Něměcko");
        prostoryCollection.put("Něměcko", germany);
        Prostor poland = new Prostor("Polsko","Polsko");
        prostoryCollection.put("Polsko", poland);
        Prostor slovakia = new Prostor("Slovensko", "Slovensko");
        prostoryCollection.put("Slovensko", slovakia);
        Prostor france = new Prostor("France","France");
        prostoryCollection.put("France", france);
        Prostor austria = new Prostor("Rakousko","Rakousko");
        prostoryCollection.put("Rakousko", austria);
        Prostor italy = new Prostor("Italia","Italia");
        prostoryCollection.put("Italia", italy);
        Prostor switzerland = new Prostor("Švýcarsko","Švýcarsko");
        prostoryCollection.put("Švýcarsko", switzerland);
        Prostor slovenia = new Prostor("Slovinsko","Slovinsko");
        prostoryCollection.put("Slovinsko", slovenia);
        Prostor hungary = new Prostor("Maďarsko","Maďarsko");
        prostoryCollection.put("Maďarsko", hungary);
        Prostor belgium = new Prostor("Belgie","Belgie");
        prostoryCollection.put("Belgie", belgium);

        // přiřazují se průchody mezi prostory (sousedící prostory)
        czech_republic.setVychod(slovakia);
        czech_republic.setVychod(poland);
        czech_republic.setVychod(germany);
        czech_republic.setVychod(austria);

        austria.setVychod(czech_republic);
        austria.setVychod(italy);
        austria.setVychod(germany);
        austria.setVychod(slovenia);
        austria.setVychod(slovakia);
        austria.setVychod(hungary);
        austria.setVychod(switzerland);

        switzerland.setVychod(france);
        switzerland.setVychod(italy);
        switzerland.setVychod(germany);
        switzerland.setVychod(austria);

        germany.setVychod(czech_republic);
        germany.setVychod(austria);
        germany.setVychod(switzerland);
        germany.setVychod(france);
        germany.setVychod(belgium);
        germany.setVychod(poland);

        slovakia.setVychod(czech_republic);
        slovakia.setVychod(hungary);
        slovakia.setVychod(austria);
        slovakia.setVychod(poland);

        france.setVychod(belgium);
        france.setVychod(switzerland);
        france.setVychod(italy);
        france.setVychod(germany);

        belgium.setVychod(france);
        belgium.setVychod(germany);

        poland.setVychod(slovakia);
        poland.setVychod(czech_republic);
        poland.setVychod(germany);

        hungary.setVychod(slovakia);
        hungary.setVychod(austria);
        hungary.setVychod(slovenia);

        slovenia.setVychod(hungary);
        slovenia.setVychod(austria);
        slovenia.setVychod(italy);

        italy.setVychod(slovenia);
        italy.setVychod(austria);
        italy.setVychod(switzerland);
        italy.setVychod(france);

        poland.setVychod(germany);
        poland.setVychod(czech_republic);
        poland.setVychod(slovakia);

        czech_republic.vlozVec(new Vec("Nůž", true));
        poland.vlozVec(new Vec("Pistole", true));
        poland.vlozVec(new Vec("Klíče", true));

        Vec kufr = new Vec("kufr", false);
        germany.vlozVec(kufr);

        aktualniProstor = czech_republic;  // hra začíná 

        // Random stat ve kterem hledany špion
        Prostor [] viteznyStat = {germany, poland, slovakia, france, austria, italy, switzerland, slovenia};
        int idx = new Random().nextInt(viteznyStat.length);
        Prostor random = (viteznyStat[idx]);

        viteznyProstor = random;

    }

    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     *  Metoda vrací vitezny prostor, ve ktetém se hráč právě nachází.
     *
     *@return aktuální vitezny prostor
     */

    public boolean vitezstvi(){
        return aktualniProstor.equals(viteznyProstor);
    }

    /**
     *  Metoda vrací odevzdal li hrač zbraň v kufr.
     *@return odevzdal zbraň nebo ne
     */

    public boolean kufr(){
        return kufr;
    }

    /**
     *  Metoda kollekce prostoru, které jsme vytvořeli v zalozProstoryHry.
     *@return kollekce prostoru
     */

    public Map<String, Prostor> getProstoryCollection() {
        return prostoryCollection;
    }

       
   /**
     * Set metoda zadavame stát v seznam, pokud ho jazyk víme.
     */
    public void setLearntProstoryCollection(Map<String, Prostor> learntProstoryCollection) {
        this.learntProstoryCollection = learntProstoryCollection;
    }

    
     /**
     * Seznam státu, kterých jazyk už vime.
     *@return seznam státu, kterých jazyk už vime
     */
    public Map<String, Prostor> getLearntProstoryCollection() {
        return learntProstoryCollection;
    }
    
    
   /**
     * Set metoda zadavame odevzdal li hrač zbraň v kufr.
     */
      public void setKufr(boolean stav) {
        this.kufr = stav;
    }
}
