/**
@Author:Otosun
Tarih :14/07/2020
*/
package AileYapisiEncapsulation;


import javax.swing.*;

public class Main {

    public static void main(String[] args) {
       Insan.insanEkle();
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Form2 form2=new Form2();
                form2.setVisible(true);
            }
        });
    }
}
