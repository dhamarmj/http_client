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
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduzca un URL: " );
        String url = scanner.next();
        scanner.close();
        Document doc  = validateUrl(url);
        if(doc != null){

            System.out.println(" ");
            System.out.println("Number of lines: " +  doc.html().split("\n").length);
            System.out.println(" ");
            Elements para = doc.getElementsByTag("p");
            System.out.println("Number of paragraphs: "+ para.size());
            System.out.println(" ");
            Elements images = doc.select("img > p");
            System.out.println("Images inside a paragraph: "+ images.size());
            System.out.println(" ");
            Elements forms = doc.select("form[method$=post],form[method$=get]");
            System.out.println("forms with method POST or GET: "+ forms.size());
            System.out.println(" ");
            Elements input = doc.getElementsByTag("input");
            for (Element a:
                    input) {
                System.out.println("Input name: " + a.attr("name") + " Type: " + a.attr("type"));
            }
            System.out.println(" ");
            Elements formPost = doc.select("form[method$=post]");
            for (Element f:
                    formPost) {
                postResponse(f);
            }
        }
        else
            System.out.println("Wrong URL");
    }

    private static Document validateUrl(String url) throws IOException {
        Document doc;
        try{
            doc = Jsoup.connect(url).get();
            return doc;
        }
        catch (IllegalArgumentException e){
            return null;
        }
        catch(UnknownHostException e){
            return null;
        }
    }


    private static void postResponse(Element element) throws IOException {
        String dir = element.absUrl("action");
            Document document1 = Jsoup.connect(dir)
                    .data("asignatura","practica1")
                    .header("matricula","20140047").post();
            System.out.println(document1.title());
    }
}
