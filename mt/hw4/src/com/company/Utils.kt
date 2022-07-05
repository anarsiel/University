package com.company


fun String.escape() = replace("\\", "\\\\").replace("\"", "\\\"")