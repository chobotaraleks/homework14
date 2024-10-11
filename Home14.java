// Інтерфейс Printer
interface Printer {
    void print(Message message);

    // Статичний внутрішній клас Message
    public static class Message {
        private String text;
        private String sender;

        // Конструктор
        public Message(String text, String sender) {
            this.text = text;
            this.sender = sender;
        }

        // Геттери
        public String getText() {
            return text;
        }

        public String getSender() {
            return sender;
        }

        // Сеттери
        public void setText(String text) {
            this.text = text;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }
    }
}

// Реалізація інтерфейсу Printer
class PrinterImpl implements Printer {

    @Override
    public void print(Message message) {
        if ((message.getSender() == null || message.getSender().isEmpty()) &&
            (message.getText() == null || message.getText().isEmpty())) {
            // Анонімний клас для обробки пустого повідомлення
            Printer anonymousPrinter = new Printer() {
                @Override
                public void print(Message msg) {
                    System.out.println("Опрацьовується пусте повідомлення від анонімного користувача...");
                }
            };
            anonymousPrinter.print(message);
        } else if (message.getSender() == null || message.getSender().isEmpty()) {
            System.out.println("Анонімний користувач відправив повідомлення: " + message.getText());
        } else {
            System.out.println("Користувач " + message.getSender() + " відправив повідомлення: " + message.getText());
        }
    }
}

// Тестування
public class Main {
    public static void main(String[] args) {
        PrinterImpl printer = new PrinterImpl();

        // Повідомлення від анонімного користувача
        Printer.Message msg1 = new Printer.Message("Привіт!", null);
        printer.print(msg1);

        // Повідомлення від конкретного користувача
        Printer.Message msg2 = new Printer.Message("Як справи?", "Олександр");
        printer.print(msg2);

        // Пусте повідомлення
        Printer.Message msg3 = new Printer.Message(null, null);
        printer.print(msg3);
    }
}
