package com.company;

import java.net.*;
import java.io.*;

// Cette fonction permettra d'analyser l'IP du textfield en parsant par des points virgules (sans utiliser le XML).

class GettingLocationFromIp {

    public static String key = "d57b1e68028f1e5629d553346e48f7f2457981a385726f9c6daf4ddff36f5eb6"; // la clé de l'api

    public GettingLocationFromIp(String ip) {

        try {
            String response = getInfo(ip);
            System.out.println(response);

            String location = parseInfo(response);
            System.out.println("Vous êtes situés: "+location); // permet de voir dans la console si ça marche (oui baaah c'est pas beau).
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static String parseInfo(String response) { // parse avec les points virgules. Chaque paramètre peut être renvoyé individuellement avec param[numero du paramètre]

        String params[] = response.split(";");

        return params[3]+" "+params[4]+" "+params[5]+" "+params[6];

    }

    public static String getParam(String response, int numeroParam) { // renvoi un paramètre particulier

        String params[] = response.split(";");

        return params[numeroParam];

    }

    // Ci dessous des getter pour chaque paramètre particuliers

    public static String getLangage(String response) {

        String params[] = response.split(";");

        return params[3];

    }

    public static String getCountry(String response) {

        String params[] = response.split(";");

        return params[4];

    }

    public static String getRegion(String response) {

        String params[] = response.split(";");

        return params[5];

    }

    public static String getCity(String response) {

        String params[] = response.split(";");

        return params[6];

    }

    public static String getPostalCode(String response) {

        String params[] = response.split(";");

        return params[7];

    }

    public static String getLongitude(String response) {

        String params[] = response.split(";");

        return params[9];

    }

    public static String getLatitude(String response) {

        String params[] = response.split(";");

        return params[8];

    }

    public static String getFuseau(String response) {

        String params[] = response.split(";");

        return params[10];

    }

    // GetInfo récupère les données "brutes", elle prend en paramètre l'ip qu'on a rentré.

    public static String getInfo(String ip) throws IOException {

        String url = "http://api.ipinfodb.com/v3/ip-city/?key="
                + key + "&ip=" + ip; // adresse de connexion avec clé api + ip

        URL ipInfoDB = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) ipInfoDB.openConnection();

        if(conn.getResponseCode() != 200) {
            System.out.println("Erreur de connexion: "+conn.getResponseMessage());
            return "Erreur";
        }

        BufferedReader rd = new BufferedReader( // Grâce au buffer on récupère l'inputstream
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();

        // at this point we have response
        String response = sb.toString();

        return response; // on renvoit les données "brutes"

    }

}
