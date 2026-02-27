
```kotlin
val focusRequester = remember { FocusRequester() }

LaunchedEffect(vm.focusRequest) {
    vm.focusRequest.collect {
        focusRequester.requestFocus()
    }
}

LaunchedEffect(vm.clearFocusRequest) {
    vm.clearFocusRequest.collect {
        focusRequester.freeFocus() // or move focus somewhere else
    }
}

TextField(
    value = vm.state().value,
    onValueChange = { vm.updateFrom(it) },
    modifier = Modifier
        .focusRequester(focusRequester)
        .onFocusChanged { vm.hasFocus.value = it.isFocused }
)
```