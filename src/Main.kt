/**
 * Assignment 2: Kotlin String Manipulation Functions
 */

/**
 * A. Create a function that encrypts a supplied string by shifting the position of each
 * letter by a supplied integer (the "key"). For example, if the key is 1, then a
 * becomes b, c becomes d, and z becomes a, and so on.
 */
fun encryptString(text: String, key: Int): String {
    val result = StringBuilder()

    for (char in text) {
        when {
            char.isLowerCase() -> {
                // Handle lowercase letters (a-z)
                val shifted = ((char - 'a' + key) % 26 + 26) % 26
                result.append('a' + shifted)
            }
            char.isUpperCase() -> {
                // Handle uppercase letters (A-Z)
                val shifted = ((char - 'A' + key) % 26 + 26) % 26
                result.append('A' + shifted)
            }
            else -> {
                // Keep non-alphabetic characters unchanged
                result.append(char)
            }
        }
    }

    return result.toString()
}

/**
 * B. Create a function that compares two single words and determines whether they
 * are anagrams of each other (e.g., dusty and study are anagrams because you can
 * rearrange the letters of dusty to create study, and vice versa).
 */
fun areAnagrams(word1: String, word2: String): Boolean {
    // Convert to lowercase to handle case-insensitive comparison
    val cleanWord1 = word1.lowercase().filter { it.isLetter() }
    val cleanWord2 = word2.lowercase().filter { it.isLetter() }

    // If lengths are different, they can't be anagrams
    if (cleanWord1.length != cleanWord2.length) {
        return false
    }

    // Count character frequencies in both words
    val charCount1 = mutableMapOf<Char, Int>()
    val charCount2 = mutableMapOf<Char, Int>()

    for (char in cleanWord1) {
        charCount1[char] = charCount1.getOrDefault(char, 0) + 1
    }

    for (char in cleanWord2) {
        charCount2[char] = charCount2.getOrDefault(char, 0) + 1
    }

    return charCount1 == charCount2
}

/**
 * C. Without using the String.Contains method, write a function that compares two
 * strings and determines whether the characters in the second string are a substring
 * of the characters in the first string.
 */
fun isSubstring(mainString: String, subString: String): Boolean {
    if (subString.isEmpty()) return true
    if (subString.length > mainString.length) return false

    for (i in 0..mainString.length - subString.length) {
        var match = true
        for (j in subString.indices) {
            if (mainString[i + j] != subString[j]) {
                match = false
                break
            }
        }
        if (match) return true
    }

    return false
}

/**
 * D. Create a function that analyzes a string and returns the longest word in that string.
 */
fun findLongestWord(text: String): String {
    if (text.isBlank()) return ""

    // Split the string into words, removing empty strings and non-alphabetic characters
    val words = text.split(Regex("\\s+"))
        .map { word -> word.filter { it.isLetter() } }
        .filter { it.isNotEmpty() }

    if (words.isEmpty()) return ""

    return words.maxByOrNull { it.length } ?: ""
}

fun main() {
    println("=== Kotlin Assignment 2: String Manipulation Functions ===\n")

    // Test A: String Encryption
    println("A. String Encryption Tests:")
    println("encrypt('abc', 1) = '${encryptString("abc", 1)}'")
    println()

    // Test B: Anagram Detection
    println("B. Anagram Detection Tests:")
    println("areAnagrams('dusty', 'study') = ${areAnagrams("dusty", "study")}")
    println("areAnagrams('The Eyes', 'They See') = ${areAnagrams("The Eyes", "They See")}")
    println()

    // Test C: Substring Detection (without String.contains)
    println("C. Substring Detection Tests:")
    println("isSubstring('hello world', 'world') = ${isSubstring("hello world", "world")}")
    println("isSubstring('kotlin', 'java') = ${isSubstring("kotlin", "java")}")
    println()

    // Test D: Longest Word Finder
    println("D. Longest Word Finder Tests:")
    println("findLongestWord('I love programming in Kotlin!') = '${findLongestWord("I love programming in Kotlin!")}'")
    println()

    println("=== All tests completed! ===")
}