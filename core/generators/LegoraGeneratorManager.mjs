import {
    ANDROID_LIBRARY_TEMPLATE,
    COMPOSE_APP_ANDROID,
    MULTI_APP_ANDROID,
    SINGLE_APP_ANDROID
} from "../models/ProjectModel.mjs";
import executeSingleModuleAndroidApplication from "./android-single-module/AndroidSingleModuleGenerator.mjs";
import executeMultiModuleAndroidApplication from "./android-multi-module/AndroidMultiModularGenerator.mjs";
import executeMultiModularJetpackComposeApplication from "./android-jetpack-compose-multi-modular/AndroidJetpackComposeGenerator.mjs";
import executeAndroidTemplateGenerator from "./android-library-template/AndroidLibraryGenerator.mjs";

/**
 * This File is The Main File to Start Generating the Target Project
 * After User Pick the Information it's Sent to This File and This File will Redirect to the Specific Generator
 */
export default function getProjectGenerator(
    generatePath = "",
    projectKey = "",
    dependencies = new Map(),
    information = new Map()
) {

    switch (projectKey) {
        case SINGLE_APP_ANDROID:
            executeSingleModuleAndroidApplication(generatePath, dependencies, information);
            break;

        case MULTI_APP_ANDROID:
            executeMultiModuleAndroidApplication(generatePath, dependencies, information);
            break;

        case COMPOSE_APP_ANDROID:
            executeMultiModularJetpackComposeApplication(generatePath, dependencies, information);
            break;

        case ANDROID_LIBRARY_TEMPLATE:
            executeAndroidTemplateGenerator(generatePath, dependencies, information);
            break;
    }

}
