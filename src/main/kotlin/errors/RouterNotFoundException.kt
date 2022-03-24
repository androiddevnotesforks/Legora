package errors

import utils.ApplicationStrings

class RouterNotFoundException: RuntimeException(ApplicationStrings.ROUTER_NOT_FOUND_MESSAGE) {
}