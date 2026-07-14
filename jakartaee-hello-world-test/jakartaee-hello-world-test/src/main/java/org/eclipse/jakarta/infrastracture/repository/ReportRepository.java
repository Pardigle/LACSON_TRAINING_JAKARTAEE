package org.eclipse.jakarta.infrastracture.repository;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jakarta.dto.ReportDto;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ReportRepository {
    private List<ReportDto> reports = new ArrayList<>();

    public List<ReportDto> findAll() {
        return reports;
    }

    public void create(ReportDto report) {
        reports.add(report);
    }
    
    public void update(int index, ReportDto newReport) {
    	if (index >= 0 && index < reports.size()) {
    		reports.set(index, newReport);
    	}
    }
    
    public void delete(int index) {
    	if (index >= 0 && index < reports.size()) {
    		reports.remove(index);
    	}
    }
}