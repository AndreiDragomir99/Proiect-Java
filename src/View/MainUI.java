package View;

import Model.Contact;

import java.util.HashMap;

public class MainUI {
    public static void main(String[] args) {

        HashMap <String, Contact> contactMap = new HashMap<>();
        UI ui  = new UI(contactMap);
        ui.startUI();

        Client client = new Client(5000, contactMap, ui);

        ui.client = client;
    }
}
