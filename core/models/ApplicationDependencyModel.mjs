/**
 * Some Projects Require Dependencies to Work
 * And This File Contains The Dependency Model for Each Project
 * This Dependencies is Picked from User By Selecting Each one of them
 *
 * There is a Dependencies is Pre Selected and it Can't be UnSelect because it's a Primary Dependency
 */
export class ApplicationDependency {

    constructor(
        dependency = "",
        version = "",
        isRemovable = true,
        isSelected = false
    ) {
        this.dependency = dependency
        this.version = version
        this.isRemovable = isRemovable
        this.isSelected = isSelected
    }

}
