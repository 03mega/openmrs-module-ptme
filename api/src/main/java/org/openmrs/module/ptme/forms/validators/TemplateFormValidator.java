package org.openmrs.module.ptme.forms.validators;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.ptme.ReportingTemplate;
import org.openmrs.module.ptme.api.PreventTransmissionService;
import org.openmrs.module.ptme.forms.TemplateForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by YEO on 17/11/2018.
 */
@Handler(supports = {TemplateForm.class}, order = 50)
public class TemplateFormValidator implements Validator {

    @Override
    public boolean supports(Class c) {
        return c.equals(TemplateForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TemplateForm form = (TemplateForm) o;

        if (form == null){
            errors.reject("ptme","general error");
        }else {
            ValidationUtils.rejectIfEmpty(errors, "name", "ptme.field.required");
            ValidationUtils.rejectIfEmpty(errors, "content", "ptme.field.required");

            if (!form.getName().isEmpty()) {
                ReportingTemplate ind = Context.getService(PreventTransmissionService.class).getTemplateByName(form.getName());

                if (ind != null) {

                    if (form.getTemplateId() == null) {
                        errors.rejectValue("name", "ptme.form.template.name.duplicated");
                    } else {
                        if (!form.getTemplateId().equals(ind.getTemplateId())) {
                            errors.rejectValue("name", "ptme.form.template.name.duplicated");
                        }
                    }

                }

            }
        }

    }
}
