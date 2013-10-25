package com.bfwdev.news;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class EcrireFichierTexte
{
	
	public void ecrire(String message){
		PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter("fichier.txt"));
			ecri.println(message);
			ecri.flush();
			ecri.close();
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a)
		{
			System.out.println("Problème d'IO");
		}
	}
  public static void main(String[] argv) throws IOException
  {
	  PrintWriter ecri ;
		try
		{
			ecri = new PrintWriter(new FileWriter("fichier.txt"));
			ecri.println("test");
			ecri.flush();
			ecri.close();
		}//try
		catch (NullPointerException a)
		{
			System.out.println("Erreur : pointeur null");
		}
		catch (IOException a)
		{
			System.out.println("Problème d'IO");
		}

}
}
