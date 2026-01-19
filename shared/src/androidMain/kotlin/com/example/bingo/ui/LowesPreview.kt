package com.example.bingo.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, name = "Lowe's Address Screen")
@Composable
fun PreviewLowesAddressScreen() {
    LowesAddressScreenPreview()
}

@Preview(showBackground = true, name = "Lowe's Buttons")
@Composable
fun PreviewLowesButtons() {
    LowesButtonPreview()
}

@Preview(showBackground = true, name = "Lowe's Outlined Buttons")
@Composable
fun PreviewLowesOutlinedButtons() {
    LowesOutlinedButtonPreview()
}

@Preview(showBackground = true, name = "Lowe's Text Field")
@Composable
fun PreviewLowesTextField() {
    LowesTextFieldPreview()
}

@Preview(showBackground = true, name = "Lowe's Address Cards")
@Composable
fun PreviewLowesAddressCards() {
    LowesAddressCardPreview()
}

@Preview(showBackground = true, showSystemUi = true, name = "Lowe's All Components")
@Composable
fun PreviewLowesComponents() {
    LowesComponentsPreview()
}

@Preview(showBackground = true, showSystemUi = true, name = "Lowe's Full App")
@Composable
fun PreviewLowesApp() {
    LowesApp()
}
