/**
 * After Picking the Project Type from Projects List
 * User should Navigate to Projects Information List to Add The Project Information
 *
 * This Project Information is a List of Items That Provide Info about the App
 * Example:
 * Android Applications Has the Following Information
 * 1. Gradle Plugin Version
 * 2. App Name
 * 3. App Description
 * 4. Programming Language
 * 5. Database Type
 * 6. Package Name
 * 7. Version
 *
 * The Same Logic is Applied on all Projects, Each one of Them Has it's Own Attributes
 *
 * The Key is a String to Get the Selected Value From it's Own Key
 * For Example:
 * Project Has a lot of Attributes
 * In this Stage i want to get the Package name
 * I Request the Key PACKAGE_NAME from the List Given from CLI Args
 *
 * The Type Attribute is To Decide Which View Want to Draw
 * TEXT -> To Show Text Field to Let User Write Value
 * SWITCH -> To Show Radio Buttons to Let User Decide If he want this Feature or Not
 */
export class ProjectInformationItem {
    constructor(
        key = "",
        type = 0,
        hint = "",
        title = "",
        selectedValue = ""
    ) {
        this.key = key;
        this.type = type;
        this.hint = hint;
        this.key = key;
        this.title = title;
        this.selectedValue = selectedValue;
    }
}

/**
 * The Following Keys is the Supported Keys for All Projects Info in Legora
 * This Keys Will be Mapped for Projects and Show it on CLI and UI
 * Once User Select and Fill the Data The UI Will Send key, Value List to Generators
 * Other Params Only for UI
 */
export const NAME = "NAME";
export const PACKAGE_NAME = "PACKAGE_NAME";
export const WEBSITE_URL = "WEBSITE_URL";
export const VERSION = "VERSION";
export const ARTIFACT = "ARTIFACT";
export const GROUP = "GROUP";
export const DESCRIPTION = "DESCRIPTION";
export const GIT_LINK = "GIT_LINK";
export const REPO_NAME = "REPO_NAME";
export const AUTHOR = "AUTHOR";
export const WEBSITE = "WEBSITE";

/**
 * The Following Types is The Types Supported to View UI in Desktop Application
 */
export const TYPE_TEXT = "TYPE_TEXT";
export const TYPE_SWITCH = "TYPE_SWITCH";
