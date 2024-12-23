
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class Scraper {
    public static ArrayList<Product> scrape() {
        try {
            // URL della pagina Nike Outlet
            String url = "https://www.nike.com/it/w/outlet-3yaep";

            // Connessione al sito web
            Document doc = Jsoup.connect(url).get();

            // Seleziona i prodotti usando il selettore specificato
            Elements products = doc.select("div.product-card.product-grid__card.css-1tmiin5");

            ArrayList<Product> productList = new ArrayList<>();

            // Itera attraverso ciascun prodotto
            for (Element product : products) {
                // Estrai il link al prodotto
                Element productLinkElement = product.selectFirst("a.product-card__link-overlay");
                String productLink = productLinkElement != null ? productLinkElement.attr("href") : "Non disponibile";

                // Estrai il prezzo scontato
                Element currentPriceElement = product.selectFirst("div.product-price.is--current-price");
                String currentPrice = currentPriceElement != null ? currentPriceElement.text() : "Non disponibile";

                // Estrai il prezzo originale
                Element originalPriceElement = product.selectFirst("div.product-price.is--striked-out");
                String originalPrice = originalPriceElement != null ? originalPriceElement.text() : "Non disponibile";

                // Stampa i dati estratti
                System.out.println("Prodotto: " + productLink);
                System.out.println("Prezzo scontato: " + currentPrice);
                System.out.println("Prezzo originale: " + originalPrice);
                System.out.println("-----------------------------");
                productList.add(new Product(productLink, currentPrice, originalPrice));
            }
            return productList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
