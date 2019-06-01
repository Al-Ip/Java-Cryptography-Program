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

import java.io.*;
import java.util.Scanner;


public class Decrypter{
    
    Cipher cipher = null;
    
    public Decrypter(Cipher cipher){
        this.cipher = cipher;
    }
    
    public void decrypt(String input){
        decrypt(input, null);
    }
    
    public void decrypt(String input, String outFile){
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
                ch = cipher.decode(ch);
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

    public static void main(String[] args) throws FileNotFoundException {
        //String path = "C:\\Users\\alexi\\Desktop\\College\\Year2\\Semester 2\\Algorithms 2\\Assignment 2\\src\\com_company";
        //Decrypter dec = new Decrypter(new Enigma("#GNUAHOVBIPWCJQXDKRYELSZFMT", "#EJOTYCHMRWAFKPUZDINSXBGLQV"));
        //Decrypter de = new Decrypter(new RotatingCipher("qwertyuiopasdfghjklzxcvbnm"));
        //dec.decrypt(path + "inTest.txt.enc");
        //enc.encrypt("HELLO#WORLD

//Part 1
        Decrypter dec = new Decrypter(new CaesarCipher());
        System.out.println("-------------PART 1 - The Caesar Cipher -------------");
        System.out.print("(1) - 'khoorzruog' Decryption = ");
        dec.decrypt("khoorzruog"); //Test for lowercase only
        System.out.println("");

        System.out.print("(2) - 'KHOORZRUOG' Decryption = ");
        dec.decrypt("KHOORZRUOG"); //Test for Uppercase only
        System.out.println("");

        System.out.print("(3) - 'KHOOR#ZRUOG' Decryption = ");
        dec.decrypt("KHOOR#ZRUOG"); //Test for uppercase and non letters
        System.out.println("");

//Part 2
        Decrypter decSub = new Decrypter(new SubstitutionCipher("qwertyuiopasdfghjklzxcvbnm")); //Subbed the specific key here
        System.out.println("\n-------------PART 2 - Substitution Ciphers -------------");
        System.out.print("(1) - 'Pqcq egrt' Decryption = ");
        decSub.decrypt("Pqcq egrt");
        System.out.println("");

        System.out.print("(2) - 'Ziol Vgkatr, O Ight?' Decryption = ");
        decSub.decrypt("Ziol Vgkatr, O Ight?");
        System.out.println("");

//Part 3
        Decrypter rotaCipher = new Decrypter(new RotatingCipher("qwertyuiopasdfghjklzxcvbnm")); //Subbed the specific key here
        System.out.println("\n-------------PART 3 - Simple Rotating Ciphers -------------");
        //System.out.println(file.exists());
        System.out.println("(1) - Decrypting the Unknown.txt file = ");
        rotaCipher.decrypt("unknown.txt");

//Part 4
        Decrypter enigMod = new Decrypter(new Enigma("#GNUAHOVBIPWCJQXDKRYELSZFMT", "#EJOTYCHMRWAFKPUZDINSXBGLQV"));
        System.out.println("\n-------------PART 4 - A Simple Enigma Model -------------");
        System.out.print("(1) - 'OKNNWRDHGERPILRLAMFZF#FMUC' Decryption = ");
        enigMod.decrypt("OKNNWRDHGERPILRLAMFZF#FMUC");

    }
}
