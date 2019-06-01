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
import java.util.Collection;
import java.util.Collections;


public class RotatingCipher extends SubstitutionCipher implements Cipher{

	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	ArrayList<Character> moveToArray = new ArrayList<Character>();


	public RotatingCipher(String key)
	{
		this.SHIFTED = key;

		//Put the string key into a char array so that i could easily move the first letter to the end
		//I then converted this array back into a string in the Rotate() method
		for (char ch : SHIFTED.toCharArray()) {
			moveToArray.add(ch);
		}

	}

	public char encode ( char ch){
		//Put all the code into checking if its a letter, if its not return the non letter
		if(Character.isLetter(ch))
		{
			int index = ALPHABET.indexOf(ch); //Default lowercase
			if (Character.isUpperCase(ch)) //Checks to see if the char is uppercase
			{
				//If uppercase make the String containing the Alphabet Uppercase
				index = ALPHABET.toUpperCase().indexOf(ch);
				//Calling the method after the first initial decode / encode so that it can use the newly shifted key
				rotate();
				return ch = SHIFTED.toUpperCase().charAt(index); //Returns the uppercase shifted words
			}
			rotate();
			return ch = SHIFTED.charAt(index); //Default lowercase shifted words
		}
		rotate();
		return ch;
	}

	/**
	 * Decodes a single character.
	 *   @param ch the character to be decoded
	 *   @return the character three earlier in the alphabet, with wrap-around
	 */
	public char decode ( char ch){
		//Basically copied and pasted the same as encryption just reversed the roles
		if(Character.isLetter(ch))
		{
			int index = SHIFTED.indexOf(ch);

			if(Character.isUpperCase(ch))
			{
				index = SHIFTED.toUpperCase().indexOf(ch);
				//Calling the method after the first initial decode / encode so that it can use the newly shifted key
				rotate();
				return ch = ALPHABET.toUpperCase().charAt(index); //Returns the uppercase shifted words

			}
			rotate();
			return ch = ALPHABET.charAt(index); //Default lowercase shifted words
		}
		rotate();
		return ch;
	}

	private void rotate(){
		//System.out.println(INNER);

		//The loop pushes the char once to the left
		for(int i = 0; i < 1; i++)
		{
			Collections.rotate(moveToArray, -1); //Moves the first value to the last index of the array
			//System.out.println(innerArray.toString());
		}

		//Had some issues with this particular part in the assignment, the main issue (fixed now) was that i was pushing them successfully
		//but wasn't using the new pushed key. So my solution was to turn the char arraylist into a string using string builder and then
		//assigning that string the same name as the previous key i.e INNER. This fixed my issue and it decrypts successfully.

		// create object of StringBuilder class
		StringBuilder sb = new StringBuilder();
		// Appends characters one by one
		for (Character ch : moveToArray)
		{
			sb.append(ch);
		}
		SHIFTED = sb.toString();
		//System.out.println(INNER);
	}

}
