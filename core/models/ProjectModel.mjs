/**
 * This project is the Project Model Type in Projects List
 * This Projects Will be Shown to User To Start Picking From Them One Project To Generate
 *
 * This Projects List will be Returned as a Static Array from Core Source Code
 * And Once User Picked the Target Project Will be Navigated to Project Information Section
 */
export class Project {
    constructor(
        title = "",
        description = "",
        icon = "",
        key = "",
        info = ""
    ) {
        this.title = title;
        this.description = description;
        this.icon = icon;
        this.title = title;
        this.key = key;
        this.info = info;
    }
}

/**
 * This Constants is the Project Types Supported By Legora
 * This Keys should be Mapped to Project.key and This Key is Used to Assign To Which Generator this Project
 * Will be Executed
 */
export const SINGLE_APP_ANDROID = "SINGLE_APP_ANDROID";
export const MULTI_APP_ANDROID = "MULTI_APP_ANDROID";
export const COMPOSE_APP_ANDROID = "COMPOSE_APP_ANDROID";
export const ANDROID_LIBRARY_TEMPLATE = "ANDROID_LIBRARY_TEMPLATE";
export const REACT_JAVA_SCRIPT = "REACT_JAVA_SCRIPT";
export const REACT_TYPE_SCRIPT = "REACT_TYPE_SCRIPT";
export const NEXT_JS_APP_JS = "NEXT_JS_APP_JS";
export const NEXT_JS_APP_TS = "NEXT_JS_APP_TS";
export const DESKTOP_COMPOSE = "DESKTOP_COMPOSE";
export const GRADLE_PLUGIN = "GRADLE_PLUGIN";
export const KTOR = "KTOR";
export const NEST_JS = "NEST_JS";
export const SPRING_JAVA = "SPRING_JAVA";
export const SPRING_KOTLIN = "SPRING_KOTLIN";
