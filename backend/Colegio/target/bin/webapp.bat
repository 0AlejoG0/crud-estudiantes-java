@REM ----------------------------------------------------------------------------
@REM  Copyright 2001-2006 The Apache Software Foundation.
@REM
@REM  Licensed under the Apache License, Version 2.0 (the "License");
@REM  you may not use this file except in compliance with the License.
@REM  You may obtain a copy of the License at
@REM
@REM       http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM  Unless required by applicable law or agreed to in writing, software
@REM  distributed under the License is distributed on an "AS IS" BASIS,
@REM  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
@REM  See the License for the specific language governing permissions and
@REM  limitations under the License.
@REM ----------------------------------------------------------------------------
@REM
@REM   Copyright (c) 2001-2006 The Apache Software Foundation.  All rights
@REM   reserved.

@echo off

set ERROR_CODE=0

:init
@REM Decide how to startup depending on the version of windows

@REM -- Win98ME
if NOT "%OS%"=="Windows_NT" goto Win9xArg

@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" @setlocal

@REM -- 4NT shell
if "%eval[2+2]" == "4" goto 4NTArgs

@REM -- Regular WinNT shell
set CMD_LINE_ARGS=%*
goto WinNTGetScriptDir

@REM The 4NT Shell from jp software
:4NTArgs
set CMD_LINE_ARGS=%$
goto WinNTGetScriptDir

:Win9xArg
@REM Slurp the command line arguments.  This loop allows for an unlimited number
@REM of arguments (up to the command line limit, anyway).
set CMD_LINE_ARGS=
:Win9xApp
if %1a==a goto Win9xGetScriptDir
set CMD_LINE_ARGS=%CMD_LINE_ARGS% %1
shift
goto Win9xApp

:Win9xGetScriptDir
set SAVEDIR=%CD%
%0\
cd %0\..\.. 
set BASEDIR=%CD%
cd %SAVEDIR%
set SAVE_DIR=
goto repoSetup

:WinNTGetScriptDir
set BASEDIR=%~dp0\..

:repoSetup
set REPO=


if "%JAVACMD%"=="" set JAVACMD=java

if "%REPO%"=="" set REPO=%BASEDIR%\repo

