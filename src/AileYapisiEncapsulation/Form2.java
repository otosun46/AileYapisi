/**
 * @Author:Otosun Tarih :08/08/2020
 */
package AileYapisiEncapsulation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Form2 extends JFrame {
    private JPanel panelAltlik;
    private JPanel panelUst;
    private JPanel panelSol;
    private JPanel panelSag;
    private JButton araButton;
    private JList bulunanKisiler;
    private JButton cocuklariButton;
    private JButton kardesleriButton;
    private JButton dedeleriButton;
    private JButton babaanneAnneanneButton;
    private JButton teyzeleriButton;
    private JButton halalariButton;
    private JButton torunlariButton;
    private JList sonucListesi;
    private JTextField textField1;
    private JButton dayilariButton;
    private JButton amcalariButton;
    private JButton yegenleriButton;
    private JButton kuzenleriButton;
    private JLabel listeIsmi;
    private JLabel kisiIsmi;
    private JScrollBar scrollBar1;
    private ArrayList<Insan> bulunanKisi;
    private ArrayList<Insan> listeSonuc;
    private DefaultListModel listModel;
    private DefaultListModel listeSonucModel;
    private Insan kisi;
    private static Insan sKisi1;
    private FormKisiBilgileri formKisiBilgileri;

    public Insan getKisi() {
        return kisi;
    }

    public void setKisi(Insan kisi) {
        this.kisi = kisi;
    }

    public static Insan getsKisi1() {
        return sKisi1;
    }

    public void setsKisi1(Insan sKisi1) {
        this.sKisi1 = sKisi1;
    }

    public Form2() {
        add(panelAltlik);
        setSize(new Dimension(600, 650));
        setTitle("Aile Yapisi");
        setLocation(550, 200);
        listeSonuc = new ArrayList<>();
        listModel = new DefaultListModel();
        listeSonucModel = new DefaultListModel();
        bulunanKisiler.setModel(listModel);
        sonucListesi.setModel(listeSonucModel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            araButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    bulunanKisi = new ArrayList<>();
                    String ad = textField1.getText();
                    bulunanKisi = Insan.girilenStringeGoreInsanBulma(ad);
                    refreshKisiList();

                }
            });
            bulunanKisiler.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int bIndex = bulunanKisiler.getSelectedIndex();
                    if (bIndex >= 0) {
                        kisi = bulunanKisi.get(bIndex);
                        kisiIsmi.setText(kisi.getName() + " " + kisi.getSurName());
                    }
                    //   formKisiBilgileri = new FormKisiBilgileri();
                }
            });
            cocuklariButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Cocuklar");
                    kisiBilgileriBaslatma();
                    if (kisi.isEvli()) {
                        if (!kisi.getCocuklar().isEmpty()) {

                            for (Insan kisi : kisi.getCocuklar()) {
                                listeSonucModel.addElement(kisi.getId() + " " + kisi.getName() + " " + kisi.getSurName());
                                listeSonuc.add(kisi);
                            }
                        }
                    }
                }

            });
            kardesleriButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Kardesler");
                    kisiBilgileriBaslatma();
                    for (Insan kardes : kisi.getAnne().getCocuklar()) {
                        if (!kardes.equals(kisi)) {
                            listeSonucModel.addElement(kardes.getId() + " " + kardes.getName() + " " + kardes.getSurName());
                            listeSonuc.add(kardes);
                        }
                    }
                }
            });
            dedeleriButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Dedeler");
                    kisiBilgileriBaslatma();
                    if (kisi.getBaba().getBaba() != null) {
                        listeSonucModel.addElement(kisi.getBaba().getBaba().getId() + " " + kisi.getBaba().getBaba().getName() + " " + kisi.getBaba().getBaba().getSurName());
                        listeSonuc.add(kisi.getBaba().getBaba());
                    }
                    if (kisi.getAnne().getBaba() != null) {
                        listeSonucModel.addElement(kisi.getAnne().getBaba().getId() + " " + kisi.getAnne().getBaba().getName() + " " + kisi.getAnne().getBaba().getSurName());
                        listeSonuc.add(kisi.getAnne().getBaba());
                    }
                }
            });
            babaanneAnneanneButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Buyukanneler");
                    kisiBilgileriBaslatma();
                    if (kisi.getBaba().getAnne() != null) {
                        listeSonucModel.addElement(kisi.getBaba().getAnne().getId() + " " + kisi.getBaba().getAnne().getName() + " " + kisi.getBaba().getAnne().getSurName());
                        listeSonuc.add(kisi.getBaba().getAnne());
                    }
                    if (kisi.getAnne().getAnne() != null) {
                        listeSonucModel.addElement(kisi.getAnne().getAnne().getId() + " " + kisi.getAnne().getAnne().getName() + " " + kisi.getAnne().getAnne().getSurName());
                        listeSonuc.add(kisi.getAnne().getAnne());
                    }
                }
            });
            teyzeleriButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Teyzeler");
                    kisiBilgileriBaslatma();
                    try {
                        for (Insan sahis : kisi.getAnne().getAnne().getCocuklar()) {
                            if (sahis.getCinsiyet() == 'K') {
                                if (sahis != kisi.getAnne()) {
                                    listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                    listeSonuc.add(sahis);
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        listeSonucModel.removeAllElements();
                        listeSonuc.clear();
                    }
                }
            });
            dayilariButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Dayilar");
                    kisiBilgileriBaslatma();
                    try {
                        for (Insan sahis : kisi.getAnne().getAnne().getCocuklar()) {
                            if (sahis.getCinsiyet() == 'E') {
                                if (sahis != kisi.getAnne()) {
                                    listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                    listeSonuc.add(sahis);
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        listeSonucModel.removeAllElements();
                        listeSonuc.clear();
                    }
                }
            });
            amcalariButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Amcalar");
                    kisiBilgileriBaslatma();
                    try {
                        for (Insan sahis : kisi.getBaba().getAnne().getCocuklar()) {
                            if (sahis.getCinsiyet() == 'E') {
                                if (sahis != kisi.getBaba()) {
                                    listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                    listeSonuc.add(sahis);
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        listeSonucModel.removeAllElements();
                        listeSonuc.clear();
                    }
                }
            });
            halalariButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Halalar");
                    kisiBilgileriBaslatma();
                    try {
                        for (Insan sahis : kisi.getBaba().getAnne().getCocuklar()) {
                            if (sahis.getCinsiyet() == 'K') {
                                if (sahis != kisi.getAnne()) {
                                    listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                    listeSonuc.add(sahis);
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        listeSonucModel.removeAllElements();
                        listeSonuc.clear();
                    }
                }
            });
            yegenleriButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Yegenler");
                    kisiBilgileriBaslatma();
                    for (Insan Kisi : kisi.kardesler(kisi)) {
                        if (Kisi.isEvli()) {
                            if (!Kisi.getCocuklar().isEmpty()) {
                                for (Insan sahis : Kisi.getCocuklar()) {
                                    listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                    listeSonuc.add(sahis);
                                }
                            }
                        }
                    }

                }
            });
            kuzenleriButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Kuzenler");
                    kisiBilgileriBaslatma();
                    try {
                        for (Insan Kisi : kisi.getBaba().getAnne().getCocuklar()) {
                            if (Kisi.isEvli()) {
                                if (Kisi != kisi.getBaba()) {
                                    for (Insan sahis : Kisi.getCocuklar()) {
                                        listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                        listeSonuc.add(sahis);
                                    }
                                }
                            }
                        }

                        for (Insan Kisi : kisi.getAnne().getAnne().getCocuklar()) {
                            if (Kisi.isEvli()) {
                                if (Kisi != kisi.getAnne()) {
                                    for (Insan sahis : Kisi.getCocuklar()) {
                                        listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                        listeSonuc.add(sahis);
                                    }
                                }
                            }
                        }
                    } catch (NullPointerException ex) {
                        listeSonucModel.removeAllElements();
                        listeSonuc.clear();
                    }


                }
            });
            torunlariButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    listeIsmi.setText("Torunlar");
                    kisiBilgileriBaslatma();
                    if (kisi.isEvli()) {
                        if (!kisi.getCocuklar().isEmpty()) {
                            for (Insan cocugu : kisi.getCocuklar()) {
                                if (cocugu.isEvli()) {
                                    if (!cocugu.getCocuklar().isEmpty()) {
                                        for (Insan sahis : cocugu.getCocuklar()) {
                                            listeSonucModel.addElement(sahis.getId() + " " + sahis.getName() + " " + sahis.getSurName());
                                            listeSonuc.add(sahis);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        } catch (Exception ex) {
            System.out.println("Hata olustu");
        }

        sonucListesi.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int bIndex = sonucListesi.getSelectedIndex();
                if (bIndex >= 0) {
                    sKisi1 = listeSonuc.get(bIndex);
                }
                formKisiBilgileri.refreshKisiBilgileri();
                formKisiBilgileri.setVisible(true);


            }
        });
    }

    public void refreshKisiList() {
        listModel.removeAllElements();
        for (Insan kisi : bulunanKisi) {
            listModel.addElement(kisi.getId() + " " + kisi.getName() + " " + kisi.getSurName());
        }
    }

    public void refreshSonucList() {
        listeSonucModel.removeAllElements();
        for (Insan kisi : listeSonuc) {
            listeSonucModel.addElement(kisi.getId() + " " + kisi.getName() + " " + kisi.getSurName());
        }
    }

    void kisiBilgileriBaslatma() {
        listeSonucModel.removeAllElements();
        listeSonuc.clear();
        formKisiBilgileri = new FormKisiBilgileri();
        FormKisiBilgileri.x += 5;
        FormKisiBilgileri.y += 5;
    }
}
