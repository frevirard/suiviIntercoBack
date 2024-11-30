package com.example.creato.objets.donneurOrdre;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.creato.auth.MessageResponse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/donneurOrdre")
public class DonneurOrdreController {

    @Autowired
    DonneurOrdreRepository donneurOrdreRepository;

    @PostMapping("/add")
    public DonneurOrdre postMethodName(@RequestBody DonneurOrdre donneurOrdre) {
        return this.donneurOrdreRepository.save(donneurOrdre);
    }

    @DeleteMapping("delete/{donneurOrdreId}")
    public MessageResponse deleteDonneurOrdre(@PathVariable Long donneurOrdreId) {
        this.donneurOrdreRepository.deleteById(donneurOrdreId);
        return new MessageResponse("success");
    }

    @GetMapping("getAll")
    public List<DonneurOrdre> getMethodName() {
        return this.donneurOrdreRepository.findAll();
    }

}
