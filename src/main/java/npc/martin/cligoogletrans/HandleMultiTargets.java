package npc.martin.cligoogletrans;

import java.util.Iterator;
import java.util.List;
import kong.unirest.Unirest;

public class HandleMultiTargets extends TranslationPrerequisites implements TranslationPrerequisites.MultiTargetRequest {

    @Override
    public void makeMutipleRequests(String ph, List<String> lc) {
        //first print out the entered source text
        System.out.println("Entered Source Text: " + ph);
        System.out.println("------------------------------------------------");
        //we need an iterator to go through the List of target codes
        Iterator it = lc.iterator();
        
        //we will go through the list picking one item at a time
        while(it.hasNext()) {
            String key = (String) it.next(); //convert the list item to a String object that's actually a HashMap key
            
            //check if key(targetted language code) exists/ is supported
            if(languageCodePair.containsKey(key)) {
                //if it exists, build the String for the HTTP request body,  and store the key value in a String somewhere
                String bodyString = "q=" + ph + "&target=" + key;
                String targetLanguage = languageCodePair.get(key);
                
                //then create and send the request to the API
                GoogleTranslateObject translateObj = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("Accept-Encoding", "application/gzip")
                    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                    .header("X-RapidAPI-Key", "API KEY REMOVED FOR SECURITY REASONS- USE YOUR RAPID API KEY")

                    .body(bodyString)
                    .asObject(GoogleTranslateObject.class)
                    .getBody();
                
                //take the JSON from the response and destructure it
                destructureJson(targetLanguage, translateObj);
            } else {
                System.out.println("Error! Language code(" + key + ") not supported. See LanguageCodes file for help on supported languages. \n");
            }
        }
    }

    @Override
    public void destructureJson(String targetLang, GoogleTranslateObject transJSON) {
        //print out the detected language from the user's phrase
        String detectedSrcLang = transJSON.getData().getTranslations().get(0).getDetectedSourceLanguage();
        System.out.println("Detected Source Language: " + languageCodePair.get(detectedSrcLang));
        
        //and print out the translated version of the phrase
        String translatedTextString = transJSON.getData().getTranslations().get(0).translatedText;
        System.out.println("Translated Text[" + targetLang + "]: " + translatedTextString + "\n");
    }
}
