package com.example.noteapp.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.NoteInputText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen() {

    Column(modifier = Modifier.padding(6.dp)) {

        TopAppBar(title = {Text(text= stringResource(id = R.string.app_name)) 
                          
        }, 
            
            actions = {
                Icon(imageVector = Icons.Rounded.Create, contentDescription = "ICON")
            })

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(modifier = Modifier.fillMaxWidth(),
                text = "Hello", label = "Hello",
                onTextChange = {} )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen()
}