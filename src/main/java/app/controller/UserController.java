package app.controller;

import app.model.User;
import app.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {  // получим по id
        model.addAttribute("user", userDao.findOne(id));
        return "show";
    }

    @GetMapping()
    public String users(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user, //добавляем нового юзера
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())   {            //проверяем на валидацию
            model.addAttribute("users", userDao.findAll());
            return "new";}
        userDao.save(user);
        return "redirect:/users"; //при добавлении возвращает нас на главную страницу со списком
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.findOne(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update (@ModelAttribute("user") @Valid User user, //изменяем данные
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())     //проверяем на валидацию
            return "edit";
        userDao.update(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        userDao.remove(id);
        return "redirect:/users";
    }
}
