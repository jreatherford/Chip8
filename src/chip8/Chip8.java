package chip8;
import java.io.*;

/**
 * CHIP-8 Emulator
 * @author James Reatherford
 */
public class Chip8 {

    private static final int loadPoint = 0x200;
    
    private static char[] memory;
    private static char PC;
    private static char I;
    
    private static byte[] register;
    
    private static char[] stack;
    private static int stackPointer;
    
    public static void main(String[] args) {
        
        register = new byte[16];
        stack = new char[16];
        stackPointer = 0;
        
        loadProgram ("Progs/BRIX");
        
        printProgramData ();
        
        for (PC = (loadPoint - 1); PC < memory.length; PC ++)
        {
            //Do stuff
        }
    }
    
    
    /*
    * Reads a program from a file and loads it into memory
    * @param the path of the file to be loaded
    */
    public static void loadProgram (String path)
    {
        File programFile = new File(path);
        FileInputStream inFile;

        try
        {
            inFile = new FileInputStream(programFile);
            
            //we only need to make the memory as big as the program
            memory = new char[loadPoint + inFile.available()/2];
            
            //stores the program as a char array
            int i = 0;
            while (inFile.available() > 0)
            {
                memory[(loadPoint + i)] = (char)((inFile.read() << 8) | (inFile.read()));
                i++;
            }
            
        }
        catch (IOException e)
        {
            System.out.println("Error loading file");
        }  
    }
    
    /*
    * Prints the program's hexcode in a (relatively) east to read format
    */
    public static void printProgramData ()
    {
        System.out.println("Registers:");
        for (int i = 0; i < register.length; i++)
        {
            System.out.printf("%2s",Integer.toHexString(register[i]));
            if (i%4 == 3)
                System.out.println();
            else
                System.out.print("\t");
        }
        
        System.out.println("\nProgram Memory:");
        for (int i = loadPoint; i < memory.length; i++)
        {
            System.out.printf(Integer.toHexString(memory[i]));

            //formatting
            if (i%4 == 3)
                System.out.println();
            else
                System.out.print("\t");
        }
    }
    
}