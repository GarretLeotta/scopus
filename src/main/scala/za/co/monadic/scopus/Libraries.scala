/*
 * Copyright 2014 David Weber
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package za.co.monadic.scopus

import za.co.monadic.scopus.opus.Opus
import za.co.monadic.scopus.speex.Speex

/**
  * Load all the native libraries
  */
object Libraries {
  // System dependent load of native libraries
  LibLoader.getOsArch match {
    case "Linux/amd64" | "Linux/i386" =>
      LibLoader("libopus.so.0", load = false) // Don't load this as it is dynamically found by the linker in Linux
      LibLoader("libspeex.so.1", load = false)
      LibLoader("libspeexdsp.so.1", load = false)
      LibLoader("libjni_opus.so")
    case "Mac OS X/x86_64" =>
      LibLoader("libopus.0.dylib", load = false)
      LibLoader("libspeex.1.dylib", load = false)
      LibLoader("libspeexdsp.1.dylib", load = false)
      LibLoader("libjni_opus.dylib")
    case "Windows 10/amd64" =>
      LibLoader("libopus-0.dll")
      LibLoader("libspeex-1.dll")
      LibLoader("libspeexdsp-1.dll")
      LibLoader("libjni_opus.dll")
    case s: String =>
      println(s"Unknown OS/platform combination: $s")
      sys.exit(-1)
  }
  // Verify we have the correct library loaded. Linux sometimes messes this up.
  if (Opus.get_version_string() != "libopus 1.2.1")
    throw new RuntimeException(s"libopus version must be 1.2.1: ${Opus.get_version_string()} found.")

  if ( Speex.get_version_string() != "1.2.0")
    throw new RuntimeException(s"libspeex version must be 1.2.0: ${Speex.get_version_string()} found")

  def apply(): Unit = Unit
}
