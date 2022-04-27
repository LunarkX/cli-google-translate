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
            
            //because the user is targeting only one language, we call the method responsible
            //for handling such a request after creating the language-language_code pairs
            //we will also take note of the time(seconds) it takes the codes to run
            System.out.println("----------------------------------------------");
            start = System.nanoTime();
            TranslationPrerequisites.createPairs();
            new HandleSingleTarget().makeRequest(phrase, langCode);
            end = System.nanoTime();
            timeInSeconds = (long) ((end - start) / 1000000000);
            System.out.println("Took " + timeInSeconds + " seconds.");
            
        } else {
            for(int i = 1; i <= numberOfTargets; i++) {
                System.out.print("Enter language code for language " + i + ": ");
                String lc;
                lc = s1.next();

                langCodes.add(lc);
            }
            
            //once we have captured all the desired language codes, we call the class that
            //is responsible for handling multiple target requests after creating the
            //language-language_code pairs
            System.out.println("----------------------------------------------");
            start = System.nanoTime();
            TranslationPrerequisites.createPairs();
            new HandleMultiTargets().makeMutipleRequests(phrase, langCodes);
            end = System.nanoTime();
            timeInSeconds = (long) ((end - start) / 1000000000);
            System.out.println("Took " + timeInSeconds + " seconds.");
        }
        s1.close();
    }
}
