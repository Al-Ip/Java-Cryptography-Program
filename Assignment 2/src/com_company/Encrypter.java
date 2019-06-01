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

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

public class Encrypter{

    Cipher cipher = null;

    public Encrypter(Cipher cipher){
        this.cipher = cipher;
    }

    public void encrypt(String input){
        encrypt(input, null);
    }
    
    public void encrypt(String input, String outFile){
        File file = new File(input);
        BufferedReader br = null;
        boolean printToFile = false;
        
        PrintStream out = System.out;
        PrintStream fout = null;
        
        try{
            if (file.exists() && !file.isDirectory()){
                br = new BufferedReader(new FileReader(file));
            }
            else{
                br = new BufferedReader(new StringReader(input));
            }
            
            if (outFile != null){
                printToFile = true;
                fout = new PrintStream(outFile);
            }
            
            char ch;
            int c;
            // Read each char until EOL or EOF
            while((c = br.read()) != -1){
                // Encrypt it and print out
                ch = (char) c;
                ch = cipher.encode(ch);
                out.print(ch);
                if (printToFile){
                    fout.print(ch);
                }
            }
            if (br != null){
                br.close();
            }
            if (printToFile){
                fout.close();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String [] args){
        String path = "C:\\Users\\alexi\\Desktop\\College\\Year2\\Semester 2\\Algorithms 2\\Assignment 2\\src\\com_company";
        //Encrypter enc = new Encrypter(new Enigma("#GNUAHOVBIPWCJQXDKRYELSZFMT", "#EJOTYCHMRWAFKPUZDINSXBGLQV"));
        //Encrypter enc = new Encrypter(new RotatingCipher("qwertyuiopasdfghjklzxcvbnm"));
        //enc.encrypt(path + "inTest.txt", path + "inTest.txt.enc");

//Part 1
        Encrypter enc = new Encrypter(new CaesarCipher());
        System.out.println("-------------PART 1 - The Caesar Cipher -------------");
        System.out.print("(1) - 'helloworld' Encryption = ");
        enc.encrypt("helloworld"); //Test for lowercase only
        System.out.println("");

        System.out.print("(2) - 'HELLOWORLD' Encryption = ");
        enc.encrypt("HELLOWORLD"); //Test for Uppercase only
        System.out.println("");

        System.out.print("(3) - 'HELLO#WORLD' Encryption = ");
        enc.encrypt("HELLO#WORLD"); //Test for uppercase and non letters
        System.out.println("");

//Part 2
        Encrypter encSub = new Encrypter(new SubstitutionCipher("qwertyuiopasdfghjklzxcvbnm")); //Subbed the specific key here
        System.out.println("\n-------------PART 2 - Substitution Ciphers -------------");
        System.out.print("(1) - 'Java code' Encryption = ");
        encSub.encrypt("Java code");
        System.out.println("");

        System.out.print("(2) - 'This Worked, I Hope?' Encryption = ");
        encSub.encrypt("This Worked, I Hope?");
        System.out.println("");

//Part 4
        //I tried implementing my own
        Encrypter enigMod = new Encrypter(new Enigma("#GNUAHOVBIPWCJQXDKRYELSZFMT", "#EJOTYCHMRWAFKPUZDINSXBGLQV"));
        System.out.println("\n-------------PART 4 - A Simple Enigma Model -------------");
        System.out.print("(1) - 'Im glad this is All Over Now! :)' Encryption = ");
        enigMod.encrypt("I'M GLAD THIS IS ALL OVER NOW! :)");
    }
}