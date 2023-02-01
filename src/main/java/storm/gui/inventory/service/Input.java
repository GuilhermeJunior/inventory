package storm.gui.inventory.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {

    public String read() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String ret = "";

        try {
            ret = reader.readLine();
        } catch (IOException ex) {
            System.out.println("Error");
        }
        return ret;
    }
}
