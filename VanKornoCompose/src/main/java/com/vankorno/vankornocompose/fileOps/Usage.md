# Usage


```kotlin

val storage = AppFiles(context)

storage.dir("user_pics") {
    val list = list()
    writeText("note.txt", "hello")
    rename("note.txt", "note2")
    delete("old.png")
}

storage.dir("exports") {
    val path = writeText("backup.json", json)
}

```

With URI import

```kotlin

storage.dir("user_pics") {
    val path = saveFromUri(uri, "imported.png")
}

```

