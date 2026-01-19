package com.example.bingo.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
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
 * Address management screen
 * Minimal design with blue color scheme
 */
@Composable
fun AddressUIAddressScreen(
    onAddAddress: () -> Unit = {},
    onViewAddresses: () -> Unit = {}
) {
    LaunchedEffect(Unit) {
        BingoSDK.trackEvent("address_screen_viewed", mapOf(
            "sdk_version" to BingoSDK.VERSION
        ))
    }

    AddressUITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AddressUIColors.White
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Header
                AddressUIHeader()

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
                        color = AddressUIColors.TextPrimary,
                        textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Save your delivery addresses for faster checkout",
                        fontSize = 14.sp,
                        color = AddressUIColors.TextSecondary,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Add Address Button
                    AddressUIButton(
                        text = "Add New Address",
                        onClick = {
                            BingoSDK.trackEvent("add_address_clicked", emptyMap())
                            onAddAddress()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // View Addresses Button
                    AddressUIOutlinedButton(
                        text = "View Saved Addresses",
                        onClick = {
                            BingoSDK.trackEvent("view_addresses_clicked", emptyMap())
                            onViewAddresses()
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    // Footer
                    Text(
                        text = "Powered by Bingo SDK v${BingoSDK.VERSION}",
                        fontSize = 12.sp,
                        color = AddressUIColors.TextSecondary
                    )
                }
            }
        }
    }
}

/**
 * Address UI branded header
 */
@Composable
private fun AddressUIHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AddressUIColors.PrimaryBlue)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = 20.dp, horizontal = 24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = "Address",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AddressUIColors.White
        )
    }
}

/**
 * Address UI styled primary button
 */
@Composable
fun AddressUIButton(
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
            containerColor = AddressUIColors.PrimaryBlue,
            contentColor = AddressUIColors.White,
            disabledContainerColor = AddressUIColors.MediumGray,
            disabledContentColor = AddressUIColors.TextSecondary
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
 * Address UI styled outlined button
 */
@Composable
fun AddressUIOutlinedButton(
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
            contentColor = AddressUIColors.PrimaryBlue,
            disabledContentColor = AddressUIColors.TextSecondary
        ),
        border = BorderStroke(2.dp, AddressUIColors.PrimaryBlue),
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
 * Address UI styled text field
 */
@Composable
fun AddressUITextField(
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
            focusedBorderColor = AddressUIColors.PrimaryBlue,
            unfocusedBorderColor = AddressUIColors.MediumGray,
            focusedLabelColor = AddressUIColors.PrimaryBlue,
            cursorColor = AddressUIColors.PrimaryBlue
        ),
        shape = RoundedCornerShape(4.dp),
        singleLine = true
    )
}

/**
 * Address UI styled card for address display
 */
@Composable
fun AddressUIAddressCard(
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
            containerColor = AddressUIColors.LightGray
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
                    color = AddressUIColors.PrimaryBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = addressLine1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = AddressUIColors.TextPrimary
            )

            if (addressLine2.isNotEmpty()) {
                Text(
                    text = addressLine2,
                    fontSize = 14.sp,
                    color = AddressUIColors.TextSecondary
                )
            }

            Text(
                text = "$city, $state $zipCode",
                fontSize = 14.sp,
                color = AddressUIColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TextButton(
                    onClick = onEdit,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = AddressUIColors.PrimaryBlue
                    )
                ) {
                    Text("Edit", fontWeight = FontWeight.SemiBold)
                }

                TextButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = AddressUIColors.Error
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
 * Main Address UI App with full navigation and API integration
 */