set CLASSPATH="%BASEDIR%"\etc;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-core\8.5.23\tomcat-embed-core-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-annotations-api\8.5.23\tomcat-annotations-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-jasper\8.5.23\tomcat-embed-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\embed\tomcat-embed-el\8.5.23\tomcat-embed-el-8.5.23.jar;"%REPO%"\org\eclipse\jdt\ecj\3.12.3\ecj-3.12.3.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper\8.5.23\tomcat-jasper-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-servlet-api\8.5.23\tomcat-servlet-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-juli\8.5.23\tomcat-juli-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-el-api\8.5.23\tomcat-el-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-api\8.5.23\tomcat-api-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-util-scan\8.5.23\tomcat-util-scan-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-util\8.5.23\tomcat-util-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-jasper-el\8.5.23\tomcat-jasper-el-8.5.23.jar;"%REPO%"\org\apache\tomcat\tomcat-jsp-api\8.5.23\tomcat-jsp-api-8.5.23.jar;"%REPO%"\org\glassfish\jersey\core\jersey-server\2.41\jersey-server-2.41.jar;"%REPO%"\org\glassfish\jersey\core\jersey-common\2.41\jersey-common-2.41.jar;"%REPO%"\org\glassfish\hk2\osgi-resource-locator\1.0.3\osgi-resource-locator-1.0.3.jar;"%REPO%"\org\glassfish\jersey\core\jersey-client\2.41\jersey-client-2.41.jar;"%REPO%"\jakarta\ws\rs\jakarta.ws.rs-api\2.1.6\jakarta.ws.rs-api-2.1.6.jar;"%REPO%"\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;"%REPO%"\org\glassfish\hk2\external\jakarta.inject\2.6.1\jakarta.inject-2.6.1.jar;"%REPO%"\jakarta\validation\jakarta.validation-api\2.0.2\jakarta.validation-api-2.0.2.jar;"%REPO%"\org\glassfish\jersey\containers\jersey-container-servlet\2.41\jersey-container-servlet-2.41.jar;"%REPO%"\org\glassfish\jersey\containers\jersey-container-servlet-core\2.41\jersey-container-servlet-core-2.41.jar;"%REPO%"\org\glassfish\jersey\media\jersey-media-json-jackson\2.41\jersey-media-json-jackson-2.41.jar;"%REPO%"\org\glassfish\jersey\ext\jersey-entity-filtering\2.41\jersey-entity-filtering-2.41.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-annotations\2.15.2\jackson-annotations-2.15.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-databind\2.15.2\jackson-databind-2.15.2.jar;"%REPO%"\com\fasterxml\jackson\core\jackson-core\2.15.2\jackson-core-2.15.2.jar;"%REPO%"\com\fasterxml\jackson\module\jackson-module-jaxb-annotations\2.15.2\jackson-module-jaxb-annotations-2.15.2.jar;"%REPO%"\jakarta\xml\bind\jakarta.xml.bind-api\2.3.3\jakarta.xml.bind-api-2.3.3.jar;"%REPO%"\org\glassfish\jersey\inject\jersey-hk2\2.41\jersey-hk2-2.41.jar;"%REPO%"\org\glassfish\hk2\hk2-locator\2.6.1\hk2-locator-2.6.1.jar;"%REPO%"\org\glassfish\hk2\external\aopalliance-repackaged\2.6.1\aopalliance-repackaged-2.6.1.jar;"%REPO%"\org\glassfish\hk2\hk2-api\2.6.1\hk2-api-2.6.1.jar;"%REPO%"\org\glassfish\hk2\hk2-utils\2.6.1\hk2-utils-2.6.1.jar;"%REPO%"\org\javassist\javassist\3.29.2-GA\javassist-3.29.2-GA.jar;"%REPO%"\com\google\code\gson\gson\2.8.8\gson-2.8.8.jar;"%REPO%"\mysql\mysql-connector-java\8.0.11\mysql-connector-java-8.0.11.jar;"%REPO%"\com\google\protobuf\protobuf-java\2.6.0\protobuf-java-2.6.0.jar;"%REPO%"\com\thetransactioncompany\cors-filter\2.10\cors-filter-2.10.jar;"%REPO%"\com\thetransactioncompany\java-property-utils\1.16\java-property-utils-1.16.jar;"%REPO%"\com\quipux\Colegio\0.0.1-SNAPSHOT\Colegio-0.0.1-SNAPSHOT.jar

set ENDORSED_DIR=
if NOT "%ENDORSED_DIR%" == "" set CLASSPATH="%BASEDIR%"\%ENDORSED_DIR%\*;%CLASSPATH%

if NOT "%CLASSPATH_PREFIX%" == "" set CLASSPATH=%CLASSPATH_PREFIX%;%CLASSPATH%

@REM Reaching here means variables are defined and arguments have been captured
:endInit

%JAVACMD% %JAVA_OPTS%  -classpath %CLASSPATH% -Dapp.name="webapp" -Dapp.repo="%REPO%" -Dapp.home="%BASEDIR%" -Dbasedir="%BASEDIR%" launch.Main %CMD_LINE_ARGS%
if %ERRORLEVEL% NEQ 0 goto error
goto end

:error
if "%OS%"=="Windows_NT" @endlocal
set ERROR_CODE=%ERRORLEVEL%

:end
@REM set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" goto endNT

@REM For old DOS remove the set variables from ENV - we assume they were not set
@REM before we started - at least we don't leave any baggage around
set CMD_LINE_ARGS=
goto postExec

:endNT
@REM If error code is set to 1 then the endlocal was done already in :error.
if %ERROR_CODE% EQU 0 @endlocal


:postExec

if "%FORCE_EXIT_ON_ERROR%" == "on" (
  if %ERROR_CODE% NEQ 0 exit %ERROR_CODE%
)

exit /B %ERROR_CODE%
