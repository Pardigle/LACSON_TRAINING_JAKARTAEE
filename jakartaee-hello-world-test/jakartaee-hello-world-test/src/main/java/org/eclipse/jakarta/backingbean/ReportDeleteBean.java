package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ReportDeleteBean {

    private int index;

    @Inject
    private ReportRepository reportRepository;

    public String delete() {
        reportRepository.delete(index);
        return "/reportList.xhtml?faces-redirect=true";
    }

    public int getIndex() { 
    	return index; 
    }
    
    public void setIndex(int index) {
    	this.index = index; 
    }
}