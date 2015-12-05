package com.company;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

// Cette classe crée la fenêtre graphique qui permet de récupérer l'ip puis le boutton qui permettra de lancer le double traitement
// Un premier traitement sans XML qui utilisera GettingLocationFromIp
// Un second avec XML qui utilisera GettingLocationXML dans un premier temps pour récupérer le XML puis AnalysingXML pour le parser


public class GUI implements ActionListener {

    private JTextField textField;
    private JTextArea result;
    private JTextArea resultXML;

    public GUI() { // On crée la fenêtre graphique

    JFrame frame = new JFrame("JFrame Example");

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout());

    JLabel label = new JLabel("IP ADRESS");
     textField = new JTextField("149.202.56.234"); // CE TEXTFIELD EST TRES IMPORTANT, IL PERMET DE RECUPERER l'IP A TRAITÉ, on le préremplit.
        result = new JTextArea();
        resultXML = new JTextArea();


            JButton button = new JButton("Button");
            button.setText("Locate me"); // le bouton qui permet de lancer le traitement
            button.addActionListener(this);


            panel.add(label);
            panel.add(textField);
            panel.add(button);
            panel.add(result);
            panel.add(resultXML);

            frame.add(panel);
            frame.setSize(300,400);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        @Override
    public void actionPerformed(ActionEvent actionEvent) { // Ici le listener permet de déclencher le traitement quand on clique sur le bouton
        String string = actionEvent.getActionCommand();

        if(string.equals("Locate me")){

            // SANS LE XML ////////

            GettingLocationFromIp test = new GettingLocationFromIp(textField.getText()); // On instancie la localisation qu'on parsera grâce à des points virgules

            try{
                //result.setText(test.getInfo(textField.getText()));
               result.setText(
                       "            SANS XML : \n\n Language : " + test.getLangage(test.getInfo(textField.getText()))
                       + "\n Pays : " + test.getCountry(test.getInfo(textField.getText()))
                       + "\n Région : " + test.getRegion(test.getInfo(textField.getText()))
                       + "\n Ville : " + test.getCity(test.getInfo(textField.getText()))
                       + "\n Code postal : " + test.getPostalCode(test.getInfo(textField.getText()))
                       + "\n Longitude : " + test.getLongitude(test.getInfo(textField.getText()))
                       + "\n Lattitude : " + test.getLatitude(test.getInfo(textField.getText()))
                       + "\n Fuseau horaire : " + test.getFuseau(test.getInfo(textField.getText()))
               );
            }
            catch(Exception e){
                e.toString();
            }

            // AVEC LE XML ////////

            Logger logger = Logger.getLogger("Tp");

            logger.log(Level.INFO,"Button Clicked");
            GettingLocationXML XMLlocation = new GettingLocationXML(textField.getText());
            logger.log(Level.INFO,"Getting information ...");
            //System.out.println(XMLlocation.getRessource());
            InputStream ressource = XMLlocation.getRessource();
            AnalysingXML XMLAnalyse = new AnalysingXML(ressource);
            //System.out.println(XMLAnalyse.getResponse());

            try{
                //result.setText(test.getInfo(textField.getText()));
                resultXML.setText(
                        "            AVEC XML : \n" +
                                "\n" +
                                " Language : " + XMLAnalyse.response("countryCode")
                                + "\n Pays : " + XMLAnalyse.response("countryName")
                                + "\n Région : " + XMLAnalyse.response("regionName")
                                + "\n Ville : " + XMLAnalyse.response("cityName")
                                + "\n Code postal : " + XMLAnalyse.response("zipCode")
                                + "\n Longitude : " + XMLAnalyse.response("longitude")
                                + "\n Latitude : " + XMLAnalyse.response("latitude")
                                + "\n Fuseau horaire : " + XMLAnalyse.response("timeZone")
                );
            }
            catch(Exception e){
                e.toString();
            }



        }
    }

}