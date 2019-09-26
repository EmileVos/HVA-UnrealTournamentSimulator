##Installation
###Requirements
 * Java 8^
 * Internet connection
 * Have access to command prompt (windws) / terminal (linux / macos)
###Installing the server
1. Download the Paper Spigot version 192 or the latest stable Paper Spigot version (if applicable)
2. Create a empty folder and paste the paper.jar file in the folder
3. Creating the startup script 
    
    **Mac/ linux**
    ```
    #!/bin/bash
    java -jar paper.jar
    ```
    Save this file as 'start.sh'
    
    **Windows**
    
    ```
    java -jar paper.jar
    ```
    Save this file as 'start.bat'

4. Run this file in a terminal application (starting the server)
5. Accept the EULA by editing the eula.txt and setting `eula=false` to `eula=true`
6. Start the server again and after succesfully loading stop it again.
7. Its recommended to change the following values of the ```server.properties```:

    ```
    allow-nether=true
    gamemode=survival
    broadcast-console-to-ops=true
    max-players=20
    spawn-npcs=true
    spawn-animals=true
    generate-structures=true
    ```
    
    to
    
     ```
    allow-nether=false
    gamemode=adventure
    broadcast-console-to-ops=false
    max-players=32
    spawn-npcs=false
    spawn-animals=false
    generate-structures=false
    ```
    and in the ```bukkit.yml```:
    
    ```
    settings:
      allow-end: true
      
    spawn-limits:
      monsters: 70
      animals: 10
      water-animals: 15
      ambient: 15
    ```
    
    to
    
    ```
    settings:
      allow-end: false
      
    spawn-limits:
      monsters: 0
      animals: 0
      water-animals: 0
      ambient: 0
    ```
    
8. Add the plugin .jar file to `/plugins` directory
9. Start the server

And... you'r done!
