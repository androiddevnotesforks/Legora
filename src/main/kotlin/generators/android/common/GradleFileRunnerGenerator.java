package generators.android.common;

import generators.base.FileGenerator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import models.FileExtention;
import org.jetbrains.annotations.NotNull;

public class GradleFileRunnerGenerator extends FileGenerator {

    private final Function1<? super String, Unit> message;
    private final String generatedFilePath;

    public GradleFileRunnerGenerator(Function1<? super String, Unit> message, String generatedFilePath) {
        this.message = message;
        this.generatedFilePath = generatedFilePath;
    }

    @NotNull
    @Override
    public String getFileName() {
        return "gradlew";
    }

    @NotNull
    @Override
    public String getFileContent() {
        return "#!/usr/bin/env sh\n" +
                "\n" +
                "#\n" +
                "# Copyright 2015 the original author or authors.\n" +
                "#\n" +
                "# Licensed under the Apache License, Version 2.0 (the \"License\");\n" +
                "# you may not use this file except in compliance with the License.\n" +
                "# You may obtain a copy of the License at\n" +
                "#\n" +
                "#      https://www.apache.org/licenses/LICENSE-2.0\n" +
                "#\n" +
                "# Unless required by applicable law or agreed to in writing, software\n" +
                "# distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
                "# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
                "# See the License for the specific language governing permissions and\n" +
                "# limitations under the License.\n" +
                "#\n" +
                "\n" +
                "##############################################################################\n" +
                "##\n" +
                "##  Gradle start up script for UN*X\n" +
                "##\n" +
                "##############################################################################\n" +
                "\n" +
                "# Attempt to set APP_HOME\n" +
                "# Resolve links: $0 may be a link\n" +
                "PRG=\"$0\"\n" +
                "# Need this for relative symlinks.\n" +
                "while [ -h \"$PRG\" ] ; do\n" +
                "    ls=`ls -ld \"$PRG\"`\n" +
                "    link=`expr \"$ls\" : '.*-> \\(.*\\)$'`\n" +
                "    if expr \"$link\" : '/.*' > /dev/null; then\n" +
                "        PRG=\"$link\"\n" +
                "    else\n" +
                "        PRG=`dirname \"$PRG\"`\"/$link\"\n" +
                "    fi\n" +
                "done\n" +
                "SAVED=\"`pwd`\"\n" +
                "cd \"`dirname \\\"$PRG\\\"`/\" >/dev/null\n" +
                "APP_HOME=\"`pwd -P`\"\n" +
                "cd \"$SAVED\" >/dev/null\n" +
                "\n" +
                "APP_NAME=\"Gradle\"\n" +
                "APP_BASE_NAME=`basename \"$0\"`\n" +
                "\n" +
                "# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.\n" +
                "DEFAULT_JVM_OPTS='\"-Xmx64m\" \"-Xms64m\"'\n" +
                "\n" +
                "# Use the maximum available, or set MAX_FD != -1 to use that value.\n" +
                "MAX_FD=\"maximum\"\n" +
                "\n" +
                "warn () {\n" +
                "    echo \"$*\"\n" +
                "}\n" +
                "\n" +
                "die () {\n" +
                "    echo\n" +
                "    echo \"$*\"\n" +
                "    echo\n" +
                "    exit 1\n" +
                "}\n" +
                "\n" +
                "# OS specific support (must be 'true' or 'false').\n" +
                "cygwin=false\n" +
                "msys=false\n" +
                "darwin=false\n" +
                "nonstop=false\n" +
                "case \"`uname`\" in\n" +
                "  CYGWIN* )\n" +
                "    cygwin=true\n" +
                "    ;;\n" +
                "  Darwin* )\n" +
                "    darwin=true\n" +
                "    ;;\n" +
                "  MINGW* )\n" +
                "    msys=true\n" +
                "    ;;\n" +
                "  NONSTOP* )\n" +
                "    nonstop=true\n" +
                "    ;;\n" +
                "esac\n" +
                "\n" +
                "CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar\n" +
                "\n" +
                "\n" +
                "# Determine the Java command to use to start the JVM.\n" +
                "if [ -n \"$JAVA_HOME\" ] ; then\n" +
                "    if [ -x \"$JAVA_HOME/jre/sh/java\" ] ; then\n" +
                "        # IBM's JDK on AIX uses strange locations for the executables\n" +
                "        JAVACMD=\"$JAVA_HOME/jre/sh/java\"\n" +
                "    else\n" +
                "        JAVACMD=\"$JAVA_HOME/bin/java\"\n" +
                "    fi\n" +
                "    if [ ! -x \"$JAVACMD\" ] ; then\n" +
                "        die \"ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME\n" +
                "\n" +
                "Please set the JAVA_HOME variable in your environment to match the\n" +
                "location of your Java installation.\"\n" +
                "    fi\n" +
                "else\n" +
                "    JAVACMD=\"java\"\n" +
                "    which java >/dev/null 2>&1 || die \"ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.\n" +
                "\n" +
                "Please set the JAVA_HOME variable in your environment to match the\n" +
                "location of your Java installation.\"\n" +
                "fi\n" +
                "\n" +
                "# Increase the maximum file descriptors if we can.\n" +
                "if [ \"$cygwin\" = \"false\" -a \"$darwin\" = \"false\" -a \"$nonstop\" = \"false\" ] ; then\n" +
                "    MAX_FD_LIMIT=`ulimit -H -n`\n" +
                "    if [ $? -eq 0 ] ; then\n" +
                "        if [ \"$MAX_FD\" = \"maximum\" -o \"$MAX_FD\" = \"max\" ] ; then\n" +
                "            MAX_FD=\"$MAX_FD_LIMIT\"\n" +
                "        fi\n" +
                "        ulimit -n $MAX_FD\n" +
                "        if [ $? -ne 0 ] ; then\n" +
                "            warn \"Could not set maximum file descriptor limit: $MAX_FD\"\n" +
                "        fi\n" +
                "    else\n" +
                "        warn \"Could not query maximum file descriptor limit: $MAX_FD_LIMIT\"\n" +
                "    fi\n" +
                "fi\n" +
                "\n" +
                "# For Darwin, add options to specify how the application appears in the dock\n" +
                "if $darwin; then\n" +
                "    GRADLE_OPTS=\"$GRADLE_OPTS \\\"-Xdock:name=$APP_NAME\\\" \\\"-Xdock:icon=$APP_HOME/media/gradle.icns\\\"\"\n" +
                "fi\n" +
                "\n" +
                "# For Cygwin or MSYS, switch paths to Windows format before running java\n" +
                "if [ \"$cygwin\" = \"true\" -o \"$msys\" = \"true\" ] ; then\n" +
                "    APP_HOME=`cygpath --path --mixed \"$APP_HOME\"`\n" +
                "    CLASSPATH=`cygpath --path --mixed \"$CLASSPATH\"`\n" +
                "\n" +
                "    JAVACMD=`cygpath --unix \"$JAVACMD\"`\n" +
                "\n" +
                "    # We build the pattern for arguments to be converted via cygpath\n" +
                "    ROOTDIRSRAW=`find -L / -maxdepth 1 -mindepth 1 -type d 2>/dev/null`\n" +
                "    SEP=\"\"\n" +
                "    for dir in $ROOTDIRSRAW ; do\n" +
                "        ROOTDIRS=\"$ROOTDIRS$SEP$dir\"\n" +
                "        SEP=\"|\"\n" +
                "    done\n" +
                "    OURCYGPATTERN=\"(^($ROOTDIRS))\"\n" +
                "    # Add a user-defined pattern to the cygpath arguments\n" +
                "    if [ \"$GRADLE_CYGPATTERN\" != \"\" ] ; then\n" +
                "        OURCYGPATTERN=\"$OURCYGPATTERN|($GRADLE_CYGPATTERN)\"\n" +
                "    fi\n" +
                "    # Now convert the arguments - kludge to limit ourselves to /bin/sh\n" +
                "    i=0\n" +
                "    for arg in \"$@\" ; do\n" +
                "        CHECK=`echo \"$arg\"|egrep -c \"$OURCYGPATTERN\" -`\n" +
                "        CHECK2=`echo \"$arg\"|egrep -c \"^-\"`                                 ### Determine if an option\n" +
                "\n" +
                "        if [ $CHECK -ne 0 ] && [ $CHECK2 -eq 0 ] ; then                    ### Added a condition\n" +
                "            eval `echo args$i`=`cygpath --path --ignore --mixed \"$arg\"`\n" +
                "        else\n" +
                "            eval `echo args$i`=\"\\\"$arg\\\"\"\n" +
                "        fi\n" +
                "        i=`expr $i + 1`\n" +
                "    done\n" +
                "    case $i in\n" +
                "        0) set -- ;;\n" +
                "        1) set -- \"$args0\" ;;\n" +
                "        2) set -- \"$args0\" \"$args1\" ;;\n" +
                "        3) set -- \"$args0\" \"$args1\" \"$args2\" ;;\n" +
                "        4) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" ;;\n" +
                "        5) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" ;;\n" +
                "        6) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" ;;\n" +
                "        7) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" ;;\n" +
                "        8) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" \"$args7\" ;;\n" +
                "        9) set -- \"$args0\" \"$args1\" \"$args2\" \"$args3\" \"$args4\" \"$args5\" \"$args6\" \"$args7\" \"$args8\" ;;\n" +
                "    esac\n" +
                "fi\n" +
                "\n" +
                "# Escape application args\n" +
                "save () {\n" +
                "    for i do printf %s\\\\n \"$i\" | sed \"s/'/'\\\\\\\\''/g;1s/^/'/;\\$s/\\$/' \\\\\\\\/\" ; done\n" +
                "    echo \" \"\n" +
                "}\n" +
                "APP_ARGS=`save \"$@\"`\n" +
                "\n" +
                "# Collect all arguments for the java command, following the shell quoting and substitution rules\n" +
                "eval set -- $DEFAULT_JVM_OPTS $JAVA_OPTS $GRADLE_OPTS \"\\\"-Dorg.gradle.appname=$APP_BASE_NAME\\\"\" -classpath \"\\\"$CLASSPATH\\\"\" org.gradle.wrapper.GradleWrapperMain \"$APP_ARGS\"\n" +
                "\n" +
                "exec \"$JAVACMD\" \"$@\"\n";
    }

    @NotNull
    @Override
    public FileExtention getFileExt() {
        return FileExtention.EMPTY;
    }

    @NotNull
    @Override
    public String getFilePath() {
        return generatedFilePath;
    }

    @Override
    public void execute() {
        generateFile(message);
    }
}
