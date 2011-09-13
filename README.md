ZwoEinsRisiko
=============

ZwoEinsRisiko is a Java version of the Risk board game.

Install Instructions
--------------------

Download these dependencies:

 + [SWT](http://archive.eclipse.org/eclipse/downloads/)

    + Select newest Eclipse build and find the SWT section.
    + Example: [SWT 3.6.1](http://archive.eclipse.org/eclipse/downloads/drops/R-3.6.1-201009090800/#SWT)
    + Then download the appropriate version of SWT for your platform.

 + [SIMON](http://dev.root1.de/projects/simon/files)

    + Download SIMON with dependencies
    + Example: [simon-1.1.2-jar-with-dependencies.jar](http://dev.root1.de/attachments/download/120/simon-1.1.2-jar-with-dependencies.jar)

Then setup the environment:

 1. Import your platform's version of SWT (Windows, Mac, Linux, etc.) as a project into Eclipse
 2. Import ZwoEinsRisiko as a project into Eclipse
 3. Make sure that the SWT project is included in ZwoEinsRisiko's build path
 4. Make sure that the SIMON jar file is included in ZwoEinsRisiko's build path
 5. Start the server:
    + src/server/AppServer.java
 6. Start the client:
    + src/gui/AppClient.java

Sorry, I don't know of an easier process. I could create a jar file but it wouldn't be cross-platform because I can only package one version of SWT at a time. If I packaged the Windows version of SWT, the jar file wouldn't work on Mac OS X or Linux and vice versa. To add insult to injury, there are also separate x86 and x64 versions of SWT, which would require me to package six jar files to cover all platforms.

Authors
-------

 + Hendrik Druse
 + Jannes Meyer
 + Timur Teker