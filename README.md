# MOOCTextEditor
https://github.com/MargaritaOstrovskaia/MOOCTextEditor/wiki/TextEditor

Add JUnit to your project. 
To do this, go to Project->Properties. 
Select "Java Build Path". 
Select the "Libraries" tab and "Add Library". 
Select JUnit, then JUnit 4.

Set up the workspace JRE. 
Right click on the MOOCTextEditor project folder in the Package Explorer and select Build Path->Configure Build Path. 
Go on the Libraries tab and click on "Add Library". 
Select the "JRE System Library" and click Next. 
Select "Workspace default JRE" and click Finish. 
Then click OK.

You need to install JDK 8. After you've installed JDK 8, switch to JDK 8 in Eclipse. 
At this point, the permissions errors will still be there, so go to File -> Properties -> Java Build Path. 
Here, you'll see at the top "no access rule defined." 
You'll want to click "edit.." on that field. 
Once you've opened that, you'll want to click "add," change the drop down at the top from "Forbidden" to "Accessible," and then type "javafx/**" as the rule pattern. 
Apply and save, and now the startup code will work.
