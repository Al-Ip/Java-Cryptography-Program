/*
 -------------Data Structures & Algorithms II-------------
|  Assignment 2 - Substitution Ciphers / Enigma Machine  |
|                                                        |
|  Name: Alexandru Ipsalat                               |
|  K Number: K00229834                                   |
|                                                        |
 --------------------------------------------------------
 */


package com_company;

import java.util.ArrayList;
import java.util.Collections;

public class Enigma implements Cipher{

    private static final String OUTER = "#BDFHJLNPRTVXZACEGIKMOQSUWY";
    String MIDDLE;
    String INNER;
    ArrayList<Character> innerArray = new ArrayList<Character>();
    ArrayList<Character> middleArray = new ArrayList<Character>();

    public Enigma(String key1, String key2)
    {
        this.INNER = key1;
        this.MIDDLE = key2;

        for (char ch : INNER.toCharArray()) {
            innerArray.add(ch);
        }

        for (char ch : MIDDLE.toCharArray()) {
            middleArray.add(ch);
        }
    }

    @Override
    public char encode ( char ch){
        //Basically copied and pasted the same as encryption just reversed the roles
        if(Character.isLetter(ch))
        {
            int index = INNER.indexOf(ch);
            char outerChar= OUTER.charAt(index);
            int middleChar = MIDDLE.indexOf(outerChar);

            if(Character.isUpperCase(ch))
            {
                index = INNER.toUpperCase().indexOf(ch);
                //System.out.println(index);
                outerChar = OUTER.toUpperCase().charAt(index);
                //System.out.println(outerChar);
                middleChar = MIDDLE.toUpperCase().indexOf(outerChar);
                //System.out.println(middleChar);

                ch = OUTER.toUpperCase().charAt(middleChar);
                rotate();
                return ch;
            }
            ch = OUTER.charAt(middleChar);
            rotate();
            return ch;
        }
        rotate();
        return ch;
    }

    /**
     * Decodes a single character.
     *   @param ch the character to be decoded
     *   @return the character three earlier in the alphabet, with wrap-around
     */
    @Override
    public char decode ( char ch){
        //Basically copied and pasted the same as encryption just reversed the roles
        if(Character.isLetter(ch))
        {
            int index = OUTER.indexOf(ch);
            char middleChar= MIDDLE.charAt(index);
            int outerChar = OUTER.indexOf(middleChar);

            if(Character.isUpperCase(ch))
            {
                index = OUTER.toUpperCase().indexOf(ch);
                //System.out.println(index);
                middleChar = MIDDLE.toUpperCase().charAt(index);
                //System.out.println(middleChar);
                outerChar = OUTER.toUpperCase().indexOf(middleChar);
                //System.out.println(outerChar);
                ch = INNER.toUpperCase().charAt(outerChar);

                rotate();
                return ch;
            }
            ch = INNER.charAt(outerChar);

            rotate();
            return ch;
        }
        rotate();
        return ch;
    }

    private void rotate(){
//INNER ROTOR TURNING
        //The loop pushes the char once to the left
        for(int i = 0; i < 1; i++)
        {
            Collections.rotate(innerArray, 1); //Moves the first value clockwise
            //System.out.println(innerArray.toString());
        }

        // create object of StringBuilder class
        StringBuilder sb = new StringBuilder();
        // Appends characters one by one
        for (Character ch : innerArray)
        {
            sb.append(ch);
        }
        INNER = sb.toString();
        //System.out.println(INNER);

//MIDDLE ROTOR TURNING
        String s = String.valueOf(innerArray.get(0));
        //System.out.println(s);
        if(s.equals("#"))
        {
            for(int j = 0; j < 1; j++)
            {
                Collections.rotate(middleArray, 1); //Moves the first value clockwise
                //System.out.println(middleArray.toString());
            }
            // create object of StringBuilder class
            StringBuilder bs = new StringBuilder();
            // Appends characters one by one
            for (Character chs : middleArray)
            {
                bs.append(chs);
            }
            MIDDLE = bs.toString();
        }

    }


}
