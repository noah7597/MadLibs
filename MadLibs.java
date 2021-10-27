//Noah H
//Program creates and saves games of mad-libs for a user
//MadLibs
//11-17-20

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.*;

public class MadLibs
{

  /**
   *   Description: The main method that coordinates all other methods
   *
   */
  
  public static void main(String[] args) throws FileNotFoundException
  {
    Scanner console = new Scanner(System.in);
    
    intro();     
     
    String option = userOption(console);
    
    while(true)
    {
      if(option.equalsIgnoreCase("c"))
      {
        create(console);
        option = userOption(console);
      }
      else if(option.equalsIgnoreCase("v"))
      {
        view(console);
        option = userOption(console);
      }
      else if(option.equalsIgnoreCase("q"))
      {
        System.out.println();
        System.out.println("Thanks for playing!");
        break;
      }
    }
  }
  
  /**
   *   Description: prints the introduction
   *
   */
  
  public static void intro()
  {
    System.out.println("Welcome to the game of Mad Libs.");
    System.out.println("I will ask you to provide various words");
    System.out.println("and phrases to fill in a story.");
    System.out.println("The result will be written to an output file.");
    System.out.println();
  }
  
  /**
   *   Description: Allows the user to decide whether they want to make a mad-lib, view a mad-lib, or quit
   *
   *   @param Scanner console is the paramater that allows the user to enter input to the computer
   *   
   *   @return choice returns the user's option (c, v, q)
   */
  
  public static String userOption(Scanner console)
  {
    System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
    String choice = console.next();
    
    if(choice.equalsIgnoreCase("c") || choice.equalsIgnoreCase("v") || choice.equalsIgnoreCase("q"))
    {
      return choice;
    }
    
    while(true)
    {
      System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
      choice = console.next();
        
      if(choice.equalsIgnoreCase("c") || choice.equalsIgnoreCase("v") || choice.equalsIgnoreCase("q"))
      {
        break;
      }

    }
    return choice;
  }
  
  /**
   *   Description: Controls the function of the program when the user makes a mad-lib
   *
   *   @param Scanner console is the paramater that allows the user to enter input to the computer
   *   
   */
  
  public static void create(Scanner console) throws FileNotFoundException
  {
    Scanner scan = MyUtils.getFileScanner(console);
    PrintStream output = new PrintStream(MyUtils.getWriterScanner(console));
    System.out.println();
        
    while(scan.hasNextLine())
    {
      String sentence = scan.nextLine();
      Scanner line = new Scanner(sentence);
      
      while(line.hasNext())
      {
        String word = line.next();
        
        if(word.startsWith("<") && word.endsWith(">"))
        {
          word = word.replace('<',' ');
          word = word.replace('>',' ');
          word = word.replace("-"," ");
          System.out.print("Please type " + article(word) + word + ": ");
          String fillBlank = console.next();
          output.print("" + fillBlank + " ");
        }
        
        else
        {
          output.print("" + word + " ");
        }
        
      }
 
    }
    
    System.out.println("Your mad-lib has been created!");
    System.out.println();
  }
  
  /**
   *   Description: Controls the function of the program when the user wants to view a mad-lib
   *
   *   @param Scanner console is the paramater that allows the user to enter input to the computer
   *   
   */
  
  public static void view(Scanner console) throws FileNotFoundException
  {
    Scanner scan = MyUtils.getFileScanner(console);
    
    while(scan.hasNextLine())
    {
      String sentence = scan.nextLine();
      System.out.println(sentence);
    }
    System.out.println();
  }
   
  /**
   *   Description: Determines whether the article will be "a" or "an" when asking for the word to fill in
   *
   *   @param String x represents the word before the article
   *   
   *   @return returns the article "a" or "an"
   */
  
  public static String article(String x)
  {
    String vowels = "aeiou"; //string of vowels
    
    for(int i = 0; i < 5; i++)
    {
      if(x.charAt(1) == vowels.charAt(i))
      {
        return "an";
      }
    }
    return "a";
  }

}