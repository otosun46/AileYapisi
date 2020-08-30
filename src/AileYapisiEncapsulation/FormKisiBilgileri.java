/**
 * @Author:Otosun Tarih :09/08/2020
 */
package AileYapisiEncapsulation;

import javax.swing.*;
import java.awt.*;

public class FormKisiBilgileri extends Form2 {

    private JPanel panel1;
    private JLabel id;
    private JLabel name;
    private JLabel surName;
    private JLabel birdhDate;
    private JLabel cinsiyet;
    private JLabel babaAdi;
    private JLabel anneAdi;
    private JLabel esAdi;
    private JLabel cocuklar;
    private JTextField textField_id;
    private JTextField textField_name;
    private JTextField textField_surName;
    private JTextField textField_BirdhDate;
    private JTextField textField_cinsiyet;
    private JTextField textField_babaAdi;
    private JTextField textField_anneAdi;
    private JTextField textField_esininAdi;
    private JList list1;
    private JScrollBar scrollBar1;
    private Insan kisi1;
    private DefaultListModel listModelcocuk;
    static int x=1150;
    static int y=200;




    public Insan getKisi1() {
        return kisi1;
    }

    public void setKisi1(Insan kisi1) {
        this.kisi1 = kisi1;
    }

    public FormKisiBilgileri() throws HeadlessException {
        add(panel1);
        setTitle("Kisi Bilgileri");
        setSize(320, 400);
        setLocation(x, y);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void refreshKisiBilgileri() {
        try {

            setKisi1(Form2.getsKisi1());
            if (kisi1 != null) {
                textField_id.setText(kisi1.getId() + "");
                textField_name.setText(kisi1.getName());
                textField_surName.setText(kisi1.getSurName());
                textField_BirdhDate.setText(kisi1.getBirdhDate().toString());
                textField_cinsiyet.setText(kisi1.getCinsiyet() + "");
                textField_babaAdi.setText(kisi1.getBaba().getName());
                textField_anneAdi.setText(kisi1.getAnne().getName());
                listModelcocuk = new DefaultListModel();
                listModelcocuk.removeAllElements();
                if (kisi1.isEvli()) {
                    textField_esininAdi.setText(kisi1.getEs().getName());
                    list1.setModel(listModelcocuk);
                    for (Insan sahis : kisi1.getCocuklar()) {
                        listModelcocuk.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                    }
                }
                else{
                    textField_esininAdi.setText("");
                    list1.setModel(listModelcocuk);
                }
            }
        }catch (NullPointerException ex){
            textField_babaAdi.setText("");
            textField_anneAdi.setText("");
            listModelcocuk = new DefaultListModel();
            listModelcocuk.removeAllElements();
            if (kisi1.isEvli()) {
                textField_esininAdi.setText(kisi1.getEs().getName());
                list1.setModel(listModelcocuk);
                for (Insan sahis : kisi1.getCocuklar()) {
                    listModelcocuk.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                }
            }

        }

    }
}
