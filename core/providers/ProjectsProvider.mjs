/**
 * Project Provider is the Provider Class to Get all Supported Projects in Legora
 * This File will Return Supported Projects That can be Generated
 */
import {
    ANDROID_LIBRARY_TEMPLATE,
    COMPOSE_APP_ANDROID, DESKTOP_COMPOSE, GRADLE_PLUGIN, KTOR,
    MULTI_APP_ANDROID, NEXT_JS_APP_JS, NEXT_JS_APP_TS,
    Project, REACT_JAVA_SCRIPT, REACT_TYPE_SCRIPT,
    SINGLE_APP_ANDROID, SPRING_JAVA, SPRING_KOTLIN
} from "../models/ProjectModel.mjs";

export default function getSupportedProjects() {
    return [
        // Android Projects
        new Project(ANDROID_TITLE, ANDROID_DESCRIPTION_SINGLE, ANDROID_ICON, SINGLE_APP_ANDROID, ""),
        new Project(ANDROID_TITLE, ANDROID_DESCRIPTION_MULTI, ANDROID_ICON, MULTI_APP_ANDROID, ""),
        new Project(ANDROID_TITLE, ANDROID_DESCRIPTION_JETPACK, ANDROID_ICON, COMPOSE_APP_ANDROID, ""),
        new Project(ANDROID_TITLE, ANDROID_LIBRARY, ANDROID_ICON, ANDROID_LIBRARY_TEMPLATE, ""),

        // Spring Boot Projects
        new Project(SPRING_BOOT, SPRING_BOOT_DESCRIPTION_J, SPRING_BOOT_ICON, SPRING_JAVA, ""),
        new Project(SPRING_BOOT, SPRING_BOOT_DESCRIPTION_K, SPRING_BOOT_ICON, SPRING_KOTLIN, ""),

        // React Projects
        new Project(REACT_TITLE, REACT_DESCRIPTION_JS, REACT_ICON, REACT_JAVA_SCRIPT, ""),
        new Project(REACT_TITLE, REACT_DESCRIPTION_TS, REACT_ICON, REACT_TYPE_SCRIPT, ""),

        // NextJs Projects
        new Project(NEXT_TITLE, NEXT_JS_DESCRIPTION, NEXT_JS_ICON, NEXT_JS_APP_JS, ""),
        new Project(NEXT_TITLE, NEXT_TS_DESCRIPTION, NEXT_JS_ICON, NEXT_JS_APP_TS, ""),

        // Desktop Compose Projects
        new Project(COMPOSE_TITLE, COMPOSE_DESCRIPTION, JETPACK_COMPOSE_ICON, DESKTOP_COMPOSE, ""),

        // Gradle Plugin Projects
        new Project(GRADLE_TITLE, GRADLE_DESCRIPTION, GRADLE_ICON, GRADLE_PLUGIN, ""),

        // Ktor Projects
        new Project(KTOR_TITLE, KTOR_DESCRIPTION, KTOR_ICON, KTOR, ""),

        // NestJs Projects
        new Project(NEST_JS, NEST_JS_DESCRIPTION, NEST_JS_ICON, NEST_JS, ""),

        //TODO:(Yazan) Add CLI's and Express Once Above Done
    ];
}

/**
 * Android Constants Here
 */
export const ANDROID_TITLE = "Android Application";
export const ANDROID_DESCRIPTION_SINGLE = "Single Module App";
export const ANDROID_DESCRIPTION_MULTI = "Multi Modular App";
export const ANDROID_DESCRIPTION_JETPACK = "Jetpack Compose App";
export const ANDROID_LIBRARY = "Android Library Template";

/**
 * React Constants
 */
export const REACT_TITLE = "React Application";
export const REACT_DESCRIPTION_JS = "JavaScript Language";
export const REACT_DESCRIPTION_TS = "JavaScript Language";

/**
 * NextJs Constants
 */
export const NEXT_TITLE = "NextJs Application";
export const NEXT_JS_DESCRIPTION = "JavaScript Language";
export const NEXT_TS_DESCRIPTION = "TypeScript Language";

/**
 * Compose Desktop Constants
 */
export const COMPOSE_TITLE = "Jetpack Compose";
export const COMPOSE_DESCRIPTION = "Desktop Application";

/**
 * Gradle Constants
 */
export const GRADLE_TITLE = "Gradle Plugins";
export const GRADLE_DESCRIPTION = "Kotlin Language";

/**
 * Ktor Constants
 */
export const KTOR_TITLE = "Ktor Application";
export const KTOR_DESCRIPTION = "Kotlin Language";

/**
 * Nest Js Constants
 */
export const NEST_JS = "NestJs Application";
export const NEST_JS_DESCRIPTION = "TypeScript Language";

/**
 * Spring Boot Constants
 */
export const SPRING_BOOT = "Spring Boot App";
export const SPRING_BOOT_DESCRIPTION_J = "Java Application";
export const SPRING_BOOT_DESCRIPTION_K = "Kotlin Application";

/**
 * Applications Icons Url's
 */
export const ANDROID_ICON = "https://user-images.githubusercontent.com/29167110/166839756-da0fbf50-b713-497a-946b-8e0a1dac92e9.png";
export const GRADLE_ICON = "https://user-images.githubusercontent.com/29167110/166839781-6aea6a9b-622c-4179-ada8-97ba2af00b32.png";
export const JETPACK_COMPOSE_ICON = "https://user-images.githubusercontent.com/29167110/166839823-12dacc81-177a-4afd-899d-7449d7b9f5a3.png";
export const KTOR_ICON = "https://user-images.githubusercontent.com/29167110/166839874-2f00e6fb-baa3-4189-a073-0a5c26064e9d.png";
export const NEST_JS_ICON = "https://user-images.githubusercontent.com/29167110/166839898-bae43899-a873-4038-b414-b2ed007a2482.png";
export const NEXT_JS_ICON = "https://user-images.githubusercontent.com/29167110/166839920-d4378dc6-792b-4d50-be38-fef353ed4a8d.png";
export const REACT_ICON = "https://user-images.githubusercontent.com/29167110/166839949-3f0f36fb-ccae-44c0-b027-d765b84a7183.png";
export const SPRING_BOOT_ICON = "https://user-images.githubusercontent.com/29167110/166839988-010e82fc-7b8c-4789-b6bc-1dea845037a0.png";

