package br.com.devdojo.demo;

import br.com.devdojo.model.Student;
import br.com.devdojo.repository.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // usando o banco normal e não o h2
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    public ExpectedException thrown = ExpectedException.none(); // quais esseções esperar

    @Test
    public void createShouldPersistData() {
        Student student = new Student("Willian", "willian@mail.com.br");
        this.studentRepository.save(student);
        Assertions.assertThat(student.getId()).isNotNull();
        Assertions.assertThat(student.getName()).isEqualTo("Willian");
        Assertions.assertThat(student.getEmail()).isEqualTo("willian@mail.com.br");

    }

    @Test
    public void deleteShoudRemoveData() {
        Student student = new Student("Willian", "willian@mail.com.br");
        this.studentRepository.save(student);
        this.studentRepository.delete(student);
        Assertions.assertThat(this.studentRepository.findById(student.getId())).isNull();
    }

    @Test
    public void updateShoudChangeAndPersistData() {
        Student student = new Student("Willian", "willian@mail.com.br");
        this.studentRepository.save(student);
        student.setName("Willian222");
        student.setEmail("willian222@mail.com.br");
        this.studentRepository.save(student);

        student = this.studentRepository.findById(student.getId()).get();

        this.studentRepository.delete(student);
        Assertions.assertThat(student.getName()).isEqualTo("Willian222");
        Assertions.assertThat(student.getName()).isEqualTo("willian222@mail.com.br");
    }

    @Test
    public void findByNameIgnoreCaseContainingShoudIgnoreCase() {
        Student student = new Student("Willian", "willian@mail.com.br");
        Student student2 = new Student("willian", "willian222@mail.com.br");
        this.studentRepository.save(student);
        this.studentRepository.save(student2);

        List<Student> studentList = this.studentRepository.findByNameIgnoreCaseContaining("willian");

        Assertions.assertThat(studentList.size()).isEqualTo(2);
    }
    @Test
    public void createWhenNameIsNullShoudThrowConstraintViolatoinException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Campo nome do estudante é obrigatório");
        this.studentRepository.save(new Student());
    }

    @Test
    public void createWhenEmailIsNullShoudThrowConstraintViolatoinException() {
        thrown.expect(ConstraintViolationException.class);
        Student student = new Student();
        student.setName("Willian");
        student.setEmail("");
        this.studentRepository.save(new Student());
    }

    @Test
    public void createWhenEmailIsNotValidShoudThrowConstraintViolatoinException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("Digite um email válido");

        Student student = new Student();
        student.setName("Willian");
        student.setEmail("asdf");
        this.studentRepository.save(new Student());
    }
}
