import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Database db = new Database();
    public static void main(String[] args) {
        ArrayList<Product> products = Scraper.scrape();
        for (Product product : products) {
           db.insertProduct(product.link, product.prezzo, product.prezzos);
        }
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBot());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}