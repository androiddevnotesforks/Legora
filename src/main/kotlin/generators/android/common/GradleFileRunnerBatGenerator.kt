package generators.android.common

import generators.base.FileGenerator
import models.FileExtention

class GradleFileRunnerBatGenerator constructor(
    private val generatedFilePath: String,
    private val onGeneratedFileListener: (String) -> Unit
): FileGenerator() {
    override fun getFileName(): String {
        return "gradlew"
    }

    override fun getFileContent(): String {
        return "@rem\n" +
                "@rem Copyright 2015 the original author or authors.\n" +
                "@rem\n" +
                "@rem Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "@rem you may not use this file except in compliance with the License.\n" +
                "@rem You may obtain a copy of the License at\n" +
                "@rem\n" +
                "@rem      https://www.apache.org/licenses/LICENSE-2.0\n" +
                "@rem\n" +
                "@rem Unless required by applicable law or agreed to in writing, software\n" +
                "@rem distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "@rem See the License for the specific language governing permissions and\n" +
                "@rem limitations under the License.\n" +
                "@rem\n" +
                "\n" +
                "@if \"%DEBUG%\" == \"\" @echo off\n" +
                "@rem ##########################################################################\n" +
                "@rem\n" +
                "@rem  Gradle startup script for Windows\n" +
                "@rem\n" +
                "@rem ##########################################################################\n" +
                "\n" +
                "@rem Set local scope for the variables with windows NT shell\n" +
                "if \"%OS%\"==\"Windows_NT\" setlocal\n" +
                "\n" +
                "set DIRNAME=%~dp0\n" +
                "if \"%DIRNAME%\" == \"\" set DIRNAME=.\n" +
                "set APP_BASE_NAME=%~n0\n" +
                "set APP_HOME=%DIRNAME%\n" +
                "\n" +
                "@rem Resolve any \".\" and \"..\" in APP_HOME to make it shorter.\n" +
                "for %%i in (\"%APP_HOME%\") do set APP_HOME=%%~fi\n" +
                "\n" +
                "@rem Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.\n" +
                "set DEFAULT_JVM_OPTS=\"-Xmx64m\" \"-Xms64m\"\n" +
                "\n" +
                "@rem Find java.exe\n" +
                "if defined JAVA_HOME goto findJavaFromJavaHome\n" +
                "\n" +
                "set JAVA_EXE=java.exe\n" +
                "%JAVA_EXE% -version >NUL 2>&1\n" +
                "if \"%ERRORLEVEL%\" == \"0\" goto execute\n" +
                "\n" +
                "echo.\n" +
                "echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.\n" +
                "echo.\n" +
                "echo Please set the JAVA_HOME variable in your environment to match the\n" +
                "echo location of your Java installation.\n" +
                "\n" +
                "goto fail\n" +
                "\n" +
                ":findJavaFromJavaHome\n" +
                "set JAVA_HOME=%JAVA_HOME:\"=%\n" +
                "set JAVA_EXE=%JAVA_HOME%/bin/java.exe\n" +
                "\n" +
                "if exist \"%JAVA_EXE%\" goto execute\n" +
                "\n" +
                "echo.\n" +
                "echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%\n" +
                "echo.\n" +
                "echo Please set the JAVA_HOME variable in your environment to match the\n" +
                "echo location of your Java installation.\n" +
                "\n" +
                "goto fail\n" +
                "\n" +
                ":execute\n" +
                "@rem Setup the command line\n" +
                "\n" +
                "set CLASSPATH=%APP_HOME%\\gradle\\wrapper\\gradle-wrapper.jar\n" +
                "\n" +
                "\n" +
                "@rem Execute Gradle\n" +
                "\"%JAVA_EXE%\" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %GRADLE_OPTS% \"-Dorg.gradle.appname=%APP_BASE_NAME%\" -classpath \"%CLASSPATH%\" org.gradle.wrapper.GradleWrapperMain %*\n" +
                "\n" +
                ":end\n" +
                "@rem End local scope for the variables with windows NT shell\n" +
                "if \"%ERRORLEVEL%\"==\"0\" goto mainEnd\n" +
                "\n" +
                ":fail\n" +
                "rem Set variable GRADLE_EXIT_CONSOLE if you need the _script_ return code instead of\n" +
                "rem the _cmd.exe /c_ return code!\n" +
                "if  not \"\" == \"%GRADLE_EXIT_CONSOLE%\" exit 1\n" +
                "exit /b 1\n" +
                "\n" +
                ":mainEnd\n" +
                "if \"%OS%\"==\"Windows_NT\" endlocal\n" +
                "\n" +
                ":omega\n"
    }

    override fun getFileExt(): FileExtention = FileExtention.BAT
    override fun getFilePath(): String = generatedFilePath

    override fun execute() {
        generateFile {
            onGeneratedFileListener(it)
        }
    }

}
