package npc.martin.cligoogletrans;

import java.util.Scanner;

public class InterractiveHandler extends Translate {
    //must provide at least one target language
    private Integer numberOfTargets = 1;
    private final Scanner s1 = new Scanner(System.in);
    
    //method to handle taking user input in interractive mode
    protected void takeInput() {
        System.out.println("Starting utility in interractive mode ...");
        
        System.out.print("Phrase to translate: ");
        phrase = s1.nextLine();
        
        System.out.print("How many languages are you translating to? [Least is 1]: ");
        numberOfTargets = s1.nextInt();
        
        if(numberOfTargets == 1) {
            System.out.print("Enter target language code: ");
            langCode = s1.next();
        } else {
            for(int i = 1; i <= numberOfTargets; i++) {
                System.out.print("Enter language code for language " + i + ": ");
                String lc;
                lc = s1.next();

                langCodes.add(lc);
            }
        }
        
        s1.close();
    }
}
