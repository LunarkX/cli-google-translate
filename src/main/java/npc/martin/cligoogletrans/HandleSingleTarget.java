package npc.martin.cligoogletrans;

import kong.unirest.Unirest;

public class HandleSingleTarget extends TranslationPrerequisites implements TranslationPrerequisites.SingeTargetRequest {

    @Override
    public void makeRequest(String ph, String tar) {
        //first we confirm that the langCode exists in our list of supported languages
        if(languageCodePair.containsKey(tar)) {
            //if it exists, build the String for the HTTP request body
            String bodyString = "q=" + ph + "&target=" + tar;
            
            //then create and send the request- the returned response is a GoogleTranslate JSON
            //so we map the request to that using Unirest's inbuilt mapper.
            GoogleTranslateObject translatedObj = Unirest.post("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .header("content-type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "application/gzip")
                .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .header("X-RapidAPI-Key", "663ca5a111msh7c4791d9dff1181p132acdjsn58c849b9b499")

                .body(bodyString)
                .asObject(GoogleTranslateObject.class)
                .getBody();
            
            destructureJson(ph,translatedObj);
            
        } else {
            System.out.println("Error! Target language not supported. See LanguageCodes file for help on supported languages.");
        }
    }

    @Override
    public void destructureJson(String sourceText, GoogleTranslateObject transJSON) {
        String translatedTextString = transJSON.getData().getTranslations().get(0).translatedText;
        System.out.println("Entered Source Text: " + sourceText);
        System.out.println("Detected Source Language: " + transJSON.getData().getTranslations().get(0).getDetectedSourceLanguage());
        System.out.println("Translated Text: " + translatedTextString);
    }   
}
