## GitHub: 

## Compilation Instructions: 

## Implemented and Working Properly: 

  - implement pause game function

  - fix endless popup caused by error InvocationTargetException

  - fix the shield image format to resolve error

  - implement horizontal movements(left right) for user plane

  - new enemy added to the game

## Implemented but Not Working Properly: 

## Features Not Implemented: 

  - endless mode that give player points at kill, display highest score (time issue hence not implemented)

  - random power-ups like invincible, double fire rate, increase movement speed occasionally drop when killing enemy(deleted, try to implement but causes error that i dont have enough time to fix)

  - main menu (explain in unexpected problems) * temporary

## New Java Classes: 

  - Shield.java - seperated from boss.java

  - BossConfig - seperated from boss.java

  - UserPlaneConfig - seperated from userplane.java

  - EnemyPlaneConfig - seperated from enemyplane.java

  - InputHandler - seperated from levelparent.java

  - EnemyPlane2Config - added a new type of enemy

  - EnemyPlane2 - new enemy

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

## Unexpected Problems: 
  - Got really sick in mid November and delayed all work (assignments, classes) by about a week
  - 5/12 
Windows file corrupted after Windows auto update and has to reset laptop to factory settings, updated codes (menu.fxml and related code changes) that is not pushed to github as I am still making changes to the code like position, background, etc.. is deleted. Wasted 2-3 days to get my laptop back to work but adding main menu to the game causes the game to throw error (idk why as the way I implemented it is the same). If I still couldnt fix this by Monday (9/12) I will not implement the main menu and can only revert to how the game originally transition to next level (after reaching kill count) else I might not complete the coursework in time.
