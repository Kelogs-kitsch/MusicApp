package com.keylogs.musicapp.ui.components

import android.text.method.TextKeyListener.clear
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    val textFieldState = remember { TextFieldState() }
    var expanded by rememberSaveable { mutableStateOf(false) }
    var searchResults by remember { mutableStateOf(listOf<String>()) }

    // Sample data
    val allItems = listOf(
        "Song A", "Song B", "Another Song",
        "Cool Track", "My Favorite Song", "Music Note"
    )

    // Filtering logic
    fun onSearch(query: String) {
        searchResults = if (query.isBlank()) {
            emptyList()
        } else {
            allItems.filter { it.contains(query, ignoreCase = true) }
        }
    }

    Box(
        modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true }
    ) {
        androidx.compose.material3.SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = 0f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = textFieldState.text.toString(),
                    onQueryChange = {
                        textFieldState.edit {
                            replace(0, length, it)
                        }
                        onSearch(it)
                    },
                    onSearch = {
                        onSearch(textFieldState.text.toString())
                        expanded = false
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    leadingIcon = {
                        Icon(
                            imageVector = if (expanded) Icons.Default.ArrowBack else Icons.Default.Search,
                            contentDescription = if (expanded) "Back" else "Search",
                            modifier = Modifier.clickable {
                                if (expanded) {
                                    // Collapse and clear text
                                    expanded = false
                                    textFieldState.edit { replace(0, length, "") }
                                    searchResults = emptyList()
                                }
                            }
                        )
                    },
                    trailingIcon = {
                        if (textFieldState.text.isNotBlank()) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear",
                                modifier = Modifier.clickable {
                                    textFieldState.edit { replace(0, length, "") }
                                    searchResults = emptyList()
                                }
                            )
                        }
                    },
                    placeholder = { Text("Search") }
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 8.dp)
            ) {
                searchResults.forEach { result ->
                    ListItem(
                        headlineContent = { Text(result) },
                        modifier = Modifier
                            .clickable {
                                textFieldState.edit {
                                    replace(0, length, result)
                                }
                                expanded = false
                            }
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    )
                }
            }
        }
    }
}
