# CLI/TERMINAL GOOGLE TRANSLATE
## WHAT THIS IS
This is a utility, not a replacement for the real [Google Translate](htpps://translate.google.com). It is simply a tool that **helps cut down the hustle of opening a browser just to translate one word or phrase.**.  
## NOTE
This installation as of now is more geared towards Linux machines, specifically ones that have a JDK/ JVM installed on them.  
## INSTALLATION
### PREREQUISITES
- The current installation process **requires that a JDK or a JVM be installed on the target machine**.
- Basic knowledge of terminal commands.
- A RAPID API key, and a subscription (it's a **freemium**) to use the Google Translate API. To get one, click [here](https://https://rapidapi.com/googlecloud/api/google-translate1/) 
- Some basic Java (or any programming language) knowledge.
- A Linux OS. This installation is specifically for Linux.
>Note that if you download the `cligoogletrans-1.0.jar` file in this repository, **you will be capped to translate only 500 CHARACTERS per month**.
>**You will also be using my API key for translation** which may bill me. The multiple people that have downloaded the same will be fast depleting the 500 char cap. So consider using procedure A along with your own API key to self-manage these issues. \
>For those who go ahead and download the jar file from this repository, feel free to skip to procedure A number 9, but take note of the hereby mentioned issues.

### PROCEDURE A
1. Download the download the `cligoogletrans-sourcecode.zip` file from this repository.
2. Extract the zip file an you should get a `cligoogletrans-sourcecode` folder.
3. In this folder, navigate to `src/main/java/npc/martin/cligoogletrans/`. In there, there should be a number of Java source files. We will deal with only two.
4. Open the `HandleSingleTarget.java` file in any text editor an navigate to the lines 18-21. On line 21 specifically, replace _API KEY REMOVED FOR SECURITY REASONS- USE YOUR RAPID API KEY_ with your RAPID API key like this:
```
.header("content-type", "application/x-www-form-urlencoded")
    .header("Accept-Encoding", "application/gzip")
    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
    .header("X-RapidAPI-Key", "YOUR RAPID API KEY HERE")
```
5. Save this file an close. Then open the `HandleMultiTargets.java` file and similarly edit the String on line 32 with your onw API key like this:
```
.header("content-type", "application/x-www-form-urlencoded")
    .header("Accept-Encoding", "application/gzip")
    .header("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
    .header("X-RapidAPI-Key", "YOUR RAPI API KEY HERE")
```
6. Save the file an exit then navigate back outside the parent folder `cligoogletrans-sourcecode`. Rename the folder as `cligoogletrans`.
7. Compile time. You need [Maven](https://maven.apache.org) for this compileation. If you don't have it installed, please consider installing it using your system's package manager. Go back into the renamed folder and run `mvn clean package` to compile and package the package the project.
8. You should now see a `target` folder. Navigate into it and there should be two jar files.  Copy the `cligoogletrans-1.0-SNAPSHOT.jar` jar file somewhere else and rename it to `cligoogletrans-1.0.jar`. This is the full package you need to use the utility.
9. To test the utility jar file, navigate to the folder where the copy (or download if you simply downloaded it from the repo) was placed and run the command `java -jar cligoogletrans-1.0.jar -h`. It should display a simple help message like this:
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
For those who downloaded the `cligoogletrans-1.0.jar`, as well as those who have come from the end of procedure A, proceed from here.
1. Copy the renamed (or downloaded) jar file into your `/home/{user}` directory, or in the directory where you store you custom scripts. For me, I have a folder called `.custom_scripts` in my `/home/{user}` directory. We are going to edit the `.bashrc` file with this information.`
2. Open the `.bashrc` file in your favorite text editor, I will use nano. It(the `.bashrc`) is located in the same `/home/{user}` loacation. So if you're using the terminal, the command would be `nano ~/.bashrc`. If you're using gedit or kate or any other text editor it would be `{text_editor_name} ~/.bashrc` and hit enter.
3. Once the file is opened, navigate to the very bottom (or to your aliases section) an add the following alias:
```
#ALIAS
alias ggtranslate="java -jar ~/{path to where you copied the jar file}/cligoogletrans-1.0.jar"
```
For example, my jar is copied into the `/home/.custom_scripts/` folder, so my alias would look like this:
```
alias ggtranslate="java -jar ~/.custom_scripts/cligoogletrans-1.0.jar"
```
4. Save the file and exit. To effect the changes we have to recompile the `.bashrc` file, so either close and re-open your terminal, or run `source ~/.bashrc` and hit enter. Both ways work. So now the install is complete. So now let's see how to use the utility.

## USING THE UTILITY
### EXAMPLE COMMAND
Because we have given the `java -jar cligoogletrans-1.0.jar` command a special alias, which is the actual command for the utility, we're going to use the alias name instead of the longer command.
Here is an example of how this works:
From your terminal, enter the command `ggtranslate -p="Hello World" -lc es` and hit enter. After a few seconds, you get a response that looks like this:
```
Entered Source Text: Hello World
Detected Source Language: Spanish
Translated Text: Hola Mundo
```
We just translated `Hello World` into Spanish, without using a browser.

### COMMAND AND OPTIONS
To use this utility, invoke the `ggtranslate` command, which is the alias we set in the bash script. 
The utility uses language codes to reffer to the human way of calling different langauges. For example, we humans know the word 'French' reffers to the 'French' language. To reffer to this language in this utility, we would use `fr` (lowercase) instead of the full word `French`.
The command cannot be used alone and must be coupled with either one of the following options:
1. `-h`: Prints out a help message for this utility.
2. `-V`: Gives you the version number of the utility.
3. `-p`: An option that allows you give the text/ phrase you want to translate. Give the phrase after an assignment in quotations for example `ggtranslate -p="Hello World"` or you can drop the assignment for example `ggtranslate -p "Hello World"`. Generally, if your phrase is more than one word, use quotations.
4. `-i`: This option helps you avoid using all other flags, and instead offers you an interractive mode (answer a series of questions) to get your phrase translated. To se it, jus type `ggtranslate -i` and hit enter. Here is a screenshot of the interractive mode in action:
![Screeshot of interractive mode](https://drive.google.com/file/d/1ABzeqYas9tQnToZPG0LQLPMyoc8QMdv0/view?usp=sharing)
5. `-lc`: Use this if you want to translate to only one language. For example, continuing the code from number 3, it would be `ggtranslate -p "Hello World" -lc fr` if we want to translate Hello World to French. You should not use quotation marks or assignment here.
6. `-lcs`: This allows you to specify multiple (upto 5) languages you want to translate to. **If you give more than 5 or less than 1, it will produce an error**. So if we wanted to translate the example on number 3 into French, Russian and English, the code would be `ggtranslate -p "Hello World" -lcs fr ru en`. The options are not specified in quotes or after an assignment.

### RULES FOR USING OPTIONS
Keep these in mind when using these command options
1. The `-i` flag **cannot** be used with any other flag. It simply launches the interactive mode
2. In all other cases(i.e **when the `-i` flag is not used**), the `-p` flag must be used.
3. When the `-p` flag is used, **either** the `-lc` or the `-lcs` flag should be used alongside, **but not both at once**.
4.When the `-lcs` flag is used, it **must have not more than 5 parameters and not less than 1**.

## LANGUAGE CODES
As before mentiond, we use **language codes** to specify which languages we want to translate into. 
Not all world languages language codes currently work in this utility. Because of the _freemium_ nature of the Google Translate API, this utility has a limit (33) as to how many languages you can translate into. You can however add your own languages support to the code. Use the _world-languages-ordered-by-code_ pdf in this repo for making language code refferences.
Check out the [Language Codes](https://github.com/LunarkX/cli-google-translate/blob/master/Language%20Codes.txt) list to see a list of the supported languages.

## KNOWN ISSUES
The following are known issues that will be fixed with later updates:
1. When the `ggtranslate` command is used without issuing any option, it throws an error like this:
```
Fatal! The -p flag must be used when -i is not used. Use -h for help.
```
2. There is no progress bar/ progress indication mechanism to show as the translation process happens, especially when translating to more than one langages at once.
3. The is no automatic way to document/ log a user's translation job to their local stprage for later refference.
4. There is a cap on how many characters you can translate in a month. After a paid upgrade to the API, this will be increased accordingly.
