package com.example.creato.objets.note;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.auth.MessageResponse;
import com.example.creato.objets.action.Action;

@RestController
@RequestMapping("note")
public class NoteController {

    @Autowired
    NoteRepository noteRepository;

    @PostMapping("/add")
    public Note postMethodName(@RequestBody Note note) {
        return this.noteRepository.save(note);
    }

    @DeleteMapping("delete/{noteId}")
    public MessageResponse deleteaction(@PathVariable Long noteId) {
        this.noteRepository.deleteById(noteId);
        return new MessageResponse("success");
    }

    @GetMapping("getAll/{auteur}")
    public List<Note> getMethodName(@PathVariable String auteur) {
        return this.noteRepository.findAllByAuteur(auteur);
    }

}
