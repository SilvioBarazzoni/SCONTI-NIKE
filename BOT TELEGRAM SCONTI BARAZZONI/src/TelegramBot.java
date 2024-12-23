import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class TelegramBot extends TelegramLongPollingBot {

    // Nome utente del bot (usato per registrarlo su Telegram)
    @Override
    public String getBotUsername() {
        return "IlTuoBotUsername"; // Sostituisci con il nome utente del tuo bot
    }

    // Token del bot (ottenuto da BotFather)
    @Override
    public String getBotToken() {
        return "8007699672:AAHLFzApZYdgeiJd0wfRR-tB8gatCRKqMJA";
    }

    // Metodo principale per gestire gli aggiornamenti dal bot
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String userMessage = message.getText();

            // Gestione del comando /start
            if (userMessage.equalsIgnoreCase("/start")) {
                sendWelcomeMessage(message.getChatId());
            } else if(userMessage.equalsIgnoreCase("/sconti")) {
                List<Product> prodotti = Main.db.getAllProducts();
                for (Product prodotto : prodotti) {
                    // Costruisci il messaggio per ciascun prodotto
                    String prodottoMessage = "Link prodotto: " + prodotto.link + "\nPrezzo originale: " + prodotto.prezzo + "€" + "\nPrezzo scontato: " + prodotto.prezzos + "€";
                    sendMessage(message.getChatId(), prodottoMessage);
                }
            } else {
                sendMessage(message.getChatId(), "Comando non riconosciuto. Usa /start per iniziare.");
            }
        }

    }

    // Metodo per inviare un messaggio di benvenuto
    private void sendWelcomeMessage(Long chatId) {
        String welcomeText = "Benvenuto al bot! Qui puoi trovare le migliori offerte sui prodotti Nike. Usa i comandi disponibili per interagire.";
        sendMessage(chatId, welcomeText);
    }

    // Metodo per inviare messaggi generici
    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message); // Metodo ereditato da TelegramLongPollingBot
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}