package com.apartments.api

import java.util.*

object ApiConsts {
    const val VERSION = "/v1/api/"
    const val fields = "fields"
    const val SELECTION_ALL = "all"

    fun getRandomGeneratedCode(): String {
        var code = ""
        for (i in 1..7) {
            code += Random().nextInt(9)
        }
        return code
    }

}
