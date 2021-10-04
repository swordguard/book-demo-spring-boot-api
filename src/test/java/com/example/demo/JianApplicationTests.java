package com.example.demo;

import com.example.demo.models.Book;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@SpringBootTest
class JianApplicationTests {
	private static Validator validator;

	@BeforeAll
	public static void setupValidatorInstance() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	@Test
	public void whenBlankName_thenOneConstraintViolations() {
		Book b1 = new Book("", "xxx");
		Set<ConstraintViolation<Book>> violations = validator.validate(b1);

		assertEquals(1, violations.size());
	}

	@Test
	public void whenBlankNameAndEmpty_thenOneConstraintViolations() {
		Book b1 = new Book(" ", "xxx");
		Set<ConstraintViolation<Book>> violations = validator.validate(b1);

		assertEquals(1, violations.size());
	}

	@Test
	public void whenNullName_thenOneConstraintViolations() {
		Book b1 = new Book(null, "xxx");
		Set<ConstraintViolation<Book>> violations = validator.validate(b1);

		assertEquals(1, violations.size());
	}

	@Test
	public void whenNullAuthor_thenNoConstraintViolations() {
		Book b1 = new Book("null", null);
		Set<ConstraintViolation<Book>> violations = validator.validate(b1);

		assertEquals(0, violations.size());
	}

	@Test
	public void toMap() {
		Map<String, Integer> result = IntStream.range(1, 10).boxed()
				.collect(Collectors.toMap(i -> "a", Function.identity(), Integer::sum));
		assertEquals(1, result.size());
		assertEquals(45, result.get("a"));
	}
}
