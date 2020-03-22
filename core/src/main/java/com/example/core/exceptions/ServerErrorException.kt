package com.example.core.exceptions

import java.io.IOException

open class ServerErrorException(message: String) : IOException(message)
