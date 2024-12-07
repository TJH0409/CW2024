## GitHub: Provide the link to your GitHub repository.

## Compilation Instructions: Provide a clear, step-by-step guide on how to compile the code to produce the application. Include any dependencies or special settings required.

## Implemented and Working Properly: List the features that have been successfully implemented and are functioning as expected. Provide a brief description of each.

  - implement pause game function

  - fix endless popup caused by error InvocationTargetException

  - fix the shield image format 

## Implemented but Not Working Properly: List any features that have been implemented but are not working correctly. Explain the issues you encountered, and if possible, the steps you took to address them.

## Features Not Implemented: Identify any features that you were unable to implement and provide a clear explanation for why they were left out.

  - endless mode that give player points at kill, display highest score (time issue hence not implemented)

  - random power-ups like invincible, double fire rate, increase movement speed occasionally drop when killing enemy(deleted, try to implement but causes error that i dont have enough time to fix)

  - main menu (explain in unexpected problems) * temporary

## New Java Classes: Enumerate any new Java classes that you introduced for the assignment. Include a brief description of each class's purpose and its location in the code.

  - add pause game function in LevelParent so player can pause the game at anytime

  - Shield.java - seperated from boss.java

  - BossConfig - seperated from boss.java

  - UserPlaneConfig - seperated from userplane.java

  - EnemyPlaneConfig - seperated from enemyplane.java

## Modified Java Classes: List the Java classes you modified from the provided code base. Describe the changes you made and explain why these modifications were necessary.

  - split Boss into smaller files (BossConfig, Shield and Boss) for easier maintainance
  
  - split UserPlane into smaller files with UserPlanConfig and UserPlane for easier maintainance

  - split EnemyPlane into smaller files (EnemyPlaneConfig and EnemyPlane) for easier maintainance
  
  - found and fix a typo in EnemyPlane (projectileYPostion to projectileYPosition)
  
  - fix the shield image format (jpg to png) in shieldimage.java

  - repackage and make changes to all relevant codes
  
  - add timeline.stop in goToNextLevel in LevelParent to fix endless popup

  - put all config files into a package and make changes to relevant codes

  - put all level files into a package and make changes to relevant codes

  - put all image.java files into a package and make changes to relevant codes
  
  - put all projectile files into a package and make changes to relevant codes
  
  - put remaining files (boss, userplane enemyplane etc) into a package - actor and make changes to all relevant codes

## Unexpected Problems: Communicate any unexpected challenges or issues you encountered during the assignment. Describe how you addressed or attempted to resolve them.
  - Got really sick in mid November and delayed all work (assignments, classes) by about a week
  - 5/12 
Windows file corrupted after updating windows and has to reset laptop to factory settings, updated codes (menu.fxml and related code changes) that is not pushed to github as I am still making changes to the code like position, background, etc.. is deleted. Wasted 2-3 days to get everyting back to work but adding main menu to the game causes the game to throw error ClassNotFound (idk why as the way I implemented it is the same). If I still couldnt fix this by Monday (9/12) I will not implement the main menu and can only revert to how the game originally transition to next level (after reaching kill count) else I might not complete the coursework in time.