# CLI/TERMINAL GOOGLE TRANSLATE
## WHAT THIS IS
This is a utility tool, not a 100% replacement for the real [Google Translate](htpps://translate.google.com). It is simply a tool that **helps cut down the hustle of opening a browser just to translate one word**.  
## NOTE
This tool (utility) was never esigned to be installed on any machine except mine at the beginning. 
However, over time I saw it could be useful and designed it to work not just for me. 
## INSTALLATION
### PREREQUISITES
- The current installation process **requires that a JDK or a JVM be installed on the target machine**.
- Basic knowledge of terminal commands.
- A RAPID API key, and a subscription to use the Google Translate API. To get one, click [here](https://https://rapidapi.com/googlecloud/api/google-translate1/) 
- Some Java (or any programming language) knowledge.
- A Linux OS. This installation is specifically for Linux.
>Note that if you download the `cligoogletrans-1.0.jar` file in this repository, **you will be capped to translate only 500 characters per month**.
>**You will also be using my API key for translation** which may bill me. Also, the multiple people that have downloaded the same will be fast depleting the 500 char cap. So consider using the first procedure described below for maximum comfortability when using this utility. Otherwise, proceed direct to procedure B, ignoring procedure A.

### PROCEDURE A
1. Download the download the source code zip file from this repository.
2. Extract the zip file an you should get a `cli-google-translate-master` folder inside.
3. In the folder, navigate to `src/main/java/npc/martin/cligoogletrans/`. In there, there should be a number of Java source files. We will deal with only two.
4. Open the `HandleSingleTarget.java` file in any text editor an navigate to the lines 18-21. Online 21 specifically, replace _API KEY REMOVED FOR SECURITY REASONS- USE YOUR RAPID API KEY_ with your RAPID API KEY like this:
```
.header("content-type", "application/x-www-form-urlencoded")
    .header("Accept-Encoding", "application/gzip")
    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
    .header("X-RapidAPI-Key", "YOUR RAPID API KEY HERE")
```
5. Save this file an close. Then open the `HandleMultiTargets.java` file and edit the same String on line 32 like this:
```
.header("content-type", "application/x-www-form-urlencoded")
    .header("Accept-Encoding", "application/gzip")
    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
    .header("X-RapidAPI-Key", "YOUR RAPI API KEY HERE")
```
6. Save the file an exit then navigate back to the parent folder `cli-google-translate-master`. Rename the folder as `cligoogletrans`.
7. Go into the rename folder and run `mvn clean package` to compile and package the package the project.
8. You should now see a `target` folder. Navigate into it an there should be two jar files.  Copy the `cligoogletrans-1.0-SNAPSHOT.jar` file somewhere else an rename it to `cligoogletrans-1.0.jar`. This is the full package you need to use the utility.
9. To test the utility jar file, navigate to the folder where the download was placed and run the command `java -jar cligoogletrans-1.0.jar -h`. It should display a simple help message like this:
```
Usage: ggtranslate [-hiV] [-lc=LANGUAGE CODE] [-p=PHRASE] [-lcs=MULTIPLE 
                   LANGUAGE CODES...]...
Starts this Google translate utility to help you translate words/ phrases
  -h, --help            Show this help message and exit.
  -i, --interractive    Run this utility in interractive mode.
      -lc, --language-code=LANGUAGE CODE
                        Language being translated to.
      -lcs, --language-codes=MULTIPLE LANGUAGE CODES...
                        Choose up to five languages to translate into.
  -p, --phrase=PHRASE   Phrase/ word to be translated.
  -V, --version         Print version information and exit.
```
10. Now that we have confirmation the tool works, let's install it in a more use-case-friendly way. Proceed to procedure B.

### PROCEDURE B
For those who downloaded the `cligoogletrans-1.0.jar`, as well as those who hae come from the end of procedure A, proceed from here.
1. Copy the renamed (or downloaded if you downloaded it) jar file into your `/home/{user}` directory, or in the directory where you store you custom scripts. For me, I have a folder called `.custom_scripts` in my `/home/{user}` directory. We are going to edit the `.bashrc file with this information.`
2. Open the `.bashrc` file in your favorite text editor, I will use nano. It is located in the same `/home/{user}` loacation. So if your using the terminal, the command would be `nano ~/.bashrc` or if your using gedit or kate or any other text editor it would be `{text_editor_name} ~/.bashrc` and hit enter.
3. One the file is opened, navigate to the very bottom an add the following alias:
```
#ALIAS
alias ggtranslate="java -jar ~/{path to where you copied the jar file}/cligoogletrans-1.0.jar"
```
4. Save the file and exit. To effect the changes we have to recompile the `.bashrc` file, so either close an re-open your terminal, or run `source ~/.bashrc` and hit enter. Both ways work. So now the install is complete. So now let's see how to use the utility.

## USING THE UTILITY
