package org.example;

import java.io.*;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "Техника.csv";
        File file = new File(fileName);
        //чистим временный файл
        ochistka();

        File newFile = new File("newFile");
        String line;
        String name;
        long x = 0;
        long y = 0;
        try {
            FileWriter writer = new FileWriter(newFile, true);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] mass;
            line = reader.readLine();
            while ((line) != null) {
                mass = line.split(",");
                name = mass[0];
                try {
                    //обрабатываем x y
                    x = Long.parseLong(getString(mass[1]));
                    x = x % 100000;
                    y = Long.parseLong(getString(mass[2]));
                    y = y % 100000;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    Logger.getLogger(Main.class.getName()).info("Шляпа: " + ex.getMessage());
                }
                writer.write(name + "," + x + "," + y + "," + 150 + "," + 0 + "," + 0 + "\n");
                line = reader.readLine();

            }
            reader.close();
            writer.close();
            System.out.println("Успешно!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        file.delete();
        newFile.renameTo(file);
    }

    public static void ochistka() throws IOException {
        File newFile = new File("newFile");
        new FileWriter(newFile);
    }

    public static String getString(String str) {
        StringBuilder digit = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                digit.append(c);
            }
        }
        return digit.toString();
    }
}