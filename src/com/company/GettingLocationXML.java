package com.company;

import java.io.InputStream;
        import java.net.HttpURLConnection;
        import java.net.URL;

// Cette classe permet de récupérer le fichier XML de l'ip passée en paramètre par l'utilisateur sur l'interface
public class GettingLocationXML {
    private String urlToLoad;
    public GettingLocationXML(String ip){
        urlToLoad = "http://api.ipinfodb.com/v3/ip-city/?key=d57b1e68028f1e5629d553346e48f7f2457981a385726f9c6daf4ddff36f5eb6&format=xml&ip="+ip; // on récupère en format XML car on aime les coupes de fouets
    }
    public InputStream getRessource(){
        HttpURLConnection connection = null;
        try{
            URL url = new URL(urlToLoad);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            return conn.getInputStream(); // On renvoi le stream qu'on a récupéré ( = le XML).
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
}