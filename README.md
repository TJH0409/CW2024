## GitHub: 
 
## Compilation Instructions: 
  ### Prerequisites

   - Java Development Kit (JDK):
   
    Ensure you have JDK 17 or later installed.

    Add the JDK bin directory to your system's PATH environment variable.

   - JavaFX SDK:

    Download the JavaFX SDK version 23.0.1 (or your required version) from the official website.

    Extract it to a location on your system .

   - Eclipse IDE:

    Install the latest version of Eclipse IDE for Java Developers.
   - Dependencies:

    The project requires JavaFX libraries. Ensure the lib folder in your JavaFX SDK is correctly referenced.

### Compilation Steps
    1. Clone the GitHub Repository
            Open a terminal or command prompt.
            Clone the repository 

    2. Import the Project into Eclipse
            Open Eclipse.
            Go to File → Import → Existing Projects into Workspace.
            Select the cloned repository folder and click Finish.
    3. Configure Build Path
            Right-click your project in the Project Explorer and select Properties.
            Go to Java Build Path → Libraries → Add External JARs.
            Navigate to the JavaFX lib directory and add all the .jar files.
    4. Set JavaFX Runtime Arguments
            Go to Run → Run Configurations.
            Select your project's main class or create a new run configuration.
            In the Arguments tab, under VM arguments, add:
            --module-path /path/to/JavaFX/lib --add-modules javafx.controls,javafx.fxml
    5. Compile and Run
        Click Run (green play button) in Eclipse.
        If configured correctly, your application will compile and run.

## Implemented and Working Properly: 

    - implement pause game function

    - fix endless popup caused by error InvocationTargetException

    - fix the shield image format to resolve error

    - implement horizontal movements(left right) for user plane

    - new enemy added to the game
  
    - added another set of movement (WASD) for player
  
    - added new level to the game

## Implemented but Not Working Properly: 
  
    - try to add a restart game function by pressing 'R' after the the incident explaind in the section [Unexpected Problems] but could not get it working (removed unused code)

## Features Not Implemented: 

    - endless mode that give player points at kill, display highest score (time issue hence not implemented)

    - random power-ups like invincible, double fire rate, increase movement speed occasionally drop when killing enemy(deleted, try to implement but causes error that i dont have enough time to fix)

    - main menu (explain in unexpected problems)

## New Java Classes: 

    - Shield.java - seperated from boss.java

    - BossConfig - seperated from boss.java

    - UserPlaneConfig - seperated from userplane.java

    - EnemyPlaneConfig - seperated from enemyplane.java

    - InputHandler - seperated from levelparent.java

    - EnemyPlane2Config - added a new type of enemy

    - EnemyPlane2 - new enemy
  
    - LevelTwo - added a new level with new type of enemy

## Modified Java Classes: 

    - split Boss.java into smaller files (BossConfig, Shield and Boss) for easier maintainance
  
    - split UserPlane.java into smaller files with UserPlanConfig and UserPlane for easier maintainance

    -  split EnemyPlane.java into smaller files (EnemyPlaneConfig and EnemyPlane) for easier maintainance
  
    - found and fix a typo in EnemyPlane.java (projectileYPostion to projectileYPosition)
  
    - fix the shield image format (jpg to png) in shieldimage.java

    - repackage and make changes to all relevant codes
  
    - add timeline.stop in goToNextLevel in LevelParent.java to fix endless popup

    - put all config.java files into a package and make changes to relevant codes

    - put all level.java files into a package and make changes to relevant codes

    - put all image.java files into a package and make changes to relevant codes
  
    - put all projectile files into a package and make changes to relevant codes
  
    - put remaining .java files (boss, userplane enemyplane etc) into a package - actor and make changes to all relevant codes

    - modified UserPlaneConfig.java, UserPlane.java and LevelParent.java to implement horizontal movement

    - split InputHandler.java from LevelParent.java for easier maintainance
  
    - added anther set of movement(WASD) 

    - added comments for generate Javadocs
  
    - rename LevelTwo, LevelViewLevelTwo to LevelBoss and LevelViewLevelBoss and all related codes

## Unexpected Problems: 
  - Got really sick in mid November and delayed all work (assignments, classes) by about a week
  - 5/12 
Windows file corrupted after Windows auto update and has to reset laptop to factory settings, updated codes (menu.fxml and related code changes) that is not pushed to github as I am still making changes to the code like position, background, etc.. is deleted. Wasted 2-3 days to get my laptop back to work but adding main menu to the game causes the game to throw error (idk why as the way I implemented it is the same). If I still couldnt fix this by Monday (9/12) I will not implement the main menu and can only revert to how the game originally transition to next level (after reaching kill count) else I might not complete the coursework in time.
