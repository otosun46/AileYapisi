/*
 @Author:Otosun Tarih :26/07/2020
 */
package AileYapisiEncapsulation;

import java.util.Scanner;

public class Utility {

    public static String kullanicidanVeriAl(String ekranYazisi) {
        Scanner oku = new Scanner(System.in);
        System.out.print(ekranYazisi + ": ");

        return oku.nextLine();
    }
/*
    public static String kelimeDuzenle(String str) {
        str = str.trim();
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        return str;
    }
*/
    public static String adDuzenle(String str) {
        str = str.trim();
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        return str;
    }

    public static String soyadDuzenle(String str) {
        str = str.trim();
        str = str.toUpperCase();
        return str;
    }

    public static int stringiIntSayiyaCevir(String ekranYazisi) {
        boolean kontrol;
        int value = 0;
        do {
            String str = Utility.kullanicidanVeriAl(ekranYazisi);
            try {
                value = Integer.parseInt(str);
                kontrol = false;
            } catch (Exception ex) {
                System.out.println("Hatali giris yaptiniz!");
                kontrol = true;
            }
        } while (kontrol);
        return value;
    }
/*
    public static String isimDuzenle(String str) {
        str = str.trim();
        str = str.replaceAll("[ ]+", " ");
        String[] arr = str.split(" ");
        str = "";
        for (int i = 0; i < arr.length; i++) {
            arr[i] = kelimeDuzenle(arr[i]) + " ";
            if (i == arr.length - 1)
                arr[i] = arr[arr.length - 1].toUpperCase();
            str += arr[i];
        }
        return str;
    }

 */

    /**
     * Method-11 public static boolean girdiSayiMi(String str)
     * Açıklaması: aldigi stringin sayi olup olmadigini döndüren method yaziniz
     * Örnek: "123a"  -->  false,     "1002"  --> True
     *
     * @param str
     * @return
     */
    public static boolean girdiSayiMi(String str) {

        return str.matches("-?\\d+(\\.\\d+)?");
    }

}


