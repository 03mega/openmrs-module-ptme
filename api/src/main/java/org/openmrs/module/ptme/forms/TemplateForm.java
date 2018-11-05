package org.openmrs.module.ptme.forms;

import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingTemplate;
import org.openmrs.module.ptme.utils.UsefullFunction;

import java.util.Date;

/**
 * Created by YEO on 25/10/2018.
 */
public class TemplateForm {
    private String mode;
    private Integer templateId;
    private String name;
    private String description;
    private byte[] content;
    private String filePath;

    public TemplateForm() {
    }

    public TemplateForm(String name) {
        this.name = name;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setTemplate(ReportingTemplate template) {
        this. setTemplateId(template.getTemplateId());
        this.setName(template.getName());
        this.setDescription(template.getDescription());
        this.setContent(template.getContent());

    }

    public ReportingTemplate getTemplate (ReportingTemplate template) {
        template.setTemplateId(this.getTemplateId());
        template.setName(this.getName());
        template.setDescription(this.getDescription());
        template.setContent(getFilePath().getBytes());
        /*template.setContent(this.getContent);*/

        if (template.getCreator() == null){
            template.setCreator(Context.getAuthenticatedUser());
            template.setDateCreated(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if (this.getTemplateId() != null) {
            template.setChangedBy(Context.getAuthenticatedUser());
            template.setDateChanged(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }
        if(template.getVoided()) {
            template.setVoidedBy(Context.getAuthenticatedUser());
            template.setDateVoided(UsefullFunction.formatDateToddMMyyyyhms(new Date()));
        }

        return template ;
    }

}
