package com.example.bingo.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bingo.BingoSDK

/**
 * Lowe's themed address management screen
 * Minimal design with Lowe's blue color scheme
 */
@Composable
fun LowesAddressScreen(
    onAddAddress: () -> Unit = {},
    onViewAddresses: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        BingoSDK.trackEvent("lowes_screen_viewed", mapOf(
            "sdk_version" to BingoSDK.VERSION
        ))
    }

    LowesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LowesColors.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                LowesHeader()

                // Content
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = "Manage Your Addresses",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = LowesColors.TextPrimary,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Save your delivery addresses for faster checkout",
                        fontSize = 14.sp,
                        color = LowesColors.TextSecondary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Add Address Button
                    LowesButton(
                        text = "Add New Address",
                        onClick = {
                            BingoSDK.trackEvent("lowes_add_address_clicked", emptyMap())
                            onAddAddress()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // View Addresses Button
                    LowesOutlinedButton(
                        text = "View Saved Addresses",
                        onClick = {
                            BingoSDK.trackEvent("lowes_view_addresses_clicked", emptyMap())
                            onViewAddresses()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Footer
                    Text(
                        text = "Powered by Bingo SDK v${BingoSDK.VERSION}",
                        fontSize = 12.sp,
                        color = LowesColors.TextSecondary
                    )
                }
            }
        }
    }
}

/**
 * Lowe's branded header
 */
@Composable
private fun LowesHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LowesColors.LowesBlue)
            .padding(vertical = 20.dp, horizontal = 24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Lowe's",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = LowesColors.White
        )
    }
}

/**
 * Lowe's styled primary button
 */
@Composable
fun LowesButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(52.dp)
            .clip(RoundedCornerShape(4.dp)),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = LowesColors.LowesBlue,
            contentColor = LowesColors.White,
            disabledContainerColor = LowesColors.MediumGray,
            disabledContentColor = LowesColors.TextSecondary
        ),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Lowe's styled outlined button
 */
@Composable
fun LowesOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .height(52.dp),
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = LowesColors.LowesBlue,
            disabledContentColor = LowesColors.TextSecondary
        ),
        border = BorderStroke(2.dp, LowesColors.LowesBlue),
        shape = RoundedCornerShape(4.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

/**
 * Lowe's styled text field
 */
@Composable
fun LowesTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = LowesColors.LowesBlue,
            unfocusedBorderColor = LowesColors.MediumGray,
            focusedLabelColor = LowesColors.LowesBlue,
            cursorColor = LowesColors.LowesBlue
        ),
        shape = RoundedCornerShape(4.dp),
        singleLine = true
    )
}

/**
 * Lowe's styled card for address display
 */
