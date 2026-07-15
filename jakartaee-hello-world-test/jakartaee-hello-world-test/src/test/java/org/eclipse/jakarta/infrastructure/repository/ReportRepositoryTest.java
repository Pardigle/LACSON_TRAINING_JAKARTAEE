package org.eclipse.jakarta.infrastructure.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ReportRepositoryTest {

	private ReportRepository reportRepository;
	private ReportDto testReport1;
	private ReportDto testReport2;
	
	@BeforeEach
	void setUp() {
		reportRepository = new ReportRepository();
		
		testReport1 = new ReportDto();
		testReport1.setTitle("Test Report 1");
		testReport1.setDetail("This is the first report.");
		
		testReport2 = new ReportDto();
		testReport2.setTitle("Test Report 2");
		testReport2.setDetail("This is the second report.");
	}
	
	@AfterEach
	void tearDown() {
		reportRepository = null;
		testReport1 = null;
		testReport2 = null;
	}
	
	/**
	 * Creating a new report must remain in the report repository.
	 * 
	 * Asserts:
	 * (1) Whether the title of the report is not null.
	 * (2) The size of report repository is one.
	 */
	@Test
	void testCreate() {
		reportRepository.create(testReport1);
		
		assertNotNull(
				testReport1.getTitle(), 
				"Report name should not be null after creation.");
		assertEquals(1, reportRepository.findAll().size());
	}
	
	/**
	 * Reading a report must allow return of title and detail.
	 * 
	 * Asserts:
	 * (1) Equality of title and detail of test report 1 and 2.
	 */
	@Test
	void testRead() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
		
		assertEquals(
				"Test Report 1", 
				reportRepository.findAll().get(0).getTitle());
		assertEquals(
				"This is the first report.", 
				reportRepository.findAll().get(0).getDetail());
		
		assertEquals(
				"Test Report 2", 
				reportRepository.findAll().get(1).getTitle());
		assertEquals(
				"This is the second report.", 
				reportRepository.findAll().get(1).getDetail());
	}
	
	/**
	 * Updating a report must override its title and detail.
	 * 
	 * Asserts:
	 * (1) Whether the title and detail changed for testReport1.
	 */
	@Test
	void testUpdate() {
		reportRepository.create(testReport1);
		reportRepository.update(0, testReport2);
		
		assertEquals(
				"Test Report 2", 
				reportRepository.findAll().get(0).getTitle());
		assertEquals(
				"This is the second report.", 
				reportRepository.findAll().get(0).getDetail());
		assertEquals(1, reportRepository.findAll().size());
	}
	
	/**
	 * Deleting a report should modify the report repository.
	 * 
	 * Asserts:
	 * (1) Whether the index 0 of report repository changed to test report 2.
	 * (2) If the size of the report repository changed to 1.
	 * (3) Whether getting the index 1 throws an out of bounds exception.
	 */
	@Test
	void testDelete() {
		reportRepository.create(testReport1);
		reportRepository.create(testReport2);
		
		reportRepository.delete(0);
		Executable getOutOfBounds = () -> reportRepository.findAll().get(1);
		
		assertEquals(
				"Test Report 2", 
				reportRepository.findAll().get(0).getTitle());
		assertEquals(
				"This is the second report.", 
				reportRepository.findAll().get(0).getDetail());
		assertEquals(1, reportRepository.findAll().size());
		assertThrows(IndexOutOfBoundsException.class, getOutOfBounds);
	}
	
	/**
	 * Updating out of bounds must be ignored.
	 * 
	 * Asserts:
	 * (1) If the element in the repository remained the same.
	 * (2) If the size of the repository remained as 1.
	 */
	@Test
	void testAttemptUpdateOutOfBounds() {
		reportRepository.create(testReport1);
		reportRepository.update(-1, testReport2);
		reportRepository.update(1, testReport2);
		
		assertEquals(
				"Test Report 1", 
				reportRepository.findAll().get(0).getTitle());
		assertEquals(
				"This is the first report.", 
				reportRepository.findAll().get(0).getDetail());
		assertEquals(1, reportRepository.findAll().size());
	}
	
	/**
	 * Deleting out of bounds must be ignored.
	 * 
	 * Asserts:
	 * (1) If the element in the repository remained the same.
	 * (2) If the size of the repository remained as 1.
	 */
	@Test
	void testAttemptDeleteOutOfBounds() {
		reportRepository.create(testReport1);
		reportRepository.delete(-1);
		reportRepository.delete(1);
		
		assertEquals(
				"Test Report 1", 
				reportRepository.findAll().get(0).getTitle());
		assertEquals(
				"This is the first report.", 
				reportRepository.findAll().get(0).getDetail());
		assertEquals(1, reportRepository.findAll().size());
	}
}
