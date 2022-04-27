package npc.martin.cligoogletrans;

import kong.unirest.Unirest;

public class HandleSingleTarget extends TranslationPrerequisites implements TranslationPrerequisites.SingeTargetRequest {

    @Override
    public void makeRequest(String ph, String tar) {
        //first we confirm that the langCode exists in our list of supported languages
        if(languageCodePair.containsKey(tar)) {
            //if it exists, build the String for the HTTP request body, and store the key value in a String somewhere
            String bodyString = "q=" + ph + "&target=" + tar;
            String targetLanguage = languageCodePair.get(tar);
            
            //then create and send the request- the returned response is a GoogleTranslate JSON
            //so we map the request to that using Unirest's inbuilt mapper.
            GoogleTranslateObject translateObj = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "663ca5a111msh7c4791d9dff1181p132acdjsn58c849b9b499")

                .body(bodyString)
                .asObject(GoogleTranslateObject.class)
                .getBody();
            
            destructureJson(ph, targetLanguage, translateObj);
            
        } else {
            System.out.println("Fatal! Language code(" + tar + ") not supported. See LanguageCodes file for help on supported languages. \n");
        }
    }

    @Override
    public void destructureJson(String sourceText, String targetLang, GoogleTranslateObject transJSON) {
        System.out.println("Entered Source Text: " + sourceText);
        
        String detectedSrcLang = transJSON.getData().getTranslations().get(0).getDetectedSourceLanguage();
        System.out.println("Detected Source Language: " + languageCodePair.get(detectedSrcLang));
        
        String translatedTextString = transJSON.getData().getTranslations().get(0).translatedText;
        System.out.println("Translated Text[" + targetLang + "]: " + translatedTextString);
    }   
}
