package com.example.creato.objets.action;

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
@RequestMapping("/action")
public class ActionController {

    @Autowired
    ActionRepository actionRepository;

    @PostMapping("/add")
    public Action postMethodName(@RequestBody Action action) {

        return this.actionRepository.save(action);
    }

    @DeleteMapping("delete/{actionId}")
    public MessageResponse deleteaction(@PathVariable Long actionId) {
        this.actionRepository.deleteById(actionId);
        return new MessageResponse("success");
    }

    @GetMapping("getAll")
    public List<Action> getMethodName() {
        return this.actionRepository.findAll();
    }

}
