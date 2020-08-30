/**
 * @Author:Otosun Tarih :14/07/2020
 */
package AileYapisiEncapsulation;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Insan {

    private final int id;
    private String name;
    private String surName;
    private LocalDate birdhDate;
    private char cinsiyet;
    private Insan baba;
    private Insan anne;
    private Insan es;
    private boolean evli = false;
    private ArrayList<Insan> cocuklar;

    static int sayac = 1001;
    static LocalDate buGun = LocalDate.now();

    static Set<Insan> insanlar = new HashSet<>();

    final int EVLILIK_YASI = 18;

    public Insan(String name, String surName, int Gun, int Ay, int Yil, char cinsiyet, Insan baba, Insan anne) {
        setName(name);
        setSurName(surName);
        setBirdhDate(LocalDate.of(Yil, Ay, Gun));
        setCinsiyet(cinsiyet);
        setBaba(baba);
        setAnne(anne);
        this.id = sayac++;
        insanlar.add(this);

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Utility.adDuzenle(name);

    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = Utility.soyadDuzenle(surName);
    }

    public LocalDate getBirdhDate() {
        return birdhDate;
    }

    public void setBirdhDate(LocalDate birdhDate) {
        this.birdhDate = birdhDate;
    }

    public void setBirdhDate(int gun, int ay, int yil) {
        LocalDate birdhDate = LocalDate.of(yil, ay, gun);
        this.birdhDate = birdhDate;
    }

    public char getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(char cinsiyet) {
        if (cinsiyet == 'K' || cinsiyet == 'k')
            this.cinsiyet = 'K';
        else if (cinsiyet == 'E' || cinsiyet == 'e')
            this.cinsiyet = 'E';
        else System.out.println("Hatali cinsiyet girisi");
    }

    public Insan getBaba() {
        return baba;
    }

    public void setBaba(Insan baba) {
        this.baba = baba;
    }

    public Insan getAnne() {
        return anne;
    }

    public void setAnne(Insan anne) {
        this.anne = anne;
    }

    public Insan getEs() {
        return es;
    }

    public void setEs(Insan es) {
        this.es = es;
    }

    public boolean isEvli() {
        return evli;
    }

    public void setEvli(boolean evli) {
        this.evli = evli;
    }

    public ArrayList<Insan> getCocuklar() {
        return cocuklar;
    }

    public void setCocuklar(ArrayList<Insan> cocuklar) {
        this.cocuklar = cocuklar;
    }


    public Insan(String name, String surName, int Gun, int Ay, int Yil, char cinsiyet) {
        this(name, surName, Gun, Ay, Yil, cinsiyet, null, null);

    }

    public Insan(String name, int Gun, int Ay, int Yil, char cinsiyet) {
        this(name, "", Gun, Ay, Yil, cinsiyet, null, null);

    }

    public Insan(String name, char cinsiyet) {
        this(name, "", buGun.getDayOfMonth(), buGun.getMonthValue(), buGun.getYear(), cinsiyet, null, null);
    }


    int getYas() {
        return Period.between(this.birdhDate, buGun).getYears();
    }

    void evlen(Insan es) {
        if (this.getYas() > EVLILIK_YASI && !evli) {
            if (cinsiyet != es.cinsiyet) {
                if (es.getYas() > EVLILIK_YASI && !es.evli) {
                    this.evli = true;
                    es.evli = true;
                    es.es = this;
                    this.es = es;
                    this.cocuklar = new ArrayList<>();
                    es.cocuklar = new ArrayList<>();
                    if (cinsiyet == 'K') {
                        surName = es.surName;
                    } else {
                        es.surName = surName;
                    }
                    System.out.println(name + " ile " + es.name + " evlenmistir. Mutluluklar dileriz.");
                } else System.out.println(es.name + "'in medeni durumu veya yasi evlenmek icin uygun degil!");
            } else System.out.println("Cinsiyetler evlilik icin uygun degil!");
        } else System.out.println(name + "'in medeni durumu veya yasi evlenmek icin uygun degil!");
    }

    public Insan cocukYap(String name, int Gun, int Ay, int Yil, char cinsiyet) {
        if (evli) {
            Insan cocuk = new Insan(name, Gun, Ay, Yil, cinsiyet);
            cocuk.anne = this;
            cocuk.baba = this.es;
            cocuk.surName = cocuk.baba.surName;
            this.cocuklar.add(cocuk);
            es.cocuklar.add(cocuk);
            DateTimeFormatter format1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            System.out.println(name + " " + surName + " " + cocuk.birdhDate.format(format1) + " tarihinde dunyaya geldi.");
            return cocuk;
        } else
            System.out.println("Anne evli degildir");
        return null;
    }

    public Insan cocukYap(String name, char cinsiyet) {
        if (evli) {
            Insan cocuk = new Insan(name, cinsiyet);
            cocuk.anne = this;
            cocuk.baba = this.es;
            cocuk.surName = cocuk.baba.surName;
            this.cocuklar.add(cocuk);
            es.cocuklar.add(cocuk);
            return cocuk;
        } else
            System.out.println("Anne evli degildir");
        return null;
    }

    @Override
    public String toString() {
        return "Insan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", birdhDate=" + birdhDate +
                ", cinsiyet=" + cinsiyet +
                ", evli=" + evli +
                ", cocuklar=" + cocuklar +
                '}';
    }

    void kisiBilgileriniYazdir() {
        System.out.printf("%-1s", this.id + " " + this.name + " " + this.surName + "\n" +
                "Dogum tarihi: " + this.birdhDate + "\n" +
                "Anne adi: " + this.anne.name + "\n" + "Baba adi: " + this.baba.name + "\n");

    }

    String kisiBilgileriniStringeYazdir() {
        String cikti = (this.id + " " + this.name + " " + this.surName + "\n" +
                "Dogum tarihi: " + this.birdhDate + "\n" +
                "Anne adi: " + this.anne.name + "\n" + "Baba adi: " + this.baba.name + "\n");

        return cikti;
    }

    ArrayList<Insan> kardesler(Insan kisi) {
        ArrayList<Insan> temp = new ArrayList<>();
        for (Insan kardes : anne.cocuklar) {
            if (!kardes.equals(this)) {
                temp.add(kardes);
            }
        }
        return temp;
    }

    static ArrayList<Insan> insanBul(String ad, String soyad) {
        ArrayList bulunanInsanlar=new ArrayList();
        for (Insan insan : insanlar) {
            if (insan.name.equalsIgnoreCase(ad) && insan.surName.equalsIgnoreCase(soyad)) {
                bulunanInsanlar.add(insan);
            }
        }
        return bulunanInsanlar;
    }

    static ArrayList<Insan> insanBul(String ad) {
        ArrayList bulunanInsanlar=new ArrayList();
        for (Insan insan : insanlar) {
            if (insan.name.equalsIgnoreCase(ad)) {
                bulunanInsanlar.add(insan);
            }
        }
        return bulunanInsanlar;
    }

    static ArrayList<Insan> insanBul(int id) {
        ArrayList bulunanInsanlar=new ArrayList();
        for (Insan insan : insanlar) {
            if (insan.id == id) {
                bulunanInsanlar.add(insan);
            }
        }
        return bulunanInsanlar;
    }

    static ArrayList<Insan> girilenStringeGoreInsanBulma(String str) {

        str = str.trim();
        if (Utility.girdiSayiMi(str)) {
            return insanBul(Integer.parseInt(str));
        } else {
            str = str.replaceAll("[ ]+", " ");
            String[] arr = str.split(" ");
            str = "";
            if (arr.length == 1) {
                return insanBul(arr[0]);
            } else {
                return insanBul(arr[0], arr[arr.length - 1]);
            }
        }
    }

    static void insanEkle() {
        Insan ali = new Insan("ali", "aslan", 1, 1, 1905, 'E');
        Insan fadim = new Insan("Fadime", 1, 1, 1910, 'K');

        Insan suleyman = new Insan("suleyman", "yakup", 1, 1, 1906, 'E');
        Insan cemile = new Insan("cemile", 1, 1, 1912, 'K');
        ali.evlen(fadim);
        suleyman.evlen(cemile);
        fadim.cocukYap("huseyin", 1, 1, 1927, 'E');
        fadim.cocukYap("salih", 1, 1, 1929, 'E');
        fadim.cocukYap("zinet", 1, 1, 1930, 'K');
        fadim.cocukYap("muruvvet", 1, 1, 1933, 'K');
        fadim.cocukYap("makbule", 1, 1, 1935, 'K');

        cemile.cocukYap("makbule", 1, 1, 1928, 'K');
        cemile.cocukYap("hanife", 1, 1, 1930, 'K');
        cemile.cocukYap("emine", 1, 1, 1933, 'K');

        Insan rahmi = new Insan("Rahmi", "Karadeniz", 1, 1, 1935, 'E');
        Insan kireza = new Insan("Kezban", 1, 1, 1940, 'K');
        Insan mustafa = new Insan("Mustafa", "Ulu", 1, 1, 1935, 'E');
        Insan fadime = new Insan("Fadime", 1, 1, 1940, 'K');
        Insan hasan = new Insan("Hasan", "YILDIZ", 1, 1, 1935, 'E');
        Insan hayriye = new Insan("Hayriye", 1, 1, 1940, 'K');
        Insan husnu = new Insan("Husnu", "cakmak", 1, 1, 1935, 'E');
        Insan kadriye = new Insan("Kadriye", 1, 1, 1940, 'K');
        Insan salih = new Insan("Salih", "zafer", 1, 1, 1935, 'E');
        Insan asiye = new Insan("Asiye", 1, 1, 1940, 'K');
        Insan hayri = new Insan("Hayri", "demir", 1, 1, 1935, 'E');
        Insan saime = new Insan("Saime", 1, 1, 1940, 'K');
        Insan ayseG = new Insan("Ayse", 1, 1, 1976, 'K');
        Insan serap = new Insan("Serap", 1, 1, 1973, 'K');
        Insan saide = new Insan("Saide", 1, 1, 1960, 'K');


        insanBul("huseyin", "aslan").get(0).evlen(insanBul("emine", "yakup").get(0));
        rahmi.evlen(kireza);
        mustafa.evlen(fadime);
        hasan.evlen(hayriye);
        husnu.evlen(kadriye);
        salih.evlen(asiye);
        hayri.evlen(saime);

        Insan.insanBul("kadriye", "cakmak").get(0).cocukYap("Sinan", 1, 1, 1970, 'E');
        Insan.insanBul("fadime", "ulu").get(0).cocukYap("Cevdet", 1, 1, 1953, 'E');

        Insan.insanBul("hayriye", "yildiz").get(0).cocukYap("Salim", 1, 1, 1958, 'E');

        Insan.insanBul("emine", "aslan").get(0).cocukYap("Ertugrul", 1, 1, 1954, 'E');
        Insan.insanBul("emine", "aslan").get(0).cocukYap("Birsen", 20, 3, 1960, 'K');
        Insan.insanBul("emine", "aslan").get(0).cocukYap("Osman", 10, 8, 1974, 'E');

        asiye.cocukYap("Muhittin", 1, 1, 1960, 'E');
        saime.cocukYap("Ihsan", 1, 1, 1962, 'E');

        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Ayse", 1, 1, 1960, 'K');
        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Fatma", 1, 1, 1965, 'K');
        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Hanife", 1, 1, 1968, 'K');
        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Abdullah", 1, 1, 1970, 'E');
        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Elmas", 1, 1, 1973, 'K');
        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Faruk", 1, 1, 1975, 'E');
        Insan.insanBul("kezban", "karadeniz").get(0).cocukYap("Aysen", 1, 1, 1979, 'K');

        insanBul("ertugrul", "aslan").get(0).evlen(saide);
        Insan.insanBul("salim", "yildiz").get(0).evlen(insanBul("ayse", "karadeniz").get(0));
        insanBul("ihsan", "demir").get(0).evlen(insanBul("hanife", "karadeniz").get(0));
        Insan.insanBul("cevdet", "ulu").get(0).evlen(insanBul("birsen", "aslan").get(0));
        Insan.insanBul("muhittin", "zafer").get(0).evlen(insanBul("fatma", "karadeniz").get(0));
        Insan.insanBul("sinan", "cakmak").get(0).evlen(insanBul("elmas", "karadeniz").get(0));
        Insan.insanBul("osman", "aslan").get(0).evlen(insanBul("aysen", "karadeniz").get(0));
        Insan.insanBul("abdullah", "karadeniz").get(0).evlen(serap);
        Insan.insanBul("faruk", "karadeniz").get(0).evlen(ayseG);

        Insan.insanBul("ayse", "yildiz").get(0).cocukYap("Sinan", 1, 1, 1978, 'E');
        Insan.insanBul("ayse", "yildiz").get(0).cocukYap("Ibrahim", 1, 1, 1989, 'E');
        Insan.insanBul("ayse", "yildiz").get(0).cocukYap("Aliye", 1, 1, 1995, 'K');

        Insan.insanBul("hanife", "demir").get(0).cocukYap("Hafsa", 1, 1, 1991, 'K');
        Insan.insanBul("hanife", "demir").get(0).cocukYap("Seyma", 1, 1, 1993, 'K');
        Insan.insanBul("hanife", "demir").get(0).cocukYap("Omer", 1, 1, 2003, 'E');

        Insan.insanBul("birsen", "ulu").get(0).cocukYap("Ozgur", 20, 8, 1976, 'E');
        Insan.insanBul("birsen", "ulu").get(0).cocukYap("Baris", 1, 5, 1982, 'E');

        Insan.insanBul("fatma", "zafer").get(0).cocukYap("Sare", 1, 1, 1991, 'K');
        Insan.insanBul("fatma", "zafer").get(0).cocukYap("Havle", 1, 1, 1993, 'K');
        Insan.insanBul("fatma", "zafer").get(0).cocukYap("Meryem", 1, 1, 2003, 'K');

        Insan.insanBul("elmas", "cakmak").get(0).cocukYap("Asim", 1, 1, 2005, 'E');

        Insan.insanBul("serap", "karadeniz").get(0).cocukYap("Sumeyye", 1, 1, 2007, 'K');

        Insan.insanBul("ayse", "karadeniz").get(0).cocukYap("Betul", 1, 1, 2009, 'K');

        Insan.insanBul("saide", "aslan").get(0).cocukYap("Fusun", 1, 1, 1976, 'K');
        Insan.insanBul("saide", "aslan").get(0).cocukYap("Funda", 1, 1, 1982, 'K');


        Insan.insanBul("hafsa", "demir").get(0).evlen(insanBul("ibrahim", "yildiz").get(0));

        Insan.insanBul("hafsa", "yildiz").get(0).cocukYap("Edip", 28, 8, 2017, 'E');

        Insan.insanBul("aysen", "aslan").get(0).cocukYap("Hilal", 12, 9, 1999, 'K');
        Insan.insanBul("aysen", "aslan").get(0).cocukYap("Aylin", 02, 10, 2005, 'K');
        Insan.insanBul("aysen", "aslan").get(0).cocukYap("Aybuke", 22, 8, 2009, 'K');
    }


}
