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

public class SubstitutionCipher implements Cipher{
	
	private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
	String SHIFTED = "";

	protected SubstitutionCipher(){
		
	}
	
	public  SubstitutionCipher(String key)
	{
		this.SHIFTED = key;
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
				return ch = SHIFTED.toUpperCase().charAt(index); //Returns the uppercase shifted words
			}
			return ch = SHIFTED.charAt(index); //Default lowercase shifted words
		}
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
				return ch = ALPHABET.toUpperCase().charAt(index);
			}
			return ch = ALPHABET.charAt(index);
		}
		return ch;
	}
}
