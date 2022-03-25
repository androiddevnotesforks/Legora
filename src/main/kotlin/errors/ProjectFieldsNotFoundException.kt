package errors

import utils.ApplicationStrings

class ProjectFieldsNotFoundException: RuntimeException(ApplicationStrings.FIELDS_NOT_FOUND) {
}