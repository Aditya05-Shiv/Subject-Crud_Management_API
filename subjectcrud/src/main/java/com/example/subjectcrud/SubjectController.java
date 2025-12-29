
package com.example.subjectcrud;

import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectRepository repo;

    public SubjectController(SubjectRepository repo) {
        this.repo = repo;
    }

    // GET all subjects
    @GetMapping
    public List<Subject> getAllSubjects() {
        return repo.findAll();
    }

    // POST add new subject
    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        repo.save(subject);
        return "Subject added successfully";
    }

    // DELETE subject by id
    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Subject deleted!";
        }
        return "Subject not found!";
    }

    // PUT update subject
    @PutMapping("/{id}")
    public String updateSubject(@PathVariable int id,
                                @RequestBody Subject updatedSubject) {

        return repo.findById(id)
                .map(subject -> {
                    subject.setSubjectCode(updatedSubject.getSubjectCode());
                    subject.setSubjectName(updatedSubject.getSubjectName());
                    repo.save(subject);
                    return "Subject updated successfully";
                })
                .orElse("Subject not updated");
    }
}

