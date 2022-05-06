/**
 * Android Single Module Generator Application
 * Here is The Generator Manager to Draw The Templates Files for Single Module App
 */
import {generateMultipleDirectories} from "../../utils/DirectoryUtils.mjs";
import generateFileFromTemplate, {convertTextToInputString} from "../../utils/FileUtils.mjs";
import {BAT, EMPTY, GRADLE} from "../../models/LegoraFileExtention.mjs";
import {NAME} from "../../models/ProjectInformationItem.mjs";

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

}

export function generateGradleDirectory(generatePath = "") {
    generateMultipleDirectories([
        generatePath + "/gradle",
        generatePath + "/gradle/wrapper",
    ]);
}

export function generateGradleDefaultFiles(
    generatePath = "",
    information = new Map()
) {
    const name = information[NAME]
    generateFileFromTemplate({
        name: convertTextToInputString(name)
    }, BASE_SOURCE_CODE_PATH + "settings.gradle.ejs", generatePath + "/settings" + GRADLE)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "gradlew.bat.ejs", generatePath + "/gradlew" + BAT)
    generateFileFromTemplate({}, BASE_SOURCE_CODE_PATH + "gradlew.ejs", generatePath + "/gradlew" + EMPTY)
}

