package edu.dhamarmj;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URI;

public class Main {
    public static void main(String[] args) throws Exception {

        URI uri = new URIBuilder("http://api.geonames.org/searchJSON")
                .addParameter("Username","dhamarmj")
                .build();
        HttpGet httpget = new HttpGet(uri);
        System.out.println(httpget.getURI());

        Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
        Elements paragraphs = doc.getElementsByTag("p");
        for (Element paragraph : paragraphs) {
            System.out.println(paragraph.text());
        }

    }
}