@Composable
fun LowesAddressCard(
    addressLine1: String,
    addressLine2: String = "",
    city: String,
    state: String,
    zipCode: String,
    isDefault: Boolean = false,
    onEdit: () -> Unit = {},
    onDelete: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = LowesColors.LightGray
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            if (isDefault) {
                Text(
                    text = "DEFAULT",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = LowesColors.LowesBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = addressLine1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = LowesColors.TextPrimary
            )

            if (addressLine2.isNotEmpty()) {
                Text(
                    text = addressLine2,
                    fontSize = 14.sp,
                    color = LowesColors.TextSecondary
                )
            }

            Text(
                text = "$city, $state $zipCode",
                fontSize = 14.sp,
                color = LowesColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextButton(
                    onClick = onEdit,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = LowesColors.LowesBlue
                    )
                ) {
                    Text("Edit", fontWeight = FontWeight.SemiBold)
                }

                TextButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = LowesColors.Error
                    )
                ) {
                    Text("Delete", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

// ==================== MAIN APP WITH NAVIGATION ====================

/**
 * Main Lowe's App with full navigation and API integration
 */
@Composable
fun LowesApp(
    viewModel: LowesAddressViewModel = remember { LowesAddressViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    DisposableEffect(Unit) {
        onDispose { viewModel.cleanup() }
    }

    LowesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LowesColors.White
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                LowesHeaderWithBack(
                    title = when (uiState.currentScreen) {
                        LowesScreen.Home -> "Lowe's"
                        LowesScreen.AddAddress -> "Add Address"
                        LowesScreen.AddressList -> "My Addresses"
                    },
                    showBack = uiState.currentScreen != LowesScreen.Home,
                    onBack = { viewModel.navigateTo(LowesScreen.Home) }
                )

                // Show messages
                uiState.error?.let { error ->
                    LowesErrorBanner(error) { viewModel.clearMessages() }
                }
                uiState.successMessage?.let { message ->
                    LowesSuccessBanner(message) { viewModel.clearMessages() }
                }

                // Screen content
                when (uiState.currentScreen) {
                    LowesScreen.Home -> LowesHomeContent(
                        onAddAddress = { viewModel.navigateTo(LowesScreen.AddAddress) },
                        onViewAddresses = { viewModel.navigateTo(LowesScreen.AddressList) }
                    )
                    LowesScreen.AddAddress -> LowesAddAddressForm(
                        isLoading = uiState.isLoading,
                        onSubmit = { street, city, state, zip, isDefault ->
                            viewModel.addAddress(street, city, state, zip, isDefault)
                        },
                        onCancel = { viewModel.navigateTo(LowesScreen.Home) }
                    )
                    LowesScreen.AddressList -> LowesAddressListContent(
                        addresses = uiState.addresses,
                        onAddNew = { viewModel.navigateTo(LowesScreen.AddAddress) },
                        onDelete = { viewModel.deleteAddress(it) },
                        onSetDefault = { viewModel.setDefaultAddress(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun LowesHeaderWithBack(
    title: String,
    showBack: Boolean,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(LowesColors.LowesBlue)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        if (showBack) {
            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart),
                colors = ButtonDefaults.textButtonColors(contentColor = LowesColors.White)
            ) {
                Text("← Back", fontWeight = FontWeight.Medium)
            }
        }
        Text(
            text = title,
            fontSize = if (showBack) 20.sp else 28.sp,
            fontWeight = FontWeight.Bold,
            color = LowesColors.White,
            modifier = Modifier.align(if (showBack) Alignment.Center else Alignment.CenterStart)
        )
    }
}

@Composable
private fun LowesErrorBanner(message: String, onDismiss: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = LowesColors.Error.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                color = LowesColors.Error,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            TextButton(onClick = onDismiss) {
                Text("✕", color = LowesColors.Error)
            }
        }
    }
}

@Composable
private fun LowesSuccessBanner(message: String, onDismiss: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = LowesColors.Success.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                color = LowesColors.Success,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            TextButton(onClick = onDismiss) {
                Text("✕", color = LowesColors.Success)
            }
        }
    }
}

@Composable
private fun LowesHomeContent(
    onAddAddress: () -> Unit,
    onViewAddresses: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Manage Your Addresses",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = LowesColors.TextPrimary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Save your delivery addresses for faster checkout",
            fontSize = 14.sp,
            color = LowesColors.TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        LowesButton(
            text = "Add New Address",
            onClick = {
                BingoSDK.trackEvent("lowes_add_address_clicked", emptyMap())
                onAddAddress()
            },
            modifier = Modifier.fillMaxWidth()
        )

        LowesOutlinedButton(
            text = "View Saved Addresses",
            onClick = {
                BingoSDK.trackEvent("lowes_view_addresses_clicked", emptyMap())
                onViewAddresses()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Powered by Bingo SDK v${BingoSDK.VERSION}",
            fontSize = 12.sp,
            color = LowesColors.TextSecondary
        )
    }
}

@Composable
private fun LowesAddAddressForm(
    isLoading: Boolean,
    onSubmit: (street: String, city: String, state: String, zipCode: String, isDefault: Boolean) -> Unit,
    onCancel: () -> Unit
) {
    var street by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    var zipCode by remember { mutableStateOf("") }
    var isDefault by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Enter your delivery address",
            fontSize = 16.sp,
            color = LowesColors.TextSecondary
        )

        LowesTextField(
            value = street,
            onValueChange = { street = it },
            label = "Street Address",
            enabled = !isLoading
        )

        LowesTextField(
            value = city,
            onValueChange = { city = it },
            label = "City",
            enabled = !isLoading
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                LowesTextField(
                    value = state,
                    onValueChange = { state = it },
                    label = "State",
                    enabled = !isLoading
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                LowesTextField(
                    value = zipCode,
                    onValueChange = { zipCode = it },
                    label = "ZIP Code",
                    enabled = !isLoading
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Checkbox(
                checked = isDefault,
                onCheckedChange = { isDefault = it },
                enabled = !isLoading,
                colors = CheckboxDefaults.colors(
                    checkedColor = LowesColors.LowesBlue
                )
            )
            Text(
                text = "Set as default address",
                fontSize = 14.sp,
                color = LowesColors.TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = LowesColors.LowesBlue
            )
        } else {
            LowesButton(
                text = "Save Address",
                onClick = {
                    if (street.isNotBlank() && city.isNotBlank() && state.isNotBlank() && zipCode.isNotBlank()) {
                        BingoSDK.trackEvent("lowes_address_submitted", mapOf(
                            "city" to city,
                            "state" to state
                        ))
                        onSubmit(street, city, state, zipCode, isDefault)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = street.isNotBlank() && city.isNotBlank() && state.isNotBlank() && zipCode.isNotBlank()
            )

            LowesOutlinedButton(
                text = "Cancel",
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LowesAddressListContent(
    addresses: List<SavedAddress>,
    onAddNew: () -> Unit,
    onDelete: (String) -> Unit,
    onSetDefault: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (addresses.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 48.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No addresses saved yet",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = LowesColors.TextSecondary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LowesButton(
                        text = "Add Your First Address",
                        onClick = onAddNew
                    )
                }
            }
        } else {
            Text(
                text = "${addresses.size} saved address${if (addresses.size > 1) "es" else ""}",
                fontSize = 14.sp,
                color = LowesColors.TextSecondary
            )

            addresses.forEach { address ->
                LowesAddressCardWithActions(
                    address = address,
                    onDelete = { onDelete(address.id) },
                    onSetDefault = { onSetDefault(address.id) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            LowesOutlinedButton(
                text = "Add Another Address",
                onClick = onAddNew,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun LowesAddressCardWithActions(
    address: SavedAddress,
    onDelete: () -> Unit,
    onSetDefault: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = LowesColors.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (address.isDefault) {
                Text(
                    text = "DEFAULT",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = LowesColors.LowesBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = address.street,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = LowesColors.TextPrimary
            )

            Text(
                text = "${address.city}, ${address.state} ${address.zipCode}",
                fontSize = 14.sp,
                color = LowesColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (!address.isDefault) {
                    TextButton(
                        onClick = onSetDefault,
                        colors = ButtonDefaults.textButtonColors(contentColor = LowesColors.LowesBlue)
                    ) {
                        Text("Set Default", fontWeight = FontWeight.SemiBold)
                    }
                }
                TextButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.textButtonColors(contentColor = LowesColors.Error)
                ) {
                    Text("Delete", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

// ==================== PREVIEWS ====================

@Composable
fun LowesAddressScreenPreview() {
    LowesAddressScreen()
}

@Composable
fun LowesButtonPreview() {
    LowesTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LowesButton(text = "Add New Address", onClick = {})
            LowesButton(text = "Disabled Button", onClick = {}, enabled = false)
        }
    }
}

@Composable
fun LowesOutlinedButtonPreview() {
    LowesTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            LowesOutlinedButton(text = "View Addresses", onClick = {})
            LowesOutlinedButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Composable
fun LowesTextFieldPreview() {
    LowesTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var text by remember { mutableStateOf("") }
            LowesTextField(
                value = text,
                onValueChange = { text = it },
                label = "Street Address"
            )
        }
    }
}

@Composable
fun LowesAddressCardPreview() {
    LowesTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LowesAddressCard(
                addressLine1 = "1000 Lowes Blvd",
                city = "Mooresville",
                state = "NC",
                zipCode = "28117",
                isDefault = true
            )
            LowesAddressCard(
                addressLine1 = "123 Main Street",
                addressLine2 = "Apt 4B",
                city = "Charlotte",
                state = "NC",
                zipCode = "28202"
            )
        }
    }
}

@Composable
fun LowesComponentsPreview() {
    LowesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = LowesColors.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header preview
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(LowesColors.LowesBlue)
                        .padding(vertical = 20.dp, horizontal = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Lowe's",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = LowesColors.White
                    )
                }

                // Buttons
                LowesButton(text = "Primary Button", onClick = {})
                LowesOutlinedButton(text = "Secondary Button", onClick = {})

                // Text field
                var text by remember { mutableStateOf("") }
                LowesTextField(value = text, onValueChange = { text = it }, label = "Address")

                // Card
                LowesAddressCard(
                    addressLine1 = "1000 Lowes Blvd",
                    city = "Mooresville",
                    state = "NC",
                    zipCode = "28117",
                    isDefault = true
                )
            }
        }
    }
}
