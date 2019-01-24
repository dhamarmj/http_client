package edu.dhamarmj;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Introduzca un URL: " );
//        String uri = scanner.next();
//        scanner.close();

        //String uri="https://en.wikipedia.org/wiki/World_War_II";
        String uri="https://www.facebook.com";
        Document doc = Jsoup.connect(uri).get();

        Elements para = doc.getElementsByTag("p");
        System.out.println("Number of paragraphs: "+ para.size());

        Elements images = doc.select("img > p");
        System.out.println("Images inside a paragraph: "+ images.size());

        Elements forms = doc.select("form[method$=post],form[method$=get]");
        System.out.println("forms with method POST or GET: "+ forms.size());

        Elements input = doc.getElementsByTag("input");
        for (Element a:
                input) {
            System.out.println("Input Type: " + a.attr("type"));
        }


        Elements formPost = doc.select("form[method$=post]");
        for (Element f:
                formPost) {
            PostResponse(uri);
        }
    }

    private static void PostResponse(String uri) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(uri);
        CloseableHttpResponse response = httpclient.execute(httpPost);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity2 = response.getEntity();
            response.addHeader("asignatura","practica1" );
            response.addHeader("matricula","20140047" );
            EntityUtils.consume(entity2);
        } finally {
            response.close();
        }
    }
}
