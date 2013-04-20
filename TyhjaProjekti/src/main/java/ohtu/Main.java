package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] args) throws IOException {

        //String studentNr = "14027451";
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Opiskelijanumero: ");
        String studentNr = scanner.nextLine();
        if (args.length > 0) {
            studentNr = args[0];
        }
        
        System.out.println("");

        String url = "http://ohtustats-2013.herokuapp.com/opiskelija/" + studentNr + ".json";

        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);

        InputStream stream = method.getResponseBodyAsStream();

        String bodyText = IOUtils.toString(stream);

        //System.out.println("json-muotoinen data:");
        //System.out.println(bodyText);

        Gson mapper = new Gson();
        Palautukset palautukset = mapper.fromJson(bodyText, Palautukset.class);

        //System.out.println("oliot:");
        
        int tehtavia = 0;
        int tunteja = 0;
        for (Palautus palautus : palautukset.getPalautukset()) {
            System.out.println(palautus);
            tehtavia += palautus.getTehtavia();
            tunteja += palautus.getTunteja();
        }
        List<Palautus> tehtavat = palautukset.getPalautukset();
        
        System.out.println("\nyhteensä " + tehtavia + " tehtävää " + tunteja + " tuntia ");
        
    }
}
