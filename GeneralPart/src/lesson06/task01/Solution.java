package lesson06.task01;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) {

        String InputFile = null;
        String OutputFile = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            InputFile = reader.readLine();
            OutputFile = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }


        ArrayList<String> words = new ArrayList<>();

        byte[] bytesOfInpFile = null;
        String input = "";

        try (FileInputStream file = new FileInputStream(InputFile)) {

            bytesOfInpFile = file.readAllBytes();

            input = new String(bytesOfInpFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);

        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            words.add(matcher.group());
        }

        Collections.sort(words);

        try (FileOutputStream file = new FileOutputStream(OutputFile)) {

            for (String s :
                    words) {
                //Почему то не срабатывает "\n\r"
                file.write((s + System.lineSeparator()).getBytes());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
