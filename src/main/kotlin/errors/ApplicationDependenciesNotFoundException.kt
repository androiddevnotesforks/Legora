package errors

import utils.ApplicationStrings

class ApplicationDependenciesNotFoundException: RuntimeException(ApplicationStrings.DEPENDENCIES_NOT_FOUND) {
}