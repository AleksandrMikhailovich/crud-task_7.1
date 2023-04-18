package app.controller;

import app.model.User;
import app.repository.UserRepository;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {  // получим по id
        model.addAttribute("user", userRepository.findOne(id));
        return "show";
    }

    @GetMapping()
    public String users(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, //добавляем нового юзера
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())               //проверяем на валидацию
            return "new";
        userRepository.save(user);
        return "redirect:/users"; //при добавлении возвращает нас на главную страницу со списком
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userRepository.findOne(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user, //изменяем данные
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())     //проверяем на валидацию
            return "edit";
        userRepository.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userRepository.delete(id);

        return "redirect:/users";
    }
}
