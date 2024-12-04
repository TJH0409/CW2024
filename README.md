## GitHub: 

## Compilation Instructions: 

## Implemented and Working Properly: 

  - implement pause game function

  - fix endless popup caused by error InvocationTargetException

  - fix the shield image format to resolve error

## Implemented but Not Working Properly: 

## Features Not Implemented: 

  - endless mode that give player points at kill, display highest score (time issue hence not implemented)

  - random power-ups like invincible, double fire rate, increase movement speed occasionally drop when killing enemy(deleted, try to implement but causes error that i dont have enough time to fix)

## New Java Classes: 

  - add pause game function in LevelParent so player can pause the game at anytime

  - Shield.java - seperated from boss.java

  - BossConfig - seperated from boss.java

  - UserPlaneConfig - seperated from userplane.java

  - EnemyPlaneConfig - seperated from enemyplane.java

## Modified Java Classes: 

  - split Boss into smaller files (BossConfig, Shield and Boss) for easier maintainance
  
  - split UserPlane into smaller files with UserPlanConfig and UserPlane for easier maintainance

  -  split EnemyPlane into smaller files (EnemyPlaneConfig and EnemyPlane) for easier maintainance
  
  - found and fix a typo in EnemyPlane (projectileYPostion to projectileYPosition)
  
  - fix the shield image format (jpg to png) in shieldimage.java

  - repackage and make changes to all relevant codes
  
  - add timeline.stop in goToNextLevel in LevelParent to fix endless popup

  - put all config files into a package and make changes to relevant codes

  - put all level files into a package and make changes to relevant codes

  - put all image.java files into a package and make changes to relevant codes
  
  - put all projectile files into a package and make changes to relevant codes
  
  - put remaining files (boss, userplane enemyplane etc) into a package - actor and make changes to all relevant codes

## Unexpected Problems: 
