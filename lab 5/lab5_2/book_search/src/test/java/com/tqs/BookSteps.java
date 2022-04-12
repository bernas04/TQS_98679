package com.tqs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class BookSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
    
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDateTime isoDate(String year, String month, String day){
        return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
    }


	@Given("a/another book with the title {string}, written by {string}, published in {isoDate}")
	public void addNewBook(final String title, final String author, final LocalDateTime published) {
		Book book = new Book(title, author, Date.from(published.toInstant(ZoneOffset.UTC)));
		library.addBook(book);
	}

	@Given("a/another {string} book with the title {string}, written by {string}, published in {isoDate}")
	public void addNewBook(final String category, final String title, final String author, final LocalDateTime published) {
		Book book = new Book(category, title, author, Date.from(published.toInstant(ZoneOffset.UTC)));
		library.addBook(book);
	}

	@Given("I have the following books in the store")
	public void haveBooksInTheStoreByList(DataTable table) {
		List<List<String>> rows = table.asLists(String.class);
		for (List<String> columns : rows) {
			library.addBook(new Book(columns.get(0), Integer.parseInt(columns.get(1))));
		}
	}

	@When("I search for books that costs more than {int}")
	public void setPriceSearch(int price){
		result = library.findBooksByPrice(price);
	}
 
	@When("the customer searches for books published between {isoDate} and {isoDate}")
	public void setSearchParameters(final LocalDateTime comeco, final LocalDateTime fim) {
		Date from = Date.from(comeco.toInstant(ZoneOffset.UTC));
		Date to = Date.from(fim.toInstant(ZoneOffset.UTC));
        result = library.findBooks(from, to);
	}

	@When("the customer searches for books written by {string}")
	public void setAuthorSearch(final String author){
		result = library.findBooksByAuthor(author);
	}

	@When("the customer searches for {string} books")
	public void setCategorySearch(final String category){
		result = library.findBooksByCategory(category);
	}
 
	@Then("{int} book(s) should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

	@Then("I find {int} books")
	public void verifyQuantity(final int quantity) {
		assertEquals(result.size(), quantity);
	}
}