import {
    ANDROID_LIBRARY_TEMPLATE,
    COMPOSE_APP_ANDROID,
    DESKTOP_COMPOSE, GRADLE_PLUGIN, KTOR,
    MULTI_APP_ANDROID, NEST_JS,
    NEXT_JS_APP_JS,
    NEXT_JS_APP_TS,
    REACT_JAVA_SCRIPT,
    REACT_TYPE_SCRIPT,
    SINGLE_APP_ANDROID, SPRING_JAVA, SPRING_KOTLIN
} from "../models/ProjectModel.mjs";
import {
    ARTIFACT, AUTHOR, DESCRIPTION, GIT_LINK,
    GROUP,
    NAME,
    PACKAGE_NAME,
    ProjectInformationItem, REPO_NAME,
    TYPE_TEXT,
    VERSION, WEBSITE
} from "../models/ProjectInformationItem.mjs";

/**
 * This File Contains All Projects Informations
 * And Each Project Request it's Informations Params By it's Own Key Provided in Each Project
 */
export default function getParamsByProjectKey(key) {
    let result = null;
    switch (key) {
        case SINGLE_APP_ANDROID:
            result = getAndroidSingleModuleFields();
            break;
        case MULTI_APP_ANDROID:
            result = getAndroidMultiModuleFields();
            break;
        case COMPOSE_APP_ANDROID:
            result = getAndroidComposeFields();
            break;
        case ANDROID_LIBRARY_TEMPLATE:
            result = getAndroidLibraryTemplateFields();
            break;
        case REACT_JAVA_SCRIPT:
        case REACT_TYPE_SCRIPT:
            result = getReactFields();
            break;
        case NEXT_JS_APP_JS:
        case NEXT_JS_APP_TS:
            result = getNextFields();
            break;
        case DESKTOP_COMPOSE:
            result = getJetpackComposeDesktopFields();
            break;
        case GRADLE_PLUGIN:
            result = getGradlePluginFields();
            break;
        case KTOR:
            result = getKtorFields();
            break;
        case NEST_JS:
            result = getNestJsFields();
            break;
        case SPRING_JAVA:
        case SPRING_KOTLIN:
            result = getSpringBootFields();
            break;
    }
    return result;
}

/**
 * Android Single Module Fields Ready
 * @returns {ProjectInformationItem[]}
 */
function getAndroidSingleModuleFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(PACKAGE_NAME, TYPE_TEXT,  "Please Enter Project Package Name ...", "Project Package Name"),
        new ProjectInformationItem(VERSION, TYPE_TEXT,  "Please Enter Project Version ...", "Project Version"),
    ];
}

/**
 * Android Library Template Text Fields is Ready
 * @returns {ProjectInformationItem[]}
 */
function getAndroidLibraryTemplateFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(PACKAGE_NAME, TYPE_TEXT,  "Please Enter Project Package Name ...", "Project Package Name"),
        new ProjectInformationItem(VERSION, TYPE_TEXT,  "Please Enter Project Version ...", "Project Version"),
        new ProjectInformationItem(GROUP, TYPE_TEXT,  "Please Enter Library Group ...",  "Library Group"),
        new ProjectInformationItem(ARTIFACT, TYPE_TEXT,  "Please Enter Library Artifact ...",  "Library Artifact"),
        new ProjectInformationItem(DESCRIPTION, TYPE_TEXT,  "Please Enter Library Description ...",  "Library Description"),
        new ProjectInformationItem(GIT_LINK, TYPE_TEXT,  "Please Enter Git Remote Link ...",  "Remote Link"),
        new ProjectInformationItem(REPO_NAME, TYPE_TEXT,  "Please Enter Repository Name ...",  "Repository Name"),
    ];
}

/**
 * Android Multi Modular Apps Text Fields Ready
 * @returns {ProjectInformationItem[]}
 */
function getAndroidMultiModuleFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(PACKAGE_NAME, TYPE_TEXT,  "Please Enter Project Package Name ...", "Project Package Name"),
        new ProjectInformationItem(VERSION, TYPE_TEXT,  "Please Enter Project Version ...", "Project Version"),
    ];
}

/**
 * Jetpack Compose Application Text Fields Ready
 * @returns {ProjectInformationItem[]}
 */
function getAndroidComposeFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(PACKAGE_NAME, TYPE_TEXT,  "Please Enter Project Package Name ...", "Project Package Name"),
    ];
}

/**
 * React Fields Text Ready ...
 * @returns {ProjectInformationItem[]}
 */
function getReactFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(WEBSITE, TYPE_TEXT, "Please Enter Website Url ...", "Website Url"),
        new ProjectInformationItem(VERSION, TYPE_TEXT, "Please Enter Project Version ...", "Website Version"),
        new ProjectInformationItem(AUTHOR, TYPE_TEXT,  "Please Enter Project Author ...", "Website Author"),
        new ProjectInformationItem(DESCRIPTION, TYPE_TEXT, "Please Enter Project Description ...", "Website Description"),
    ];
}

/**
 * NextJs Text Fields Ready
 * @returns {ProjectInformationItem[]}
 */
function getNextFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(WEBSITE, TYPE_TEXT, "Please Enter Website Url ...", "Website Url"),
        new ProjectInformationItem(VERSION, TYPE_TEXT, "Please Enter Project Version ...", "Website Version"),
    ];
}

/**
 * SpringBoot Generic Text Fields Ready
 * @returns {ProjectInformationItem[]}
 */
function getSpringBootFields() {
    return [
        new ProjectInformationItem(NAME, TYPE_TEXT, "Please Enter Project Name ...", "Project Name"),
        new ProjectInformationItem(DESCRIPTION, TYPE_TEXT, "Please Enter Project Description ...", "Website Description"),
        new ProjectInformationItem(VERSION, TYPE_TEXT, "Please Enter Project Version ...", "Website Version"),
        new ProjectInformationItem(GROUP, TYPE_TEXT,  "Please Enter Project Group ...",  "Project Group"),
        new ProjectInformationItem(ARTIFACT, TYPE_TEXT,  "Please Enter Project Artifact ...",  "Project Artifact"),
    ];
}

function getJetpackComposeDesktopFields() {
    return [
        new ProjectInformationItem()
    ];
}

function getGradlePluginFields() {
    return [
        new ProjectInformationItem()
    ];
}

function getKtorFields() {
    return [
        new ProjectInformationItem()
    ];
}

function getNestJsFields() {
    return [
        new ProjectInformationItem()
    ];
}

