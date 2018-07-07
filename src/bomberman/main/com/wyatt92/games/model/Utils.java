package com.wyatt92.games.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loads Strings from files.
 * Parses Strings to Integers
 */
class Utils
{
    static String loadFileAsString(String path) {
        StringBuilder builder = new StringBuilder();
        String tempPath = Utils.class.getClassLoader().getResource("./worlds/" + path).getPath();
        assert tempPath != null : "Path is invalid!";

        try{
            BufferedReader br = new BufferedReader(new FileReader(tempPath));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line).append("\n");

            br.close();
        } catch(IOException e){
            e.printStackTrace();
        }

        return builder.toString();
    }

    static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        } catch(NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
