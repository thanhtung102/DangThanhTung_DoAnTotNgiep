/*
 * (C) Copyright 2022. All Rights Reserved.
 *
 * @author DongTHD
 * @date Mar 10, 2022
 */
package vn.fs.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.fs.entity.Category;
import vn.fs.entity.Contact;
import vn.fs.entity.Notification;
import vn.fs.repository.CategoryRepository;
import vn.fs.repository.ContactRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("api/contact")
public class ContactApi {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping
    public ResponseEntity<List<Contact>> getAll() {
        return ResponseEntity.ok(contactRepository.findAll());
    }
    //Lấy thông tin liên hệ theo id
    @GetMapping("{id}")
    public ResponseEntity<Contact> getById(@PathVariable("id") Long id) {
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contactRepository.findById(id).get());
    }
    @PostMapping
    public ResponseEntity<Contact> post(@RequestBody Contact contact) {
        if (contactRepository.existsById(contact.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(contactRepository.save(contact));
    }
    // xóa danh sách liên hệ theo id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!contactRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        contactRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
