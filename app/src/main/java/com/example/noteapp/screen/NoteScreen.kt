package com.example.noteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.noteapp.R
import com.example.noteapp.components.NoteButton
import com.example.noteapp.components.NoteInputText
import com.example.noteapp.data.NotesDataSource
import com.example.noteapp.model.Note
import java.text.DateFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {

    var title by remember {
        mutableStateOf("")
    }
    
    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {

        TopAppBar(title = {Text(text= stringResource(id = R.string.app_name)) 
                          
        }, 
            
            actions = {
                Icon(imageVector = Icons.Rounded.Create, contentDescription = "ICON")
            })

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            NoteInputText(modifier = Modifier.padding(
                top = 10.dp,
                bottom = 8.dp
            ),
                text = title, label = "Title",
                onTextChange = {
                    if (it.all{char ->
                        char.isLetter() || char.isWhitespace()
                        }) title = it
                } )

            NoteInputText(modifier = Modifier.padding(
                top = 10.dp,
                bottom = 8.dp
            ),
                text = description, label = "Add a note",
                onTextChange = {if (it.all{char ->
                        char.isLetter() || char.isWhitespace()
                    }) description = it
                })

            NoteButton(modifier = Modifier,
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() && description.isNotEmpty()) {
                        onAddNote(Note(title = title,
                            description = description))
                        title = ""
                        description = ""
                        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    }
                })
        }

        Divider(modifier = Modifier.padding(12.dp))
        LazyColumn{
            items(notes) { note ->
                NoteRow(note = note,
                    onNoteClicked = {} )
                }

            }
        }
    }


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {
    val surfaceModifier = modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
        .fillMaxWidth()
    Surface(
        modifier = surfaceModifier,
        color = Color(0xFFDFE613)
    ) {
        Column(
            modifier = Modifier
                .clickable { onNoteClicked(note) }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        )  {
            Text(
                text = note.title,
                style = MaterialTheme.typography.labelMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = note.description,
                    style = MaterialTheme.typography.labelLarge
                )
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete icon"
                )
            }
            Text(
                text = note.entryDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                style = MaterialTheme.typography.labelSmall
            )

        }



    }



}


@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote ={})
}