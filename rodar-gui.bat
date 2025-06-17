@echo off
cd /d C:\meu-crud-java-2
echo Compilando interface gráfica...
javac -encoding UTF-8 -cp ".;libs\sqlite-jdbc-3.42.0.0.jar" ProfessorGUI.java
echo Abrindo a janela do programa...
java -cp ".;libs\sqlite-jdbc-3.42.0.0.jar" ProfessorGUI
pause
