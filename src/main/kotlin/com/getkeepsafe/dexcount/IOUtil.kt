/*
 * Copyright (C) 2016 KeepSafe Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.getkeepsafe.dexcount

import java.io.File
import java.io.InputStream
import java.io.PrintStream

object IOUtil {
    @JvmStatic fun drainToFile(stream: InputStream, file: File) {
        stream.use { input ->
            File(file.path).outputStream().use { output ->
                input.copyTo(output)
                output.flush()
            }
        }
    }
}

fun File.writeFromStream(stream: InputStream) {
    this.outputStream().use { output ->
        stream.copyTo(output)
    }
}

fun InputStream.copyToFile(file: File) {
    file.outputStream().use {
        copyTo(it)
        it.flush()
    }
}

fun File.printStream(): PrintStream {
    parentFile.mkdirs()
    createNewFile()
    return PrintStream(outputStream())
}