@Composable
fun AddressUIApp(
    viewModel: AddressUIViewModel = remember { AddressUIViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    DisposableEffect(Unit) {
        onDispose { viewModel.cleanup() }
    }

    AddressUITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AddressUIColors.White
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                AddressUIHeaderWithBack(
                    title = when (uiState.currentScreen) {
                        AddressUIScreen.Home -> "Address"
                        AddressUIScreen.AddAddress -> "Add Address"
                        AddressUIScreen.AddressList -> "My Addresses"
                    },
                    showBack = uiState.currentScreen != AddressUIScreen.Home,
                    onBack = { viewModel.navigateTo(AddressUIScreen.Home) }
                )

                // Show messages
                uiState.error?.let { error ->
                    AddressUIErrorBanner(error) { viewModel.clearMessages() }
                }
                uiState.successMessage?.let { message ->
                    AddressUISuccessBanner(message) { viewModel.clearMessages() }
                }

                // Screen content
                when (uiState.currentScreen) {
                    AddressUIScreen.Home -> AddressUIHomeContent(
                        onAddAddress = { viewModel.navigateTo(AddressUIScreen.AddAddress) },
                        onViewAddresses = { viewModel.navigateTo(AddressUIScreen.AddressList) }
                    )
                    AddressUIScreen.AddAddress -> AddressUIAddAddressForm(
                        isLoading = uiState.isLoading,
                        onSubmit = { street, city, state, zip, isDefault ->
                            viewModel.addAddress(street, city, state, zip, isDefault)
                        },
                        onCancel = { viewModel.navigateTo(AddressUIScreen.Home) }
                    )
                    AddressUIScreen.AddressList -> AddressUIAddressListContent(
                        addresses = uiState.addresses,
                        onAddNew = { viewModel.navigateTo(AddressUIScreen.AddAddress) },
                        onDelete = { viewModel.deleteAddress(it) },
                        onSetDefault = { viewModel.setDefaultAddress(it) }
                    )
                }
            }
        }
    }
}

@Composable
private fun AddressUIHeaderWithBack(
    title: String,
    showBack: Boolean,
    onBack: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(AddressUIColors.PrimaryBlue)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        if (showBack) {
            TextButton(
                onClick = onBack,
                modifier = Modifier.align(Alignment.CenterStart),
                colors = ButtonDefaults.textButtonColors(contentColor = AddressUIColors.White)
            ) {
                Text("← Back", fontWeight = FontWeight.Medium)
            }
        }
        Text(
            text = title,
            fontSize = if (showBack) 20.sp else 28.sp,
            fontWeight = FontWeight.Bold,
            color = AddressUIColors.White,
            modifier = Modifier.align(if (showBack) Alignment.Center else Alignment.CenterStart)
        )
    }
}

@Composable
private fun AddressUIErrorBanner(message: String, onDismiss: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = AddressUIColors.Error.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                color = AddressUIColors.Error,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            TextButton(onClick = onDismiss) {
                Text("✕", color = AddressUIColors.Error)
            }
        }
    }
}

@Composable
private fun AddressUISuccessBanner(message: String, onDismiss: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = AddressUIColors.Success.copy(alpha = 0.1f)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = message,
                color = AddressUIColors.Success,
                modifier = Modifier.weight(1f),
                fontSize = 14.sp
            )
            TextButton(onClick = onDismiss) {
                Text("✕", color = AddressUIColors.Success)
            }
        }
    }
}

@Composable
private fun AddressUIHomeContent(
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
            color = AddressUIColors.TextPrimary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Save your delivery addresses for faster checkout",
            fontSize = 14.sp,
            color = AddressUIColors.TextSecondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        AddressUIButton(
            text = "Add New Address",
            onClick = {
                BingoSDK.trackEvent("add_address_clicked", emptyMap())
                onAddAddress()
            },
            modifier = Modifier.fillMaxWidth()
        )

        AddressUIOutlinedButton(
            text = "View Saved Addresses",
            onClick = {
                BingoSDK.trackEvent("view_addresses_clicked", emptyMap())
                onViewAddresses()
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Powered by Bingo SDK v${BingoSDK.VERSION}",
            fontSize = 12.sp,
            color = AddressUIColors.TextSecondary
        )
    }
}

