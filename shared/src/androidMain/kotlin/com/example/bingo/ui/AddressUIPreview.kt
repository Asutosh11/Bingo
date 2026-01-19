package com.example.bingo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, name = "Address Screen")
@Composable
fun PreviewAddressUIAddressScreen() {
    AddressUIAddressScreenPreview()
}

@Preview(showBackground = true, name = "Address Buttons")
@Composable
fun PreviewAddressUIButtons() {
    AddressUIButtonPreview()
}

@Preview(showBackground = true, name = "Address Outlined Buttons")
@Composable
fun PreviewAddressUIOutlinedButtons() {
    AddressUIOutlinedButtonPreview()
}

@Preview(showBackground = true, name = "Address Text Field")
@Composable
fun PreviewAddressUITextField() {
    AddressUITextFieldPreview()
}

@Preview(showBackground = true, name = "Address Cards")
@Composable
fun PreviewAddressUIAddressCards() {
    AddressUIAddressCardPreview()
}

@Preview(showBackground = true, showSystemUi = true, name = "Address All Components")
@Composable
fun PreviewAddressUIComponents() {
    AddressUIComponentsPreview()
}

@Preview(showBackground = true, showSystemUi = true, name = "Address Full App")
@Composable
fun PreviewAddressUIApp() {
    AddressUIApp()
}
