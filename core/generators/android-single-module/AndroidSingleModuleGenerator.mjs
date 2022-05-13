/**
 * Android Single Module Generator Application
 * Here is The Generator Manager to Draw The Templates Files for Single Module App
 */
import {generateMultipleDirectories} from "../../utils/DirectoryUtils.mjs";
import generateFileFromTemplate, {convertTextToInputString} from "../../utils/FileUtils.mjs";
import {BAT, EMPTY, GRADLE, PROPERTIES} from "../../models/LegoraFileExtention.mjs";
import {NAME, PACKAGE_NAME} from "../../models/ProjectInformationItem.mjs";

const BASE_SOURCE_CODE_PATH = "../../generators/android-single-module/sourceCode/";

export default function executeSingleModuleAndroidApplication(
    generatePath = "",
    dependencies = new Map(),
    information = new Map()
) {
    generateAppDirectory(generatePath);
    generateGradleDirectory(generatePath);
    generateGradleDefaultFiles(generatePath);
}

export function generateAppDirectory(generatePath = "") {
    generateMultipleDirectories([
        generatePath + "/app",
        generatePath + "/app/src",
        generatePath + "/app/src/androidTest",
        generatePath + "/app/src/test",
        generatePath + "/app/src/main",
        generatePath + "/app/src/main/java",
        generatePath + "/app/src/main/res",
        generatePath + "/app/src/main/res/drawable",
        generatePath + "/app/src/main/res/drawable-hdpi",
        generatePath + "/app/src/main/res/drawable-mdpi",
        generatePath + "/app/src/main/res/drawable-xhdpi",
        generatePath + "/app/src/main/res/drawable-xxhdpi",
        generatePath + "/app/src/main/res/drawable-xxxhdpi",
        generatePath + "/app/src/main/res/drawable-v24",
        generatePath + "/app/src/main/res/layout",
        generatePath + "/app/src/main/res/values",
        generatePath + "/app/src/main/res/values-ar",
        generatePath + "/app/src/main/res/values-night",
        generatePath + "/app/src/main/res/mipmap-anydpi-v26",
        generatePath + "/app/src/main/res/mipmap-hdpi",
        generatePath + "/app/src/main/res/mipmap-mdpi",
        generatePath + "/app/src/main/res/mipmap-xhdpi",
        generatePath + "/app/src/main/res/mipmap-xxhdpi",
        generatePath + "/app/src/main/res/mipmap-xxxhdpi",
    ]);
}

export function generateGradleDirectory(generatePath = "") {
    generateMultipleDirectories([
        generatePath + "/gradle",
        generatePath + "/gradle/wrapper",
    ]);

    generateFileFromTemplate(
        {},
        BASE_SOURCE_CODE_PATH + "gradle-wrapper.properties.ejs",
        generatePath + "/gradle/wrapper/gradle-wrapper" + PROPERTIES
    )
}

export function generateGradleDefaultFiles(
    generatePath = "",
    information = new Map()
) {
    const name = information[NAME]
    const packageName = information[PACKAGE_NAME]

    generateFileFromTemplate({
        name: convertTextToInputString(name)
    }, BASE_SOURCE_CODE_PATH + "settings.gradle.ejs", generatePath + "/settings" + GRADLE)

    generateFileFromTemplate({
        packageName: convertTextToInputString(packageName)
    }, BASE_SOURCE_CODE_PATH + "AppDetails.gradle.ejs", generatePath + "/AppDetails" + GRADLE)

    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "gradlew.bat.ejs", generatePath + "/gradlew" + BAT)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "gradlew.ejs", generatePath + "/gradlew" + EMPTY)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "gradle.properties.ejs", generatePath + "/gradle" + PROPERTIES)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "build.gradle.ejs", generatePath + "/build" + GRADLE)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "Libraries.gradle.ejs", generatePath + "/Libraries" + GRADLE)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + ".gitignore.ejs", generatePath + "/.gitignore" + EMPTY)
}