@Composable
private fun AddressUIAddAddressForm(
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
            color = AddressUIColors.TextSecondary
        )

        AddressUITextField(
            value = street,
            onValueChange = { street = it },
            label = "Street Address",
            enabled = !isLoading
        )

        AddressUITextField(
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
                AddressUITextField(
                    value = state,
                    onValueChange = { state = it },
                    label = "State",
                    enabled = !isLoading
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                AddressUITextField(
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
                    checkedColor = AddressUIColors.PrimaryBlue
                )
            )
            Text(
                text = "Set as default address",
                fontSize = 14.sp,
                color = AddressUIColors.TextPrimary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = AddressUIColors.PrimaryBlue
            )
        } else {
            AddressUIButton(
                text = "Save Address",
                onClick = {
                    if (street.isNotBlank() && city.isNotBlank() && state.isNotBlank() && zipCode.isNotBlank()) {
                        BingoSDK.trackEvent("address_submitted", mapOf(
                            "city" to city,
                            "state" to state
                        ))
                        onSubmit(street, city, state, zipCode, isDefault)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = street.isNotBlank() && city.isNotBlank() && state.isNotBlank() && zipCode.isNotBlank()
            )

            AddressUIOutlinedButton(
                text = "Cancel",
                onClick = onCancel,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AddressUIAddressListContent(
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
                        color = AddressUIColors.TextSecondary
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AddressUIButton(
                        text = "Add Your First Address",
                        onClick = onAddNew
                    )
                }
            }
        } else {
            Text(
                text = "${addresses.size} saved address${if (addresses.size > 1) "es" else ""}",
                fontSize = 14.sp,
                color = AddressUIColors.TextSecondary
            )

            addresses.forEach { address ->
                AddressUIAddressCardWithActions(
                    address = address,
                    onDelete = { onDelete(address.id) },
                    onSetDefault = { onSetDefault(address.id) }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            AddressUIOutlinedButton(
                text = "Add Another Address",
                onClick = onAddNew,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun AddressUIAddressCardWithActions(
    address: SavedAddress,
    onDelete: () -> Unit,
    onSetDefault: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = AddressUIColors.LightGray),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            if (address.isDefault) {
                Text(
                    text = "DEFAULT",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = AddressUIColors.PrimaryBlue
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            Text(
                text = address.street,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = AddressUIColors.TextPrimary
            )

            Text(
                text = "${address.city}, ${address.state} ${address.zipCode}",
                fontSize = 14.sp,
                color = AddressUIColors.TextSecondary
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                if (!address.isDefault) {
                    TextButton(
                        onClick = onSetDefault,
                        colors = ButtonDefaults.textButtonColors(contentColor = AddressUIColors.PrimaryBlue)
                    ) {
                        Text("Set Default", fontWeight = FontWeight.SemiBold)
                    }
                }
                TextButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.textButtonColors(contentColor = AddressUIColors.Error)
                ) {
                    Text("Delete", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

// ==================== PREVIEWS ====================

@Composable
fun AddressUIAddressScreenPreview() {
    AddressUIAddressScreen()
}

@Composable
fun AddressUIButtonPreview() {
    AddressUITheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AddressUIButton(text = "Add New Address", onClick = {})
            AddressUIButton(text = "Disabled Button", onClick = {}, enabled = false)
        }
    }
}

@Composable
fun AddressUIOutlinedButtonPreview() {
    AddressUITheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AddressUIOutlinedButton(text = "View Addresses", onClick = {})
            AddressUIOutlinedButton(text = "Disabled", onClick = {}, enabled = false)
        }
    }
}

@Composable
fun AddressUITextFieldPreview() {
    AddressUITheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            var text by remember { mutableStateOf("") }
            AddressUITextField(
                value = text,
                onValueChange = { text = it },
                label = "Street Address"
            )
        }
    }
}

@Composable
fun AddressUIAddressCardPreview() {
    AddressUITheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            AddressUIAddressCard(
                addressLine1 = "1000 Main Blvd",
                city = "Mooresville",
                state = "NC",
                zipCode = "28117",
                isDefault = true
            )
            AddressUIAddressCard(
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
fun AddressUIComponentsPreview() {
    AddressUITheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AddressUIColors.White
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header preview
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AddressUIColors.PrimaryBlue)
                        .padding(vertical = 20.dp, horizontal = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = "Address",
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = AddressUIColors.White
                    )
                }

                // Buttons
                AddressUIButton(text = "Primary Button", onClick = {})
                AddressUIOutlinedButton(text = "Secondary Button", onClick = {})

                // Text field
                var text by remember { mutableStateOf("") }
                AddressUITextField(value = text, onValueChange = { text = it }, label = "Address")

                // Card
                AddressUIAddressCard(
                    addressLine1 = "1000 Main Blvd",
                    city = "Mooresville",
                    state = "NC",
                    zipCode = "28117",
                    isDefault = true
                )
            }
        }
    }
}
