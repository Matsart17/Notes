import {useEffect, useState} from "react";
import CreateForm from "./components/CreateForm.jsx";
import Note from "./components/Note.jsx";
import {createNote, deleteNote, getCategories, getNotes} from "./api.js"

function App() {
    const [notes, setNotes] = useState([])
    const [categories, setCategories] = useState([])
    useEffect(() => {
        getNotes()
            .then(setNotes)
            .catch(err => {
                setNotes([])
                console.error(err)
            })
    }, [])

    useEffect(() => {
        getCategories()
            .then(setCategories)
            .catch(err => {
                setCategories([])
                console.error(err)
            })
    }, []);
    const addNote = (note) => {
        setNotes([...notes, note])
        createNote(note).catch(err => console.error(err))
    }
    const deleteNoteLocal = (id) => {
        setNotes(p => p.filter((note, index) => index !== id))
        deleteNote(id).catch(err => console.error(err))
    }
    return (
        <div>
            <CreateForm onAddNote={addNote} categories={categories}/>
            <div className={"container"}>
                {notes && notes.map((item) => (
                    <Note
                        key={item.id}
                        id={item.id}
                        title={item.title}
                        category={item.category.title}
                        body={item.body}
                        onDelete={deleteNoteLocal}
                    />
                ))}
            </div>
        </div>
    )
}

export default App
