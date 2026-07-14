package org.eclipse.jakarta.backingbean;

import org.eclipse.jakarta.dto.ReportDto;
import org.eclipse.jakarta.infrastracture.repository.ReportRepository;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.validation.constraints.NotBlank;

@Named @RequestScoped
public class ReportUpdateBean {

    private int index;

    @NotBlank(message = "You cannot leave the title blank.")
    private String newTitle;
    private String newDetail;

    @Inject
    private ReportRepository reportRepository;

    public void load() {
        ReportDto report = reportRepository.findAll().get(index);
        this.newTitle = report.getTitle();
        this.newDetail = report.getDetail();
    }

    public String update() {
        ReportDto newReport = new ReportDto(newTitle, newDetail);
        reportRepository.update(index, newReport);
        return "/reportList.xhtml?faces-redirect=true";
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getTitle() {
        return newTitle;
    }

    public void setTitle(String title) {
        this.newTitle = title;
    }

    public String getDetail() {
        return newDetail;
    }

    public void setDetail(String detail) {
        this.newDetail = detail;
    }
}