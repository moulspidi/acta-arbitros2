@ECHO OFF
SET DIR=%~dp0
SET WRAPPER_JAR=%DIR%gradle\wrapper\gradle-wrapper.jar
IF NOT EXIST "%WRAPPER_JAR%" (
  mkdir "%DIR%gradle\wrapper" 2>NUL
  powershell -Command "Invoke-WebRequest -Uri https://repo1.maven.org/maven2/org/gradle/gradle-wrapper/8.7/gradle-wrapper-8.7.jar -OutFile '%WRAPPER_JAR%'" 1>NUL 2>NUL
)
java -jar "%WRAPPER_JAR%" %*
