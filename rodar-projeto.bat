.bat

@echo off
cd /d C:\meu-crud-java-2
echo Compilando...
javac -encoding UTF-8 -cp ".;libs\sqlite-jdbc-3.42.0.0.jar" *.java
echo Rodando o programa...
java -cp ".;libs\sqlite-jdbc-3.42.0.0.jar" Main
pause
